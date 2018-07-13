package client.manager;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import client.listener.ListListener;

public class ListManager {
	/**
	 * ��������б�
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
	//����JList����ʱҪ�Ըö���ָ��һ��DefaultListModel����
	//JList�����е��������ListModel������,
    //ͨ������JList�����ListModel����������JList�е���,
	//һ������DefaultListModel,
	public JList getPlayerList() {
		
		if(playerList == null){
			
			playerList = new JList(getListModel());
			//����б������˫���б�Ҳ����ս���֣�
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
	//�Ƴ��б��е�������ң������»���ͼ��
	public void clearList(){
		
		this.getListModel().clear();
		this.getPlayerList().repaint();
		
	}
	//��ָ���������ӵ��б��ĩβ�������»���ͼ��
	public void addPlayer(String name){
		
		this.getListModel().addElement(name);
		this.getPlayerList().repaint();
		
	}
	//�Ƴ�ָ������Ҳ����»���ͼ��
	public void removePlayer(String name){
		
		this.getListModel().removeElement(name);
		this.getPlayerList().repaint();
		
	}
}
