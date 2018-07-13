package client.net;

import java.io.*;
import java.net.*;

import client.data.*;
import client.manager.IOManager;
import client.manager.MessageManager;
import client.ui.GameFrame;

public class Connect {
	/**
	 * 登陆服务器
	 */
	public void connect(){

		try {
			//获取输入的端口号
			String portStr = GameFrame.getInstance().getGamePanel().getPort().getText();
			//获取输入的IP地址
			String ipStr = GameFrame.getInstance().getGamePanel().getIp().getText();
			//将端口号转化为int类型
			int portValue = Integer.parseInt(portStr);
			//创建socket连接服务端
			Socket socket = new Socket(ipStr,portValue);
			
			Data.connected = true;
			
			MessageManager.getInstance().addMessage("服务器已连接！");
			//改变gamebar处的文本信息
			GameFrame.getInstance().getGamePanel().getConnectInfo().setText("服务器状态：已登录！");
			
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			InputStreamReader isr = new InputStreamReader(is);
			//获取输入输出流
			IOManager.getInstance().setBr(isr);
			IOManager.getInstance().setPs(os);
			//若发生异常则提示相应信息			
		} catch (UnknownHostException e) {
			
			MessageManager.getInstance().addMessage("找不到服务器！");
			
		} catch (IOException e) {
			
			MessageManager.getInstance().addMessage("服务器连接出错！");
		}

	}
}
