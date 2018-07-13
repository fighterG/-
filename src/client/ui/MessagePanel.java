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
	 * 消息区
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel message = new JPanel(new BorderLayout());
	//编辑单行文本，就是输入框。在getMessageIn()中对对象进行初始化
	private JTextField messageIn = null;
	public JTextField getMessageIn() {
		if(messageIn == null){
			messageIn = new JTextField();
		}
		return messageIn;
	}
     //消息面板
	MessagePanel(){
		
		this.setLayout(new BorderLayout());
		//吧设置好的文本框添加到message窗体中
		message.add(MessageManager.getInstance().getMessageArea(),BorderLayout.CENTER);
		//为输入框添加监听
		getMessageIn().addActionListener(new MessageListener());
		//把输入框添加到框体中
		message.add(getMessageIn(),BorderLayout.SOUTH);
		//把message添加到主窗体中
		this.add(message);

	}
}
