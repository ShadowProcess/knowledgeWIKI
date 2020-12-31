package com.example.bimap;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Test;

public class BiMapTest {

    @Test
    public void test(){
        BiMap<String,String> biMap = HashBiMap.create();
        biMap.put("1","11111");
        biMap.put("2","22222");
        biMap.put("3","33333");
        System.out.println(biMap);
        //{1=11111, 2=22222, 3=33333}

        //反转Map
        BiMap<String, String> inverse = biMap.inverse();
        System.out.println(inverse);
        //{11111=1, 22222=2, 33333=3}
    }


}
