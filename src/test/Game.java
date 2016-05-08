package test;

import java.awt.event.KeyEvent;

import com.pahrfsrw.core.AbstractGame;
import com.pahrfsrw.core.GameContainer;
import com.pahrfsrw.core.Input;
import com.pahrfsrw.core.Renderer;
import com.pahrfsrw.core.gfx.Image;

public class Game extends AbstractGame{
	
	private Image image = new Image("/smiley.png");
	
	float x = 0;

	@Override
	public void update(GameContainer gc, float dt) {
		
		if(Input.isKey(KeyEvent.VK_UP)){
			x += dt * 100;
		}
		
		if(Input.isKeyPressed(KeyEvent.VK_DOWN)){
			System.out.println("Key down");
		}

		if(Input.isKey(KeyEvent.VK_LEFT)){
	
		}

		if(Input.isKey(KeyEvent.VK_RIGHT)){
	
		}
		
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		
		r.drawImage(image, (int)x, 50);
		
		//r.drawString("Hey",  0xffffff00, 120, 100);
	}
	
	public static void main(String[] args){
		GameContainer gc = new GameContainer(new Game());
		gc.setWidth(320);
		gc.setHeight(240);
		gc.setScale(2);
		gc.start();
	}

}
