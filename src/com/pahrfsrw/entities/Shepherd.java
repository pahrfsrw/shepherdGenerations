package com.pahrfsrw.entities;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.pahrfsrw.misc.SimulationResult;
import com.pahrfsrw.utils.MyPoint;

public class Shepherd extends Creature implements Comparable<Shepherd> {
	
	private static int defaultGeneLength = 400000; // Tvö stök fyrir hvern in-game tímapunkt í hermuninni (og nokkur ónotuð auka stök aftast).
												   // Annað stakið af þeim tveimur táknar snúning, hitt hreyfingu.
												   // 0 er öfug hreyfing/snúningur, 1 er ekkert, 2 er jákvæð hreyfing/snúningur.
	
	private int genePointer = 0;
	private byte[] genes = new byte[defaultGeneLength];
	private SimulationResult fitness = new SimulationResult(0, Integer.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, false, Double.MAX_VALUE, false);
	
	
	// Constructors
	public Shepherd(){
		super(100, 450);
	}
	
	public Shepherd(MyPoint p){
		super(p);
	}
	
	public Shepherd(double x, double y){
		super(x, y);
	}
	
	@Override
	public void update(double du){
		if(true){
			obeyGenes(this.genePointer);
			genePointer++;
		}
		super.update(du);
	}
	
	private void obeyGenes(int geneIndex){
		if(this.genes[geneIndex] == 0){
			this.setSpeed(-this.maxSpeed);
		} else if (this.genes[geneIndex] == 1){
			this.setSpeed(0);
		} else if (this.genes[geneIndex] == 2){
			this.setSpeed(this.maxSpeed);
		}
		
		int l = this.genes.length/2;
		if(this.genes[l+geneIndex] == 0){
			this.setRotSpeed(-this.maxRotSpeed);
		} else if (this.genes[l+geneIndex] == 1){
			this.setRotSpeed(0);
		} else if (this.genes[l+geneIndex] == 2){
			this.setRotSpeed(this.maxRotSpeed);
		}
	}
	
	// Getters and setters
	public static void setDefaultGeneLength(int length) {
        defaultGeneLength = length;
    }
	
	public byte getGene(int index) {
        return genes[index];
    }
	
	public SimulationResult getFitness() {
		return this.fitness;
    }
	
	public void setFitness(SimulationResult fitness){
		this.fitness = fitness;
	}
	
	public void setGene(int index, byte value) {
        this.genes[index] = value;
    }
	
	// Other methods
	public void generateShepherd(){
		for (int i = 0; i < size(); i++) {
           byte gene = (byte)(Math.floor(3*Math.random()));
           this.genes[i] = gene;
           //genes[i] = 0;
        }
	}
	
	public int size() {
        return this.genes.length;
    }
	
	@Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < size(); i++) {
            geneString += getGene(i);
        }
        return geneString;
    }
	
	public int compareTo(Shepherd s){
		return(this.fitness.compareTo(s.getFitness()));
	}
	
	@Override
	public Shepherd clone(){
		Shepherd clone = new Shepherd(this.getPosition().clone());
		clone.genes = this.genes.clone();
		clone.fitness = this.fitness.clone();
		return clone;
	}
}

