package figures.fileutils;

import figures.RGBImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {
    public static RGBImage loadImage(String path) {
        BufferedImage bf = null;
        try {
            File f = new File(path);
            bf = ImageIO.read(f);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int height = bf.getHeight();
        int width = bf.getWidth();

        RGBImage img = new RGBImage(width, height);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int col = bf.getRGB(x, y);
                img.setPixel(x, y, col);
            }
        }

        return img;
    }
}
