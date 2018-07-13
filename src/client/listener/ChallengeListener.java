package client.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;
import javax.swing.JOptionPane;

import client.data.Data;
import client.manager.IOManager;
import client.manager.ListManager;
import client.manager.MessageManager;


public class ChallengeListener implements ActionListener{
	/**
	 *监听挑战按钮 
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		int target;
		
		JList list = ListManager.getInstance().getPlayerList();
		//判断是否有队手
		if(Data.oppoId == 0){
			//判断是否选中对手
			if(!list.isSelectionEmpty()){
				
				String s =(String) list.getSelectedValue();
				//在字符中间添加分隔符—	
				String [] ss = s.split("-");
				
				s = ss[0];
				//将s转化为int类型
				target = Integer.parseInt(s);
				
				if(target != Data.myId){
					
					MessageManager.getInstance().addMessage("等待对方接受挑战！");
					
					IOManager.getInstance().getPs().println("OPER:" + "CHAL:" + target);
				}
				else{
					
					JOptionPane.showMessageDialog(null, "别闹。。。不能挑战自己。。。");
				}
				
			}
			else{
				
				JOptionPane.showMessageDialog(null, "未选中任何对手！");
			}
		}
		else{
			MessageManager.getInstance().addMessage("已经有对手了！");
		}
	}

}
