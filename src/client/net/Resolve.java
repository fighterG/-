package client.net;

import java.lang.reflect.InvocationTargetException;
import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

import client.data.Data;
import client.manager.IOManager;
import client.manager.ListManager;
import client.manager.MessageManager;
import client.ui.GameFrame;
import client.ui.ChessBoardCanvas;



public class Resolve {
	/**
	 * �����ӷ������յ�������
	 */
	
	String readLine;
	
//	��½��ʼ��
	public void init(String _readLine) {
		
		readLine = _readLine;
		
		String s[] = readLine.split("-");
		String name = s[1];
		
		int id = Integer.parseInt(s[0]);
		
		Data.myId = id;
		Data.myName = name;
		
//		�����б�
		IOManager.getInstance().getPs().println("LIST:");
	}
	
	
//	��Ϸ��ʼ����
	public void start(String _readLine){
		
		readLine = _readLine;
		//�����ж����Լ����»��ǶԷ����£������Լ����£��ҵ�����Ϊ��ɫ1������Ϊ��ɫ-1���غ�Ϊ�ҵĻغϣ�����Ļغ�Ϊ1��
		if(readLine.substring(0,5).equals("BLACK")){
			
			Data.myChess = Data.BLACK;
			Data.oppoChess = Data.WHITE;
			Data.turn = Data.BLACK;
			Data.started = true;
			
		}
		//���ǶԷ����£��ҵ�����Ϊ��ɫ-1������Ϊ��ɫ1���غ�Ϊ�Է��Ļغϣ�����Ļغ�Ϊ1��
		else{
			if(readLine.substring(0,5).equals("WHITE")){
				
				Data.myChess = Data.WHITE;
				Data.oppoChess = Data.BLACK;
				Data.turn = Data.BLACK;
				Data.started = true;
				
			}
		}
	}
	
//	��Ϸ��ʼ��Ϣ
	public void startMessage(String _readLine) {
		
		readLine = _readLine;
		
		MessageManager.getInstance().addMessage(readLine);
	}

	
//	����
	public void play(String _readLine){
		
		readLine = _readLine;

		int position = Integer.parseInt(readLine);
		int chessY = position/15;
		int chessX = position - chessY * 15;

		new PlayChess().play(chessX, chessY, Data.oppoChess);	
		
	}

//	����
	public void chat(String _readLine){
		
		readLine = _readLine;
		//spilt��������ͨ����������������ַ���
		String s[] = readLine.split("&");
		String message = s[0];
		String who = s[1];
		
		MessageManager.getInstance().addMessage(who + "˵��" + message);
	}

//	����
	public void operation(String _readLine){
		
		readLine = _readLine;
		
//		�������
		if(readLine.substring(0,5).equals("BACK:")){
			
			Data.wait = false;
			
			if(readLine.substring(5).substring(0,4).equals("ONE:")){
				
				int position = Integer.parseInt(readLine.substring(9));
				
				int y = position / 15;
				int x = position - 15 * y;
				
//				������Ȩ
				if(Data.turn == Data.myChess){
					
					Data.turn = Data.oppoChess;
					MessageManager.getInstance().addMessage("�ȴ��Է����塣��");
					
				}
				else{
					if(Data.turn == Data.oppoChess){
						
						Data.turn = Data.myChess;
						
						MessageManager.getInstance().addMessage("�������塣��");
					}
				}
				
//				��������
				Data.chessBoard[x][y] = 0;
				
				ChessBoardCanvas mapCanvas = GameFrame.getInstance().getGamePanel().getMapCanvas();
				mapCanvas.paintMapImage();
				mapCanvas.repaint();
			}
			//���л���
			else{
				String str = readLine.substring(9);
				String [] s = str.split("&");
				
				int position1 = Integer.parseInt(s[0]);
				int position2 = Integer.parseInt(s[1]);
				
				int y1 = position1 / 15;
				int y2 = position2 / 15;
				int x1 = position1 - y1 * 15;
				int x2 = position2 - y2 * 15;
				
				Data.chessBoard[x1][y1] = 0;
				Data.chessBoard[x2][y2] = 0;
				
				ChessBoardCanvas mapCanvas = GameFrame.getInstance().getGamePanel().getMapCanvas();
				mapCanvas.paintMapImage();
				mapCanvas.repaint();
			}
		}
		
//		�Է�����
		if(readLine.substring(0,5).equals("ESCA:")){
			Data.oppoId = 0;
			Data.started = false;
			Data.ready = false;
			Data.chessBoard = new int[15][15];
			Data.last = -1;
			Data.myChess = 0;
			Data.oppoChess = 0;
			
			ChessBoardCanvas mapCanvas = GameFrame.getInstance().getGamePanel().getMapCanvas();
			mapCanvas.paintMapImage();
			mapCanvas.repaint();
			
			JOptionPane.showMessageDialog(null, "�Է������ˡ�������");
		}
		
//		�Է��뿪
		if(readLine.substring(0,5).equals("QUIT:")){
			Data.oppoId = 0;
			Data.started = false;
			Data.ready = false;
			Data.chessBoard = new int[15][15];
			Data.last = -1;
			Data.myChess = 0;
			Data.oppoChess = 0;
			
			ChessBoardCanvas mapCanvas = GameFrame.getInstance().getGamePanel().getMapCanvas();
			mapCanvas.paintMapImage();
			mapCanvas.repaint();
			
			MessageManager.getInstance().addMessage("�Է��뿪��,���������ѡ����֣�");
		}
		
//		�յ���ս
		if(readLine.substring(0, 5).equals("CHAL:")){
			
			readLine = readLine.substring(5);
			
			String [] s = readLine.split("-");
			int from = Integer.parseInt(s[0]);
			
			int value = JOptionPane.showConfirmDialog(null,"��ҡ�" + readLine +
					"����ս�㣬�Ƿ���У�" ,"�յ���ս",JOptionPane.YES_NO_OPTION);
			
//			������ս
			if(value == JOptionPane.YES_OPTION){
				
				IOManager.getInstance().getPs().println("REPL:CHAL:" + from + "&YES");
				
				Data.oppoId = from;
				
				GameFrame.getInstance().getFunctionPanel().getPlayerPanel().getOpponentInfo().setText("Ŀǰ���֣�" + readLine);
				
				JOptionPane.showMessageDialog(null, "�������˶Է�����ս���밴����ʼ����ť��ʼ��Ϸ��");
				
				MessageManager.getInstance().addMessage("�������˶Է�����ս���밴����ʼ����ť��ʼ��Ϸ��");

			}
			
//			�ܾ���ս
			else{
				IOManager.getInstance().getPs().println("REPL:CHAL:" + from + "&NO");
				
				JOptionPane.showMessageDialog(null, "�����ˣ�");
				
				MessageManager.getInstance().addMessage("���ˡ�����");
			}
		}
		
	}
	
	
//	�ظ�
	public void reply(String _readLine){
		
		readLine = _readLine;
		
//		����ظ�
		
		if(readLine.substring(0,5).equals("BACK:")){
			
			MessageManager.getInstance().addMessage("�Է��ܾ����壡");
			
			Data.wait = false;
		}
//		��ս�ظ�
		if(readLine.substring(0, 5).equals("CHAL:")){
			
			readLine = readLine.substring(5);
			
			String s [] = readLine.split("&");
			String challenged = s[0];
			String result = s[1];
			
//			�Է�������ս
			if(result.equals("YES")){
				
				String s2 [] = challenged.split("-");
				int uid = Integer.parseInt(s2[0]);
				
				JOptionPane.showMessageDialog(null, "��ҡ�" + challenged + "��������������ս���밴����ʼ����ť��ʼ��Ϸ��");
				

//				���ö���id
				Data.oppoId = uid;
				
//				���ö���״̬����Ϣ
				GameFrame.getInstance().getFunctionPanel().getPlayerPanel().getOpponentInfo().setText("Ŀǰ���֣�" + challenged);
				
				MessageManager.getInstance().addMessage("�Է�������������ս���밴����ʼ����ť��ʼ��Ϸ��");
			}
			
//			�Է��ܾ���ս
			if(result.equals("NO")){
				
				JOptionPane.showMessageDialog(null, "��ҡ�" + challenged + "�������ˡ��������ҽ�����ս������");
			}			
		}
		
	}
	
//	������
	public void addList(String _readLine) {
		
		readLine = _readLine;		
		
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
			
				@Override
				public void run() {
					ListManager.getInstance().addPlayer(readLine);
				}
			});
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		} catch (InvocationTargetException e) {
		
			e.printStackTrace();
		}
	}
	
