package chess.board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import chess.handlers.MouseHandler;
import chess.handlers.Player;
import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.piece.Type;


public class Board  extends JPanel{
	
	private Tile[][] tiles;

	private int width,height;

	private Main main;
	private Player pW, pB;
	
	private boolean endGame;

	public Board(int width, int height, Main main){
		setEndGame(false);
		this.width = width;
		this.height = height;
		this.main = main;
		tiles = new Tile[width][height];
		createTiles();
		initPlayers();
	}
	
	public void initPlayers(){
		Dimension tmp = new Dimension(width, height);
		pW = new Player(Type.WHITE, tiles, main, tmp);
		pB = new Player(Type.BLACK, tiles, main, tmp);
	}
	
	public void removePiece(Tile tile, Type color){
		ArrayList<Piece> pices;
		if(color == Type.WHITE)
			pices = pW.getPieces();
		else
			pices = pB.getPieces();
		for(int i = 0; i < pices.size(); i++){
			if(pices.get(i).getTile() == tile && pices.get(i).getColor() == color){
				if(pices.get(i).getType() == Type.KING)
					setEndGame(true);
				pices.remove(i);
			}
		}
		
	}
	
	public void createTiles(){
		for(int i = 0; i < tiles.length; i++){
			for(int j = 0; j < tiles[i].length; j++){
				Color c;
				if((i + 1) % 2 != 0){
					c = new Color(222,184,135);
					if((j + 1) % 2 == 0)
						c = new Color(139,69,19);
				}else{
					c = new Color(139,69,19);
					if((j + 1) % 2 == 0)
						c = new Color(222,184,135);
				}
				tiles[i][j] = new Tile(j,i,c,null);
			}
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(endGame)
			createTiles();
		
		for(Tile [] i : tiles){
			for(Tile t : i){
				g.setColor(t.getColor());
				g.fillRect((int) (t.getX()), (int)(t.getY()), Tile.SIZE, Tile.SIZE);
			}
		}
		ArrayList<Piece> pices = pW.getPieces();
		for(int i = 0; i < pices.size(); i++)
			g.drawImage(pices.get(i).getImage(), (int) (pices.get(i).getTile().getX()), (int) (pices.get(i).getTile().getY()), null);
		pices = pB.getPieces(); 
		for(int i = 0; i < pices.size(); i++)
			g.drawImage(pices.get(i).getImage(), (int) (pices.get(i).getTile().getX()), (int) (pices.get(i).getTile().getY()), null);
		if(MouseHandler.bT)
			g.setColor(Color.BLACK);
		else
			g.setColor(Color.WHITE);
		//Turn Color
		g.fillRect(480, 0, Tile.SIZE, Tile.SIZE);
		g.setColor(Color.RED);
		for(int i = 0; i < 5; i++)
			g.drawRect(480, 0, Tile.SIZE - i, Tile.SIZE - i);
		if(endGame)
			main.setRunGame(false);
	}

	public Tile[][] getTiles() {
		return tiles;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	

	public Player getpW() {
		return pW;
	}

	public Player getpB() {
		return pB;
	}

	public boolean isEndGame() {
		return endGame;
	}

	public void setEndGame(boolean endGame) {
		this.endGame = endGame;
	}
}

