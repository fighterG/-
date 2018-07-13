package client.manager;

import java.awt.TextArea;

public class MessageManager {
	/**
	 * ������Ϣ
	 */
	private static MessageManager instance = null;
	private TextArea messageArea = null;
	//���캯��
	private MessageManager(){
		
	}
	// getInstance()�Ĺ��ܾ���ͨ�����캯����instance������г�ʼ��
	public static MessageManager getInstance(){
		
		if(instance == null){
			
			instance = new MessageManager();
		}
		return instance;
	}
	
	//�����ı�������ʾ�����ݣ��գ��Լ��������ķ���
	public TextArea getMessageArea(){
		
		if(messageArea == null){
			
			messageArea = new TextArea("",16,28,TextArea.SCROLLBARS_VERTICAL_ONLY);
		}
		return messageArea;
	}
	
    //ͨ��append������������е�������Ϣ׷�ӵ��ı���ʾ����
	public void addMessage(String message){
		
		getMessageArea().append(message + "\n\n" );
	}
	
}
