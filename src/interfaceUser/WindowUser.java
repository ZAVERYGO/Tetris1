package interfaceUser;

import LogicFigures.ActiveFigure;
import LogicFigures.Coord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class WindowUser extends JFrame implements Runnable, GetColorSquares {
    private final Squares[][] squares;
    private ActiveFigure activeFigure;


    public WindowUser() {
        squares = new Squares[Const.WIDTH][Const.HEiGHT];
        addKeyListener(new keyAdapter1());
        Timer timer = new Timer(500, new TimeAdapter());
        timer.start();
    }

    @Override
    public void run() {
        creationWindow();
        creationSquares();
    }

    private void creationWindow() {
        setSize(Const.WIDTH * Const.SIZE + 200, Const.HEiGHT * Const.SIZE + 35);
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

    @Override
    public Color getColorSquares(int x, int y) {
        return squares[x][y].getBackground();
    }

    public void addFigure() {
        activeFigure = new ActiveFigure(this);
        showFigure();
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
            if(squares[x][y] == null){
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
            squares[x][y].setBackground(Const.backgroundColor);
        }

    }

    private void moveHideShow(int x, int y) {
        hideFigure();
        activeFigure.moveFigure(x, y);
        showFigure();
    }

    private boolean isFullLine(int y) {
        for (int x = 0; x < Const.WIDTH; x++) {
            if (squares[x][y].getBackground().equals(Const.backgroundColor)) {
                return false;
            }
        }
        return true;
    }

    private void hideLine(int y) {
        for (int x = 0; x < Const.WIDTH; x++) {
            squares[x][y].setBackground(Const.backgroundColor);
        }
    }
    private void moveFiguresDown(int Y){
        for (int y = Y; y > 0; y--) {
            for (int x = 0; x < Const.WIDTH; x++) {
                if(!squares[x][y].getBackground().equals(Const.backgroundColor)){
                    squares[x][y + 1].setBackground(squares[x][y].getBackground());
                    squares[x][y].setBackground(Const.backgroundColor);
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
                removeTheLine();
                addFigure();
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