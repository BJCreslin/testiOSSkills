package ru.bjcreslin.model;

import lombok.Data;

/**
 * Класс игрового поля.
 */
//Генерируется комната, в ней случайно расставляются роботы, золото, дырки в полу и игрок.

@Data

public class PlayingField {

    private GameObject[][] playingFieldCells; // Само игровое поле

    public PlayingField(int nSize) {
        playingFieldCells = new GameObject[nSize][nSize];

    }
}
