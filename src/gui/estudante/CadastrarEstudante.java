package gui.estudante;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.Year;
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
import javax.swing.JComboBox;

import gui.Login;
import gui.util.Style;

public class CadastrarEstudante extends JPanel implements ActionListener, MouseListener{
	public JScrollPane scroll = new JScrollPane(this);
	GridLayout grid61 = new GridLayout(20,1);
	
	//form elements
	JPanel[] panels;
	JLabel[] labels;
	JTextField[] text_fields;
	JComboBox<String> year_jcomboBox, month_jcomboBox, day_jcomboBox;
	JComboBox<String> nivel_combo;
	JComboBox<LocalTime> horario_combo;

	String[] year, month, day;
	
	JLabel title = new JLabel("MATRICULAR ESTUDANTES");
	JPanel title_panel = new JPanel(Style.flow_center);
	
	//Items to the forms JLabel
	String[] form_text = new String[] {"Nome Completo", "Morada", "Bairro", "Email", "Data de nascimento (DD/MM/AAAA)", "Numero de celular    (+258)", "Sexo", "Contacto de emergência", "Nome Completo", "Numero de Celular    (+258)", "nível "};
	String[] radio_text = new String[] {"M", "F"};
	String[] nivel_text = {"A1 (Básico)", "B1 (Intermediário)", "C1 (Avançado)","D1 (Fluente)", "Curso Intensivo", "Inglês para negócios", "Aulas ao domicílio", "Aulas online"};
	LocalTime[] horario = new LocalTime[this.get_time().length];
	Double[] price = new Double[nivel_text.length];
	
	//Radio button
	JRadioButton[] radio_button;
	JRadioButton[] nivel_radio = new JRadioButton[nivel_text.length];
	ButtonGroup nivel_g;
	
	//setting buttons
	JButton submit = new JButton("Salvar");
	JButton clear = new JButton("Limpar Formulario");
	
	//empty label to mae busy the panel[6]
	JLabel empty_label = new JLabel("");
	
	public CadastrarEstudante() {
		//this.start();
	}
	
	public void start(){
		scroll.getVerticalScrollBar().setUnitIncrement(10);

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
			//System.out.println("i = " + i + " = " + form_text[i]);
			
			label[i] = new JLabel(organize_string(form_text[i]).toUpperCase());
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
				if(i == 6){
					panel[i].add(radio_button[0]);
					panel[i].add(radio_button[1]);

					ButtonGroup group = new ButtonGroup();
					group.add(radio_button[0]);
					group.add(radio_button[1]);

					JPanel jp = new JPanel();
					jp.setBackground(Style.bg);

					label_title2.setFont(new Font("Consolas", Font.BOLD, 22));
					label_title2.setForeground(Style.fg);

					panel_title2.setBackground(Style.bg);
					panel_title2.add(label_title2);
					
					this.add(jp);
					this.add(panel_title2);
				
				}
			}

			if(i == 10) {
				//System.out.println(i);
				nivel_combo = new JComboBox<>(nivel_text);
				nivel_combo.setPreferredSize(new Dimension(200, 30));

				nivel_combo.setBackground(Style.tf_bg);
				nivel_combo.setFont(Style.tf_font);
				nivel_combo.addMouseListener(this);
				// for(int j = 0; j < nivel_text.length; j++){
					//System.out.println(i);
					// nivel_radio[j] = new JRadioButton(nivel_text[j]);
					// nivel_radio[j].setBackground(Style.bg);
					// nivel_radio[j].setFocusable(false);
					// nivel_radio[j].setFont(Style.tf_font);
					// nivel_radio[j].setForeground(Style.fg);

					// nivel_radio[j].addActionListener(this);

					
					
				// }
				
				panel[i].remove(text_field[i]);
				// panel[i].remove(label[i]);
				panel[i].add(nivel_combo);

				JPanel pn = new JPanel();
				pn.setBackground(Style.bg);
				//this.add(pn);
				
				// nivel_g = new ButtonGroup();
				
				// for(int l = 0; l < nivel_text.length; l++){
				// 	nivel_g.add(nivel_radio[l]);
				// 	panel[i].add(nivel_radio[l]);
				// }
				
				for(int v = 0; v < this.horario.length; v++){
					this.horario[v] = get_time()[v];
					System.out.println(get_time()[v]);
				}

				horario_combo = new JComboBox<>(this.horario);
				horario_combo.setPreferredSize(new Dimension(200, 30));

				horario_combo.setBackground(Style.tf_bg);
				horario_combo.setFont(Style.tf_font);
				horario_combo.addMouseListener(this);
				panel[i].add(horario_combo);
			}

