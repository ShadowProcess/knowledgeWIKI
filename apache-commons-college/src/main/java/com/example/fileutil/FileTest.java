package com.example.fileutil;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.zip.CRC32;

public class FileTest {

    @Test
    public void _0() throws IOException {
        File fileA = new File("E:\\test\\test.txt");
        File fileB = new File("E:\\test1\\test.txt");
        FileUtils.copyFile(fileA, fileB);

        FileUtils.checksum(fileA, new CRC32());
    }
}
