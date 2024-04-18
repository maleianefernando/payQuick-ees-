package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Time;
import java.time.LocalTime;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import gui.estudante.CadastrarEstudante;
import gui.estudante.ListaNominalEstudante;
import gui.estudante.Mensalidades;
import gui.estudante.Pauta;
import gui.funcionario.CadastrarFuncionario;
import gui.funcionario.ListaNominalFuncionario;
import gui.util.Style;
import xutil.Utilitario;

public class Menu extends JPanel implements ActionListener, MouseListener, Utilitario{
	public JPanel panel_menu = new JPanel();

	Icon pesq_icon = new ImageIcon("./img/pesquisa.png");
	Icon pesq_icon2 = new ImageIcon("./img/motor-de-pesquisa.png");

	JTextField pesq_estudante;
	JComboBox<String> pesq_nivel;
	JComboBox<LocalTime> pesq_turma;

	JButton pesquisar;

	String[] nivel_text = new String[] {"", "A1 (Básico)", "B1 (Intermediário)", "C1 (Avançado)", "D1 (Fluente)", "Inglês intensivo", "Inglês para negócios", "Aulas Domiciliares", "Aulas OnLine"};

	//side bar JMenuIems titles
	private String[] side_bar_items = new String[] {"Menu"};	//
	private String[] est_items = new String[] {"Lista Nominal", "Desempenho", "Mensalidades", "Cadastrar"};	//items of the menu 'estudante'
	private String[] func_items = new String[] {"Lista Nominal", "Cadastrar"};	//items of the menu 'funcionario'
	private String[] conta_items = new String[]{"Minha conta", "Logout", "Sair"};
	private String[] ajuda_items = new String[] {"Ajuda", "Quem Somos?", "Logout", "Sair"};	//items of the menu ajuda
	
	private JButton estudante_cadastrado, funcionario_cadastrado, cadastrar, turmas; 
	public JMenuBar jmenu_bar = new JMenuBar();
	private JMenu side_bar;
	private JMenu estudante;
	public JMenu funcionario;
	public JMenu conta;
	private JMenu ajuda;
	private JMenuItem[] est_JMenuItem = new JMenuItem[est_items.length];
	private JMenuItem[] func_JMenuItem = new JMenuItem[func_items.length];
	private JMenuItem[] conta_JMenuItem = new JMenuItem[conta_items.length];
	private JMenuItem[] ajuda_JMenuItem = new JMenuItem[ajuda_items.length];
	private JMenuItem[] side_JMenuItem = new JMenuItem[side_bar_items.length];
	
	public Menu(){
		this.setLayout(Style.grid42);
		this.setBackground(Style.blue);
		this.setVisible(true);
		
		jmenu_bar.setPreferredSize(new Dimension(0, 50));
		jmenu_bar.add(Box.createHorizontalGlue());
		jmenu_bar.setBackground(Style.jmenu_bar_bg);

		botoes(estudante_cadastrado, "Estudante Cadastrado", Style.blue, Color.white, Style.ft, this);
		botoes(funcionario_cadastrado, "Funcionario Cadastrado", Style.blue, Color.white, Style.ft, this);
		botoes(cadastrar, "Cadastrar", Style.blue, Color.white, Style.ft, this);
		botoes(turmas, "Turmas", Style.blue, Color.white, Style.ft, this);
		
		this.revalidate();
		this.repaint();
	}

