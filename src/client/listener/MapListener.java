package client.listener;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import client.data.Data;
import client.manager.MessageManager;
import client.net.PlayChess;
import client.ui.ChessBoardCanvas;


public class MapListener extends MouseAdapter {
	/**
	 * 监听棋盘
	 */
	
	@Override
	//监听鼠标按下
	public void mousePressed(MouseEvent e) {
		//返回最初发生的事件的对象
		ChessBoardCanvas canvas = (ChessBoardCanvas)e.getSource();
		
           //判断是否登陆
		if(Data.connected){
            //判断是否选择了对手
			if(Data.oppoId != 0){
				//判断是否已经准备好
				if(Data.ready == true){
					//判断是否已经开始
					if(Data.started){
						//判断是否轮到自己落棋
						if(!Data.wait){
							if(Data.turn == Data.myChess){
                             //	判断是否在棋盘范围内
								if(e.getX() < canvas.getMapWidth() - 6 && e.getY() < canvas.getHeight() - 7 ){
									
									int chessX;
									int chessY;
									//把棋子放在交叉点上
									chessX = e.getX() / 35;
									chessY = e.getY()/ 35;
									
                                    //判断此处是否已有棋子
									if(Data.chessBoard[chessX][chessY] == 0){
										
										new PlayChess().play(chessX, chessY , Data.myChess);

									}
									else{
										
										MessageManager.getInstance().addMessage("不能下在这里。。");
									}
								}
								

							}
                        //	若为对方落子，则提示我不能下棋
	 						else{

								if(Data.turn == Data.oppoChess){
									
									MessageManager.getInstance().addMessage("没轮到你下。。。");
								}
								else{
//									若游戏已经结束
									if(Data.turn == 0){
										MessageManager.getInstance().addMessage("已经结束啦！");
									}
								}

							}
						}
						//若我已按下开始则提示等待对方按下开始按钮
						else{
							MessageManager.getInstance().addMessage("等待对方回复。。。");
						}
					}
					//若没有准备好则提醒等待对方准备
					else{
						MessageManager.getInstance().addMessage("等待对方准备。。。");
					}
				}
				//若未开始则提示先点击开始
				else{
					MessageManager.getInstance().addMessage("先点击“开始”吧！");
				}
			}
			//若没有选择对手则提示先选择对手
			else{
				MessageManager.getInstance().addMessage("选个对手吧！");
			}
		}
		
		//若未登陆则提示先登陆
		else{
			
			MessageManager.getInstance().addMessage("请先登陆！");
		}
	}
      //设置鼠标进入事件  
	@Override
	public void mouseEntered(MouseEvent e3) {
		
		ChessBoardCanvas canvas = (ChessBoardCanvas)e3.getSource();
		
		if(!Data.wait){
			//若轮到自己落棋就将光标设置为指导光标图像（设置为手型光标）
			canvas.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		    //若不轮自己下棋就设置为等待光标类型
		else{
			canvas.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		}
		
		
	}
	//设置鼠标离开事件，若鼠标离开设置为默认光标类型
	@Override
	public void mouseExited(MouseEvent e4) {
		ChessBoardCanvas canvas = (ChessBoardCanvas)e4.getSource();
		canvas.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
	}
	
	
}
