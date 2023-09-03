package interfaceUser;

import javax.swing.*;

public class Squares extends JPanel {

    public Squares(int x, int y) {
        setBounds(x * Const.SIZE, y * Const.SIZE, Const.SIZE, Const.SIZE);
        setBackground(Const.backgroundColor);
    }
}
