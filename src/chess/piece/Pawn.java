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
		Tile [] [] tmp = main.getBoard().getTiles();
		if(color == Type.WHITE){
			if(tile.getY()/ Tile.SIZE == 1){
				if(deltaY == -2){
					if(tmp[2][(int) (tile.getX() / Tile.SIZE)].getPiece() == null)
						return true;
				}
			}
			if(deltaY == -1 && tmp[(int) (tile.getX() / Tile.SIZE)][(int) (moveTile.getY() / Tile.SIZE)].getPiece() == null)
				return true;
		}else{
			if(tile.getY()/ Tile.SIZE == 6){
				if(deltaY == 2){
					if(tmp[5][(int) (tile.getX() / Tile.SIZE)].getPiece() == null)
						return true;
				}
			}
			if(deltaY == 1 && tmp[(int) (tile.getX() / Tile.SIZE)][(int) (moveTile.getY() / Tile.SIZE )].getPiece() == null)
				return true;
		}
		return false;
	}
	
	public boolean canTake(Tile moveTile, int dX, int dY){
		if(moveTile.getPiece() == null)
			return false;
		if(dX != 1 && dX != -1)
			return false;
		if(color == Type.WHITE && dY == -1){
			if(moveTile.getPiece().getColor() == Type.BLACK)
				return true;
		}else if(color == Type.BLACK && dY == 1){
			if(moveTile.getPiece().getColor() == Type.WHITE)
				return true;
		}
		return false;
	}

}
