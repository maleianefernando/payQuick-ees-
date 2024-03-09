package gui.estudante;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import gui.util.Style;

public class CadastrarEstudante extends JPanel{
	JScrollPane scroll = new JScrollPane(this);
	GridLayout grid61 = new GridLayout(15,1);
	
	JPanel[] panels;
	JLabel[] labels;
	JTextField[] text_fields;
	
	JLabel title = new JLabel("MATRICULAR ESTUDANTES");
	JPanel title_panel = new JPanel(Style.flow_center);
	
	String[] form_text = new String[] {"Nome Completo", "Idade", "Bairro", "Documento de identificacao", "Frequenta alguma instituicao de ensino?"};
	String[] radio_text = new String[] {"Sim", "Não"};
	
	JRadioButton[] radio_button;
	
	public CadastrarEstudante() {
		panels = new JPanel[form_text.length];
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
}
