package Test;

import org.junit.Assert;
import org.junit.Test;
import Controller.ChessRules;
import Controller.Rules;
import Model.ChessConfiguration;
import Model.Configuration;
import Pezzi.ChessColor;
import Pezzi.ChessPiece;
import Pezzi.Piece;

public class RulesTest {

	/**
	 * Controlla che i movimenti e le catture del Pedone siano consentiti
	 * */
	@Test
	public void pawnCanEatAndMoveTest(){
	Piece[][] piece = new Piece[8][8];
	piece[3][4] = new Piece(ChessPiece.PAWN,ChessColor.WHITE,0);
	piece[2][4] = new Piece(ChessPiece.PAWN,ChessColor.BLACK,0);
	piece[2][3] = new Piece(ChessPiece.PAWN,ChessColor.BLACK,1);
	Configuration configuration = new ChessConfiguration(piece,new int[]{0,0},new int[]{4,7});
	Rules rule = new ChessRules();
	Assert.assertTrue(rule.pawnCanEat(3,4,2,3,configuration));
	Assert.assertFalse(rule.pawnCanEat(3,4,2,4,configuration));
	Assert.assertTrue(rule.pawnCanEat(2,3,3,4,configuration));
	Assert.assertFalse(rule.pawnCanEat(2,4,3,4,configuration));
	Assert.assertFalse(rule.pawnCanMove(3,4,2,4,configuration));
	Assert.assertTrue(rule.pawnCanEat(2,3,3,3,configuration));
}
	
	/**
	 * Controlla se i movimenti del Cavallo siano consentiti
	 * */
	@Test
	public void knightCanMoveTest(){
		Piece[][] piece = new Piece[8][8];
		piece[5][6] = new Piece(ChessPiece.KNIGHT,ChessColor.WHITE,0);
		Configuration configuration = new ChessConfiguration(piece,new int[]{0,0},new int[]{4,7});
		Rules rule = new ChessRules();
		Assert.assertTrue(rule.knightCanMove(5,6,4,4,configuration,false));
		Assert.assertFalse(rule.knightCanMove(5,6,1,2,configuration,false));
	}

	/**
	 * Controlla se i movimenti della Torre siano consentiti
	 * */
	 @Test
	 public void rookCanMoveTest(){
		 Piece[][] piece = new Piece[8][8];
			piece[5][6] = new Piece(ChessPiece.ROOK,ChessColor.WHITE,0);
			Configuration configuration = new ChessConfiguration(piece,new int[]{0,0},new int[]{4,7});
			Rules rule = new ChessRules();
			Assert.assertTrue(rule.rookCanMove(5,6,5,2,configuration,false));
			Assert.assertTrue(rule.rookCanMove(5,6,3,6,configuration,false));
			Assert.assertFalse(rule.rookCanMove(5,6,3,2,configuration,false));
		}
	 
		/**
		 * Controlla se i movimenti dell'Alfiere siano consentiti
		 * */
	 @Test
	 public void bishopCanMoveTest(){
		 Piece[][] piece = new Piece[8][8];
			piece[5][6] = new Piece(ChessPiece.BISHOP,ChessColor.WHITE,0);
			Configuration configuration = new ChessConfiguration(piece,new int[]{0,0},new int[]{4,7});
			Rules rule = new ChessRules();
			Assert.assertTrue(rule.bishopCanMove(5,6,1,2,configuration,false));
			Assert.assertTrue(rule.bishopCanMove(5,6,4,5,configuration,false));
			Assert.assertFalse(rule.bishopCanMove(5,6,3,2,configuration,false));
		}
	 
		/**
		 * Controlla se i movimenti della Regina siano consentiti
		 * */
	 @Test
	 public void queenCanMoveTest(){
		 Piece[][] piece = new Piece[8][8];
			piece[5][6] = new Piece(ChessPiece.QUEEN,ChessColor.WHITE,0);
			Configuration configuration = new ChessConfiguration(piece,new int[]{0,0},new int[]{4,7});
			Rules rule = new ChessRules();
			Assert.assertTrue(rule.queenCanMove(5,6,1,2,configuration,false));
			Assert.assertTrue(rule.queenCanMove(5,6,4,5,configuration,false));
			Assert.assertFalse(rule.queenCanMove(5,6,3,2,configuration,false));
			Assert.assertTrue(rule.queenCanMove(5,6,5,2,configuration,false));
			Assert.assertTrue(rule.queenCanMove(5,6,3,6,configuration,false));
			Assert.assertFalse(rule.queenCanMove(5,6,1,1,configuration,false));
		}
	 
