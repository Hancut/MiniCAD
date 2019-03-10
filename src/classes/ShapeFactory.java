package classes;

import classes.shapes.Canvas;
import classes.shapes.Circle;
import classes.shapes.Diamond;
import classes.shapes.Line;
import classes.shapes.Polygon;
import classes.shapes.Rectangle;
import classes.shapes.Square;
import classes.shapes.Triangle;
import interfaces.Visitable;

public final class ShapeFactory {
    private static ShapeFactory ourInstance = new ShapeFactory();

    public static ShapeFactory getInstance() {
        return ourInstance;
    }

    private ShapeFactory() {
    }

    public Visitable getShape(final ShapeEnum shape) {
        switch (shape) {
            case CANVAS:
                return new Canvas();
            case LINE:
                return new Line();
            case CIRCLE:
                return new Circle();
            case SQUARE:
                return new Square();
            case DIAMOND:
                return new Diamond();
            case POLYGON:
                return new Polygon();
            case TRIANGLE:
                return new Triangle();
            case RECTANGLE:
                return new Rectangle();
                default:
                    return null;
        }
    }
}
