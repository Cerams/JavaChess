package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import Controller.Controller;
import Model.ChessModel;
import Model.Configuration;
import Model.Model;
import Pezzi.ChessColor;
import Pezzi.Piece;

public class ChessView extends JFrame implements View{

	private static final long serialVersionUID = 1L;
	private Model model;
	private final Controller controller;
	private final JPanel chessboard;
	private final JButton[][] casella = new JButton[8][8];

	public ChessView(){
		model = new ChessModel(this);
        this.controller = model.getController();
		chessboard = new JPanel();
		chessboard.setLayout(new GridLayout(8, 8));
		for(int x=0; x<8; x++){		// doppio ciclo per la matrice
			for(int y=0; y<8; y++){
				casella[x][y]=mkButton(x,y);
				chessboard.add(casella[x][y]);
			}
		}
		
		add(chessboard, BorderLayout.CENTER);
		
		impostaConfigurazione(model.getConfiguration());
	}
			
	/**
	 * Crea un bottone, definendone le coordinate, il colore e l'evento
	 * @param x		coordinata x della scaccheira
	 * @param y		coordinata y della scaccheira
	 * @return JButton		bottone creato
	 * */	
	private JButton mkButton(int x, int y) {
		
		casella[x][y] = new JButton();
		if ((x%2 == 0 && y%2 == 0) || (x%2 != 0 && y%2 != 0))
			casella[x][y].setBackground(Color.WHITE);
		else
			casella[x][y].setBackground(Color.GRAY);
		
		casella[x][y].setBorder(null);
		casella[x][y].setFocusable(false);
		casella[x][y].addActionListener(event -> {
			casella[x][y].setBorder(new LineBorder(Color.blue,4));
			controller.onClick(x, y);				
		});
		
		return casella[x][y];
	}

	@Override
	public void clearBorder() {
		for (int i=0; i<8; i++)
			for(int j=0; j<8; j++)
				casella[i][j].setBorder(null);		
	}

	@Override
	public void impostaConfigurazione(Configuration configuration){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				Piece piece = model.at(i,j);
				casella[i][j].setIcon(piece == null? null : piece.getImage());	// a ogni casella occupata da un pezzo, l'immagine appropriata			
			}
		}		
	}

	@Override
	public void paintCheck(ChessColor color) {
		int[] king = model.getKing(color);
		 // bordo per indicare che il Re e' sotto scacco	
		 casella[king[0]][king[1]].setBorder(new LineBorder(Color.red,10)); 	
	}

	@Override	// prima non c'era
	public void checkmate(ChessColor color) {
		
		// ruota l'immagine del Re e imposta un nuovo bordo
		casella[model.getKing(color)[0]][model.getKing(color)[1]].setIcon(new ImageIcon("img/"+color+"KingRotate.png"));
		casella[model.getKing(color)[0]][model.getKing(color)[1]].setBorder(new LineBorder(Color.ORANGE,20));
		
		String[] options = {"Nuovo Gioco","Annulla"}; 	// opzione di fine partita
		int selection = JOptionPane.showOptionDialog(this, "Complimenti! Scacco Matto \n Volete giocare di nuovo?",
				"Scacco Matto",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
		
		if(selection == 0){
			model.onNewGame();
			clearBorder();
			impostaConfigurazione(model.getConfiguration());
		}
		
	}
	
	@Override
	public int trasform() {
		String[] options = {"Regina","Torre","Cavallo","Alfiere"};
		int selection = JOptionPane.showOptionDialog(this, "Seleziona in cosa promuovere il Pedone",
				"Promozione Pedone",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);

		return selection;
	}	
}


