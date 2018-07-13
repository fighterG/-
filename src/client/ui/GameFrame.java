package client.ui;

import java.awt.BorderLayout;
import javax.swing.*;

public class GameFrame extends JFrame{
	/**
	 * 游戏主界面
	 */
	private static final long serialVersionUID = 1L;
	
	private static GameFrame instance;
	
	GamePanel gamePanel = null;
	FunctionPanel functionPanel = null;
	
	private GameFrame(){
		super("崔磊设计的联网五子棋");
	}
	public static GameFrame getInstance(){
		
		if(instance == null){
			
			instance = new GameFrame();
		}
		return instance;
	}
	
	
	public void launchFrame(){
		
		this.setLayout(new BorderLayout());
		this.add(getGamePanel(),BorderLayout.CENTER);
		this.add(getFunctionPanel(),BorderLayout.EAST);
		//调整此窗口的大小，以适合其子组件的首选大小和布局
		this.pack();
		this.setLocation(200, 10);
		this.setVisible(true);
		this.setResizable(false);
		//设置用户在此窗体上发起 "close" 时默认执行的操作
		//EXIT_ON_CLOSE（在 JFrame 中定义）：使用 System exit 方法退出应用程序
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public FunctionPanel getFunctionPanel(){
		
		if(functionPanel == null){
			
			functionPanel = new FunctionPanel();
		}
		return functionPanel;
	}
	
	public GamePanel getGamePanel(){
		
		if(gamePanel == null){
			
			gamePanel = new GamePanel();
		}
		return gamePanel;
	}
	

		
}
