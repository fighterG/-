package client.manager;

import java.awt.TextArea;

public class MessageManager {
	/**
	 * 管理消息
	 */
	private static MessageManager instance = null;
	private TextArea messageArea = null;
	//构造函数
	private MessageManager(){
		
	}
	// getInstance()的功能就是通过构造函数对instance对象进行初始化
	public static MessageManager getInstance(){
		
		if(instance == null){
			
			instance = new MessageManager();
		}
		return instance;
	}
	
	//设置文本框中显示的内容（空）以及滚动条的方向
	public TextArea getMessageArea(){
		
		if(messageArea == null){
			
			messageArea = new TextArea("",16,28,TextArea.SCROLLBARS_VERTICAL_ONLY);
		}
		return messageArea;
	}
	
    //通过append方法把输入框中的输入信息追加到文本显示框中
	public void addMessage(String message){
		
		getMessageArea().append(message + "\n\n" );
	}
	
}
