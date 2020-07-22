package com.example.zip;

import org.apache.commons.io.FileUtils;
import org.junit.platform.commons.logging.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 原生的: 压缩文件
 */

public class PrimitiveDemo {


    private static final Logger logger = (Logger) LoggerFactory.getLogger(PrimitiveDemo.class);
    private static final int BUFFER_SIZE = 2 * 1024;

    //主函数
    public static void main(String[] args) {
        try {
            //压缩文件名称
            File file = new File("G:\\original.zip");
            //创建文件输出流
            FileOutputStream fileOutputStream = FileUtils.openOutputStream(file);
            //压缩文件
            toZip("G:\\original", fileOutputStream, true);
        } catch (IOException e) {
            System.out.println("压缩文件异常");
        }
    }


    /**
     * 压缩成ZIP
     *
     * @param srcDir           压缩文件夹路径
     * @param out              压缩文件输出流
     * @param keepDirStructure 是否保留原来的目录结构(true:保留目录结构;false:所有文件跑到压缩包根目录下)
     *                         (注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    private static void toZip(String srcDir, OutputStream out, boolean keepDirStructure)
            throws RuntimeException {
        //记录压缩开始时间
        long start = System.currentTimeMillis();
        //创建zip输出流
        try (ZipOutputStream zos = new ZipOutputStream(out)) {
            File sourceFile = new File(srcDir);
            compress(sourceFile, zos, sourceFile.getName(), keepDirStructure);
            //记录压缩结束时间
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) + " ms");
        } catch (Exception e) {
            System.out.println("压缩文件{}异常");
        }
    }

    /**
     * 递归压缩方法
     *
     * @param sourceFile       源文件
     * @param zos              zip输出流
     * @param name             压缩后的名称
     * @param keepDirStructure 是否保留原来的目录结构(true:保留目录结构;false:所有文件跑到压缩包根目录下)
     *                         (注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception 异常
     */
    private static void compress(File sourceFile,
                                 ZipOutputStream zos,
                                 String name,
                                 boolean keepDirStructure) throws Exception {
        byte[] buf = new byte[BUFFER_SIZE];
        if (sourceFile.isFile()) {
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                if (keepDirStructure) {
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();
                }
            } else {
                for (File file : listFiles) {
                    // 判断是否需要保留原来的文件结构
                    if (keepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, zos, name + "/" + file.getName(), keepDirStructure);
                    } else {
                        compress(file, zos, file.getName(), keepDirStructure);
                    }
                }
            }
        }
    }


    /**
     * 压缩成ZIP 压缩多个文件
     *
     * @param srcFiles 需要压缩的文件列表
     * @param out      压缩文件输出流
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    private static void toZip(List<File> srcFiles, OutputStream out) throws RuntimeException {
        long start = System.currentTimeMillis();
        try (ZipOutputStream zos = new ZipOutputStream(out)) {
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[BUFFER_SIZE];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) + " ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);
        }
    }

}
