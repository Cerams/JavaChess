package Pezzi;

import javax.swing.ImageIcon;

public final class Piece {
	private final ChessColor color;
	private final ChessPiece type;
	private final int number;

	public Piece(ChessPiece type, ChessColor color, int number) {
		this.type=type;
		this.color = color;
		this.number = number;
	}
	
	/**
	 * Ritorna il colore di un pezzo.
	 * @return ChessColor	il colore del pezzo
	 * */
	public ChessColor getColor() {
		return color;
	}
	
	/**
	 * Ritorna il tipo di un pezzo.
	 * @return ChessPiece	il tipo del pezzo
	 * */
	public ChessPiece getType() {
		return type;
	}
	
	/**
	 * Ritorna il number di un pezzo
	 * @return Ritorna il numero del pezzo
	 * */
	public int getNumber(){
		return number;
	}
	
	@Override // prima non c'era
	public boolean equals(Object other){
		return other instanceof Piece && ((Piece) other).getColor()==color && 
				((Piece) other).getType().equals(type) && ((Piece) other).getNumber()==number;
	}
	
	/**
	 * Assegna ad ogni pezzo la sua corrispettiva immagine.
	 * @return ImageIcon	immagine relativa al pezzo
	 * */
	public ImageIcon getImage(){
		switch (type) {
		case ROOK:
			if (color==ChessColor.BLACK)
				return new ImageIcon("img/BlackRook.png");
			else
				return new ImageIcon("img/WhiteRook.png");
		case KNIGHT:
			if (color == ChessColor.BLACK)
				return new ImageIcon("img/BlackKnight.png");
			else
				return new ImageIcon("img/WhiteKnight.png");
		case BISHOP:
			if (color == ChessColor.BLACK)
				return new ImageIcon("img/BlackBishop.png");
			else
				return new ImageIcon("img/WhiteBishop.png");
		case QUEEN:
			if (color == ChessColor.BLACK)
				return new ImageIcon("img/BlackQueen.png");
			else
				return new ImageIcon("img/WhiteQueen.png");
		case KING:
			if (color == ChessColor.BLACK)
				return new ImageIcon("img/BlackKing.png");
			else
				return new ImageIcon("img/WhiteKing.png");
		case PAWN:
			if (color == ChessColor.BLACK)
				return new ImageIcon("img/BlackPawn.png");
			else
				return new ImageIcon("img/WhitePawn.png");
		default:
			return null;
		}
	}
	
	@Override
	public int hashCode(){
		return type.hashCode()^color.hashCode()^number;
	}
}
