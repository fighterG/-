package server.tool.check;

public class CheckX {
	/**
	 * 检查X轴
	 * 
	 * */
	
	public static int checkX(int line ,int row ,int id,int[][] chessBoard){
		
		int check = 0;
		int checkLeft = 0;
		int checkRight = 0;
		//通过传进来的位置坐标进行判断，只要连成五子就算赢，所以line-i判断是否小于-1，若小于
		//-1表明此处已经没有放子的地方了，这个方向已经走完了。如果大于-1就继续往下判断
		//通过id判断当前是那一种棋，若是就加一
		//判断两个方向的所有一个颜色的棋子，然后相加，这就是一给定的一点坐标判断x轴方向所有相连的
		//同色棋子个数
		for(int i = 0; i < 5; i++){
			
			if(line - i > -1){
			
				if( chessBoard[line-i][row] == id){
					
					checkLeft++;
				}
				else{
					
					break;
				}
			}
		
		}
		
		for(int i = 1; i < 5; i++){
			
			if(line + i < 15 ){
				
				if(chessBoard[line+i][row] == id){
					
					checkRight++;
				}
				else{
					break;
				}
			}
			
		}
		
		check = checkLeft + checkRight;
		return (check);
	}
}
