package com.example;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DailyTest {

    @Test
    public void _0() {
        Object o = Optional.ofNullable(null).isPresent();
    }

    @Test
    public void _1() {
        boolean ss = StringUtils.equals("-1", "ss");
        System.out.println(ss);
    }

    public static <T, E> void s(T s, E y) {
    }

    @Test
    public void _0_() {
        s("ss", "ff");
    }



    //仅显示姓名最后一位，其余用*代替
    @Test
    public void _() {
        String name = "字";

        int i = name.length() - 1;
        String substring = name.substring(name.length() - 1);
        String mi = "";
        for (int j = 0; j < i; j++) {
            mi += "*";
        }
        System.out.println(mi + substring);
    }

    @Test
    public void ii(){
        int monthValue = LocalDate.now().getMonthValue();
        int day = LocalDate.now().getDayOfMonth();
        System.out.println(String.format("%d月%d日",monthValue,day));
    }

    @Test
    public void OO(){
        LocalDateTime openStart = LocalDateTime.of(2020, 8, 6, 0, 0, 0);
        LocalDateTime openEnd = LocalDateTime.of(2020, 8, 8, 0, 0, 0);
        Duration between = Duration.between(openStart, openEnd);
        System.out.println(between.toDays());

        for (int i = 0; i < between.toDays(); i++) {
            System.out.println(openStart.plusDays(i).toString());
        }
    }
}
