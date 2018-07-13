package client.ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import client.listener.ChallengeListener;
import client.listener.QuitListener;
import client.manager.ListManager;

public class PlayerPanel extends JPanel {
	/**
	 * 玩家列表区
	 */
	private static final long serialVersionUID = 1L;
	//当前对手
	private JLabel opponentInfo = null;
	


	private JPanel playerBar;
	private JPanel playerBody;
	private JPanel playerButtom;
	//玩家列表区的滚动条
	private JScrollPane listPane;
	//挑战他
	private JButton challenge;
	//不玩了
	private JButton quit;



	PlayerPanel(){
		
		playerBar = new JPanel();
		playerBody = new JPanel();
		playerButtom = new JPanel(new BorderLayout());
		
		listPane = new JScrollPane();
		
		challenge = new JButton("挑战他");
		quit = new JButton("不玩了");
		//用于水平滚动条的显示策略
		listPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//用于竖直滚动条的显示策略
		listPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//在窗体中显示已经加入的玩家
		listPane.setViewportView(ListManager.getInstance().getPlayerList());
		
		playerBody.add(listPane);
		//获取当前对手信息
		playerButtom.add(getOpponentInfo(),BorderLayout.NORTH);
		//把不玩了按钮添加到窗体中
		playerButtom.add(quit,BorderLayout.WEST);
		//把挑战他按钮添加到窗体中
		playerButtom.add(challenge,BorderLayout.EAST);
		//为不玩了按钮添加监听
		quit.addActionListener(new QuitListener());
		//为挑战他按钮添加监听
		challenge.addActionListener(new ChallengeListener());
		//设置礼列表中每一行的宽度
		ListManager.getInstance().getPlayerList().setFixedCellWidth(200);
		
		
		this.setLayout(new BorderLayout());
		//设置玩家列表标题居中在顶部显示
		this.setBorder(new TitledBorder(new EtchedBorder(), "玩家列表" ,TitledBorder.CENTER ,TitledBorder.TOP ));
		//把顶部那一列添加到主窗体中
		this.add(playerBar,BorderLayout.NORTH);
		//把滚动栏添加到主窗体中
		this.add(playerBody,BorderLayout.CENTER);
		//把按钮添加到主窗体中
		this.add(playerButtom,BorderLayout.SOUTH);
		
	}
	
	//设置初始化的对手信息
	public JLabel getOpponentInfo() {
		
		if (opponentInfo == null){
			
			opponentInfo = new JLabel("目前对手：无");
		}
		return opponentInfo;
	}
	
}
