package client.ui;

import java.awt.BorderLayout;
import javax.swing.*;

public class GameFrame extends JFrame{
	/**
	 * ��Ϸ������
	 */
	private static final long serialVersionUID = 1L;
	
	private static GameFrame instance;
	
	GamePanel gamePanel = null;
	FunctionPanel functionPanel = null;
	
	private GameFrame(){
		super("������Ƶ�����������");
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
		//�����˴��ڵĴ�С�����ʺ������������ѡ��С�Ͳ���
		this.pack();
		this.setLocation(200, 10);
		this.setVisible(true);
		this.setResizable(false);
		//�����û��ڴ˴����Ϸ��� "close" ʱĬ��ִ�еĲ���
		//EXIT_ON_CLOSE���� JFrame �ж��壩��ʹ�� System exit �����˳�Ӧ�ó���
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
