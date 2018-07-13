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
	 * ��Դӿͻ��˴������ݽ������ִ����Ӧ������
	 */
	
	HashMapManager manager = HashMapManager.getInstance();
	
//	�¿ͻ���	
	public void newClient(int uid){
		HashMap<Integer,Player> players = manager.getPlayers();
		Collection<Player> c = players.values();
		Iterator<Player> i = c.iterator();
		while(i.hasNext()){
			Socket s = (Socket)i.next().socket;
			 try {
				 //��ȡ�����
			 OutputStream os = s.getOutputStream();
			 PrintStream ps = new PrintStream(os);
			 String name = manager.getName(uid);
			 //�ɷ�������ͻ��˴�ӡ�����Ϣ
			 ps.println("ADDL:" + uid + "-" + name);
			 } catch (IOException e) {	
			 e.printStackTrace();
			 }
		}
	}
	
//	�Ƴ��ͻ���
	public void removeClient(int uid){
		//keySet()���ش�ӳ�����������ļ��� Set ��ͼ
		//�� set ֧��Ԫ�ص��Ƴ���ͨ�� Iterator.remove��Set.remove��removeAll��retainAll 
		//�� clear �����ɴӸ�ӳ�����Ƴ���Ӧ��ӳ���ϵ��
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
	
//	��ȡ�б�
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
	
//	����������Ϣ
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
	
//	������ս��Ϣ
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

//	�������¿�ʼ����
	public void restart(int uid) {
		manager.getFightManagers().get(uid).restart(uid);
	}

	public void sendQuit() {

	}
	
//	��������
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

//	�ظ���ս
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

//	�޸�����
	public void changeName(PrintStream ps,int uid, String readLine) {
		String newName = readLine.substring(5);
		if(manager.getName(uid) != null){
			MessageManager.getInstance().addMessage("��ҡ�" + manager.getName(uid) + "����������Ϊ��" + newName + "��");
		}
		else{
			MessageManager.getInstance().addMessage("��ҡ�" + uid + "����������Ϊ��" + newName + "��");
		}
		manager.getPlayer(uid).setName(newName);
		ps.println("NAME:" + uid + "-" + newName);
		this.updateName(uid);
		
	}
	
//	׼��
	public void ready(int uid,String readLine) {
		int oppoId = 0;
		
//		�����׼��
		manager.getReadys().add(uid);
		
//		��ȡ�Է�id
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
		
//		����������׼������ʼ����
		if(manager.getReadys().contains(oppoId)){
//			��ȡ�Ѿ�ע���fightManager
			FightManager publicManager = manager.getFightManagers().get(oppoId);
//			����һ��fightManager
			manager.getFightManagers().put(uid, publicManager);
			
			publicManager.startPlay(uid,oppoId);
			
			publicManager.sendStartMessage();
			
		}
		else{
//			���Է�δ׼��
			manager.getFightManagers().put(uid, new FightManager());
		}
	}
	
//	����
	public void playChess(int from, int position) {
        //form�൱��uid,ͨ��from��õ�ǰ��ս��fightmanager����Ȼ���ڵ���fightmanager
		//��sendplay��������
		manager.getFightManagers().get(from).sendPlay(from,position);
	}

//	������
	public void quit(int uid,int oppoId) {
		
		HashMapManager manager = HashMapManager.getInstance();
//		֪ͨ����
		Socket s = manager.getPlayer(oppoId).socket;
		OutputStream os;
		try {
			os = s.getOutputStream();
			PrintStream ps = new PrintStream(os);
			
			ps.println("OPER:QUIT:");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
//		�Ƴ�׼��״̬
		manager.getReadys().remove(uid);
		manager.getReadys().remove(oppoId);
		
//		�Ƴ����
		if(manager.getMatchs().containsKey(uid)){
			
			manager.removeMatchs(uid);
		}
		else{
			manager.removeMatchs(oppoId);
		}
		
//		�Ƴ�FightManager
		manager.getFightManagers().remove(uid);
		manager.getFightManagers().remove(oppoId);		
		
	}

//	����
	public void esca(int uid, int oppoId) {
		HashMapManager manager = HashMapManager.getInstance();
		
		
//		֪ͨ����
		Socket s = manager.getPlayer(oppoId).socket;
		OutputStream os;
		try {
			os = s.getOutputStream();
			PrintStream ps = new PrintStream(os);
			
			ps.println("OPER:ESCA:");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
//		�Ƴ�׼��״̬
		manager.getReadys().remove(uid);
		manager.getReadys().remove(oppoId);
		
//		�Ƴ����
		if(manager.getMatchs().containsKey(uid)){
			
			manager.removeMatchs(uid);
		}
		else{
			manager.removeMatchs(oppoId);
		}
		
//		�Ƴ�FightManager
		manager.getFightManagers().remove(uid);
		manager.getFightManagers().remove(oppoId);
		
	}

	
//	����
	public void askForBack(int uid, int from) {
		
		manager.getFightManagers().get(from).askForBack(from);
		
	}
	
//	����ظ�
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
