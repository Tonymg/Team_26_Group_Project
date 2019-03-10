import java.util.ArrayList;
import java.util.*; 
import java.util.List; 
import java.util.Arrays;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Gui extends JFrame implements KeyListener 
{
	
	JLabel label;
	private char playerMove = ' ';
	RunGame guiGame1;
	RunGame guiGame2;
	boolean escButton = false;
	boolean isHomeScreen = true;
	int numPlayers = 0;
	private char whichScreen = 'H';


	public Gui(String s)
	{
		super(s);
        JPanel p = new JPanel();
        label = new JLabel("Tetris Game");
        p.add(label);
        add(p);
        addKeyListener(this);
        setSize(200, 100);
        setVisible(true);		
		
	}
	
	/**Initialize the proper amount of games depending on number of players */
	public void initGames(RunGame game1)
	{
		guiGame1 = game1;
	}
	
	/**Initialize the proper amount of games depending on number of players */
	public void initGames(RunGame game1, RunGame game2)
	{
		guiGame1 = game1;
		guiGame2 = game2;
	}
	
	/**Set the number of players so the tetris class knows how many to initialize */
	public void setNumOfPlayers(int num)
	{
		
		this.numPlayers = num;
	}
	
	
	public int getNumOfPlayers()
	{
	
		//this resets the var numPlayers so that the player has to input the number
		int holder = this.numPlayers;
		this.numPlayers = 0;
		
		return holder;
	}
	
	/** returns a boolean if the player hits the escape key and also resets it each time*/
	public boolean returnToHome()
	{
		//this resets the var escButton so that the player has to input the number
		boolean holder = this.escButton;
		this.escButton = false;
		
		return holder;
	}
		
		
	public void changeScreen(char c)
	{
		this.whichScreen = c;
	}
	
	@Override
    public void keyTyped(KeyEvent e) 
	{
		/*

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
            System.out.println("Right key typed");
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) 
		{
            System.out.println("Left key typed");
        }
		*/

    }

    @Override
    public void keyPressed(KeyEvent e) 
	{

		
		/** if the screen is currently at the home screen allow these keys*/
		if (this.whichScreen == '1' || this.whichScreen == '2')
		{
			char move = ' ';
			
			
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
			{
				//System.out.println("Right key pressed");
				move = 'd';
				guiGame1.onePlayerGame(move);
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) 
			{
				//System.out.println("Left key pressed");
				move = 'a';
				guiGame1.onePlayerGame(move);

			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) 
			{
				//System.out.println("Down key pressed");
				move = 's';
				guiGame1.onePlayerGame(move);
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) 
			{
				//System.out.println("Up key pressed");
				move = 'w';
				guiGame1.onePlayerGame(move);

			}
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) 
			{
				System.out.println("Escape key pressed");
				
				this.escButton = true;
				this.whichScreen = 'H';

			}
		}
		/** if the screen is currently at the home screen allow these keys*/
		else if (this.whichScreen == 'H')
		{
			if (e.getKeyCode() == KeyEvent.VK_1) 
			{
				setNumOfPlayers(1);
				//this.numPlayers = 1;
				this.whichScreen = '1';

			}
			if (e.getKeyCode() == KeyEvent.VK_2) 
			{
				setNumOfPlayers(2);
				//this.numPlayers = 2;
				this.whichScreen = '2';
			}
			
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) 
			{
				System.out.println("GAME OVER");
				System.exit(0);
			}
		}
		/** if the screen is currently at the end screen allow these keys*/
		else if (whichScreen == 'E')
		{
			if (e.getKeyCode() == KeyEvent.VK_ENTER) 
			{				
				this.whichScreen = 'H';
			}
		}
		
		

    }

    @Override
    public void keyReleased(KeyEvent e) 
	{
		/*
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
            System.out.println("Right key Released");
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) 
		{
            System.out.println("Left key Released");
        }
		*/
    }
	
	
}
