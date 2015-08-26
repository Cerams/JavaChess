package Test;

import org.junit.Assert;
import org.junit.Test;
import Model.ChessConfiguration;
import Model.Configuration;
import Pezzi.ChessColor;
import Pezzi.ChessPiece;
import Pezzi.Piece;

public class ConfigurationTest {

	/**
	 * Controlla che ci siano pezzi o meno e di che tipo, nelle caselle
	 * */
	@Test
	public void atTest(){
		Piece[][] piece = new Piece[8][8];
		piece[4][3] = new Piece(ChessPiece.KING,ChessColor.WHITE,0);
		piece[1][2] =new Piece(ChessPiece.ROOK,ChessColor.BLACK,0);
		Configuration configuration = new ChessConfiguration(piece,new int[]{4,3},new int[]{0,0});
		
		Assert.assertNotNull(configuration.at(4,3));
		Assert.assertNull(configuration.at(5, 3));
		Assert.assertTrue(configuration.at(1, 2).getType() == ChessPiece.ROOK);		
	}
	
	/**
	 * Controlla che due configurazioni siano uguali
	 * */
	@Test
	public void configurationqualsTest(){
		Piece[][] piece1 = new Piece[8][8];
		Piece[][] piece2 = new Piece[8][8];
		Piece[][] piece3 = new Piece[8][8];
		
		piece1[4][3] = new Piece(ChessPiece.KING,ChessColor.WHITE,0);
		piece1[3][1] =new Piece(ChessPiece.KNIGHT,ChessColor.BLACK,0);
		piece1[1][2] =new Piece(ChessPiece.ROOK,ChessColor.BLACK,0);
		piece1[2][4] =new Piece(ChessPiece.BISHOP,ChessColor.BLACK,0);
		Configuration configuration1 = new ChessConfiguration(piece1,new int[]{4,3},new int[]{3,6});
		piece2[3][4] = new Piece(ChessPiece.PAWN,ChessColor.WHITE,0);
		piece2[2][4] = new Piece(ChessPiece.PAWN,ChessColor.BLACK,0);
		piece2[2][3] = new Piece(ChessPiece.PAWN,ChessColor.BLACK,1);
		Configuration configuration2 = new ChessConfiguration(piece2,new int[]{4,4},new int[]{3,6});
		piece3[3][4] = new Piece(ChessPiece.PAWN,ChessColor.WHITE,0);
		piece3[2][4] = new Piece(ChessPiece.PAWN,ChessColor.BLACK,0);
		piece3[2][3] = new Piece(ChessPiece.PAWN,ChessColor.BLACK,1);
		Configuration configuration3 = new ChessConfiguration(piece3,new int[]{4,3},new int[]{3,6});
			
		Assert.assertFalse(configuration1.equals(configuration2));
		Assert.assertTrue(configuration3.equals(configuration2));		
	}
	
	/**
	 * Controlla che il Re si trovi in una determinata casella
	 * */
	@Test
	public void getKingTest(){
		Piece[][] piece = new Piece[8][8];
		Configuration configuration = new ChessConfiguration(piece,new int[]{4,3},new int[]{0,0});
		int[] king;
		
		piece[4][3] = new Piece(ChessPiece.KING,ChessColor.WHITE,0);
		king = configuration.getKing(ChessColor.WHITE);
				
		Assert.assertTrue(king[0] == 4 && king[1] == 3);	
	}
}
