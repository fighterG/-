package server.tool;

import java.io.IOException;
import java.io.PrintStream;

import server.tool.check.Check;

public class FightManager {
	
	/**
	 * �洢��ս��Ϣ
	 * �շ������ͻ��˵Ķ�ս��Ϣ
	 * �����ͻ��˹���һ��FightManager
	 * 
	 */
	boolean restartA = false;
	boolean restartB = false;
	
	int turn = 0;
	
	int BLACK = 1;
	int WHITE = -1;
	
	int playerA;			//����
	int playerB;			//����
	
	PrintStream psA = null;
	PrintStream psB = null;
	
	int lastWhite = -1;		//�������
	int lastBlack = -1;
	
	int [][] chessBoard = new int[15][15];
	
//	��ȡplayerA�������
	public PrintStream getPsA() {
		
		if(psA == null){
			
			try {
				
				psA = new PrintStream(HashMapManager.getInstance().getPlayer(playerA).socket.getOutputStream());
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		return psA;
	}
	
//	��ȡplayerB�Ĵ�ӡ��
	public PrintStream getPsB() {
		
		if(psB == null){
			
			try {
				
				psB = new PrintStream(HashMapManager.getInstance().getPlayer(playerB).socket.getOutputStream());
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		return psB;
	}
	
//	�������A��Id
	public void setPlayerA(int playerA) {
		
		this.playerA = playerA;
	}
	
//	�������B��Id
	public void setPlayerB(int playerB) {
		
		this.playerB = playerB;
	}

//	�ɷ�������ͻ��˷��Ϳ�ʼ��Ϣ
	public void sendStartMessage() {
		
		this.getPsA().println("SYST:��Ϸ��ʼ�������ӡ�����");
		this.getPsB().println("SYST:��Ϸ��ʼ���ȴ��Է����ӡ�����");
		
		turn = BLACK;
	}
	
//	��ʼ����
	public void startPlay(int playerA,int playerB) {
		
		this.setPlayerA(playerA);

		this.setPlayerB(playerB);
		
		this.getPsA().println("STAR:BLACK");
		this.getPsB().println("STAR:WHITE");
	}
	
//	����������Ϣ
	public void sendPlay(int from,int position) {
		
		if(from == playerA){
						
			int y = position / 15;
			int x = position - 15 * y;
			
			chessBoard [x][y] = BLACK;
			
			this.getPsB().println("PLAY:" + position);
			
			lastBlack = position;
			
			turn = WHITE;
			
//			����Ƿ�ʤ��
			if(this.checkWin(x, y, BLACK)){
				
				this.getPsA().println("UWIN:");
				this.getPsB().println("LOSE:");
			}
		
		}
		else{
			if(from == playerB){
				
				int y = position / 15;
				int x = position - 15 * y;
				
				chessBoard [x][y] = WHITE;
				
				this.getPsA().println("PLAY:" + position);
				
				lastWhite = position;
				
				turn = BLACK;
				
//				����Ƿ�ʤ��
				if(this.checkWin(x, y, WHITE)){
					
					this.getPsB().println("UWIN:");
					this.getPsA().println("LOSE:");
				}

			}
			
			else{
				
				MessageManager.getInstance().addMessage("����������Ϣ����"+ "PLAY:" + position);
				
				System.out.println("��Դid��" + from);
				System.out.println("playerAid:" + playerA);
				System.out.println("playerBid:" + playerB);
			}
		}		
	}
	
//	����Ƿ�ʤ��
	public boolean checkWin(int x ,int y ,int id){
		
		boolean isWin = new Check().check(x,y,id,chessBoard);
		return isWin;
		
	}
//	���¿�ʼ
	public void restart(int uid) {
		
		if(uid == playerA){
			
			if(restartA == false){
				
				restartA = true;
			}
		}
		else{
			if(restartB == false){
				restartB = true;
			}
		}
		
		if(restartA && restartB){
						
			psA = null;
			psB = null;
			
			chessBoard = new int[15][15];
			
			lastBlack = -1;
			lastWhite = -1;
			
			this.startPlay(playerB,playerA);
			this.sendStartMessage();
			
			restartA = false;
			restartB = false;
		}
	}

//	����
	public void back(int uid){
		
//		��ȡ����id
		int from = (uid == playerA)? playerB:playerA;
		
//		�ж�˭���������
		//��ֻ����һ���ͻ���������ǰ������
		//��˫������һ���ٻ����˫��������һ����
		if(from == playerA){
			//���ǶԷ��Ļغϻ���ֻ�ܻ�һ��
			//�����Լ��Ļغ���˫������һ�����Լ�ִ�ڣ�
			if(turn == BLACK){
				
				this.getPsA().println("OPER:BACK:TWO:" + lastBlack + "&" + lastWhite);
				this.getPsB().println("OPER:BACK:TWO:" + lastBlack + "&" + lastWhite);
				
				int y1 = lastBlack/15;
				int x1 = lastBlack - y1*15;
				this.chessBoard[x1][y1] = 0;
				
				int y2 = lastWhite/15;
				int x2 = lastWhite - y2*15;
				this.chessBoard[x2][y2] = 0;
				
			}
			else{
				
				this.getPsA().println("OPER:BACK:ONE:" + lastBlack);
				this.getPsB().println("OPER:BACK:ONE:" + lastBlack);
				
				int y = lastBlack/15;
				int x = lastBlack - y*15;
				this.chessBoard[x][y] = 0;
				
				
				
				turn = BLACK;
			}
		}
		else{
			if(turn == BLACK){
				
				this.getPsA().println("OPER:BACK:ONE:" + lastWhite);
				this.getPsB().println("OPER:BACK:ONE:" + lastWhite);
				
				int y = lastBlack/15;
				int x = lastBlack - y*15;
				this.chessBoard[x][y] = 0;
				
				turn = WHITE;
			}
			else{
				
				this.getPsB().println("OPER:BACK:TWO:" + lastBlack + "&" + lastWhite);
				this.getPsA().println("OPER:BACK:TWO:" + lastBlack + "&" + lastWhite);
				
				int y1 = lastBlack/15;
				int x1 = lastBlack - y1*15;
				this.chessBoard[x1][y1] = 0;
				
				int y2 = lastWhite/15;
				int x2 = lastWhite - y2*15;
				this.chessBoard[x2][y2] = 0;
				
			}
		}
	}
//������壬��A��ҷ�����������B��ҽ�������ʾ�������󣬷�֮��Ȼ
	public void askForBack(int from) {
		if(from == playerA){
			this.getPsB().println("ASK4:BACK:");
		}
		else{
			this.getPsA().println("ASK4:BACK:");
		}
		
	}
    //�ܾ����壬��a��Ҿܾ��������b��ҽ�������ʾ�ܾ����壬��֮��Ȼ��
	public void refuseBack(int from) {
		
		if(from == playerA){
			
			this.getPsB().println("REPL:BACK:NO");
			
		}
		else{
			this.getPsA().println("REPL:BACK:NO");
		}
		
	}
}