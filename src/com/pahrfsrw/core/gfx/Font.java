package com.pahrfsrw.core.gfx;

public enum Font {
	
	STANDARD("/fonts/standard.png");
	
	public final int NUM_UNICODES = 59; // fjöldi unicode stafa í standard.png
	public int[] offsets = new int[NUM_UNICODES];
	public int[] widths = new int[NUM_UNICODES];
	public Image image;
	
	Font(String path){
		image = new Image(path);
		
		int unicode = -1;
		
		for(int i = 0; i < image.width; i++){
			int color = image.pixels[i];
			
			// 0xff0000ff er blái liturinn sem segir að það sé byrjað á nýjum staf í standard.png
			if(color == 0xff0000ff){
				unicode++;
				offsets[unicode] = i;
			}
			
			// 0xffffff00 er guli liturinn sem segir að það sé búið að lesa staf standard.png
			if(color == 0xffffff00){
				widths[unicode] = i - offsets[unicode];
			}
		}
	}
}
