package com.pahrfsrw.core.ui;

import javax.swing.JFrame;

public class InfoPanel {
	// Bjó til nýjan klasa MyPanel sem lætur það vera mjög auðvelt og þægilegt
	// að sýna hvaða gögn sem maður vill OG gerir það mjög auðvelt að sýna uppfærslur
	// á öllum gögnum.
	private static final InfoPanel INSTANCE = new InfoPanel();
	
	private MyPanel infoPanel = new MyPanel();
	
	private InfoPanel(){
	
		infoPanel.addCell("info01", "Current generation info:");
		infoPanel.addCell("filler01");
		infoPanel.addCell("gen", "Current generation: ", "n/a");
		infoPanel.addCell("genTime", "Current gen. time: ", "n/a");
		infoPanel.addCell("genIndiv", "Current indiv: ", "n/a");
		infoPanel.addCell("genFrames", "Current gen in-game time: ", "n/a");
		infoPanel.addCell("sheep", "Sheep herded: ", "n/a");
	
		infoPanel.addCell("filler02");
		infoPanel.addCell("filler03");
		infoPanel.addCell("filler04");
		infoPanel.addCell("info02", "General info:");
		
		infoPanel.addCell("filler05");
		
		infoPanel.addCell("totalTime", "Total time: ", "n/a");
		
		infoPanel.addCell("totalFrames", "Total in-game time: ", "n/a");
		
		infoPanel.addCell("critTime", "Critical in-game time:", "n/a");
		infoPanel.addCell("herdSize", "Herd size:", "n/a");
		
		infoPanel.addCell("bestTime", "Best time: ", "n/a");
		infoPanel.addCell("mostSheepHerded", "Most sheep herded:", "n/a");
	
	}
	
	public static InfoPanel getInstance(){
		return INSTANCE;
	}
	
	public void addTo(JFrame frame){
		frame.add(infoPanel);
	}
}
