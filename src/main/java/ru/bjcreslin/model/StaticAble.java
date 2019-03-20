package ru.bjcreslin.model;


import lombok.Data;
import lombok.Getter;

@Data
public abstract class StaticAble {

    boolean robotCanMove;
    boolean playerCanMove;
    boolean deathAble;
    boolean reachAble;
}
