package client.ui;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
//import javax.swing.border.EtchedBorder;
//import javax.swing.border.TitledBorder;
import client.listener.MessageListener;
import client.manager.MessageManager;

public class MessagePanel extends JPanel {
	/**
	 * ��Ϣ��
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel message = new JPanel(new BorderLayout());
	//�༭�����ı��������������getMessageIn()�жԶ�����г�ʼ��
	private JTextField messageIn = null;
	public JTextField getMessageIn() {
		if(messageIn == null){
			messageIn = new JTextField();
		}
		return messageIn;
	}
     //��Ϣ���
	MessagePanel(){
		
		this.setLayout(new BorderLayout());
		//�����úõ��ı�����ӵ�message������
		message.add(MessageManager.getInstance().getMessageArea(),BorderLayout.CENTER);
		//Ϊ�������Ӽ���
		getMessageIn().addActionListener(new MessageListener());
		//���������ӵ�������
		message.add(getMessageIn(),BorderLayout.SOUTH);
		//��message��ӵ���������
		this.add(message);

	}
}
