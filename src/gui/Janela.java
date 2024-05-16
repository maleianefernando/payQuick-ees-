package gui;

import javax.swing.JComponent;
import javax.swing.JFrame;

import gui.util.Style;
import xutil.Utilitario;

public class Janela extends JFrame implements Utilitario{
	//public Menu menu = Utilitario.menu_ref;
	public Menu menu = new Menu();
	//public JFrame frame = new JFrame();
	private String __title = "Emmanuel English School";
	
	public Janela (){
		
	}
	
	public void start(String user){
		this.setTitle(__title);
		this.setSize(1600, 900);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(Style.border);
		this.getContentPane().setBackground(Style.bg);
		this.setLocationRelativeTo(null);
		
		if(user.equalsIgnoreCase("administrador")){
			this.setAdmMenu();

		}else if(user.equalsIgnoreCase("professor")){
			this.setMenu();
			
		}
		//this.addMenuSide();
		System.out.println("Start");
	}

	public void setAdmMenu(){
		menu.set_adm_jmenu_bar(this);
		
		this.revalidate();
		this.repaint();
	}

	public void setMenu(){
		menu.set_jmenu_bar(this);
		
		this.revalidate();
		this.repaint();
	}

	public int addConteiner(JComponent panel, String layout, String title){
		this.getContentPane().removeAll();

		this.setTitle(this.__title + " | " + title);
		this.getContentPane().add(panel, layout);
		this.getContentPane().revalidate();
		this.getContentPane().repaint();
		
		return 1;		
	}
	
	public int addMenuSide(JComponent panel, String layout, String title){

		this.setTitle(this.__title + " - " + title);
		this.getContentPane().add(panel, layout);
		this.getContentPane().revalidate();
		this.getContentPane().repaint();
		
		return 1;		
	}

	public int removeConteiner(JComponent panel){
		this.setTitle(__title);
		this.getContentPane().remove(panel);
		this.getContentPane().revalidate();
		this.getContentPane().repaint();
		
		return -1;
	}
}
