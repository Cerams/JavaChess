package Controller;

import Model.ChessConfiguration;
import Model.Configuration;
import java.util.HashMap;
import Pezzi.ChessColor;
import Pezzi.ChessPiece;
import Pezzi.Piece;

public class ChessRules implements Rules  {

	private ChessColor turn = ChessColor.WHITE;

	private final HashMap<Piece,int[]> whiteCheckers = new HashMap<>();
	private final HashMap<Piece,int[]> blackCheckers = new HashMap<>();
	
	@Override
	public Piece[][] startConfiguration() {
		Piece[][] pedine = new Piece[8][8];
		turn=ChessColor.WHITE;
		pedine[0][0] = new Piece(ChessPiece.ROOK, ChessColor.BLACK,1);
		pedine[0][1] = new Piece(ChessPiece.KNIGHT, ChessColor.BLACK,1);
		pedine[0][2] = new Piece(ChessPiece.BISHOP,ChessColor.BLACK,1);
		pedine[0][3] = new Piece(ChessPiece.QUEEN, ChessColor.BLACK,1);
		pedine[0][4] = new Piece(ChessPiece.KING, ChessColor.BLACK,1);
		pedine[0][5] = new Piece(ChessPiece.BISHOP, ChessColor.BLACK,2);
		pedine[0][6] = new Piece(ChessPiece.KNIGHT, ChessColor.BLACK,2);
		pedine[0][7] = new Piece(ChessPiece.ROOK, ChessColor.BLACK,2);
		for (int i = 0; i < 8; i++) {
			pedine[1][i] = new Piece(ChessPiece.PAWN, ChessColor.BLACK,i+1);
			pedine[6][i] = new Piece(ChessPiece.PAWN, ChessColor.WHITE,i+1);
			pedine[2][i] = null;
			pedine[3][i] = null;
			pedine[4][i] = null;
			pedine[5][i] = null;
		}
		pedine[7][0] = new Piece(ChessPiece.ROOK,  ChessColor.WHITE,1);
		pedine[7][1] = new Piece(ChessPiece.KNIGHT,  ChessColor.WHITE,1);
		pedine[7][2] = new Piece(ChessPiece.BISHOP,  ChessColor.WHITE,1);
		pedine[7][3] = new Piece(ChessPiece.QUEEN,  ChessColor.WHITE,1);
		pedine[7][4] = new Piece(ChessPiece.KING,  ChessColor.WHITE,1);
		pedine[7][5] = new Piece(ChessPiece.BISHOP,  ChessColor.WHITE,2);
		pedine[7][6] = new Piece(ChessPiece.KNIGHT,  ChessColor.WHITE,2);
		pedine[7][7] = new Piece(ChessPiece.ROOK,  ChessColor.WHITE,2); 


		return pedine;	
	}
	
	@Override
	public boolean firstClick(int firstX, int firstY, Configuration configuration){
		return configuration.at(firstX, firstY)==null || turn !=configuration.at(firstX, firstY).getColor();
	}

	@Override
	public boolean secondClick(int firstX, int firstY, int secondX, int secondY, Configuration configuration){
		
		if(configuration.at(secondX,secondY)!=null)
			if (configuration.at(secondX,secondY).getColor()==configuration.at(firstX,firstY).getColor())
				return false;
		
		switch (configuration.at(firstX,firstY).getType()){
		case PAWN:
			return pawnCanMove(firstX, firstY, secondX, secondY, configuration);
		case ROOK:
			return rookCanMove(firstX,firstY,secondX,secondY,configuration, true);
		case BISHOP:
			return bishopCanMove(firstX,firstY,secondX,secondY,configuration, true);
		case KNIGHT:
			return knightCanMove(firstX,firstY,secondX,secondY,configuration, true);
		case QUEEN:
			return queenCanMove(firstX,firstY,secondX,secondY,configuration, true);
		case KING:
			return kingCanMove(firstX,firstY,secondX,secondY,configuration, true,false);
		default:
			break;
		}
		return false;

	}
	
