/**THIS CLASS CONTAINS ALL OF THE METHODS NECESSARY FOR THE EXECUTION OF THE GAME AS WELL AS RESPONSE TO USER
 * INPUT
 */

import java.util.*; 
import java.util.List; 
import java.util.Arrays;
import java.util.ArrayList;

import java.util.Timer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;



public class RunGame
{
	
	private final int ROWS = 20;
	private final int COL = 10;
	private char Board[][];
	private ArrayList<Pieces> allPieces = new ArrayList<Pieces>();
	private Pieces curPiece = new Pieces();
	private boolean isGameOver = false;
	private boolean nextToLeft = false;
	private boolean nextToRight = false;
	private boolean testDrop = false;

	
	
	public RunGame()
	{
		intitializeBoard();
		intitializePiece();
		printScreen();
		runningGame();
	}
	
	public void printScreen()
	{
		
		for (char [] x: this.Board)
		{
			for (char y: x)
			{
				System.out.print(y);
			}
			System.out.println();
		}
				
	}
	
	/** SETS A EMPTY BOARD FOR THE START OF THE GAME*/
	public void intitializeBoard()
	{
		this.Board = new char[ROWS][COL];
		
		for (int i = 0; i < ROWS; i++)
		{
			for (int j = 0; j < COL; j++)
			{
				this.Board[i][j] = '.';							
			}
		}


	}
	
	/** CREATES A NEW USER PIECE AND SET IT ON THE BOARD*/
	public void intitializePiece()
	{
		if (!this.curPiece.getStatus())
		{
			this.curPiece = new Pieces();
			this.allPieces.add(this.curPiece);
			this.curPiece.setStatus(true);
		}
		
		setPieceOnBoard();
		
		
	}
	
	/** SETS ALL THE PIECES ONTO THE BOARD USING THE PIECES ARRAY AND THEIR COORDINATES*/
	public void setPieceOnBoard()
	{
		
		ArrayList<Integer> shapeLoca = new ArrayList<Integer>();
		shapeLoca = this.curPiece.getCoord();
		
		/** loop through each piece */
		for (int i = 0; i < this.allPieces.size()-1; i++)
		{
			ArrayList<Integer> shapeLoca2 = new ArrayList<Integer>();
			shapeLoca2 = this.allPieces.get(i).getCoord();
			
			/** for each piece loop through its coordinates and set them to the board*/
			for (int j = 0; j < this.allPieces.get(i).getCoord().size()-1; j += 2)
			{
				/** check if the piece is the current piece*/
				if (this.allPieces.get(i).getStatus() == false)
				{
					this.Board[shapeLoca2.get(j+1)][shapeLoca2.get(j)] = this.allPieces.get(i).getShapeID();
				}
			}
		}
		
		/** set current piece onto the board*/
		for (int i = 0; i < shapeLoca.size()-1; i+=2)
		{
			this.Board[shapeLoca.get(i+1)][shapeLoca.get(i)] = this.curPiece.getShapeID();
		}
	}
	
	/** MAIN FUNCTION THAT CONTAINS GAME LOOP, CALLS ALL THE FUCNTIONS NECESSARY FOR THE GAME TO RUN 
	AND LOOPS UNTIL THE GAME HAS FINISHED*/
	public void runningGame()
	{
		
		
		while(!this.isGameOver)
		{
			
			/** FOR SHITTY VERSION */
			Scanner keyboard = new Scanner(System.in);
			System.out.println("Choose your move");
			char myChar = keyboard.next().charAt(0);
			

			/** sleep();*/

			clearBoard();
			updatePieces(myChar);
			setPieceOnBoard();
			
			
			/** booleans used to detect if the current piece is next to a dead piece*/
			this.nextToLeft = false;
			this.nextToRight = false;

			
			
			/** SLOPPY WITH TWO IF STATEMENTS NEED REFINEMENT*/
			/** check for collision*/
			if (checkCollision())
			{
				
				/** if piece has collided deactivate current piece and create new one*/
				this.curPiece.setStatus(false);
				intitializePiece();				
			}
			
			printScreen();
			
		
		}
		
		
		
	}
	
	/**CHECKS IF ANY PIECES ARE TOUCHING THE TOP OF THE BOARD.  IF SO, THE GAME ENDS */
	public void gameOver()
	{		
		
		ArrayList<Integer> shapeLoca = new ArrayList<Integer>();
		shapeLoca = this.curPiece.getCoord();
		
		
		/** LOOP THROUGH EACH PIECE IN THE ALIVE PIECE AND CHECK IF ANY COORDINATES ARE EQUAL TO 0*/
		for (int i = 0; i < shapeLoca.size()-1; i+=2)
		{
			if (shapeLoca.get(i+1) == 0)
			{
				this.isGameOver = true;
			}
		}
	}
	
