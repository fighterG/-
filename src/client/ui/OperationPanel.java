package client.ui;

import javax.swing.JButton;
import javax.swing.JPanel;

import client.listener.BackListener;
import client.listener.RestartListener;
import client.listener.StartListener;

public class OperationPanel extends JPanel {
	/**
	 * �ײ�������
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel operationPanel = new JPanel();
	
	private JButton reStart = new JButton("����");
	private JButton start = new JButton("��ʼ");
	private JButton back = new JButton("����");
	
	OperationPanel(){
		//Ϊ���尴ť��Ӽ���
		back.addActionListener(new BackListener());
		//Ϊ��ʼ��ť��Ӽ���
		start.addActionListener(new StartListener());
		//Ϊ������ť��Ӽ���
		reStart.addActionListener(new RestartListener());
		
		
		operationPanel.add(back);
		operationPanel.add(reStart);
		operationPanel.add(start);
		
		this.add(operationPanel);
	}
}
