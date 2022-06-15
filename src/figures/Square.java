package figures;

import java.util.Random;

public class Square {
    public int x, y, w, h, r, g, b;

    public Square(int x, int y, int w, int h, int r, int g, int b) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public static Square getRandomSquare(int minX, int minY, int maxX, int maxY, int minW, int minH, int maxW, int maxH) {
        Random rnd = new Random();
        int x = (int)(rnd.nextDouble() * (maxX - minX)) + minX;
        int y = (int)(rnd.nextDouble() * (maxY - minY)) + minY;
        int w = (int)(rnd.nextDouble() * (maxW - minW)) + minW;
        int h = (int)(rnd.nextDouble() * (maxH - minH)) + minH;
        int r = (int)(rnd.nextDouble() * 256);
        int g = (int)(rnd.nextDouble() * 256);
        int b = (int)(rnd.nextDouble() * 256);
        return new Square(x, y, w, h, r, g, b);
    }

}
