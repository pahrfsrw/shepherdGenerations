package com.pahrfsrw.evolution;

import com.pahrfsrw.entities.Shepherd;
import com.pahrfsrw.misc.SimulationResult;

public class TournamentManager {
	private static final TournamentManager INSTANCE = new TournamentManager();
	
	public static final int tournamentSize = 10;
	private static final boolean elitism = true;
	private static final int defaultElitismOffset = 1; // Það virðist hættulegt að stækka þetta. Ég veit ekki af hverju.

	private TournamentManager(){};
	
	public static TournamentManager getInstance(){
		return INSTANCE;
	}
	
	public Population holdTournament(Population oldPopulation){
		Population newPopulation = new Population(oldPopulation.size(), false);
		
		int elitismOffset;
        if (elitism) {
            elitismOffset = defaultElitismOffset;
        } else {
            elitismOffset = 0;
        }

        if (elitism) {
        	for(int i = 0; i < elitismOffset; i++){
        		newPopulation.saveIndividual(i, oldPopulation.getFittest());
        	}
        }
		
        // Breed population
		for (int i = elitismOffset; i < oldPopulation.size(); i++) {
        	Shepherd indiv1 = tournamentSelection(oldPopulation);
            Shepherd indiv2 = tournamentSelection(oldPopulation);
            Shepherd newIndiv = Evolution.crossover(indiv1, indiv2);
            newPopulation.saveIndividual(i, newIndiv);
        }
		
		// Mutate population
        for (int i = elitismOffset; i < oldPopulation.size(); i++) {
            Evolution.mutate(newPopulation.getIndividual(i));
        }
        
        return newPopulation;
	}
	
	// Select individuals for crossover
	private static Shepherd tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        
        // Get the fittest
        Shepherd fittest = tournament.getFittest();
        return fittest;
    }
}