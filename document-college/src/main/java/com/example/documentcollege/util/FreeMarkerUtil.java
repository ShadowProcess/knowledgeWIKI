package com.example.documentcollege.util;

import com.google.common.collect.Maps;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * FREEMARKER 模板工具类
 */
public class FreeMarkerUtil {

    private static Logger log = LoggerFactory.getLogger(FreeMarkerUtil.class);

    private static final String WINDOWS_SPLIT = "\\";

    private static final String UTF_8 = "UTF-8";

    private static final String TEMPLATES_URL = "classpath:templates";

    private static Map<String, FileTemplateLoader> fileTemplateLoaderCache = Maps.newConcurrentMap();

    private static Map<String, Configuration> configurationCache = Maps.newConcurrentMap();

    public static Configuration getConfiguration(String templateFilePath) {
        if (null != configurationCache.get(templateFilePath)) {
            return configurationCache.get(templateFilePath);
        }
        Configuration config = new Configuration(Configuration.VERSION_2_3_25);
        config.setDefaultEncoding(UTF_8);
        config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        config.setLogTemplateExceptions(false);
        FileTemplateLoader fileTemplateLoader = null;
        if (null != fileTemplateLoaderCache.get(templateFilePath)) {
            fileTemplateLoader = fileTemplateLoaderCache.get(templateFilePath);
        }
        try {
            fileTemplateLoader = new FileTemplateLoader(new File(templateFilePath));
            fileTemplateLoaderCache.put(templateFilePath, fileTemplateLoader);
        } catch (IOException e) {
            throw new RuntimeException("fileTemplateLoader init error!", e);
        }
        config.setTemplateLoader(fileTemplateLoader);
        configurationCache.put(templateFilePath, config);
        return config;

    }


    /**
     * @description 获取模板
     */
    public static String getContent(String fileName, Object data) {
        String templatePath = getPDFTemplatePath(fileName);
        String templateFileName = getTemplateName(templatePath);
        String templateFilePath = getTemplatePath(templatePath);
        log.info("模板的url:{}", templatePath);
        if (StringUtils.isEmpty(templatePath)) {
            log.error("templatePath can not be empty!");
            throw new RuntimeException("templatePath can not be empty!");
        }
        try {
            Template template = getConfiguration(templateFilePath).getTemplate(templateFileName);
            StringWriter writer = new StringWriter();
            template.process(data, writer);
            writer.flush();
            return writer.toString();
        } catch (Exception ex) {
            log.error("FreeMarkerUtil process fail", ex);
            throw new RuntimeException("FreeMarkerUtil process fail", ex);
        }
    }


    private static String getTemplatePath(String templatePath) {
        if (StringUtils.isEmpty(templatePath)) {
            return "";
        }
        if (templatePath.contains(WINDOWS_SPLIT)) {
            return templatePath.substring(0, templatePath.lastIndexOf(WINDOWS_SPLIT));
        }
        return templatePath.substring(0, templatePath.lastIndexOf("/"));
    }

    private static String getTemplateName(String templatePath) {
        if (StringUtils.isEmpty(templatePath)) {
            return "";
        }
        if (templatePath.contains(WINDOWS_SPLIT)) {
            return templatePath.substring(templatePath.lastIndexOf(WINDOWS_SPLIT) + 1);
        }
        return templatePath.substring(templatePath.lastIndexOf("/") + 1);
    }

    /**
     * @param fileName PDF文件名    (hello.pdf)
     * @return 匹配到的模板名
     * @description 获取PDF的模板路径,
     * 默认按照PDF文件名匹对应模板
     */
    public static String getPDFTemplatePath(String fileName) {
        log.info("PDF文件名:{}", fileName);
        File file = null;
        try {
            file = ResourceUtils.getFile(TEMPLATES_URL);
            log.debug("templates的url:{}", file.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!file.isDirectory()) {
            log.error("PDF模板文件不存在,请检查templates文件夹!");
            throw new RuntimeException("PDF模板文件不存在,请检查templates文件夹!");
        }
        String pdfFileName = fileName.substring(0, fileName.lastIndexOf("."));
        File defaultTemplate = null;
        File matchTemplate = null;
        for (File f : file.listFiles()) {
            if (!f.isFile()) {
                continue;
            }
            String templateName = f.getName();
            if (templateName.lastIndexOf(".ftl") == -1) {
                continue;
            }
            if (defaultTemplate == null) {
                defaultTemplate = f;
            }
            if (StringUtils.isEmpty(fileName) && defaultTemplate != null) {
                break;
            }
            templateName = templateName.substring(0, templateName.lastIndexOf("."));
            if (templateName.toLowerCase().equals(pdfFileName.toLowerCase())) {
                matchTemplate = f;
                break;
            }
        }
        if (matchTemplate != null) {
            return matchTemplate.getAbsolutePath();
        }
        if (defaultTemplate != null) {
            return defaultTemplate.getAbsolutePath();
        }
        return null;
    }

}
