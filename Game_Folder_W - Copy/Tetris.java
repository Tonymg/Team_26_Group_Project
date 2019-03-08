import java.util.ArrayList;
import java.util.*; 
import java.util.List; 
import java.util.Arrays;


public class Tetris 
{

	RunGame myGame;
	Gui myGui;

	
	public Tetris()
	{
		/** creates a new game and places it in the GUI to access the keylistener and 
		use the info from the game*/
		myGame = new RunGame();
		myGui = new Gui("Tetris", myGame);//Creating a instance of the GUI which contains the keylistener
										//which when pressed will run the onePlayerGame in the RunGame object

	}
	
	//LOOPS THE GAME DEPENDING ON IF IT IS ONE PLAYER OR TWO PLAYER
	public void mainMode()
	{
		
		//TAKE IN A CHOICE OF 1 OR OTHER TO DETERMINE IF THE GAME IS 
		//ONE PLAYER OR COMP PLAYER
		int numOfPlayers = myGame.chooseAmountOfPlayers();
		
		//choose between 1 player or playing against the computer
		if(numOfPlayers == 1)
		{
		
			//loop game until end game scenario hits for one player
			while(!myGame.gameFinished())
			{
				//timer to pace the current pieces automatic drop
				sleep();
				myGame.moveAlivePiece();
				
			}
		}
		//loop game until end game scenario hits for two player
		else
		{
			while(!myGame.gameFinished())
			{
				//timer to pace the current pieces automatic drop
				sleep();
				myGame.moveAlivePiece();
			}
		}
		
	}
		
	//USED AS A TIMER TO SLOWLY DROP THE PIECE
	public void sleep()
	{
		try 
		{
			//sending the actual Thread of execution to sleep X milliseconds
			Thread.sleep(1000);
		} catch(InterruptedException ie) {}
	
	}
	
	
	public static void main(String[] args) 
	{
		Tetris newTetris = new Tetris();
		
		//main loop
		newTetris.mainMode();
		
		System.out.println("GAME OVER");
		
		//End game 
		System.exit(0);
	}
}
