package case_study;

import java.awt.Color;
import java.awt.Graphics;

public class composition_line extends baseline
{
	public composition_line()
	{
		
	}
	@Override
	public void create_line(Graphics g) 
	{
		//獲得起點跟終點座標
        int startX = start.getX();
        int startY = start.getY();
        int endX = end.getX();
        int endY = end.getY();
        
        //繪製線段
        g.setColor(Color.black);
        g.drawLine(startX, startY, endX, endY);
        
        double angle = Math.atan2(endY - startY, endX - startX);
        int arrowLength = 8;

        //菱形四個點座標
        int[] diamondX = new int[4];
        int[] diamondY = new int[4];

        // 左頂點
        diamondX[0] = (int) (endX - arrowLength * Math.cos(angle + Math.PI / 2));
        diamondY[0] = (int) (endY - arrowLength * Math.sin(angle + Math.PI / 2));
        // 上頂點
        diamondX[1] = (int) (endX - arrowLength * Math.cos(angle));
        diamondY[1] = (int) (endY - arrowLength * Math.sin(angle));
        // 右頂點
        diamondX[2] = (int) (endX + arrowLength * Math.cos(angle + Math.PI / 2));
        diamondY[2] = (int) (endY + arrowLength * Math.sin(angle + Math.PI / 2));
        // 下頂點
        diamondX[3] = (int) (endX + arrowLength * Math.cos(angle));
        diamondY[3] = (int) (endY + arrowLength * Math.sin(angle));

        // 繪製菱形
        g.setColor(Color.white);
        g.fillPolygon(diamondX, diamondY, 4);
        g.setColor(Color.black);
        g.drawPolygon(diamondX, diamondY, 4);
	}

}