	@Override
	public boolean pawnCanEat(int firstX, int firstY, int secondX, int secondY, Configuration configuration) {
	
		if (configuration.at(firstX,firstY).getColor()==ChessColor.WHITE){

			return secondX==(firstX-1) && (secondY==(firstY+1) || secondY==(firstY-1));
		}else{
			if (secondX==(firstX+1) && configuration.at(secondX,secondY)==null && secondY==firstY){
				return true;
			}
			return secondX==(firstX+1) && (secondY==(firstY+1) || secondY==(firstY-1)) && configuration.at(secondX,secondY)!=null;
		}		
	}

	@Override
	public boolean queenCanMove(int firstX, int firstY, int secondX, int secondY,Configuration configuration, boolean type) {
		
		int spostamentoX;
		int spostamentoY;
		
		if(firstY == secondY || firstX == secondX){
			int spostamento;
			if(firstY<secondY){
				spostamento = firstY+1;
				while(spostamento!=secondY){
					if (configuration.at(secondX,spostamento)!=null)
						return false;
					spostamento++; 
				}
				if(type)
					return tryToMove(firstX, firstY, secondX,
							secondY,configuration,false);
				return true;
			}else if(firstY>secondY){
				spostamento = firstY-1;
				while(spostamento!=secondY){
					if (configuration.at(secondX,spostamento)!=null)
						return false;
					spostamento--;
				}if(type)
					return tryToMove(firstX, firstY, secondX,
							secondY, configuration,false);
				return true;
			}
			else if(firstX<secondX){
				spostamento = firstX+1;
				while(spostamento!=secondX){
					if (configuration.at(spostamento,secondY)!=null)
						return false;
					spostamento++;
				}
				if(type)
					return tryToMove(firstX, firstY, secondX,
							secondY, configuration,false);
				return true;
			}
			else if(firstX>secondX){
				spostamento = firstX-1;
				while(spostamento!=secondX){
					if (configuration.at(spostamento,secondY)!=null)
						return false;
					spostamento--;
				}
				if(type)
					return tryToMove(firstX, firstY, secondX,
							secondY,configuration,false);
				return true;
			}
		}
		else if(firstY<secondY && firstX<secondX){
			if ((firstX-firstY) != (secondX - secondY))
				return false;
			spostamentoX = firstX+1;
			spostamentoY = firstY+1;
			while(spostamentoY!=secondY || spostamentoX!=secondX){
				if (configuration.at(spostamentoX,spostamentoY)!=null)
					return false;
				spostamentoX++;
				spostamentoY++;
			}
			if (type)
				return tryToMove(firstX, firstY, secondX,
						secondY,configuration,false);
			return true;
		}else if(firstY>secondY && firstX > secondX){
			if ((firstX-firstY) != (secondX - secondY))
				return false;
			spostamentoX = firstX-1;
			spostamentoY = firstY-1;
			while(spostamentoY!=secondY || spostamentoX!=secondX){
				if (configuration.at(spostamentoX,spostamentoY)!=null)
					return false;
				spostamentoX--;
				spostamentoY--;
			}
			if (type)
				return tryToMove(firstX, firstY, secondX,
						secondY,configuration,false);
			return true;
		}
		else if(firstX<secondX && firstY > secondY){
			if((secondY+secondX)!=(firstY+firstX))
				return false;
			spostamentoX = firstX+1;
			spostamentoY = firstY-1;
			while(spostamentoY!=secondY || spostamentoX!=secondX){
				if (configuration.at(spostamentoX,spostamentoY)!=null)
					return false;
				spostamentoX++;
				spostamentoY--;
			}
			if(type)
				return tryToMove(firstX, firstY, secondX,
						secondY,configuration,false);
			return true;
		}
		else if(firstX>secondX && firstY<secondY){
			if((secondX+secondY)!=(firstX+firstY))
				return false;
			spostamentoX = firstX-1;
			spostamentoY = firstY+1;
			while(spostamentoY!=secondY || spostamentoX!=secondX){
				if (configuration.at(spostamentoX,spostamentoY)!=null)
					return false;
				spostamentoX--;
				spostamentoY++;
			}
			if(type)
				return tryToMove(firstX, firstY, secondX,
						secondY,configuration,false);
			return true;
		}

		return false;

	}

