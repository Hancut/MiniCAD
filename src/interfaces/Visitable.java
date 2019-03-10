package interfaces;

import fileio.implementations.FileReader;

import java.io.IOException;
import java.util.ArrayList;

public interface Visitable {
    void accept(Visitor v);
    void initialize(Visitable this, FileReader fileReader) throws IOException;
    ArrayList<Object> getParameters();
}
