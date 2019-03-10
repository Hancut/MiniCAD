package classes.shapes;

import fileio.implementations.FileReader;
import interfaces.Visitable;
import interfaces.Visitor;

import java.io.IOException;
import java.util.ArrayList;

public final class Line implements Visitable {
    private int xStart, yStart;
    private int xFinal, yFinal;
    private String rgb;
    private int alpha;

    @Override
    public void accept(final Visitor v) {
        v.visit(this);
    }

    @Override
    public void initialize(final FileReader fileReader) throws IOException {
        this.xStart = fileReader.nextInt();
        this.yStart = fileReader.nextInt();
        this.xFinal = fileReader.nextInt();
        this.yFinal = fileReader.nextInt();
        this.rgb = fileReader.nextWord();
        this.alpha = fileReader.nextInt();
    }

    @Override
    public ArrayList<Object> getParameters() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(xStart);
        list.add(yStart);
        list.add(xFinal);
        list.add(yFinal);
        list.add(rgb);
        list.add(alpha);
        return list;
    }
}
