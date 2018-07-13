package client.listener;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import client.data.Data;
import client.manager.MessageManager;
import client.net.PlayChess;
import client.ui.ChessBoardCanvas;


public class MapListener extends MouseAdapter {
	/**
	 * ��������
	 */
	
	@Override
	//������갴��
	public void mousePressed(MouseEvent e) {
		//��������������¼��Ķ���
		ChessBoardCanvas canvas = (ChessBoardCanvas)e.getSource();
		
           //�ж��Ƿ��½
		if(Data.connected){
            //�ж��Ƿ�ѡ���˶���
			if(Data.oppoId != 0){
				//�ж��Ƿ��Ѿ�׼����
				if(Data.ready == true){
					//�ж��Ƿ��Ѿ���ʼ
					if(Data.started){
						//�ж��Ƿ��ֵ��Լ�����
						if(!Data.wait){
							if(Data.turn == Data.myChess){
                             //	�ж��Ƿ������̷�Χ��
								if(e.getX() < canvas.getMapWidth() - 6 && e.getY() < canvas.getHeight() - 7 ){
									
									int chessX;
									int chessY;
									//�����ӷ��ڽ������
									chessX = e.getX() / 35;
									chessY = e.getY()/ 35;
									
                                    //�жϴ˴��Ƿ���������
									if(Data.chessBoard[chessX][chessY] == 0){
										
										new PlayChess().play(chessX, chessY , Data.myChess);

									}
									else{
										
										MessageManager.getInstance().addMessage("�������������");
									}
								}
								

							}
                        //	��Ϊ�Է����ӣ�����ʾ�Ҳ�������
	 						else{

								if(Data.turn == Data.oppoChess){
									
									MessageManager.getInstance().addMessage("û�ֵ����¡�����");
								}
								else{
//									����Ϸ�Ѿ�����
									if(Data.turn == 0){
										MessageManager.getInstance().addMessage("�Ѿ���������");
									}
								}

							}
						}
						//�����Ѱ��¿�ʼ����ʾ�ȴ��Է����¿�ʼ��ť
						else{
							MessageManager.getInstance().addMessage("�ȴ��Է��ظ�������");
						}
					}
					//��û��׼���������ѵȴ��Է�׼��
					else{
						MessageManager.getInstance().addMessage("�ȴ��Է�׼��������");
					}
				}
				//��δ��ʼ����ʾ�ȵ����ʼ
				else{
					MessageManager.getInstance().addMessage("�ȵ������ʼ���ɣ�");
				}
			}
			//��û��ѡ���������ʾ��ѡ�����
			else{
				MessageManager.getInstance().addMessage("ѡ�����ְɣ�");
			}
		}
		
		//��δ��½����ʾ�ȵ�½
		else{
			
			MessageManager.getInstance().addMessage("���ȵ�½��");
		}
	}
      //�����������¼�  
	@Override
	public void mouseEntered(MouseEvent e3) {
		
		ChessBoardCanvas canvas = (ChessBoardCanvas)e3.getSource();
		
		if(!Data.wait){
			//���ֵ��Լ�����ͽ��������Ϊָ�����ͼ������Ϊ���͹�꣩
			canvas.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		    //�������Լ����������Ϊ�ȴ��������
		else{
			canvas.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		}
		
		
	}
	//��������뿪�¼���������뿪����ΪĬ�Ϲ������
	@Override
	public void mouseExited(MouseEvent e4) {
		ChessBoardCanvas canvas = (ChessBoardCanvas)e4.getSource();
		canvas.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
	}
	
	
}
