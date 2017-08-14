package com.example.io;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multiset;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class IODemo {

    //读取文件内容
    @Test
    public void _0() throws IOException {

        File file = new File("D:\\DevelopmentSoftWare\\ideaWebWorkSpace\\knowledgeWIKI\\guava-usage\\src\\main\\java\\com\\example\\GuavaDemo.java");
        ImmutableList<String> strings = Files.asCharSource(file, StandardCharsets.UTF_8).readLines();
        System.out.println(strings);

        System.out.println("======================");
        URL url = Resources.getResource("GuavaDemo.java");
        String s = Resources.toString(url, StandardCharsets.UTF_8);
        System.out.println(s);
    }



    //计算文件hash
    @Test
    public void _1() throws IOException {
        File file = new File("D:\\DevelopmentSoftWare\\ideaWebWorkSpace\\knowledgeWIKI\\guava-usage\\src\\main\\java\\com\\example\\GuavaDemo.java");
        HashCode hash = Files.asByteSource(file).hash(Hashing.sha256());
        System.out.println(hash);
    }



    //读取网络数据，并存储到文件
    @Test
    public void _2() throws IOException {
        File file = new File("D:\\DevelopmentSoftWare\\ideaWebWorkSpace\\knowledgeWIKI\\guava-usage\\src\\main\\java\\com\\example\\GuavaDemo.java");
        Resources.asByteSource(new URL("http://www.baidu.com")).copyTo(Files.asByteSink(file));
    }


    //计算文件中，不同单词出现的次数
    @Test
    public void _3() throws IOException {
        File file = new File("D:\\DevelopmentSoftWare\\ideaWebWorkSpace\\knowledgeWIKI\\guava-usage\\src\\main\\java\\com\\example\\GuavaDemo.java");

        //按照指定分割器分隔文件内容
        Multiset<String> wordOccurrences = HashMultiset.create(
                Splitter.on(CharMatcher.whitespace()) //按照空格分隔文件中的所有内容
                        .trimResults()
                        .omitEmptyStrings()
                        .split(Files.asCharSource(file, Charsets.UTF_8).read()));
        System.out.println(wordOccurrences);

        //计算文件中，指定字符出现次数
        long count = wordOccurrences.stream().filter(it-> it.contains("java")).count();
        System.out.println(count);

    }
}
