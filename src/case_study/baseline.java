package case_study;

import java.awt.Graphics;

public abstract class baseline 
{
	public connectionPort start;//線的起點
	public connectionPort end;//線的終點
	public baseclass start_class;//線的起始物件
	public baseclass end_class;//線的終點物件
	public baseline()
	{
		
	}
	public abstract void create_line(Graphics g);
}
