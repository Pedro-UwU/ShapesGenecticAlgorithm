package figures.genetic;

import figures.Square;

import java.util.Random;

public class GeneticSquare extends Square {
    private double fitness;
    public GeneticSquare(int x, int y, int w, int h, int r, int g, int b) {
        super(x, y, w, h, r, g, b);
        fitness = 0;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public static GeneticSquare getRandomGSquare(int minX, int minY, int maxX, int maxY, int minW, int minH, int maxW, int maxH) {
        Random rnd = new Random();
        int x = (int)(rnd.nextDouble() * (maxX - minX)) + minX;
        int y = (int)(rnd.nextDouble() * (maxY - minY)) + minY;
        int w = (int)(rnd.nextDouble() * (maxW - minW)) + minW;
        int h = (int)(rnd.nextDouble() * (maxH - minH)) + minH;
        int r = (int)(rnd.nextDouble() * 256);
        int g = (int)(rnd.nextDouble() * 256);
        int b = (int)(rnd.nextDouble() * 256);
        return new GeneticSquare(x, y, w, h, r, g, b);
    }
}
