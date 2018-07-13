package client.net;

import java.io.*;
import java.net.*;

import client.data.*;
import client.manager.IOManager;
import client.manager.MessageManager;
import client.ui.GameFrame;

public class Connect {
	/**
	 * ��½������
	 */
	public void connect(){

		try {
			//��ȡ����Ķ˿ں�
			String portStr = GameFrame.getInstance().getGamePanel().getPort().getText();
			//��ȡ�����IP��ַ
			String ipStr = GameFrame.getInstance().getGamePanel().getIp().getText();
			//���˿ں�ת��Ϊint����
			int portValue = Integer.parseInt(portStr);
			//����socket���ӷ����
			Socket socket = new Socket(ipStr,portValue);
			
			Data.connected = true;
			
			MessageManager.getInstance().addMessage("�����������ӣ�");
			//�ı�gamebar�����ı���Ϣ
			GameFrame.getInstance().getGamePanel().getConnectInfo().setText("������״̬���ѵ�¼��");
			
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			InputStreamReader isr = new InputStreamReader(is);
			//��ȡ���������
			IOManager.getInstance().setBr(isr);
			IOManager.getInstance().setPs(os);
			//�������쳣����ʾ��Ӧ��Ϣ			
		} catch (UnknownHostException e) {
			
			MessageManager.getInstance().addMessage("�Ҳ�����������");
			
		} catch (IOException e) {
			
			MessageManager.getInstance().addMessage("���������ӳ���");
		}

	}
}
