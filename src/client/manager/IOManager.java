package client.manager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

public class IOManager {
	/**
	 * 获取输入输出流
	 */
	//声明为成员变量
	private static IOManager instance = null;
	//对outputstream进行包装，printstream中有写出换行的方法
	private PrintStream ps;
	//对inputstream进行包装
	private BufferedReader br;
	
	private IOManager(){
		
	}
	//通过getInstance()方法对iomanager的对象进行初始化
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
	//获取到的输出流
	public void setPs(OutputStream os){
		ps = new PrintStream(os);
	}
	//获取到的输入流
	public void setBr(InputStreamReader isr){
		br  = new BufferedReader(isr);
	}
}
