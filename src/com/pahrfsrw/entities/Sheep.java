package com.pahrfsrw.entities;

import java.awt.Color;
import java.io.Serializable;

import com.pahrfsrw.utils.MyPoint;
import com.pahrfsrw.utils.Utils;

public class Sheep extends Creature{	
	
	public static final double defaultFlightDistance = 80;
	private double flightDuration = 2.0;
	private double flightDistance = defaultFlightDistance;
	private double fenceAvoidDistance = 30;
	
	private double flightDistanceSq = flightDistance*flightDistance;
	
	private double currentFlightTime = 0.0;
	
	public Sheep(){
		super();
		creatureColor = Color.WHITE;
		size = 5;
		noseSize = 6;
		maxSpeed = 100.0;
		maxRotSpeed = Math.PI*4;
	}
	
	public Sheep(MyPoint p){
		super(p);
		creatureColor = Color.WHITE;
		size = 5;
		noseSize = 6;
		maxSpeed = 100.0;
		maxRotSpeed = Math.PI*4;
	}
	
	public Sheep(double x, double y){
		super(x, y);
		creatureColor = Color.WHITE;
		size = 5;
		noseSize = 6;
		maxSpeed = 100.0;
		maxRotSpeed = Math.PI*4;
	}
	
	public boolean shouldIFlee(Creature c){
		if(Creature.distanceSq(this, c) < this.flightDistanceSq && this.canMove){
			this.initFlight();
			return true;
		}
		return false;
	}
	
	private void initFlight(){
		this.currentFlightTime = this.flightDuration;
		this.speed = this.maxSpeed;
	}
	
	public void flee(Creature c, Meadow m, double dt){
		boolean p = shouldIFlee(c);
		
		if(this.currentFlightTime <= 0.0){
			this.speed = 0.0;
			this.rotSpeed = 0.0;
			return;
		}
		this.currentFlightTime -= dt;
		
		// ------------------------------------
		// Látum kindurnar begðast við girðingunum
		// ------------------------------------
		MyPoint point = this.distanceFromFence();
		if(Math.abs(point.getX()) < this.fenceAvoidDistance || Math.abs(point.getY()) < this.fenceAvoidDistance){
			this.pushBack();
		} else {
			// ------------------------------------
			// Ef kindurnar bregðast ekki við girðingu látum við kindurnar begðast við smalanum
			// ------------------------------------
			if(p){
				// Áttin sem kindin þarf að snúa í til þess að komast sem hraðast burt frá smalanum,
				// þ.e. sama horn og kindin þyrfti að snúa í ef hún vildi fara beinustu leið í átt að smalanum.
				double rotationGoal = MyPoint.angle(c.getPosition(), this.getPosition());
				
				double rotationDifference = rotationGoal-this.rotation;
				while(rotationDifference < Math.PI){
					rotationDifference += 2*Math.PI;
				}
				while(rotationDifference > Math.PI){
					rotationDifference -= 2*Math.PI;
				}

				if(Utils.isZero(rotationDifference, Math.PI/6)){
					this.rotSpeed = 0;
				} else {
					if (rotationDifference > 0)
						this.rotSpeed = this.maxRotSpeed;
					else if (rotationDifference <= 0)
						this.rotSpeed = -this.maxRotSpeed;
				}
			}
			else{
				this.rotSpeed = 0;
			}
		}
		
		super.update(dt);
		Gate g = m.getGate();
		if(isInside(g) && g.getSticky() == true){
			this.canMove = false;
		}
	}
	
	public MyPoint distanceFromFence(){
		double x = this.getPosition().getX() < meadow.getWidth()/2 ? -this.getPosition().getX() : meadow.getWidth() - this.getPosition().getX();
		double y = this.getPosition().getY() < meadow.getHeight()/2 ? -this.getPosition().getY() : meadow.getHeight() - this.getPosition().getY();
		
		return new MyPoint(x, y);
	}
	
	public void pushBack(){
		// Þarf að laga það að láta 250 bara vera töfratölu sem er miðjan á svæðinu.
		MyPoint t = new MyPoint(250-this.getPosition().getX(), 250-this.getPosition().getY());
		
		double rotationGoal = t.getT();
		double rotationDifference = rotationGoal-this.rotation;
		
		while(rotationDifference < Math.PI){
			rotationDifference += 2*Math.PI;
		}
		while(rotationDifference > Math.PI){
			rotationDifference -= 2*Math.PI;
		}
		
		// --------------------------
		// Point p = this.distanceFromFence();
		// Ég vil að snúningsáttin sé táknuð með vigri.
		// Lengdin á vigrinum er 1/(fjarlægð að girðingu)
		// double theta = Math.atan(Math.max(p.getX(), p.getY())/Math.min(p.getX(), p.getY()));
		// double r = 1/Math.min(p.getX(), p.getY());
		// Stefnan á vigrinum er erfið...

		if(Utils.isZero(rotationDifference, Math.PI/6)){
			this.rotSpeed = 0;
		} else {
			if (rotationDifference > 0)
				this.rotSpeed = this.maxRotSpeed/2;
			else if (rotationDifference <= 0)
				this.rotSpeed = -this.maxRotSpeed/2;
		}
	}
	
	
	public boolean isInside(Gate g){
		return g.hasInside(this);
	}
	
}
