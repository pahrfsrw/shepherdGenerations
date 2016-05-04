package com.pahrfsrw.core.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {
	
	public int width, height;
	public Color[] pixels;
	
	public Image(String path){
		BufferedImage image = null;
		
		try{
			image = ImageIO.read(Image.class.getResourceAsStream(path));
		}
		catch (IOException e){
			e.printStackTrace();
		}
		
		width = image.getWidth();
		height = image.getHeight();
		int[] p = image.getRGB(0, 0, width, height, null, 0, width);
		pixels = new Color[p.length];
		
		for(int i = 0; i < p.length; i++){
			pixels[i] = new Color((0xff & (p[i] >> 24)) / 255f,
								  (0xff & (p[i] >> 16)) / 255f,
								  (0xff & (p[i] >> 8)) / 255f,
								  (0xff & (p[i])) / 255f
					);
		}
		
		image.flush();
	}
}
