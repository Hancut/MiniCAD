import classes.Draw;
import classes.ShapeEnum;
import classes.ShapeFactory;
import fileio.implementations.FileReader;
import interfaces.Visitable;

import javax.imageio.ImageIO;
import java.io.IOException;

public final class Main {

    private Main() {
    }

    public static void main(final String[] args) throws IOException {
        FileReader fileReader = new FileReader("input.txt");

        int n = fileReader.nextInt();

        String nextString;
        ShapeFactory factory = ShapeFactory.getInstance();
        Draw draw = new Draw();
        Visitable form;

        for (int i = 0; i < n; i++) {
            nextString = fileReader.nextWord();
            form = factory.getShape(ShapeEnum.valueOf(nextString));
            form.initialize(fileReader);
            form.accept(draw);
        }
        ImageIO.write(draw.getImage(), "png", new java.io.File("draw.png"));
    }
}
