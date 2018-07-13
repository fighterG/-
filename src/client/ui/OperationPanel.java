package client.ui;

import javax.swing.JButton;
import javax.swing.JPanel;

import client.listener.BackListener;
import client.listener.RestartListener;
import client.listener.StartListener;

public class OperationPanel extends JPanel {
	/**
	 * 底部操作区
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel operationPanel = new JPanel();
	
	private JButton reStart = new JButton("重来");
	private JButton start = new JButton("开始");
	private JButton back = new JButton("悔棋");
	
	OperationPanel(){
		//为悔棋按钮添加监听
		back.addActionListener(new BackListener());
		//为开始按钮添加监听
		start.addActionListener(new StartListener());
		//为重来按钮添加监听
		reStart.addActionListener(new RestartListener());
		
		
		operationPanel.add(back);
		operationPanel.add(reStart);
		operationPanel.add(start);
		
		this.add(operationPanel);
	}
}
