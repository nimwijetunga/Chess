package chess.handlers;

import java.awt.Dimension;
import java.util.ArrayList;

import chess.board.Main;
import chess.board.Tile;
import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.piece.Type;

public class Player {

	ArrayList<Piece> pieces;

	Type color;
	Tile [][] tiles;
	Main main;
	Dimension size;

	public Player(Type color, Tile [][] tiles, Main main, Dimension size){
		this.color = color;
		pieces = new ArrayList<Piece>();
		this.tiles = tiles;
		this.size = size;
		this.main = main;
		initPieces();
	}

	public void initPieces(){
		int width = size.width, height = 0, dif = 0;
		if(color == Type.BLACK){
			height = size.width;
			for(int i = 0; i < 8; i++)
				pieces.add(new Pawn(Type.BLACK, tiles[height - 2][i], main));
			dif = 1;
		}else{
			for(int i = 0; i < 8; i++)
				pieces.add(new Pawn(Type.WHITE, tiles[1][i], main));
		}
		//Rooks
		pieces.add(new Rook(color, tiles[height - dif][0], main));
		pieces.add(new Rook(color, tiles[height - dif][width - 1], main));
		//Knights
		pieces.add(new Knight(color, tiles[height - dif][1], main));
		pieces.add(new Knight(color, tiles[height - dif][width - 2], main));
		//Bishops
		pieces.add(new Bishop(color, tiles[height - dif][2], main));		
		pieces.add(new Bishop(color, tiles[height - dif][width - 3], main));
		//Queens
		pieces.add(new Queen(color, tiles[height - dif][width - 4], main));
		//Kings
		pieces.add(new King(color, tiles[height - dif][width - 5], main));
	}

	public ArrayList<Piece> getPieces() {
		return pieces;
	}

	/*public void initWhite(){
		for(int i = 0; i < 8; i++)
			pieces.add(new Pawn(Type.WHITE, tiles[1][i], main));
		pieces.add(new Rook(Type.WHITE, tiles[0][0], main));
		pieces.add(new Rook(Type.WHITE, tiles[0][size.width - 1], main));
		pieces.add(new Knight(Type.WHITE, tiles[0][1], main));
		pieces.add(new Knight(Type.WHITE, tiles[0][size.width - 2], main));
		pieces.add(new Bishop(Type.WHITE, tiles[0][2], main));		
		pieces.add(new Bishop(Type.WHITE, tiles[0][size.width - 3], main));
	}*/

}
