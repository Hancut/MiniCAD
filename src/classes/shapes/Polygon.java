package classes.shapes;

import fileio.implementations.FileReader;
import interfaces.Visitable;
import interfaces.Visitor;

import java.io.IOException;
import java.util.ArrayList;

public final class Polygon implements Visitable {
    private int numberOfpoints;
    private ArrayList<Integer> pointsCoord;
    private String rgbLine, rgbFill;
    private int alphaLine, alphaFill;

    @Override
    public void accept(final Visitor v) {
        v.visit(this);
    }

    @Override
    public void initialize(final FileReader fileReader) throws IOException {
        numberOfpoints = fileReader.nextInt();
        pointsCoord = new ArrayList<>();
        for (int i = 0; i < numberOfpoints; i++) {
            pointsCoord.add(fileReader.nextInt());
            pointsCoord.add(fileReader.nextInt());
        }
        rgbLine = fileReader.nextWord();
        alphaLine = fileReader.nextInt();
        rgbFill = fileReader.nextWord();
        alphaFill = fileReader.nextInt();
    }

    @Override
    public ArrayList<Object> getParameters() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(numberOfpoints);
        list.addAll(pointsCoord);
        list.add(rgbLine);
        list.add(alphaLine);
        list.add(rgbFill);
        list.add(alphaFill);
        return list;
    }
}
