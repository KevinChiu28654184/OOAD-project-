package case_study;

import java.awt.Color;
import java.awt.Graphics;

public class association_line extends baseline
{
	public association_line()
	{
		
	}
	@Override
	public void create_line(Graphics g) 
	{
		// 獲取起點和終點的座標
        int startX = start.getX();
        int startY = start.getY();
        int endX = end.getX();
        int endY = end.getY();
        
        // 畫線段
        g.setColor(Color.BLACK);
        g.drawLine(startX, startY, endX, endY);
        
        double angle = Math.atan2(endY - startY, endX - startX);
        int arrowLength = 15;

        // 畫箭頭頭部
        int arrowEndX = (int) (endX - arrowLength * Math.cos(angle - Math.PI / 6));
        int arrowEndY = (int) (endY - arrowLength * Math.sin(angle - Math.PI / 6));
        g.drawLine(endX, endY, arrowEndX, arrowEndY);
        
        arrowEndX = (int) (endX - arrowLength * Math.cos(angle + Math.PI / 6));
        arrowEndY = (int) (endY - arrowLength * Math.sin(angle + Math.PI / 6));
        g.drawLine(endX, endY, arrowEndX, arrowEndY);
	}
	
}
