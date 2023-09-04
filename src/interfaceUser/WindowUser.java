package interfaceUser;

import LogicFigures.ActiveFigure;
import LogicFigures.Coord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class WindowUser extends JFrame implements GetColorSquares {
    private static final Squares[][] squares = new Squares[Const.WIDTH][Const.HEiGHT];;
    private static final SquaresNextFigure[][] squaresNextFigure = new SquaresNextFigure[Const.WIDTH_NEXT_FIGURE][Const.HEIGHT_NEXT_FIGURE];;
    private ActiveFigure activeFigure;
    private static ActiveFigure nextFigure;
    private static boolean bool = true;


    public WindowUser() {
        creationWindow();
        creationSquares();
        creationPanelWithNextFigure();
        add(new Paint());
        repaint();
        addKeyListener(new keyAdapter1());
        Timer timer = new Timer(500, new TimeAdapter());
        timer.start();
    }

    private void creationWindow() {
        setSize(Const.WIDTH * Const.SIZE + 500, Const.HEiGHT * Const.SIZE + 35);
        setTitle("Tetris");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }

    private void creationSquares() {
        for (int x = 0; x < Const.WIDTH; x++) {
            for (int y = 0; y < Const.HEiGHT; y++) {
                squares[x][y] = new Squares(x, y);
                add(squares[x][y]);
                squares[x][y].setVisible(true);
            }
        }
    }

    private void creationPanelWithNextFigure() {
        for (int x = 0; x < Const.WIDTH_NEXT_FIGURE; x++) {
            for (int y = 0; y < Const.HEIGHT_NEXT_FIGURE; y++) {
                squaresNextFigure[x][y] = new SquaresNextFigure(x, y);
                add(squaresNextFigure[x][y]);
                squaresNextFigure[x][y].setVisible(true);
            }
        }
    }

    @Override
    public Color getColorSquares(int x, int y) {
        return squares[x][y].getBackground();
    }

    public void addFigure() {
        if (bool) {
            activeFigure = new ActiveFigure(this);
            nextFigure = new ActiveFigure(this);
            showFigure();
            showNextFigure();
            bool = false;
            return;
        }
            activeFigure = nextFigure;
            nextFigure = new ActiveFigure(this);
            showFigure();
            showNextFigure();
    }

    private void showNextFigure() {
        int x, y;
        for (Coord point : nextFigure.getActiveFigure().points) {
            x = point.getX() + 1;
            y = point.getY() + 1;
            /*if (x < 0 || x >= Const.WIDTH_NEXT_FIGURE) {
                continue;
            }
            if (y < 0 || y >= Const.HEIGHT_NEXT_FIGURE) {
                continue;
            }*/

            //
            if (squaresNextFigure[x][y] == null) {
                return;
            }

            // without if nullPointerException
            squaresNextFigure[x][y].setBackground(nextFigure.getActiveFigure().getColor());
            //
        }
    }

    private void hideNextFigure() {
        int x, y;
        for (Coord point : nextFigure.getActiveFigure().points) {
            x = point.getX() + 1;
            y = point.getY() + 1;
            /*if (x < 0 || x >= Const.WIDTH_NEXT_FIGURE) {
                continue;
            }
            if (y < 0 || y >= Const.HEIGHT_NEXT_FIGURE) {
                continue;
            }*/
            squaresNextFigure[x][y].setBackground(Const.BACKGROUND_COLOR);
        }

    }

    private void showFigure() {
        int x, y;
        for (Coord point : activeFigure.getActiveFigure().points) {
            x = point.getX() + activeFigure.getCoord().getX();
            y = point.getY() + activeFigure.getCoord().getY();
            if (x < 0 || x >= Const.WIDTH) {
                continue;
            }
            if (y < 0 || y >= Const.HEiGHT) {
                continue;
            }


            //
            if (squares[x][y] == null) {
                return;
            }

            // without if nullPointerException
            squares[x][y].setBackground(activeFigure.getActiveFigure().getColor());
            //
        }
    }

    private void hideFigure() {
        int x, y;
        for (Coord point : activeFigure.getActiveFigure().points) {
            x = point.getX() + activeFigure.getCoord().getX();
            y = point.getY() + activeFigure.getCoord().getY();
            if (x < 0 || x >= Const.WIDTH) {
                continue;
            }
            if (y < 0 || y >= Const.HEiGHT) {
                continue;
            }
            squares[x][y].setBackground(Const.BACKGROUND_COLOR);
        }

    }

    private void moveHideShow(int x, int y) {
        hideFigure();
        activeFigure.moveFigure(x, y);
        showFigure();
    }

    private boolean isFullLine(int y) {
        for (int x = 0; x < Const.WIDTH; x++) {
            if (squares[x][y].getBackground().equals(Const.BACKGROUND_COLOR)) {
                return false;
            }
        }
        return true;
    }

    private void hideLine(int y) {
        for (int x = 0; x < Const.WIDTH; x++) {
            squares[x][y].setBackground(Const.BACKGROUND_COLOR);
        }
    }

    private void moveFiguresDown(int Y) {
        for (int y = Y; y > 0; y--) {
            for (int x = 0; x < Const.WIDTH; x++) {
                if (!squares[x][y].getBackground().equals(Const.BACKGROUND_COLOR)) {
                    squares[x][y + 1].setBackground(squares[x][y].getBackground());
                    squares[x][y].setBackground(Const.BACKGROUND_COLOR);
                }
            }
        }
    }

    private void removeTheLine() {
        for (int y = 0; y < Const.HEiGHT; y++) {
            if (isFullLine(y)) {
                hideLine(y);
                moveFiguresDown(y);
            }
        }
    }

    private class TimeAdapter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            moveHideShow(0, 1);
            if (activeFigure.isStatic()) {
                hideNextFigure();
                addFigure();
                removeTheLine();

            }
        }
    }

    private class keyAdapter1 extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    moveHideShow(-1, 0);
                    break;
                case KeyEvent.VK_RIGHT:
                    moveHideShow(1, 0);
                    break;
                case KeyEvent.VK_DOWN:
                    moveHideShow(0, 1);
                    break;
                case KeyEvent.VK_UP:
                    hideFigure();
                    activeFigure.turnFigure();
                    showFigure();
                    break;
            }
        }
    }
}