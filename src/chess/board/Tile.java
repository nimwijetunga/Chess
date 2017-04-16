package chess.board;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

import chess.piece.Piece;
import chess.piece.Type;

public class Tile extends Rectangle{
	
	private Color color, origColor;

	public static final int SIZE = 60;
	private Piece piece;
	
	public Tile(int x, int y, Color color, Piece piece){
		this.x = x;
		this.y = y;
		this.color = color;
		this.origColor = color;
		this.piece = piece;
		this.setBounds(x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE);
	}
	
	public boolean containsPiece(){
		if(piece != null)
			return true;
		return false;
	}
	
	public boolean containsAlly(Piece p2){
		if(piece == null)
			return false;		
		if(piece.getColor() == p2.getColor()){
			return true;
		}
		return false;
	}
	
	public boolean containsEnemy(Piece p2){
		if(piece == null)
			return false;
		if(piece.getColor() != p2.getColor()){
			return true;
		}
		return false;
	}
	
	

	public Color getColor() {
		return color;
	}
	
	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

	public Color getOrigColor() {
		return origColor;
	}

	public void setOrigColor(Color origColor) {
		this.origColor = origColor;
	}

}
