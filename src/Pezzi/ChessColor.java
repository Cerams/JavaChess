package Pezzi;

/**
 * Definiamo l'enum dei due colori
 * */
public enum ChessColor {
WHITE,
BLACK;

	/**
	 * Cambia il colore
	 * @return Il colore opposto 
	 * */
	public ChessColor next(){
		if (this == ChessColor.WHITE)
			return BLACK;
		return WHITE;
	}
}
