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
	RunGame guiGame;


	//Along with a string it takes the current game instance to update and access
	public Gui(String s, RunGame game)
	{
		super(s);
        JPanel p = new JPanel();
        label = new JLabel("Tetris Game");
        p.add(label);
        add(p);
        addKeyListener(this);
        setSize(200, 100);
        setVisible(true);
		
		//passed by reference to change the original
		guiGame = game;
		
	}
	
	
	/*Possible unneeded code
	public char getMove()
	{
		char holder = this.playerMove;
		this.playerMove = ' ';
		
		return holder;
	}
	
	public void setMove(char c)
	{
		this.playerMove = c;
	}
	
	*/
	
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

		
		char move = ' ';
		
		
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
            //System.out.println("Right key pressed");
			move = 'd';
			guiGame.onePlayerGame(move);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) 
		{
            //System.out.println("Left key pressed");
			move = 'a';
			guiGame.onePlayerGame(move);

        }
		if (e.getKeyCode() == KeyEvent.VK_DOWN) 
		{
            //System.out.println("Down key pressed");
			move = 's';
			guiGame.onePlayerGame(move);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) 
		{
            //System.out.println("Up key pressed");
			move = 'w';
			guiGame.onePlayerGame(move);

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
