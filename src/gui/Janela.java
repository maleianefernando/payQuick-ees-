package gui;

import javax.swing.JFrame;

import gui.util.Style;


public class Janela extends JFrame {
	Menu menu = new Menu();
	
	public Janela (){
		this.setTitle("Home");
		this.setSize(1600, 900);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(Style.border);
		this.getContentPane().setBackground(Style.bg);
		this.setLocationRelativeTo(null);
		
		setMenu();
		
	}
	
	private void setMenu(){
		menu.menu_bar(this);
	
		this.add(menu, Style.border.NORTH);
		this.validate();
	}
}