		/**
		 * Controlla se i movimenti del Re siano consentiti
		 * */
	 @Test
	 public void kingCanMoveTest(){
		 Piece[][] piece = new Piece[8][8];
			piece[4][3] = new Piece(ChessPiece.KING,ChessColor.WHITE,0);
			piece[3][1] =new Piece(ChessPiece.KNIGHT,ChessColor.BLACK,0);
			piece[1][2] =new Piece(ChessPiece.ROOK,ChessColor.BLACK,0);
			piece[2][4] =new Piece(ChessPiece.BISHOP,ChessColor.BLACK,0);
			Configuration configuration = new ChessConfiguration(piece,new int[]{4,3},new int[]{0,0});
			Rules rule = new ChessRules();
			Assert.assertTrue(rule.kingCanMove(4,3,4,4,configuration,true,true));
			Assert.assertTrue(rule.kingCanMove(4,3,3,4,configuration,true,true));
			Assert.assertFalse(rule.kingCanMove(4,3,3,3,configuration,true,true));
			Assert.assertFalse(rule.kingCanMove(4,3,3,2,configuration,true,true));
			Assert.assertFalse(rule.kingCanMove(4,3,2,1,configuration,true,true));
		}
	
	 /**
	  * Controlla se lo spostamento di un pezzo metta sotto scacco il proprio Re
	  * */
	 @Test
	 public void tryToMoveTest(){
		 Piece[][] piece = new Piece[8][8];
			piece[4][3] = new Piece(ChessPiece.KING,ChessColor.WHITE,0);
			piece[4][1] =new Piece(ChessPiece.ROOK,ChessColor.BLACK,0);
			piece[1][2] =new Piece(ChessPiece.ROOK,ChessColor.BLACK,0);
			piece[2][4] =new Piece(ChessPiece.BISHOP,ChessColor.BLACK,0);
			
			piece[4][2]= new Piece(ChessPiece.KNIGHT,ChessColor.WHITE,0);
			Configuration configuration = new ChessConfiguration(piece,new int[]{4,3},new int[]{0,0});
			
			Rules rule = new ChessRules();
			
			Assert.assertFalse(rule.tryToMove(4,2,3,4,configuration, true));
			Assert.assertFalse(rule.tryToMove(4,2,2,1,configuration, true));
			
			piece[4][2]= new Piece(ChessPiece.ROOK,ChessColor.WHITE,0);
			configuration = new ChessConfiguration(piece,new int[]{4,3},new int[]{0,0});
			Assert.assertFalse(rule.tryToMove(4,2,3,2,configuration, true));
			Assert.assertTrue(rule.tryToMove(4,2,4,1,configuration, true));
			
			piece[4][2]= new Piece(ChessPiece.QUEEN,ChessColor.WHITE,0);
			configuration = new ChessConfiguration(piece,new int[]{4,3},new int[]{0,0});
			Assert.assertFalse(rule.tryToMove(4,2,3,2,configuration, true));
			Assert.assertTrue(rule.tryToMove(4,2,4,1,configuration, true));
			
			piece[4][2]= new Piece(ChessPiece.PAWN,ChessColor.WHITE,0);
			piece[4][1] = null;
			piece[3][1]= new Piece(ChessPiece.KNIGHT,ChessColor.BLACK,0);			
			configuration = new ChessConfiguration(piece,new int[]{4,3},new int[]{0,0});
			Assert.assertFalse(rule.tryToMove(4,2,3,2,configuration, true));
			Assert.assertTrue(rule.tryToMove(4,2,3,1,configuration, true));
							
			piece[4][2]= new Piece(ChessPiece.BISHOP,ChessColor.WHITE,0);
			configuration = new ChessConfiguration(piece,new int[]{4,3},new int[]{0,0});
			Assert.assertFalse(rule.tryToMove(4,2,3,2,configuration, true));
			Assert.assertTrue(rule.tryToMove(4,2,3,1,configuration, true));					
	 }

	 /**
	  * Controlla se si sta selezionando un pezzo consentito
	  * */
	 @Test
	 public void firstClickTest(){
		 Rules rules = new ChessRules();
		 Piece[][] piece = new Piece[8][8];
		 piece[1][1] = new Piece(ChessPiece.BISHOP,ChessColor.WHITE,0);
		 piece[4][5] = new Piece(ChessPiece.BISHOP,ChessColor.BLACK,0);
		 Configuration configuration = new ChessConfiguration(piece,new int[]{0,0}, new int[]{3,3});
		 
		 Assert.assertFalse(rules.firstClick(1,1,configuration));
		 Assert.assertTrue(rules.firstClick(2, 3, configuration));
		 Assert.assertTrue(rules.firstClick(4, 5, configuration));
	 }
	 
