package LogicFigures;

import interfaceUser.Const;
import interfaceUser.GetColorSquares;

public class ActiveFigure {
    private Figures activeFigure;
    private Coord coord;
    private boolean isStatic;
    private final GetColorSquares square;

    public boolean isStatic() {
        return isStatic;
    }

    public Figures getActiveFigure() {
        return activeFigure;
    }

    public Coord getCoord() {
        return coord;
    }

    public ActiveFigure(GetColorSquares square) {
        activeFigure = Figures.randomFigure();
        coord = new Coord(Const.WIDTH / 2 - 1, activeFigure.topPoint.getY() - activeFigure.botPoint.getY() - 1);
        this.square = square;
    }

    public boolean canMoveFigure(Figures activeFigure, int x, int y) {
        if (coord.getX() + activeFigure.topPoint.getX() + x < 0) {
            return false;
        }
        if (coord.getX() + activeFigure.botPoint.getX() + x >= Const.WIDTH) {
            return false;
        }
        if (coord.getY() + activeFigure.botPoint.getY() + y >= Const.HEiGHT) {
            return false;
        }
        for (Coord point : activeFigure.points) {
            if(point.getY() + coord.getY() + y < 0){
                continue;
            }
            if(!square.getColorSquares(point.getX() + coord.getX() + x, point.getY() + coord.getY() + y).equals(Const.BACKGROUND_COLOR)){
                return false;
            }
        }
        return true;
    }

    public void moveFigure(int x, int y) {
        if (canMoveFigure(activeFigure, x, y)) {
            coord = new Coord(coord.getX() + x, coord.getY() + y);
        }
        else if(y == 1){
            isStatic = true;
        }
    }
    public void turnFigure() {
        Figures rotated = activeFigure.turnFigure();
        if (canMoveFigure(rotated, 0, 0)) {
            activeFigure = rotated;
        } else if (canMoveFigure(rotated, 1, 0)) {
            activeFigure = rotated;
            moveFigure(1, 0);
        } else if (canMoveFigure(rotated, -1, 0)) {
            activeFigure = rotated;
            moveFigure(-1, 0);
        } else if (canMoveFigure(rotated, 0, -1)) {
            activeFigure = rotated;
            moveFigure(0, -1);
        }
    }
}
