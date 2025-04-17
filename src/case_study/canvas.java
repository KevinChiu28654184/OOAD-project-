package case_study;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class canvas extends JPanel 
{

	static String button_select = "select";//目前的按鈕選擇
	static baseclass select;//用來儲存現在點擊的物件
	static ArrayList<baseclass> class_list = new ArrayList<baseclass>();//用來儲存sheet跟use case物件
	static ArrayList<baseline> line_list = new ArrayList<baseline>();//用來儲存line物件
	static ArrayList<group> group_list = new ArrayList<group>();//用來儲存group物件
	private Point start;//游標起點
	private Point end;//游標終點
	private boolean group_mode = false ;//確認現在是否是大範圍選取狀態
    public canvas() 
    {
        this.setBackground(Color.white);// 畫布背景為白色
        this.setBounds(20,20,20,20);//設定畫布邊界
        this.addMouseListener(new MouseAdapter() 
        {
        	//放開滑鼠後
        	@Override
        	public void mouseReleased(MouseEvent e)
        	{
        		switch(button_select) {
        		
        		case "select":
        			group_mode = false;
        			start = null;
        			end = null;
        			break;
        		case "association"://將線畫出來
        			select = detect(e.getPoint());
        			if(select == null)
        			{
        				break;
        			}
        			line_list.get(line_list.size()-1).end_class = select;
        			line_list.get(line_list.size()-1).end = nearest_port(e.getPoint(),select);
        			break;
        		case "composition"://將線畫出來
        			select = detect(e.getPoint());
        			if(select == null)
        			{
        				break;
        			}
        			line_list.get(line_list.size()-1).end_class = select;
        			line_list.get(line_list.size()-1).end = nearest_port(e.getPoint(),select);
        			break;
        		case "sheet":
        			//創造並儲存sheet
        			class_list.add(new baseclass_sheet(e.getPoint()));
        			break;
        		case "use case":
        			//創造並儲存use case
        			class_list.add(new baseclass_use_case(e.getPoint()));
        			break;
        		case "generalization"://將線畫出來
        			select = detect(e.getPoint());
        			if(select == null )
        			{
        				break;
        			}
        			line_list.get(line_list.size()-1).end_class = select;
        			line_list.get(line_list.size()-1).end = nearest_port(e.getPoint(),select);
        			break;
        		}
        		repaint();//call paintComponent
        	}
        	@Override
        	//按下滑鼠後
        	public void mousePressed(MouseEvent e)
        	{
        		group_mode = false;
        		switch(button_select) {
        		case "select"://先清空被物件狀態，再重新判斷點擊哪裡
        			clean_select(null);
        			select = detect(e.getPoint());
        			start = e.getPoint();
        			end = e.getPoint();
        			//System.out.println(select);
        			clean_select(select);
        			if(select == null)
        			{
        				group_mode = true;
        				break;
        			}	        			
        			object_drag(end,select,start);
        			break;
        		case "association"://創造並儲存線
        			select = detect(e.getPoint());
        			if(select == null)
        			{
        				break;
        			}
        			line_list.add(new association_line());
        			line_list.get(line_list.size()-1).start_class = select;
        			line_list.get(line_list.size()-1).start = nearest_port(e.getPoint(),select);
        			break;
        		case "composition"://創造並儲存線
        			select = detect(e.getPoint());
        			if(select == null)
        			{
        				break;
        			}
        			line_list.add(new composition_line());
        			line_list.get(line_list.size()-1).start_class = select;
        			line_list.get(line_list.size()-1).start = nearest_port(e.getPoint(),select);
        			break;
        		case "generalization"://創造並儲存線
        			select = detect(e.getPoint());
        			if(select == null)
        			{
        				break;
        			}
        			line_list.add(new generalization_line());
        			line_list.get(line_list.size()-1).start_class = select;
        			line_list.get(line_list.size()-1).start = nearest_port(e.getPoint(),select);
        			break;
        		}
        	}
        });
        this.addMouseMotionListener(new MouseMotionAdapter() 
        {
        	@Override
        	//拖曳滑鼠時
        	public void mouseDragged(MouseEvent e)
        	{
        		
        		switch(button_select) {
        		case "select"://不斷更新滑鼠游標位置
        			end = e.getPoint();
        			if(select == null)
        			{
        				repaint();
        				break;
        			}
        			object_drag(end,select,start);
        			start = e.getPoint();
        			break;
        		}
        	}
        });
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        // 在這裡畫畫
        draw_graphic(g);
        if(select != null && button_select == "select")
        {
        	select.click();
        }
        draw_line(g);
        if(end != start && group_mode == true)
        {
        	Frame_selection(start,end,g);
        }
    }
    
    //畫非線條的物件
    private void draw_graphic(Graphics g)
    {
    	for(baseclass draw_class: class_list)
    	{
    		draw_class.create_class(g);
    	}
    	for(baseclass draw_class: group_list)
    	{
    		draw_class.create_class(g);
    	}
    }
    //偵測滑鼠點擊時點在哪個物件上
    private baseclass detect(Point p)
    {
    	baseclass last_select = null;
    	for(baseclass oj:class_list)
    	{
    		oj.detected(p);
    		if(oj.clicked == true)
    		{
    			last_select = oj;
    		}
    	}
    	for(baseclass oj:group_list)
    	{
    		oj.detected(p);
    		if(oj.clicked == true)
    		{
    			last_select = oj;
    		}
    	}
    	return last_select;
    }
    //畫線
    private void draw_line(Graphics g)
    {
    	
    	for(baseline draw_line: line_list)
    	{
    		if(draw_line.start == null ||draw_line.end == null || draw_line.start == draw_line.end || draw_line.start_class == draw_line.end_class)
    		{
    			continue;
    		}
    		draw_line.create_line(g);
    	}
    }
    //找出點擊或放開時最靠近的那個port
    private connectionPort nearest_port(Point p,baseclass select)
    {
    	double minlen = 99999999;
    	connectionPort near_port = null;
    	for(connectionPort port:select.cp)
    	{
    		double len = Math.pow(p.x-port.getX(),2)+Math.pow(p.y-port.getY(),2);
    		if(len<minlen)
    		{
    			minlen = len;
    			near_port = port;
    		}
    	}
    	return near_port;
    }
    //移動物件（被拖曳時會呼叫）
    private void object_drag(Point mouse_end,baseclass select,Point mouse_start)
    {
    	int dx = mouse_end.x - mouse_start.x;
    	int dy = mouse_end.y - mouse_start.y;
    	select.move(dx, dy);
        repaint();
    }
    //選取選框內的所有物件
    private void Frame_selection(Point mouse_start,Point mouse_end,Graphics g)
    {
    	int x = Math.min(mouse_start.x, mouse_end.x);
        int y = Math.min(mouse_start.y, mouse_end.y);
        int width = Math.abs(mouse_start.x - mouse_end.x);
        int height = Math.abs(mouse_start.y - mouse_end.y);

        g.setColor(new Color(192, 192, 192, 128));
        g.fillRect(x, y, width, height);
        g.setColor(Color.GRAY);
        g.drawRect(x, y, width, height);
        Frame_object_selection(mouse_start,mouse_end);
    }
    //將選取框內的所有物件都變成選取狀態
    private void Frame_object_selection(Point mouse_start,Point mouse_end)
    {
    	boolean object_select = true;
    	int minx = Math.min(mouse_start.x, mouse_end.x);
    	int miny = Math.min(mouse_start.y, mouse_end.y);
    	int maxx = Math.max(mouse_start.x, mouse_end.x);
    	int maxy = Math.max(mouse_start.y, mouse_end.y);
    	for(baseclass object:class_list)
    	{
    		if(object.minX>minx && object.minY>miny && object.maxX<maxx && object.maxY<maxy)
    		{
    			object.click();
    		}
    		else
    		{
    			object.unclick();
    		}
    	}
    	for(group OJ:group_list)
    	{
    		for(baseclass object:OJ.this_group_list)
    		{
    			if(object.minX>minx && object.minY>miny && object.maxX<maxx && object.maxY<maxy)
    			{
    				
    			}
    			else
    			{
    				object_select = false;
    			}
    		}
    		for(group oj:OJ.this_group_list_Group)
    		{
    			for(baseclass object:oj.this_group_list)
        		{
        			if(object.minX>minx && object.minY>miny && object.maxX<maxx && object.maxY<maxy)
        			{
        			}
        			else
        			{
        				object_select = false;
        			}
        		}
    		}
    		if(object_select == true)
    		{
    			OJ.click();
    		}
    		else
    		{
    			OJ.unclick();
    		}
    	}
    }
    //清空不是select的物件選取
    private void clean_select(baseclass select)
    {
    	for(baseclass oj:class_list)
    	{
    		if(oj != select)
    		{
    			oj.unclick();
    		}
    	}
    	for(group oj:group_list)
    	{
    		if(oj != select)
    		{
    			oj.unclick();
    		}
    	}
    	repaint();
    }
}
