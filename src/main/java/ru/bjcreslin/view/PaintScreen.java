package ru.bjcreslin.view;


public abstract class PaintScreen {

    /**
     * метод вывода на экран игрового пространства
     * @param gameObjects -матрица игровых объектов
     */
    abstract public void viewMatrix(String[][] gameObjects);

    /**
     * Вывод на экран количества оставшегося золота
     * @param gold -количество золота на поле
     */
    abstract public void viewScore(int gold);

    /**
     * ПОздравление с победой
     */
    public abstract void viewWin();

    /**
     * Горечь поражения на экране
     */
    public abstract void viewLose();
}