	 /**
	  * Controlla se sara' possibile eseguire i controlli per spostare un pezzo
	  */
	 @Test
	 public void secondClickTest(){
		 Rules rules = new ChessRules();
		 Piece[][] piece = new Piece[8][8];
		 piece[1][1] = new Piece(ChessPiece.BISHOP,ChessColor.BLACK,0);
		 piece[4][5] = new Piece(ChessPiece.ROOK,ChessColor.WHITE,0);
		 piece[6][5] = new Piece(ChessPiece.BISHOP,ChessColor.BLACK,0);
		 Configuration configuration = new ChessConfiguration(piece,new int[]{6,7}, new int[]{3,3});
		 
		 Assert.assertFalse(rules.secondClick(1,1,4,5,configuration));
		 Assert.assertTrue(rules.secondClick(4,5,6, 5, configuration));
		 Assert.assertTrue(rules.secondClick(1,1,2, 2, configuration));
	 }
	 
	 /**
	  * Controlla se c'e' uno Scacco in corso
	  * */
	 @Test
	 public void checkTest(){
		 Rules rules = new ChessRules();
		 Piece[][] piece = new Piece[8][8];
		 
		 //for White
		 piece[4][3] = new Piece(ChessPiece.KING,ChessColor.WHITE,0);
		 piece[1][3] = new Piece(ChessPiece.ROOK,ChessColor.BLACK,0);
		 piece[2][4] = new Piece(ChessPiece.BISHOP,ChessColor.BLACK,0);
		 Configuration configuration = new ChessConfiguration(piece,new int[]{4,3}, new int[]{0,3});
		 
		 Assert.assertEquals(rules.check(configuration,false),"White");
		 Assert.assertNotEquals(rules.check(configuration,false),"Black");
		 Assert.assertNotEquals(rules.check(configuration,false),"");
		 
		 //for Black
		 piece[4][3] = new Piece(ChessPiece.KING,ChessColor.BLACK,0);
		 piece[1][3] = new Piece(ChessPiece.ROOK,ChessColor.WHITE,0);
		 piece[2][4] = new Piece(ChessPiece.BISHOP,ChessColor.WHITE,0);
		 
		 configuration = new ChessConfiguration(piece,new int[]{0,3}, new int[]{4,3});
		 Assert.assertEquals(rules.check(configuration,false),"Black");
		 Assert.assertNotEquals(rules.check(configuration,false),"White");
		 Assert.assertNotEquals(rules.check(configuration,false),"");
		 
		 //not Check
		 piece[4][3] = new Piece(ChessPiece.KING,ChessColor.WHITE,0);
		 piece[1][4] = new Piece(ChessPiece.ROOK,ChessColor.WHITE,0);
		 piece[2][4] = new Piece(ChessPiece.BISHOP,ChessColor.WHITE,0);
		 
		 configuration = new ChessConfiguration(piece,new int[]{4,3}, new int[]{0,0});
		 Assert.assertEquals(rules.check(configuration,false),"");
		 Assert.assertNotEquals(rules.check(configuration,false),"White");
		 Assert.assertNotEquals(rules.check(configuration,false),"Black");	
	 }
	 
	 /**
	  * Controlla se uno Scacco e' Matto
	  * */
	 @Test
	 public void checkmateTest(){
		 Rules rules = new ChessRules();
		 Piece[][] piece = new Piece[8][8];
		
		 piece[3][1] = new Piece(ChessPiece.KING,ChessColor.WHITE,0);
		 piece[4][3] = new Piece(ChessPiece.QUEEN,ChessColor.BLACK,0);
		 piece[2][3] = new Piece(ChessPiece.KNIGHT,ChessColor.BLACK,0);
		 piece[3][3] = new Piece(ChessPiece.PAWN,ChessColor.BLACK,0);
		 Configuration configuration = new ChessConfiguration(piece,new int[]{3,1}, new int[]{0,3});
		 rules.check(configuration, false);
		 Assert.assertNotEquals(rules.checkmate(configuration, ChessColor.WHITE),ChessColor.WHITE);
		 Assert.assertEquals(rules.checkmate(configuration, ChessColor.BLACK),ChessColor.BLACK);		 
	 }
}

