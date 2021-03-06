package server.net;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Collection;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Set;

import server.tool.FightManager;
import server.tool.HashMapManager;
import server.tool.MessageManager;
import server.tool.Player;

public class Action {
	
	/**
	 * 针对从客户端传来数据解析后的执行相应操作。
	 */
	
	HashMapManager manager = HashMapManager.getInstance();
	
//	新客户端	
	public void newClient(int uid){
		HashMap<Integer,Player> players = manager.getPlayers();
		Collection<Player> c = players.values();
		Iterator<Player> i = c.iterator();
		while(i.hasNext()){
			Socket s = (Socket)i.next().socket;
			 try {
				 //获取输出流
			 OutputStream os = s.getOutputStream();
			 PrintStream ps = new PrintStream(os);
			 String name = manager.getName(uid);
			 //由服务器向客户端打印玩家信息
			 ps.println("ADDL:" + uid + "-" + name);
			 } catch (IOException e) {	
			 e.printStackTrace();
			 }
		}
	}
	
//	移除客户端
	public void removeClient(int uid){
		//keySet()返回此映射中所包含的键的 Set 视图
		//该 set 支持元素的移除，通过 Iterator.remove、Set.remove、removeAll、retainAll 
		//和 clear 操作可从该映射中移除相应的映射关系。
		Set<Integer> p = (Set<Integer>)manager.getPlayers().keySet();
		Iterator<Integer> i = p.iterator();
		while(i.hasNext()){
			Socket s = (Socket)(manager.getPlayer(i.next()).socket);

			 try {
			 OutputStream os = s.getOutputStream();
			 PrintStream ps = new PrintStream(os);		 
			 ps.println("DELE:" + uid + "-" + manager.getPlayer(uid).name );
			 
			 
			 } catch (IOException e) {	
			 e.printStackTrace();

			 }
		}
	}
	
//	获取列表
	public void getList(Socket s) {
		String list = null;
		try {
			OutputStream os = s.getOutputStream();
			PrintStream ps = new PrintStream(os);
			Set<Integer> ids = (Set<Integer>)(manager.getPlayers().keySet());

			Iterator<Integer> i = ids.iterator();

			while (i.hasNext()) {
				int id = (Integer) i.next();
				
				if (list == null) {
					list = id + "-" + manager.getPlayer(id).name + "&";
				} else {
					list = list + id + "-" + manager.getPlayer(id).name + "&";
				}
			}
			ps.println("LIST:" + list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	发送聊天消息
	public void sendMessage(int uid ,String readLine) {
		readLine = readLine.substring(5);
		String s[] = readLine.split("&");
		String message = s[0];
		int targetId = Integer.parseInt(s[1]);
		Socket socket = manager.getPlayer(targetId).socket;
		OutputStream outputStream;
		try {
			outputStream = socket.getOutputStream();
			PrintStream printStream = new PrintStream(outputStream);
			printStream.println("CHAT:"  +  message + "&"+ uid + "-" + HashMapManager.getInstance().getName(uid) );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	发送挑战消息
	public void sendChallenge(int uid, int target) {
		HashMap<Integer,Player> players = manager.getPlayers();
		String name = HashMapManager.getInstance().getName(uid);
		Socket s = players.get(target).socket;
		 try {
			 OutputStream os = s.getOutputStream();
			 PrintStream ps = new PrintStream(os);
			 
			 ps.println("OPER:CHAL:" + uid + "-" + name);
			 } catch (IOException e) {	
			 e.printStackTrace();
			 }
	}

//	发送重新开始请求
	public void restart(int uid) {
		manager.getFightManagers().get(uid).restart(uid);
	}

	public void sendQuit() {

	}
	
//	更新姓名
	public void updateName(int uid) {
		HashMap<Integer,Player> players = manager.getPlayers();
		Collection<Player> c = players.values();
		Iterator<Player> i = c.iterator();
		while(i.hasNext()){
			Socket s = (Socket)i.next().socket;
			String name = null;
			 try {
			 OutputStream os = s.getOutputStream();
			 PrintStream ps = new PrintStream(os);
			 name = manager.getName(uid);
			 ps.println("UPNA:" + uid + "-" + name);
			 } catch (IOException e) {	
			 e.printStackTrace();
			 }
		}
	}

//	回复挑战
	public void replyChallenge(int uid,String readLine) {
		
		String [] s = readLine.split("&");
		int challengerId = Integer.parseInt(s[0]);
		String choose = s[1];
		Socket socket = manager.getPlayer(challengerId).socket;
		OutputStream outputStream;
		try {
			outputStream = socket.getOutputStream();
			PrintStream challengerPs = new PrintStream(outputStream);
			if(choose.equals("YES")){
				challengerPs.println("REPL:CHAL:" + uid + "-" + manager.getName(uid) + "&YES");
				manager.getMatching().remove(challengerId);
				manager.addMatchs(challengerId, uid);
				
			}
			else{
				challengerPs.println("REPL:CHAL:" + uid + "-" +manager.getName(uid)+ "&NO");
				manager.getMatching().remove(challengerId);						
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		
	}

//	修改姓名
	public void changeName(PrintStream ps,int uid, String readLine) {
		String newName = readLine.substring(5);
		if(manager.getName(uid) != null){
			MessageManager.getInstance().addMessage("玩家“" + manager.getName(uid) + "”更改名字为“" + newName + "”");
		}
		else{
			MessageManager.getInstance().addMessage("玩家“" + uid + "”更改名字为“" + newName + "”");
		}
		manager.getPlayer(uid).setName(newName);
		ps.println("NAME:" + uid + "-" + newName);
		this.updateName(uid);
		
	}
	
//	准备
	public void ready(int uid,String readLine) {
		int oppoId = 0;
		
//		标记已准备
		manager.getReadys().add(uid);
		
//		获取对方id
		if(manager.getMatchs().containsKey(uid)){
			oppoId = manager.getMatchs().get(uid);
		}
		else{
			Set<Integer> s = manager.getMatchs().keySet();
			Iterator<Integer> i = s.iterator();
			while(i.hasNext()){
				int id = (Integer)i.next();
				if(manager.getMatchs().get(id) == uid){
					oppoId = id;
				}
			}
			
		}
		
//		若两方都已准备，开始对弈
		if(manager.getReadys().contains(oppoId)){
//			获取已经注册的fightManager
			FightManager publicManager = manager.getFightManagers().get(oppoId);
//			公用一个fightManager
			manager.getFightManagers().put(uid, publicManager);
			
			publicManager.startPlay(uid,oppoId);
			
			publicManager.sendStartMessage();
			
		}
		else{
//			若对方未准备
			manager.getFightManagers().put(uid, new FightManager());
		}
	}
	
//	落子
	public void playChess(int from, int position) {
        //form相当于uid,通过from获得当前对战的fightmanager对象，然后在调用fightmanager
		//的sendplay方法下棋
		manager.getFightManagers().get(from).sendPlay(from,position);
	}

//	解除配对
	public void quit(int uid,int oppoId) {
		
		HashMapManager manager = HashMapManager.getInstance();
//		通知对手
		Socket s = manager.getPlayer(oppoId).socket;
		OutputStream os;
		try {
			os = s.getOutputStream();
			PrintStream ps = new PrintStream(os);
			
			ps.println("OPER:QUIT:");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
//		移除准备状态
		manager.getReadys().remove(uid);
		manager.getReadys().remove(oppoId);
		
//		移除配对
		if(manager.getMatchs().containsKey(uid)){
			
			manager.removeMatchs(uid);
		}
		else{
			manager.removeMatchs(oppoId);
		}
		
//		移除FightManager
		manager.getFightManagers().remove(uid);
		manager.getFightManagers().remove(oppoId);		
		
	}

//	逃跑
	public void esca(int uid, int oppoId) {
		HashMapManager manager = HashMapManager.getInstance();
		
		
//		通知对手
		Socket s = manager.getPlayer(oppoId).socket;
		OutputStream os;
		try {
			os = s.getOutputStream();
			PrintStream ps = new PrintStream(os);
			
			ps.println("OPER:ESCA:");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
//		移除准备状态
		manager.getReadys().remove(uid);
		manager.getReadys().remove(oppoId);
		
//		移除配对
		if(manager.getMatchs().containsKey(uid)){
			
			manager.removeMatchs(uid);
		}
		else{
			manager.removeMatchs(oppoId);
		}
		
//		移除FightManager
		manager.getFightManagers().remove(uid);
		manager.getFightManagers().remove(oppoId);
		
	}

	
//	悔棋
	public void askForBack(int uid, int from) {
		
		manager.getFightManagers().get(from).askForBack(from);
		
	}
	
//	悔棋回复
	public void replyBack(int uid, String str) {
		
		if(str.substring(0,2).equals("NO")){
			
			manager.getFightManagers().get(uid).refuseBack(uid);

		}
		else{
			if(str.substring(0,3).equals("YES")){
				manager.getFightManagers().get(uid).back(uid);
			}
			

		}
	}
	

}
