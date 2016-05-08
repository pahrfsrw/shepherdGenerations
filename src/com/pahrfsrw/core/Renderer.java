package com.pahrfsrw.core;

import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;

import com.pahrfsrw.core.gfx.Pixel;
import com.pahrfsrw.core.gfx.Font;
import com.pahrfsrw.core.gfx.Image;

public class Renderer {
	
	//-
	public static final int BLACK = 0xff000000;
	public static final int BLUE = 0xff0000ff;
	public static final int GREEN = 0xff00ff00;
	public static final int CYAN = 0xff00ffff;
	public static final int RED = 0xffff0000;
	public static final int MAGENTA = 0xffff00ff;
	public static final int YELLOW = 0xffff00;
	public static final int WHITE = 0xffffffff;
	//-
	
	private int width, height;
	private int[] pixels;
	private Font font = Font.STANDARD;

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
	
	public void drawString(String text, int color, int offX, int offY)
	{
		text = text.toUpperCase(); // Það eru bara hástafir í standard.png

		int offset = 0;
		for (int i = 0; i < text.length(); i++)
		{
			int unicode = text.codePointAt(i) - 32;

			for (int x = 0; x < font.widths[unicode]; x++)
			{
				for (int y = 1; y < font.image.height; y++)
				{
					if (font.image.pixels[(x + font.offsets[unicode]) + y * font.image.width] == 0xffffffff)
						setPixel(x + offX + offset, y + offY - 1, color);
				}
			}

			offset += font.widths[unicode];
		}
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
