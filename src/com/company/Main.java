package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {
    //Песевдокод для сортировки слиянием был взят отсюда
    //https://neerc.ifmo.ru/wiki/index.php?title=%D0%A1%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D0%B0_%D1%81%D0%BB%D0%B8%D1%8F%D0%BD%D0%B8%D0%B5%D0%BC
    static void merge(List<Integer> a, int left, int mid, int right){
        //И чтобы доказать что я не просто тупо списал, а еще понял как это работает
        //Я напишу как понял алгоритм
        int i = 0;
        int j = 0;
        // Мы создаем два счетчика
        int[] result = new int[right - left + 1];
        //Создаем массив размера с индекса left до right
        while (left + i < mid && mid + j < right){
            if (a.get(left + i) < a.get(mid + j)){
                result[i + j] = a.get(left + i);
                i++;
            }else{
                result[i + j] = a.get(mid + j);
                j++;
            }
            // Весь цикл нужен для того,что бы набрать минимальную половину
            // из двух половин
        }
        while (left + i < mid){
            result[i + j] = a.get(left + i);
            i++;
        }
        while (mid + j < right){
            result[i + j] = a.get(mid + j);
            j++;
        }
        //Эти два цикла заполняют оставшуюся часть массива, причем
        //каждый элемент осташейся части больше предыдущей
        for(int k = 0; k < i + j; k++){
            a.set(left + k, result[k]);
        }
        //В конце можно добавить что это слияние уже двух отсортированных половин
        //И они уже отсортированы, так как это слияние проводилось, начиная от каждого элемента
    }

    static void mergeSort(List<Integer> a, int left, int right){
        // Это рекурсивная функция, что постоянно делит массив на два
        // до тех пор пока элементы не будут разделены до единичных,
        // это нужно для слиния
        if(left + 1 >= right){
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(a,left,mid);
        mergeSort(a,mid,right);
        //сначала делится потом сливается,
        // это значит, что слияние начнется с самых маленьких элементов
        merge(a,left,mid,right);
        //запускаем уже знакомую нам выше фунцию слияния
    }

    static void quickSort(int[] a, int left, int right, int m, int g){
        // вот тут уже сделал сам, так что возможны косяки но на этом примере вроде работет
        if(left + 1 >= right){
            return;
        }
        int[] result = new int[right - left];
        boolean[] check = new boolean[right - left];
        int k = 0;
        for(int i = left; i < right; i++){
            if(a[i] < m){
                check[i - left] = true;
                result[k] = a[i];
                k++;
            }
        }
        result[k] = m;
        check[g - left] = true;
        k++;
        int count = k;
        for(int j = left; j < right; j++){
            if(!check[j - left]){
                result[k] = a[j];
                k++;
            }
        }
        for(int l = 0; l < result.length; l++){
            a[left + l] =  result[l];
        }
        boolean flag = true;
        for(int l = 0; l < result.length - 1; l++){
            if(a[left + l] > a[left + l + 1]){
                flag = false;
            }
        }
        if(flag){
            return;
        }
        int newM = left + ((count - 1) / 2);
        quickSort(a,left,count - 1,a[newM],newM);
        newM = count + (right - left - count) / 2;
        quickSort(a,count,right,a[newM],newM);
    }

    public static void main(String[] args) {
        Random rnd = new Random();
        int[] array = new int[100];
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();
        for(int i = 0; i < array.length; i++){
            array[i] = rnd.nextInt(2);
            arrayList.add(array[i]);
            linkedList.add(array[i]);
        }
        System.out.println(linkedList);
        mergeSort(arrayList,0,arrayList.size());
        System.out.println(arrayList);
        mergeSort(linkedList,0,linkedList.size());
        System.out.println(linkedList);
        quickSort(array,0,array.length,array[array.length/2],array.length/2);
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
    }
}
