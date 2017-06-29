package chess.handlers;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import chess.board.Main;
import chess.board.Tile;
import chess.piece.Piece;
import chess.piece.Type;

public class MouseHandler extends MouseAdapter implements MouseMotionListener{

	private int mx,my;
	public static boolean wT, bT;

	private ArrayList<Piece> white, black;
	private Main main;

	public MouseHandler(Main main){
		this.main = main;
		wT = true;
		bT = false;
	}

	public void mouseClicked(MouseEvent e){
		mx = e.getX();
		my = e.getY();
		selectPiece();
		movePiece();
	}

	public void selectPiece(){
		ArrayList<Piece> tmp;
		white = main.getBoard().getpW().getPieces();
		black = main.getBoard().getpB().getPieces();
		if(wT)
			tmp = white;
		else
			tmp = black;
		for(Piece i : tmp){
			if(i.contains(mx, my) && i.isSelected() == false){
				i.setSelected(true);
				Color c;
				if(wT)
					c = Color.RED;
				else
					c = Color.GREEN;
				i.getTile().setColor(c);
				i.getTile().setSelected(true);
			}
		}
	}

	public void movePiece(){
		ArrayList<Piece> tmp;
		white = main.getBoard().getpW().getPieces();
		black = main.getBoard().getpB().getPieces();
		Tile [][] tiles = main.getBoard().getTiles();
		if(wT)
			tmp = white;
		else
			tmp = black;
		Tile moveTile = null;
		for(Tile [] i : tiles){
			for(Tile t : i){
				if(t.contains(mx,my)){
					moveTile = t;
					break;
				}
			}
		}
		for(Piece i : tmp){
			if(i.isSelected() && moveTile != null && moveTile != i.getTile()){
				if(!moveTile.containsAlly(i)){
					boolean switchM = i.move(moveTile);
					if(switchM){
						if(wT){
							wT = false;
							bT = true;
						}
						else if(bT){
							bT = false;
							wT = true;
						}
					}
				}
				i.setSelected(false);
				i.getTile().setSelected(false);
				i.getTile().setColor(i.getTile().getOrigColor());
			}
		}
	}

	public void mouseDragged(MouseEvent e) {
		//does nothing
	}

	public void mouseMoved(MouseEvent e) {
		int mx = e.getX(), my = e.getY();
		try{
			if(main.getBoard().getTiles() == null)
				return;
			for(Tile [] i : main.getBoard().getTiles()){
				for(Tile t : i){
					if(t.contains(mx,my)){
						if(t.getPiece() == null)
							t.setColor(Color.MAGENTA);
						else if(t.getPiece().getColor() == Type.BLACK)
							t.setColor(Color.GREEN);
						else if (t.getPiece().getColor() == Type.WHITE)
							t.setColor(Color.RED);
					}else if(!t.contains(mx,my) && !t.isSelected())
						t.setColor(t.getOrigColor());
				}
			}
		}catch(Exception c){}
	}

	public boolean iswT() {
		return wT;
	}

	public boolean isbT() {
		return bT;
	}
}
