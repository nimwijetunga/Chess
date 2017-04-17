package chess.piece;

import chess.board.Main;
import chess.board.Tile;

public class Bishop extends Piece implements BishopInterface{

	private static final long serialVersionUID = 1L;

	public Bishop(Type color, Tile tile, Main main) {
		super(Type.BISHOP, color, tile, main);
	}

	public boolean moveValid(Tile moveTile){
		double deltaX = tile.getX() - moveTile.getX();
		double deltaY = tile.getY() - moveTile.getY();
		double slope = deltaY / deltaX;
		if(!pathBlocked(moveTile, tile) && (slope == 1 || slope == -1))
			return true;
		return false;
	}

	public boolean pathBlocked(Tile moveTile, Tile tile){
		Tile [][] tiles = main.getBoard().getTiles();
		int y = (int) (tile.getY() / Tile.SIZE), dif = 0;
		if(moveTile.getY() < tile.getY())
			dif = -1;
		else
			dif = 1;
		if(tile.getX() < moveTile.getX()){
			for(int i = (int) (tile.getX() / Tile.SIZE); i < moveTile.getX() / Tile.SIZE; i++){
				Tile t = tiles[y + dif][i + 1];
				y += dif;
				if(t.containsAlly(this) || (t.containsEnemy(this) && !t.equals(moveTile)))
					return true;
			}
		}else{
			for(int i =(int) (moveTile.getX() / Tile.SIZE); i < tile.getX() / Tile.SIZE - 1; i++){
				Tile t = tiles[y + dif][i];
				y += dif;
				if(t.containsAlly(this) || (t.containsEnemy(this) && !t.equals(moveTile))){
					return true;
				}
			}
		}
		return false;
	}
}
