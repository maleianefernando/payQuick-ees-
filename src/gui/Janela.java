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
		
		this.setMenu();
		this.addMenuSide();
		
	}
	
	public void setMenu(){
		menu.set_jmenu_bar(this);
		this.validate();
	}
	
	public int addMenuSide(){
		this.getContentPane().add(menu, Style.border.WEST);
		this.getContentPane().revalidate();
		this.getContentPane().repaint();
		
		return 1;
	}
	
	public int removeMenuSide(){
		this.getContentPane().remove(menu);
		this.getContentPane().revalidate();
		this.getContentPane().repaint();
		
		return -1;
	}	
}
