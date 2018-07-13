package server.net;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

import server.tool.HashMapManager;
import server.tool.MessageManager;



public class Resolve {
	/**
	 *�����ӿͻ����յ������� 
	 */
	
	int uid;
	Socket s;
	OutputStream os;
	String readLine;
	
	public void resolve(int _uid,Socket _s ,String _readLine) {
		this.s = _s;
		this.uid = _uid;
		this.readLine = _readLine;
		try {
			os = s.getOutputStream();
			PrintStream ps = new PrintStream(os);
//			�����б�
			if(readLine.substring(0, 5).equals("LIST:")){
				new Action().getList(s);
			}
//			�ظ�
			if(readLine.substring(0, 5).equals("REPL:")){
				
				String str = readLine.substring(5);
				
//				�ظ���ս
				if(str.substring(0, 5).equals("CHAL:")){
					
					new Action().replyChallenge(uid ,str.substring(5));
					
				}
//				�ظ���������
				if(str.substring(0, 5).equals("BACK:")){
					
					new Action().replyBack(uid ,str.substring(5));
					
				}
			}
//			����
			if(readLine.substring(0, 5).equals("PLAY:")){
				String str = readLine.substring(5);
				int position = Integer.parseInt(str);
				new Action().playChess(uid,position);
			}
//			����
			if(readLine.substring(0, 5).equals("CHAT:")){
				new Action().sendMessage(uid ,readLine);
			}
//			����
			if(readLine.substring(0, 5).equals("OPER:")){
				String str = readLine.substring(5);
//				��ս���
				if(str.substring(0, 5).equals("CHAL:")){
					str = str.substring(5);
					int target = Integer.parseInt(str);
					new Action().sendChallenge(uid,target);
					HashMapManager.getInstance().getMatching().put(uid, target);
				}
//				��ʼ׼��
				if(str.substring(0, 5).equals("STAR:")){
					new Action().ready(uid,str.substring(5));
				}
//				���¿�ʼ
				if(str.substring(0, 5).equals("REST:")){
					new Action().restart(uid);
				}
//				�˳����
				if(str.substring(0, 5).equals("QUIT:")){
					int oppoId = Integer.parseInt(str.substring(5));
					new Action().quit(uid,oppoId);
				}
//				����
				if(str.substring(0, 5).equals("BACK:")){
					int from = Integer.parseInt(str.substring(5));
					new Action().askForBack(uid,from);
				}
//				�Է�����
				if(str.substring(0, 5).equals("ESCA:")){
					int oppoId = Integer.parseInt(str.substring(5));
					
					new Action().esca(uid,oppoId);
				}
			}
//			��ʼ����Ϣ
			if(readLine.substring(0, 5).equals("INIT:")){
				HashMapManager.getInstance().getPlayer(uid).setName(readLine.substring(5));
				new Action().newClient(uid);
				ps.println("INIT:" + uid + "-" + readLine.substring(5));
			}
//			�޸��ǳ�
			if(readLine.substring(0, 5).equals("NAME:")){
				new Action().changeName(ps,uid,readLine);
			}
		} catch (IOException e) {
			MessageManager.getInstance().addMessage("����ʱ��ȡ���������");
			e.printStackTrace();
		}
		
	}

}
