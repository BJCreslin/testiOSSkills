package ru.bjcreslin.model;

/**
 * Реализация класса Hole
 */
public class Hole extends StaticAble implements GameObject {
    /*
    Изображение дыры-0;
     */
    private String symbolForview = "0";

    @Override
    public String getObjectSymbol() {
        return symbolForview;
    }

    /*   ПО дыре может ходить игрок
    но она для него смертельна
    робот ходить по дыре не может
    дыра не обогащает игрока
    */
    private static Hole instance;


    public Hole() {
        this.deathAble = true;
        this.playerCanMove = true;
        this.reachAble = false;
        this.robotCanMove = false;
    }

    public static Hole getInstance() {
        if (instance == null) {
            instance = new Hole();
        }
        return instance;
    }

}
