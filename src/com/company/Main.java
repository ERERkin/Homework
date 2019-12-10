package com.company;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Cat> cats1 = new ArrayList<>();
        LinkedList<Cat> cats2 = new LinkedList<>();
        for(int i = 0; i < 1000000; i++){
            cats1.add(new Cat());
            cats2.add(new Cat());
        }
        long timeListStart = System.currentTimeMillis();
        for(int i = 500000; i < 500100; i++){
            cats1.add(i,new Cat());
        }
        float timeListEnd = System.currentTimeMillis() - timeListStart;
        System.out.println("Середина add array list - " + timeListEnd);
        timeListStart = System.currentTimeMillis();
        for(int i = 500000; i < 500100; i++){
            cats2.add(i,new Cat());
        }
        timeListEnd = System.currentTimeMillis() - timeListStart;
        System.out.println("Середина add linked list - " + timeListEnd);
        timeListStart = System.currentTimeMillis();
        for(int i = 0; i < 100; i++){
            cats1.add(i,new Cat());
        }
        timeListEnd = System.currentTimeMillis() - timeListStart;
        System.out.println("Начало add array list - " + timeListEnd);
        timeListStart = System.currentTimeMillis();
        for(int i = 0; i < 100; i++){
            cats2.add(i,new Cat());
        }
        timeListEnd = System.currentTimeMillis() - timeListStart;
        System.out.println("Начало add linked list - " + timeListEnd);
        timeListStart = System.currentTimeMillis();
        for(int i = 0; i < 100; i++){
            cats1.add(new Cat());
        }
        timeListEnd = System.currentTimeMillis() - timeListStart;
        System.out.println("Конец add array list - " + timeListEnd);
        timeListStart = System.currentTimeMillis();
        for(int i = 0; i < 100; i++){
            cats2.add(new Cat());
        }
        timeListEnd = System.currentTimeMillis() - timeListStart;
        System.out.println("Конец add linked list - " + timeListEnd);
        timeListStart = System.currentTimeMillis();
        for(int i = 0; i < 100; i++){
            cats1.get(cats1.size()/2);
        }
        //Исскуственное увеличение времени что бы увидеть что быстрее работает
        timeListEnd = System.currentTimeMillis() - timeListStart;
        System.out.println("get середина array list - " + timeListEnd);
        timeListStart = System.currentTimeMillis();
        for(int i = 0; i < 100; i++){
            cats2.get(cats1.size()/2);
        }
        timeListEnd = System.currentTimeMillis() - timeListStart;
        System.out.println("get середина linked list - " + timeListEnd);
        timeListStart = System.currentTimeMillis();
        for(int i = 0; i < 100; i++){
            cats1.remove(500000);
        }
        timeListEnd = System.currentTimeMillis() - timeListStart;
        System.out.println("Середина delete array list - " + timeListEnd);
        timeListStart = System.currentTimeMillis();
        for(int i = 0; i < 100; i++){
            cats2.remove(500000);
        }
        timeListEnd = System.currentTimeMillis() - timeListStart;
        System.out.println("Середина delete linked list - " + timeListEnd);
        timeListStart = System.currentTimeMillis();
        for(int i = 0; i < 100; i++){
            cats1.remove(0);
        }
        timeListEnd = System.currentTimeMillis() - timeListStart;
        System.out.println("Начало delete array list - " + timeListEnd);
        timeListStart = System.currentTimeMillis();
        for(int i = 0; i < 100; i++){
            cats2.remove(0);
        }
        timeListEnd = System.currentTimeMillis() - timeListStart;
        System.out.println("Начало delete linked list - " + timeListEnd);

    }
}

class Cat{

}
