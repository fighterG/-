package server.tool.check;

public class CheckX {
	/**
	 * ���X��
	 * 
	 * */
	
	public static int checkX(int line ,int row ,int id,int[][] chessBoard){
		
		int check = 0;
		int checkLeft = 0;
		int checkRight = 0;
		//ͨ����������λ����������жϣ�ֻҪ�������Ӿ���Ӯ������line-i�ж��Ƿ�С��-1����С��
		//-1�����˴��Ѿ�û�з��ӵĵط��ˣ���������Ѿ������ˡ��������-1�ͼ��������ж�
		//ͨ��id�жϵ�ǰ����һ���壬���Ǿͼ�һ
		//�ж��������������һ����ɫ�����ӣ�Ȼ����ӣ������һ������һ�������ж�x�᷽������������
		//ͬɫ���Ӹ���
		for(int i = 0; i < 5; i++){
			
			if(line - i > -1){
			
				if( chessBoard[line-i][row] == id){
					
					checkLeft++;
				}
				else{
					
					break;
				}
			}
		
		}
		
		for(int i = 1; i < 5; i++){
			
			if(line + i < 15 ){
				
				if(chessBoard[line+i][row] == id){
					
					checkRight++;
				}
				else{
					break;
				}
			}
			
		}
		
		check = checkLeft + checkRight;
		return (check);
	}
}
