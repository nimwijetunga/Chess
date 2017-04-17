package chess.piece;

import chess.board.Main;
import chess.board.Tile;

public class King extends Piece{

	private static final long serialVersionUID = 1L;

	public King(Type color, Tile tile, Main main) {
		super(Type.KING, color, tile, main);
	}

	public boolean moveValid(Tile moveTile) {
		int deltaX = (int) Math.abs((tile.getX() - moveTile.getX())) / Tile.SIZE;
		int deltaY = (int) Math.abs(((tile.getY()) - moveTile.getY())) / Tile.SIZE;
		if((deltaX == 0 && deltaY == 1) || (deltaY == 0 && deltaX == 1) || (deltaX == 1 && deltaY == 1))
			return true;
		return false;
	}

}
