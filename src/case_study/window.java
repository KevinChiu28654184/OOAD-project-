package case_study;

import javax.swing.*;
import java.awt.*;

public class window extends JFrame {
	//window的建構子
    public window() {
        setTitle("OOAD Project");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(1000, 250, 700, 500);

        // 建立按鈕
        JButton select = new button();
        select.setText("select");
        select.setIcon(new ImageIcon("image\\button_select.png"));
        JButton association = new button();
        association.setText("association");
        association.setIcon(new ImageIcon("image\\button_association.png"));
        JButton generalization = new button();
        generalization.setText("generalization");
        generalization.setIcon(new ImageIcon("image\\button_generalization.png"));
        JButton composition = new button();
        composition.setText("composition");
        composition.setIcon(new ImageIcon("image\\button_composition.png"));
        JButton sheet = new button();
        sheet.setText("sheet");
        sheet.setIcon(new ImageIcon("image\\button_class.png"));
        JButton use_case = new button();
        use_case.setText("use case");
        use_case.setIcon(new ImageIcon("image\\button_use case.png"));
        // 建立畫布
        canvas canvas = new canvas();
        
     // 建立menu
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu edit_select = new EditMenu(canvas);     
        menuBar.add(fileMenu);
        menuBar.add(edit_select);
        setJMenuBar(menuBar);

        // 設定排版（按鈕新增到按鈕列，並垂直排列）
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(select);
        buttonPanel.add(association);
        buttonPanel.add(generalization);
        buttonPanel.add(composition);
        buttonPanel.add(sheet);
        buttonPanel.add(use_case);

        // 將按鈕和畫布顯示到畫面上
        Container contentPane = getContentPane();
        contentPane.add(canvas, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.WEST);
        
        //顯示
        setVisible(true);
    }

    //主函式
    public static void main(String[] args) {
        SwingUtilities.invokeLater(window::new);
        
    }
}
