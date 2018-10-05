package by.shidlovsky.maxim.tictaktoe.game;

public class WinnerCheckerDiagonalRight implements WinnerCheckerInterface {
    private Game game;

    WinnerCheckerDiagonalRight(Game game) {
        this.game = game;
    }

    public Gamer checkWinner() {
        Square[][] field = game.getField();
        Gamer currPlayer;
        Gamer lastPlayer = null;
        int successCounter = 1;
        for (int i = 0, len = field.length; i < len; i++) {
            currPlayer = field[i][len - (i + 1)].getGamer();
            if (currPlayer != null) {
                if (lastPlayer == currPlayer) {
                    successCounter++;
                    if (successCounter == len) {
                        return currPlayer;
                    }
                }
            }
            lastPlayer = currPlayer;
        }
        return null;
    }
}