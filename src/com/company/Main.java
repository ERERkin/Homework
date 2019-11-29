package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        List mainList = new List();
        List selected = new List();
        Random random = new Random();
        int duckMin = 1, duckMax = 6, sharkMin = 2, sharkMax = 1300, turtleMin = 1, turtleMax = 200, bearMin = 2, bearMax = 700;
        Selectable[] isSelect = {new Duck(1),new Bear(1)};
        // Теперь мы сможем выделить какое угодно колличесвто животных лишь по массиву образцов этих животных
        for(int i = 0; i < 5; i++){
            mainList.addListItem(new Duck(random.nextInt(duckMax - duckMin + 1) + duckMin));
        }
        for(int i = 0; i < 9; i++){
            mainList.addListItem(new Shark(random.nextInt(sharkMax - sharkMin + 1) + sharkMin));
        }
        for(int i = 0; i < 6; i++){
            mainList.addListItem(new Turtle(random.nextInt(turtleMax - turtleMin + 1) + turtleMin));
        }
        for(int i = 0; i < 6; i++){
            mainList.addListItem(new Bear(random.nextInt(bearMax - bearMin + 1) + bearMin));
        }
        selected = Selectable.select(mainList,isSelect);
        // Вы же не будете снимать баллы за статичные интерфейсы?
        // Прото когда писал перед default static была какая-то ошибка,
        // и тут пришлось бы создавать объект чтобы иметь доступ к интерфейсу
        ListItem animal = selected.getFirst();
        for(;;){
            if(animal != null){
                Selectable b;
                b = animal.getObject();
                b.say();
                animal = animal.getNext();
            }else{
                break;
            }
        }
        selected = Selectable.filterByWeight(mainList,3);
        animal = selected.getFirst();
        for(;;){
            if(animal != null){
                Selectable b;
                b = animal.getObject();
                b.say();
                System.out.println(b.getWeight());
                animal = animal.getNext();
            }else{
                break;
            }
        }
    }
}

interface Selectable {
    static List select(List mainList,Selectable[] animals){
        List ans = new List();
        ListItem animal = mainList.getFirst();
        for(;;){
            if(animal != null){
                for(int i = 0; i < animals.length; i++){
                    if(animals[i].getClass().equals(animal.getObject().getClass())){
                        ans.addListItem(animal.getObject());
                        break;
                    }
                }
                animal = animal.getNext();
            }else{
                break;
            }
        }
        return ans;
    }
    static List filterByWeight(List mainList,int necessaryWeight){
        List ans = new List();
        ListItem animal = mainList.getFirst();
        for(;;){
            if(animal != null){
                if(animal.getObject().getWeight() >= necessaryWeight)
                    ans.addListItem(animal.getObject());
                animal = animal.getNext();
            }else{
                break;
            }
        }
        return ans;
    }
    void say();
    int getWeight();
}

class List {
    private ListItem first;
    private ListItem last;
    void addListItem(Selectable selectable){
        ListItem newListItem = new ListItem(selectable, last);
        if(last == null){
            first = newListItem;
            last = newListItem;
            return;
        }
        last.setNext(newListItem);
        last = newListItem;
    }

    public ListItem getFirst() {
        return first;
    }

    public ListItem getLast() {
        return last;
    }
}

class ListItem {
    Selectable object;
    ListItem previous;
    ListItem next;

    public ListItem(Selectable object, ListItem previous) {
        this.object = object;
        this.previous = previous;
    }

    public Selectable getObject() {
        return object;
    }

    public ListItem getPrevious() {
        return previous;
    }

    public void setPrevious(ListItem previous) {
        this.previous = previous;
    }

    public ListItem getNext() {
        return next;
    }

    public void setNext(ListItem next) {
        this.next = next;
    }
}

abstract class Animal{
    int weight;

    public Animal(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}

class Duck extends Animal implements Selectable{
    public Duck(int weight) {
        super(weight);
    }

    @Override
    public void say() {
        System.out.println("Duck");
    }
}

class Shark extends Animal implements Selectable{
    public Shark(int weight) {
        super(weight);
    }

    public void say(){
        System.out.println("Shark");
    }
}

class Turtle extends Animal implements Selectable{
    public Turtle(int weight) {
        super(weight);
    }

    public void say(){
        System.out.println("Turtle");
    }
}

class Bear extends Animal implements Selectable{
    public Bear(int weight) {
        super(weight);
    }

    @Override
    public void say() {
        System.out.println("Bear");
    }
}