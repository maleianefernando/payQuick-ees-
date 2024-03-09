package gui.estudante;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import gui.util.Style;

public class CadastrarEstudante extends JPanel implements ActionListener{
	JScrollPane scroll = new JScrollPane(this);
	GridLayout grid61 = new GridLayout(15,1);
	
	//form elements
	JPanel[] panels;
	JLabel[] labels;
	JTextField[] text_fields;
	
	JLabel title = new JLabel("MATRICULAR ESTUDANTES");
	JPanel title_panel = new JPanel(Style.flow_center);
	
	//Items to the forms JLabel
	String[] form_text = new String[] {"Nome Completo", "Idade", "Bairro", "Documento de identificacao", "Frequenta alguma instituicao de ensino?"};
	String[] radio_text = new String[] {"Sim", "Não"};
	
	//Radio button
	JRadioButton[] radio_button;
	
	//setting buttons
	JButton submit = new JButton("Salvar");
	JButton clear = new JButton("Limpar Formulario");
	
	
	public CadastrarEstudante() {
		panels = new JPanel[form_text.length+2];
		labels = new JLabel[form_text.length];
		text_fields = new JTextField[form_text.length];
		radio_button = new JRadioButton[radio_text.length];
		
		this.setLayout(grid61);
		this.setBackground(Style.bg);
		
		title.setFont(new Font("Consolas", Font.BOLD, 30));
		title.setForeground(Style.fg);
		title_panel.add(title);
		title_panel.setBackground(Style.bg);
		
		this.add(new JLabel(""));
		this.add(title_panel);
		this.add(new JLabel(""));
		this.addPanel(panels, labels, text_fields);
		
		
		panels[5] = new JPanel();
		panels[5].add(new JLabel(""));
		panels[5].setBackground(Style.bg);
		this.add(panels[5]);
		
		this.addButtons(submit);
		this.addButtons(clear);
	}
	
	private void addPanel(JPanel[] panel, JLabel[] label, JTextField[] text_field){
		
		for(int i = 0; i < form_text.length; i++){

			
			label[i] = new JLabel(form_text[i]);
			label[i].setFont(Style.tf_font);
				
			if(i != 4){
				text_field[i] = new JTextField(25);
				text_field[i].setFont(Style.tf_font);
				
				
			}
			else if( i == 4){
				for(int k = 0; k < radio_text.length; k++){
					
					
					radio_button[k] = new JRadioButton(radio_text[k]);
					radio_button[k].setBackground(Style.bg);
					radio_button[k].setFocusable(false);
					radio_button[k].addActionListener(this);
				}
			}
			
			panel[i] = new JPanel(Style.flow_center);			
			panel[i].setBackground(Style.bg);
			panel[i].add(label[i]);
			
			try{
				panel[i].add(text_field[i]);
				
			} catch(NullPointerException e){
			
				panel[i].add(radio_button[0]);
				panel[i].add(radio_button[1]);
				
				ButtonGroup group = new ButtonGroup();
				group.add(radio_button[0]);
				group.add(radio_button[1]);
			}
			
			panel[i].setPreferredSize(new Dimension(200, 50));
			
			this.add(panel[i]);
		}
	}
	
	private void addButtons(JButton button){

		button.setBackground(Style.blue);
		button.setForeground(new Color(0xffffff));
		button.setPreferredSize(new Dimension(200, 25));
		button.setFocusable(false);
		
		panels[6] = new JPanel(Style.flow_center);
		panels[6].setPreferredSize(new Dimension(200, 50));
		panels[6].setBackground(Style.bg);
		
		//
		button.addActionListener(this);
		panels[6].add(button);
		this.add(panels[6]);
		
	}
	
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(submit)){
			System.out.println("Salvar..");
		}
		
		else if(e.getSource().equals(clear)){
			System.out.println("Limpar..");
		}
		
		else if(e.getSource().equals(radio_button[0])){
		
		}
	}
}
