package chess.board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

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

	public Board(int width, int height, Main main){
		this.width = width;
		this.height = height;
		this.main = main;
		tiles = new Tile[width][height];
		createTiles();
		initPlayers(main);
	}
	
	public void initPlayers(Main main){
		Dimension tmp = new Dimension(width, height);
		pW = new Player(Type.WHITE, tiles, main,tmp);
		pB = new Player(Type.BLACK, tiles, main,tmp);
	}
	
	/*public void addPieces(){
		//Pawns
		for(int i = 0; i < 8; i++){
			pices.add(new Pawn(Type.WHITE, tiles[1][i], main));
			pices.add(new Pawn(Type.BLACK, tiles[height - 2][i], main));
		}
		//Rooks
		pices.add(new Rook(Type.WHITE, tiles[0][0], main));
		pices.add(new Rook(Type.WHITE, tiles[0][width - 1], main));
		pices.add(new Rook(Type.BLACK, tiles[height - 1][0], main));
		pices.add(new Rook(Type.BLACK, tiles[height - 1][width - 1], main));
		//Knights
		pices.add(new Knight(Type.WHITE, tiles[0][1], main));
		pices.add(new Knight(Type.WHITE, tiles[0][width - 2], main));
		pices.add(new Knight(Type.BLACK, tiles[height - 1][1], main));
		pices.add(new Knight(Type.BLACK, tiles[height - 1][width - 2], main));
		//Bishops
		pices.add(new Bishop(Type.WHITE, tiles[0][2], main));		
		pices.add(new Bishop(Type.WHITE, tiles[0][width - 3], main));
		pices.add(new Bishop(Type.BLACK, tiles[height - 1][2], main));		
		pices.add(new Bishop(Type.BLACK, tiles[height -1 ][width - 3], main));
		//Queens
		pices.add(new Queen(Type.WHITE, tiles[0][width - 4], main));
		pices.add(new Queen(Type.BLACK, tiles[height - 1][width - 4], main));
		//Kings
		pices.add(new King(Type.WHITE, tiles[0][width - 5], main));
		pices.add(new King(Type.BLACK, tiles[height - 1][width - 5], main));
	}*/
	
	public void removePiece(Tile tile, Type color){
		ArrayList<Piece> pices;
		if(color == Type.WHITE)
			pices = pW.getPieces();
		else
			pices = pB.getPieces();
		for(int i = 0; i < pices.size(); i++){
			if(pices.get(i).getTile() == tile && pices.get(i).getColor() == color){
				pices.remove(i);
			}
		}
		
	}
	
	public void createTiles(){
		for(int i = 0; i < tiles.length; i++){
			for(int j = 0; j < tiles[i].length; j++){
				Color c;
				if((i + 1) % 2 != 0){
					c = Color.BLUE;
					if((j + 1) % 2 == 0){
						c = Color.ORANGE;
					}
				}else{
					c = Color.ORANGE;
					if((j + 1) % 2 == 0){
						c = Color.BLUE;
					}
				}
				tiles[i][j] = new Tile(j,i,c,null);
			}
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
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
}

