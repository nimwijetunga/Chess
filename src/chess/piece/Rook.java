
package chess.piece;


import chess.board.Main;
import chess.board.Tile;

public class Rook extends Piece implements RookInterface{

	private static final long serialVersionUID = 1L;

	public Rook(Type color, Tile tile, Main main) {
		super(Type.ROOK, color, tile, main);
	}

	public boolean moveValid(Tile moveTile){
		if(((tile.getX() == moveTile.getX()) || (tile.getY() == moveTile.getY())) && isBlocked(moveTile) == false)
			return true;
		return false;
	}

	public boolean isBlocked(Tile moveTile){
		int start = 0, end = 0;
		if(tile.getX() == moveTile.getX()){
			int ty = (int) (tile.getY() / Tile.SIZE), ty2 = (int) (moveTile.getY() / Tile.SIZE);
			start = ty;
			end = ty2;
			return pathSearch(start,end,true, moveTile);
		}
		else if(tile.getY() == moveTile.getY()){
			int tx = (int) (tile.getX() / Tile.SIZE), tx2 = (int) (moveTile.getX() / Tile.SIZE);
			start = tx;
			end = tx2;
			return pathSearch(start,end,false, moveTile);
		}
		return false;
	}

	public boolean pathSearch(int start, int end, boolean vertical, Tile moveTile){
		if(end < start){
			start--;
			int temp = end;
			end = start;
			start = temp;
		}else
			start++;
		for(int i = start; i <= end; i++){
			for(Tile j[] : main.getBoard().getTiles()){
				for(Tile t : j){
					if(vertical){
						if(t.getX() == tile.getX()  && t.getY() / Tile.SIZE == i){
							if(t.containsAlly(this) || (t.containsEnemy(this) && t.getY() != moveTile.getY()))
								return true;
						}
					}else{
						if(t.getX() / Tile.SIZE == i  && t.getY() == tile.getY()){
							if(t.containsAlly(this) || (t.containsEnemy(this) && t.getX() != moveTile.getX()))
								return true;
						}
					}
				}
			}
		}
		return false;
	}
}
