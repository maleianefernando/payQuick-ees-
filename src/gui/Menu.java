package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import gui.util.Style;


public class Menu extends JPanel {
	private JButton estudante_cadastrado, funcionario_cadastrado, cadastrar, turmas; 
	private JMenuBar jmenu = new JMenuBar();
	private JMenu estudante;
	private JMenuItem[] est_item;
	Menu(){
		this.setLayout(Style.flow_center);
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
	public void menu_bar(javax.swing.JFrame jf){
		jmenu(estudante, "Estudante", est_item, Style.menu_font);
		
		jf.setJMenuBar(jmenu);
	}
	
	
	//setting the menus
//	est_item = new JMenuItem[3];
	
	private void jmenu(JMenu jm, String jm_txt, JMenuItem[] jm_item, Font ft){
//		jm = new JMenu(jm_txt);
//		jm.setFont(ft);
//		
//		for(int i =0; i < jm_item.length(); i++){
//			jm_item[i] = new JMenuItem("adc");
//			
//			jm_item[i].setFont(ft);
//			jm.setMenuItem(jm_item[i]);
//		}
	}
}

