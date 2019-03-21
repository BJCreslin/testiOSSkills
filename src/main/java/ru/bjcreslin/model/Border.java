package ru.bjcreslin.model;

/**
 * Класс границы игрового поля
 */
public class Border extends StaticAble {
    /*
  Изображение
   */
    private String symbolForview = "#";

    @Override
    public String getObjectSymbol() {
        return symbolForview;
    }

    private static Border instance;

    public Border() {
        this.deathAble = false;
        this.playerCanMove = false;
        this.reachAble = false;
        this.robotCanMove = false;
    }

    public static Border getInstance() {
        if (instance == null) {
            instance = new Border();
        }
        return instance;
    }


}
