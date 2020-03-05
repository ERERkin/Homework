package com.company;

import java.util.Random;

public class ThreadD implements Runnable{
    static volatile int firstTeamGoals = 0;
    static volatile int firstTeamCards = 0;
    static volatile int firstTeamOffsides = 0;
    static volatile int secondTeamGoals = 0;
    static volatile int secondTeamCards = 0;
    static volatile int secondTeamOffsides = 0;
    static volatile int min = 0;
    int team;
    @Override
    public void run() {
        Random rd = new Random();
            int goals = rd.nextInt(16);
            int cards = rd.nextInt(16);
            int offsides = rd.nextInt(16);
            if (team == 1) {
                while (goals > firstTeamGoals ||
                        cards > firstTeamCards ||
                        offsides > firstTeamOffsides ||
                        min <= 90) {
                    int check = rd.nextInt(3);
                    if (check == 0 && goals > firstTeamGoals) {
                        firstTeamGoals++;
                        System.out.println("First team GOAl");
                    } else if (check == 1 && cards > firstTeamCards) {
                        firstTeamCards++;
                        System.out.println("First team Yellow card");
                    } else if (check == 2 && offsides > firstTeamOffsides) {
                        firstTeamOffsides++;
                        System.out.println("First team offside");
                    }
                    min++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else if (team == 2) {
                while (goals > secondTeamGoals ||
                        cards > secondTeamCards ||
                        offsides > secondTeamOffsides ||
                        min <= 90) {
                    int check = rd.nextInt(3);
                    if (check == 0 && goals > secondTeamGoals) {
                        secondTeamGoals++;
                        System.out.println("Second team GOAl");
                    } else if (check == 1 && cards > secondTeamCards) {
                        secondTeamCards++;
                        System.out.println("Second team Yellow card");
                    } else if (check == 2 && offsides > secondTeamOffsides) {
                        secondTeamOffsides++;
                        System.out.println("Second team offside");
                    }
                    min++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("Goals " + ThreadD.firstTeamGoals + ":" + ThreadD.secondTeamGoals);
                System.out.println("Cards " + ThreadD.firstTeamCards + ":" + ThreadD.secondTeamCards);
                System.out.println("Offsides " + ThreadD.firstTeamOffsides + ":" + ThreadD.secondTeamOffsides);
            }
    }

    public ThreadD(int team) {
        this.team = team;
    }
}
