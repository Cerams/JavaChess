package Model;

import Controller.Controller;
import Pezzi.ChessColor;
import Pezzi.Piece;
import View.View;

public interface Model {
	
	/**
	 * Restituisce il pezzo alle coordinate desiderate.
	 * @param x coordinata x
	 * @param y coordinata y
	 * @return Ritorna il pezzo che si trova nella posizione indicata
	 */
	public Piece at(int x, int y);
	
	/**
	 * Ritorna la configurazione corrette.
	 * @return Restituisce la configurazione corrente
	 */
	public Configuration getConfiguration();
	
	/**
	 * Imposta la configurazione.
	 * @param configuration 
	 */
	public void setConfiguration(Configuration configuration);
	
	/**
	 * Imposta la View.
	 * @param listener 	la view da impostare
	 */
	public void setView(View listener);
	
	/**
	 * Imposta il Controller.
	 * @param controller 	il controller da impostare
	 */
	public void setController(Controller controller);
		
	/**
	 * Ritorna la posizione del Re di un determinato colore.
	 * @param color 	il colore del Re di cui vogliamo le coordinate
	 * @return Ritorna un array int[] che contiene le coordinatedel Re, in ordine x e y 
	 * */
	public int[] getKing(ChessColor color);
	
	/**
	 * Chiama il metodo clearBorder dell'Interfaccia View
	 * */
	public void wrongPath();
	
	/**
	 * Chiama il metodo paintCheck dell'Interfaccia View
	 * @param color		il colore del Re a cui applicare il metodo
	 * */
	public void paintCheck(ChessColor color);
	
	/**
	 * Chiama il metodo checkmate dell'Interfaccia View
	 * @param color		il colore su del Re su cui applicare il metodo
	 * */
	public void checkmate(ChessColor color);

	/**
	 * Chiama il metodo trasfrom dell'Interfaccia View
	 * @return Ritorna un intero che indica l'opzione scelta
	 * */
	public int trasform();

	/**
	 * Ritorna il controller
	 * @return Ritorna il controller*/
	public Controller getController();

	/**
	 * Imposta la configurazione per una nuova partita
	 * */
	public void onNewGame();
}
