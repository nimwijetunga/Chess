package chess.piece;


import chess.board.Main;
import chess.board.Tile;

public class Pawn extends Piece{

	private static final long serialVersionUID = 1L;

	public Pawn(Type color, Tile tile, Main main) {
		super(Type.PAWN, color, tile, main);
	}

	public boolean moveValid(Tile moveTile) {
		int deltaX = (int) (tile.getX() - moveTile.getX()) / Tile.SIZE;
		int deltaY = (int) (tile.getY() - moveTile.getY()) / Tile.SIZE;
		if(canTake(moveTile, deltaX, deltaY))
			return true;
		if(deltaX != 0)
			return false;
		int mult = 1, htmp = -1;
		Tile [] [] tmp = main.getBoard().getTiles();
		if(color == Type.WHITE){
			if(tile.getY()/ Tile.SIZE == 1)
				htmp = 0;
			mult = -1;
		}else{
			if(tile.getY()/ Tile.SIZE == 6)
				htmp = main.getBoard().getHeight() - 1;
		}
		if(htmp != -1){
			if(deltaY == (mult * 2) && tmp[htmp - deltaY][(int) (tile.getX() / Tile.SIZE)].getPiece() == null)
				return true;
		}
		if(deltaY == mult && tmp[(int) (moveTile.getY() / Tile.SIZE )][(int) (tile.getX() / Tile.SIZE)].getPiece() == null)
			return true;
		return false;
	}
	
	public boolean canTake(Tile moveTile, int dX, int dY){
		if(moveTile.getPiece() == null || (dX != 1 && dX != -1))
			return false;
		if((dY == -1 || dY == 1) && moveTile.containsEnemy(this))
			return true;
		return false;
	}

}
