package interfaceUser;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class Squares extends JPanel {

    public Squares(int x, int y) {
        setBounds(x * Const.SIZE, y * Const.SIZE, Const.SIZE, Const.SIZE);
        setBackground(Const.BACKGROUND_COLOR);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        Line2D line1 = new Line2D.Float(0, 0, 0, Const.SIZE);
        graphics2D.draw(line1);
        Line2D line2 = new Line2D.Float(0, 0, Const.SIZE, 0 );
        graphics2D.draw(line2);
    }
}