	@Override
	public boolean kingCanMove(int firstX, int firstY, int secondX, int secondY, Configuration configuration, boolean type, boolean checkmateControl) {
		if((Math.abs(secondY-firstY)>1 || Math.abs(secondX-firstX)>1))
			return false;
		if (configuration.at(secondX,secondY)!=null && configuration.at(secondX,secondY).getColor()==configuration.at(firstX,firstY).getColor())
			return false;
		if(type)
			return tryToMove(firstX, firstY, secondX,
					secondY, configuration,checkmateControl);
		return true;
	}

	@Override
	public boolean knightCanMove(int firstX, int firstY, int secondX, int secondY,Configuration configuration, boolean type) {
		if(firstY==(secondY-2) && (firstX==(secondX-1) || firstX==(secondX+1))){
			if(type){
				return tryToMove(firstX, firstY, secondX,
						secondY,configuration,false);	
			}
			return true;


		}
		else if(firstX==(secondX-2) && (firstY==(secondY-1) || firstY==(secondY+1))){
			if(type){
				return tryToMove(firstX, firstY, secondX,
						secondY, configuration,false);
			}
			return true;
		}

		else if (firstX ==(secondX+2) && (firstY==(secondY+1) || firstY==(secondY-1))){
			if(type){
				return tryToMove(firstX, firstY, secondX,
						secondY, configuration,false);
			}
			return true;
		}

		else if(firstY==(secondY+2) && (firstX==(secondX-1) || firstX==(secondX+1))){
			if(type)
				return tryToMove(firstX, firstY, secondX,
						secondY, configuration,false);			
			return true;
		}
		return false;
	}

	@Override
	public boolean bishopCanMove(int firstX, int firstY, int secondX, int secondY, Configuration configuration, boolean type) {
		
		int spostamentoX;
		int spostamentoY;
	
		if(firstY == secondY || firstX == secondX)
			return false;
		if(firstY<secondY && firstX<secondX){
			if ((firstX-firstY) != (secondX - secondY))
				return false;
			spostamentoX = firstX+1;
			spostamentoY = firstY+1;
			while(spostamentoY!=secondY || spostamentoX!=secondX){
				if (configuration.at(spostamentoX,spostamentoY)!=null)
					return false;
				spostamentoX++;
				spostamentoY++;
			}
			if (type)
				return tryToMove(firstX, firstY, secondX,
						secondY,configuration,false);
			return true;
		}else if(firstY>secondY && firstX > secondX){
			if ((firstX-firstY) != (secondX - secondY))
				return false;
			spostamentoX = firstX-1;
			spostamentoY = firstY-1;
			while(spostamentoY!=secondY || spostamentoX!=secondX){
				if (configuration.at(spostamentoX,spostamentoY)!=null)
					return false;
				spostamentoX--;
				spostamentoY--;
			}
			if (type)
				return tryToMove(firstX, firstY, secondX,
						secondY,configuration,false);
			return true;
		}
		else if(firstX<secondX && firstY > secondY){
			if((secondY+secondX)!=(firstY+firstX))
				return false;
			spostamentoX = firstX+1;
			spostamentoY = firstY-1;
			while(spostamentoY!=secondY || spostamentoX!=secondX){
				if (configuration.at(spostamentoX,spostamentoY)!=null)
					return false;
				spostamentoX++;
				spostamentoY--;
			}
			if(type)
				return tryToMove(firstX, firstY, secondX,
						secondY,configuration,false);
			return true;
		}
		else if(firstX>secondX && firstY<secondY){
			if((secondX+secondY)!=(firstX+firstY))
				return false;
			spostamentoX = firstX-1;
			spostamentoY = firstY+1;
			while(spostamentoY!=secondY || spostamentoX!=secondX){
				if (configuration.at(spostamentoX,spostamentoY)!=null)
					return false;
				spostamentoX--;
				spostamentoY++;
			}
			if(type)
				return tryToMove(firstX, firstY, secondX,
						secondY, configuration,false);
			return true;
		}
		return false;


	}

