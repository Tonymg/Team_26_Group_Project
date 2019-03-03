import java.util.*; 
import java.util.List; 
import java.util.Arrays;
import java.util.ArrayList;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class RunGame extends JFrame implements KeyListener 
{

	 JLabel label;

	private Board myBoard;
	private ArrayList<Pieces> allPieces = new ArrayList<Pieces>();
	private Pieces curPiece = new Pieces();
	private boolean isGameOver = false;
	HumanPlayer hPlayer = new HumanPlayer();
	CompPlayer cPlayer = new CompPlayer();
	private int numbOfPlayers = 0;
	
	public RunGame(String s)
	{
		super(s);
        JPanel p = new JPanel();
        label = new JLabel("Tetris Game");
        p.add(label);
        add(p);
        addKeyListener(this);
        setSize(200, 100);
        setVisible(true);
		
		
		myBoard = new Board();
		intitializePiece();
		//myBoard.printScreen();
		//chooseAmountOfPlayers();

	}
	
	//CREATES A NEW USER PIECE AND SET IT ON THE BOARD
	public void intitializePiece()
	{
		if (!this.curPiece.getStatus())
		{
			this.curPiece = new Pieces();
			this.allPieces.add(this.curPiece);
			this.curPiece.setStatus(true);
		}
		
		myBoard.setPieceOnBoard(curPiece, allPieces);
		
	}
	
	//DETERMINES IF THE PLAYER IS PLAYING ALONE OR AGAINST THE COMP
	public int chooseAmountOfPlayers()
	{
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Press 1 for 1 player and 2 to play against the Comp");
		int myInt = keyboard.nextInt();
		
		
		//Deterimines if the player wants to play by themselves or against an ai
		if (myInt == 1)
		{
			this.numbOfPlayers = 1;
		}
		else if(myInt == 2)
		{
			this.numbOfPlayers = 2;
		}	

		return myInt;
				
	}
	
	//RUNS ONLY IF PLAYER IS PLAYING ALONE
	public void onePlayerGame(char move)
	{
		
		myBoard.clearBoard();
		myBoard.updatePieces(move, this.curPiece);
		myBoard.setPieceOnBoard(this.curPiece, this.allPieces);
			
			
		//resets the blocks to be able to collide again
		myBoard.resetSideColWithBlcs();
			
			
		///check for collision
		if (myBoard.checkCollision(curPiece, allPieces))
		{
				
			//Takes the num of deleted lines and updates the player score using it and 
			//resets the deleted lines number
			this.hPlayer.upDateScore(this.myBoard.getdeletedLinesNum()*100);
			this.myBoard.resetDeletedLinesNum();
			
				
			//if piece has collided deactivate current piece and create new one
			this.curPiece.setStatus(false);
			this.intitializePiece();				
		}
		
			
		myBoard.printScreen();
			
		System.out.println("Player Score: " + this.hPlayer.getScore());
		
		
	}
	
	//DETERMINES IF THE GAME HAS ENDED OR NOT
	public boolean gameFinished()
	{
		
		boolean isOver = false;
		
		///check for collision
		if (myBoard.checkCollision(this.curPiece, this.allPieces))
		{
			//checks if the alive piece is at the top of the screen
			isOver = myBoard.gameOver(this.curPiece);
		}
		
		return isOver;
	}
	
	
	//CURRETLY ONLY RUNS THE AI 
	public void twoPlayerGame()
	{
		
			//creates a computer player
		char move = this.cPlayer.playerMove();
			
		myBoard.clearBoard();
		myBoard.updatePieces(move, this.curPiece);
		myBoard.setPieceOnBoard(this.curPiece, this.allPieces);
			
			
		//resets the blocks to be able to collide again
		myBoard.resetSideColWithBlcs();
			
			
		///check for collision
		if (myBoard.checkCollision(curPiece, allPieces))
		{
				
			//Takes the num of deleted lines and updates the player score using it and 
			//resets the deleted lines number
			this.hPlayer.upDateScore(this.myBoard.getdeletedLinesNum() * 100);
			this.myBoard.resetDeletedLinesNum();
				
				
			//if piece has collided deactivate current piece and create new one
			this.curPiece.setStatus(false);
			this.intitializePiece();				
		}
			
			
		myBoard.printScreen();
			
		System.out.println("Comp Score: " + this.cPlayer.getScore());
			
		
	}
	
	//moves alive piece down one 
	public void moveAlivePiece()
	{
		//determines if the game is one player or two
		if (this.numbOfPlayers == 1)
		{
			char move = 's';
	
			onePlayerGame(move);
		}
		else
		{
			twoPlayerGame();
		}
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

		char move = ' ';
		
		
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
            System.out.println("Right key pressed");
			move = 'd';
			hPlayer.setMove(move);
			onePlayerGame(move);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) 
		{
            System.out.println("Left key pressed");
			move = 'a';
			hPlayer.setMove(move);
			onePlayerGame(move);

        }
		if (e.getKeyCode() == KeyEvent.VK_DOWN) 
		{
            System.out.println("Down key pressed");
			move = 's';
			hPlayer.setMove(move);
			onePlayerGame(move);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) 
		{
            System.out.println("Up key pressed");
			move = 'w';
			hPlayer.setMove(move);
			onePlayerGame(move);

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
	