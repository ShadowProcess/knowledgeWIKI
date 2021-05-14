//package example.inputstream;
//
//import java.io.FileOutputStream;
//
//import org.junit.Test;
//
//public class InputStreamTest {
//
//
//	/**
//	 * transferTo  直接两个流的对接
//	 */
//    @Test
//    public void testName() throws Exception {
//        var cl = this.getClass().getClassLoader();
//        try (var is = cl.getResourceAsStream("file");
//             var os = new FileOutputStream("file2")) {
//            is.transferTo(os); //把输入流中的数据直接自动复制到输出流中
//        }
//    }
//}