	String __nivel_avaliacao = "";
	String __turma = "";
	public JPanel SideMenu(Color bg, Color fg){

		JLabel pesq_estudante_label;
		JLabel nivel_est_label;
		JLabel turma_est_label;

		GridLayout __grid = new GridLayout(12, 1, 15, 5);
		JPanel side_menu = new JPanel(__grid);

		JPanel id_est_panel = new JPanel(Style.flow_center);
		JPanel nivel_panel = new JPanel(Style.flow_center);
		JPanel turma_panel = new JPanel(Style.flow_center);
		JPanel pesquisar_panel = new JPanel(Style.flow_center);

		// side_menu.setLayout(new GridLayout(16, 2));
		side_menu.setBackground(bg);
		side_menu.setPreferredSize(new Dimension(350, 0));

		side_menu.setVisible(true);

		//id estudante label
		pesq_estudante_label = new JLabel("ID do estudante");
		pesq_estudante_label.setForeground(fg);
		pesq_estudante_label.setFont(Style.tf_font);

		//id text field
		pesq_estudante = new JTextField(13);
		pesq_estudante.setPreferredSize(new Dimension(15, 30));
		pesq_estudante.setFont(Style.tf_font);

		//id estudante panel
		id_est_panel.setBackground(bg);
		id_est_panel.add(pesq_estudante_label);
		id_est_panel.add(pesq_estudante);

		//nivel combo box
		pesq_nivel = new JComboBox<>(nivel_text);
		pesq_nivel.setSelectedIndex(0);
		pesq_nivel.addActionListener(this);

		pesq_turma = new JComboBox<>();
		pesq_turma.setPreferredSize(pesq_nivel.getPreferredSize());
		pesq_turma.addActionListener(this);

		for(int i = 0; i < 9; i++){
			pesq_turma.addItem(CadastrarEstudante.get_time()[i]);
		}
		pesq_turma.addItem(LocalTime.of(00, 00));
		pesq_turma.setSelectedIndex(9);

		//turma label
		turma_est_label = new JLabel("Selecione a turma");
		turma_est_label.setFont(Style.tf_font);
		turma_est_label.setForeground(fg);

		//turma panel
		turma_panel.setBackground(bg);
		turma_panel.add(turma_est_label);
		turma_panel.add(pesq_turma);

		//nivel label
		nivel_est_label = new JLabel("Selecione o nivel");
		nivel_est_label.setForeground(fg);
		nivel_est_label.setFont(Style.tf_font);

		//nivel panel
		nivel_panel.setBackground(bg);
		nivel_panel.add(nivel_est_label);
		nivel_panel.add(pesq_nivel);
		
		side_menu.add(new JLabel(""));
		// side_menu.add(new JLabel(""));
		
		//pesquisar button
		pesquisar = new JButton("Pesquisar");
		pesquisar.setPreferredSize(new Dimension(side_menu.getWidth(), 70));
		pesquisar.setBackground(Style.bg);
		pesquisar.setFocusable(false);
		pesquisar.setIcon(pesq_icon);
		pesquisar.addActionListener(this);
		pesquisar.addMouseListener(this);
		pesquisar.setBackground(Style.table_bg);
		pesquisar.setMnemonic('P');

		// pesquisar_panel.setBackground(Color.red);
		// pesquisar_panel.setPreferredSize(new Dimension(side_menu.getX(), 60));
		// pesquisar_panel.add(pesquisar);
		
		//adding panels
		side_menu.add(id_est_panel);
		side_menu.add(nivel_panel);
		side_menu.add(turma_panel);
		side_menu.add(pesquisar);

		return side_menu;
	}
	
	
	//setting the buttons
	private void botoes(JButton btn, String btn_txt, Color bg, Color fg, Font ft, JComponent conteiner){
		btn = new JButton(btn_txt);
		btn.setFont(ft);
		btn.setBackground(bg);	//mid blue = 0x3960a1 | TR orange = 0xf2701a
		btn.setForeground(fg);
		btn.setFocusable(false);
		//btn.setPreferredSize(new Dimension(375, 50));
		// btn.addActionListener(this);
		//System.out.println(btn.getActionListeners() + "\n" + btn);

		conteiner.add(btn);

	}
	
	
	//setting the admin menu bar
	public void set_adm_jmenu_bar(JFrame jf){
		create_jmenu(side_bar, "Menu", Style.menu_font, side_JMenuItem, side_bar_items);
		create_jmenu(estudante, "Estudante", Style.menu_font, est_JMenuItem, est_items);
		create_jmenu(funcionario, "Funcionario", Style.menu_font, func_JMenuItem, func_items);
		create_jmenu(conta, "Conta", Style.menu_font, conta_JMenuItem, conta_items);
		create_jmenu(ajuda, "Ajuda", Style.menu_font, ajuda_JMenuItem, ajuda_items);
		
		jf.setJMenuBar(jmenu_bar);
	}

	//setting the menu bar
	public void set_jmenu_bar(JFrame jf){
		create_jmenu(side_bar, "Menu", Style.menu_font, side_JMenuItem, side_bar_items);
		create_jmenu(estudante, "Estudante", Style.menu_font, est_JMenuItem, est_items);
		create_jmenu(conta, "Conta", Style.menu_font, conta_JMenuItem, conta_items);
		create_jmenu(ajuda, "Ajuda", Style.menu_font, ajuda_JMenuItem, ajuda_items);
		
		jf.setJMenuBar(jmenu_bar);
	}

