package client.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import client.data.Data;
import client.manager.IOManager;
import client.manager.MessageManager;
import client.ui.GameFrame;
import client.ui.ChessBoardCanvas;


public class QuitListener implements ActionListener{
	/**
	 * �����˳�����
	 * */

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(Data.oppoId != 0){
			
			if(Data.started){
				
				int value = JOptionPane.showConfirmDialog(null,"��Ϸ��û���������ܾ������䣬��ȷ��Ҫ���ܣ�" ,"����",JOptionPane.YES_NO_OPTION);
				
				if(value == JOptionPane.YES_OPTION){
					
					JOptionPane.showMessageDialog(null, "���޳ܵ��ܵ��ˣ�");
					
					IOManager.getInstance().getPs().println("OPER:" + "ESCA:" + Data.oppoId);
				
					Data.oppoId = 0;
					Data.started = false;
					Data.ready = false;
					Data.chessBoard = new int[15][15];
					Data.last = -1;
					Data.myChess = 0;
					Data.oppoChess = 0;
					
					ChessBoardCanvas mapCanvas = GameFrame.getInstance().getGamePanel().getMapCanvas();
					mapCanvas.paintMapImage();
					mapCanvas.repaint();
					
					MessageManager.getInstance().addMessage("���������ѡ������ˣ�");
				
				}
		
				
				
			}
			
			else{
				IOManager.getInstance().getPs().println("OPER:" + "QUIT:" + Data.oppoId);
				
				Data.oppoId = 0;
				Data.started = false;
				Data.ready = false;
				Data.chessBoard = new int[15][15];
				Data.last = -1;
				Data.myChess = 0;
				Data.oppoChess = 0;
				
				ChessBoardCanvas mapCanvas = GameFrame.getInstance().getGamePanel().getMapCanvas();
				mapCanvas.paintMapImage();
				mapCanvas.repaint();
				
				MessageManager.getInstance().addMessage("���������ѡ������ˣ�");
			}
			
		}
	}

}
