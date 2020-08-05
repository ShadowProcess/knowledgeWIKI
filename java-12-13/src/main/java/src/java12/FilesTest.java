package src.java12;

import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class FilesTest {

    //使用IDEA的单元测试方法，默认的相对路径是在当前module下
    //IO : File
    //NIO 2 : Files操作本地文件的工具类  ； Path:替换原有的File ; Paths :实例化Path
    @Test
    public void testFilesMismatch() throws IOException {
        FileWriter fileWriter = new FileWriter("a.txt");
        fileWriter.write("x");
        fileWriter.write("y");
        fileWriter.write("z");
        fileWriter.close();

        FileWriter fileWriterB = new FileWriter("b.txt");
        fileWriterB.write("x");
        fileWriterB.write("y");
        fileWriterB.write("z");
        fileWriterB.close();

        System.out.println(Files.mismatch(Path.of("a.txt"),Path.of("b.txt")));
    }

}
