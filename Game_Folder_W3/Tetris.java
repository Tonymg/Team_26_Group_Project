import java.util.ArrayList;
import java.util.*; 
import java.util.List; 
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;


public class Tetris 
{

	boolean isOneGame;
	RunGame myGame1;
	RunGame myGame2;
	Gui myGui;
	//created volitile int because of multithreading issue trying to get info from a keylistener
	private volatile int numOfPlayers;

	
	public Tetris()
	{
		myGui = new Gui("Tetris");
	}
	
	/** based off how many players have been choosen initialize the proper amount
	and add the the Gui instance*/
	public void init(int isOne)
	{
		
		if(isOne == 1)
		{
			/** creates a new game and places it in the GUI to access the keylistener and 
			use the info from the game*/
			this.myGame1 = new RunGame();
			this.myGui.initGames(myGame1);
										//which when pressed will run the onePlayerGame in the RunGame object
		}
		else
		{
			
			/** creates a new game and places it in the GUI to access the keylistener and 
			use the info from the game*/
			this.myGame1 = new RunGame();
			this.myGame2 = new RunGame();
			this.myGui.initGames(this.myGame1, this.myGame2);

		}

		
	}
	
	/**LOOPS THE GAME DEPENDING ON IF IT IS ONE PLAYER OR TWO PLAYER*/
	public void mainMode()
	{
		
		this.numOfPlayers = 0;
		
		//HERE YOU CAN CALL A METHOD THAT CREATES THE MAIN MENU
		
		System.out.println("Press 1 for a one player game and 2 for two player");
		
		//loops for player to choose single or two player game
		//needs revision
		while(true)
		{
			//wasn't working earlier because of multithreading and it was stuck in one thread
			//changed var this.numOfPlayers to be volitile so that it will be read from memory and not the cache
			this.numOfPlayers = myGui.getNumOfPlayers();
			
			//if the user input then break loop
			if (this.numOfPlayers > 0)
			{
				break;
			}
		
		}


		//choose between 1 player or playing against the computer
		if(numOfPlayers == 1)
		{
			/** initialize a one player game*/
			init(numOfPlayers);
			
						//HERE YOU CAN CALL A METHOD THAT CREATES THE SINGLE PLAYER SCREEN

			
			/** initialize the correct type of player 1 for human and 2 for comp*/
			myGame1.chooseWhichPlayer(1);

			//loop game until end game scenario hits for one player
			while(!myGame1.gameFinished())
			{
				
				//timer to pace the current pieces automatic drop
				sleep();
				myGame1.moveAlivePiece();
				
				/** if player presses the escape button use recursive method to restart the game*/
				if(myGui.returnToHome())
				{
					//returns to the main menu
					mainMode();
				}
			}
		}
		//loop game until end game scenario hits for two player
		else if (numOfPlayers > 1)
		{
			/** initializes a two player game*/
			init(numOfPlayers);
			
						//HERE YOU CAN CALL A METHOD THAT CREATES THE SINGLE PLAYER SCREEN

			/** initialize the correct type of players 1 for human and 2 for computer*/
			myGame1.chooseWhichPlayer(1);
			myGame2.chooseWhichPlayer(2);
			
			
			while(!myGame1.gameFinished())
			{
				//timer to pace the current pieces automatic drop
				sleep();
				//first move player
				myGame1.moveAlivePiece();

				//then update the comp and get input for its move
				myGame2.moveAlivePiece();
				myGame2.onePlayerGame(myGame2.player.getMove());
				
				/** if player presses the escape button use recursive method to restart the game*/
				if(myGui.returnToHome())
				{
					mainMode();
				}
			}
		
		}
		
		/** CHANGES THE CONTROLS AND SCREEN AND RETURNS TO THE 
		SAME METHOD TO RETURN TO THE MAIN MENU*/
		System.out.println("End screen press enter to go to home screen");
		myGui.changeScreen('E');
		mainMode();
		
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
