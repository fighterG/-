package client.net;

import java.io.BufferedReader;
import java.io.IOException;

import client.data.Data;
import client.manager.IOManager;
import client.manager.ListManager;
import client.manager.MessageManager;

public class Receive implements Runnable{
	
	/**
	 * ���ܷ�������������Ϣ
	 */

	@Override
	public void run() {
		
		BufferedReader br = IOManager.getInstance().getBr();
		
		String readLine;
		
		while(Data.connected){
			try {
				
				readLine = br.readLine();
				
           //��ʼ��ID��Ϣ�ж�ǰ����ַ��Ƿ�Ϊinit,���ǵ���init�����ӵ�����ֽڿ�ʼ�����
		   //����Ӧ��name��id���и�ֵ
				if(readLine.substring(0, 5).equals("INIT:")){
					new Resolve().init(readLine.substring(5));
				}
				
           //������ǰ����ַ�Ϊsyst:������Ϸ��ʼ��Ϣ
				if(readLine.substring(0, 5).equals("SYST:")){				
					new Resolve().startMessage(readLine.substring(5));
				}
				
          //������ǰ����ַ�Ϊname:�����޸��ǳ�
				if(readLine.substring(0, 5).equals("NAME:")){
					new Resolve().changeName(readLine.substring(5));
				}
				
         //������ǰ����ַ�Ϊupna:�����������
				if(readLine.substring(0, 5).equals("UPNA:")){				
					new Resolve().updateName(readLine.substring(5));
				}
				
         //������ǰ����ַ�Ϊlist:��������б�
				if(readLine.substring(0, 5).equals("LIST:")){	
					
					new Resolve().updateList(readLine.substring(5));

				}
				
         //������ǰ����ַ�Ϊaddl:����	������
				if(readLine.substring(0, 5).equals("ADDL:")){				
					new Resolve().addList(readLine.substring(5));

				}
				
        //������ǰ����ַ�Ϊdele:����ɾ�����
				if(readLine.substring(0, 5).equals("DELE:")){				
					new Resolve().delList(readLine.substring(5));
				}
			
				
       //������ǰ����ַ�Ϊstar:����ʼ
				if(readLine.substring(0, 5).equals("STAR:")){
					new Resolve().start(readLine.substring(5));					
				}
				
       //������ǰ����ַ�Ϊplay:��������
				if(readLine.substring(0, 5).equalsIgnoreCase("PLAY:")){
					new Resolve().play(readLine.substring(5));
				}
				
       //������ǰ����ַ�Ϊchat:��������
				if(readLine.substring(0, 5).equals("CHAT:")){
					new Resolve().chat(readLine.substring(5));
				}
				
        //������ǰ����ַ�Ϊoper:�������
				if(readLine.substring(0, 5).equals("OPER:")){
					new Resolve().operation(readLine.substring(5));
				}
				
          //������ǰ����ַ�Ϊrepl:������ֻظ�
				if(readLine.substring(0, 5).equals("REPL:")){
					new Resolve().reply(readLine.substring(5));
				}
				
          //������ǰ����ַ�Ϊuwin:����ʤ��
				if(readLine.substring(0, 5).equals("UWIN:")){
					new Resolve().win();
				}
         //������ǰ����ַ�Ϊlose:����ʧ��
				if(readLine.substring(0, 5).equals("LOSE:")){
					new Resolve().lose();
				}
         //������ǰ����ַ�Ϊask4:�����������
				if(readLine.substring(0, 5).equals("ASK4:")){
					new Resolve().askFor(readLine.substring(5));
				}
			} catch (IOException e) {
				
				Data.connected = false;
				Data.chessBoard = new int[15][15];
				Data.last = -1;
				Data.myChess = 0;
				Data.myId = 0;
				Data.myName = null;
				Data.oppoChess = 0;
				Data.oppoId = 0;
				Data.ready = false;
				Data.started = false;
				Data.turn = -1;
				
				ListManager.getInstance().clearList();
				MessageManager.getInstance().addMessage("������������жϣ�");
				
			}
		}
	}

}
