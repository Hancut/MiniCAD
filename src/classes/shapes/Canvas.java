package classes.shapes;

import fileio.implementations.FileReader;
import interfaces.Visitable;
import interfaces.Visitor;

import java.io.IOException;
import java.util.ArrayList;

public final class Canvas implements Visitable {
    private int height, width;
    private String rgb;
    private int alpha;

    @Override
    public void accept(final Visitor v) {
        v.visit(this);
    }

    @Override
    public void initialize(final FileReader fileReader) throws IOException {
        this.height = fileReader.nextInt();
        this.width = fileReader.nextInt();
        this.rgb = fileReader.nextWord();
        this.alpha = fileReader.nextInt();
    }

    @Override
    public ArrayList<Object> getParameters() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(this.width);
        list.add(this.height);
        list.add(this.rgb);
        list.add(this.alpha);
        return list;
    }
}
