package case_study;

import java.awt.Color;
import java.awt.Graphics;

public class connectionPort 
{
	private int x;
	private int y;
	//設定x座標和y座標
	public connectionPort(int x,int y)
	{
		this.x = x;
		this.y = y;
	}
	//取得port的X座標
	 public int getX() 
	 {
	     return x;
	 }
	//取得port的Y座標
	 public int getY() 
	 {
	     return y;
	 }
	//更改port的X座標
	 public void changeX(int dx)
	 {
		 this.x = this.x + dx;
	 }
	//更改port的Y座標
	 public void changeY(int dy)
	 {
		 this.y = this.y + dy;
	 }
	//畫出這個點
	public void draw(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(x - 10 / 2, y - 10 / 2, 10, 10);
	}
	
}
