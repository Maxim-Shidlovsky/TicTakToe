package by.shidlovsky.maxim.tictaktoe.game;

public class Square {
    private Gamer gamer = null;

    public void fill(Gamer gamer) {
        this.gamer = gamer;
    }

    boolean isFilled() {
        return gamer != null;
    }

    public Gamer getGamer() {
        return gamer;
    }
}
