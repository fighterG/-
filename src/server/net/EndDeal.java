package server.net;

import server.tool.HashMapManager;
import server.tool.MessageManager;

public class EndDeal {
	/**
	 * ��������
	 * */
	public void clientOff(int uid) {
		
		HashMapManager manager = HashMapManager.getInstance();
		
//		�Ƿ������
		if(manager.getMatchs().containsKey(uid)){
			
			int oppoId = HashMapManager.getInstance().getMatchs().get(uid);
			
//			�Ƿ���׼��
			if(manager.getReadys().contains(uid)){
				manager.getReadys().remove(uid);
			}
			if(manager.getReadys().contains(oppoId)){
				manager.getReadys().remove(oppoId);
			}
			
//			�Ƿ���ע��FightManager
			if(manager.getFightManagers().containsKey(uid)){
				
				
//				�����Ƿ���ע��FightManager
				if(manager.getFightManagers().containsKey(oppoId)){
//					�Ƴ�FightManager
					manager.getFightManagers().remove(oppoId);
				}
//				�Ƴ�FightManager
				manager.getFightManagers().remove(uid);
			}
			
//			�Ƴ����
			manager.removeMatchs(uid);

		}
		
		
		
//		�Ƿ��������
		if(manager.getMatching().containsKey(uid)){
//			�Ƴ��������
			manager.getMatching().remove(uid);
		}
		
//		�����Ƴ��ͻ���ָ��
		new Action().removeClient(uid);
		MessageManager.getInstance().addMessage("���" + uid + "�����ˣ�");
		
		
//		�Ƴ�player
		manager.removePlayer(uid);

	}

}
