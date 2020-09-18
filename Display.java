import java.awt.*;
import javax.swing.*;

public class Display extends Canvas {
    String title;
    Dimension size;
    JFrame frame;
    Game game;

    public Display(Dimension size, String title, Game game) {
        this.title = title;
        this.game = game;
        this.size=size;
        create();
    }

    public void create() {
        frame = new JFrame(title);
        frame.setMinimumSize(size);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(game);
        frame.pack();
        game.start();
    }

}

