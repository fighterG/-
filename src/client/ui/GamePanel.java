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
	 * �����Ϸ��
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
		//����Ҫ��ʾ���ı�
		nameIn = new JTextField("����", 6);

		connectBtn = new JButton("��¼");
		symbol = new JLabel(":");

		name = new JButton("�޸��ǳ�");
		//���޸��ǳư�ť��Ӽ���
		name.addActionListener(new NameListener());
        //����½��ť��Ӽ���
		connectBtn.addActionListener(new ConnectListener());
		//�������Ƿ��޸Ĺ���������������
		nameArea = new JPanel();
		nameArea.setLayout(new BorderLayout());
		nameArea.add(getNameIn(), BorderLayout.CENTER);
		nameArea.add(name, BorderLayout.EAST);
        //��IP����Ϣ��ӵ�gamebar��
		gameBar = new JPanel();
		gameBar.setLayout(new FlowLayout());
		gameBar.add(this.getConnectInfo());
		gameBar.add(getIp());
		gameBar.add(symbol);
		gameBar.add(getPort());	
		gameBar.add(connectBtn);
		gameBar.add(nameArea);
        //��Ϸ�����������������̣�
		gameBody = new JPanel();
		gameBody.add(getMapCanvas());
		
        //��gamebar��gamebody��ӵ���ǰ������
		this.setLayout(new BorderLayout());
		this.add(gameBar, BorderLayout.NORTH);
		this.add(gameBody, BorderLayout.CENTER);

	}
   //��ʼ����������½״̬
	public JLabel getConnectInfo() {
		if (connectInfo == null) {
			connectInfo = new JLabel("������״̬��δ��¼��  ");
		}
		return connectInfo;
	}
    //��ʼ����Ϸ��壨�������̣�
	public ChessBoardCanvas getMapCanvas() {

		if (mapCanvas == null) {

			mapCanvas = new ChessBoardCanvas();
		}
		return mapCanvas;
	}
      //���ö˿ں�
	public JTextField getPort() {

		if (port == null) {

			port = new JTextField("9990");
		}
		return port;
	}
      //����ip
	public JTextField getIp() {

		if (ip == null) {

			ip = new JTextField("127.0.0.1", 9);
		}
		return ip;
	}
      //�����������
	public JTextField getNameIn() {

		if (nameIn == null) {

			nameIn = new JTextField("��̳����", 6);
			nameIn.setEditable(true);
		}
		return nameIn;
	}

}
