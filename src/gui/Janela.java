package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.util.Style;
import gui.estudante.*;
import xutil.Utilitario;

public class Janela extends JFrame implements Utilitario{
	public Menu menu = Utilitario.menu_ref;
	private ListaNominalEstudante lista_estudante = Utilitario.lista_estudante_ref;
	//public JFrame frame = new JFrame();
	private String __title = "Turma Dos Revoltados";
	
	public Janela (){
		this.setTitle(__title);
		this.setSize(1600, 900);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(Style.border);
		this.getContentPane().setBackground(Style.bg);
		this.setLocationRelativeTo(null);
		
		this.setMenu();
		//this.addMenuSide();
		
	}
	
	public void setMenu(){
		menu.set_jmenu_bar(this);
		
		this.validate();
	}
	
	/*
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
	
	public int addListaEstudante(){
		this.getContentPane().add(lista_estudante, Style.border.CENTER);
		this.getContentPane().revalidate();
		this.getContentPane().repaint();
		
		return 1;
	}
	
	public int removeListaEstudante(){
		this.getContentPane().remove(lista_estudante);
		this.getContentPane().revalidate();
		this.getContentPane().repaint();
		
		return -1;
	}
	
	public int addCadastrarEstudante(){
		this.getContentPane().add(Utilitario.cadastrar_estudante_ref, Style.border.CENTER);
		this.getContentPane().revalidate();
		this.getContentPane().repaint();
		
		return 1;
	}
	
	public int removeCadastrarEstudante(){
		this.getContentPane().remove(Utilitario.cadastrar_estudante_ref);
		this.getContentPane().revalidate();
		this.getContentPane().repaint();
		
		return -1;
	}
	*/
	public int addConteiner(JPanel panel, String layout, String title){
		this.setTitle(this.__title + " - " + title);
		this.getContentPane().add(panel, layout);
		this.getContentPane().revalidate();
		this.getContentPane().repaint();
		
		return 1;		
	}
	
	public int removeConteiner(JPanel panel){
		this.setTitle(__title);
		this.getContentPane().remove(panel);
		this.getContentPane().revalidate();
		this.getContentPane().repaint();
		
		return -1;
	}
}
