import interfaceUser.Text;
import interfaceUser.WindowUser;

public class Tetris {
    public static void main(String[] args) {
        WindowUser windowUser = new WindowUser();
        windowUser.add(new Text());

        windowUser.addFigure();
        /*JFrame jFrame = new JFrame();
        jFrame.setSize(300, 300);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.add(new Paint());*/
    }
}