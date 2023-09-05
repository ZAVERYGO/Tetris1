package interfaceUser;

import javax.swing.*;
import java.awt.*;

public class NumberOfLines extends JComponent {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        Font font = new Font("Dialog", Font.BOLD, 15);
        graphics2D.setFont(font);
        graphics2D.drawString(String.valueOf(WindowUser.getNumberOfLines()), 520, 300);
    }
}
