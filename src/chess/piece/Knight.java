package chess.piece;

import chess.board.Main;
import chess.board.Tile;

public class Knight extends Piece{

	private static final long serialVersionUID = 1L;

	public Knight(Type color, Tile tile, Main main) {
		super(Type.KNIGHT, color, tile, main);
	}

	public boolean moveValid(Tile moveTile) {
		int dX = (int) (tile.getX() - moveTile.getX()) / Tile.SIZE;
		int dY = (int) (tile.getY() - moveTile.getY()) / Tile.SIZE;
		if(move1(moveTile, dX, dY) || move2(moveTile, dX, dY))
			return true;
		return false;
	}
	
	public boolean move1(Tile moveTile, int deltaX, int deltaY){
		if((deltaX == 1 || deltaX == -1) && (deltaY == -2 || deltaY == 2))
			return true;
		return false;
	}
	
	public boolean move2(Tile moveTile, int deltaX, int deltaY){
		if((deltaX == 2 || deltaX == -2) && (deltaY == -1 || deltaY == 1))
			return true;
		return false;
	}
	

}
