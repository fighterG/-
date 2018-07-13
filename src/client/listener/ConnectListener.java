package client.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import client.data.Data;
import client.manager.IOManager;
import client.manager.MessageManager;
import client.net.Connect;
import client.net.Receive;
import client.ui.GameFrame;


public class ConnectListener implements ActionListener{
	
	/**
	 *������½��ť 
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(!Data.connected){
			//��½������
			new Connect().connect();
			
			if(Data.connected){
				//�����߳�
				Thread t = new Thread(new Receive());
				t.start();

				//��ʼ������ɺ�����б���ȡ�ı����е�����
				String name = GameFrame.getInstance().getGamePanel().getNameIn().getText();
				IOManager.getInstance().getPs().println("INIT:" + name);
			}
		}
		else{
			MessageManager.getInstance().addMessage("�Ѿ��������ˣ�");
		}
	}
}
