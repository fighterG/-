package server.ui;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import server.net.ServerThread;
import server.tool.MessageManager;

public class ServerFrame extends JFrame {

	/**
	 * ������������
	 */
	private static final long serialVersionUID = 1L;

	private static ServerFrame instance = null;

	ServerSocket ss;
	private ClientPanel clientPanel = null;
	private MatchsPanel matchsPanel = null;
	private MessagePanel messagePanel = null;

	private ServerFrame() {

		this.setLayout(new BorderLayout());

		this.setVisible(true);
		this.setLocation(300, 100);

		this.add(getMatchsPanel(), BorderLayout.EAST);
		this.add(getClientPanel(), BorderLayout.WEST);
		this.add(getMessagePanel(), BorderLayout.SOUTH);

		this.pack();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void launchFrame() {

		// �����������˿�
		try {
			ss = new ServerSocket(9990);
			MessageManager.getInstance().addMessage("�������Ѿ�����!");
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "�˿ڱ�ռ�ã�����˿�");
			System.exit(0);
		}
		while (true) {
			try {

				// ÿ����һ���ͻ������Ӵ���һ���߳�
				Socket s = ss.accept();

				Thread t = new Thread(new ServerThread(s));
				t.start();

			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}
//��ʼ������б����
	public ClientPanel getClientPanel() {
		if (clientPanel == null) {
			clientPanel = new ClientPanel();
		}
		return clientPanel;
	}
//��ʼ������б����
	public MatchsPanel getMatchsPanel() {
		if (matchsPanel == null) {
			matchsPanel = new MatchsPanel();
		}
		return matchsPanel;
	}
//��ʼ����Ϣ�����
	public MessagePanel getMessagePanel() {
		if (messagePanel == null) {
			messagePanel = new MessagePanel();
		}
		return messagePanel;
	}
//��ʼ����ǰ�����������ڵ��ñ����е���������
	public static ServerFrame getInstance() {
		if (instance == null) {
			instance = new ServerFrame();
		}
		return instance;
	}

}
