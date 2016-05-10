package com.pahrfsrw.entities;

import com.pahrfsrw.utils.MyPoint;

public class Entity {
	protected MyPoint position;
	protected double rotation;
	
	public Entity(){
		this.position = new MyPoint();
	}
	
	public Entity(MyPoint loc){
		this.position = loc.clone();
	}
	
	public Entity(double x, double y){
		this.position = new MyPoint(x, y);
	}
	
	public void move(double x, double y){
		this.position.move(x, y);
	}
	
	public void move(MyPoint p){
		this.position.move(p);
	}
	
	public void movePolar(double r, double t){
		this.position.movePolar(r, t);
	}
	
	public MyPoint getPosition(){
		return this.position;
	}
	
	public double getRotation(){
		return this.rotation;
	}
	
	public void setRotation(double theta){
		this.rotation = theta;
	}
	
	public double distance(Entity e){
		return this.position.distance(e.position);
	}
	
	public static double distance(Entity e1, Entity e2){
		return MyPoint.distance(e1.position, e2.position);
	}
	
	public double distanceSq(Entity e){
		return this.position.distanceSq(e.position);
	}
	
	public static double distanceSq(Entity e1, Entity e2){
		return MyPoint.distanceSq(e1.position, e2.position);
	}
}
