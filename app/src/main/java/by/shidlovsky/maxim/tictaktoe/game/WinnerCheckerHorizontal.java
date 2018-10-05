package by.shidlovsky.maxim.tictaktoe.game;

public class WinnerCheckerHorizontal implements WinnerCheckerInterface {
    private Game game;

    WinnerCheckerHorizontal(Game game) {
        this.game = game;
    }

    public Gamer checkWinner() {
        Square[][] field = game.getField();
        Gamer currPlayer;
        Gamer lastPlayer;
        for (Square[] aField : field) {
            lastPlayer = null;
            int successCounter = 1;
            for (int j = 0, len2 = aField.length; j < len2; j++) {
                currPlayer = aField[j].getGamer();
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
