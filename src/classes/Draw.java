package classes;

import classes.shapes.Canvas;
import classes.shapes.Circle;
import classes.shapes.Diamond;
import classes.shapes.Line;
import classes.shapes.Polygon;
import classes.shapes.Rectangle;
import classes.shapes.Square;
import classes.shapes.Triangle;
import interfaces.Visitor;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public final class Draw implements Visitor {
    private BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    public int sign(final int x) {
        if (x > 0) {
            return 1;
        } else if (x < 0) {
            return -1;
        }
        return 0;
    }

    public void drawLine(final int xStart, final int yStart,
                            final int xFin, final int yFin,
                                final int argb) {
        int x = xStart;
        int y = yStart;
        int xFinal = xFin;
        int yFinal = yFin;

        if ((yFinal > image.getHeight()
                && y > image.getHeight())
                    || (xFinal > image.getWidth()
                        && x > image.getWidth())) {
            return;
        }
        if (yFinal > image.getHeight()) {
            yFinal = image.getHeight();
        }
        if (xFinal > image.getWidth()) {
            xFinal = image.getWidth();
        }
        if (y > image.getHeight()) {
            y = image.getHeight() - 1;
        }
        if (x > image.getWidth()) {
            x = image.getWidth() - 1;
        }
        if (yFinal < 0) {
            yFinal = 0;
        }
        if (xFinal < 0) {
            xFinal = 0;
        }
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }

        int deltaX = Math.abs(xFinal - x);
        int deltaY = Math.abs(yFinal - y);
        int signX = sign(xFinal - x);
        int signY = sign(yFinal - y);
        int interchange;
        boolean interchanged;

        if (deltaY > deltaX) {
            interchange = deltaX;
            deltaX = deltaY;
            deltaY = interchange;
            interchanged = true;
        } else {
            interchanged = false;
        }

        int error = 2 * deltaY - deltaX;

        for (int i = 0; i < deltaX; i++) {
            image.setRGB(x, y, argb);
            while (error > 0) {
                if (interchanged) {
                    x = x + signX;
                } else {
                    y = y + signY;
                }
                error = error - 2 * deltaX;
            }

            if (interchanged) {
                y = y + signY;
            } else {
                x = x + signX;
            }
            error = error + 2 * deltaY;
        }
    }

    public boolean isValid(final int x, final int y) {
        if (x < 0 || x >= image.getWidth()
                || y >= image.getHeight() || y < 0) {
            return false;
        }
        return true;
    }

    public void drawCircle(final int xCenter, final int yCenter,
                            final int x, final int y, final int argb) {
        if (isValid(xCenter + x, yCenter + y)) {
            image.setRGB(xCenter + x, yCenter + y, argb);
        }
        if (isValid(xCenter - x, yCenter + y)) {
            image.setRGB(xCenter - x, yCenter + y, argb);
        }
        if (isValid(xCenter + x, yCenter - y)) {
            image.setRGB(xCenter + x, yCenter - y, argb);
        }
        if (isValid(xCenter - x, yCenter - y)) {
            image.setRGB(xCenter - x, yCenter - y, argb);
        }
        if (isValid(xCenter - y, yCenter - x)) {
            image.setRGB(xCenter - y, yCenter - x, argb);
        }
        if (isValid(xCenter + y, yCenter - x)) {
            image.setRGB(xCenter + y, yCenter - x, argb);
        }
        if (isValid(xCenter - y, yCenter + x)) {
            image.setRGB(xCenter - y, yCenter + x, argb);
        }
        if (isValid(xCenter + y, yCenter + x)) {
            image.setRGB(xCenter + y, yCenter + x, argb);
        }
    }

    public void fillForm(final int x, final int y, final int argb,
                            final int argbLine) {
        Pair coordinates = new Pair(x, y);
        LinkedList<Pair> mylist = new LinkedList<>();
        mylist.add(coordinates);

        while (!mylist.isEmpty()) {
            Pair currentPixel = mylist.removeLast();
            int xCurrent = (Integer) currentPixel.getKey();
            int yCurrent = (Integer) currentPixel.getValue();
            if (xCurrent < image.getWidth() && yCurrent < image.getHeight()) {
                if (image.getRGB(xCurrent, yCurrent) != argb
                        && image.getRGB(xCurrent, yCurrent) != argbLine) {
                    image.setRGB(xCurrent, yCurrent, argb);
                }
            }

            // Take neighbours
            // UP
            Pair up = new Pair(xCurrent, yCurrent - 1);
            if (xCurrent < image.getWidth() && yCurrent - 1 > 0
                    && yCurrent - 1 < image.getHeight()) {
                if (image.getRGB(xCurrent, yCurrent - 1) != argb
                        && image.getRGB(xCurrent, yCurrent - 1) != argbLine) {
                    mylist.add(up);
                }
            }
            // DOWN
            Pair down = new Pair(xCurrent, yCurrent + 1);
            if (xCurrent < image.getWidth() && yCurrent + 1 < image.getHeight()) {
                if (image.getRGB(xCurrent, yCurrent + 1) != argb
                        && image.getRGB(xCurrent, yCurrent + 1) != argbLine) {
                    mylist.add(down);
                }
            }
            // LEFT
            Pair left = new Pair(xCurrent - 1, yCurrent);
            if (xCurrent - 1 > 0 && xCurrent - 1 < image.getWidth()
                    && yCurrent < image.getHeight()) {
                if (image.getRGB(xCurrent - 1, yCurrent) != argb
                        && image.getRGB(xCurrent - 1, yCurrent) != argbLine) {
                    mylist.add(left);
                }
            }
            // RIGHT
            Pair right = new Pair(xCurrent + 1, yCurrent);
            if (xCurrent + 1 < image.getWidth() && yCurrent < image.getHeight()) {
                if (image.getRGB(xCurrent + 1, yCurrent) != argb
                        && image.getRGB(xCurrent + 1, yCurrent) != argbLine) {
                    mylist.add(right);
                }
            }

        }
    }

    public int argbValue(final Object justRGB, final Object justAlpha) {
        final int hexNumber = 16;
        String rgb = justRGB.toString();
        String alpha = Integer.toHexString((int) justAlpha);
        int argb = Integer.parseInt(rgb.replaceAll("#", alpha), hexNumber);
        return argb;
    }

    @Override
    public void visit(final Canvas c) {
        image = new BufferedImage((int) c.getParameters().get(0),
                (int) c.getParameters().get(1), BufferedImage.TYPE_INT_ARGB);

        int constanta = 0;
        int width = (int) c.getParameters().get(constanta);
        int height = (int) c.getParameters().get(++constanta);
        int argb = argbValue(c.getParameters().get(++constanta),
                                c.getParameters().get(++constanta));

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                image.setRGB(j, i, argb);
            }
        }
    }

    @Override
    public void visit(final Line l) {
        int constanta = 0;
        int xStart = (int) l.getParameters().get(constanta);
        int yStart = (int) l.getParameters().get(++constanta);
        int xFinal = (int) l.getParameters().get(++constanta);
        int yFinal = (int) l.getParameters().get(++constanta);
        int argb = argbValue(l.getParameters().get(++constanta),
                                l.getParameters().get(++constanta));
        drawLine(xStart, yStart, xFinal, yFinal, argb);
    }

    @Override
    public void visit(final Polygon p) {
        int constanta = 0;
        final int constanta2 = 3;
        int numberOfPoints = (int) p.getParameters().get(constanta);
        int argbLine = argbValue(p.getParameters().get(numberOfPoints * 2 + 1),
                p.getParameters().get(numberOfPoints * 2 + 2));
        int argbFill = argbValue(p.getParameters().get(numberOfPoints * 2 + constanta2),
                p.getParameters().get(numberOfPoints * 2 + constanta2 + 1));

        int xStart = (int) p.getParameters().get(++constanta);
        int yStart = (int) p.getParameters().get(++constanta);
        int xfinal = xStart;
        int yfinal = yStart;
        int sumX = xStart;
        int sumY = yStart;
        for (int i = 0; i < numberOfPoints - 1; i++) {
            int xFinal = (int) p.getParameters().get(++constanta);
            int yFinal = (int) p.getParameters().get(++constanta);
            drawLine(xStart, yStart, xFinal, yFinal, argbLine);
            xStart = xFinal;
            yStart = yFinal;
            sumX += xStart;
            sumY += yStart;
        }
        drawLine(xStart, yStart, xfinal, yfinal, argbLine);

        fillForm(sumX / numberOfPoints, sumY / numberOfPoints, argbFill, argbLine);
    }

    @Override
    public void visit(final Circle c) {
        int constanta = 0;
        int xCenter = (int) c.getParameters().get(constanta);
        int yCenter = (int) c.getParameters().get(++constanta);
        int ray = (int) c.getParameters().get(++constanta);
        int argbLine = argbValue(c.getParameters().get(++constanta),
                c.getParameters().get(++constanta));
        int argbFill = argbValue(c.getParameters().get(++constanta),
                c.getParameters().get(++constanta));

        int x = 0;
        int y = ray;
        final int number3 = 3;
        int d = number3 - 2 * ray;
        final int number10 = 10;
        final int number6 = 6;
        final int number4 = 4;

        while (y >= x) {
            drawCircle(xCenter, yCenter, x, y, argbLine);
            x++;
            if (d > 0) {
                y--;
                d = d + number4 * (x - y) + number10;
            } else {
                d = d + number4 * x + number6;
            }
            drawCircle(xCenter, yCenter, x, y, argbLine);
        }

        fillForm(xCenter, yCenter, argbFill, argbLine);
    }

    @Override
    public void visit(final Diamond d) {
        int constanta = 0;
        int xCenter = (int) d.getParameters().get(constanta);
        int yCenter = (int) d.getParameters().get(++constanta);
        int horizontal = (int) d.getParameters().get(++constanta);
        horizontal = horizontal / 2;
        int vertical = (int) d.getParameters().get(++constanta);
        vertical = vertical / 2;
        int argbLine = argbValue(d.getParameters().get(++constanta),
                d.getParameters().get(++constanta));
        int argbFill = argbValue(d.getParameters().get(++constanta),
                d.getParameters().get(++constanta));

        drawLine(xCenter, yCenter - vertical, xCenter + horizontal, yCenter, argbLine);
        drawLine(xCenter + horizontal, yCenter, xCenter, yCenter + vertical, argbLine);
        drawLine(xCenter, yCenter + vertical, xCenter - horizontal, yCenter, argbLine);
        drawLine(xCenter - horizontal, yCenter, xCenter, yCenter - vertical, argbLine);

        fillForm(xCenter, yCenter, argbFill, argbLine);
    }

    @Override
    public void visit(final Rectangle r) {
        int constanta = 0;
        int xStart = (int) r.getParameters().get(constanta);
        int yStart = (int) r.getParameters().get(++constanta);
        int height = (int) r.getParameters().get(++constanta);
        int width = (int) r.getParameters().get(++constanta);
        int argbLine = argbValue(r.getParameters().get(++constanta),
                r.getParameters().get(++constanta));
        int argbFill = argbValue(r.getParameters().get(++constanta),
                r.getParameters().get(++constanta));

        drawLine(xStart, yStart, xStart + width, yStart, argbLine);
        drawLine(xStart + width, yStart, xStart + width, yStart + height, argbLine);
        drawLine(xStart + width, yStart + height, xStart, yStart + height, argbLine);
        drawLine(xStart, yStart, xStart, yStart + height, argbLine);


        int x = xStart + width / 2;
        int y = yStart + height / 2;
        if (xStart + width > image.getWidth()) {
            x = (image.getWidth() - xStart) / 2 + xStart;
        }
        if (yStart + height > image.getHeight()) {
            y = (image.getHeight() - yStart) / 2 + yStart;
        }
        fillForm(x, y, argbFill, argbLine);
    }

    @Override
    public void visit(final Square s) {
        int constanta = 0;
        int xStart = (int) s.getParameters().get(constanta);
        int yStart = (int) s.getParameters().get(++constanta);
        int dim = (int) s.getParameters().get(++constanta);
        int argbLine = argbValue(s.getParameters().get(++constanta),
                s.getParameters().get(++constanta));
        int argbFill = argbValue(s.getParameters().get(++constanta),
                s.getParameters().get(++constanta));

        drawLine(xStart, yStart, xStart, yStart + dim, argbLine);
        drawLine(xStart, yStart, xStart + dim, yStart, argbLine);
        drawLine(xStart + dim, yStart, xStart + dim, yStart + dim, argbLine);
        drawLine(xStart + dim, yStart + dim, xStart, yStart + dim, argbLine);

        int x = xStart + dim / 2;
        int y = yStart + dim / 2;
        if (xStart + dim > image.getWidth()) {
            x = (image.getWidth() - xStart) / 2 + xStart;
        }
        if (yStart + dim > image.getHeight()) {
            y = (image.getHeight() - yStart) / 2 + yStart;
        }
        fillForm(x, y, argbFill, argbLine);
    }

    @Override
    public void visit(final Triangle t) {
        int constanta = 0;
        final int numberOfPoints = 3;
        int argbLine = argbValue(t.getParameters().get(numberOfPoints * 2),
                t.getParameters().get(numberOfPoints * 2 + 1));
        int argbFill = argbValue(t.getParameters().get(numberOfPoints * 2),
                t.getParameters().get(numberOfPoints * 2 + numberOfPoints));

        int xStart = (int) t.getParameters().get(constanta);
        int yStart = (int) t.getParameters().get(++constanta);
        int xfinal = xStart;
        int yfinal = yStart;
        int sumX = xStart;
        int sumY = yStart;
        for (int i = 0; i < numberOfPoints - 1; i++) {
            int xFinal = (int) t.getParameters().get(++constanta);
            int yFinal = (int) t.getParameters().get(++constanta);
            drawLine(xStart, yStart, xFinal, yFinal, argbLine);
            xStart = xFinal;
            yStart = yFinal;
            sumX += xStart;
            sumY += yStart;
        }
        drawLine(xStart, yStart, xfinal, yfinal, argbLine);
        fillForm(sumX / numberOfPoints, sumY / numberOfPoints, argbFill, argbLine);
    }
}
