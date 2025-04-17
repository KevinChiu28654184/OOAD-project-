package case_study;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class baseclass_use_case extends baseclass
{
	public baseclass_use_case(Point p) 
	{
		super(p);	
		this.height = 50;
		cp = new ArrayList<>();
		//將四個點的位置加進去
		cp.add(new connectionPort(location.x+(this.width/2),location.y));
		cp.add(new connectionPort(location.x+this.width,location.y+(this.height/2)));
		cp.add(new connectionPort(location.x+(this.width/2),location.y+this.height));
		cp.add(new connectionPort(location.x,location.y+(this.height/2)));
		//儲存這個物件的四點頂點
		minX = location.x;
		minY = location.y;
		maxX = location.x+this.width;
		maxY = location.y+this.height;
	}
	@Override
	public void create_class(Graphics g)
	{
		//畫物件
		 int x = location.x;
	     int y = location.y;
	     g.setColor(Color.GRAY);
	     g.fillOval(x, y, 80, 50);
	     g.setColor(Color.black);
	     g.drawOval(x, y, 80, 50);
	     
	     if(clicked == true && canvas.button_select == "select")
	        {
	        	for(connectionPort port:cp)
	    		{
	    			port.draw(g);
	    		}
	        }
	     
	     //如果名字是可顯示狀態（通常是改名後）把名字顯示出來
	     if(name_visible == true)
	        {
	    	 	//
	        	Font originalFont = g.getFont();
	            Font boldFont = originalFont.deriveFont(Font.BOLD);
	            g.setFont(boldFont);
	            //設定名字位置
	            FontMetrics fm = g.getFontMetrics();
	            int nameWidth = fm.stringWidth(Name); // Get width of the name
	            int nameX = x + (this.width - nameWidth) / 2; // Center the name horizontally
	            int nameY = y + fm.getAscent(); // Place the name just above the rectangle
	            g.drawString(Name, nameX, nameY);
	        }
	}
	@Override
	//被點擊時，將點擊狀態設為true
	public void click()
	{
		this.clicked = true;
	}
	@Override
	//移動物件位置
	public void move(int dx, int dy) 
	{
		
		this.location.x = this.location.x + dx;
		this.location.y = this.location.y + dy;
		minX = this.location.x;
		minY = this.location.y;
		maxX = this.location.x+this.width;
		maxY = this.location.y+this.height;
		//更改四個port
		for(connectionPort p:cp)
		{
			p.changeX(dx);
			p.changeY(dy);
		}
	}
	@Override
	//改名子並把顯示狀態改成可更改
	public void change_name(String name) 
	{
		Name = name;
		name_visible = true;
	}
	@Override//取消點擊狀態
	public void unclick() 
	{
		this.clicked = false;
		
	}
	@Override//偵測此物件是否被點擊
	public void detected(Point p) 
	{
		if(p.x>this.minX&&p.x<this.maxX&&p.y>this.minY&&p.y<this.maxY)
		{
			this.click();
		}
		else
		{
			this.unclick();
		}	
	}
}
