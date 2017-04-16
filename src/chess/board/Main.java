package chess.board;

import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

import chess.handlers.MouseHandler;

public class Main extends Applet implements Runnable{
	
	Board board;

	private static JFrame display;
	private static Main main;
	private Dimension size = new Dimension (475, 475);
	private boolean runGame;
	private Image displayImg;
	
	//Variables need to create FPS counter (Set game at 60 FPS)
	private final int TARGET_FPS = 100; 
	private final long optimalTime = 1000000000/  TARGET_FPS; 
	private long lastFPS = 0;
	private int FPS = 0;
	private int renderFPS = 0;

	
	public static void main (String [] args){
		//Allowing game to start once JFrame is created
		main = new Main(); 

		//Creating JFrame
		display = new JFrame();
		display.add(main);
		display.pack();
		display.setTitle("Chess");
		display.setResizable(false);
		display.setLocationRelativeTo(null);
		display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display.setVisible(true);
		main.start();
	}
	
	public Main(){
		setPreferredSize(size); 
		addMouseListener (new MouseHandler(this)); 
		addMouseMotionListener (new MouseHandler(this)); 
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void start(){
		board = new Board(8, 8, this);
		new Thread (this).start();
		requestFocus();
		runGame = true;
	}
	
	public void stop(){
		runGame = false;
	}
	
	public void update(double delta){
		display.pack();
	}
	
	public void run() {

		displayImg = createVolatileImage(size.width, size.height);      

		long lastLoopTime = System.nanoTime(); 

		while (runGame){
			//FPS was inspired by http://stackoverflow.com/questions/34010255/fps-counter-in-java
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			
			double delta = updateLength / (double) optimalTime;
			lastFPS += updateLength;
			FPS++;
			
			if(lastFPS >= 1000000000){
				renderFPS = FPS;
				FPS = 0;
				lastFPS = 0;
			}
			update(delta);
			render();
			
			try{
				Thread.sleep((lastLoopTime - System.nanoTime() + optimalTime) / 1000000);
			}catch(Exception e){
				
			}

		}
	}
	
	public void render(){
		Graphics g;
		g = displayImg.getGraphics();
		//Code goes here
		board.paintComponent(g);
		
		g = this.getGraphics();

		g.drawImage(displayImg, 0, 0, size.width, size.height, 0, 0, size.width, size.height, null);

		g.dispose();
	}
}
