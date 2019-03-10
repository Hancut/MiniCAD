package classes.shapes;

import fileio.implementations.FileReader;
import interfaces.Visitable;
import interfaces.Visitor;

import java.io.IOException;
import java.util.ArrayList;

public final class Square implements Visitable {
    private int xLeftCorner, yLeftCorner;
    private int dimension;
    private String rgbLine, rgbFill;
    private int alphaLine, alphaFill;

    @Override
    public void accept(final Visitor v) {
        v.visit(this);
    }

    @Override
    public void initialize(final FileReader fileReader) throws IOException {
        this.xLeftCorner = fileReader.nextInt();
        this.yLeftCorner = fileReader.nextInt();
        this.dimension = fileReader.nextInt();
        this.rgbLine = fileReader.nextWord();
        this.alphaLine = fileReader.nextInt();
        this.rgbFill = fileReader.nextWord();
        this.alphaFill = fileReader.nextInt();
    }

    @Override
    public ArrayList<Object> getParameters() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(xLeftCorner);
        list.add(yLeftCorner);
        list.add(dimension);
        list.add(rgbLine);
        list.add(alphaLine);
        list.add(rgbFill);
        list.add(alphaFill);
        return list;
    }
}
