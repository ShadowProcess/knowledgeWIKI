package src.example.text_blocks;

import org.junit.Test;

/**
 * 文本块(text blocks)的使用
 */
public class Feature05 {

    @Test
    public void test1(){
        String text1 = "The Sound of silence\n" +
                "Hello darkness, my old friend\n" +
                "I've come to talk with you again\n" +
                "Because a vision softly creeping\n" +
                "Left its seeds while I was sleeping\n" +
                "And the vision that was planted in my brain\n" +
                "Still remains\n" +
                "Within the sound of silence";

        System.out.println(text1);

        //jdk13中的新特性：
        String text2 = """
                The Sound of silence
                Hello darkness, my old friend
                I've come to talk with you again
                Because a vision softly creeping
                Left its seeds while I was sleeping
                And the vision that was planted in my brain
                Still remains
                Within the sound of silence
                """;
        System.out.println();
        System.out.println(text2);

        System.out.println(text1.length());
        System.out.println(text2.length());
    }

    //html
    @Test
    public void test2(){
        String html1 = "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>java14新特性</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <p>hello,atguigu</p>\n" +
                "</body>\n" +
                "</html>";
        //jdk13中的新特性：
        String html2 = """
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>java14新特性</title>
                </head>
                <body>
                    <p>hello,atguigu</p>
                </body>
                </html>
                """;
    }

    //json
    @Test
    public void test3() {
        //jdk13之前的写法
        String myJson = "{\n" +
                "    \"name\":\"Song Hongkang\",\n" +
                "     \"address\":\"www.atguigu.com\",\n" +
                "    \"email\":\"shkstart@126.com\"\n" +
                "}";
        System.out.println(myJson);

        //jdk13的新特性
        String myJson1 = """
                {
                    "name":"Song Hongkang",
                     "address":"www.atguigu.com",
                    "email":"shkstart@126.com"
                }""";
        System.out.println(myJson1);
    }

    //sql
    @Test
    public void test4(){
        String sql = "SELECT id,NAME,email\n" +
                "FROM customers\n" +
                "WHERE id > 4\n" +
                "ORDER BY email DESC";

        //jdk13新特性：
        String sql1 = """
                SELECT id,NAME,email
                FROM customers
                WHERE id > 4
                ORDER BY email DESC
                """;
    }

    //jdk14新特性
    @Test
    public void test5(){
        String sql1 = """
                SELECT id,NAME,email
                FROM customers
                WHERE id > 4
                ORDER BY email DESC
                """;
        System.out.println(sql1);

        // \:取消换行操作
        // \s:表示一个空格
        String sql2 = """
                SELECT id,NAME,email \
                FROM customers\s\
                WHERE id > 4 \
                ORDER BY email DESC
                """;
        System.out.println(sql2);
    }
}
