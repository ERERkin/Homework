package com.company;

public class Main {

    public static void main(String[] args) {
        Monkey smallMonkey = new Monkey("Billy");
        Climable climable = new Monkey("Jhon",smallMonkey);
        climable.climb();
    }
}