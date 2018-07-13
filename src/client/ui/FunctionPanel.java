package client.ui;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class FunctionPanel extends JPanel{
	/**
	 * ������
	 */
	private static final long serialVersionUID = 1L;
	//����б�
	PlayerPanel playerPanel = null;
	//��Ϣ��
	MessagePanel  messagePanel = null;
	//�ײ�������
	OperationPanel operationPanel = null;
	
	//�ѹ�����������������ӵ���������
	FunctionPanel(){
		
		this.setLayout(new BorderLayout());
		this.add(getPlayerPanel(),BorderLayout.NORTH);
		this.add(getMessagePanel(),BorderLayout.CENTER);
		this.add(getOperationPanel(),BorderLayout.SOUTH);
		
	}

//��getPlayerPanel()�жԶ�����г�ʼ��
	public PlayerPanel getPlayerPanel() {
		
		if (playerPanel == null){
			
			playerPanel = new PlayerPanel();
		}
		return playerPanel;
	}

//��getMessagePanel()�жԶ�����г�ʼ��
	public MessagePanel getMessagePanel() {
		
		if (messagePanel == null){
			
			messagePanel = new MessagePanel();
		}
		return messagePanel;
	}

//�� getOperationPanel()�жԶ�����г�ʼ��
	public OperationPanel getOperationPanel() {
		
		if (operationPanel == null){
			
			operationPanel = new OperationPanel();
		}
		return operationPanel;
	}

}
