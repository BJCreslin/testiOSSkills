package ru.bjcreslin.model;

/**
 * Реализация класса -кучка золота
 */
public class PieceOfGold extends StaticAble implements GameObject {
    /*
      Изображение кучи золота ;
       */
    private String symbolForview;

    {
        symbolForview = "༆";
    }

    @Override
    public String getObjectSymbol() {
        return symbolForview;
    }

    /*   ПО золоту может ходить игрок
   но она для него не смертельна
   робот ходить по золоту не может
   Золото обогащает игрока
   */
    private static PieceOfGold instance;

    private PieceOfGold() {
        this.deathAble = false;
        this.playerCanMove = true;
        this.reachAble = true;
        this.robotCanMove = false;
    }

    static PieceOfGold getInstance() {
        if (instance == null) {
            instance = new PieceOfGold();
        }
        return instance;
    }
}
