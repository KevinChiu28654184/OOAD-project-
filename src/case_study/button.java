package case_study;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.UIManager;

public class button extends JButton 
{
	public button()
	{
		this.addActionListener(e ->actionPerformed(e));
	}
	public void actionPerformed(ActionEvent e)
	{
		JButton clickedButton = (JButton) e.getSource();//儲存現在點擊按鈕的所有資訊
		canvas.select = null;//點擊其他按鈕時，將所有被選取的物件取消選取
		canvas.button_select = clickedButton.getText();
		 // 恢复其他按钮的颜色
		for (int i = 0; i < clickedButton.getParent().getComponentCount(); i++) 
		{
            JButton button = (JButton) clickedButton.getParent().getComponent(i);
            if (button != clickedButton) 
            {
                button.setBackground(UIManager.getColor("Button.background"));
            }
        }
		//按按鈕時顏色設成灰色
		clickedButton.setBackground(Color.gray);
		
	}
}
