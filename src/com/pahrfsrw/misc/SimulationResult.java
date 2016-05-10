package com.pahrfsrw.misc;

import com.pahrfsrw.entities.Sheep;

public class SimulationResult implements Comparable<SimulationResult>{
	
	/* Ra�a� eftir mikilv�gi ni�urst��u */
	public final int sheepHerded; 					// Fj�ldi kinda sem var b�i� a� smala �egar t�minn kl�ra�ist.
	public final int time;	  					// T�minn sem �a� t�k smalann a� smala s��ustu kindinni sem t�kst a� smala ��ur en t�minn kl�ra�ist.
	public final double avgHerdDistance;			// Me�alfjarl�g� hjar�arinnar fr� hli�inu �egar t�minn kl�ra�ist.
	public final double herdDensity;				// Me�alfjarl�g� hjar�arinnar fr� eigin massami�ju.
	public final boolean hasHerdMoved;			 	// �a� �arf a� hvetja smalann til a� reyna a� hreyfa kindurnar.
	public final double distanceToClosestSheep; 	// �a� �arf a� hvetja smalann til a� n�lgast kindurnar.
	public final boolean hasShepherdMoved; 			// �a� �arf a� hvetja smalann til a� hreyfa sig.
	
	private static int timeBenefit = 0;
	private static double hDistBenefit = 0;
	private static double hDensBenefit = 0;
	private static double dtcsBenefit = 0;
	
	public SimulationResult(int sheepHerded, 
							int time, 
							double avgHerdDistance,
							double herdDensity,
							boolean hasHerdMoved, 
							double distanceToClosestSheep, 
							boolean hasShepherdMoved)
	{
		this.sheepHerded = sheepHerded;
		this.time = time;
		this.avgHerdDistance = avgHerdDistance;
		this.herdDensity = herdDensity;
		this.hasHerdMoved = hasHerdMoved;
		this.distanceToClosestSheep = distanceToClosestSheep;
		this.hasShepherdMoved = hasShepherdMoved;
	}
	
	public static SimulationResult getWorst(){
		return new SimulationResult(0, Integer.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, false, Double.MAX_VALUE, false);
	}
	
	public Object[] toArray(){
		Object[] o = {this.sheepHerded, this.time, this.avgHerdDistance, this.herdDensity, this.hasHerdMoved, this.distanceToClosestSheep, this.hasShepherdMoved};
		return o;
	}
	
	
	public int compareTo(SimulationResult res){
		if(this.sheepHerded > res.sheepHerded) return 1;
		else if(this.sheepHerded > res.sheepHerded) return -1;
		else{
			if(this.time - timeBenefit < res.time) return 1;
			else if(res.time - timeBenefit < this.time) return -1;
			else{
				if(this.avgHerdDistance - hDistBenefit < res.avgHerdDistance) return 1;
				else if (res.avgHerdDistance - hDistBenefit < this.avgHerdDistance) return -1;
				else{
					if(this.herdDensity - hDensBenefit < res.herdDensity) return 1;
					else if (res.avgHerdDistance - hDensBenefit < this.herdDensity) return -1;
					else{
						if(this.hasHerdMoved == true && res.hasHerdMoved == false) return 1;
						else if (res.hasHerdMoved == true && this.hasHerdMoved == false) return -1;
						else{
							if(this.distanceToClosestSheep - dtcsBenefit < res.distanceToClosestSheep) return 1;
							else if (res.distanceToClosestSheep - dtcsBenefit < this.distanceToClosestSheep) return -1;
							else{
								if(this.hasShepherdMoved == true && res.hasShepherdMoved == false) return 1;
								else if (res.hasShepherdMoved == true && this.hasShepherdMoved == false) return -1;
								else{
									return 0;
								}
							}
						}
					}
				}
			}
		}
	}
	
	public double toNumber(){
		double fitness = ((double)(sheepHerded+1))*(1/(double)time)*(avgHerdDistance)*(herdDensity);
		if(distanceToClosestSheep <= Sheep.defaultFlightDistance){
			return fitness; // Ef smali kemst innan fl�ttarad�uss kindar h�ttir a� skipta m�li hva� hann komst n�lagt �eim s��ar meir.
		} else {
			fitness *= distanceToClosestSheep;
		}
		return fitness;
	}
	
	@Override
	public String toString(){
		String s = "";
		s = s + "Sheep herded: " + Integer.toString(this.sheepHerded) + "\n";
		s = s + "Time taken: " + Integer.toString(this.time) + "\n";
		s = s + "Avg. herd distance: " + Double.toString(this.avgHerdDistance) + "\n";
		s = s + "Avg. herd density: " + Double.toString(this.herdDensity) + "\n";
		s = s + "Distance to closest sheep: " + Double.toString(this.distanceToClosestSheep) + "\n";
		return s;
	}
	
	@Override
	public SimulationResult clone(){
		return new SimulationResult(
					this.sheepHerded,
					this.time,
					this.avgHerdDistance,
					this.herdDensity,
					this.hasHerdMoved,
					this.distanceToClosestSheep,
					this.hasShepherdMoved
				);
	}
	
	public static void main(String[] args){
		SimulationResult a = new SimulationResult(0, 100000, 10.0, 10.0, false, 10.0, false);
		SimulationResult b = SimulationResult.getWorst();
		System.out.println(a.compareTo(b));
		System.out.println(b.compareTo(a));
	}
}
