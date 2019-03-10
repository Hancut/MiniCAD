package classes.shapes;

import fileio.implementations.FileReader;
import interfaces.Visitable;
import interfaces.Visitor;

import java.io.IOException;
import java.util.ArrayList;

public final class Rectangle implements Visitable {
    private int xLeftCorner, yLeftCorner;
    private int height, width;
    private String rgbLine, rgbFill;
    private int alphaLine, alphaFill;

    @Override
    public void accept(final Visitor v) {
        v.visit(this);
    }

    @Override
    public void initialize(final FileReader fileReader) throws IOException {
        xLeftCorner = fileReader.nextInt();
        yLeftCorner = fileReader.nextInt();
        height = fileReader.nextInt();
        width = fileReader.nextInt();
        rgbLine = fileReader.nextWord();
        alphaLine = fileReader.nextInt();
        rgbFill = fileReader.nextWord();
        alphaFill = fileReader.nextInt();
    }

    @Override
    public ArrayList<Object> getParameters() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(xLeftCorner);
        list.add(yLeftCorner);
        list.add(height);
        list.add(width);
        list.add(rgbLine);
        list.add(alphaLine);
        list.add(rgbFill);
        list.add(alphaFill);
        return list;
    }
}