	//setting the menus
	private void create_jmenu(JMenu jm, String jm_txt, Font ft, JMenuItem[] jm_item, String[] items){
		jm = new JMenu(jm_txt);
		jm.setFont(ft);
		jm.setMnemonic(jm_txt.charAt(0));
		jm.setPreferredSize(new Dimension(100, 0));
		jm.setLayout(new FlowLayout(FlowLayout.CENTER));
		//jm.addMouseListener(this);
		jmenu_bar.add(jm);
		
		for(int i = 0; i < jm_item.length; i++){
			int k = 0;

			jm_item[i] = new JMenuItem(items[i]);

			jm_item[i].setFont(ft);
			jm.add(jm_item[i]);

			if(items[i].charAt(k) == 'M' || items[i].charAt(k) == 'E' || items[i].charAt(k) == 'F' || items[i].charAt(k) == 'A'){
				++k;
			}
			
			jm_item[i].setMnemonic(items[i].charAt(k));
			
			//Adding ActionListeners to all JMenuItems
			jm_item[i].addActionListener(this);
			
		}
	}

	private void logout(){
		Login.janela.dispose();
		login = new Login();
		login.start();
	}

	private void exit(){
		Integer exit_status = JOptionPane.showConfirmDialog(Login.janela, "Sair?", "Confirmar saida", JOptionPane.YES_NO_OPTION);

		if(exit_status.equals(0)){
			System.exit(0);
		}
	}
	
	int side_status = 0;
	int list_status = 0;
	int add_status = 0;
	int reg_func_status = 0;
	int list_func_status = 0;
	int mens_status = 0;

	
	Login login;
	CadastrarEstudante reg_est;
	ListaNominalEstudante lista_est;
	Mensalidades mensalidade;
	Pauta pauta;
	JComponent side = Utilitario.menu_ref;

	CadastrarFuncionario reg_func;
	ListaNominalFuncionario list_func;

