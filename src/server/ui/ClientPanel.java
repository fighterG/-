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
	 * �ͻ����б�
	 */
	private static final long serialVersionUID = 1L;
	
	JList clientList = null;
	DefaultListModel model = null;
	//��ʼ���ͻ����б�
	public JList getClientList() {
		
		if(clientList == null){
			
			clientList = new JList(this.getModel());
			//����һ���̶�ֵ���������б���ÿ����Ԫ�Ŀ��
			clientList.setFixedCellWidth(150);
			//���� visibleRowCount ���ԣ����ڲ�ͬ�Ĳ��ַ��򣬴˷����в�ͬ�ĺ��壺
			//���� VERTICAL ���ַ��򣬴˷�������Ҫ��ʾ����ѡ��������Ҫ���������
			clientList.setVisibleRowCount(10);
		}
		return clientList;
	}
	//��ʼ��DefaultListModel�������ڶ��б���в���
	public DefaultListModel getModel() {
		
		if(model == null){
			
			model = new DefaultListModel();
			
		}
		return model;
	}
	
	public ClientPanel(){
		//����һ���յ�JScrollPane
		JScrollPane listPane = new JScrollPane();
		//�������ӿ�ˮƽ��ͼλ�õ�ˮƽ��������ӵ����������У�һ��JScrollPane Ĭ�ϴ���ˮƽ�ʹ�ֱ������
		listPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//�������ӿڴ�ֱ��ͼλ�õĹ�������ӵ����������С�һ�� JScrollPane Ĭ�ϴ�����ֱ��ˮƽ������
		listPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//����һ���ӿڲ���������ͼ
		listPane.setViewportView(this.getClientList());
		
		this.setLayout(new BorderLayout());
		this.add(listPane,BorderLayout.CENTER);
		this.setBorder(new TitledBorder(new EtchedBorder(), "����б�" ,TitledBorder.CENTER ,TitledBorder.TOP ));
		
	}
	
	public void addClient(int uid){
		
		this.getModel().addElement(uid );
		
	}
	public void removeClient(int uid){
		
		this.getModel().removeElement(uid);
	}
}
