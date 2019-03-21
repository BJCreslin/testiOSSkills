package ru.bjcreslin.view;


import java.util.Arrays;

public class paintsScreenMatrix extends PaintScreen {
    @Override
    public void viewMatrix(String[][] gameObjects) {
        for (String[] gameObject : gameObjects) {
            Arrays.stream(gameObject, 0, gameObjects[0].length).forEach(System.out::print);
            System.out.println();
        }


    }


}
