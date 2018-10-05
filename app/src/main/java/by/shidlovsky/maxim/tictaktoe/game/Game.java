package by.shidlovsky.maxim.tictaktoe.game;

public class Game {
    private Gamer[] gamers;
    private Square[][] field;
    private Gamer currentGamer;
    private int filledItems;
    private int itemsCount;
    private WinnerCheckerInterface[] winnerCheckers;

    public Game() {
        winnerCheckers = new WinnerCheckerInterface[4];
        winnerCheckers[0] = new WinnerCheckerHorizontal(this);
        winnerCheckers[1] = new WinnerCheckerVertical(this);
        winnerCheckers[2] = new WinnerCheckerDiagonalLeft(this);
        winnerCheckers[3] = new WinnerCheckerDiagonalRight(this);

        field = new Square[3][3];
        itemsCount = 0;
        for (int i = 0, l = field.length; i < l; i++) {
            for (int j = 0, l2 = field[i].length; j < l2; j++) {
                field[i][j] = new Square();
                itemsCount++;
            }
        }
        gamers = new Gamer[2];
        currentGamer = null;
        filledItems = 0;
    }

    public void start() {
        resetPlayers();
    }

    private void resetPlayers() {
        gamers[0] = new Gamer("X");
        gamers[1] = new Gamer("O");
        setCurrentGamer(gamers[0]);
    }

    private void setCurrentGamer(Gamer player) {
        currentGamer = player;
    }

    public Square[][] getField() {
        return field;
    }

    public boolean makeTurn(int x, int y) {
        if (field[x][y].isFilled()) {
            return false;
        }

        field[x][y].fill(getCurrentGamer());
        filledItems++;
        switchPlayers();

        return true;
    }

    private void switchPlayers() {
        currentGamer = (currentGamer == gamers[0]) ? gamers[1] : gamers[0];
    }

    public Gamer getCurrentGamer() {
        return currentGamer;
    }

    public boolean isFieldFilled() {
        return itemsCount == filledItems;
    }

    public void reset() {
        resetField();
        resetPlayers();
    }

    private void resetField() {
        for (Square[] squares : field) {
            for (Square square : squares) {
                square.fill(null);
            }
        }
        filledItems = 0;
    }

    public Gamer checkWinner() {
        for (WinnerCheckerInterface winChecker : winnerCheckers) {
            Gamer winner = winChecker.checkWinner();
            if (winner != null) {
                return winner;
            }
        }
        return null;
    }
}
