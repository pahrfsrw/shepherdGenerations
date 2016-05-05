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
		// TODO Auto-generated method stub
		
		r.drawImage(image, (int)x, 50);
	}
	
	public static void main(String[] args){
		GameContainer gc = new GameContainer(new Game());
		gc.start();
	}

}
