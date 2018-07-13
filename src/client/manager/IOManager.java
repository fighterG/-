package client.manager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

public class IOManager {
	/**
	 * ��ȡ���������
	 */
	//����Ϊ��Ա����
	private static IOManager instance = null;
	//��outputstream���а�װ��printstream����д�����еķ���
	private PrintStream ps;
	//��inputstream���а�װ
	private BufferedReader br;
	
	private IOManager(){
		
	}
	//ͨ��getInstance()������iomanager�Ķ�����г�ʼ��
	public static IOManager getInstance(){
		
		if(instance == null){
			
			instance = new IOManager();
		}
		return instance;
	}
	
	public PrintStream getPs(){
		return ps;
	}
	
	public BufferedReader getBr(){
		return br;
	}
	//��ȡ���������
	public void setPs(OutputStream os){
		ps = new PrintStream(os);
	}
	//��ȡ����������
	public void setBr(InputStreamReader isr){
		br  = new BufferedReader(isr);
	}
}
