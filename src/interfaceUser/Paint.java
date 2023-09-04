package interfaceUser;

import javax.swing.*;
import java.awt.*;

public class Paint extends JComponent {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Font font = new Font("Dialog", Font.BOLD, 10);
        Graphics2D graphics2D = (Graphics2D) g;
        //graphics2D.setFont(font);
        graphics2D.drawString("next figure", 500,  100);
    }

}