	/** resets board and updates pieces locations on the board*/
	public void clearBoard()
	{
		for (int i = 0; i < 20; i++)
		{
			System.out.println();
		}
 
		for (int i = 0; i < ROWS; i++)
		{
			for (int j = 0; j < COL; j++)
			{
				this.Board[i][j] = '.';			
			}
		}
	}
	
	/** CHECKS IF PLAYER CAN MAKE A MOVE AND IF ITS HIT A BORDER*/
	public void updatePieces(char m)
	{
		/** if not going to go out of bounds then move*/
		if (checkSideBoarders(m))
		{
			this.curPiece.movePc(m);
		}
				
	}


	//FOR LATER USAGE
	public void sleep()
	{
		try 
		{
			//sending the actual Thread of execution to sleep X milliseconds
			Thread.sleep(500);
		} catch(InterruptedException ie) {}
	
	}
	
	/** CHECKS THAT A SIDEWAYS MOVE WONT SEND THE PIECE OFF THE BOARD*/
	public boolean checkSideBoarders(char move)
	{
		
		if (move == 'a')
		{
			for (int i = 0; i < this.curPiece.getCoord().size()-1; i+=2)
			{	
				
				if (this.curPiece.getCoord().get(i) <= 0 || this.nextToLeft == true) 
				{
					
					/** doesn't allow the curpiece to move beyond the boarders*/
					return false;
				}
				
			}
		}
		else if (move == 'd')
		{
			for (int i = 0; i < this.curPiece.getCoord().size()-1; i+= 2)
			{
				if (this.curPiece.getCoord().get(i) >= COL-1 || this.nextToRight == true)
				{
					/** doesn't allow the curpiece to move beyond the boarders*/
					return false;
				}
				
			}
		}
		
		
		return true;
			
	}
	
	
	/** CHECKS IF A PIECE HAS COLLIDED WITH THE BORDER OR ANOTHER PIECE*/
	public boolean checkCollision()
	{
		
		boolean hasCollided = false;
		if (checkBorder() == true || checkDeadPieces() == true)
		{
			hasCollided = true;
		}
		
		return hasCollided;
		
	}
		
		
	/** CHECKS IF THE CURRENT PIECE HAS COLLIDED WITH ANY OF THE DEAD PIECES FROM EACH ANGLE*/
	public boolean checkDeadPieces()
	{
		boolean isCollided = false;
		
		
		if (this.curPiece.getCoord()!= null)
		{
						
			/** SECOND CHECK: CHECKS TO SEE IF THE PIECE HAS COLLIDED WITH ANY OTHER PIECES*/
			for (int i = 0; i < this.allPieces.size()-1; i++)
			{
				ArrayList<Integer> shapeLoca2 = new ArrayList<Integer>();
				shapeLoca2 = this.curPiece.getCoord();
					
				/** LOOP THROUGH EACH PIECE OF THE ALIVE COORDINATE*/
				for (int j = 0; j < shapeLoca2.size(); j+=2)
				{
					/** get the piece from the allpices list 
					then get the coordinates of that piece and test them against the current piece */
					Pieces deadPiece = this.allPieces.get(i);
					ArrayList<Integer> deadLoca = new ArrayList<Integer>();
					deadLoca = deadPiece.getCoord();
						
						
						
					/** Simplyfing code to access the 2d array Board*/
					int yValue = shapeLoca2.get(j+1)+1;
					int xValue = shapeLoca2.get(j);

					/** THIS LOOP CHECKS EACH COORDINATE OF THE DEAD PIECE WITH ONE COORDINATE OF THE ALIVE 
					 * PIECE*/
					for (int k = 0; k < deadLoca.size(); k+=2)
					{

						/** check the each dead piece coordinates with the alive piece coordinates*/
						if(deadLoca.get(k+1) == (shapeLoca2.get(j+1)+1) && deadLoca.get(k) == shapeLoca2.get(j))
						{
							
							deleteLine();

							isCollided = true;
							return isCollided;
						
						}
					
	
						/** check if you can move side to side*/
						if(deadLoca.get(k+1) == (shapeLoca2.get(j+1)) && deadLoca.get(k) == shapeLoca2.get(j)+1)
						{
							this.nextToRight = true;
						
					
						}
						else if(deadLoca.get(k+1) == (shapeLoca2.get(j+1)) && deadLoca.get(k) == shapeLoca2.get(j)-1)
						{
							this.nextToLeft = true;

						}
					}
			
				}		
		
				 
			}
		}
	
		
		return isCollided;
	}
		
	
	//** CHECKS IF THE CURRENT PIECE CONTROLLED BY THE USER HAS TOUCHED THE BOTTOM OF THE ARRAY*/
	public boolean checkBorder()
	{
		/** check collision and if collsion set isAlive boolean and curPiece*/
		boolean isCollided = false;
		ArrayList<Integer> shapeLoca = new ArrayList<Integer>();
		
		
		/** check to make sure curPiece has been initialized*/
		if (this.curPiece.getCoord()!= null)
		{
			shapeLoca = this.curPiece.getCoord();
		
			/** FIRST CHECK TO SEE IF THE BLOCK HAS COLLIDED WITH THE BORDER
			loop through each block in the current piece*/
			for (int i = 0; i < this.curPiece.getShapeSize(); i+=2)
			{
				/** check if current piece has hit the bottom*/
				if (shapeLoca.get(i+1) >= ROWS-1)
				{
					
					/** once pieced has hit the bottom check if a full line has been filled*/
					deleteLine();
					isCollided = true;
				}	
			
			}
			
		}
		
		return isCollided;

	}		
	
	
	/** CHECKS TO SEE IF ANY OF THE LINES ARE FILLED*/
	public void deleteLine()
	{
	
		boolean delLine = false;
		
		/////////
		//TODO: CHECK FOR ANY OTHER LINES OTHER THAN THE BOTTOM ROWS
		/////////
		
		/** go through each line and col and check if all the locations are covered by a piece*/
		for (int i = 0; i < this.ROWS; i++)
		{
			
			/** if bool is not set to false then the whole line is covered by pieces*/
			delLine = true;
			
			for (int j = 0; j < this.COL; j++)
			{
				if (this.Board[i][j] == '.')
				{
					delLine = false;
					break;
				}					
			}
			
			
			if (delLine)
			{
				clearLine(i);
			}
		}
		
	}
	
