package LogicFigures;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public enum Figures {
    I1(1, 0, 1, 1, 1, 2, 1, 3),
    I2(0, 1, 1, 1, 2, 1, 3, 1),


    J1(1, 0, 1, 1, 1, 2, 0, 2),
    J2(0, 0, 0, 1, 1, 1, 2, 1),
    J3(2, 0, 1, 0, 1, 1, 1, 2),
    J4(0, 1, 1, 1, 2, 1, 2, 2),


    L1(1, 0, 1, 1, 1, 2, 2, 2),
    L2(2, 1, 1, 1, 0, 1, 0, 2),
    L3(0, 0, 1, 0, 1, 1, 1, 2),
    L4(2, 0, 2, 1, 1, 1, 0, 1),


    O(1, 1, 1, 2, 2, 1, 2, 2),


    T1(0, 1, 1, 1, 2, 1, 1, 2),
    T2(1, 0, 1, 1, 1, 2, 0, 1),
    T3(0, 1, 1, 1, 2, 1, 1, 0),
    T4(1, 0, 1, 1, 1, 2, 2, 1),


    S1(0, 2, 1, 2, 1, 1, 2, 1),
    S2(0, 0, 0, 1, 1, 1, 1, 2),

    Z1(0, 1, 1, 1, 1, 2, 2, 2),
    Z2(2, 0, 2, 1, 1, 1, 1, 2);
    public final List<Coord> points;
    public Color color;
    public final Coord topPoint;
    public final Coord botPoint;

    Figures(int... coords) {
        points = new ArrayList<>();
        for (int i = 0; i < coords.length; i += 2) {
            points.add(new Coord(coords[i], coords[i + 1]));
        }
        topPoint = topPoint();
        botPoint = botPoint();
    }

    public Figures turnFigure() {
        return switch (this) {
            case I1 -> I2;
            case I2 -> I1;
            case J1 -> J2;
            case J2 -> J3;
            case J3 -> J4;
            case J4 -> J1;
            case L1 -> L2;
            case L2 -> L3;
            case L3 -> L4;
            case L4 -> L1;
            case O -> O;
            case T1 -> T2;
            case T2 -> T3;
            case T3 -> T4;
            case T4 -> T1;
            case S1 -> S2;
            case S2 -> S1;
            case Z1 -> Z2;
            default -> Z1;
        };
    }

    public static Figures randomFigure() {
        return switch ((int) (Math.random() * 7)) {
            case 0 -> I1;
            case 1 -> J1;
            case 2 -> L1;
            case 3 -> O;
            case 4 -> T1;
            case 5 -> S1;
            default -> Z1;
        };
    }

    public Color getColor() {
        return switch (this) {
            case I1, I2 -> Color.CYAN;
            case J1, J4, J2, J3 -> Color.BLUE;
            case L1, L2, L3, L4 -> Color.ORANGE;
            case O -> Color.YELLOW;
            case T1, T2, T3, T4 -> Color.PINK;
            case S1, S2 -> Color.GREEN;
            default -> Color.RED;
        };
    }

    public Coord topPoint() {
        int x = points.get(0).getX();
        int y = points.get(0).getY();
        for (Coord point : points) {
            if (point.getX() < x) {
                x = point.getX();
            }
            if (point.getY() < y) {
                y = point.getY();
            }
        }
        return new Coord(x, y);
    }

    public Coord botPoint() {
        int x = points.get(0).getX();
        int y = points.get(0).getY();
        for (Coord point : points) {
            if (point.getX() > x) {
                x = point.getX();
            }
            if (point.getY() > y) {
                y = point.getY();
            }
        }
        return new Coord(x, y);
    }
}
