package com.example.documentcollege.service;

import com.example.documentcollege.util.PDFHeaderFooter;
import com.example.documentcollege.util.PDFKit;
import com.example.documentcollege.util.TemplateBO;
import com.example.documentcollege.util.XYLine;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class PDFService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String FILE_OUTPUT_URL = "D:\\123.pdf";

    public void exportToResponse(HttpServletResponse response) {
        //设置自定义PDF页眉页脚工具类
        PDFHeaderFooter headerFooter = new PDFHeaderFooter();
        PDFKit pdfKit = new PDFKit();
        pdfKit.setHeaderFooterBuilder(headerFooter);

        response.setContentType("application/force-download");// 设置强制下载不打开
        response.addHeader("Content-Disposition", "attachment;fileName=" + "11.pdf");// 设置文件名


        TemplateBO templateBO = new TemplateBO();
        templateBO.setTemplateName("Hello iText! Hello freemarker! Hello jFreeChart!");
        templateBO.setFreeMarkerUrl("http://www.zheng-hang.com/chm/freemarker2_3_24/ref_directive_if.html");
        templateBO.setITEXTUrl("http://developers.itextpdf.com/examples-itext5");
        templateBO.setJFreeChartUrl("http://www.yiibai.com/jfreechart/jfreechart_referenced_apis.html");
        templateBO.setImageUrl("http://mss.vip.sankuai.com/v1/mss_74e5b6ab17f44f799a524fa86b6faebf/360report/logo_1.png");

        pdfKit.exportToResponse("custom_pdf.ftl", templateBO, response);


//        List<String> scores=new ArrayList<String>();
//        scores.add("90");
//        scores.add("95");
//        scores.add("98");
////        templateBO.setScores(scores);
//        //折线图
//        List<XYLine> lineList=getTemperatureLineList();
//        DefaultLineChart lineChart=new DefaultLineChart();
//        lineChart.setHeight(500);
//        lineChart.setWidth(300);
//        String picUrl=lineChart.draw(lineList,0);
//        templateBO.setPicUrl(picUrl);

        //散点图
//        String scatterUrl=ScatterPlotChart.draw(ScatterPlotChartTest.getData(),1,"他评得分(%)","自评得分(%)");
//        templateBO.setScatterUrl(scatterUrl);
//        pdfKit.exportToResponse(templatePath, templateBO, response);

    }

    /**
     * 根据路径导出pdf
     */
    public void pdfExport() {
        TemplateBO templateBO = new TemplateBO();
        templateBO.setTemplateName("Hello iText! Hello freemarker! Hello jFreeChart!");
        templateBO.setFreeMarkerUrl("http://www.zheng-hang.com/chm/freemarker2_3_24/ref_directive_if.html");
        templateBO.setITEXTUrl("http://developers.itextpdf.com/examples-itext5");
        templateBO.setJFreeChartUrl("http://www.yiibai.com/jfreechart/jfreechart_referenced_apis.html");
        templateBO.setImageUrl("http://mss.vip.sankuai.com/v1/mss_74e5b6ab17f44f799a524fa86b6faebf/360report/logo_1.png");
//        List<String> scores=new ArrayList<String>();
//        scores.add("90");
//        scores.add("95");
//        scores.add("98");
////        templateBO.setScores(scores);
//        //折线图
//        List<XYLine> lineList=getTemperatureLineList();
//        DefaultLineChart lineChart=new DefaultLineChart();
//        lineChart.setHeight(500);
//        lineChart.setWidth(300);
//        String picUrl=lineChart.draw(lineList,0);
//        templateBO.setPicUrl(picUrl);

        //散点图
//        String scatterUrl=ScatterPlotChart.draw(ScatterPlotChartTest.getData(),1,"他评得分(%)","自评得分(%)");
//        templateBO.setScatterUrl(scatterUrl);
//        pdfKit.exportToResponse(templatePath, templateBO, response);
        String path = createPDF(templateBO, "hello.pdf");
        System.out.println(path);

    }


    /**
     * @param data
     * @param templatePath 模板的名称
     * @return
     */
    public static String createPDF(Object data, String templatePath) {
        //pdf保存路径
        try {
            //设置自定义PDF页眉页脚工具类
            PDFHeaderFooter headerFooter = new PDFHeaderFooter();
            PDFKit kit = new PDFKit();
            kit.setHeaderFooterBuilder(headerFooter);
            //设置输出路径
            kit.setSaveFilePath(FILE_OUTPUT_URL);
            String saveFilePath = kit.exportToFile(templatePath, data);
            return saveFilePath;
        } catch (Exception e) {

            return null;
        }
    }

    public static List<XYLine> getTemperatureLineList() {
        List<XYLine> list = Lists.newArrayList();
        for (int i = 1; i <= 7; i++) {
            XYLine line = new XYLine();
            float random = Math.round(Math.random() * 10);
            line.setxValue("星期" + i);
            line.setyValue(20 + random);
            line.setGroupName("下周");
            list.add(line);
        }
        for (int i = 1; i <= 7; i++) {
            XYLine line = new XYLine();
            float random = Math.round(Math.random() * 10);
            line.setxValue("星期" + i);
            line.setyValue(20 + random);
            line.setGroupName("这周");
            list.add(line);
        }
        return list;
    }

}
