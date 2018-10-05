package by.shidlovsky.maxim.tictaktoe.game;

public class WinnerCheckerVertical implements WinnerCheckerInterface {
    private Game game;

    WinnerCheckerVertical(Game game) {
        this.game = game;
    }

    public Gamer checkWinner() {
        Square[][] field = game.getField();
        Gamer currPlayer;
        Gamer lastPlayer;
        for (int i = 0, len = field.length; i < len; i++) {
            lastPlayer = null;
            int successCounter = 1;
            for (int j = 0, len2 = field[i].length; j < len2; j++) {
                currPlayer = field[j][i].getGamer();
                if (currPlayer == lastPlayer && currPlayer != null) {
                    successCounter++;
                    if (successCounter == len2) {
                        return currPlayer;
                    }
                }
                lastPlayer = currPlayer;
            }
        }
        return null;
    }
}
