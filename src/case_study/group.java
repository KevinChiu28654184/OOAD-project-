package case_study;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class group extends baseclass
{
	public ArrayList<baseclass> this_group_list = new ArrayList<baseclass>();//儲存此group裡有幾個單一物件
	public ArrayList<group> this_group_list_Group = new ArrayList<group>();//儲存此group裡有幾個group
	public group(Point p) 
	{
		super(p);
		//儲存這個物件的四點頂點
		cp = new ArrayList<>();
		//儲存這個物件的四點頂點
	}
	
	@Override
	//創造group
	public void create_class(Graphics g) 
	{
		for(baseclass object:this_group_list)
		{
			object.create_class(g);
		}
		for(baseclass object:this_group_list_Group)
		{
			object.create_class(g);
		}
	}

	@Override
	//將整個group變成點擊狀態
	public void click() 
	{
		for(baseclass object:this_group_list)
		{
			object.click();
			
		}	
		for(baseclass object:this_group_list_Group)
		{
			object.click();
		}	
		this.clicked = true;
	}
	
	@Override
	//移動整個物件
	public void move(int dx, int dy) 
	{
		for(baseclass object:this_group_list)
		{
			object.move(dx, dy);
		}
		for(baseclass object:this_group_list_Group)
		{
			object.move(dx, dy);
		}
		this.location.x = this.location.x + dx;
		this.location.y = this.location.y + dy;
	}

	@Override
	//改名整個group
	public void change_name(String name) 
	{
		for(baseclass object:this_group_list)
		{
			object.change_name(name);
		}
		for(baseclass object:this_group_list_Group)
		{
			object.change_name(name);
		}
		this.Name = name;
		this.name_visible = true;
	}

	@Override
	//取消整個物件的點擊
	public void unclick() 
	{
		for(baseclass object:this_group_list)
		{
			object.unclick();
		}
		for(baseclass object:this_group_list_Group)
		{
			object.unclick();
		}
		this.clicked = false;
	}

	@Override
	//偵測這個group是不是被點擊到
	public void detected(Point p) 
	{
		
		for(baseclass object:this_group_list)
		{
			object.detected(p);
		}
		for(baseclass object:this_group_list_Group)
		{
			object.detected(p);
		}
		
		for(baseclass object:this_group_list)
		{
			if(object.clicked == true)
			{
				this.click();
			}
		}
		for(baseclass object:this_group_list_Group)
		{
			if(object.clicked == true)
			{
				this.click();
			}
		}
		if(this.clicked == false)
		{
			this.unclick();
		}
	}

}
