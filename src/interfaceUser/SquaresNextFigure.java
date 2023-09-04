package interfaceUser;

import javax.swing.*;

public class SquaresNextFigure extends JPanel {
    public SquaresNextFigure(int x, int y) {
        setBounds((x + Const.WIDTH + 3)  * Const.SIZE, (y + 1) * Const.SIZE, Const.SIZE, Const.SIZE);
        setBackground(Const.BACKGROUND_COLOR);
    }
}
