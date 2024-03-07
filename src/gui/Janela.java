package gui;

import javax.swing.JFrame;

import gui.util.Style;
import xutil.Utilitario;

public class Janela extends JFrame implements Utilitario{
	public Menu menu = Utilitario.menu_ref;
	//public JFrame frame = new JFrame();
	
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
	
	public void setMenu(){
		menu.set_jmenu_bar(this);
	
		this.add(menu, Style.border.WEST);
		this.validate();
	}
	
}
