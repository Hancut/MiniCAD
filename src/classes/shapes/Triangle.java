package classes.shapes;

import fileio.implementations.FileReader;
import interfaces.Visitable;
import interfaces.Visitor;

import java.io.IOException;
import java.util.ArrayList;

public final class Triangle implements Visitable {
    private ArrayList<Integer> pointsCoord;
    private String rgbLine, rgbFill;
    private int alphaLine, alphaFill;
    private final int numberOfPoints = 3;

    @Override
    public void accept(final Visitor v) {
        v.visit(this);
    }

    @Override
    public void initialize(final FileReader fileReader) throws IOException {
        pointsCoord = new ArrayList<>();
        for (int i = 0; i < numberOfPoints; i++) {
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
        list.addAll(pointsCoord);
        list.add(rgbLine);
        list.add(alphaLine);
        list.add(rgbFill);
        list.add(alphaFill);
        return list;
    }
}
