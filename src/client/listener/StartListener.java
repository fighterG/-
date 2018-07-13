package client.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import client.data.Data;
import client.manager.IOManager;
import client.manager.MessageManager;

public class StartListener implements ActionListener {
	
	/**
	 * 监听开始按钮
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//先判断是否登陆若是再判断是否选好玩家若是则可以点击开始按钮，刚开始按钮的值为false点击
		//开始后再消息框中提示请稍等
		//然后按钮的值变为true
		//此时若再点击开始按钮则提示已经准备过了。
		
		if(Data.connected){
			if(Data.oppoId != 0){
				if(Data.ready == false){
					IOManager.getInstance().getPs().println("OPER:STAR:");
					
					MessageManager.getInstance().addMessage("请稍等。。。");
					
					Data.ready = true;
				}
				else{
					MessageManager.getInstance().addMessage("已经准备过了！");
				}
				
			}
			else{
				
				JOptionPane.showMessageDialog(null, "先选择一个对手吧！");
			}
		}
		else{
			MessageManager.getInstance().addMessage("先登陆吧！");
		}
		
		
	}
}
