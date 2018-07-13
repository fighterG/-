package client.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.data.Data;
import client.manager.IOManager;
import client.manager.MessageManager;


public class BackListener implements ActionListener{
	/**
	 * 监听悔棋操作
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(Data.started){
			//相当于ps.println()客户端向服务器写一句话
			IOManager.getInstance().getPs().println("OPER:BACK:" + Data.myId);
			
			Data.wait = true;
			//在当前界面的消息区显示等待对方回复。
			MessageManager.getInstance().addMessage("等待对方回复。。。");
			
		}
		
	}

}
