package case_study;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EditMenu extends JMenu
{
	public JPanel Canvas ;
	public EditMenu(JPanel canvas)
	{
		//新增3個下拉式選單
		super("edit");
		JMenuItem group_select = new JMenuItem("group");
		JMenuItem ungroup_select = new JMenuItem("ungroup");
		JMenuItem name_select = new JMenuItem("change object name");	
		
		Canvas = canvas;
		//加入3個選項進去
		this.add(group_select);
		this.add(ungroup_select);
		this.add(name_select);
		
		name_select.addActionListener(e -> change_name(e));
		group_select.addActionListener(e -> group_object(e));
		ungroup_select.addActionListener(e -> ungroup_object(e));
	}
	//按下change name時改變名字
	public void change_name(ActionEvent e)
	{
		ArrayList<baseclass> change_name_list = new ArrayList<baseclass>();
		for(baseclass oj:canvas.class_list)
		{
			if(oj.clicked == true)
				change_name_list.add(oj);
		}
		for(group oj:canvas.group_list)
		{
			if(oj.clicked == true)
				change_name_list.add(oj);
		}
		if(change_name_list.size() == 0)
			return;
        String newName = JOptionPane.showInputDialog(null, "Enter New Name:");
        if(newName != null)
        {
        	for(baseclass oj:change_name_list)
        	{
        		oj.change_name(newName);
        	}
        }
        Canvas.repaint();
	}
	
	//按下group
	public void group_object(ActionEvent e)
	{
		ArrayList<baseclass> objected_group = new ArrayList<baseclass>();//暫存被選取的單一物件
		ArrayList<group> objected_group_Group = new ArrayList<group>();//暫存被選取的group
		Point p = new Point(0,0);//用來儲存能包圍整個group的頂點（左上角那點）
		Point minp = new Point(0,0);//整個group的最小點
		for(baseclass selected_object: canvas.class_list)
		{
			if(selected_object.clicked == true)
			{
				if(p.x < selected_object.maxX)
				{
					p.x = selected_object.maxX;
				}
				if(p.y < selected_object.maxY)
				{
					p.y = selected_object.maxY;
				}
				if(minp.x > selected_object.minX)
				{
					minp.x = selected_object.minX;
				}
				if(minp.y > selected_object.minY)
				{
					minp.y = selected_object.minY;
				}
				objected_group.add(selected_object);
			}
		}	
		for(group selected_object: canvas.group_list)
		{
			if(selected_object.clicked == true)
			{
				if(p.x < selected_object.maxX)
				{
					p.x = selected_object.maxX;
				}
				if(p.y < selected_object.maxY)
				{
					p.y = selected_object.maxY;
				}
				if(minp.x > selected_object.minX)
				{
					minp.x = selected_object.minX;
				}
				if(minp.y > selected_object.minY)
				{
					minp.y = selected_object.minY;
				}
				objected_group_Group.add(selected_object);
			}
		}
		if(objected_group_Group.size()+objected_group.size()<=1)
		{
			return;
		}
		canvas.group_list.add(new group(p));
		for(baseclass oj:objected_group)
		{
			canvas.group_list.get(canvas.group_list.size()-1).this_group_list.add(oj);
			canvas.class_list.remove(oj);
		}
		for(group oj:objected_group_Group)
		{
			canvas.group_list.get(canvas.group_list.size()-1).this_group_list_Group.add(oj);
			canvas.group_list.remove(oj);
		}
	}
	//按下ungroup
	public void ungroup_object(ActionEvent e)
	{
		Iterator<group> groupiterator = canvas.group_list.iterator();
		group tmp = null;
		while(groupiterator.hasNext())
		{
			tmp = groupiterator.next();
			if(tmp != null)
			{
				if(tmp.clicked == true)
				{
					groupiterator.remove();
					break;
				}
			}	
		}
		if(tmp != null)
		{
			canvas.class_list.addAll(tmp.this_group_list);
			canvas.group_list.addAll(tmp.this_group_list_Group);
		}
	}
}
