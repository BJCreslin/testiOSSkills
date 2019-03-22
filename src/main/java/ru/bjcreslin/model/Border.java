package ru.bjcreslin.model;

/**
 * Класс границы игрового поля
 */
public class Border extends StaticAble {
    /*
  Изображение
   */
    private final String symbolForview;

    {
        symbolForview = "#";
    }

    @Override
    public String getObjectSymbol() {
        return symbolForview;
    }

    private static Border instance;

    private Border() {
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
