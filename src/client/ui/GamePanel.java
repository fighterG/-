package client.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.listener.ConnectListener;
import client.listener.NameListener;

public class GamePanel extends JPanel {
	/**
	 * 左边游戏区
	 */
	private static final long serialVersionUID = 1L;

	private JPanel gameBar;
	private JPanel gameBody;
	private JPanel nameArea;

	private JButton name;
	private JButton connectBtn;

	private JLabel symbol;
	private JLabel connectInfo;

	private JTextField ip;
	private JTextField port = null;
  
	private ChessBoardCanvas mapCanvas = null;;

	private JTextField nameIn;

	GamePanel() {
		//设置要显示的文本
		nameIn = new JTextField("新手", 6);

		connectBtn = new JButton("登录");
		symbol = new JLabel(":");

		name = new JButton("修改昵称");
		//给修改昵称按钮添加监听
		name.addActionListener(new NameListener());
        //给登陆按钮添加监听
		connectBtn.addActionListener(new ConnectListener());
		//看名字是否修改过，看情况添加名字
		nameArea = new JPanel();
		nameArea.setLayout(new BorderLayout());
		nameArea.add(getNameIn(), BorderLayout.CENTER);
		nameArea.add(name, BorderLayout.EAST);
        //把IP等信息添加到gamebar中
		gameBar = new JPanel();
		gameBar.setLayout(new FlowLayout());
		gameBar.add(this.getConnectInfo());
		gameBar.add(getIp());
		gameBar.add(symbol);
		gameBar.add(getPort());	
		gameBar.add(connectBtn);
		gameBar.add(nameArea);
        //游戏的主题区域（五子棋盘）
		gameBody = new JPanel();
		gameBody.add(getMapCanvas());
		
        //把gamebar和gamebody添加到当前界面中
		this.setLayout(new BorderLayout());
		this.add(gameBar, BorderLayout.NORTH);
		this.add(gameBody, BorderLayout.CENTER);

	}
   //初始化服务器登陆状态
	public JLabel getConnectInfo() {
		if (connectInfo == null) {
			connectInfo = new JLabel("服务器状态：未登录！  ");
		}
		return connectInfo;
	}
    //初始化游戏面板（五子棋盘）
	public ChessBoardCanvas getMapCanvas() {

		if (mapCanvas == null) {

			mapCanvas = new ChessBoardCanvas();
		}
		return mapCanvas;
	}
      //设置端口号
	public JTextField getPort() {

		if (port == null) {

			port = new JTextField("9990");
		}
		return port;
	}
      //设置ip
	public JTextField getIp() {

		if (ip == null) {

			ip = new JTextField("127.0.0.1", 9);
		}
		return ip;
	}
      //设置玩家姓名
	public JTextField getNameIn() {

		if (nameIn == null) {

			nameIn = new JTextField("棋坛新手", 6);
			nameIn.setEditable(true);
		}
		return nameIn;
	}

}
