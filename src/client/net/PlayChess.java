package client.net;

import java.io.PrintStream;

import client.data.*;
import client.manager.IOManager;
import client.manager.MessageManager;
import client.ui.GameFrame;
import client.ui.ChessBoardCanvas;

public class PlayChess {
	/**
	 * 落棋
	 */
	
	public void play(int chessX, int chessY , int chess){
        //使用ps来发送输出流
		PrintStream ps = IOManager.getInstance().getPs();
		
		int position;
		
		position = 15 * chessY + chessX;
		
		Data.last = position;		//标记最后一个棋子位置
		//判断是否是我的回合
		if(chess == Data.myChess){
			//把我下棋的位置记录在二未数组中
			Data.chessBoard[chessX][ chessY] = Data.myChess;
			//重新绘制画板
			ChessBoardCanvas mapCanvas = GameFrame.getInstance().getGamePanel().getMapCanvas();
			mapCanvas.paintMapImage();
			mapCanvas.repaint();
			//同时把回合变为对方的回合
			Data.turn = Data.oppoChess;
			//通过io流传递棋子的位置
			ps.println("PLAY:" + position);
			
			MessageManager.getInstance().addMessage("等待对方落棋。。");
			
		}
		//判断是否是对方的回合
		if(chess == Data.oppoChess){
			//把对手下棋的位置记录在二未数组中
			Data.chessBoard[chessX][ chessY] = Data.oppoChess;
			//重新绘制画板
			ChessBoardCanvas mapCanvas = GameFrame.getInstance().getGamePanel().getMapCanvas();
			mapCanvas.paintMapImage();
			mapCanvas.repaint();
			//同时把回合变为对我的回合
			Data.turn = Data.myChess;
			
			MessageManager.getInstance().addMessage("请您落棋。。");
			
		}
	}
	

}
