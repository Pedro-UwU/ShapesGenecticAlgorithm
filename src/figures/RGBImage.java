package figures;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RGBImage {
    private final int R = 0, G = 1, B = 2;

    private int[] pixels;
    private int width, height;

    public RGBImage(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new int[width * height * 3]; // [r1, g1, b1, r2, g2, b2]
    }

    public void fill(int color) {
        int[] colors = getColors(color);
        for (int i = 0; i < (width * height); i++) {
            pixels[(i*3)+R] = colors[R];
            pixels[(i*3)+G] = colors[G];
            pixels[(i*3)+B] = colors[B];
        }
    }

    private int[] getColors(int color) {
        int r = (color & 0xFF0000) >>> 16;
        int g = (color & 0x00FF00) >>> 8;
        int b = (color & 0x0000FF);
        int[] result = new int[3];
        result[0] = r;
        result[1] = g;
        result[2] = b;
        return result;
    }

    public void saveImage(String path) {
        BufferedImage bf = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int index = getIndex(x, y);
                bf.setRGB(x, y, getColorInt(pixels[index + R], pixels[index + G], pixels[index + B]));
            }
        }
        try {
            File output_file = new File(path);
            output_file.createNewFile();
            ImageIO.write(bf, "png", output_file);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPixel(int x, int y, int color) {
        if (x < 0 || x > width || y < 0 || y > height) {
            throw new RuntimeException("ERROR: setPixel Invalid Coordinates");
        }
        int index = getIndex(x, y);
        int[] colors = getColors(color);
        pixels[index + R] = colors[R];
        pixels[index + G] = colors[G];
        pixels[index + B] = colors[B];
    }

    private int getColorInt(int r, int g, int b) {
        int col = 0;
        col += r;
        col <<= 8;
        col += g;
        col <<= 8;
        col += b;
        return col;
    }

    private int getIndex(int x, int y) {
        return (y * width + x) * 3;
    }


}
