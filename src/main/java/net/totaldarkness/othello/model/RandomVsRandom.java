package net.totaldarkness.othello.model;

import java.util.Random;

/**
 * Attempts to show that the null hypothesis is correct.
 * Uses bootstrap resampling on a few simulated games to determine the probability
 * that a specified player wins.
 * Bootstrap resampling uses a sample, creates new samples using random data from the sample,
 * in order to create new data from the sample. It repeats this many times to get a new result.
 * Using the class shows that when playing random players against each other, the probability
 * does not change by much for each player, showing that P1 has a disadvantage compared to P2.
 */
public class RandomVsRandom {

    /**
     * Gets the probability that a player wins a game,
     * simulating the desired number of games.
     * @param numGames the number of games to simulate.
     * @param player the player to record wins.
     * @return the probability the player wins from these simulated games
     */
    private static float getProbabilityPlayerWins(int numGames, char player) {
        int playerWins = 0;
        for (int i = 0; i < numGames; i++) {
            OthelloController oc = new OthelloControllerRandomVSRandom();
            oc.play();
            char winner = oc.othello.getWinner();
            if (player == winner) {
                playerWins++;
            }
        }
        return (float) playerWins / numGames;
    }

    /**
     * Gets the sample data using the number of samples requested,
     * for the requested player
     * @param numSamples the number of samples to generate.
     * @param player the player to record wins.
     * @return the samples of the simulated games for player winning.
     */
    private static float[] getSample(int numSamples, char player) {
        float[] sample = new float[numSamples];
        for (int i = 0; i < numSamples; i++) {
            sample[i] = getProbabilityPlayerWins(numSamples, player);
        }
        return sample;
    }

    /**
     * Simulates by default a set of 50 samples of 50 games, calculating the
     * probability that a player wins. Then uses bootstrap resampling to quickly
     * generate more data from the original to create a better estimate of the
     * probability that the player wins. When this method is run multiple times
     * it is clear that the probability that the player wins does not change by much.
     * @param args
     */
    public static void main(String[] args) {
        char player = OthelloBoard.P2;
        int numSamples = 50, sampleSize = 100000;
        Random rand = new Random();
        float[] sample = getSample(numSamples, player);
        float sumOfMeans = 0;
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
