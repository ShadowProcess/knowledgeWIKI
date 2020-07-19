package com.myelfunction;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class MyEl {
    public static String myDecoder(String str) throws UnsupportedEncodingException {
        return URLDecoder.decode(str,"UTF-8");
    }

}
