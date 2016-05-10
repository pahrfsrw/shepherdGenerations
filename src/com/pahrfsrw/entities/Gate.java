package com.pahrfsrw.entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.pahrfsrw.utils.Utils;

public class Gate extends Entity{
		
		private static int defaultWidth = 100;
		private static int defaultHeight = 50;
		private static boolean defaultSticky = true;
		
		private int width = defaultWidth;
		private int height = defaultHeight;
		private boolean sticky = defaultSticky;
		
		private Meadow parentMeadow;
		
		public Gate(){super();}
		
		public Gate(int width, int height, boolean sticky){
			this.width = width;
			this.height = height;
			this.sticky = sticky;
		}
		
		public Gate(int width, int height){
			this.width = width;
			this.height = height;
		}
		
		public int getWidth(){
			return this.width;
		}
		
		public int getHeight(){
			return this.height;
		}
		
		public boolean getSticky(){
			return this.sticky;
		}
		
		public void setParentMeadow(Meadow m){
			this.parentMeadow = m;
		}
		
		public Meadow getParentMeadow(){
			return this.parentMeadow;
		}
		
		public static double getDefaultHeight(){
			return defaultHeight;
		}
		
		public static void setDefaultHeight(int height){
			defaultHeight = height;
		}
		
		public static double getDefaultWidth(){
			return defaultWidth;
		}
		
		public static void setDefaultWidth(int width){
			defaultWidth = width;
		}
		
		public static boolean getDefaultSticky(){
			return defaultSticky;
		}
		
		public static void setDefaultSticky(boolean sticky){
			defaultSticky = sticky;
		}
		
		public boolean hasInside(Entity e){
			
			double leftX = this.getPosition().getX() - this.width/2;
			double rightX = this.getPosition().getX() + this.width/2;
			double topY = this.getPosition().getY() - this.height/2;
			double bottomY = this.getPosition().getY() + this.height/2;
			return Utils.isInside(e.getPosition(), leftX, topY, rightX, bottomY);

		}
		
		public void draw(Graphics g){
			Graphics2D g2d = (Graphics2D) g;
			
			g2d.setColor(Color.RED);
			g2d.setStroke(new BasicStroke(3));
			int x = (int) Math.round(this.getPosition().getX());
			int y = (int) Math.round(this.getPosition().getY());
			int[] xPoints = {x-this.width/2, x+this.width/2, x+this.width/2, x-this.width/2};
			int[] yPoints = {y-this.height/2, y-this.height/2, y+this.height/2, y+this.height/2};
			
			g2d.drawPolygon(xPoints, yPoints, 4);
		}
	}
