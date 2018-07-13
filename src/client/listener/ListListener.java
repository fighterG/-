package client.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.JOptionPane;

import client.data.Data;
import client.manager.IOManager;
import client.manager.ListManager;
import client.manager.MessageManager;

public class ListListener extends MouseAdapter{
	
	/**
	 * �����б�˫��������˫���б�Ҳ������ս����
	 * */
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//���ж����޶���
		if(Data.oppoId == 0){
			//���ѡ���˷���true
			if(!ListManager.getInstance().getPlayerList().isSelectionEmpty()){
				if(e.getClickCount() == 2){
					JList list = ListManager.getInstance().getPlayerList();
					//������ӽ�λ�õĵ�Ԫ����ֵ��-1
					int index = list.locationToIndex(e.getPoint());
					//�����ƶ���������ֵ��������ת��Ϊstring����
					String s =(String) list.getModel().getElementAt(index);
					String [] ss = s.split("-");
					
					s = ss[0];
					
					int target = Integer.parseInt(s);
					
					if(target != Data.myId){
						
						MessageManager.getInstance().addMessage("�ȴ��Է�������ս��");
						
						IOManager.getInstance().getPs().println("OPER:" + "CHAL:" + target);
					}
					else{
						
						JOptionPane.showMessageDialog(null, "���֡�����������ս�Լ�������");
					}

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
