package interfaceUser;

import LogicFigures.ActiveFigure;

import javax.swing.*;
import java.awt.*;

public class Text extends JComponent {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font font = new Font("Dialog", Font.BOLD, 15);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setFont(font);
        graphics2D.drawString("next figure", 450,  20);
        graphics2D.drawString("number of lines: ", 400, 300);
    }

}
