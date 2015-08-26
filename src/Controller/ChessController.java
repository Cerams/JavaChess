package Controller;

import Model.ChessConfiguration;
import Model.Configuration;
import Model.Model;
import Pezzi.ChessColor;
import Pezzi.ChessPiece;
import Pezzi.Piece;
import View.View;

public final class ChessController implements Controller {
	
	private final Model model;
	private boolean taken = false;
	private int firstX;
	private int firstY;
	private final ChessRules rules;
	

	public ChessController(Model model, View View) {
		this.model = model;		
		rules = new ChessRules();
		model.setController(this);
	}

	@Override
	public void onClick(int x, int y) {
		Piece[][] piece = new Piece[8][8];
		Configuration configuration;
		//non e' stato fatto alcun click
		if (taken==false){
			if (rules.firstClick(x, y, model.getConfiguration())==false){
				firstX=x;
				firstY=y;
				taken=true;
			}
			else
				model.wrongPath();
		}else{
			if(rules.secondClick(firstX, firstY, x, y, model.getConfiguration())){
				for(int i=0;i<8;i++)
                	for(int j=0; j<8; j++)
                		piece[i][j] = model.getConfiguration().at(i,j);
                	
				piece[x][y] = model.at(firstX, firstY);
				piece[firstX][firstY]=null;
				
				if (piece[x][y].getType()==ChessPiece.KING){
					if(piece[x][y].getColor()==ChessColor.WHITE){
						configuration = new ChessConfiguration(piece,new int[]{x,y},model.getKing(ChessColor.BLACK));
					}
					else{
						configuration =new ChessConfiguration(piece,model.getKing(ChessColor.WHITE),new int[]{x,y});
					}
				}
				else{
					if(piece[x][y].getType()==ChessPiece.PAWN){
						if (piece[x][y].getColor()==ChessColor.WHITE && x==0){
							int scelta = model.trasform();
							int max=0;
							switch(scelta){
							case 0:
								for(int i=0; i<8; i++)
									for(int j=0; j<8; j++)
										if(piece[i][j]!=null && piece[i][j].getType()==ChessPiece.QUEEN && piece[i][j].getColor()==piece[x][y].getColor())
											if (piece[i][j].getNumber()>max)
												max=piece[i][j].getNumber();
								piece[x][y]= new Piece(ChessPiece.QUEEN,piece[x][y].getColor(),max);
								break;
							case 1:
								for(int i=0; i<8; i++)
									for(int j=0; j<8; j++)
										if(piece[i][j]!=null && piece[i][j].getType()==ChessPiece.ROOK && piece[i][j].getColor()==piece[x][y].getColor())
											if (piece[i][j].getNumber()>max)
												max=piece[i][j].getNumber();

								piece[x][y]= new Piece(ChessPiece.ROOK,piece[x][y].getColor(),max);
								break;
							case 2:
								for(int i=0; i<8; i++)
									for(int j=0; j<8; j++)
										if(piece[i][j]!=null && piece[i][j].getType()==ChessPiece.KNIGHT && piece[i][j].getColor()==piece[x][y].getColor())
											if (piece[i][j].getNumber()>max)
												max=piece[i][j].getNumber();
								piece[x][y]= new Piece(ChessPiece.KNIGHT,piece[x][y].getColor(),max);
								break;
							case 3:
								for(int i=0; i<8; i++)
									for(int j=0; j<8; j++)
										if(piece[i][j]!=null && piece[i][j].getType()==ChessPiece.BISHOP && piece[i][j].getColor()==piece[x][y].getColor())
											if (piece[i][j].getNumber()>max)
												max=piece[i][j].getNumber();
								piece[x][y]= new Piece(ChessPiece.BISHOP,piece[x][y].getColor(),max);
								break;
							default:
							}

						}
						else if (piece[x][y].getColor()==ChessColor.BLACK && x==7){
							int scelta = model.trasform();
							int max=0;
							switch(scelta){
							case 0:
								for(int i=0; i<8; i++)
									for(int j=0; j<8; j++)
										if(piece[i][j]!=null && piece[i][j].getType()==ChessPiece.QUEEN && piece[i][j].getColor()==piece[x][y].getColor())
											if (piece[i][j].getNumber()>max)
												max=piece[i][j].getNumber();
								piece[x][y]= new Piece(ChessPiece.QUEEN,piece[x][y].getColor(),max);
								break;
							case 1:
								for(int i=0; i<8; i++)
									for(int j=0; j<8; j++)
										if(piece[i][j]!=null && piece[i][j].getType()==ChessPiece.ROOK && piece[i][j].getColor()==piece[x][y].getColor())
											if (piece[i][j].getNumber()>max)
												max=piece[i][j].getNumber();

								piece[x][y]= new Piece(ChessPiece.ROOK,piece[x][y].getColor(),max);
								break;
							case 2:
								for(int i=0; i<8; i++)
									for(int j=0; j<8; j++)
										if(piece[i][j]!=null && piece[i][j].getType()==ChessPiece.KNIGHT && piece[i][j].getColor()==piece[x][y].getColor())
											if (piece[i][j].getNumber()>max)
												max=piece[i][j].getNumber();
								piece[x][y]= new Piece(ChessPiece.KNIGHT,piece[x][y].getColor(),max);
								break;
							case 3:
								for(int i=0; i<8; i++)
									for(int j=0; j<8; j++)
										if(piece[i][j]!=null && piece[i][j].getType()==ChessPiece.BISHOP && piece[i][j].getColor()==piece[x][y].getColor())
											if (piece[i][j].getNumber()>max)
												max=piece[i][j].getNumber();
								piece[x][y]= new Piece(ChessPiece.BISHOP,piece[x][y].getColor(),max);
								break;
							default:
							}
						}
					}
					configuration =new ChessConfiguration(piece,model.getConfiguration().getKing(ChessColor.WHITE),model.getConfiguration().getKing(ChessColor.BLACK));		
				}


					model.setConfiguration(configuration);
				

			}
			
			taken = false;

			model.wrongPath();
			rules.cleanCheckers();
			String check = check();
			if (check.contains("White")){
				model.paintCheck(ChessColor.WHITE);
				checkmate(model.getConfiguration(), ChessColor.WHITE);
			}
			if (check.contains("Black")){
				model.paintCheck(ChessColor.BLACK);
				checkmate(model.getConfiguration(), ChessColor.BLACK);	
			}
		
		}
		
		

	}


	@Override
	public String check(){
		return rules.check(model.getConfiguration(),false);
	}

	@Override
	public void checkmate(Configuration configuration, ChessColor color) {
	ChessColor colorCheckmate = rules.checkmate(configuration,color);
		if(colorCheckmate!=null)
			model.checkmate(colorCheckmate);
		
	}

	@Override
	public Piece[][] onNewGame() {
		return rules.startConfiguration();
		
	}

}
