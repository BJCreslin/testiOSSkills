package ru.bjcreslin.model;


import lombok.Data;
import ru.bjcreslin.service.*;
import ru.bjcreslin.view.PaintScreen;

import java.util.*;

@Data
public class Game {
    public static void setPlayerAlive(boolean playerAlive) {
        Game.playerAlive = playerAlive;
    }

    private static boolean playerAlive;  // переменная игры. true- означает, что игра идёт . false- игре конец
    private int nSize; // Размер игрового поля ( N*N клеток)
    private int nPieceOfGold;// количество кусков золота
    private int nHole;//Количество дырок в полу
    private int nRobots;//количество роботов


    PaintScreen paintScreen; // вывод на экран
    PlayingField playingField; // Поле статичных объектов
    CheckMovableCollision checkMovableCollision;// Контроллер коллизий движения объектов
    CheckStartCollision checkStartCollision; //Контроллер коллизий стартовых позиций


    private Player player; // Игрок
    private List<Robot> robotList; //Лист роботов

    private Deque<Movable> movableQueue;//очередь ходов


    public Game(PaintScreen paintScreen, int nSize, int nPieceOfGold, int nHole, int nRobots) {
        this.nSize = nSize;
        this.nPieceOfGold = nPieceOfGold;
        this.nHole = nHole;
        this.nRobots = nRobots;

        this.paintScreen = paintScreen;

        this.playingField = new PlayingField(nSize);
        playingField.fillAllCellsGround();

        this.checkStartCollision = new CheckStartCollision(this);
        this.checkMovableCollision = new CheckMovableCollision(this);

        this.player = new Player(nSize);

        fillHole();

        fillGold();


        this.robotList = new ArrayList<>();
        fillRobotList(nRobots);

        //Делаем игрока живым
        playerAlive = true;
    }

    /*
    заполнение комнаты золотом
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

    /*
    Заполняем комнату дырками
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

    /*
    Заполенение комнаты роботами
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
            while (true) {
                robot = (Robot) robotFactory.getNewGameObject();
                /*
                робот на расстоянии от игрока
                под роботом земля
                робот может сделать ход
                 */
                if ((checkStartCollision.isPlayerFar(robot)) &
                        (playingField.getCell(robot.getX(), robot.getY()).equals(Ground.getInstance())) &
                        (checkStartCollision.isNotEnvironmentByHole(robot))) {
                    break;
                }
            }
            robotList.add(robot);
        }

    }

    public void play() {
        movableQueue = makeQuee();
        paintScreen.viewMatrix(ScreeenFieldMaker.gameSymbolsScreeenFieldMaker(playingField.getPlayingFieldCells(),
                movableQueue));

    }

    private Deque<Movable> makeQuee() {
        Deque<Movable> movableQueue = new ArrayDeque<>();
        movableQueue.addAll(robotList);
        movableQueue.add(player);
        return movableQueue;
    }

    public boolean isPlayerHere(Movable movable) {
        return ((movable.getX() == player.getX()) & (movable.getY() == player.getY())) ? true : false;
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

        public Staticable invoke() {
    /*
    перебираем переменные, пока по ним не будет ячейка -Земля и  в этой ячейке не будет игрока
     */
            while (true) {
                x = new Random().nextInt(nSize);
                y = new Random().nextInt(nSize);

                if ((playingField.getCell(x, y).equals(Ground.getInstance())) &
                        (!player.isHere(x, y))) {
                    break;
                }
            }
            return this;
        }
    }
}
