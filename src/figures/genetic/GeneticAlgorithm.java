package figures.genetic;

import figures.RGBImage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GeneticAlgorithm {
    private static final int popSize = 150;
    private static final int maxSquares = 2000;
    private static final int maxGen = 20;

    public static void run(RGBImage target, RGBImage baseImg) {
        GeneticSquare best = null;
        double best_fit = Double.POSITIVE_INFINITY;


        for (int total_squares = 0; total_squares < maxSquares; total_squares++) {
            List<GeneticSquare> population = new ArrayList<>();
            ArrayList<GeneticSquare> newPop = new ArrayList<>();

            for (int gen = 0; gen < maxGen; gen++) {
                int popToAdd = 0;
                if (population.isEmpty()) {
                    popToAdd = popSize;
                } else {
                    popToAdd = popSize/2;
                }
                for (int i = 0; i < popToAdd; i++) {
                    newPop.add(GeneticSquare.getRandomGSquare(0, 0, target.getWidth(), target.getHeight(), 10, 10, target.getWidth(), target.getHeight()));
                }

                for (int i = 0; i < popSize; i++) {
                    GeneticSquare sq = newPop.get(i);
                    if (sq.getFitness() != 0) continue; //Do not re calculate fitness

                    RGBImage img = baseImg.copy();
                    img.drawSquare(sq.x, sq.y, sq.w, sq.h, sq.r, sq.g, sq.b);
                    sq.setFitness(target.getSquaredDistance(img));
                }

                newPop.sort(Comparator.comparing(GeneticSquare::getFitness));
                population = newPop.subList(0, popSize/2);
                newPop = new ArrayList<>(population);
//            }

            }
            best = population.get(0);
            if (best.getFitness() <= best_fit) {
                baseImg.drawSquare(best.x, best.y, best.w, best.h, best.r, best.g, best.b);
                best_fit = best.getFitness();
            }
            if (total_squares % 5 == 0) {
                baseImg.saveImage(String.format("./images/%d.png", total_squares));
            }

            System.out.println("Ready Gen: " + total_squares + " fit: " + best_fit);
        }


    }
}
