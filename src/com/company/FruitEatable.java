package com.company;

public interface FruitEatable {
    default void eatFruit(){
        System.out.println("Ест съедобный фрукт");
    }
}
