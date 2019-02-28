/** This class is in charge of the creatin, manipulation and movement of the pieces that fall down the board*/

import java.util.Random;
import java.util.List;
import java.util.*; 
import java.util.List; 
import java.util.Arrays;
import java.util.Scanner;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;


public class Pieces
{
	
	boolean isAlive = false;
	private final String SHAPES[] = {"NoShape", "ZShape", "SShape", "LineShape", 
		"TShape", "SquareShape", "LShape", "MirroredLShape"};
	private String curShape = " ";
	private final int SHAPESIZE = 8;
	private ArrayList<Integer> coords = new ArrayList<Integer>();
	private char shapeId = ' ';


	public Pieces()
	{		
		intitializePieces();
	}
	
	/** SETS COORDINATES AND CHOOSE A RANDOM SHAPE*/
	public void intitializePieces()
	{
		/** choose shape determines which shape*/
		chooseShape();
		//** setCoord determines what spaces to fill based off the shape choosen*/
		setCoord();
	}
	
	
	public int getShapeSize()
	{
		return this.SHAPESIZE;
	}

	/** Randomly picks a number and chooses a shape based off of the number*/
	public void chooseShape()
	{
		Random rand = new Random();
        int randInt = Math.abs(rand.nextInt()) % 7 + 1;
        this.curShape = SHAPES[randInt];
	}
	
	
	public void setStatus(boolean notAct)
	{
		this.isAlive = notAct;
	}
	
	public boolean getStatus()
	{
		return this.isAlive;
	}
	
	
	public String getPiece()
	{
		return this.curShape;
	}
	
	public char getShapeID()
	{
		return this.shapeId;
	}
	
	
	/** SETS THE STARTING COORDINATES OF THE NEW PIECE DEPENDING ON WHAT IT IS*/
	public void setCoord()
	{		
		if (this.curShape == "NoShape") 
		{
			
		}
		else if (this.curShape == "SShape") 
		{
			this.coords.add(5);
			this.coords.add(0);
			this.coords.add(5);
			this.coords.add(1);
			this.coords.add(6);
			this.coords.add(1);
			this.coords.add(6);
			this.coords.add(2);

			this.shapeId = 'S';
			
		}
		else if (this.curShape == "ZShape") 
		{
			this.coords.add(6);
			this.coords.add(0);
			this.coords.add(6);
			this.coords.add(1);
			this.coords.add(5);
			this.coords.add(1);
			this.coords.add(5);
			this.coords.add(2);

			this.shapeId = 'Z';
		}
		else if (this.curShape == "LineShape") 
		{
			this.coords.add(5);
			this.coords.add(0);
			this.coords.add(5);
			this.coords.add(1);
			this.coords.add(5);
			this.coords.add(2);
			this.coords.add(5);
			this.coords.add(3);

			this.shapeId = '|';
		}
		else if (this.curShape == "TShape") 
		{
			this.coords.add(5);
			this.coords.add(0);
			this.coords.add(5);
			this.coords.add(1);
			this.coords.add(4);
			this.coords.add(1);
			this.coords.add(6);
			this.coords.add(1);

			this.shapeId = 'T';
		}
		else if (this.curShape == "SquareShape") 
		{
			this.coords.add(5);
			this.coords.add(0);
			this.coords.add(5);
			this.coords.add(1);
			this.coords.add(6);
			this.coords.add(0);
			this.coords.add(6);
			this.coords.add(1);

			this.shapeId = 'B';
		}
		else if (this.curShape == "LShape") 
		{
			this.coords.add(5);
			this.coords.add(0);
			this.coords.add(6);
			this.coords.add(0);
			this.coords.add(6);
			this.coords.add(1);
			this.coords.add(6);
			this.coords.add(2);

			this.shapeId = 'L';
		}
		else if (this.curShape == "MirroredLShape") 
		{
			this.coords.add(5);
			this.coords.add(0);
			this.coords.add(4);
			this.coords.add(0);
			this.coords.add(4);
			this.coords.add(1);
			this.coords.add(4);
			this.coords.add(2);

			this.shapeId = 'L';
		}
	}
	
	
	/** returns the coordinates of the shape*/
	public ArrayList<Integer> getCoord()
	{
		
		ArrayList<Integer> holder = new ArrayList<Integer>();

		if (this.coords != null)
		{
			for (int i = 0; i < this.coords.size(); i++)
			{		
				holder.add(this.coords.get(i));
			}
			return holder;
		}
		return holder;
		
	}
	
	
	/** MOVES ONE BLOCK ON THE PIECE DOWN */
	public void moveOneDown(int yCoord)
	{
		int holder = this.coords.get(yCoord);
		this.coords.set(yCoord, holder+1);
	}
	
	/** MOVES THE ENTIRE PIECE DOWN */
	public void updatePiece()
	{
		for (int i = 1; i < this.coords.size(); i+=2)
		{
			Integer holder = this.coords.get(i);
			this.coords.set(i, holder + 1);
		}
	}
	
	/** TAKES A DIRECTION AND CALLS THE RIGHT FUNCTION BASED OF THE CHAR*/
	public void movePc(char m)
	{
		if (m == 'w')
		{
			moveRotate();
		}
		else if(m == 's')
		{
			moveDown();
		}
		else if(m == 'a')
		{
			moveLeft();
		}
		else if(m == 'd')
		{
			moveRight();
		}
	}
	
	/** ROTATES THE PIECE*/
	public void moveRotate()
	{
		Integer middleX = this.coords.get(2);
        Integer middleY = this.coords.get(3);
		
		if(!(middleX.equals(0) || middleX.equals(1) || middleX.equals(8) || middleX.equals(9) || middleY.equals(0)|| middleY.equals(1)))
		{
			for (int i = 0; i <= this.coords.size()-1; i+=2)
			{
				
				Integer oldX = this.coords.get(i);
				Integer oldY = this.coords.get(i+1);
				
				Integer newX = -(oldY - middleY ) + middleX;
				Integer newY = (oldX - middleX ) + middleY;

				this.coords.set(i, newX);
				this.coords.set(i + 1, newY);
			}
		}
	}
	
	/** MOVES PIECE LEFT*/
	public void moveLeft()
	{
		for (int i = 0; i < this.coords.size()-1; i+=2)
		{
			Integer holder = this.coords.get(i);
			this.coords.set(i, holder - 1);
		}
	}
	
	/** MOVES PIECE RIGHT*/
	public void moveRight()
	{
		for (int i = 0; i < this.coords.size()-1; i+=2)
		{
			Integer holder = this.coords.get(i);
			this.coords.set(i, holder + 1);
		}
	}
	
	/** MOVES PIECE DOWN*/
	public void moveDown()
	{
		for (int i = 1; i <= this.coords.size()-1; i+=2)
		{
			Integer holder = this.coords.get(i);
			this.coords.set(i, holder + 1);
		}
	}
	
	/** REMOVES BLOCK IN PIECE*/
	public void removeBlock(int xCoord, int yCoord)
	{
		this.coords.remove(xCoord);
		this.coords.remove(yCoord);
	}
}
