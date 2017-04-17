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
		if((dX == 1 || dX == -1) && (dY == -2 || dY == 2)) // Move 1
			return true;
		else if((dX == 2 || dX == -2) && (dY == -1 || dY == 1)) // Move 2
			return true;
		return false;
	}
}
