package com.company;

import java.util.ArrayList;
import java.util.Random;

public class TaskB {
    public static void main(String[] args) {
        Random random = new Random();
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int i = 0; i < 50; i++){
            arrayList.add(random.nextInt(100) + 1);
        }
        ArrayList<Integer> evenNumbered = new ArrayList<>();
        ArrayList<Integer> oddNumbered = new ArrayList<>();
        for(int i = 0; i < arrayList.size(); i++){
            if(arrayList.get(i) % 2 == 0){
                evenNumbered.add(arrayList.get(i));
            }else{
                oddNumbered.add(arrayList.get(i));
            }
        }
        System.out.println(arrayList);
        System.out.println(evenNumbered);
        System.out.println(oddNumbered);
    }
}
