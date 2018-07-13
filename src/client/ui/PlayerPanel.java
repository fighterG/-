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
	 * ����б���
	 */
	private static final long serialVersionUID = 1L;
	//��ǰ����
	private JLabel opponentInfo = null;
	


	private JPanel playerBar;
	private JPanel playerBody;
	private JPanel playerButtom;
	//����б����Ĺ�����
	private JScrollPane listPane;
	//��ս��
	private JButton challenge;
	//������
	private JButton quit;



	PlayerPanel(){
		
		playerBar = new JPanel();
		playerBody = new JPanel();
		playerButtom = new JPanel(new BorderLayout());
		
		listPane = new JScrollPane();
		
		challenge = new JButton("��ս��");
		quit = new JButton("������");
		//����ˮƽ����������ʾ����
		listPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//������ֱ����������ʾ����
		listPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//�ڴ�������ʾ�Ѿ���������
		listPane.setViewportView(ListManager.getInstance().getPlayerList());
		
		playerBody.add(listPane);
		//��ȡ��ǰ������Ϣ
		playerButtom.add(getOpponentInfo(),BorderLayout.NORTH);
		//�Ѳ����˰�ť��ӵ�������
		playerButtom.add(quit,BorderLayout.WEST);
		//����ս����ť��ӵ�������
		playerButtom.add(challenge,BorderLayout.EAST);
		//Ϊ�����˰�ť��Ӽ���
		quit.addActionListener(new QuitListener());
		//Ϊ��ս����ť��Ӽ���
		challenge.addActionListener(new ChallengeListener());
		//�������б���ÿһ�еĿ��
		ListManager.getInstance().getPlayerList().setFixedCellWidth(200);
		
		
		this.setLayout(new BorderLayout());
		//��������б��������ڶ�����ʾ
		this.setBorder(new TitledBorder(new EtchedBorder(), "����б�" ,TitledBorder.CENTER ,TitledBorder.TOP ));
		//�Ѷ�����һ����ӵ���������
		this.add(playerBar,BorderLayout.NORTH);
		//�ѹ�������ӵ���������
		this.add(playerBody,BorderLayout.CENTER);
		//�Ѱ�ť��ӵ���������
		this.add(playerButtom,BorderLayout.SOUTH);
		
	}
	
	//���ó�ʼ���Ķ�����Ϣ
	public JLabel getOpponentInfo() {
		
		if (opponentInfo == null){
			
			opponentInfo = new JLabel("Ŀǰ���֣���");
		}
		return opponentInfo;
	}
	
}
