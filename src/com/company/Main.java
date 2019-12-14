package com.company;

import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class Main {

    static String randomName(){
        Random rnd = new Random();
        String ans = "";
        int name_size = rnd.nextInt(4) + 3;
        int nameLetter = rnd.nextInt(26) + (int)'A';
        ans = ans + (char)nameLetter;
        for(int i = 0; i < name_size - 1; i++){
            nameLetter = rnd.nextInt(26) + (int)'a';
            ans = ans + (char)nameLetter;
        }
        return ans;
    }

    public static void main(String[] args) {
        Random rnd = new Random();
        Set<Dog> dogs = new HashSet<>();
        for(int i = 0; i < 40; i++){
            int prevSize = dogs.size();
            dogs.add(new Dog(randomName(),rnd.nextInt(10) + 1));
            if(dogs.size() == prevSize){
                i--;
            }
        }
        System.out.println(dogs);
    }
}

class Dog{
    private String name;
    private int age;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return age == dog.age &&
                Objects.equals(name, dog.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}' + '\n';
    }
}
