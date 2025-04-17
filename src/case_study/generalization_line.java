package case_study;

import java.awt.Color;
import java.awt.Graphics;

public class generalization_line extends baseline
{
	public generalization_line()
	{
		
	}
	@Override
	public void create_line(Graphics g) 
	{
		// 獲得起點和終點座標
        int startX = start.getX();
        int startY = start.getY();
        int endX = end.getX();
        int endY = end.getY();
        
        // 畫線段
        g.setColor(Color.black);
        g.drawLine(startX, startY, endX, endY);
        
        double angle = Math.atan2(endY - startY, endX - startX);
        int arrowLength = 15;

        // 畫箭頭
        int[] arrowHeadX = new int[3];
        int[] arrowHeadY = new int[3];

        // 計算三角形箭頭三個頂點
        arrowHeadX[0] = endX;
        arrowHeadY[0] = endY;
        arrowHeadX[1] = (int) (endX - arrowLength * Math.cos(angle - Math.PI / 6));
        arrowHeadY[1] = (int) (endY - arrowLength * Math.sin(angle - Math.PI / 6));
        arrowHeadX[2] = (int) (endX - arrowLength * Math.cos(angle + Math.PI / 6));
        arrowHeadY[2] = (int) (endY - arrowLength * Math.sin(angle + Math.PI / 6));

        // 畫出箭頭
        g.setColor(Color.white);
        g.fillPolygon(arrowHeadX, arrowHeadY, 3);
        g.setColor(Color.black);
        g.drawPolygon(arrowHeadX, arrowHeadY, 3);
	}
}
