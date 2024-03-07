package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import gui.util.Style;
import xutil.Utilitario;

public class Menu extends JPanel implements ActionListener, Utilitario{
	public JPanel panel_menu = new JPanel();

	//side bar JMenuIems titles
	private String[] side_bar_items = new String[] {"Menu"};	//
	private String[] est_items = new String[] {"Lista Nominal", "Desempenho", "Menssalidades Em Atraso", "Disciplinas Lecionadas"};	//items of the menu 'estudante'
	private String[] func_items = new String[] {"Lista Nominal", "Salários", "Pendentes"};	//items of the menu 'funcionario'
	private String[] ajuda_items = new String[] {"Ajuda", "Quem Somos?"};	//items of the menu ajuda
	
	private JButton estudante_cadastrado, funcionario_cadastrado, cadastrar, turmas; 
	private JMenuBar jmenu_bar = new JMenuBar();
	private JMenu side_bar;
	private JMenu estudante;
	private JMenu funcionario;
	private JMenu ajuda;
	private JMenuItem[] est_JMenuItem = new JMenuItem[est_items.length];
	private JMenuItem[] func_JMenuItem = new JMenuItem[func_items.length];
	private JMenuItem[] ajuda_JMenuItem = new JMenuItem[ajuda_items.length];
	private JMenuItem[] side_JMenuItem = new JMenuItem[side_bar_items.length];
	
	public Menu(){
		this.setLayout(Style.grid42);
		this.setBackground(Style.blue);
		this.setVisible(true);
	
		botoes(estudante_cadastrado, "Estudante Cadastrado", Style.blue, Color.white, Style.ft);
		botoes(funcionario_cadastrado, "Funcionario Cadastrado", Style.blue, Color.white, Style.ft);
		botoes(cadastrar, "Cadastrar", Style.blue, Color.white, Style.ft);
		botoes(turmas, "Turmas", Style.blue, Color.white, Style.ft);
		
		this.validate();
	}
	
	
	//setting the buttons
	private void botoes(JButton btn, String btn_txt, Color bg, Color fg, Font ft){
		btn = new JButton(btn_txt);
		btn.setFont(ft);
		btn.setBackground(bg);	//mid blue = 0x3960a1 | TR orange = 0xf2701a
		btn.setForeground(fg);
		btn.setFocusable(false);
		//btn.setPreferredSize(new Dimension(375, 50));
		
		this.add(btn);
	}
	
	
	//setting the menu bar
	public void set_jmenu_bar(javax.swing.JFrame jf){
		create_jmenu(side_bar, "Menu", Style.menu_font, side_JMenuItem, side_bar_items);
		create_jmenu(estudante, "Estudante", Style.menu_font, est_JMenuItem, est_items);
		create_jmenu(funcionario, "Funcionario", Style.menu_font, func_JMenuItem, func_items);
		create_jmenu(ajuda, "Ajuda", Style.menu_font, ajuda_JMenuItem, ajuda_items);
		
		jf.setJMenuBar(jmenu_bar);
	}

	
	//setting the menus
	private void create_jmenu(JMenu jm, String jm_txt, Font ft, JMenuItem[] jm_item, String[] items){
		jm = new JMenu(jm_txt);
		jm.setFont(ft);
		jmenu_bar.add(jm);
		
		for(int i = 0; i < jm_item.length; i++){
			jm_item[i] = new JMenuItem(items[i]);
			
			jm_item[i].setFont(ft);
			jm.add(jm_item[i]);
			
			//Adding ActionListeners to all JMenuItems
			jm_item[i].addActionListener(this);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(side_JMenuItem[0])){
			//Login.janela.remove(menu);
			//Login.janela.validate();
			//new Janela();
			//Login.removeMenu();
			
			System.out.println("Ola Mundo");
			//Utilitario.login_ref.janela.remove(Utilitario.menuPanel_ref);
			//Utilitario.login_ref.janela.validate();
			
		}
	}
	
}

