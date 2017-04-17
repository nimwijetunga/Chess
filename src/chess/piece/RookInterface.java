package chess.piece;

import chess.board.Tile;

public interface RookInterface {
	public boolean isBlocked(Tile moveTile);
	public boolean pathSearch(int start, int end, boolean vertical, Tile moveTile);
}
