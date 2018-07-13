package client.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.data.Data;
import client.manager.IOManager;
import client.manager.MessageManager;
import client.ui.GameFrame;
import client.ui.ChessBoardCanvas;

public class RestartListener implements ActionListener{
	/**
	 * ����������ť
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(Data.connected){
			//����Ϸ�Ѿ��������ͽ������ӣ����»������̿�ʼ��Ϸ����֮������Ϣ�����ʾ��Ϸ��δ����
			if(!Data.started){
				Data.chessBoard = new int [15][15];
				Data.turn = 0;
				Data.last = -1;
				
				
//				��������
				Data.myChess = 0;
				Data.oppoChess = 0;
				//���»�������
				ChessBoardCanvas mapCanvas = GameFrame.getInstance().getGamePanel().getMapCanvas();
				mapCanvas.paintMapImage();
				mapCanvas.repaint();
				
				IOManager.getInstance().getPs().println("OPER:REST:");
			}
			else{
				MessageManager.getInstance().addMessage("��Ϸ��û������");
			}
		}
		else{
			MessageManager.getInstance().addMessage("�ȵ�½�ɣ�");
		}
	}

}