//	ɾ�����
	public void delList(String _readLine) {
		
		readLine = _readLine;		
		
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
			
				@Override
				public void run() {
					ListManager.getInstance().removePlayer(readLine);
				}
			});
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		} catch (InvocationTargetException e) {
		
			e.printStackTrace();
		}
	}
	
//	�����б�
	public void updateList(String _readLine) {
		
		readLine = _readLine;		
		
		try {
			//���� doRun.run() �� AWT �¼�ָ���߳���ͬ��ִ�С�
			//�����й���� AWT �¼��������꣨Ȼ�󣩷��� doRun.run() ֮ǰ���˵��ý���������״̬��
			//�˷���Ӧ����Ӧ�ó����߳���Ҫ���¸� GUI ʱʹ�á�
			SwingUtilities.invokeAndWait(new Runnable() {
			
				@Override
				public void run() {
						ListManager.getInstance().clearList();
						String [] s = readLine.split("&");
						for(int i = 0 ; i < s.length; i ++){

							ListManager.getInstance().addPlayer(s[i]);
					}
				}
			});
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		} catch (InvocationTargetException e) {
		
			e.printStackTrace();
		}
	}
	
//	�޸��Լ��ǳ�
	public void changeName(String _readLine) {
		
		readLine = _readLine;
		
		String[] str = readLine.split("-");
		int myId = Integer.parseInt(str[0]);
		
		Data.myId = myId;
		Data.myName = str[1];
		
		MessageManager.getInstance().addMessage("�ǳ��޸ĳɹ���");

	}
	
