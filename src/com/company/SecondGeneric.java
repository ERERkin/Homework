package com.company;

import java.util.List;
import java.util.Map;

public class SecondGeneric<T> {
    public static Number getSum(List<? extends Number> list){
        double sum = 0;
        for(Number number : list){
            sum += number.doubleValue();
        }
        return sum;
    }
    public static  <T extends Number>T getAvg(T[] arr){
        double sum = 0;
        for(T t : arr){
            sum += t.doubleValue();
        }
        sum = sum / arr.length;
        return (T)(Number)sum;
    }
}
