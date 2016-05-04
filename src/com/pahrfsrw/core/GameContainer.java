package com.pahrfsrw.core;

import com.pahrfsrw.utils.Utils;

public class GameContainer implements Runnable{
	
	private Thread thread;
	private AbstractGame game;
	private Renderer renderer;
	private Input input;
	
	private boolean isRunning = false;
	private boolean isPaused = false;
	
	private double fps = 60.0;
	private double frameCap = 1.0/fps;
	
	private int width = 320, height = 240;
	private float scale = 2.0f;
	
	private MainWindow window;

	private String title = "Shepherd Generations";
	
	public GameContainer(AbstractGame game){
		this.game = game;
	}
	
	public void start(){
		if(isRunning)
			return;
		
		window = new MainWindow(this);
		renderer = new Renderer(this);
		thread = new Thread(this);
		input = new Input(this);
		thread.run();
	}
	
	public void stop(){
		if(!isRunning)
			return;
		
		isRunning = false;
	}
	
	public void pause(){
		if(isPaused)
			return;
		
		isPaused = true;
	}
	
	public void resume(){
		if(!isPaused)
			return;
		
		isPaused = false;
	}
	
	public void run()
	{
		isRunning = true;
		
		// Til að telja hvað forritið er búið að vera í gangi lengi
		double programStartTime = System.nanoTime()*Utils.NANO;
		double programRunningTime = programStartTime;
		// -
		
		double firstTime = 0;
		double lastTime = System.nanoTime()*Utils.NANO;
		double passedTime = 0;
		double unprocessedTime = 0;
		
		double frameTime = 0;
		int frames = 0;
		
		while(isRunning)
		{
			// Breyta í true til að rendera ALLTAF. Þá ætti fps að hækka töluvert (hækkaði í 1100+ þegar leikurinn teiknaði bara auðan skjá).
			boolean render = true;
			
			firstTime = System.nanoTime()*Utils.NANO;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;
			
			unprocessedTime += passedTime;
			frameTime += passedTime;
			
			programRunningTime += passedTime;
			
			while(unprocessedTime >= frameCap)
			{
				game.update(this, (float)frameCap);
				input.update();
				unprocessedTime -= frameCap;
				render = true;
				
				if(frameTime >= 1){
					frameTime = 0;
					System.out.println(frames);
					frames = 0;
				}
			}
			
			if(render)
			{
				renderer.clear();
				game.render(this, renderer);
				window.update();
				frames++;
			} 
			else
			{
				try
				{
					Thread.sleep(1);
				}
				catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		}
		
		cleanUp();
	}
	
	private void cleanUp(){
		window.cleanUp();
	}
	
	/* 
	 * Getters and setters
	 *  */
	public MainWindow getWindow() {
		return window;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
