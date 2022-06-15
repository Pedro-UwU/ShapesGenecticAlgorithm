package figures;

import figures.fileutils.ImageLoader;
import figures.genetic.GeneticAlgorithm;

public class Main {
    public static void main(String[] args) {
        RGBImage img = ImageLoader.loadImage("./cafecito.jpg");
        RGBImage base = new RGBImage(img.getWidth(), img.getHeight());
//        img.drawSquare(50, 50, 10, 30, 0xFF0000);
//        img.saveImage("test.png");
//        RGBImage img = new RGBImage(400, 400);
//        Square[] squares = new Square[45];
//        for (int i = 0; i < 45; i++) {
//            squares[i] = Square.getRandomSquare(0, 0, 400, 400, 15, 15, 100, 100);
//
//            img.drawSquare(squares[i].x, squares[i].y, squares[i].w, squares[i].h, squares[i].r, squares[i].g, squares[i].b);
//        }
//        img.saveImage("test.png");
        GeneticAlgorithm.run(img, base);
    }

}