	@Override
	public void actionPerformed(ActionEvent e){

		//manange left side menu
		if(e.getSource().equals(side_JMenuItem[0])){
			//side = new Menu();
			// side = Utilitario.menu_ref;
			
			if(side_status == -1 || side_status == 0){
				side_status = Login.janela.addMenuSide(side, BorderLayout.WEST, "Menu");
				//System.out.println("menu removido,  status: " + side_status);
				
			}
			else{
				side_status = Login.janela.removeConteiner(Utilitario.menu_ref);
				//System.out.println("menu adicionado,  status: " + side_status);
				
			}
		}
		
		//list added students
		else if(e.getSource().equals(est_JMenuItem[0])){
			side = Utilitario.menu_ref;

				lista_est = new ListaNominalEstudante();
				list_status = Login.janela.addConteiner(lista_est, BorderLayout.CENTER, "Lista Nominal De Estudantes");

				lista_est.start_list();
				side_status = -1;
			
		}
		
		//pauta
		else if(e.getSource().equals(est_JMenuItem[1])){
			
			side = this.SideMenu(Style.blue, Style.tf_bg);
			
			pauta = new Pauta("SELECT * FROM avaliacao_nivel1", "avaliacao_nivel1");
				list_status = Login.janela.addConteiner(pauta, BorderLayout.CENTER, "Pauta");

				pauta.start_table();
				side_status = -1;
		}

		//monthly
		else if(e.getSource().equals(est_JMenuItem[2])){
			
			side = Utilitario.menu_ref;

				mensalidade = new Mensalidades();
				Login.janela.addConteiner(mensalidade, BorderLayout.CENTER, "Controlo de mensalidades");

				mensalidade.start_table();
				
				side_status = -1;
			
		}

		//add students
		else if(e.getSource().equals(est_JMenuItem[3])){	//Register student
			
			side = Utilitario.menu_ref;

				reg_est = new CadastrarEstudante();
				add_status = Login.janela.addConteiner(reg_est.scroll, BorderLayout.CENTER, "Cadastrar Estudantes");
				reg_est.start();

				side_status = -1;
			
		}
		else if(e.getSource().equals(func_JMenuItem[0])){	//List registered officers

			side = Utilitario.menu_ref;

				list_func = new ListaNominalFuncionario();
				list_func_status = Login.janela.addConteiner(list_func, BorderLayout.CENTER, "Listar funcionarios");

				list_func.start_list();

				side_status = -1;

		}
		else if(e.getSource().equals(func_JMenuItem[1])){	//register a offical

			side = Utilitario.menu_ref;

				reg_func = new CadastrarFuncionario();
				reg_func_status = Login.janela.addConteiner(reg_func, BorderLayout.CENTER, "Registar funcionarios");

				side_status = -1;

		}
		//account details
		else if(e.getSource().equals(conta_JMenuItem[0])){

		}

		//logout
		else if(e.getSource().equals(conta_JMenuItem[1])){
			this.logout();
		}

		//exit
		else if(e.getSource().equals(conta_JMenuItem[2])){
			this.exit();
		}

		//help
		else if(e.getSource().equals(ajuda_JMenuItem[0])){
			JOptionPane.showMessageDialog(Login.janela, "Esta funcionalidade ainda está em desenvolvimento", "Ajuda", JOptionPane.INFORMATION_MESSAGE);
		}

		//about us
		else if(e.getSource().equals(ajuda_JMenuItem[1])){	
			JOptionPane.showMessageDialog(Login.janela, "Somos a Emmanuel English School, uma escola especializada em ensinar a lingua inglesa atravês de métodos funcionais e eficazes", "Quem Somos Nós?", JOptionPane.INFORMATION_MESSAGE);
		}

		else if(e.getSource().equals(ajuda_JMenuItem[2])){	//Logout button - JMenuItem

			this.logout();
		}
		
		else if(e.getSource().equals(ajuda_JMenuItem[3])){	//Exit button - JMenuItem
			this.exit();
		}
		


		////////
		else if(e.getSource().equals(pesquisar)){
			String id_estudante = pesq_estudante.getText();
			String nivel = pesq_nivel.getSelectedItem().toString();
			Time turma = Time.valueOf((LocalTime)pesq_turma.getSelectedItem());

			String query = "SELECT * FROM avaliacao_nivel1";
			String tabela = __nivel_avaliacao;

			Boolean hasId = false;

			if(!(id_estudante.trim().equals("")) && !(nivel.equals("")) && !(turma.equals(Time.valueOf(LocalTime.of(0,0,0))))){
				query = "SELECT * FROM "+ __nivel_avaliacao + " WHERE id_estudante = ?";
				System.out.println(1);

				// pauta = new Pauta(query, tabela, id_estudante);
				hasId = true;
			}

			else if(!(id_estudante.trim().equals("")) && !(nivel.equals(""))){
				query = "SELECT * FROM " + __nivel_avaliacao + " WHERE id_estudante = ?";
				System.out.println(2);
				System.out.println(query);

				// pauta = new Pauta(query, __nivel_avaliacao, id_estudante);
				hasId = true;
			}

			else if(id_estudante.trim().equals("") && !(nivel.equals(""))){
				query = "SELECT * FROM " + __nivel_avaliacao;
				System.out.println(3);

				// pauta = new Pauta(query, tabela);
				hasId = false;
			}

			else if(!(id_estudante.trim().equals("")) && nivel.equals("")){
				query = "SELECT * FROM " + __nivel_avaliacao + "id_estudante = ?";
				System.out.println(4);

				// pauta = new Pauta(query, tabela);
				hasId = false;
			}

			Login.janela.getContentPane().remove(pauta);
			
			if(hasId){
				pauta = new Pauta(query, tabela, id_estudante);
			}
			else {
				pauta = new Pauta(query, tabela);
			}

			pauta.start_table();
			Login.janela.getContentPane().add(pauta, BorderLayout.CENTER);
			Login.janela.getContentPane().revalidate();
			Login.janela.getContentPane().repaint();
		}
		else if(e.getSource().equals(pesq_nivel)){

			if(pesq_nivel.getSelectedItem().toString().equals(nivel_text[1])){
				__nivel_avaliacao = "avaliacao_nivel1";
			}

			else if(pesq_nivel.getSelectedItem().toString().equals(nivel_text[2])){
				__nivel_avaliacao = "avaliacao_nivel2";
			}

			else if(pesq_nivel.getSelectedItem().toString().equals(nivel_text[3])){
				__nivel_avaliacao = "avaliacao_nivel3";
			}

			else if(pesq_nivel.getSelectedItem().toString().equals(nivel_text[4])){
				__nivel_avaliacao = "avaliacao_nivel4";
			}

			else if(pesq_nivel.getSelectedItem().toString().equals(nivel_text[5])){
				__nivel_avaliacao = "avaliacao_ingles_intensivo";
			}

			else if(pesq_nivel.getSelectedItem().toString().equals(nivel_text[6])){
				__nivel_avaliacao = "avaliacao_ingles_negocios";
			}
			
			else if(pesq_nivel.getSelectedItem().toString().equals(nivel_text[7])){
				__nivel_avaliacao = "avaliacao_ingles_domiciliar";
			}

			else if(pesq_nivel.getSelectedItem().toString().equals(nivel_text[8])){
				__nivel_avaliacao = "avaliacao_ingles_online";
			}

			System.out.println(__nivel_avaliacao);
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {

	}


	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource().equals(pesquisar)){
			pesquisar.setIcon(pesq_icon2);
		}
	}


	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource().equals(pesquisar)){
			pesquisar.setIcon(pesq_icon);
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {

	}


	@Override
	public void mouseReleased(MouseEvent e) {

	}
}

