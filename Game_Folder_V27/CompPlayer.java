import java.util.ArrayList;
import java.util.*; 


//Assignment requirements: provide trace of this code.
public class CompPlayer extends Player
{
	
	private char playerInput;
	private int score;
	private char moves[] = {'a', 's', 'd', 'w'};
	
	
	public CompPlayer()
	{
		super();
	}
	
	//takes player input and sets the playerinput var and returns input char
	public char playerMove()
	{
		Random rand = new Random();
        int randInt = Math.abs(rand.nextInt()) % 4;
		
		this.playerInput = moves[randInt];
		
		return moves[randInt];
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