			Dimension jcombo_dimension = new Dimension(70, 30);
			if(i == 4){
				panel[i].remove(text_field[i]);

				year_jcomboBox = new JComboBox<>();
				year_jcomboBox.setPreferredSize(jcombo_dimension);
				year_jcomboBox.setBackground(Style.tf_bg);
				year_jcomboBox.setFont(Style.tf_font);
				year_jcomboBox.addMouseListener(this);

				month_jcomboBox = new JComboBox<>();
				month_jcomboBox.setPreferredSize(jcombo_dimension);
				month_jcomboBox.setBackground(Style.tf_bg);
				month_jcomboBox.setFont(Style.tf_font);
				month_jcomboBox.addMouseListener(this);

				day_jcomboBox = new JComboBox<>();
				day_jcomboBox.setPreferredSize(jcombo_dimension);
				day_jcomboBox.setBackground(Style.tf_bg);
				day_jcomboBox.setFont(Style.tf_font);
				day_jcomboBox.addMouseListener(this);

				Year year = Year.now();
				MonthDay month = MonthDay.of(1,1);

				int day = 1;
				do {
					if(day<10){
						day_jcomboBox.addItem("0" + day);	
					}else{
						day_jcomboBox.addItem("" + day);
					}
					day++;

				} while (day <= 31);
				panel[i].add(day_jcomboBox);

				do {
					if(month.getMonthValue() < 10){
						month_jcomboBox.addItem("0" + month.getMonthValue());
					}else{
						month_jcomboBox.addItem("" + month.getMonthValue());
					}
					
					try{
						month = MonthDay.of(month.getMonthValue()+1, 1);
					} catch (java.time.DateTimeException e){
						break;
					}
				} while (month.getMonthValue() <= 12);
				panel[i].add(month_jcomboBox);
			
				do{
					year_jcomboBox.addItem(year.getValue() + "");
					year = Year.of(year.getValue()-1);

				}while(year.getValue() >= Year.of(1960).getValue());
				panel[i].add(year_jcomboBox);

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

	private String organize_string(String string){
		String max = form_text[7];
		int min_str = string.length(), max_str = max.length(), mid = max_str-min_str;

		for(int i = 0; i <= mid; i++){

			string = "  " + string;

		}

		return string;
	}
	
	public static LocalTime[] get_time(){
		LocalTime time = LocalTime.of(04, 30, 00);
		//LocalTime t_end = time.plusMinutes(90);

		LocalTime[] time_r = new LocalTime[9];

		for(int i = 0; i < 9; i++){
			if(time.toString().equals("10:30")){
				time = time.plusHours(2);
				time = time.plusMinutes(30);
				time_r[i] = time;

			}
			else{
				//time_r[i] = time;
				time = time.plusHours(1);
				time = time.plusMinutes(30);
				time_r[i] = time;
			}	
		}

		return time_r;
	}

	private Double get_price(){
		Double price = 0.0;

		this.price[0] = 1000.0;
		this.price[1] = 1025.0;
		this.price[2] = 1225.0;
		this.price[3] = 1425.0;
		this.price[4] = 3489.0;
		this.price[5] = 4499.0;
		this.price[6] = 5419.0;
		this.price[7] = 1409.0;

		for(int k = 0; k < nivel_combo.getItemCount(); k++){
			if(nivel_combo.getSelectedItem().toString().equals(nivel_combo.getItemAt(k).toString())){
				price = this.price[k];
			}
		}

		return price;
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
			String data_nascimeto = year_jcomboBox.getSelectedItem()+"-"+month_jcomboBox.getSelectedItem()+"-"+day_jcomboBox.getSelectedItem();
			String numero_celular = text_fields[5].getText();
			String sexo = "";
			String contacto_emergencia = text_fields[7].getText();
			String nome_emergencia = text_fields[8].getText();
			String nr_celular_emergencia = text_fields[9].getText();
			String nivel = nivel_combo.getSelectedItem().toString();
			String __nivel_avaliacao = "";

			// for(int i = 0; i< nivel_text.length; i++){
				// String[] nivel_text = {"A1 (Básico)", "B1 (Intermediário)", "C1 (Avançado)","D1 (Fluente)", "Curso Intensivo", "Inglês para negócios", "Aulas ao domicíclio", "Aulas online"};
				
				
				// if(nivel_radio[i].isSelected()){
				// 	nivel = nivel_radio[i].getText();

				// 	if(i == 0){
				// 		preco = 625.00;
				// 	}
				// 	else if(i == 1){
				// 		preco = 725.00;
				// 	}
				// 	else if(i == 2){
				// 		preco = 925.00;
				// 	}
				// 	else if(i == 3){
				// 		preco = 1025.00;
				// 	}
				// }
			// }

			Time horario = Time.valueOf((LocalTime)horario_combo.getSelectedItem());

			java.util.Date date = new java.util.Date();
			java.sql.Date data_matricula = new java.sql.Date(date.getTime());

			if(radio_button[0].isSelected()){
				sexo = radio_button[0].getText();
			}
			else if (radio_button[1].isSelected()){
				sexo = radio_button[1].getText();
			}

			String query = "INSERT INTO estudante (id_estudante, nome_completo, morada, bairro, email, data_nascimento, numero_celular, sexo, nivel, horario, data_matricula) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			String emergencia_query = "INSERT INTO emergencia (id_estudante, contacto_emergencia, nome_completo, numero_celular) VALUES (?, ?, ?, ?)";

			String mensalidade_query = "INSERT INTO mensalidade (id_estudante, mes, status, divida) VALUES (?, MONTH(NOW()), ?, ?)";

			String avaliacao_query = "";

			PreparedStatement ps = null;
			PreparedStatement ps_em = null;
			PreparedStatement ps_mens = null;
			PreparedStatement ps_nivel = null;

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


				ps_em = Conexao.getConexao_ees().prepareStatement(emergencia_query);
				
				ps_em.setString(1, id);
				ps_em.setString(2, contacto_emergencia);
				ps_em.setString(3, nome_emergencia);
				ps_em.setString(4, "+258"+nr_celular_emergencia);


				ps_mens = Conexao.getConexao_ees().prepareStatement(mensalidade_query);
				ps_mens.setString(1, id);
				ps_mens.setString(2, "nao pago");
				ps_mens.setDouble(3, this.get_price());



				if(nivel.equals(nivel_text[0])){
					avaliacao_query = "INSERT INTO avaliacao_nivel1 (id_estudante) VALUE (?)";
				}
				else if(nivel.equals(nivel_text[1])){
					avaliacao_query = "INSERT INTO avaliacao_nivel2 (id_estudante) VALUE (?)";
				}
				else if(nivel.equals(nivel_text[2])){
					avaliacao_query = "INSERT INTO avaliacao_nivel3 (id_estudante) VALUE (?)";
				}
				else if(nivel.equals(nivel_text[3])){
					avaliacao_query = "INSERT INTO avaliacao_nivel4 (id_estudante) VALUE (?)";
				}
				else if(nivel.equals(nivel_text[4])){
					avaliacao_query = "INSERT INTO avaliacao_ingles_intensivo (id_estudante) VALUE (?)";
				}
				else if(nivel.equals(nivel_text[5])){
					avaliacao_query = "INSERT INTO avaliacao_ingles_negocios (id_estudante) VALUE (?)";
				}
				else if(nivel.equals(nivel_text[6])){
					avaliacao_query = "INSERT INTO avaliacao_ingles_domiciliar (id_estudante) VALUE (?)";
				}
				else if(nivel.equals(nivel_text[7])){
					avaliacao_query = "INSERT INTO avaliacao_ingles_online (id_estudante) VALUE (?)";
				}

				ps_nivel = Conexao.getConexao_ees().prepareStatement(avaliacao_query);
				ps_nivel.setString(1, id);

				try {
					if(ps.executeUpdate() == 1 && ps_em.executeUpdate() == 1 && ps_mens.executeUpdate() == 1 && ps_nivel.executeUpdate() == 1){
						JOptionPane.showMessageDialog(Login.janela, "Estudante " + nome + " cadastrado!", "Sucesso!!!", JOptionPane.INFORMATION_MESSAGE);
					}

				} catch (com.mysql.cj.jdbc.exceptions.MysqlDataTruncation ex){
					JOptionPane.showMessageDialog(null, "Por favor verifique a data de nascimento ou o numero de digitos do seu numero de telefone", "Erro", JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex, "Erro", JOptionPane.INFORMATION_MESSAGE);
				}// catch (com.mysql.cj.jdbc.exceptions.MysqlDataTruncation ex2){
				// 	JOptionPane.showMessageDialog(null, "Data de nascimento invalida", "Erro", JOptionPane.INFORMATION_MESSAGE);
				// }

				ps.close();
				ps_em.close();
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

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}
}
