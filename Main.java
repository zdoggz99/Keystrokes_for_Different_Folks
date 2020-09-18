import java.awt.*;

public class Main {
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static void main(String[] args){
        new Game(screenSize);
    }
}
