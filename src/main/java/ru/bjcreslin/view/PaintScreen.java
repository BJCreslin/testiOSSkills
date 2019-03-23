package ru.bjcreslin.view;


public abstract class PaintScreen {

    /**
     * метод вывода на экран игрового пространства
     * @param gameObjects -матрица игровых объектов
     */
    abstract public void viewMatrix(String[][] gameObjects);

    /**
     * Вывод на экран количества оставшегося золота и зарядов шокера
     * @param gold -золото на поле
     * @param charge  - шокер
     */
    abstract public void viewScore(int gold, int charge);

    /**
     * ПОздравление с победой
     */
    public abstract void viewWin();

    /**
     * Горечь поражения на экране
     */
    public abstract void viewLose();
}
