import java.util.*; 
import java.util.List; 
import java.util.Arrays;
import java.util.ArrayList;

import java.util.Timer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;



public class Board
{
	
	private final int ROWS = 20;
	private final int COL = 10;
	private char Board[][];
	private boolean nextToLeft = false;
	private boolean nextToRight = false;
	private boolean testDrop = false;
	private int deletedLines = 0;

	
	
	public Board()
	{
		intitializeBoard();
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
	
	
	
	//SETS A EMPTY BOARD FOR THE START OF THE GAME
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
	
	
	//SETS ALL THE PIECES ONTO THE BOARD USING THE PIECES ARRAY AND THEIR COORDINATES
	public void setPieceOnBoard(Pieces curPiece, ArrayList<Pieces> allPieces)
	{
		
		ArrayList<Integer> shapeLoca = new ArrayList<Integer>();
		shapeLoca = curPiece.getCoord();
		
		//loop through each piece 
		for (int i = 0; i < allPieces.size()-1; i++)
		{
			ArrayList<Integer> shapeLoca2 = new ArrayList<Integer>();
			shapeLoca2 = allPieces.get(i).getCoord();
			
			//for each piece loop through its coordinates and set them to the board
			for (int j = 0; j < allPieces.get(i).getCoord().size()-1; j += 2)
			{
				//check if the piece is the current piece
				if (allPieces.get(i).getStatus() == false)
				{
					this.Board[shapeLoca2.get(j+1)][shapeLoca2.get(j)] = allPieces.get(i).getShapeID();
				}
			}
		}
		
		//set current piece onto the board
		for (int i = 0; i < shapeLoca.size()-1; i+=2)
		{
			this.Board[shapeLoca.get(i+1)][shapeLoca.get(i)] = curPiece.getShapeID();
		}
	}
	
	public void resetSideColWithBlcs()
	{
		//booleans used to detect if the current piece is next to a dead piece
		this.nextToLeft = false;
		this.nextToRight = false;
	}
	
	
	//FOR LATER USAGE
	public boolean gameOver(Pieces curPiece)
	{		
		
		ArrayList<Integer> shapeLoca = new ArrayList<Integer>();
		shapeLoca = curPiece.getCoord();
		
		
		//LOOP THROUGH EACH PIECE IN THE ALIVE PIECE AND CHECK IF ANY COORDINATES ARE EQUAL TO 0
		for (int i = 0; i < shapeLoca.size()-1; i+=2)
		{
			if (shapeLoca.get(i+1) == 0)
			{
				return true;
			}
		}
		
		
		return false;
	}


	//resets board and updates pieces locations on the board
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
	
	//CHECKS IF PLAYER CAN MAKE A MOVE AND IF ITS HIT A BORDER
	public void updatePieces(char m, Pieces curPiece)
	{
				
		//if not going to go out of bounds then move
		if (checkSideBoarders(m, curPiece))
		{
			curPiece.movePc(m);
		}
				
	}


	//WHAT MOVE THE PLAYER WANTS TO MAKE AND DETERMINES IF IT IS A VIABLE MOVE
	public boolean checkSideBoarders(char move, Pieces curPiece)
	{
		
		if (move == 'a')
		{
			for (int i = 0; i < curPiece.getCoord().size()-1; i+=2)
			{	
				
				if (curPiece.getCoord().get(i) <= 0 || this.nextToLeft == true) 
				{
					
					//doesn't allow the curpiece to move beyond the boarders
					return false;
				}
				
			}
		}
		else if (move == 'd')
		{
			for (int i = 0; i < curPiece.getCoord().size()-1; i+= 2)
			{
				if (curPiece.getCoord().get(i) >= COL-1 || this.nextToRight == true)
				{
					//doesn't allow the curpiece to move beyond the boarders
					return false;
				}
				
			}
		}
		
		
		return true;
			
	}
	
	
	//CHECKS IF A PIECE HAS COLLIDED WITH THE BORDER OR ANOTHER PIECE
	public boolean checkCollision(Pieces curPiece, ArrayList<Pieces> allPieces)
	{
		
		boolean hasCollided = false;
		if (checkBorder(curPiece, allPieces) == true || checkDeadPieces(curPiece, allPieces) == true)
		{
			hasCollided = true;
		}
		
		
		return hasCollided;
		
	}
		
		
	//CHECKS IF THE CURRENT PIECE HAS COLLIDED WITH ANY OF THE DEAD PIECES FROM EACH ANGLE
	public boolean checkDeadPieces(Pieces curPiece, ArrayList<Pieces> allPieces)
	{
		boolean isCollided = false;
		
		
		if (curPiece.getCoord()!= null)
		{
						
			//SECOND CHECK: CHECKS TO SEE IF THE PIECE HAS COLLIDED WITH ANY OTHER PIECES
			for (int i = 0; i < allPieces.size()-1; i++)
			{
				ArrayList<Integer> shapeLoca2 = new ArrayList<Integer>();
				shapeLoca2 = curPiece.getCoord();
					
				//LOOP THROUGH EACH PIECE OF THE ALIVE COORDINATE
				for (int j = 0; j < shapeLoca2.size(); j+=2)
				{
					//get the piece from the allpices list 
					//then get the coordinates of that piece and test them against the current piece 
					Pieces deadPiece = allPieces.get(i);
					ArrayList<Integer> deadLoca = new ArrayList<Integer>();
					deadLoca = deadPiece.getCoord();
						
						
						
					//Simplyfing code to access the 2d array Board
					int yValue = shapeLoca2.get(j+1)+1;
					int xValue = shapeLoca2.get(j);
			

						//COMPARE THE COORDINATES OF THE CURRENT PIECE WITH ALL THE OTHER PIECES COORDINATES OF THE LIVE SHAPE AND IF THE BLOCK BELOW IS NOT A BLOCK IN THE SHAPE THEN MOVE DOWN

					//THIS LOOP CHECKS EACH COORDINATE OF THE DEAD PIECE WITH ONE COORDINATE OF THE ALIVE PIECE
					for (int k = 0; k < deadLoca.size(); k+=2)
					{

						//check the each dead piece coordinates with the alive piece coordinates
						if(deadLoca.get(k+1) == (shapeLoca2.get(j+1)+1) && deadLoca.get(k) == shapeLoca2.get(j))
						{
							//IF COLLIDED WITH ANOTHER PIECE FROM BELOW CHECK IF IT CREATED A FULL LINE TO DELETE
							deleteLine(allPieces);
							isCollided = true;
							
							//ensures that the same line to delete is not counted twice
							return isCollided;
						
						}
					
	
						//check if you can move side to side
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
		
	
	//CHECKS IF THE CURRENT PIECE CONTROLLED BY THE USER HAS TOUCHED THE BOTTOM OF THE ARRAY
	public boolean checkBorder(Pieces curPiece, ArrayList<Pieces> allPieces)
	{
		//check collision and if collsion set isAlive boolean and curPiece
		boolean isCollided = false;
		ArrayList<Integer> shapeLoca = new ArrayList<Integer>();
		
		
		//check to make sure curPiece has been initialized
		if (curPiece.getCoord()!= null)
		{
			shapeLoca = curPiece.getCoord();
		
			//FIRST CHECK TO SEE IF THE BLOCK HAS COLLIDED WITH THE BORDER
			//loop through each block in the current piece
			for (int i = 0; i < curPiece.getCoord().size();/*getShapeSize();*/ i+=2)
			{
				//check if current piece has hit the bottom
				if (shapeLoca.get(i+1) >= ROWS-1)
				{
					
					//once pieced has hit the bottom check if a full line has been filled
					deleteLine(allPieces);
					isCollided = true;
					
					//break out of loop to make sure you dont count the same line twice
					return isCollided;
				}	
			
			}
			
		}
		
		return isCollided;

	}		
	
	
	//CHECKS TO SEE IF ANY OF THE LINES ARE FILLED
	public void deleteLine(ArrayList<Pieces> allPieces)
	{
			
		boolean delLine = false;
		
		//go through each line and col and check if all the locations are covered by a piece
		for (int i = 0; i < this.ROWS; i++)
		{
			
			//if bool is not set to false then the whole line is covered by pieces
			delLine = true;
			
			for (int j = 0; j < this.COL; j++)
			{
				
				if (this.Board[i][j] == '.')
				{
					//catches if there are any empty spots on a line
					delLine = false;
					break;
				}					
			}
			
			
			//if a line has been filled up then delete it
			if (delLine)
			{
				
				//counts number of deleted lines to calc player scores
				this.deletedLines++;
				clearLine(i, allPieces);
			}
		}
		
	}
	
	//TAKES THE YCOORDINATE FROM DELETE LINE AND REMOVES ANY PIECES BLOCKS THAT ARE LOCATED ON THAT LINE
	public void clearLine(int yCoord, ArrayList<Pieces> allPieces)
	{
		

		//CYCLE THROUGH ENTIRE LIST OF DEAD PIECES 
		for (int pc = 0; pc < allPieces.size(); pc++)
		{
			
			//get a piece and put it in holderPc
			//then create a Arraylist to hold the coordinate array from the piece
			Pieces holderPc = allPieces.get(pc);
			//ArrayList<Integer> holderLoca = new ArrayList<Integer>();
			//holderLoca = holderPc.getCoord();

			
			//CYCLE THROUGH THE ENTIRE COLUMN OR X COORDINATES
				//CYCLE THROUGH EACH COORDINATE OF THE DEAD PIECE AND CHECK IF IT HAS ANY PIECE ON THE LINE TO DELETE
				for (int deadPc = 0; deadPc < holderPc.getCoord().size(); deadPc += 2)
				{
					if (yCoord == holderPc.getCoord().get(deadPc+1))
					{
						//DELETE THE Y AND X COORDINATE LIKE DELETING THE BLOCK THAT IS ON THE LINE TO DELETE
						holderPc.removeBlock(deadPc, deadPc);
						
						//decrease by 2 because when two items have been removed the iterator will skip two
						deadPc -= 2;
						
					}	
				}								
		}
		
		dropPieces(yCoord, allPieces);					
	}
		
		
	//DETERMINES IF THE ANY OF THE PIECES COORDINATES ARE LESS THAN THE LINE THATS BEING DELETED
	//AND DROPS THEM DOWN
	public void dropPieces(int yC, ArrayList<Pieces> allPieces)
	{
		//class int is the number of lines that have been deleted, so the pieces must be moved by that many
		for (int pc = 0; pc < allPieces.size(); pc++)
		{						
			
			//get a piece and put it in holderPc
			//then create a Arraylist to hold the coordinate array from the piece
			Pieces holderPc = allPieces.get(pc);
			//ArrayList<Integer> holderLoca = new ArrayList<Integer>();
			//holderLoca = holderPc.getCoord();

			
			//CYCLE THROUGH THE ENTIRE COLUMN OR X COORDINATES
			//CYCLE THROUGH EACH COORDINATE OF THE DEAD PIECE AND CHECK IF IT HAS ANY PIECE ON THE LINE TO DELETE
			for (int deadPc = 0; deadPc < holderPc.getCoord().size()-1; deadPc += 2)
			{
				if (yC > holderPc.getCoord().get(deadPc+1))
				{
					//DELETE THE Y AND X COORDINATE LIKE DELETING THE BLOCK THAT IS ON THE LINE TO DELETE
					holderPc.moveOneDown(deadPc+1);
				}	
			}							
				
		}
	}
	
	//returns the number of lines to delete to calc the score
	public int getdeletedLinesNum()
	{
		return this.deletedLines;
	}
	
	//resets counter of deleted lines that uses the number of lines to cal players score
	public void resetDeletedLinesNum()
	{
		this.deletedLines = 0;
	}
}
	