package ru.bjcreslin.model;


import lombok.Data;

@Data
public abstract class StaticAble {
    public abstract String getObjectSymbol();

    boolean robotCanMove;
    boolean playerCanMove;
    boolean deathAble;
    boolean reachAble;
}
