package chess.piece;

import chess.board.Main;
import chess.board.Tile;

public class Queen extends Piece{

	private static final long serialVersionUID = 1L;

	public Queen(Type color, Tile tile, Main main) {
		super(Type.QUEEN, color, tile, main);
	}

	public boolean moveValid(Tile moveTile) {
		double deltaX = tile.getX() - moveTile.getX();
		double deltaY = tile.getY() - moveTile.getY();		
		return true;
	}

}
