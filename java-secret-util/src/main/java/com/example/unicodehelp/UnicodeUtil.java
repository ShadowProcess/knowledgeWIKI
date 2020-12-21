package com.example.unicodehelp;


import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnicodeUtil {

    public static String encoding(String string) {
        StringBuilder sbr = new StringBuilder(string.length() * 4);
        for (char ch : string.toCharArray()) {
            if (ch > 0xfff) {
                sbr.append("\\u");
                sbr.append(Integer.toHexString(ch).toUpperCase(Locale.ENGLISH));
            } else if (ch > 0xff) {
                sbr.append("\\u0");
                sbr.append(Integer.toHexString(ch).toUpperCase(Locale.ENGLISH));
            } else if (ch > 0x7f) {
                sbr.append("\\u00");
                sbr.append(Integer.toHexString(ch).toUpperCase(Locale.ENGLISH));
            } else if (ch < 32) {
                switch (ch) {
                    case '\b':
                        sbr.append('\\');
                        sbr.append('b');
                        break;
                    case '\n':
                        sbr.append('\\');
                        sbr.append('n');
                        break;
                    case '\t':
                        sbr.append('\\');
                        sbr.append('t');
                        break;
                    case '\f':
                        sbr.append('\\');
                        sbr.append('f');
                        break;
                    case '\r':
                        sbr.append('\\');
                        sbr.append('r');
                        break;
                    default:
                        if (ch > 0xf) {
                            sbr.append("\\u00");
                            sbr.append(Integer.toHexString(ch).toUpperCase(Locale.ENGLISH));
                        } else {
                            sbr.append("\\u000");
                            sbr.append(Integer.toHexString(ch).toUpperCase(Locale.ENGLISH));
                        }
                        break;
                }
            } else {
                switch (ch) {
                    case '\'':
                        sbr.append("\\u0027");
                        break;
                    case '"':
                    case '\\':
                        sbr.append("\\");
                        sbr.append(ch);
                        break;
                    default:
                        sbr.append(ch);
                        break;
                }
            }
        }
        return sbr.toString();
    }


    public static String decoding(String unicode) {
        char[] chars = unicode.toCharArray();
        StringBuilder buffer = new StringBuilder();
        for (int i = 0, len = chars.length; i < len; ) {
            char c = chars[i++];
            switch (c) {
                case 0:
                case '\n':
                case '\r':
                    break;
                case '\\':
                    c = chars[i++];
                    switch (c) {
                        case 'b':
                            buffer.append('\b');
                            break;
                        case 't':
                            buffer.append('\t');
                            break;
                        case 'n':
                            buffer.append('\n');
                            break;
                        case 'f':
                            buffer.append('\f');
                            break;
                        case 'r':
                            buffer.append('\r');
                            break;
                        case 'u':
                            if (i + 3 > chars.length - 1) {
                                //当前索引+3，如果已经大于总长度了，会脚标越界的  防止\\u5这种情况
                                buffer.append(new String(new char[]{chars[i++]}));
                            } else {
                                buffer.append((char) Integer.parseInt(
                                        new String(new char[]{chars[i++], chars[i++], chars[i++], chars[i++]}), 16)
                                );
                            }
                            break;
                        case 'x':
                            buffer.append((char) Integer.parseInt(
                                    new String(new char[]{chars[i++], chars[i++]}), 16)
                            );
                            break;
                        default:
                            buffer.append(c);
                    }
                    break;
                default:
                    buffer.append(c);
            }
        }
        return buffer.toString();
    }


    //POSIX字符类\p{XDigit}匹配,匹配任意十六进制字符
    public static String unicodeToRecognizable(String unicode) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(unicode);
        char c;
        while (matcher.find()) {
            c = (char) Integer.parseInt(matcher.group(2), 16);
            unicode = unicode.replace(matcher.group(1), c + "");
        }
        return unicode;
    }


    public static void main(String[] args) {
        //String s = decoding("\\ue10b \\u732a\\u732a\\u4fa0^(oo)^\\ud83c\\udf7c");
        //String s = decoding("Tiao\\u00b7Pi\\u00ebch");
        //String s = decoding("\\ue50a \\ue50a \\ue50a \\ue50a \\ue50a \\ue50a \\ue50a \\ue50a \\ue50a \\ue50a \\ue50a");
        //String s = decoding("cy\\ud83c\\udf52\\u4e0a\\u6d77\\u806a\\u5b87\\u6851\\u62ff\\u6d17\\u6d74\\u8db3\\u6d74\\u8f6f\\u4ef6    251");
        //String s = decoding("\\ud83c\\udf1f \\u4f3c\\u662f\\u661f\\u6cb3\\u5165\\u68a6\\ud83c\\udf1f");
        //String s = decoding("cissce\\u2740");
        //String s = decoding("+\\u597d\\u4eba\\u4e00\\u751f\\u5e73\\u5b89");
        //String s = decoding("\\u80e1\\uff5e\\u6b4cStay Strong");
        //String s = decoding("(^\\u03c9^)=\\u261e37\\u8fc8");
        //String s = decoding("\\u53f6\\u80e4--\\u5b50\\u826f--danfox");
        //String s = decoding("\\ud83e\\udd82 \\u30df\\u30cd\\u3000\\u30ea\\u3000\\ud83d\\udde3 \\ud83d\\udd1a");
        //String s = decoding("homer\\uff08\\u4e01\\u6cc9\\u6811\\uff09"); //ok
        //String s = decoding("\\u96ea\\u6c99king");
        //String s = decoding("\\u556f88");

        //TODO 不能识别,把空白替换掉,可以识别
        String s = decoding("\\ud83c\\udf89\\ud83c\\udf7c\\ud83d\\udca4\\u0b18\\u6211\\u957f\\u7684\\u5     56 f88\\u5446".replace(" ",""));
        //String s = decoding("\\ud83c\\udf89\\ud83c\\udf7c\\ud83d\\udca4\\u0b18\\u6211\\u957f\\u7684\\u556f88\\u5446");
        //String s = decoding("\\ud83c\\udf89\\ud83c\\udf7c\\ud83d\\udca4\\u0b18\\u6211\\u957f\\u7684\\u5");
        System.out.println(s);
    }
}
