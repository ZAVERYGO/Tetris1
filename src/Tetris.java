import interfaceUser.WindowUser;

import javax.swing.*;

public class Tetris {
    public static void main(String[] args) {
        WindowUser windowUser = new WindowUser();
        SwingUtilities.invokeLater(windowUser);
        windowUser.addFigure();
    }
}