package com.company;

public class Monkey  implements MonkeySkills{
    private String name;
    private Monkey monkey;
    public Monkey(String name) {
        this.name = name;
    }
    public Monkey(String name, Monkey monkey) {
        this.name = name;
        this.monkey = monkey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Monkey getMonkey() {
        return monkey;
    }

    public void setMonkey(Monkey monkey) {
        this.monkey = monkey;
    }

    @Override
    public String toString() {
        String temp = "";
        if(monkey != null){
            temp = ", несет " + monkey;
        }
        return "Имя " + name + "\n" + temp;
    }
}
