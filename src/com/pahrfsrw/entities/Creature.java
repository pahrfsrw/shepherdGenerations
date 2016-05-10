package com.pahrfsrw.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.pahrfsrw.utils.MyPoint;
import com.pahrfsrw.utils.Utils;

public class Creature extends Entity{
	
	protected Meadow meadow;
	
	public double maxSpeed = 200;
	public double maxRotSpeed = Math.PI;
	
	protected boolean canMove = true;
	
	protected double rotSpeed = 0.0;
	protected double speed = 0.0;
	
	// Variables for drawing
	protected Color creatureColor = Color.BLACK;
	protected int size = 10;
	protected int noseSize = 15;
	
	public Creature(){
		super();
	}
	
	public Creature(MyPoint p){
		super(p);
	}
	
	public Creature(double x, double y){
		super(x, y);
	}
	
	public Creature RandCreature(){
		double x = Math.random()*Meadow.getDefaultWidth();
		double y = Math.random()*Meadow.getDefaultHeight();
		MyPoint p = new MyPoint(x, y);
		return new Creature(p);
	}
	
	public void setSpeed(double speed){
		this.speed = speed;
	}
	
	public void setRotSpeed(double rot){
		this.rotSpeed = rot;
	}
	
	public void update(double du){
		if(!canMove){return;}
		
		MyPoint nextPosition = this.getPosition().clone();
		nextPosition.translatePolar(this.speed*du, this.rotation);
		
		this.rotation += du*this.rotSpeed;
		
		if(Utils.isInside(nextPosition, 0, 0, meadow.getWidth(), meadow.getHeight()))
			this.translatePolar(this.speed*du, this.rotation);
	}
	
	public void translate(double x, double y){
		this.getPosition().translate(x, y);
	}
	
	public void translate(MyPoint p){
		this.getPosition().translate(p);
	}
	
	public void translatePolar(double r, double t){
		this.getPosition().translatePolar(r, t);
	}
	
	public void setCanMove(boolean p){
		this.canMove = p;
	}
	

	
	public void draw(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(creatureColor);
		int x = (int) Math.round(this.getPosition().getX());
		int y = (int) Math.round(this.getPosition().getY());
		g2d.fillOval(x-this.size, y-this.size, 2*this.size, 2*this.size);
		
		int xPointer = x + (int) Math.round(noseSize*Math.cos(this.rotation));
		int yPointer = y + (int) Math.round(noseSize*Math.sin(this.rotation));
		g2d.drawLine(x, y, xPointer, yPointer);
	}
	
	public void setMeadow(Meadow m){
		this.meadow = m;
	}
}
