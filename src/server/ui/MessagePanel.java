package server.ui;

import javax.swing.JPanel;

import server.tool.MessageManager;

public class MessagePanel extends JPanel {
	/**
	 * 消息显示区
	 */
	private static final long serialVersionUID = 1L;
         //创建服务器的消息显示区，使用messagemanager中的getMessageArea()方法
	public MessagePanel(){
		this.add(MessageManager.getInstance().getMessageArea());
	}
}
