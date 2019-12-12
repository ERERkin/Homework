package com.company;

public class TheoryFive {
    public static void main(String[] args) {
        Month thisMonth = Month.December;
        switch (thisMonth){
            case January:
                System.out.println(Month.January.getCode());
                break;
            case February:
                System.out.println(Month.February.getCode());
                break;
            case March:
                System.out.println(Month.March.getCode());
                break;
            case April:
                System.out.println(Month.April.getCode());
                break;
            case May:
                System.out.println(Month.May.getCode());
                break;
            case June:
                System.out.println(Month.June.getCode());
                break;
            case Jule:
                System.out.println(Month.Jule.getCode());
                break;
            case August:
                System.out.println(Month.August.getCode());
                break;
            case September:
                System.out.println(Month.September.getCode());
                break;
            case October:
                System.out.println(Month.October.getCode());
                break;
            case November:
                System.out.println(Month.November.getCode());
                break;
            case December:
                System.out.println(Month.December.getCode());
                break;
        }
    }
}

enum Month{
    January("Январь"), February("Февраль"), March("Март"), April("Апрель"), May("Май"), June("Июнь"),
    Jule("Июль"), August("Август"), September("Сентябрь"), October("Октябрь"), November("Ноябрь"), December("Декабрь");
    private String code;
    Month(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}