package client.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import client.data.Data;
import client.manager.IOManager;
import client.manager.MessageManager;

public class StartListener implements ActionListener {
	
	/**
	 * ������ʼ��ť
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//���ж��Ƿ��½�������ж��Ƿ�ѡ�������������Ե����ʼ��ť���տ�ʼ��ť��ֵΪfalse���
		//��ʼ������Ϣ������ʾ���Ե�
		//Ȼ��ť��ֵ��Ϊtrue
		//��ʱ���ٵ����ʼ��ť����ʾ�Ѿ�׼�����ˡ�
		
		if(Data.connected){
			if(Data.oppoId != 0){
				if(Data.ready == false){
					IOManager.getInstance().getPs().println("OPER:STAR:");
					
					MessageManager.getInstance().addMessage("���Եȡ�����");
					
					Data.ready = true;
				}
				else{
					MessageManager.getInstance().addMessage("�Ѿ�׼�����ˣ�");
				}
				
			}
			else{
				
				JOptionPane.showMessageDialog(null, "��ѡ��һ�����ְɣ�");
			}
		}
		else{
			MessageManager.getInstance().addMessage("�ȵ�½�ɣ�");
		}
		
		
	}
}
