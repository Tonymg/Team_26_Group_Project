import java.util.ArrayList;
import java.util.*; 


public class Player
{
	
	private char playerInput;
	private int score;
	
	public Player()
	{
		
		this.score = 0;
		this.playerInput = '.';
		
		
	}
	
	public char playerMove()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Choose your move");
		char myChar = keyboard.next().charAt(0);
		
		return myChar;
	}
	
	public void upDateScore(int newPoints)
	{
		int holder = newPoints;
		this.score += holder;	
	}
	
	public int getScore()
	{
		return this.score;	
	}
	
}