	/** TAKES THE YCOORDINATE FROM DELETE LINE AND REMOVES ANY PIECES BLOCKS THAT ARE LOCATED ON THAT LINE*/
	public void clearLine(int yCoord)
	{
		

		/** CYCLE THROUGH ENTIRE LIST OF DEAD PIECES */
		for (int pc = 0; pc < this.allPieces.size(); pc++)
		{
			
			/** get a piece and put it in holderPc
			then create a Arraylist to hold the coordinate array from the piece*/
			Pieces holderPc = this.allPieces.get(pc);
			
				/** CYCLE THROUGH EACH COORDINATE OF THE DEAD PIECE AND CHECK IF IT HAS ANY PIECE ON THE LINE TO 
				 * DELETE*/
				for (int deadPc = 0; deadPc < holderPc.getCoord().size()-1; deadPc += 2)
				{
					if (yCoord == holderPc.getCoord().get(deadPc+1))
					{
						System.out.println("tester");
						/** DELETE THE Y AND X COORDINATE LIKE DELETING THE BLOCK THAT IS ON THE LINE TO DELETE*/
						holderPc.removeBlock(deadPc, deadPc);
						
						/** decrease by 2 because when two items have been removed the iterator will skip two*/
						deadPc -= 2;
						
					}	
				}								
		}
		
		dropPieces(yCoord);					
	}
		
		
	/** DETERMINES IF THE ANY OF THE PIECES COORDINATES ARE LESS THAN THE LINE THATS BEING DELETED
	AND DROPS THEM DOWN*/
	public void dropPieces(int yC)
	{
		/** class int is the number of lines that have been deleted, so the pieces must be moved by that many*/
		for (int pc = 0; pc < this.allPieces.size(); pc++)
		{						
			
			/** get a piece and put it in holderPc
			then create a Arraylist to hold the coordinate array from the piece*/
			Pieces holderPc = this.allPieces.get(pc);
			
			/** CYCLE THROUGH THE ENTIRE COLUMN OR X COORDINATES
			CYCLE THROUGH EACH COORDINATE OF THE DEAD PIECE AND CHECK IF IT HAS ANY PIECE ON THE LINE TO DELETE*/
			for (int deadPc = 0; deadPc < holderPc.getCoord().size()-1; deadPc += 2)
			{
				if (yC > holderPc.getCoord().get(deadPc+1))
				{
					/** DELETE THE Y AND X COORDINATE LIKE DELETING THE BLOCK THAT IS ON THE LINE TO DELETE*/
					holderPc.moveOneDown(deadPc+1);
				}	
			}							
				
		}
	}
}
	