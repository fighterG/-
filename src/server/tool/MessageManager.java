package server.tool;

import javax.swing.JTextArea;

public class MessageManager {
	
	/**
	 * ��Ϣ����������������Ϣ��
	 */
	private static MessageManager instance = null;
	
	private JTextArea messageArea = null;
	
	
	private MessageManager(){
		
	}
	public static MessageManager getInstance(){
		if(instance == null){
			instance = new MessageManager();
		}
		return instance;
	}
	
	public JTextArea getMessageArea(){
		if(messageArea == null){
			//�������ָ���ı����������������µ� TextArea��
			messageArea = new JTextArea("",10,33);
		}
		return messageArea;
	}
	
//	�����Ϣ
	public void addMessage(String s){
		messageArea.append(s +"\n");
	}
}

