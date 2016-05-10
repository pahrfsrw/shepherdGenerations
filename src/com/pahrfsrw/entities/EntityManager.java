package com.pahrfsrw.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;

import com.pahrfsrw.utils.MyPoint;
import com.pahrfsrw.utils.Utils;

public class EntityManager {
	
	private static EntityManager INSTANCE = new EntityManager();
	
	private Shepherd shepherd;
	private ArrayList<Sheep> sheep;
	private ArrayList<Sheep> collectedSheep;
	private Meadow meadow;
	
	private int sheepCol = 5;
	private int sheepRow = 5;
	
	private EntityManager(){
		
	};
	
	/*
	 * Getters
	 */
	public static EntityManager getInstance(){
		return INSTANCE;
	}
	
	public Meadow getMeadow(){
		return meadow;
	}
	
	public Shepherd getShepherd(){
		return shepherd;
	}
	
	public void setShepherd(Shepherd s){
		shepherd = s;
	}
	
	public boolean winCondition(){
		return sheep.size() == 0;
	}
	
	public int getHerded(){
		return collectedSheep.size();
	}
	
	public int getHerdSize(){
		return sheep.size();
	}
	
	public double getHerdDistance(){
		MyPoint gateCenter = this.meadow.getGate().getPosition();
		double totalDistance = 0;
		int size = sheep.size();
		if(size == 0) return 0;
		for(int i = 0; i < size; i++){
			Sheep currentSheep = sheep.get(i);
			totalDistance += gateCenter.distance(currentSheep.getPosition());
		}
		return totalDistance/(double)size;
	}
	
	public double getHerdDensity(){
		MyPoint centerOfMass;
		double comX = 0;
		double comY = 0;
		double density = 0;
		int size = sheep.size();
		if(size == 0) return 0;
		// Reiknum út massamiðju
		for(int i = 0; i < size; i++){
			Sheep currentSheep = sheep.get(i);
			comX += currentSheep.getPosition().getX();
			comY += currentSheep.getPosition().getY();
		}
		comX /= (double)size;
		comY /= (double)size;
		centerOfMass = new MyPoint(comX, comY);
		
		// Reiknum út þéttleika
		for(int i = 0; i < size; i++){
			Sheep currentSheep = sheep.get(i);
			density += centerOfMass.distance(currentSheep.getPosition());
		}
		
		return density/(double)size;
		
	}
	
	public double getDistanceToClosestSheep(){
		double minDist = Double.MAX_VALUE;
		for(int i = 0; i < sheep.size(); i++){
			Sheep currentSheep = sheep.get(i);
			double distance = shepherd.getPosition().distance(currentSheep.getPosition());
			if(distance < minDist){
				minDist = distance;
			}
		}
		return minDist;
	}
	
	/*
	 * Updating methods
	 */
	public void update(double du){
		updateShepherd(du);
		updateSheep(shepherd, meadow, du);
	}
	
	public void updateShepherd(double du){
		shepherd.update(du);
	}
	
	public void updateSheep(Shepherd shepherd, Meadow meadow, double du){
		// LinkedList er hraðvirkara en ArrayList í okkar tilgangi.
		LinkedList<Integer> indices = new LinkedList<Integer>();
		
		for(int i = 0; i < sheep.size(); i++){
			Sheep currentSheep = sheep.get(i);
			currentSheep.flee(shepherd, meadow, du);
			if(currentSheep.isInside(meadow.getGate())){
				indices.push(i);
			}
		}
		while(indices.size() != 0){
			int index = indices.pop();
			collectedSheep.add(sheep.get(index));
			sheep.remove(index);
		}
	}
	
	
	/*
	 * Rendering methods
	 */
	public void draw(Graphics g){
		drawMeadow(g);
		drawSheep(g);
		drawShepherd(g);
	}
	
	public void drawShepherd(Graphics g){
		shepherd.draw(g);
	}
	
	public void drawSheep(Graphics g){
		for(int i = 0; i < sheep.size(); i++){
			sheep.get(i).draw(g);
		}
		
		for(int i = 0; i < collectedSheep.size(); i++){
			collectedSheep.get(i).draw(g);
		}
	}
	
	public void drawMeadow(Graphics g){
		meadow.draw(g);
	}
	
	/*
	 * Cleanup and initialization
	 */
	public void newSim(Shepherd s){
		this.cleanUp();
		
		this.meadow = new Meadow();
		this.shepherd = s;
		s.setMeadow(meadow);
		
		this.sheep = new ArrayList<Sheep>();
		this.collectedSheep = new ArrayList<Sheep>();

		double density = 5;
		
		MyPoint topLeft = new MyPoint(350, 350);
		MyPoint bottomRight = new MyPoint(topLeft.getX()+100, topLeft.getY()+100);
		MyPoint[] points = Utils.initPoints(sheepCol, sheepRow, topLeft, bottomRight, density);
		for(int i = 0; i < points.length; i++){
			Sheep oneSheep = new Sheep(points[i]);
			oneSheep.setMeadow(this.meadow);
			sheep.add(oneSheep);
		}
	}
	
	public void cleanUp(){
		this.shepherd = null;
		this.sheep = null;
		this.collectedSheep = null;
		this.meadow = null;
	}

}

