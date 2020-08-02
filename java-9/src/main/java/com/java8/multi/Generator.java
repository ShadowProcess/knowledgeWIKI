package com.java8.multi;

import java.util.HashSet;
import java.util.Set;

public class Generator {

    public Set<String> createStrings()
    {
        HashSet localHashSet = new HashSet();
        localHashSet.add("Java");
        localHashSet.add("8");
        return localHashSet;
    }
}
