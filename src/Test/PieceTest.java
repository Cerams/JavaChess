package Test;

import org.junit.Assert;
import org.junit.Test;
import Pezzi.ChessColor;
import Pezzi.ChessPiece;
import Pezzi.Piece;

public class PieceTest {

	/**
	 * Controlla se due pezzi sono uguali tra loro
	 * */
	@Test
	public void pieceEqualsTest(){
		Piece pedoneBianco = new Piece(ChessPiece.PAWN, ChessColor.WHITE,0);
		Piece pedoneNero = new Piece(ChessPiece.PAWN, ChessColor.BLACK,0);
		Piece pedoneBianco2 = new Piece(ChessPiece.PAWN, ChessColor.WHITE,0);
		Piece torreBianca = new Piece(ChessPiece.ROOK, ChessColor.WHITE,0);
		Assert.assertTrue(pedoneBianco.getType().equals(pedoneNero.getType()));
		Assert.assertFalse(pedoneBianco.getColor()==pedoneNero.getColor());
		Assert.assertTrue(pedoneBianco.getNumber()==pedoneNero.getNumber());
		Assert.assertNotEquals(pedoneBianco, pedoneNero);
		Assert.assertEquals(pedoneBianco, pedoneBianco2);
		Assert.assertNotEquals(torreBianca,pedoneBianco);		
	}
}
