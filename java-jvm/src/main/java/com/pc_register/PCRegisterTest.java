package com.pc_register;

// Pc寄存器

public class PCRegisterTest {

    public static void main(String[] args) {
        int i = 10;
        int j = 20;
        int k = i + j;

        String s = "abc";
        System.out.println(i);
        System.out.println(k);

    }
}

// javap -verbose PCRegisterTest.class

/*
public static void main(java.lang.String[]);
        descriptor: ([Ljava/lang/String;)V
        flags: ACC_PUBLIC, ACC_STATIC
        Code:
        stack=2, locals=5, args_size=1
        0: bipush        10
        2: istore_1
        3: bipush        20
        5: istore_2
        6: iload_1
        7: iload_2
        8: iadd
        9: istore_3
        10: ldc           #2                  // String abc
        12: astore        4
        14: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
        17: iload_1
        18: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
        21: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
        24: iload_3
        25: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
        28: return
        LineNumberTable:
        line 7: 0
        line 8: 3
        line 9: 6
        line 11: 10
        line 12: 14
        line 13: 21
        line 15: 28
        LocalVariableTable:
        Start  Length  Slot  Name   Signature
        0      29     0  args   [Ljava/lang/String;
        3      26     1     i   I
        6      23     2     j   I
        10      19     3     k   I
        14      15     4     s   Ljava/lang/String;
        }
        SourceFile: "PCRegisterTest.java"
        */

