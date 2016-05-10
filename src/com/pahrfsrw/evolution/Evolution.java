package com.pahrfsrw.evolution;

import java.util.concurrent.CountDownLatch;

import com.pahrfsrw.entities.Shepherd;

public class Evolution {
	
	private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.02;

    // Crossover individuals
    public static Shepherd crossover(Shepherd indiv1, Shepherd indiv2) {
    	Shepherd newSol = new Shepherd(100, 450);
        // Loop through genes
        for (int i = 0; i < indiv1.size(); i++) {
            // Crossover
            if (Math.random() <= uniformRate) {
                newSol.setGene(i, indiv1.getGene(i));
            } else {
                newSol.setGene(i, indiv2.getGene(i));
            }
        }
        return newSol;
    }

    // Mutate an individual
    public static void mutate(Shepherd indiv) {
        // Loop through genes
        for (int i = 0; i < indiv.size(); i++) {
            if (Math.random() <= mutationRate) {
                // Create random gene
            	byte gene = (byte)(Math.floor(3*Math.random()));
                indiv.setGene(i, gene);
            }
        }
    }

}


