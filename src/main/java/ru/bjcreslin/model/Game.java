package ru.bjcreslin.model;


import lombok.Data;
import lombok.Getter;
import ru.bjcreslin.service.*;
import ru.bjcreslin.view.PaintScreen;

import java.util.*;

@Data
public class Game {
    private boolean playerAlive;  // переменная игры. true- означает, что игра идёт . false- игре конец

    public Game(PaintScreen paintScreen, GameParametres gameParametres) {
        this.nSize = gameParametres.getNSize();
        this.nPieceOfGold = gameParametres.getNPieceOfGold();
        this.nHole = gameParametres.getNHole();
        this.nRobots = gameParametres.getNRobots();

        this.paintScreen = paintScreen;

        this.playingField = new PlayingField(nSize);

        this.checkStartCollision = new CheckStartCollision(this);
        this.checkMovableCollision = new CheckMovableCollision(this);

        this.player = new Player(this);

        fillHole();
        fillGold();

        this.robotList = new ArrayList<>();
        fillRobotList(nRobots);

        //Делаем игрока живым
        playerAlive = true;
    }

    private int nSize; // Размер игрового поля ( N*N клеток)
    private int nPieceOfGold;// количество кусков золота
    private int nHole;//Количество дырок в полу
    private int nRobots;//количество роботов


    private PaintScreen paintScreen; // вывод на экран
    @Getter
    private PlayingField playingField; // Поле статичных объектов
    @Getter
    private CheckMovableCollision checkMovableCollision;// Контроллер коллизий движения объектов
    private CheckStartCollision checkStartCollision; //Контроллер коллизий стартовых позиций


    private Player player; // Игрок
    private List<Robot> robotList; //Лист роботов

    private Deque<Movable> movableQueue;//очередь ходов

    void setPlayerAlive(boolean playerAlive) {
        this.playerAlive = playerAlive;
    }

    /**
     * заполнение комнаты золотом
     */
    private void fillGold() {
        CheckStartCollision checkStartCollision = new CheckStartCollision(this);

        StaticAble[][] tempField = playingField.getPlayingFieldCells().clone();
        while (true) {
            for (int i = 0; i < nPieceOfGold; i++) {
                Staticable staticable = new Staticable().invoke();
                int x = staticable.getX();
                int y = staticable.getY();
                playingField.setCell(x, y, PieceOfGold.getInstance());
            }
            /*
            Проверяем, может ли игрок двигаться, если может, то сохранем данные- выходим из цикла
             */
            if (checkStartCollision.isPlayerCanMoveStatic()) {
                break;
            }
            playingField.setPlayingFieldCells(tempField.clone());
        }
    }

    /**
     * Заполняем комнату дырками
     */
    private void fillHole() {
        CheckStartCollision checkStartCollision = new CheckStartCollision(this);
        StaticAble[][] tempField = playingField.getPlayingFieldCells().clone();
        while (true) {
            for (int i = 0; i < nHole; i++) {
                Staticable staticable = new Staticable().invoke();
                int x = staticable.getX();
                int y = staticable.getY();
                playingField.setCell(x, y, Hole.getInstance());
            }
            /*
            Проверяем, может ли игрок двигаться, если может, то сохранем данные- выходим из цикла
             */
            if (checkStartCollision.isPlayerCanMoveStatic()) {
                break;
            }
            playingField.setPlayingFieldCells(tempField.clone());
        }
    }

    /**
     * Заполнение комнаты роботами
     *
     * @param nSize- количество роботов
     */
    private void fillRobotList(int nSize) {
        GameObjectFactory robotFactory = new RobotFactory(this);
        for (int i = 0; i < nSize; i++) {
            Robot robot;
            /*
            Создаём роботов, пока не выполнятся условия:
            1 .на стартовой позиции между игроком и ближайшими к нему роботами должно быть минимум 2 клетки.
            2. робот окружен дырками и не может двигаться;
             */
            do {
                robot = (Robot) robotFactory.getNewGameObject();
                /*
                робот на расстоянии от игрока
                под роботом земля
                робот может сделать ход
                 */
            }
            while ((!checkStartCollision.isPlayerFar(robot)) &
                    (!playingField.getCell(robot.getX(), robot.getY()).equals(Ground.getInstance())) &
                    (!checkStartCollision.isNotEnvironmentByHole(robot)));
            robotList.add(robot);
        }
    }

    /**
     * Основной метод игры
     * в цикле
     * 1.рисуется картинка
     * 2.ходит игрок
     * 3.ходят работы
     */
    public void play() {
        /* Цикл пока жив игрок и количсетво золота больше нуля*/
        while (playerAlive & (nPieceOfGold > 0)) {
            movableQueue = makeQuee();
            paintScreen.viewScore(nPieceOfGold, player.getNumberShockerCharges());
            paintScreen.viewMatrix(ScreeenFieldMaker.gameSymbolsScreeenFieldMaker(playingField.getPlayingFieldCells(),
                    movableQueue));
            movableQueue.forEach(Movable::action);
        }

        if (playerAlive) {
            paintScreen.viewWin();
        } else {
            paintScreen.viewLose();
        }
    }

    /**
     * Создание очереди двигающихся объектов
     *
     * @return очередь
     */
    private Deque<Movable> makeQuee() {
        Deque<Movable> movableQueue = new ArrayDeque<>(robotList);
        movableQueue.add(player);
        return movableQueue;
    }


    private class Staticable {
        private int x;
        private int y;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        Staticable invoke() {
    /*
    перебираем переменные, пока по ним не будет ячейка -Земля и  в этой ячейке не будет игрока
     */
            do {
                x = new Random().nextInt(nSize);
                y = new Random().nextInt(nSize);
            }
            while ((playingField.getCell(x, y).equals(Ground.getInstance())) &
                    (player.isHere(x, y)));
            return this;
        }
    }
}
