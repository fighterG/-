package server.ui;

import javax.swing.JPanel;

import server.tool.MessageManager;

public class MessagePanel extends JPanel {
	/**
	 * ��Ϣ��ʾ��
	 */
	private static final long serialVersionUID = 1L;
         //��������������Ϣ��ʾ����ʹ��messagemanager�е�getMessageArea()����
	public MessagePanel(){
		this.add(MessageManager.getInstance().getMessageArea());
	}
}
