package com.pahrfsrw.core;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.pahrfsrw.core.ui.ControlPanel;
import com.pahrfsrw.core.ui.InfoPanel;
import com.pahrfsrw.core.ui.Toolbar;

public class MainWindow {
	
	private JFrame frame;
	private Canvas canvas;
	private BufferedImage image;
	private Graphics g;
	private BufferStrategy bs;
	
	public MainWindow(GameContainer gc){
		image = new BufferedImage(gc.getWidth(), gc.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		canvas = new Canvas();
		Dimension dim = new Dimension(  
										(int)(gc.getWidth() * gc.getScale()), 
									    (int)(gc.getHeight()*gc.getScale())
									 );
		canvas.setMaximumSize(dim);
		canvas.setPreferredSize(dim);
		
		frame = new JFrame(gc.getTitle());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setLayout(new BorderLayout());
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
		
		//-
		Toolbar.getInstance().addTo(frame);
		ControlPanel.getInstance().addTo(frame);
		//-
		
		frame.add(canvas, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null); // Frame starts in the middle of screen insetead of top left
		frame.setResizable(false);
		frame.setVisible(true);
		
		canvas.createBufferStrategy(1);
		bs = canvas.getBufferStrategy();
		g = bs.getDrawGraphics();
		
		//-
		InfoPanel.getInstance().addTo(frame);
		frame.pack();
		//-
	}
	
	public void update(){
		g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
		bs.show();
	}
	
	public void cleanUp(){
		g.dispose();
		bs.dispose();
		image.flush();
		frame.dispose();
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public BufferedImage getImage() {
		return image;
	}

}
