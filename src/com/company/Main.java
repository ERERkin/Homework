package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Ветер с моря дул");
        arrayList.add("Нагонял беду");
        arrayList.add("И сказал ты мне");
        arrayList.add("Больше не приду");
        for(int i = 0; i < arrayList.size(); i++){
            arrayList.set(i,arrayList.get(i) + " " + arrayList.get(i));
            System.out.println(arrayList.get(i));
        }
        ArrayList<String> arrayList1 = new ArrayList<>();
        for(int i = 0; i < arrayList.size(); i++){
            if(arrayList.get(i).length() > 20)
                arrayList1.add(arrayList.get(i));

        }
        for(int i = 0; i < arrayList1.size(); i++){
            if(arrayList1.get(i).lastIndexOf("И сказал ты мне") != -1){
                arrayList1.remove(i);
                i--;
            }
        }
        System.out.println(arrayList1);

    }
}
