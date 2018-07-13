package client.net;

import java.io.PrintStream;

import client.data.*;
import client.manager.IOManager;
import client.manager.MessageManager;
import client.ui.GameFrame;
import client.ui.ChessBoardCanvas;

public class PlayChess {
	/**
	 * ����
	 */
	
	public void play(int chessX, int chessY , int chess){
        //ʹ��ps�����������
		PrintStream ps = IOManager.getInstance().getPs();
		
		int position;
		
		position = 15 * chessY + chessX;
		
		Data.last = position;		//������һ������λ��
		//�ж��Ƿ����ҵĻغ�
		if(chess == Data.myChess){
			//���������λ�ü�¼�ڶ�δ������
			Data.chessBoard[chessX][ chessY] = Data.myChess;
			//���»��ƻ���
			ChessBoardCanvas mapCanvas = GameFrame.getInstance().getGamePanel().getMapCanvas();
			mapCanvas.paintMapImage();
			mapCanvas.repaint();
			//ͬʱ�ѻغϱ�Ϊ�Է��Ļغ�
			Data.turn = Data.oppoChess;
			//ͨ��io���������ӵ�λ��
			ps.println("PLAY:" + position);
			
			MessageManager.getInstance().addMessage("�ȴ��Է����塣��");
			
		}
		//�ж��Ƿ��ǶԷ��Ļغ�
		if(chess == Data.oppoChess){
			//�Ѷ��������λ�ü�¼�ڶ�δ������
			Data.chessBoard[chessX][ chessY] = Data.oppoChess;
			//���»��ƻ���
			ChessBoardCanvas mapCanvas = GameFrame.getInstance().getGamePanel().getMapCanvas();
			mapCanvas.paintMapImage();
			mapCanvas.repaint();
			//ͬʱ�ѻغϱ�Ϊ���ҵĻغ�
			Data.turn = Data.myChess;
			
			MessageManager.getInstance().addMessage("�������塣��");
			
		}
	}
	

}
