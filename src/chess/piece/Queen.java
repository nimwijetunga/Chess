package chess.piece;

import chess.board.Main;
import chess.board.Tile;

public class Queen extends Piece implements RookInterface, BishopInterface{

	private static final long serialVersionUID = 1L;
	private Rook r;
	private Bishop b;
	
	public Queen(Type color, Tile tile, Main main) {
		super(Type.QUEEN, color, tile, main);
		r = new Rook(color, tile, main);
		b = new Bishop(color,tile,main);
	}

	public boolean moveValid(Tile moveTile) {
		double deltaX = tile.getX() - moveTile.getX();
		double deltaY = tile.getY() - moveTile.getY();
		double slope = deltaY / deltaX;
		if(((tile.getX() == moveTile.getX()) || (tile.getY() == moveTile.getY())) && isBlocked(moveTile) == false)
			return true;
		else if(!pathBlocked(moveTile, tile) && (slope == 1 || slope == -1))
			return true;
		return false;
	}

	public boolean pathBlocked(Tile moveTile, Tile tile) {
		return b.pathBlocked(moveTile, tile);
	}

	public boolean isBlocked(Tile moveTile) {
		return r.isBlocked(moveTile);
	}

	public boolean pathSearch(int start, int end, boolean vertical, Tile moveTile) {
		return r.pathSearch(start, end, vertical, moveTile);
	}

}
