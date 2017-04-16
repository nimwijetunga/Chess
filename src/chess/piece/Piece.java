package chess.piece;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import chess.board.Main;
import chess.board.Tile;

public abstract class Piece extends Rectangle{
	
	private static final long serialVersionUID = 1L;
	
	protected Type type, color;
	protected Tile tile;

	protected boolean canMove, isSelected;

	protected BufferedImage image;
	
	protected Main main;

	public Piece(Type type, Type color, Tile tile, Main main){
		this.type = type;
		this.color = color;
		this.tile = tile;
		this.main = main;
		canMove = false;
		isSelected = false;
		this.setBounds((int) tile.getX(), (int) tile.getY(), Tile.SIZE, Tile.SIZE);
		tile.setPiece(this);
		loadImage();
	}
	
	public boolean move(Tile moveTile) {
		if(moveValid(moveTile)){
			if(moveTile.getPiece() != null)
				main.getBoard().removePiece(moveTile, moveTile.getPiece().getColor());
			tile.setPiece(null);
			this.setTile(moveTile);
			moveTile.setPiece(this);
			this.setBounds(moveTile.x, moveTile.y, Tile.SIZE, Tile.SIZE);
			this.isSelected = false;
			return true;
		}
		return false;
	}
	
	public abstract boolean moveValid(Tile moveTile);
		
	public void loadImage(){
		try{
			String path = "res/" + color + "_" + type.toString() + ".png";
			image = ImageIO.read(new File(path));
		}catch(Exception e){
			e.printStackTrace();
		}
		image = resize(image, Tile.SIZE - 10, Tile.SIZE - 10);
	}
	
	public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	} 
	
	public BufferedImage getImage() {
		return image;
	}
	
	
	public boolean CanMove() {
		return canMove;
	}
	
	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}
	
	public Tile getTile() {
		return tile;
	}
	
	public void setTile(Tile tile) {
		this.tile = tile;
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	public Type getColor() {
		return color;
	}
}
