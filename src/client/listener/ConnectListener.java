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
	 *监听登陆按钮 
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(!Data.connected){
			//登陆服务器
			new Connect().connect();
			
			if(Data.connected){
				//开启线程
				Thread t = new Thread(new Receive());
				t.start();

				//初始化（完成后更新列表）获取文本框中的名字
				String name = GameFrame.getInstance().getGamePanel().getNameIn().getText();
				IOManager.getInstance().getPs().println("INIT:" + name);
			}
		}
		else{
			MessageManager.getInstance().addMessage("已经连接上了！");
		}
	}
}
