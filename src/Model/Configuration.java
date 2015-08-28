package Model;

import Pezzi.ChessColor;
import Pezzi.Piece;

public interface Configuration {

	/**
	 * Restituisce il pezzo alle coordinate desiderate.
	 * @param x coordinata x
	 * @param y coordinata y
	 * @return Il pezzo che si trova nella posizione indicata
	 */
	public Piece at(int x, int y);
	
	/**
	 * Ritorna la posizione del Re di un determinato colore.
	 * @param color 	il colore del Re di cui vogliamo le coordinate
	 * @return 	Ritorna un array int[2]	che contiene le coordinate, in ordine, x e y*/
	public int[] getKing(ChessColor color);
	
	public boolena equals(Object other);
	
}
