import java.util.ArrayList;
import java.util.*; 


//Assignment requirements: provide trace of this code.
public class HumanPlayer extends Player
{
	
	private char playerInput;
	private int score;
	
	public HumanPlayer()
	{
		super();
	}
	
	//takes player input and sets the playerinput var and returns input char
	public char playerMove()
	{
		/*
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Choose your move");
		char myChar = keyboard.next().charAt(0);
		this.playerInput = myChar;
		*/
		
		//System.out.println("entered player move"
		char holder = this.playerInput;
		this.playerInput = 'y';
		
		return holder;
	}
	
	public void setMove(char c)
	{
		
		//System.out.println("entered set move from keypress");
		this.playerInput = c;
		
	}
	//updates the new points to the players total score
	public void upDateScore(int newPoints)
	{
		
		super.upDateScore(newPoints);
		
	}
	
	
	//retrieves the players score
	public int getScore()
	{
		
		return super.getScore();
	}
	
}
