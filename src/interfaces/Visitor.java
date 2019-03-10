package interfaces;

import classes.shapes.Circle;
import classes.shapes.Diamond;
import classes.shapes.Polygon;
import classes.shapes.Rectangle;
import classes.shapes.Square;
import classes.shapes.Triangle;

public interface Visitor {
    void visit(classes.shapes.Canvas c);
    void visit(classes.shapes.Line l);
    void visit(Polygon p);
    void visit(Circle c);
    void visit(Diamond d);
    void visit(Rectangle r);
    void visit(Square s);
    void visit(Triangle t);
}
