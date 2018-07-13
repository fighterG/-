package client.ui;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class FunctionPanel extends JPanel{
	/**
	 * 功能区
	 */
	private static final long serialVersionUID = 1L;
	//玩家列表
	PlayerPanel playerPanel = null;
	//消息区
	MessagePanel  messagePanel = null;
	//底部操作区
	OperationPanel operationPanel = null;
	
	//把功能区的三个构件添加到主窗体中
	FunctionPanel(){
		
		this.setLayout(new BorderLayout());
		this.add(getPlayerPanel(),BorderLayout.NORTH);
		this.add(getMessagePanel(),BorderLayout.CENTER);
		this.add(getOperationPanel(),BorderLayout.SOUTH);
		
	}

//在getPlayerPanel()中对对象进行初始化
	public PlayerPanel getPlayerPanel() {
		
		if (playerPanel == null){
			
			playerPanel = new PlayerPanel();
		}
		return playerPanel;
	}

//在getMessagePanel()中对对象进行初始化
	public MessagePanel getMessagePanel() {
		
		if (messagePanel == null){
			
			messagePanel = new MessagePanel();
		}
		return messagePanel;
	}

//在 getOperationPanel()中对对像进行初始化
	public OperationPanel getOperationPanel() {
		
		if (operationPanel == null){
			
			operationPanel = new OperationPanel();
		}
		return operationPanel;
	}

}
