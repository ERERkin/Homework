package com.company;

public class ThreadD implements Runnable{
    int choice;
    static int a;
    @Override
    public void run() {
        switch (choice){
            case 1: a = divide(a);
            break;
            case 2: a = multiply(a);
                break;
            case 3: a = minus(a);
                break;
            case 4: a = plus(a);
                break;
        }
        System.out.println(a + " " + choice);
    }

    public ThreadD(int choice) {
        this.choice = choice;
    }

    public int divide(int a){
        return a / 2;
    }

    public int multiply(int a){
        return a * 2;
    }

    public int plus(int a){
        return a + 2;
    }

    public int minus(int a){
        return a - 2;
    }
}
