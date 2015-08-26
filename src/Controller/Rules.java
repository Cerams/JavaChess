package Controller;

import Model.Configuration;
import Pezzi.ChessColor;
import Pezzi.Piece;

public interface Rules {

	/**
	 * Dispone tutti i pezzi sulla scacchiera nella posizione iniziale.
	 * @return Ritorna la scacchiera inizializzata secondo le regole
	 * */
	public Piece[][] startConfiguration() ;

	/**
	 * Controlla se e' possibile cliccare su una casella per selezionarne il pezzo
	 * @param firstX 	coordinata x
	 * @param firstY 	coordinata y
	 * @param configuration		la configurazione attuale
	 * @return Ritorna false se e' possibile selezionare il pezzo
	 * */
	public boolean firstClick(int firstX, int firstY, Configuration configuration);

	/**
	 * Indica se e' possibile eseguire la mossa voluta
	 * @param	firstX		coordinata x del pezzo che si desidera muovere
	 * @param 	firstY		coordinata y del pezzo che si desidera muovere
	 * @param	secondX		coordinata x dove si vuole spostare il pezzo
	 * @param	secondY		coordinata y dove si vuole spostare il pezzo
	 * @param configuration		configurazione corrente
	 * @return Ritorna true se la mossa e' valida, altrimenti ritorna false
	 * */
	public boolean secondClick(int firstX, int firstY, int secondX, int secondY, Configuration configuration);

	/**
	 * Controlla se un Re e' sotto scacco
	 * @param configuration 	la configurazione attuale
	 * @param checkmateControl 	indica se il Re e' sotto scacco
	 * @return Ritorna una stringa contenente il colore che indica il colore del Re sotto scacco
	 * */
	public String check(Configuration configuration,boolean checkmateControl);
	
	/**
	 * Verifica se lo scacco del Re e' matto
	 * @param configuration		la configurazione attuale
	 * @param color		il colore del Re che si vuole controllare
	 * @return Ritorna il colore del Re se e' sotto scacco matto, altrimenti ritorna null
	 * */
	public ChessColor checkmate(Configuration configuration, ChessColor color);
	
	/**
	 * Controlla se e' possibile seguire il movimento desiderato per il Pedone
	 * @param firstX	coordinata x della posizione del pezzo
	 * @param firstY	coordinata y della posizione del pezzo
	 * @param secondX	coordinata x della posizione da raggiungere
	 * @param secondY 	coordinata y della posizione da raggiungere
	 * @param configuration		configurazione attuale su cui applicare il moviemento
	 * @return ritorna true se il movimento e' consentito, false altrimenti
	 * */
	public boolean pawnCanMove(int firstX, int firstY, int secondX, int secondY,Configuration configuration);
	
	/**
	 * Controlla se e' possibile seguire l'attacco desiderato per il Pedone
	 * @param firstX	coordinata x della posizione del pezzo
	 * @param firstY	coordinata y della posizione del pezzo
	 * @param secondX	coordinata x della posizione da raggiungere
	 * @param secondY 	coordinata y della posizione da raggiungere
	 * @param configuration		configurazione attuale su cui applicare il moviemento
	 * @return ritorna true se il movimento e' consentito, false altrimenti
	 * */
	public boolean pawnCanEat(int firstX, int firstY, int secondX, int secondY, Configuration configuration);
	
	/**
	 * Controlla se e' possibile seguire il movimento desiderato per il Cavallo
	 * @param firstX	coordinata x della posizione del pezzo
	 * @param firstY	coordinata y della posizione del pezzo
	 * @param secondX	coordinata x della posizione da raggiungere
	 * @param secondY 	coordinata y della posizione da raggiungere
	 * @param configuration		configurazione attuale su cui applicare il movimento
	 * @param type 	se true, indica che si deve controllare se la mossa mette in scacco il proprio Re
	 * @return ritorna true se il movimento e' consentito, false altrimenti
	 * */
	public boolean knightCanMove(int firstX, int firstY, int secondX, int secondY,Configuration configuration, boolean type);
	
	/**
	 * Controlla se e' possibile seguire il movimento desiderato per la Torre
	 * @param firstX	coordinata x della posizione del pezzo
	 * @param firstY	coordinata y della posizione del pezzo
	 * @param secondX	coordinata x della posizione da raggiungere
	 * @param secondY 	coordinata y della posizione da raggiungere
	 * @param configuration		configurazione attuale su cui applicare il movimento
	 * @param type 
	 * @return ritorna true se il movimento e' consentito, false altrimenti
	 * */
	public boolean rookCanMove(int firstX, int firstY, int secondX, int secondY,Configuration configuration, boolean type);	
	
	/**
	 * Controlla se e' possibile seguire il movimento desiderato per la Regina
	 * @param firstX	coordinata x della posizione del pezzo
	 * @param firstY	coordinata y della posizione del pezzo
	 * @param secondX	coordinata x della posizione da raggiungere
	 * @param secondY 	coordinata y della posizione da raggiungere
	 * @param configuration		configurazione attuale su cui applicare il movimento
	 * @param type 	se true, indica che si deve controllare se la mossa mette in scacco il proprio Re
	 * @return ritorna true se il movimento e' consentito, false altrimenti
	 * */
	public boolean queenCanMove(int firstX, int firstY, int secondX, int secondY,Configuration configuration, boolean type);
	
	/**
	 * Controlla se e' possibile seguire il movimento desiderato per l'Alfiere
	 * @param firstX	coordinata x della posizione del pezzo
	 * @param firstY	coordinata y della posizione del pezzo
	 * @param secondX	coordinata x della posizione da raggiungere
	 * @param secondY 	coordinata y della posizione da raggiungere
	 * @param configuration		configurazione attuale su cui applicare il movimento
	 * @param type 	se true, indica che si deve controllare se la mossa mette in scacco il proprio Re 
	 * @return ritorna true se il movimento e' consentito, false altrimenti
	 * */
	public boolean bishopCanMove(int firstX, int firstY, int secondX, int secondY,Configuration configuration, boolean type);
	
	/**
	 * Controlla se e' possibile seguire il movimento desiderato per il Re
	 * @param firstX	coordinata x della posizione del pezzo
	 * @param firstY	coordinata y della posizione del pezzo
	 * @param secondX	coordinata x della posizione da raggiungere
	 * @param secondY 	coordinata y della posizione da raggiungere
	 * @param configuration		configurazione attuale su cui applicare il movimento
	 * @param type 	se true, indica che si deve controllare se la mossa mette in scacco il proprio Re 
	 * @param checkmateControl	indica se il Re e' sotto scacco
	 * @return ritorna true se il movimento e' consentito, false altrimenti
	 * */
	public boolean kingCanMove(int firstX, int firstY, int secondX, int secondY, Configuration configuration, boolean type, boolean checkmateControl);
	
	/**
	 * Verifica che la mossa che si vuole compiere non metta in scacco il proprio Re
	 * @param firstX	coordinata x del pezzo che si vuole muovere
	 * @param firstY	coordinata y del pezzo che si vuole muovere
	 * @param secondX	coordinata x della casella in cui si vuole muovere il pezzo
	 * @param secondY	coordinata y della casella in cui si vuole muovere il pezzo
	 * @param configuration	configuratione attuale
	 * @param checkmateControl		indica se il Re e' sotto scacco
	 * @return Ritorna true se la mossa non mette in scacco il Re, altrimenti ritorna false.
	 * */
	public boolean tryToMove(int firstX, int firstY, int secondX, int secondY,Configuration configuration, boolean checkmateControl );

}