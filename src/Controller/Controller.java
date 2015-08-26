package Controller;

import Model.Configuration;
import Pezzi.ChessColor;
import Pezzi.Piece;

public interface Controller {

	/**
	 * Gestisce il singolo click su di una casella, permettendo di capire se il click e' lecito o meno.
	 * @param x		coordinata x del bottone
	 * @param y 	coordinata y del bottone
	 * */
	public void onClick(int x, int y);
	
	/**
	 * Controlla se vi e' uno scacco in corso
	 * @return Stringa contenete il colore del Re sotto scacco
	 * */	
	public String check();
	
	/**
	 * Controlla se uno scacco e' uno scacco matto
	 * @param configuration 	configurazione corrente
	 * @param color		colore del Re sotto scacco
	 * */
	public void checkmate(Configuration configuration, ChessColor color);

	/**
	 * Permette di iniziare una nuova partita
	 * @return Piece[][] la configurazione da usare per la nuova partita
	 * */
	public Piece[][] onNewGame();

}
