package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class TaskB {
    public static void main(String[] args) {
        LinkedList<AbstractAnimal> animals = new LinkedList<>();
        //Создать по 50 кошек и собак, с возрастом идущим на увеличение
        //Сохранить всех кошек и собак в LinkedList
        for(int i = 0; i < 50; i++){
            animals.add(new Kitty(i / 10));
        }
        for(int i = 50; i < 100; i++){
            animals.add(new Dog(i / 10));
        }
        //Удалить из списка каждую 5 кошку
        //Вставить после каждой 3 собаки – одну мышку.
        int countKitty = 0;
        int countDog = 0;
        for(int i = 0; i < animals.size(); i++){
            if(animals.get(i) instanceof Kitty) {
                if (countKitty + 1 == 5) {
                    animals.remove(i);
                    i--;
                    countKitty = 0;
                } else {
                    countKitty++;
                }
            }else if(animals.get(i) instanceof Dog){
                if (countDog + 1 == 3) {
                    animals.add(i,new Mouse(1));
                    countDog = 0;
                } else {
                    countDog++;
                }
            }
        }
        //Найти оказавшихся рядом кошек и мышек, переместить их в новый LinkedList.
        LinkedList<AbstractAnimal> catAndMouse = new LinkedList<>();
        if((animals.get(0) instanceof Kitty && animals.get(1) instanceof Mouse) ||
                (animals.get(1) instanceof Kitty && animals.get(0) instanceof Mouse)){
            catAndMouse.add(animals.get(0));
        }
        for(int i = 0; i < animals.size() - 1; i++){
            if((animals.get(i) instanceof Kitty && animals.get(i + 1) instanceof Mouse) ||
                    (animals.get(i + 1) instanceof Kitty && animals.get(i) instanceof Mouse)){
                catAndMouse.add(animals.get(i + 1));
            }
        }
        //Скопировать весь список из пункта 4 в ArrayList
        ArrayList<AbstractAnimal> animals1 = new ArrayList<>();
        animals1.addAll(animals);
        //Получить сумму возрастов отдельно по мышкам, кошкам и собакам.
        //Нужно распределить элементы в новый LinkedList так, чтобы в посередине были собаки, вначале кошки, а в хвосте мыши (чтобы не было конфликта мышей и кошек)
        LinkedList<AbstractAnimal> newAnimalList = new LinkedList<>();
        int sum = 0;
        for(int i = 0; i < animals.size(); i++){
            if(animals.get(i) instanceof Kitty){
                sum += animals.get(i).getAge();
                newAnimalList.add(animals.get(i));
            }
        }
        sum = 0;
        for(int i = 0; i < animals.size(); i++){
            if(animals.get(i) instanceof Dog){
                sum += animals.get(i).getAge();
                newAnimalList.add(animals.get(i));
            }
        }
        sum = 0;
        for(int i = 0; i < animals.size(); i++){
            if(animals.get(i) instanceof Mouse){
                sum += animals.get(i).getAge();
                newAnimalList.add((animals.get(i)));
            }
        }
        animals.clear();
        animals.addAll(newAnimalList);
        newAnimalList.clear();
    }
}

class AbstractAnimal{
    private int age;

    public AbstractAnimal(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

class Dog extends AbstractAnimal{
    public Dog(int age) {
        super(age);
    }
}

class Kitty extends AbstractAnimal{
    public Kitty(int age) {
        super(age);
    }
}

class Mouse extends AbstractAnimal{
    public Mouse(int age) {
        super(age);
    }
}