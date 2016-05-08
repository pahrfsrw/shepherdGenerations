package com.pahrfsrw.core.gfx;

public enum Font {
	
	STANDARD("/fonts/standard.png");
	
	public final int NUM_UNICODES = 59; // fj�ldi unicode stafa � standard.png
	public int[] offsets = new int[NUM_UNICODES];
	public int[] widths = new int[NUM_UNICODES];
	public Image image;
	
	Font(String path){
		image = new Image(path);
		
		int unicode = -1;
		
		for(int i = 0; i < image.width; i++){
			int color = image.pixels[i];
			
			// 0xff0000ff er bl�i liturinn sem segir a� �a� s� byrja� � n�jum staf � standard.png
			if(color == 0xff0000ff){
				unicode++;
				offsets[unicode] = i;
			}
			
			// 0xffffff00 er guli liturinn sem segir a� �a� s� b�i� a� lesa staf standard.png
			if(color == 0xffffff00){
				widths[unicode] = i - offsets[unicode];
			}
		}
	}
}
