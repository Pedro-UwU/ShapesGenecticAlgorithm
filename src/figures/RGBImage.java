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

        int[] colors = getColors(color);
        fillPixel(x, y, colors);
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

    public int[] getPixelRGB(int x, int y) {
        int index = getIndex(x, y);
        int[] colors = new int[3];
        colors[R] = pixels[index + R];
        colors[G] = pixels[index + G];
        colors[B] = pixels[index + B];
        return colors;
    }

    public double getSquaredDistance(RGBImage other) {
        if (other.width != width || other.height != height) {
            throw new RuntimeException("ERROR Trying to calculate distance of images with different sizes");
        }

        double total_diff = 0;

         for (int x = 0; x < width; x++) {
             for (int y = 0; y < height; y++) {
                 int index = getIndex(x, y);
                 int[] my_colors = {pixels[index + R], pixels[index + G], pixels[index + B]};
                 int[] other_colors = other.getPixelRGB(x, y);
                 double diff = 0;
                 for (int i = 0; i < 3; i++) {
                    diff += Math.abs((my_colors[i] - other_colors[i])/255.0);
                 }
                 total_diff += (diff/3.0);
             }
         }
         return total_diff;
    }

    public void drawSquare(int x, int y, int w, int h, int color) {
        if (x < 0 || x >= width || y < 0 || y >= height || w < 0 || h < 0) {
            throw new RuntimeException("ERROR: drawSquare Invalid Coordinates");
        }

        int[] colors = getColors(color);
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                fillPixel(x+i, y+j, colors);
            }
        }
    }

    public void drawSquare(int x, int y, int w, int h, int r, int g, int b) {
        if (x < 0 || x >= width || y < 0 || y >= height || w < 0 || h < 0) {
            throw new RuntimeException("ERROR: drawSquare Invalid Coordinates");
        }

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (x+i >= width || y+j >= height) continue;
                fillPixel(x+i, y+j, new int[]{r,g,b});
            }
        }
    }

    private void fillPixel(int x, int y, int[] colors) {
        int index = getIndex(x, y);
        pixels[index + R] = colors[R];
        pixels[index + G] = colors[G];
        pixels[index + B] = colors[B];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public RGBImage copy() {
        RGBImage img = new RGBImage(width, height);
        for (int i = 0; i < pixels.length; i++) {
            img.pixels[i] = pixels[i];
        }
        return img;
    }
}