	@Override
	public boolean rookCanMove(int firstX, int firstY, int secondX, int secondY, Configuration configuration, boolean type) {
		int spostamento;
		
		if(firstY != secondY && firstX != secondX)
			return false;
		if(firstY<secondY){
			spostamento = firstY+1;
			while(spostamento!=secondY){
				if (configuration.at(secondX,spostamento)!=null)
					return false;
				spostamento++;
			}
			if(type)
				return tryToMove(firstX, firstY, secondX,
						secondY, configuration,false);
			return true;
		}else if(firstY>secondY){
			spostamento = firstY-1;
			while(spostamento!=secondY){
				if (configuration.at(secondX,spostamento)!=null)
					return false;
				spostamento--;
			}if(type)
				return tryToMove(firstX, firstY, secondX,
						secondY, configuration,false);
			return true;
		}
		else if(firstX<secondX){
			spostamento = firstX+1;
			while(spostamento!=secondX){
				if (configuration.at(spostamento,secondY)!=null)
					return false;
				spostamento++;
			}
			if(type)
				return tryToMove(firstX, firstY, secondX,
						secondY, configuration,false);
			return true;
		}
		else if(firstX>secondX){
			spostamento = firstX-1;
			while(spostamento!=secondX){
				if (configuration.at(spostamento,secondY)!=null)
					return false;
				spostamento--;
			}
			if(type)
				return tryToMove(firstX, firstY, secondX,
						secondY, configuration,false);
			return true;
		}



		return false;


	}

	@Override
	public boolean pawnCanMove(int firstX, int firstY, int secondX, int secondY, Configuration configuration) {
		
		if (turn == ChessColor.WHITE){
			if (secondX==(firstX-1) && configuration.at(secondX,secondY)==null && secondY==firstY){
				return tryToMove(firstX, firstY, secondX, secondY,configuration,false);
			}
			if(secondX==(firstX-1) && (secondY==(firstY+1) || secondY==(firstY-1)) && configuration.at(secondX,secondY)!=null){
				return tryToMove(firstX, firstY, secondX,
						secondY, configuration,false);
			}
			return false;
		}else{
			if (secondX==(firstX+1) && configuration.at(secondX,secondY)==null && secondY==firstY){
				return tryToMove(firstX, firstY, secondX,
						secondY, configuration,false);
			}
			if(secondX==(firstX+1) && (secondY==(firstY+1) || secondY==(firstY-1)) && configuration.at(secondX,secondY)!=null){
				return tryToMove(firstX, firstY, secondX,
						secondY,configuration,false);
			}
			return false;
		}
	}

	@Override
	public boolean tryToMove(int firstX, int firstY, int secondX, int secondY,Configuration configuration, boolean checkmateControl ){
		String check;
		Piece piece[][] = new Piece[8][8];
		
		for(int i=0; i<8;i++)
			for(int j=0; j<8; j++)
				piece[i][j]=configuration.at(i,j);
		piece[secondX][secondY]= piece[firstX][firstY];
		piece[firstX][firstY]=null;

		if(piece[secondX][secondY]!= null &&piece[secondX][secondY].getType()==ChessPiece.KING){
			if(piece[secondX][secondY].getColor()==ChessColor.WHITE){
				int[] newWhiteKing = new int[]{secondX,secondY};
				Configuration newConfiguration = new ChessConfiguration(piece,newWhiteKing,configuration.getKing(ChessColor.BLACK));
				check = check(newConfiguration,checkmateControl);
				
			}else{
				int[] newBlackKing = new int[]{secondX,secondY};
				Configuration newConfiguration = new ChessConfiguration(piece,configuration.getKing(ChessColor.WHITE),newBlackKing);

				check = check(newConfiguration,checkmateControl);
			}

		}
		else{
			Configuration newConfiguration = new ChessConfiguration(piece,configuration.getKing(ChessColor.WHITE),configuration.getKing(ChessColor.BLACK));
			check = check(newConfiguration,checkmateControl);
		}
			

		if(check.contains("White") && turn==ChessColor.WHITE){
			return false;
		}
			

		else if(check.contains("Black") && turn==ChessColor.BLACK){
			return false;
		}
			
		else{
			if (!checkmateControl)
				turn=turn.next();

		}
		return true;
	}

