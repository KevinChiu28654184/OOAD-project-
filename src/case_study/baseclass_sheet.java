package case_study;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public  class baseclass_sheet extends baseclass
{
	
	public baseclass_sheet(Point p) 
	{
		super(p);
		
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
	//畫物件
	public void create_class(Graphics g)
	{
		//畫物件本人
		int x =  this.location.x;
        int y =  this.location.y;
        g.setColor(Color.GRAY);
        g.fillRect(x, y, this.width, this.height); 
        g.setColor(Color.black);
        g.drawRect(x, y, this.width, this.height);
        g.setColor(Color.BLACK);
        //畫線
        g.drawLine(x, y+(this.height/3), x + this.width, y+(this.height/3));
        g.drawLine(x, y+(this.height*2/3), x + this.width, y+(this.height*2/3));
        
        if(clicked == true && canvas.button_select == "select")
        {
        	for(connectionPort port:cp)
    		{
    			port.draw(g);
    		}
        }
        
        //如果是可顯示的狀態，把名字顯示
        if(name_visible == true)
        {
        	//設定名字的大小跟
        	Font originalFont = g.getFont();
            Font boldFont = originalFont.deriveFont(Font.BOLD);
            g.setFont(boldFont);
            //設定名字的位置
            FontMetrics fm = g.getFontMetrics();
            int nameWidth = fm.stringWidth(Name); // Get width of the name
            int nameX = x + (this.width - nameWidth) / 2; // Center the name horizontally
            int nameY = y + fm.getAscent(); 
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
	//更改名字並把名字變成可顯示
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
