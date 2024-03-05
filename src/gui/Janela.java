package gui;

import javax.swing.JFrame;

import gui.util.Style;


public class Janela{
	public Menu menu = new Menu();
	JFrame frame = new JFrame();
	
	public Janela (){
		frame.setTitle("Home");
		frame.setSize(1600, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLayout(Style.border);
		frame.getContentPane().setBackground(Style.bg);
		frame.setLocationRelativeTo(null);
		
		setMenu();
		
	}
	
	public void setMenu(){
		menu.set_jmenu_bar(frame);
	
		frame.add(menu.panel_menu, Style.border.WEST);
		frame.validate();
	}
	
}
