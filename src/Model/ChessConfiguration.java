package Model;

import Pezzi.ChessColor;
import Pezzi.Piece;
import java.util.Arrays;

public class ChessConfiguration implements Configuration {
	
	private Piece[][] pedine;
	private int[] blackKing = new int[2];
	private int[] whiteKing = new int[2];

	public ChessConfiguration(Piece[][] piece, int[] whiteKing, int[] blackKing){
		Piece[][] pezzi=new Piece[8][8];
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				pezzi[i][j]=piece[i][j];
			}
		}
		this.pedine=pezzi;
		this.whiteKing=new int[]{whiteKing[0],whiteKing[1]};
		this.blackKing=new int[]{blackKing[0],blackKing[1]};
	}

	public ChessConfiguration(Piece[][]piece){
        Piece[][] pezzi=new Piece[8][8];
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                pezzi[i][j]=piece[i][j];
            }
        }
        this.pedine=pezzi;
		whiteKing = new int[]{7,4};
		blackKing = new int[]{0,4};
	}
	
	@Override
	public Piece at(int x, int y) {
		
		return pedine[x][y];
	}
	
	@Override
	public boolean equals(Object other){
		if (other instanceof Configuration){
			ChessConfiguration otherConfiguration = (ChessConfiguration) other;
                        for(int i=0;i<8;i++){
                        	for(int j=0; j<8; j++){
                        		if (at(i, j)!=otherConfiguration.at(i, j))
                        			return false;
                        	}
                        }
                }
		return true;
		
	}
	
    @Override
    public int hashCode() {
        return Arrays.hashCode(this.pedine);
    }
 	
    @Override
    public int[] getKing(ChessColor color){
    	if (color==ChessColor.WHITE)
    		return new int[]{whiteKing[0],whiteKing[1]};
    	return new int[]{blackKing[0],blackKing[1]};
    }
}
