package figures;

import figures.fileutils.ImageLoader;

public class Main {
    public static void main(String[] args) {
        RGBImage img = ImageLoader.loadImage("./mona.jpg");
        img.saveImage("test.png");
    }
}
