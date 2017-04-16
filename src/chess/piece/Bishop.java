package chess.piece;

import chess.board.Main;
import chess.board.Tile;

public class Bishop extends Piece{

	private static final long serialVersionUID = 1L;

	public Bishop(Type color, Tile tile, Main main) {
		super(Type.BISHOP, color, tile, main);
	}
	
	public boolean moveValid(Tile moveTile){
		double deltaX = tile.getX() - moveTile.getX();
		double deltaY = tile.getY() - moveTile.getY();
		double slope = deltaY / deltaX;
		if(!pathBlocked(moveTile) && (slope == 1 || slope == -1))
			return true;
		return false;
	}
	
	public boolean pathBlocked(Tile moveTile){
		Tile [][] tiles = main.getBoard().getTiles();
		try{
			if(tile.getX() < moveTile.getX()){
				int y = (int) (tile.getY() / Tile.SIZE);
				if(moveTile.getY() < tile.getY()){
					for(int i = (int) (tile.getX() / Tile.SIZE); i < (moveTile.getX()) / Tile.SIZE; i++){
						Tile t = tiles[y - 1][i + 1];
						y--;
						if(hasPiece(t, moveTile))
							return true;
					}
				}else{
					for(int i = (int) (tile.getX() / Tile.SIZE); i < moveTile.getX() / Tile.SIZE; i++){
						Tile t = tiles[y + 1][i + 1];
						y++;
						if(hasPiece(t, moveTile))
							return true;
					}
				}
			}else{
				int y = (int) (moveTile.getY() / Tile.SIZE);
				if(moveTile.getY() < tile.getY()){
					for(int i =(int) (moveTile.getX() / Tile.SIZE); i < tile.getX() / Tile.SIZE; i++){
						Tile t = tiles[y][i];
						y++;
						if(hasPiece(t, moveTile))
							return true;
					}
				}else{
					for(int i =(int) (moveTile.getX() / Tile.SIZE); i < tile.getX() / Tile.SIZE; i++){
						Tile t = tiles[y][i];
						y--;
						if(hasPiece(t, moveTile))
							return true;
					}
				}
			}
		}catch(Exception e){}
		return false;
	}
	
	public boolean hasPiece(Tile t, Tile moveTile){
		if(t.containsAlly(this))
			return true;
		if(t.containsEnemy(this) && !t.equals(moveTile))
			return true;
		return false;
	}
}
