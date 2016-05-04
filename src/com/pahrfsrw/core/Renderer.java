package com.pahrfsrw.core;

import java.awt.image.DataBufferByte;

import com.pahrfsrw.core.gfx.Color;
import com.pahrfsrw.core.gfx.Image;

public class Renderer {
	
	private int width, height;
	private byte[] pixels;

	public Renderer(GameContainer gc){
		width = gc.getWidth();
		height = gc.getHeight();
		pixels = ((DataBufferByte)gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
	}
	
	public void setPixel(int x, int y, Color c) {
		if(x < 0 || x >= width || y < 0 || y >= height || c.a == 0)
			return;
		
		int index = (x+y*width)*4;
		pixels[index] = (byte)((c.a * 255f) + 0.5f); // 0.5f er til þess að gildin námundist rétt því type casting sker það sem er á bak við kommu bara af.
		pixels[index+1] = (byte)((c.b * 255f) + 0.5f);
		pixels[index+2] = (byte)((c.g * 255f) + 0.5f);
		pixels[index+3] = (byte)((c.r * 255f) + 0.5f);
	}
	
	public void clear(){
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				setPixel(x, y, Color.BLACK);
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
