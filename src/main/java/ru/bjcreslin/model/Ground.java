package ru.bjcreslin.model;

/**
 * Обычная клетка. Все могут наступать.
 * Синглтон
 */
class Ground extends StaticAble implements GameObject {
    /*   ПО земле могут ходить игрок и роботы
    земля не смертельна
    земля не обогащает игрока
    */
    private static Ground instance;

    private Ground() {
        this.deathAble = false;
        this.playerCanMove = true;
        this.reachAble = false;
        this.robotCanMove = true;
    }

    public static Ground getInstance() {
        if (instance == null) {
            instance = new Ground();
        }
        return instance;
    }

}
