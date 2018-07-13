package client.manager;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import client.listener.ListListener;

public class ListManager {
	/**
	 * 管理玩家列表
	 */
	private static ListManager instance = null;
	
	private JList playerList = null;
	private DefaultListModel listModel = null; 
	
	private ListManager(){
		
	}
	//
	public static ListManager getInstance(){
		
		if(instance == null){
			
			instance = new ListManager();
		}
		return instance;

	}
	//创建JList对象时要对该对象指定一个DefaultListModel对象，
	//JList对象中的项都放在了ListModel对象中,
    //通过操作JList对象的ListModel对象来操作JList中的项,
	//一般是用DefaultListModel,
	public JList getPlayerList() {
		
		if(playerList == null){
			
			playerList = new JList(getListModel());
			//添加列表监听（双击列表也可挑战对手）
			playerList.addMouseListener(new ListListener());
		}
		return playerList;

		
	}
	
	public DefaultListModel getListModel() {
		
		if(listModel == null){
			
			listModel = new DefaultListModel();
		}
		return listModel;
	}
	//移除列表中的所有玩家，并重新绘制图板
	public void clearList(){
		
		this.getListModel().clear();
		this.getPlayerList().repaint();
		
	}
	//将指定的玩家添加到列表的末尾，并重新绘制图板
	public void addPlayer(String name){
		
		this.getListModel().addElement(name);
		this.getPlayerList().repaint();
		
	}
	//移除指定的玩家并重新绘制图板
	public void removePlayer(String name){
		
		this.getListModel().removeElement(name);
		this.getPlayerList().repaint();
		
	}
}