//	���±����ǳ�
	public void updateName(String _readLine) {
		
		readLine = _readLine;
		
		DefaultListModel model = (DefaultListModel) ListManager.getInstance().getListModel();
		
		String s1;
		String s2;
		String[] str1;
		String[] str2;
		
		s2 = readLine;
		str2 = s2.split("-");
		
		for(int i = 0; i < model.size(); i++){
			
			s1 = (String) model.elementAt(i);
			str1 = s1.split("-");
			
			if (str1[0].equals(str2[0])){
				
				model.removeElementAt(i);
				ListManager.getInstance().addPlayer(readLine);
				
			}
		}
	}

//	ʤ��
	public void win() {
		JOptionPane.showMessageDialog(null, "����������Ӯ����");
		Data.turn = 0;
		Data.started = false;
	}

//	ʧ��
	public void lose() {
		JOptionPane.showMessageDialog(null, "�ò˰������˰ɣ�");
		Data.turn = 0;
		Data.started = false;
	}


	
//	�յ���������
	public void askFor(String _readLine) {
		
		readLine = _readLine;
		
		if(readLine.substring(0, 5).equals("BACK:")){
			
			int value = JOptionPane.showConfirmDialog(null, "�Է�������壬�Ƿ�ͬ�⣿","�����������" ,JOptionPane.YES_NO_OPTION );
			
			if(value == JOptionPane.YES_OPTION){
				IOManager.getInstance().getPs().println("REPL:BACK:YES");
				
			}
			else{
				IOManager.getInstance().getPs().println("REPL:BACK:NO");
				
			}
		}
		
	}

	
}
