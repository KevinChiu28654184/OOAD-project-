package case_study;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

public abstract class baseclass 
{
	Point location;
	public int width = 80;
	public int height = 90;
	public ArrayList<connectionPort> cp;//儲存四個點
	//四個頂點的位置
	public int maxX;
	public int minX;
	public int maxY;
	public int minY;
	
	public String Name;//名字
	public boolean name_visible = false;//
	public boolean clicked = false;//確認是否被點擊
	public JPanel Canvas;
	public baseclass(Point p)//預設左上角位置
	{
		location = p;
	}
	//創造物件
	public abstract void create_class(Graphics g);
	//儲存點擊狀態
	public abstract void click();
	//取消點擊
	public abstract void unclick();
	//移動
	public abstract void move(int dx,int dy);
	//改名
	public abstract void change_name(String name); 
	//偵測是否被選取
	public abstract void detected(Point p);
}
