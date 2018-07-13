package server.ui;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import server.tool.HashMapManager;


public class MatchsPanel extends JPanel {
	
	/**
	 * ����б�
	 */
	private static final long serialVersionUID = 1L;
	
	JList matchsList = null;
	DefaultListModel model = null;
	
	public MatchsPanel(){
		
		JScrollPane listPane = new JScrollPane();
		
		listPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		listPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		listPane.setViewportView(this.getMatchsList());
				
		this.setLayout(new BorderLayout());
		this.add(listPane,BorderLayout.CENTER);
		this.setBorder(new TitledBorder(new EtchedBorder(), "����б�" ,TitledBorder.CENTER ,TitledBorder.TOP ));
		
	}

	public JList getMatchsList() {
		
		if(matchsList == null){
			
			matchsList = new JList(getModel());
			matchsList.setFixedCellWidth(150);
			matchsList.setVisibleRowCount(10);
		}
		return matchsList;
	}
	
	public DefaultListModel getModel(){
		
		if(model == null){
			
			model = new DefaultListModel();
		}
		
		return model;
	}
	
		
//�������
	public void addMatchs(Integer uid1, Integer uid2) {
		
		this.getModel().addElement(uid1 + "vs" + uid2 );
		
	}
//�Ƴ����
	public void removeMatchs(Integer uid) {
		
		this.getModel().removeElement(uid + "vs" + HashMapManager.getInstance().getMatchs().get(uid));
	}
}
