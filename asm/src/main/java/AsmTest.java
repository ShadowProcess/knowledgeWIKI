import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ASM 能够通过改造既有类，直接生成需要的代码。增强的代码是硬编码在新生成的类文件内部的，
 * 没有反射带来性能上的付出。同时，ASM 与 Proxy 编程不同，不需要为增强代码而新定义一个接口，
 * 生成的代码可以覆盖原来的类，或者是原始类的子类。它是一个普通的 Java 类而不是 proxy 类，
 * 甚至可以在应用程序的类框架中拥有自己的位置，派生自己的子类。
 *
 * 相比于其他流行的 Java 字节码操纵工具，ASM 更小更快。ASM 具有类似于 BCEL 或者 SERP 的功能，而只有 33k 大小，
 * 而后者分别有 350k 和 150k。同时，同样类转换的负载，如果 ASM 是 60% 的话，
 * BCEL 需要 700%，而 SERP 需要 1100% 或者更多。
 */

public class AsmTest {
    public static void main(String[] args) throws IOException {
        ClassWriter classWriter = new ClassWriter(0);
        String className = "com/AsmTest";
        classWriter.visit(Opcodes.V1_5,Opcodes.ACC_PUBLIC,className,null,"java/lang/Object",null);

        MethodVisitor initVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC,"<init>","V()",null,null);
        initVisitor.visitCode();
        initVisitor.visitVarInsn(Opcodes.ALOAD,0);
        initVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL,"java/lang/Object","<init>","V()");

        initVisitor.visitInsn(Opcodes.RETURN);
        initVisitor.visitMaxs(1,1);
        initVisitor.visitEnd();

        MethodVisitor helloVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "sayHello",
                "()V;", null, null);
        helloVisitor.visitCode();
        helloVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out",
                "Ljava/io/PrintStream;");
        helloVisitor.visitLdcInsn("hello world!");
        helloVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
                "println", "(Ljava/lang/String;)V");
        helloVisitor.visitInsn(Opcodes.RETURN);
        helloVisitor.visitMaxs(1, 1);
        helloVisitor.visitEnd();

        classWriter.visitEnd();
        byte[] code = classWriter.toByteArray();
        File file = new File("D:\\DevelopmentSoftWare\\ideaWebWorkSpace\\knowledgeWIKI\\asm\\src\\main\\java\\com\\AsmTest.class");
        FileOutputStream output = new FileOutputStream(file);
        output.write(code);
        output.close();

    }
}
