package com.company;

import java.util.HashMap;
import java.util.HashSet;

public class FirstGeneric<T> {
    private HashMap<Integer, T> map = new HashMap<>();

    public void addValue(Integer k, T v){
        map.put(k,v);
    }
    public String getStringByKey(Integer k){
        String ans = "" + k + '\n' + map.getOrDefault(k, null);
        return ans;
    }
}
