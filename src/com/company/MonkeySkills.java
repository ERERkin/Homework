package com.company;

public interface MonkeySkills extends Climable, Carryable, FruitEatable{
    @Override
    default void climb() {
        System.out.println("Обезьяна карабкается");
    }

    @Override
    default public void carry() {
        System.out.println("Обезьяна несет");
    }
}
