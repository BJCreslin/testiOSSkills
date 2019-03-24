# testiOSSkills
Представляю решение тестового задания.
Решение выполнено на JAva. JDK - Amazon Corretto - 8.
Использован фреймворк Lombok- только ради того, чтобы "скрыть" из кода геттеры и сеттеры.
В качестве игровой стратегии была выбрана - пошаговая стратегия. Ход игрока не ограничен по времени.
Все игровые объекты наследуются либо от класса Movable либо от Staticable. И этим логически делятся на два типа:
стационарные объекты и двигающиеся. Все стационарные объекты хранятся в двумерном массиве внутри объекта класса PlayingField.
Все стационарные объекты -Hole (дыра), PieceofGold (кусок золота), Ground(земля) являются синглтонами и имеют параметры
deathAble (смертелен для игрока), playerCanMove (проходимы игроком),reachAble (обогащают ли игрока -золото), robotCanMove
 (может ли ходить по ним робот). 
 Двигающиеся объекты (Movable) хранят координаты внутри себя, логика ходов размещена внутри них, а реализация возможных
  ходов прописана в MoveDispatcher. Возможные коллизии движения проверяются в сервисе CheckMovableCollision.
  
  В начале игры GameService выводит меню, которое даёт поменять стандартные настройки игры на свои.
При запуске игры, объекте класса Game -генерируется комната - PlayingField. 
В центральные координаты помещается игрок- Player.
Заполняется дырками в полу и золотом, методами  fillHole() и fillGold(). Коллизии проверяются возможностью хождения 
игрока хотя бы на 1 ход, и реализующими логику методами в классе CheckStartCollision.
Далее формируется Список (LIst) роботов, каждый робот создается RobotFactory до тех пор, пока не будет удовлетворять
 условиям задачи, и только после этого добавляется в список роботов. У робота есть параметр numberOfStepsParalyze, 
 в котором описывается оставшееся время парализации. 
Все делают ход поочередно -Deque<Movable> movableQueue, формирует очередь ходов, выбран параметром класса Game.  
Роботы ходят случайно, согласно Move Dispatcher,Если ход не возможен, то делается откат хода. Ход робота возможен, если
в текущей ячейке объекта PlayingField, параметр robotCanMove = true. Робот не может наезжать на золото и дырки. 
В случае если параметр numberOfStepsParalyze больше нуля, то робот ход пропускает, а сам параметр уменьшается на 1. 
Если робот наехал на игрока, то игроку засчитывается поражение. Так же проверятся условие невыхода объекта из комнаты. 
Игрок (Player) ходит, вводя цифры на клавиатуре в методах ConsoleService. Если наезжает на робота или дыру, то проигрывает.
 Если на золото, то "забирает" его себе с поля, то есть в текущей ячейке PlayingField ячейка меняется на Ground, и 
 параметр nPieceOfGold в Game уменьшается на 1. Когда этот параметр становится равным нулю, то игрок признаётся победителем.
Формирование изображения игры формируется в наследниках класса PaintScreen. В данном случае, в символьном представлении
 игровой матрицы- PaintScreenMatrox. Которая в свою очередь образуется из игрового поля и очереди movable объектов. 
К этой матрице, по краю, добавляются Border, который формирует внешнюю границу изображения. Так же в наследнике 
PaintScreen выводится счет игры (остаток золота и зарядов шокера) и сообщения о выигрыше игрока или его проигрыше.


Тестовое задание
 Суть задания 

Разработать игру, в которой игрок перемещается по комнате N*N клеток с разбросанным по ней золотом и собирает его. Для того, чтобы собрать золото, игроку необходимо встать на клетку с ним. В комнате присутствуют роботы, которые пытаются поймать игрока, а также есть дырки в полу - клетки, на которые нельзя вставать ни игроку, ни роботам. Для выигрыша необходимо собрать всё золото в комнате, не будучи пойманным роботами. Если один из роботов встаёт на клетку, где стоит игрок - игра заканчивается проигрышем.

Ход игры

1) Генерируется комната, в ней случайно расставляются роботы, золото, дырки в полу и игрок. Игрок всегда начинает с центра комнаты.

Предусмотреть защиту от возможных коллизий:

- золото окружено дырками и к нему не подобраться;

- главный герой окружен дырками и не может двигаться;

- робот окружен дырками и не может двигаться;

- на стартовой позиции между игроком и ближайшими к нему роботами должно быть минимум 2 клетки.

2) Игровое поле выводится на экран в любом удобном виде (графический интерфейс, консольный вывод двухмерной символьной матрицы и пр.). Вид на игровое поле - сверху.

3) Игрок каким-то образом указывает направление своего хода (сдвиг вверх, вниз, вправо или влево на одну клетку),

4) Свой ход делает каждый из роботов (можно все одновременно). Логика хода - случайный выбор направления.

5) Проверяется условие окончания игры (игрок собрал все золото или пойман роботами). Если игра не окончена - переход на шаг 2. Если окончена - выводится результат игры (выигрыш или проигрыш), количество ходов и собранного золота, после чего предложение сыграть ещё раз. При согласии - переход на шаг 1.

Требования

1) Разработка в соответствии с парадигмами ООП.

2) Продуманная и выделенная в отдельные классы модель данных.

3) Знание и применение паттернов при разработке данной программы будет большим плюсом.

4) В случае, если какой-то из пунктов задания (например, требование про проверки на возможные коллизии) не удается реализовать полностью - не страшно, упомяните об этом в письме + уточните, в чём возникли проблемы. Ваше задание проверяется комплексно, а не формально.

Дополнительные задания

1) Реализовать ввод характеристик игры перед началом игры (см. шаг 1): размер комнаты N (в клетках), кол-во роботов R, кол-во золота G и кол-во дырок в полу H. Дать также возможность перед началом игры указать эти значения, либо играть со значениями по умолчанию.

2) Реализовать улучшенную логику роботов - с тем, чтобы они стремились догнать и поймать игрока, а не перемещались хаотично.

3) Реализовать электроизлучатель - оружие игрока против роботов. Игрок при старте игры имеет 3 заряда излучателя и имеет возможность применить его вместо своего хода. При применении излучателя роботы, находящиеся на соседних с игроком клетках по горизонтали, вертикали и диагонали (итого 8 клеток) парализуются на 5 ходов (не могут передвигаться). Количество оставшихся зарядов излучателя выводится при каждом обновлении игрового поля (шаг 2).

