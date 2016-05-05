package com.pahrfsrw.core;

import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;

import com.pahrfsrw.core.gfx.Pixel;
import com.pahrfsrw.core.gfx.Image;

public class Renderer {
	
	private int width, height;
	private int[] pixels;

	public Renderer(GameContainer gc){
		width = gc.getWidth();
		height = gc.getHeight();
		pixels = ((DataBufferInt)gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
	}
	
	public void setPixel(int x, int y, int color) {
		if(x < 0 || x >= width || y < 0 || y >= height)
			return;
		
		pixels[x+y*width] = color;
	}
	
	public void clear(){
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				setPixel(x, y, 0xff000000);
			}
		}
	}
	
	public void drawImage(Image image, int offX, int offY){
		for(int x = 0; x < image.width; x++){
			for(int y = 0; y < image.height; y++){
				setPixel(x + offX, y + offY, image.pixels[x+y * image.width]);
			}
		}
	}
}
