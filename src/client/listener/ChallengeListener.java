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
	 *������ս��ť 
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		int target;
		
		JList list = ListManager.getInstance().getPlayerList();
		//�ж��Ƿ��ж���
		if(Data.oppoId == 0){
			//�ж��Ƿ�ѡ�ж���
			if(!list.isSelectionEmpty()){
				
				String s =(String) list.getSelectedValue();
				//���ַ��м���ӷָ�����	
				String [] ss = s.split("-");
				
				s = ss[0];
				//��sת��Ϊint����
				target = Integer.parseInt(s);
				
				if(target != Data.myId){
					
					MessageManager.getInstance().addMessage("�ȴ��Է�������ս��");
					
					IOManager.getInstance().getPs().println("OPER:" + "CHAL:" + target);
				}
				else{
					
					JOptionPane.showMessageDialog(null, "���֡�����������ս�Լ�������");
				}
				
			}
			else{
				
				JOptionPane.showMessageDialog(null, "δѡ���κζ��֣�");
			}
		}
		else{
			MessageManager.getInstance().addMessage("�Ѿ��ж����ˣ�");
		}
	}

}
