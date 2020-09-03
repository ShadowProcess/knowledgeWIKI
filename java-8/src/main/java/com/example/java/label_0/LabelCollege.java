package com.example.java.label_0;

import org.junit.Test;

public class LabelCollege {

    @Test
    public void labelTest(){

        outer:for (int i = 0; i < 100; i++) {
            inner:for (int j = 0; j < 10; j++) {
                System.out.println(j);
                if (j < 5) {
                    continue outer;
                }
            }
        }


    }
}
