package com.example.controller.static_study;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.stream.Stream;

@Component
public class GraphImg {

    public static GraphImg SELF;

    @Autowired
    public void setSelf(GraphImg self) {
        SELF = self;
    }

    public static GraphImg self() {
        return SELF;
    }
    private static final SecureRandom random = new SecureRandom();
    private static final String randDigit = "0123456789"; // 随机字符串范围
    private static final int width = 80; // 图片宽
    private static final int height = 26;// 图片高

    private static Font getFont() {
        return new Font("Fixedsys", Font.BOLD, 18);
    }

    private static Color getRandColor() {
        int fc = 110, bc = 133; //两者值最大上限255
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /**
     * 绘制字符串
     */
    private static String drawString(Graphics g, String randomString, int i) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(randDigit.length())));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }

    /**
     * 绘制干扰线
     */
    private static void drawLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * 获取随机的字符
     */
    public static String getRandomString(int num) {
        return String.valueOf(randDigit.charAt(num));
    }


    public static String makeGraph(HttpServletRequest request, HttpServletResponse response) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        g.setColor(getRandColor());
        // 绘制干扰线
        Stream.iterate(0, x -> x + 1)
                .limit(5)
                .forEach(y ->
                        drawLine(g)
                );
        // 绘制随机字符
        String randomString = "";
        for (int i = 1; i <= 4; i++) {
            randomString = drawString(g, randomString, i);
        }
//        session.removeAttribute(GRAPH_KEY);
//        session.setAttribute(GRAPH_KEY, randomString);
        g.dispose();
        OutputStream os = null;
        try {
            response.setContentType("image/jpeg");              //设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            os = response.getOutputStream();
            ImageIO.write(image, "JPEG", os);// 将内存中的图片通过流动形式输出到客户端
            return randomString;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }
}
