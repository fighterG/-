package server.tool;

import java.util.HashMap;
import java.util.HashSet;

import server.ui.ServerFrame;




public class HashMapManager {
	
	/**
	 * �洢Map����
	 */
	private static HashMapManager instance = null;
	
	private HashMap<Integer,Player> players = null;					//player
	private HashMap<Integer,Integer> matchs = null;					//��Գɹ�
	private HashMap<Integer,Integer> matching = null;				//�����
	private HashMap<Integer,FightManager> fightManagers = null;		//��ս������
	//HashSetΪ���������ṩ���ȶ����ܣ���Щ������������ add��remove��contains �� size��
	//HashSet����һ������ָ�� collection �е�Ԫ�ص��� set
	private HashSet<Integer> readys;								//�洢��׼�������
		
	private HashMapManager(){
		
	}
	//��ʼ����ǰ�����
	public static HashMapManager getInstance(){
		
		if(instance == null){
			
			instance = new HashMapManager();
		}
		return instance;
	}
	//��ʼ��fightmanager����
	public HashMap<Integer,FightManager> getFightManagers(){
		
		if (fightManagers == null){
			
			fightManagers = new HashMap<Integer,FightManager>();
		}
		return fightManagers;
	}
   //��ʼ��ready����
	public HashSet<Integer> getReadys(){
		
		if (readys == null){
			
			readys = new HashSet<Integer>();
		}
		return readys;
	}
	//��ʼ��matching����
	public HashMap<Integer,Integer> getMatching(){
		
		if (matching == null){
			
			matching = new HashMap<Integer,Integer>();
		}
		return matching;
	}
	//��Ӷ�ս��Ϣ
	public void addMatchs(Integer uid1,Integer uid2){
		
		getMatchs().put(uid1, uid2);
		
//		��������ʾ����
		ServerFrame.getInstance().getMatchsPanel().addMatchs(uid1,uid2);
	}
	//�Ƴ���ս��Ϣ
	public void removeMatchs(Integer uid){
		
//		��������ʾ����
		ServerFrame.getInstance().getMatchsPanel().removeMatchs(uid);
		//remove�Ƴ�������uidΪkey��ӳ��
		getMatchs().remove(uid);
		
	}
	public HashMap<Integer,Integer> getMatchs(){
		
		if (matchs == null){
			
			matchs = new HashMap<Integer,Integer>();
		}
		return matchs;
	}
       //ͨ��id��ȡ�����Ϣ
	public String getName(Integer uid){
		return getPlayer(uid).name;
	}
      //����������Ϣ
	public void addPlayer(Integer uid, Player player){
		
		getPlayers().put(uid, player);
		
         //��������ʾ����
		ServerFrame.getInstance().getClientPanel().addClient(uid);
	}
	//�Ƴ������Ϣ
	public void removePlayer(Integer uid){
		
         //��������ʾ����		
		ServerFrame.getInstance().getClientPanel().removeClient(uid);
		getPlayers().remove(uid);
	}
	public Player getPlayer(Integer uid){
		//����ָ������ӳ���ֵ��ͨ�����ݵ�uid��Ϣ���ض�Ӧ�������Ϣ
		return getPlayers().get(uid);
	}
	public HashMap<Integer,Player> getPlayers(){
		
		if(players == null){
			
			players = new HashMap<Integer,Player>();
		}
		return players;
	}

}
