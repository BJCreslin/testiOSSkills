package ru.bjcreslin.view;


public abstract class PaintScreen {

    abstract public void viewMatrix(String[][] gameObjects);

    abstract public void viewScore(int gold);

    public abstract void viewWin();

    public abstract void viewLose();
}
