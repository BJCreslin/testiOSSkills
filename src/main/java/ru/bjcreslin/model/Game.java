package ru.bjcreslin.model;


import lombok.Data;
import ru.bjcreslin.controller.*;
import ru.bjcreslin.view.PaintScreen;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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


    private int score;//счёт игры
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


        this.robotList = new ArrayList<>();
        fillRobotList(nSize);


        //Делаем игрока живым
        playerAlive = true;
    }

    private void fillHole() {

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
                if (checkStartCollision.isPlayerFar(robot)) {
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


}
