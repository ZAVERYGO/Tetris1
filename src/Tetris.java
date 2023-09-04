import interfaceUser.Paint;
import interfaceUser.WindowUser;

import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Tetris {
    public static void main(String[] args) {
        WindowUser windowUser = new WindowUser();
        windowUser.addFigure();
        /*JFrame jFrame = new JFrame();
        jFrame.setSize(300, 300);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.add(new Paint());*/
    }
}