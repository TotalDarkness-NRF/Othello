package ca.yorku.eecs3311.a1;

import java.util.Random;

public class RandomVsRandom {
    private static float getProbabilityPlayerWins(int numGames, char player) {
        int playerWins = 0;
        for (int i = 0; i < numGames; i++) {
            OthelloControllerRandomVSRandom oc = new OthelloControllerRandomVSRandom();
            oc.play();
            char winner = oc.othello.getWinner();
            if (player == winner) {
                playerWins++;
            }
        }
        return (float) playerWins / numGames;
    }

    private static float[] getSample(int numSamples, char player) {
        float[] sample = new float[numSamples];
        for (int i = 0; i < numSamples; i++) {
            sample[i] = getProbabilityPlayerWins(numSamples, player);
        }
        return sample;
    }

    public static void main(String[] args) {
        char player = OthelloBoard.P2;
        int numSamples = 50, sampleSize = 100000;
        Random rand = new Random();
        float[] sample = getSample(numSamples, player);
        float sumOfMeans = 0;
        /*
        bootstrap method gets a sample
        then draws random samples, calculate average
        do this for a number of times
         */
        for (int i = 0; i < sampleSize; i++) {
            float sum = 0;
            for (int j = 0; j < numSamples; j++) {
                sum += sample[rand.nextInt(numSamples)];
            }
            sumOfMeans += (sum / numSamples);
        }
        System.out.println("Probability " + player + " wins=" + sumOfMeans/sampleSize);
    }
}