	@Override
	public String check(Configuration configuration, boolean checkmateControl) {
		
		int[] whiteKing = configuration.getKing(ChessColor.WHITE);
		int[] blackKing = configuration.getKing(ChessColor.BLACK);
		boolean resultWhite = false;
		boolean resultBlack = false;
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if(configuration.at(i,j)!=null){
					if(configuration.at(i,j).getColor()!=ChessColor.WHITE){
						if(checkmateControl)
							resultWhite = (canBeEat(i,j,whiteKing[0],whiteKing[1],configuration) || resultWhite);
						else
							resultWhite = (findCheck(i,j,whiteKing[0],whiteKing[1],configuration) || resultWhite );
					}
					if(configuration.at(i,j).getColor()!=ChessColor.BLACK){
						if(checkmateControl)
							resultBlack =(canBeEat(i,j,blackKing[0],blackKing[1],configuration) || resultBlack);
						else
							resultBlack = (findCheck(i,j,blackKing[0],blackKing[1],configuration) || resultBlack);
					}
				}

			}
		}
		if (resultWhite == true && resultBlack == true){
			return "Black White";
		}
		if (resultWhite == true){
			return "White";
		}
		if (resultBlack == true){
			return "Black";
		}
		else
			return "";


	}
	
	/**
	 * Controlla se un Re si trova sotto scacco
	 * @param x			coordinata x di un pezzo avversario
	 * @param y			coordinata y di un pezzo avversario
	 * @param kingX		coordinata x del proprio Re
	 * @param kingY		coordinata y del proprio Re
	 * @param configuration		configurazione attuale
	 * */
	private boolean findCheck(int x, int y, int kingX, int kingY, Configuration configuration) {
		
		switch (configuration.at(x,y).getType()){
		case PAWN:
				if(pawnCanEat(x, y,  kingX, kingY, configuration)){
					if (configuration.at(x,y).getColor()==ChessColor.WHITE)
						whiteCheckers.put(configuration.at(x,y), new int[]{x,y});
					else
						blackCheckers.put(configuration.at(x,y), new int[]{x,y});
					return true;
				}
				return false;
		case ROOK:
			if(rookCanMove(x,y, kingX,kingY,configuration, false)){
				if (configuration.at(x,y).getColor()==ChessColor.WHITE)
					whiteCheckers.put(configuration.at(x,y), new int[]{x,y});
				else
					blackCheckers.put(configuration.at(x,y), new int[]{x,y});
				return true;
			}
			return false;
		case BISHOP:
			if(bishopCanMove(x,y, kingX,kingY,configuration, false)){
				if (configuration.at(x,y).getColor()==ChessColor.WHITE)
					whiteCheckers.put(configuration.at(x,y), new int[]{x,y});
				else
					blackCheckers.put(configuration.at(x,y), new int[]{x,y});
				return true;
			}
			return false;
		case KNIGHT:
			if(knightCanMove(x,y, kingX,kingY,configuration, false)){
				if (configuration.at(x,y).getColor()==ChessColor.WHITE)
					whiteCheckers.put(configuration.at(x,y), new int[]{x,y});
				else
					blackCheckers.put(configuration.at(x,y), new int[]{x,y});
				return true;
			}
			return false;
		case QUEEN:
			if(queenCanMove(x,y, kingX,kingY,configuration, false)){
				if (configuration.at(x,y).getColor()==ChessColor.WHITE)
					whiteCheckers.put(configuration.at(x,y), new int[]{x,y});
				else
					blackCheckers.put(configuration.at(x,y), new int[]{x,y});
				return true;
			}
			return false;
		case KING:
			if(kingCanMove(x,y, kingX,kingY, configuration, false,false)){
				if (configuration.at(x,y).getColor()==ChessColor.WHITE)
					whiteCheckers.put(configuration.at(x,y), new int[]{x,y});
				else
					blackCheckers.put(configuration.at(x,y), new int[]{x,y});
				return true;
			}
			return false;
		default:
			break;
		}

		return false;
	}
	
	@Override
	public ChessColor checkmate(Configuration configuration, ChessColor color){
		int[] king = configuration.getKing(color);
		HashMap<Piece,int[]> checkers;
		if (color==ChessColor.BLACK)
			checkers=whiteCheckers;
		else
			checkers=blackCheckers;

		if (checkers.size()>=1){
			for(int i=(king[0]-1); i<=(king[0]+1); i++){
				for(int j=(king[1]-1); j<=(king[1]+1); j++){
					if(j>=0 && i>=0 && j<8 && i<8){
						if(kingCanMove(king[0], king[1], i, j, configuration, true, true))
							return (ChessColor)null;
					}
				}
			}
		}
		if(checkers.size()==1){
			for(Piece pezzo: checkers.keySet()){
				
				int[] position = checkers.get(pezzo);

				if(canAllMove(color, configuration, position))
					return (ChessColor)null;

				switch(pezzo.getType()){
				case ROOK:
					if(position[0]==king[0]){
						if(position[1]>king[1]){
							while(position[1]!=king[1]){
								if(canAllMove(color, configuration, position))
									return (ChessColor)null;
								position[1]++;
							}
							return color;
						}else{
							while(position[1]!=king[1]){
								if(canAllMove(color, configuration, position))
									return (ChessColor)null;
								position[1]--;
							}
							return color;
						}

					}else{
						if(position[0]>king[0]){
							while(position[0]!=king[0]){
								if(canAllMove(color, configuration, position))
									return (ChessColor)null;
								position[0]--;
							}
							return color;
						}else{
							while(position[0]!=king[0]){
								if(canAllMove(color, configuration, position))
									return (ChessColor)null;
								position[0]++;
							}
							return color;
						}
					}
				case BISHOP:
					if(position[1]<king[1] && position[0]<king[0]){
						while(position[1]!=king[1] || position[0]!=king[0]){
							if(canAllMove(color, configuration, position))
								return (ChessColor)null;

							position[1]++;
							position[0]++;
						}
						return color;

					}else if(position[1] > king[1] && position[0] > king[0]){


						while(position[1]!=king[1] || position[0]!=king[0]){
							if(canAllMove(color, configuration, position))
								return (ChessColor)null;
							position[0]--;
							position[1]--;
						}
						return color;

					}
					else if(position[0]<king[0] && position[1] > king[1]){
						while(position[1]!=king[1] || position[0]!=king[0]){
							if(canAllMove(color, configuration, position))
								return (ChessColor)null;
							position[0]++;
							position[1]--;
						}
						return color;
					}
					else if(position[0]>king[0] && position[1]<king[1]){

						while(position[1]!=king[1] || position[0]!=king[0]){
							if(canAllMove(color, configuration, position))
								return (ChessColor)null;
							position[0]--;
							position[1]++;
						}
						return color;
					}
				case QUEEN:
					

					if(position[1] == king[1] || position[0] == king[0]){
						if(position[1]<king[1]){
							while(position[1]!=king[1]){
								if(canAllMove(color, configuration, position))
									return (ChessColor)null;
								position[1]++;
							}
							return color;
						}else if(position[1]>king[1]){
							while(position[1]!=king[1]){
								if(canAllMove(color, configuration, position))
									return (ChessColor)null;
								position[1]--;
							}
							return color;
						}
						else if(position[0]<king[0]){

							while(position[0]!=king[0]){
								if(canAllMove(color, configuration, position))
									return (ChessColor)null;
								position[0]++;
							}
							return color;
						}
						else if(position[0]>king[0]){

							while(position[0]!=king[0]){
								if(canAllMove(color, configuration, position))
									return (ChessColor)null;
								position[0]--;
							}
							return color;
						}
					}
					else if(position[1]<king[1] && position[0]<king[0]){


						while(position[1]!=king[1] || position[0]!=king[0]){
							if(canAllMove(color, configuration, position))
								return (ChessColor)null;
							position[0]++;
							position[1]++;
						}
						return color;

					}else if(position[1]>king[1] && position[0] > king[0]){


						while(position[1]!=king[1] || position[0]!=king[0]){
							if(canAllMove(color, configuration, position))
								return (ChessColor)null;
							position[0]--;
							position[1]--;
						}
						return color;

					}
					else if(position[0]<king[0] && position[1] > king[1]){
						while(position[1]!=king[1] || position[0]!=king[0]){
							if(canAllMove(color, configuration, position))
								return (ChessColor)null;
							position[0]++;
							position[1]--;
						}
						return color;
					}
					else if(position[0]>king[0] && position[1]<king[1]){

						while(position[1]!=king[1] || position[0]!=king[0]){
							if(canAllMove(color, configuration, position))
								return (ChessColor)null;
							position[0]--;
							position[1]++;
						}
						return color;
					}

					return color;

				default:
					return color;
				}
			}
		}
		return color;
	}

	/**
	 * Controlla se un pezzo che sta mettendo sotto scacco il proprio Re puo' essere catturato 
	 * @param x		coordinata x del pezzo che vuole catturare
	 * @param y		coordinata y del pezzo che vuole catturare
	 * @param pieceX	coordinata x del pezzo che mette sotto scacco il Re
	 * @param pieceY	coordinata y del pezzo che mette sotto scacco il Re
	 * @param configuration		configurazione attuale
	 * @return Ritorna true se il pezzo puo' essere catturato, altrimenti false
	 * */
	private boolean canBeEat(int x, int y, int pieceX, int pieceY, Configuration configuration) {
	
		switch (configuration.at(x,y).getType()){
		case PAWN:
				if(( configuration.at(pieceX,pieceY)!=null && pawnCanEat(x, y,  pieceX, pieceY, configuration))|| // il pedone puo' catturare il pezzo
						pawnCanProtect(x,y,pieceX,pieceY,configuration)){ // o mettersi tra lui e il Re
					return true;
				}
				return false;
		case ROOK:
			if(rookCanMove(x,y, pieceX,pieceY,configuration, false)){
				return true;
			}

			return false;
		case BISHOP:
			if(bishopCanMove(x,y, pieceX,pieceY,configuration, false)){
				return true;
			}

			return false;
		case KNIGHT:
			if(knightCanMove(x,y, pieceX,pieceY,configuration, false)){
					return true;
			}
			return false;
		case QUEEN:
			if(queenCanMove(x,y, pieceX,pieceY,configuration, false)){

				return true;
			}
			return false;
		case KING:
			if(kingCanMove(x,y,pieceX,pieceY,configuration,true,true)){
				return true;
			}
				return false;

		default:
			break;
		}

		return false;
	}

	/**
	 * Controlla se c'e' almeno uno dei propri pezzi che può catturarne uno avversario che mette sotto scacco
	 * il proprio Re
	 * @param color		il colore dei pezzi che vogliono catturare l'avversario
	 * @param configuration		la configurazione corrente
	 * @param position		posizione del pezzo che mette sotto scacco il Re
	 * @return Ritorna true se almeno un pezzo puo' togliere il Re dallo scacco, altrimenti ritorna false
	 * */
	private boolean canAllMove(ChessColor color, Configuration configuration, int[] position){
		
		boolean result = false;
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
					if(configuration.at(i,j)!=null && configuration.at(i,j).getColor()==color){
						result = (canBeEat(i,j,position[0],position[1],configuration) || result );
						if (result==true)
							return result;
					}				
			}
		}
			return false;
	}

	/**
	 * Elimina gli elementi presenti nelle HashMap dedicate a indicare i pezzi che mettono sotto scacco
	 * il Re avversario
	 * */
	public void cleanCheckers() {
		blackCheckers.clear();
		whiteCheckers.clear();
		
	}

	/**
	 * Indica se un pedone puo' proteggere il proprio Re
	 * @param firstX
	 * @param firstY
	 * @param secondX
	 * @param secondY
	 * @param configuration
	 * @return Ritorna true se il pedone puo' proteggere il Re
	 * */
	private boolean pawnCanProtect(int firstX, int firstY, int secondX, int secondY, Configuration configuration) {
		
		if (turn == ChessColor.WHITE){
			if (secondX==(firstX-1) && configuration.at(secondX,secondY)==null && secondY==firstY){
				return true;
			}
			return secondX==(firstX-1) && (secondY==(firstY+1) || secondY==(firstY-1)) && configuration.at(secondX,secondY)!=null;
		}else{
			if (secondX==(firstX+1) && configuration.at(secondX,secondY)==null && secondY==firstY){
				return true;
			}
			return secondX==(firstX+1) && (secondY==(firstY+1) || secondY==(firstY-1)) && configuration.at(secondX,secondY)!=null;
		}
	}

}
