package gui.estudante;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Random;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import connection.Conexao;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import gui.Login;
import gui.util.Style;

public class CadastrarEstudante extends JPanel implements ActionListener{
	public JScrollPane scroll = new JScrollPane(this);
	GridLayout grid61 = new GridLayout(20,1);
	
	//form elements
	JPanel[] panels;
	JLabel[] labels;
	JTextField[] text_fields;
	
	JLabel title = new JLabel("MATRICULAR ESTUDANTES");
	JPanel title_panel = new JPanel(Style.flow_center);
	
	//Items to the forms JLabel
	String[] form_text = new String[] {"Nome Completo", "Morada", "Bairro", "Email", "Data de nascimento", "Numero de celular", "Sexo", "Contacto de emergência", "Nome Completo", "Numero de Celular    (+258)", "nivel"};
	String[] radio_text = new String[] {"M", "F"};
	
	//Radio button
	JRadioButton[] radio_button;
	
	//setting buttons
	JButton submit = new JButton("Salvar");
	JButton clear = new JButton("Limpar Formulario");
	
	//empty label to mae busy the panel[6]
	JLabel empty_label = new JLabel("");
	
	public CadastrarEstudante() {
		this.start();
	}
	
	public void start(){
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
		
		this.add(panels[6]);
		
		//Adding buttons
		this.addButtons(submit);
		this.addButtons(clear);
	}

	private void addPanel(JPanel[] panel, JLabel[] label, JTextField[] text_field){
		
		JPanel panel_title2 = new JPanel(Style.flow_center);
		JLabel label_title2 = new JLabel("informacoes de emergencia".toUpperCase());

		for(int i = 0; i < form_text.length; i++){

			
			label[i] = new JLabel(form_text[i].toUpperCase());
			label[i].setFont(Style.tf_font);
				
			if(i != 6){
				text_field[i] = new JTextField(25);
				text_field[i].setFont(Style.tf_font);
				
				
			}
			else if( i == 6){



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
				JPanel jp = new JPanel();
				jp.setBackground(Style.bg);

				label_title2.setFont(new Font("Consolas", Font.BOLD, 22));
				label_title2.setForeground(Style.fg);

				panel_title2.setBackground(Style.bg);
				panel_title2.add(label_title2);
				
				this.add(jp);
				this.add(panel_title2);
				
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
			String morada = text_fields[1].getText();
			String bairro = text_fields[2].getText();
			String email = text_fields[3].getText();
			String data_nascimeto = text_fields[4].getText();
			String numero_celular = text_fields[5].getText();
			String sexo = "";
			String contacto_emergencia = text_fields[7].getText();
			String nome_emergencia = text_fields[8].getText();
			String nr_celular_emergencia = text_fields[9].getText();
			String nivel = text_fields[10].getText();

			Time horario = new Time(130000);

			java.util.Date date = new java.util.Date();
			java.sql.Date data_matricula = new java.sql.Date(date.getTime());

			if(radio_button[0].isSelected()){
				sexo = radio_button[0].getText();
			}
			else if (radio_button[1].isSelected()){
				sexo = radio_button[1].getText();
			}

			String query = "INSERT INTO estudante (id_estudante, nome_completo, morada, bairro, email, data_nascimento, numero_celular, sexo, nivel, horario, data_matricula) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = null;

			try{
				ps = Conexao.getConexao_ees().prepareStatement(query);
				
				ps.setString(1, id);
				ps.setString(2, nome);
				ps.setString(3, morada);
				ps.setString(4, bairro);
				ps.setString(5, email);
				ps.setString(6, data_nascimeto);
				ps.setString(7, "+258"+numero_celular);
				ps.setString(8, sexo);
				ps.setString(9, nivel);
				ps.setTime(10, horario);
				ps.setDate(11, data_matricula);


				try {
						if(ps.executeUpdate() == 1){
							JOptionPane.showMessageDialog(Login.janela, "Estudante " + nome + " cadastrado!", "Sucesso!!!", JOptionPane.INFORMATION_MESSAGE);
						}
					// System.out.println(ps);
					// System.out.println(query);

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex, "Erro", JOptionPane.INFORMATION_MESSAGE);
				}
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
			
		}
		
		else if(e.getSource().equals(radio_button[1])){

		}
	}
}
