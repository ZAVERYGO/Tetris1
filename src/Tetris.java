import interfaceUser.Text;
import interfaceUser.WindowUser;

public class Tetris {
    public static void main(String[] args) {
        WindowUser windowUser = new WindowUser();
        windowUser.add(new Text());

        windowUser.addFigure();

    }
}