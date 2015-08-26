package View;

import Model.Configuration;
import Pezzi.ChessColor;

public interface View {
	
	/**
	 * Imposta la scacchiera usando una configurazione ricevuta.
	 * @param configuration		la configurazione che vogliamo la scacchiera assuma
	 * */
	void impostaConfigurazione(Configuration configuration);
		
	/**
	 * Elimina i bordi inseriti in ogni casella
	 * */
	void clearBorder();
	
	/**
	 * Delinea la casella in cui si trova il Re di un determinato colore.
	 * @param color 	specifica il colore del Re.
	 * */
	void paintCheck(ChessColor color);
	
	/**
	 * Esegue la routine di operazioni allo Scacco Matto
	 * @param color		il colore del Re sotto scacco matto
	 * */
	void checkmate(ChessColor color);

	/**
	 * Permette di scegliere il pezzo in che pezzo promuovere il Pedone
	 * @return Ritorna un intero che indica la scelta
	 * */
	int trasform();

}
