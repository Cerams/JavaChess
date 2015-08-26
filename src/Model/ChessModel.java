package Model;

import Controller.ChessController;
import Controller.Controller;
import Pezzi.ChessColor;
import Pezzi.Piece;
import View.View;

public final class ChessModel implements Model {
	private Configuration configuration;
	private View view;
	private Controller controller;

	public ChessModel(View view){
		this.view=view;
		controller = new ChessController(this,view);
		onNewGame();	
	}
	
	@Override
	public Controller getController(){
		return controller;
	}
	
	@Override
	public Piece at(int x, int y) {
		return configuration.at(x,y);
	}

	@Override
	public Configuration getConfiguration() {
		return configuration;
	}

	@Override
	public void setConfiguration(Configuration configuration) {
		if (configuration!=null || !this.configuration.equals(configuration)){
			this.configuration = configuration;
			view.impostaConfigurazione(this.configuration);
		}
	}

	@Override
	public void setView(View listener) {
		this.view = listener;
	}
	
	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public int[] getKing(ChessColor color){
		return configuration.getKing(color);
	}
	
	@Override
	public void wrongPath() {
		view.clearBorder();	
	}
	
	@Override
	public void paintCheck(ChessColor color) {
		view.paintCheck(color);	
	}
	
	@Override
	public void checkmate(ChessColor color) {
		view.checkmate(color);		
	}
	
	@Override
	public int trasform(){
		return view.trasform();
	}
	
	@Override
	public void onNewGame() {
		configuration = new ChessConfiguration(controller.onNewGame());
	}
	
}
