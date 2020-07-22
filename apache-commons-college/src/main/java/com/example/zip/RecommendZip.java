package com.example.zip;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RecommendZip {


    private static void toZip() {
        ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry("压缩zip");
        File file = new File("G:\\test1.txt");
        try (FileInputStream inputStream = new FileInputStream(file)) {
            File zipFile = new File("G:\\test1.zip");
            ZipArchiveOutputStream zipArchiveOutputStream = new ZipArchiveOutputStream(zipFile);
            zipArchiveOutputStream.putArchiveEntry(zipArchiveEntry);
            byte[] bytes = new byte[1024];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                zipArchiveOutputStream.write(len);
            }
            zipArchiveOutputStream.closeArchiveEntry();
            zipArchiveOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 解压Zip文件
     *
     * @param zipFile 需要解压的zip文件位置
     * @param destDir 解压的目标位置
     */
    public static void unzip(String zipFile, String destDir) {
        File f;
        try (ArchiveInputStream i = new ArchiveStreamFactory().createArchiveInputStream(
                ArchiveStreamFactory.ZIP,
                Files.newInputStream(Paths.get(zipFile)))) {

            ArchiveEntry entry;

            while ((entry = i.getNextEntry()) != null) {
                if (!i.canReadEntryData(entry)) {
                    continue;
                }
                f = new File(destDir, entry.getName());
                if (entry.isDirectory()) {
                    if (!f.isDirectory() && !f.mkdirs()) {
                        throw new IOException("failed to create directory " + f);
                    }
                } else {
                    File parent = f.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs()) {
                        throw new IOException("failed to create directory " + parent);
                    }
                    try (OutputStream o = Files.newOutputStream(f.toPath())) {
                        IOUtils.copy(i, o);
                    }
                }
            }
        } catch (IOException | ArchiveException e) {
            e.printStackTrace();
        }
    }
}
