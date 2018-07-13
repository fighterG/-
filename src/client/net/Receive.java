package client.net;

import java.io.BufferedReader;
import java.io.IOException;

import client.data.Data;
import client.manager.IOManager;
import client.manager.ListManager;
import client.manager.MessageManager;

public class Receive implements Runnable{
	
	/**
	 * 接受服务器传来的信息
	 */

	@Override
	public void run() {
		
		BufferedReader br = IOManager.getInstance().getBr();
		
		String readLine;
		
		while(Data.connected){
			try {
				
				readLine = br.readLine();
				
           //初始化ID信息判断前五个字符是否为init,若是调用init方法从第五个字节开始往后读
		   //把相应的name和id进行赋值
				if(readLine.substring(0, 5).equals("INIT:")){
					new Resolve().init(readLine.substring(5));
				}
				
           //若读到前五个字符为syst:代表游戏开始消息
				if(readLine.substring(0, 5).equals("SYST:")){				
					new Resolve().startMessage(readLine.substring(5));
				}
				
          //若读到前五个字符为name:代表修改昵称
				if(readLine.substring(0, 5).equals("NAME:")){
					new Resolve().changeName(readLine.substring(5));
				}
				
         //若读到前五个字符为upna:代表更新姓名
				if(readLine.substring(0, 5).equals("UPNA:")){				
					new Resolve().updateName(readLine.substring(5));
				}
				
         //若读到前五个字符为list:代表更新列表
				if(readLine.substring(0, 5).equals("LIST:")){	
					
					new Resolve().updateList(readLine.substring(5));

				}
				
         //若读到前五个字符为addl:代表	添加玩家
				if(readLine.substring(0, 5).equals("ADDL:")){				
					new Resolve().addList(readLine.substring(5));

				}
				
        //若读到前五个字符为dele:代表删除玩家
				if(readLine.substring(0, 5).equals("DELE:")){				
					new Resolve().delList(readLine.substring(5));
				}
			
				
       //若读到前五个字符为star:代表开始
				if(readLine.substring(0, 5).equals("STAR:")){
					new Resolve().start(readLine.substring(5));					
				}
				
       //若读到前五个字符为play:代表落子
				if(readLine.substring(0, 5).equalsIgnoreCase("PLAY:")){
					new Resolve().play(readLine.substring(5));
				}
				
       //若读到前五个字符为chat:代表聊天
				if(readLine.substring(0, 5).equals("CHAT:")){
					new Resolve().chat(readLine.substring(5));
				}
				
        //若读到前五个字符为oper:代表操作
				if(readLine.substring(0, 5).equals("OPER:")){
					new Resolve().operation(readLine.substring(5));
				}
				
          //若读到前五个字符为repl:代表各种回复
				if(readLine.substring(0, 5).equals("REPL:")){
					new Resolve().reply(readLine.substring(5));
				}
				
          //若读到前五个字符为uwin:代表胜利
				if(readLine.substring(0, 5).equals("UWIN:")){
					new Resolve().win();
				}
         //若读到前五个字符为lose:代表失败
				if(readLine.substring(0, 5).equals("LOSE:")){
					new Resolve().lose();
				}
         //若读到前五个字符为ask4:代表各种请求
				if(readLine.substring(0, 5).equals("ASK4:")){
					new Resolve().askFor(readLine.substring(5));
				}
			} catch (IOException e) {
				
				Data.connected = false;
				Data.chessBoard = new int[15][15];
				Data.last = -1;
				Data.myChess = 0;
				Data.myId = 0;
				Data.myName = null;
				Data.oppoChess = 0;
				Data.oppoId = 0;
				Data.ready = false;
				Data.started = false;
				Data.turn = -1;
				
				ListManager.getInstance().clearList();
				MessageManager.getInstance().addMessage("与服务器连接中断！");
				
			}
		}
	}

}
