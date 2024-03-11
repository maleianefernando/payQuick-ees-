package gui.estudante;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

import connection.Conexao;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;

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
	
	//empty label to mae busy the panel[6]
	JLabel empty_label = new JLabel("");
	
	public CadastrarEstudante() {
		panels = new JPanel[15];	//form_text.length+2
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
		
		//The elements are added tho the panels[5] on actionPerformed method
		panels[5] = new JPanel(Style.flow_center);
		panels[5].setBackground(Style.bg);
		panels[5].setPreferredSize(new Dimension(200, 50));
		this.add(panels[5]);
		
		//Adding select subject options
		panels[6] = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		panels[6].setBackground(Style.bg);
		this.selectDisciplina(panels[6]);
		
		this.add(panels[6]);
		
		//Adding buttons
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
					radio_button[k].setFont(Style.tf_font);
					
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
		
		panels[10] = new JPanel(Style.flow_center);
		panels[10].setPreferredSize(new Dimension(200, 50));
		panels[10].setBackground(Style.bg);
		
		//
		button.addActionListener(this);
		panels[10].add(button);
		this.add(panels[10]);
		
	}
	
	private String[] __nivel = new String[] {"Ensino Superior", "Ensino Secundario", "Ensino Medio", "Outro"};
	private JRadioButton[] nivel = new JRadioButton[__nivel.length];
	private ButtonGroup button_group = new ButtonGroup();
	
	private void setNivelActual(JPanel panel){
		for(int i = 0; i < __nivel.length; i++){
		
			nivel[i] = new JRadioButton(__nivel[i]);
			nivel[i].setFocusable(false);
			nivel[i].setBackground(Style.bg);
			nivel[i].setFont(Style.tf_font);
			
			panel.add(nivel[i]);
			button_group.add(nivel[i]);
		}
	}
	
	private String[] __disciplina = new String[] {"Português", "Matemática", "Inglês", "Filosofia", "História", "Geografia", "Quimica", "Biologia", "Física"};
	private JCheckBox[] disciplina = new JCheckBox[__disciplina.length];
	
	private void selectDisciplina(JPanel panel){
		
		for(int i = 0; i < __disciplina.length; i++){
			disciplina[i] = new JCheckBox(__disciplina[i]);
			disciplina[i].setBackground(Style.bg);
			disciplina[i].setFont(Style.tf_font);
			
			panel.add(disciplina[i]);
		}
	}
	
	int status_nivel = 0;
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(submit)){
			System.out.println("Salvar..");
			String id = "est_";
			Random r = new Random();

			for(int i = 0; i < 4; i++){
				id = id + r.nextInt(9);
			}

			String nome = text_fields[0].getText();
			Integer idade = Integer.parseInt(text_fields[1].getText());
			String bairro = text_fields[2].getText();
			String identificacao = text_fields[3].getText();
			
			String sql = "INSERT INTO estudantes (ID, NOME, IDADE, BAIRRO, IDENTIFICACAO_NR, OCUPACAO, DISCIPLINAS) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = null;

			try{
				ps = Conexao.getConexao().prepareStatement(sql);

				ps.setString(1, id);
				ps.setString(2, nome);
				ps.setInt(3, idade);
				ps.setString(4, bairro);
				ps.setString(5, identificacao);
				ps.setString(6, "ocupacao");
				ps.setString(7, "disciplinas");

				ps.execute();
				ps.close();
			}
			catch(SQLException ex){
				ex.printStackTrace();
			}

		}
		
		else if(e.getSource().equals(clear)){
			System.out.println("Limpar..");
		}
		
		else if(e.getSource().equals(radio_button[0])){
			
			if(status_nivel != 1){
				this.setNivelActual(panels[5]);
				
				panels[5].revalidate();
				panels[5].repaint();
				
				status_nivel = 1;
			}
		}
		
		else if(e.getSource().equals(radio_button[1])){
			status_nivel = -1;
			
			for(int i = 0; i < __nivel.length; i++){
				panels[5].remove(nivel[i]);
			}
			
			panels[5].revalidate();
			panels[5].repaint();
		}
	}
}
