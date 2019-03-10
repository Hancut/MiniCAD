package classes.shapes;

import fileio.implementations.FileReader;
import interfaces.Visitable;
import interfaces.Visitor;

import java.io.IOException;
import java.util.ArrayList;

public final class Circle implements Visitable {
    private int xCenter, yCenter;
    private int ray;
    private String rgbLine, rgbFill;
    private int alphaLine, alphaFill;

    @Override
    public void accept(final Visitor v) {
        v.visit(this);
    }

    @Override
    public void initialize(final FileReader fileReader) throws IOException {
        xCenter = fileReader.nextInt();
        yCenter = fileReader.nextInt();
        ray = fileReader.nextInt();
        rgbLine = fileReader.nextWord();
        alphaFill = fileReader.nextInt();
        rgbFill = fileReader.nextWord();
        alphaFill = fileReader.nextInt();
    }

    @Override
    public ArrayList<Object> getParameters() {
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(xCenter);
        list.add(yCenter);
        list.add(ray);
        list.add(rgbLine);
        list.add(alphaLine);
        list.add(rgbFill);
        list.add(alphaFill);
        return list;
    }
}
