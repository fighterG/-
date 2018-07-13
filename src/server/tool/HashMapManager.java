package server.tool;

import java.util.HashMap;
import java.util.HashSet;

import server.ui.ServerFrame;




public class HashMapManager {
	
	/**
	 * 存储Map集合
	 */
	private static HashMapManager instance = null;
	
	private HashMap<Integer,Player> players = null;					//player
	private HashMap<Integer,Integer> matchs = null;					//配对成功
	private HashMap<Integer,Integer> matching = null;				//配对中
	private HashMap<Integer,FightManager> fightManagers = null;		//对战管理器
	//HashSet为基本操作提供了稳定性能，这些基本操作包括 add、remove、contains 和 size，
	//HashSet构造一个包含指定 collection 中的元素的新 set
	private HashSet<Integer> readys;								//存储已准备的玩家
		
	private HashMapManager(){
		
	}
	//初始化当前类对象
	public static HashMapManager getInstance(){
		
		if(instance == null){
			
			instance = new HashMapManager();
		}
		return instance;
	}
	//初始化fightmanager对象
	public HashMap<Integer,FightManager> getFightManagers(){
		
		if (fightManagers == null){
			
			fightManagers = new HashMap<Integer,FightManager>();
		}
		return fightManagers;
	}
   //初始化ready对象
	public HashSet<Integer> getReadys(){
		
		if (readys == null){
			
			readys = new HashSet<Integer>();
		}
		return readys;
	}
	//初始化matching对象
	public HashMap<Integer,Integer> getMatching(){
		
		if (matching == null){
			
			matching = new HashMap<Integer,Integer>();
		}
		return matching;
	}
	//添加对战信息
	public void addMatchs(Integer uid1,Integer uid2){
		
		getMatchs().put(uid1, uid2);
		
//		服务器显示更新
		ServerFrame.getInstance().getMatchsPanel().addMatchs(uid1,uid2);
	}
	//移除对战信息
	public void removeMatchs(Integer uid){
		
//		服务器显示更新
		ServerFrame.getInstance().getMatchsPanel().removeMatchs(uid);
		//remove移除的是以uid为key的映射
		getMatchs().remove(uid);
		
	}
	public HashMap<Integer,Integer> getMatchs(){
		
		if (matchs == null){
			
			matchs = new HashMap<Integer,Integer>();
		}
		return matchs;
	}
       //通过id获取玩家信息
	public String getName(Integer uid){
		return getPlayer(uid).name;
	}
      //添加新玩家信息
	public void addPlayer(Integer uid, Player player){
		
		getPlayers().put(uid, player);
		
         //服务器显示更新
		ServerFrame.getInstance().getClientPanel().addClient(uid);
	}
	//移除玩家信息
	public void removePlayer(Integer uid){
		
         //服务器显示更新		
		ServerFrame.getInstance().getClientPanel().removeClient(uid);
		getPlayers().remove(uid);
	}
	public Player getPlayer(Integer uid){
		//返回指定键所映射的值，通过传递的uid信息返回对应的玩家信息
		return getPlayers().get(uid);
	}
	public HashMap<Integer,Player> getPlayers(){
		
		if(players == null){
			
			players = new HashMap<Integer,Player>();
		}
		return players;
	}

}
