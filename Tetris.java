/**THIS CLASS IS THE ONE THAT IS RUN.  IT CALLS UPON THE METHODS IN RUNGAME TO WORK */

import java.util.ArrayList;

public class Tetris 
{

	RunGame myGame;
	
	public Tetris()
	{
		myGame = new RunGame();
	}
	
	public static void main(String[] args) 
	{
		Tetris newTetris = new Tetris();
		
		System.out.println("GAME OVER");
	}
}
