package test;

import java.awt.event.KeyEvent;

import com.pahrfsrw.core.AbstractGame;
import com.pahrfsrw.core.GameContainer;
import com.pahrfsrw.core.Input;
import com.pahrfsrw.core.Renderer;
import com.pahrfsrw.core.gfx.Image;
import com.pahrfsrw.entities.EntityManager;

public class ShepherdSimulator extends AbstractGame{
	
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
		
	}
	
	public static void main(String[] args){
		GameContainer gc = new GameContainer(new Game());
		gc.setWidth(500);
		gc.setHeight(500);
		gc.setScale(1);
		gc.start();
	}

}

