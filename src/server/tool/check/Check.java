package server.tool.check;
import java.util.*;

public class Check {
	/**
	 * 检查胜负
	 * 
	 * */
	public boolean check(int x ,int y ,int id,int[][] chessBoard){
		
		int winPoint[] = new int [4];
		
		winPoint[0] = CheckX.checkX(x ,y ,id, chessBoard);
		winPoint[1] = CheckY.checkY(x ,y ,id, chessBoard);
		winPoint[2] = CheckM.checkM(x ,y ,id, chessBoard);
		winPoint[3] = CheckN.checkN(x ,y ,id, chessBoard);
		//把数组中的值按大小进行排序，winpoint[3]的值为最大
		Arrays.sort(winPoint);
		//对winpoint[3]进行判断，若它大于4就表明在四个方向上已经有五子连成了
		if(winPoint[3] > 4){
			return true;
		}
		else{
			return false;
		}		
	}
}
