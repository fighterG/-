package server.ui;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ClientPanel extends JPanel {

	/**
	 * 客户端列表
	 */
	private static final long serialVersionUID = 1L;
	
	JList clientList = null;
	DefaultListModel model = null;
	//初始化客户端列表
	public JList getClientList() {
		
		if(clientList == null){
			
			clientList = new JList(this.getModel());
			//设置一个固定值，将用于列表中每个单元的宽度
			clientList.setFixedCellWidth(150);
			//设置 visibleRowCount 属性，对于不同的布局方向，此方法有不同的含义：
			//对于 VERTICAL 布局方向，此方法设置要显示的首选行数（不要求滚动）；
			clientList.setVisibleRowCount(10);
		}
		return clientList;
	}
	//初始化DefaultListModel对象，用于对列表进行操作
	public DefaultListModel getModel() {
		
		if(model == null){
			
			model = new DefaultListModel();
			
		}
		return model;
	}
	
	public ClientPanel(){
		//创建一个空的JScrollPane
		JScrollPane listPane = new JScrollPane();
		//将控制视口水平视图位置的水平滚动条添加到滚动窗格中，一般JScrollPane 默认创建水平和垂直滚动条
		listPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//将控制视口垂直视图位置的滚动条添加到滚动窗格中。一般 JScrollPane 默认创建垂直和水平滚动条
		listPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//创建一个视口并设置其视图
		listPane.setViewportView(this.getClientList());
		
		this.setLayout(new BorderLayout());
		this.add(listPane,BorderLayout.CENTER);
		this.setBorder(new TitledBorder(new EtchedBorder(), "玩家列表" ,TitledBorder.CENTER ,TitledBorder.TOP ));
		
	}
	
	public void addClient(int uid){
		
		this.getModel().addElement(uid );
		
	}
	public void removeClient(int uid){
		
		this.getModel().removeElement(uid);
	}
}
