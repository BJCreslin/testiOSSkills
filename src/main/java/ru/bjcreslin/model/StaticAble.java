package ru.bjcreslin.model;


import lombok.Data;
import lombok.Getter;

@Data
public abstract class StaticAble {
    public abstract String getObjectSymbol();

    boolean robotCanMove;
    boolean playerCanMove;
    boolean deathAble;
    boolean reachAble;
}
