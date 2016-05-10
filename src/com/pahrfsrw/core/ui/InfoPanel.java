package com.pahrfsrw.core.ui;

import javax.swing.JFrame;

public class InfoPanel {
	// Bj� til n�jan klasa MyPanel sem l�tur �a� vera mj�g au�velt og ��gilegt
	// a� s�na hva�a g�gn sem ma�ur vill OG gerir �a� mj�g au�velt a� s�na uppf�rslur
	// � �llum g�gnum.
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
