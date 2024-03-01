package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JButton;


import gui.util.Style;


public class Menu extends JPanel {
	private JButton estudante_cadastrado, funcionario_cadastrado, cadastrar, turmas; 
	
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
	
	private void botoes(JButton btn, String btn_txt, Color bg, Color fg, Font ft){
		btn = new JButton(btn_txt);
		btn.setFont(ft);
		btn.setBackground(bg);	//mid blue = 0x3960a1 | TR orange = 0xf2701a
		btn.setForeground(fg);
		btn.setFocusable(false);
		//btn.setPreferredSize(new Dimension(375, 50));
		
		this.add(btn);
	}
}

