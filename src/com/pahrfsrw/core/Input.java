package com.pahrfsrw.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Input implements KeyListener, MouseListener, MouseMotionListener{
	
	private GameContainer gc;
	
	private static boolean[] keys = new boolean[256]; // 256 er bara tala sem er stærri heldur en fjöldi takka á lyklaborðinu
	private static boolean[] keysLast = new boolean[256];
	private static boolean[] buttons = new boolean[5];
	private static boolean[] buttonsLast = new boolean[5];
	
	public Input(GameContainer gc){
		this.gc = gc;
		gc.getWindow().getCanvas().addKeyListener(this);
		gc.getWindow().getCanvas().addMouseListener(this);
		gc.getWindow().getCanvas().addMouseMotionListener(this);
	}
	
	public void update(){
		this.keysLast = keys.clone();
		this.buttonsLast = buttons.clone();
	}
	
	public static boolean isKey(int keyCode){
		return keys[keyCode];
	}
	
	public static boolean isKeyPressed(int keyCode){
		return keys[keyCode] && !keysLast[keyCode];
	}
	
	public static boolean isKeyReleased(int keyCode){
		return !keys[keyCode] && keysLast[keyCode];
	}
	
	public static boolean isButton(int button){
		return buttons[button];
	}
	
	public static boolean isButtonPressed(int button){
		return buttons[button] && !buttonsLast[button];
	}
	
	public static boolean isButtonReleased(int button){
		return !buttons[button] && buttonsLast[button];
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		buttons[e.getButton()] = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		buttons[e.getButton()] = false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
