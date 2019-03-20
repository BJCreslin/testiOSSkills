package ru.bjcreslin.model;

/**
 * Реализация класса -кучка золота
 */
public class PieceOfGold extends StaticAble implements GameObject {
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

    public static PieceOfGold getInstance() {
        if (instance == null) {
            instance = new PieceOfGold();
        }
        return instance;
    }
}
