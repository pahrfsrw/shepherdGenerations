package test;

import java.awt.event.KeyEvent;

import com.pahrfsrw.core.AbstractGame;
import com.pahrfsrw.core.GameContainer;
import com.pahrfsrw.core.Input;
import com.pahrfsrw.core.Renderer;

public class Game extends AbstractGame{

	@Override
	public void update(GameContainer gc, float dt) {
		
		if(Input.isKeyPressed(KeyEvent.VK_UP)){
			System.out.println("Hey");
		}
		
		if(Input.isKey(KeyEvent.VK_DOWN)){
			
		}

		if(Input.isKey(KeyEvent.VK_LEFT)){
	
		}

		if(Input.isKey(KeyEvent.VK_RIGHT)){
	
		}
		
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		// TODO Auto-generated method stub
		

	}
	
	public static void main(String[] args){
		GameContainer gc = new GameContainer(new Game());
		gc.start();
	}

}
