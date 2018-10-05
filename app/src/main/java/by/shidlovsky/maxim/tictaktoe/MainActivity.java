package by.shidlovsky.maxim.tictaktoe;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.shidlovsky.maxim.tictaktoe.game.Game;
import by.shidlovsky.maxim.tictaktoe.game.Gamer;
import by.shidlovsky.maxim.tictaktoe.game.Square;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tableLayout)
    TableLayout mainLayout;

    private Button[][] buttons = new Button[3][3];

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        startNewGame();
        createGameField();
    }

    private void createGameField() {
        Square[][] field = game.getField();
        Context baseContext = getBaseContext();
        for (int i = 0, lenI = field.length; i < lenI; i++ ) {
            TableRow row = new TableRow(this);
            for (int j = 0, lenJ = field[i].length; j < lenJ; j++) {
                AppCompatButton button = new AppCompatButton(this);
                buttons[i][j] = button;
                button.setOnClickListener(new Listener(i, j));
                row.addView(button, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                int pixels = getScreenWidth(baseContext) / 3;
                button.setWidth(pixels);
                button.setHeight(pixels);
            }
            mainLayout.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    private void startNewGame() {
        game = new Game();
        game.start();
    }

    public class Listener implements View.OnClickListener {
        private int x;
        private int y;

        Listener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void onClick(View view) {
            Button button = (Button) view;
            Game game = MainActivity.this.game;
            Gamer gamer = game.getCurrentGamer();

            if (makeTurn(x, y)) {
                button.setText(gamer.getName());
            }

            Gamer winner = game.checkWinner();
            if (winner != null) {
                gameOver(winner);
            }

            if (game.isFieldFilled()) {
                gameOver();
            }
        }
    }

    private boolean makeTurn(int x, int y) {
        return game.makeTurn(x, y);
    }

    private void gameOver(Gamer player) {
        String txtWin = "Gamer " + player.getName() + " won!";
        Toast.makeText(this, txtWin, Toast.LENGTH_SHORT).show();
        game.reset();
        refresh();
    }

    private void gameOver() {
        Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
        game.reset();
        refresh();
    }

    private void refresh() {
        Square[][] field = game.getField();
        for (int i = 0, len = field.length; i < len; i++) {
            for (int j = 0, len2 = field[i].length; j < len2; j++) {
                if (field[i][j].getGamer() == null) {
                    buttons[i][j].setText("");
                } else {
                    buttons[i][j].setText(field[i][j].getGamer().getName());
                }
            }
        }
    }

    public static int getScreenWidth(Context context) {
        Point size = getPoint(context);
        return size.x;
    }

    @NonNull
    private static Point getPoint(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager == null) {
            return new Point(0, 0);
        }

        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }
}
