package com.pahrfsrw.evolution;

import com.pahrfsrw.entities.Shepherd;
import com.pahrfsrw.misc.SimulationResult;

public class Nature {
	private static final Nature INSTANCE = new Nature();
	private static final int POPULATION_SIZE = 25;
	
	private Population pop;
	private int currentGeneration = 0;
	private int currentIndividualIndex = 0;
	
	public void createLife(){
		currentGeneration++;
		pop = new Population(POPULATION_SIZE, true);
	}
	
	public void continueLife(){
		// while(true)
			Population newPopulation = new Population(pop.size(), false);
			
			// Test individuals
			testPopulation();
			
			// Hold tournament
			pop = TournamentManager.getInstance().holdTournament(pop);
	}
	
	private void testPopulation(){
		for(int i = 0; i < pop.size(); i++){
			Shepherd s = pop.getIndividual(i);
			SimulationResult result = null; //Simulation.runSimulation(s);
			s.setFitness(result);
		}
		pop.sort();
	}
	
	private void setFitness(SimulationResult result){
		pop.getIndividual(currentIndividualIndex).setFitness(result);
		currentIndividualIndex++;
	}
	
	
	
	
	
	
	
	
	public Object[][] getPopInfo(){
		Object[][] data = new Object[pop.size()][5];
		for(int i = 0; i < pop.size(); i++){
			Shepherd s = pop.getIndividual(i);
			data[i][0] = new Integer(i);
			data[i][1] = "n/a";
			data[i][2] = "n/a";
			data[i][3] = s.getPosition().getX();
			data[i][4] = s.getPosition().getY();
		}
		return data;
	}
	
	public Object[] getPopInfo(int rowIndex){
		Object[] data = new Object[5];
			Shepherd s = pop.getIndividual(rowIndex);
			data[0] = new Integer(rowIndex);
			data[1] = "n/a";
			data[2] = "n/a";
			data[3] = s.getPosition().getX();
			data[4] = s.getPosition().getY();
		return data;
	}
	
	public void printPop(){
		System.out.println("Population from PopulationManager (prints only ten): ");
		for(int i = 0; i < 10; i++){
			System.out.println(pop.getIndividual(i).getPosition());
		}
	}
}
