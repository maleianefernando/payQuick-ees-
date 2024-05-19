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
import gui.estudante.ContaUser;
import gui.estudante.ListaNominalEstudante;
import gui.estudante.Mensalidades;
import gui.estudante.Pauta;
import gui.funcionario.CadastrarFuncionario;
import gui.funcionario.ListaNominalFuncionario;
import gui.util.Style;
import xutil.Utilitario;

public class Menu extends JPanel implements ActionListener, MouseListener, Utilitario{

	JPanel northPanel;
	JButton menuSideButton;
	JPanel manageMenuPanel;
	// Login login;

	String est_query = "SELECT * FROM estudante";
	public JPanel panel_menu = new JPanel();

	Icon pesq_icon = new ImageIcon("./img/pesquisa.png");
	Icon pesq_icon2 = new ImageIcon("./img/motor-de-pesquisa.png");

	JTextField pesq_estudante;
	JComboBox<String> pesq_nivel;
	JComboBox<LocalTime> pesq_turma;
	JComboBox<String> nivel_combo;
	JComboBox<String> ano_combo;

	JButton pesquisar, listar_estudante;

	String[] nivel_text = new String[] {"", "A1 (Básico)", "B1 (Intermediário)", "C1 (Avançado)", "D1 (Fluente)", "Inglês intensivo", "Inglês para negócios", "Aulas domiciliares", "Aulas onLine"};

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
		
		jmenu_bar.setPreferredSize(new Dimension(0, 30));
		jmenu_bar.add(Box.createHorizontalGlue());
		jmenu_bar.setBackground(Style.jmenu_bar_bg);

		// Login.janela.add(setNorthPanel());

		botoes(estudante_cadastrado, "Estudante Cadastrado", Style.blue, Color.white, Style.ft, this);
		botoes(funcionario_cadastrado, "Funcionario Cadastrado", Style.blue, Color.white, Style.ft, this);
		botoes(cadastrar, "Cadastrar", Style.blue, Color.white, Style.ft, this);
		botoes(turmas, "Turmas", Style.blue, Color.white, Style.ft, this);

		this.revalidate();
		this.repaint();
	}

	String __nivel_avaliacao = "";
	String __turma = "";

	public JPanel setNorthPanel(){

		menuSideButton = new JButton();
		menuSideButton.setOpaque(false);
		// menuSideButton.setPreferredSize(new Dimension(60,35));
		menuSideButton.setBackground(new Color(0x4ea295));
		menuSideButton.setFocusable(false);
		menuSideButton.setHorizontalTextPosition(JButton.CENTER);
		menuSideButton.setVerticalTextPosition(JButton.CENTER);
		menuSideButton.setBounds(5, 3, 60, 35);
		menuSideButton.setIcon(new ImageIcon("./img/abrir-menu.png"));
		menuSideButton.addActionListener(this);

		northPanel = new JPanel();
		northPanel.setLayout(null);
		northPanel.setPreferredSize(new Dimension(0, 40));
		northPanel.setBackground(Style.blue);

		manageMenuPanel = new JPanel();
		manageMenuPanel.setLayout(null);
		manageMenuPanel.setPreferredSize(new Dimension(60, 40));
		manageMenuPanel.setBackground(new Color(0x4ea295));
		manageMenuPanel.setBounds(0, 0, 80, 40);
		manageMenuPanel.add(menuSideButton);

		northPanel.add(manageMenuPanel);

		return northPanel;
	}

	JButton homeButton, contaButton, logoutButton, sairButton;

	private JButton homeButton(JPanel conteiner, Color bg, Color fg){

		homeButton = new JButton("Início");
		homeButton.setMnemonic('I');
		homeButton.setFont(new Font("Consolas", Font.BOLD, 20));
		homeButton.setForeground(Color.WHITE);
		homeButton.setOpaque(true);
		homeButton.setBackground(bg);
		homeButton.setPreferredSize(new Dimension(conteiner.getWidth(), 75));
		homeButton.setFocusable(false);
		homeButton.setFocusPainted(false);
		homeButton.setIcon(new ImageIcon("./img/home.png"));
		homeButton.setVerticalTextPosition(JButton.CENTER);
		homeButton.setHorizontalTextPosition(JButton.RIGHT);
		homeButton.setHorizontalAlignment(JButton.CENTER);
		homeButton.setVerticalAlignment(JButton.CENTER);
		homeButton.addActionListener(this);
		
		return homeButton;
	}

	private JButton contaButton(JPanel conteiner, Color bg, Color fg){

		contaButton = new JButton("Conta ");
		contaButton.setMnemonic('C');
		contaButton.setFont(new Font("Consolas", Font.BOLD, 20));
		contaButton.setForeground(Color.WHITE);
		contaButton.setOpaque(true);
		contaButton.setBackground(bg);
		contaButton.setPreferredSize(new Dimension(conteiner.getWidth(), 75));
		contaButton.setFocusable(false);
		contaButton.setFocusPainted(false);
		contaButton.setIcon(new ImageIcon("./img/profile.png"));
		contaButton.setVerticalTextPosition(JButton.CENTER);
		contaButton.setHorizontalTextPosition(JButton.RIGHT);
		contaButton.setHorizontalAlignment(JButton.CENTER);
		contaButton.setVerticalAlignment(JButton.CENTER);
		contaButton.addActionListener(this);
		
		return contaButton;
	}

	private JButton logoutButton(JPanel conteiner, Color bg, Color fg){

		logoutButton = new JButton("Logout");
		logoutButton.setMnemonic('L');
		logoutButton.setFont(new Font("Consolas", Font.BOLD, 20));
		logoutButton.setForeground(Color.WHITE);
		logoutButton.setOpaque(true);
		logoutButton.setBackground(bg);
		logoutButton.setPreferredSize(new Dimension(conteiner.getWidth(), 75));
		logoutButton.setFocusable(false);
		logoutButton.setFocusPainted(false);
		logoutButton.setIcon(new ImageIcon("./img/logout2.png"));
		logoutButton.setVerticalTextPosition(JButton.CENTER);
		logoutButton.setHorizontalTextPosition(JButton.RIGHT);
		logoutButton.setHorizontalAlignment(JButton.CENTER);
		logoutButton.setVerticalAlignment(JButton.CENTER);
		logoutButton.addActionListener(this);
		
		return logoutButton;
	}

	private JButton sairButton(JPanel conteiner, Color bg, Color fg){

		sairButton = new JButton("Sair  ");
		sairButton.setMnemonic('S');
		sairButton.setFont(new Font("Consolas", Font.BOLD, 20));
		sairButton.setForeground(Color.WHITE);
		sairButton.setOpaque(true);
		sairButton.setBackground(bg);
		sairButton.setPreferredSize(new Dimension(conteiner.getWidth(), 75));
		sairButton.setFocusable(false);
		sairButton.setFocusPainted(false);
		sairButton.setIcon(new ImageIcon("./img/power-button.png"));
		sairButton.setVerticalTextPosition(JButton.CENTER);
		sairButton.setHorizontalTextPosition(JButton.RIGHT);
		sairButton.setHorizontalAlignment(JButton.CENTER);
		sairButton.setVerticalAlignment(JButton.CENTER);
		sairButton.addActionListener(this);
		
		return sairButton;
	}

	public JPanel defaultMenuSideBar(Color bg, Color fg){
		
		// manageMenuPanel.setBackground(new Color(0x4ea295));
		// menuSideButton.setBackground(new Color(0x4ea295));
		GridLayout __grid = new GridLayout(12, 1, 15, 5);
		JPanel side_menu = new JPanel(__grid);
		JPanel title = new JPanel();
		JPanel homePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		// side_menu.setLayout(new GridLayout(16, 2));
		side_menu.setBackground(new Color(0x4ea295));
		side_menu.setPreferredSize(new Dimension(250, 255));
		side_menu.setVisible(true);

		homePanel.setBackground(bg);

		// title.setBackground(bg);

		title.setBackground(bg);
		side_menu.add(title);
		side_menu.add(homeButton(side_menu, bg, fg));
		side_menu.add(contaButton(side_menu, bg, fg));
		side_menu.add(logoutButton(side_menu, bg, fg));
		side_menu.add(sairButton(side_menu, bg, fg));

		
		return side_menu;
	}

	public JPanel sideMenu(Color bg, Color fg){

		JLabel pesq_estudante_label;
		JLabel nivel_est_label;
		JLabel turma_est_label;

		GridLayout __grid = new GridLayout(12, 1, 15, 5);
		JPanel side_menu = new JPanel(__grid);

		JPanel id_est_panel = new JPanel(Style.flow_center);
		JPanel nivel_panel = new JPanel(Style.flow_center);
		JPanel turma_panel = new JPanel(Style.flow_center);

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

		//adding panels
		side_menu.add(id_est_panel);
		side_menu.add(nivel_panel);
		side_menu.add(turma_panel);
		side_menu.add(pesquisar);

		return side_menu;
	}
	
	
	public JPanel sideMenu_studants(Color bg, Color fg){

		JLabel ano_label;
		JLabel nivel_label;

		GridLayout __grid = new GridLayout(12, 1, 15, 5);
		JPanel side_menu = new JPanel(__grid);

		JPanel ano_panel = new JPanel(Style.flow_center);
		JPanel nivel_panel = new JPanel(Style.flow_center);

		// side_menu.setLayout(new GridLayout(16, 2));
		side_menu.setBackground(bg);
		side_menu.setPreferredSize(new Dimension(350, 0));

		side_menu.setVisible(true);

		//combo estudante label
		ano_label = new JLabel("Filtrar por ano");
		ano_label.setForeground(fg);
		ano_label.setFont(Style.tf_font);

		//year combo box
		String[] __ano__ = new String[] {"", "2024"};
		ano_combo = new JComboBox<>(__ano__);
		ano_combo.setSelectedIndex(0);
		ano_combo.addActionListener(this);
		ano_combo.setPreferredSize(new Dimension(170, 25));

		//year estudante panel
		ano_panel.setBackground(bg);
		ano_panel.add(ano_label);
		ano_panel.add(ano_combo);

		//nivel label
		nivel_label = new JLabel("Filtrar pelo nivel");
		nivel_label.setFont(Style.tf_font);
		nivel_label.setForeground(fg);

		//nivel combo
		nivel_combo = new JComboBox<>(nivel_text);
		nivel_combo.setSelectedIndex(0);
		nivel_combo.addActionListener(this);

		//nivel panel
		nivel_panel.setBackground(bg);
		nivel_panel.add(nivel_label);
		nivel_panel.add(nivel_combo);

		side_menu.add(new JLabel(""));
		
		//pesquisar button
		listar_estudante = new JButton("Pesquisar");
		listar_estudante.setPreferredSize(new Dimension(side_menu.getWidth(), 70));
		listar_estudante.setBackground(Style.bg);
		listar_estudante.setFocusable(false);
		listar_estudante.setIcon(pesq_icon);
		listar_estudante.addActionListener(this);
		listar_estudante.addMouseListener(this);
		listar_estudante.setBackground(Style.table_bg);
		listar_estudante.setMnemonic('P');
		
		//adding panels
		side_menu.add(ano_panel);
		side_menu.add(nivel_panel);
		side_menu.add(listar_estudante);

		return side_menu;
	}


	//setting the buttons
	private void botoes(JButton btn, String btn_txt, Color bg, Color fg, Font ft, JComponent conteiner){
		btn = new JButton(btn_txt);
		btn.setFont(ft);
		btn.setBackground(bg);	//mid blue = 0x3960a1 | TR orange = 0xf2701a
		btn.setForeground(fg);
		btn.setFocusable(false);

		conteiner.add(btn);

	}
	
	
	//setting the admin menu bar
	public void set_adm_jmenu_bar(JFrame jf){
		//=========================Side Bar JMenu==============================
		create_jmenu(side_bar, "Menu", Style.menu_font, side_JMenuItem, side_bar_items);
		side_JMenuItem[0].setIcon(new ImageIcon("./img/menu.png"));
		// side_bar.setIcon(new ImageIcon("./img/"));
		//=====================================================================



		//=========================estudante JMenu=============================
		create_jmenu(estudante, "Estudante", Style.menu_font, est_JMenuItem, est_items);
		est_JMenuItem[0].setIcon(new ImageIcon("./img/lista_estudante.png"));
		est_JMenuItem[1].setIcon(new ImageIcon("./img/desempenho.png"));
		est_JMenuItem[2].setIcon(new ImageIcon("./img/visa.png"));
		est_JMenuItem[3].setIcon(new ImageIcon("./img/cadastrar_est.png"));
		//=====================================================================
		


		//=========================funcionario JMenu===========================
		create_jmenu(funcionario, "Funcionario", Style.menu_font, func_JMenuItem, func_items);

		func_JMenuItem[0].setIcon(new ImageIcon("./img/estudante.png"));
		func_JMenuItem[1].setIcon(new ImageIcon("./img/adicionar-usuario.png"));
		//======================================================================



		//========================Conta JMenu===================================
		create_jmenu(conta, "Conta", Style.menu_font, conta_JMenuItem, conta_items);

		conta_JMenuItem[0].setIcon(new ImageIcon("./img/perfil.png"));
		conta_JMenuItem[1].setIcon(new ImageIcon("./img/logout.png"));
		conta_JMenuItem[2].setIcon(new ImageIcon("./img/__sair.png"));
		//======================================================================



		//========================Ajuda JMenu===================================
		create_jmenu(ajuda, "Ajuda", Style.menu_font, ajuda_JMenuItem, ajuda_items);

		ajuda_JMenuItem[0].setIcon(new ImageIcon("./img/__ajuda.png"));
		ajuda_JMenuItem[1].setIcon(new ImageIcon("./img/ajuda.png"));
		ajuda_JMenuItem[2].setIcon(new ImageIcon("./img/logout.png"));
		ajuda_JMenuItem[3].setIcon(new ImageIcon("./img/__sair.png"));
		//======================================================================

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

	public JPanel setHomeBackground(){


		ImageIcon background = new ImageIcon("./img/background.png");
		

		JPanel homeBackgroundPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		JLabel homeBackgroundLabel = new JLabel("the emmanuel english school".toUpperCase());

		homeBackgroundLabel.setIcon(new ImageIcon(background.getImage()));
		homeBackgroundLabel.setFont(new Font("Mv Boli", Font.BOLD, 40));
		homeBackgroundLabel.setForeground(Color.orange);
		homeBackgroundLabel.setHorizontalTextPosition(JLabel.CENTER);
		homeBackgroundLabel.setVerticalTextPosition(JLabel.CENTER);

		homeBackgroundPanel.setBackground(Style.blue);
		homeBackgroundPanel.add(homeBackgroundLabel);

		return homeBackgroundPanel;
	}

	
	int side_status = 0;
	int list_status = 0;
	int add_status = 0;
	int reg_func_status = 0;
	int list_func_status = 0;
	int mens_status = 0;
	int conta_status = 0;

	
	Login login;
	CadastrarEstudante reg_est;
	ListaNominalEstudante lista_est;
	Mensalidades mensalidade;
	Pauta pauta;
	ContaUser contaUser;
	String[] query_objects = new String[]{"Pauta", "Mensalidade"};
	String query_object = "";
	// JComponent side = Utilitario.menu_ref;
	Color sideBackground = new Color(0x4ea285);
	JComponent side = this.defaultMenuSideBar(sideBackground, Style.tf_bg);

	CadastrarFuncionario reg_func;
	ListaNominalFuncionario list_func;

	@Override
	public void actionPerformed(ActionEvent e){

		//manange left side menu
		if(e.getSource().equals(side_JMenuItem[0]) || e.getSource().equals(menuSideButton)){

			if(side_status == -1 || side_status == 0){
				side_status = Login.janela.addMenuSide(side, BorderLayout.WEST, "Menu");
				manageMenuPanel.setSize(250, 40);
			}
			else{
				side_status = Login.janela.removeConteiner(side);
				manageMenuPanel.setSize(80, 40);
			}
		}
		
		//list added students
		else if(e.getSource().equals(est_JMenuItem[0])){
				side = this.sideMenu_studants(Style.blue, Style.tf_bg);

				lista_est = new ListaNominalEstudante(est_query);
				list_status = Login.janela.addConteiner(lista_est, BorderLayout.CENTER, "Lista Nominal De Estudantes");

				lista_est.start_list();
				// side_status = -1;
			
		}
		
		//pauta
		else if(e.getSource().equals(est_JMenuItem[1])){
			
			side = this.sideMenu(Style.blue, Style.tf_bg);
			query_object = query_objects[0];

			pauta = new Pauta("SELECT * FROM avaliacao_nivel1", "avaliacao_nivel1");
				list_status = Login.janela.addConteiner(pauta, BorderLayout.CENTER, "Pauta");

				pauta.start_table();
				// side_status = -1;
		}

		//monthly
		else if(e.getSource().equals(est_JMenuItem[2])){
			
			side = this.sideMenu(Style.blue, Style.tf_bg);
			query_object = query_objects[1];

		
			mensalidade = new Mensalidades("SELECT id_estudante, mes, status, divida FROM mensalidade");
			Login.janela.addConteiner(mensalidade, BorderLayout.CENTER, "Controlo de mensalidades");

			mensalidade.start_table();
			
			// side_status = -1;
			
		}

		//add students
		else if(e.getSource().equals(est_JMenuItem[3])){	//Register student
			
			side = this.defaultMenuSideBar(sideBackground, Style.tf_bg);

			reg_est = new CadastrarEstudante();
			add_status = Login.janela.addConteiner(reg_est.scroll, BorderLayout.CENTER, "Cadastrar Estudantes");
			reg_est.start();

			// side_status = -1;
			
		}
		else if(e.getSource().equals(func_JMenuItem[0])){	//List registered officers

				side = this.defaultMenuSideBar(sideBackground, Style.tf_bg);

				list_func = new ListaNominalFuncionario();
				list_func_status = Login.janela.addConteiner(list_func, BorderLayout.CENTER, "Listar funcionarios");
				
				list_func.start_list();

				// side_status = -1;

		}
		else if(e.getSource().equals(func_JMenuItem[1])){	//register a offical

				side = this.defaultMenuSideBar(sideBackground, Style.tf_bg);

				reg_func = new CadastrarFuncionario();
				reg_func_status = Login.janela.addConteiner(reg_func, BorderLayout.CENTER, "Registar funcionarios");
				// side_status = -1;
		}
		//account details
		else if(e.getSource().equals(conta_JMenuItem[0]) || e.getSource().equals(contaButton)){
			System.out.println("detalhes da conta");
			side = this.defaultMenuSideBar(sideBackground, Style.tf_bg);

			contaUser = new ContaUser();
			conta_status = Login.janela.addConteiner(contaUser, BorderLayout.CENTER, "Detalhes da conta");
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
			JOptionPane.showMessageDialog(Login.janela, "Para ajuda por favor contacte o administrador +258 842 926 333 (Frenque Tovela)\n\nO desenvolvedor: +258 845 220 593 (Fernando Maleiane)", "Ajuda", JOptionPane.INFORMATION_MESSAGE);
		}

		//about us
		else if(e.getSource().equals(ajuda_JMenuItem[1])){	
			JOptionPane.showMessageDialog(Login.janela, "Somos a Emmanuel English School, uma escola especializada em ensinar a lingua inglesa atravês de métodos funcionais e eficazes", "Quem Somos Nós?", JOptionPane.INFORMATION_MESSAGE);
		}

		else if(e.getSource().equals(ajuda_JMenuItem[2]) || e.getSource().equals(logoutButton)){	//Logout button - JMenuItem

			this.logout();
		}
		
		else if(e.getSource().equals(ajuda_JMenuItem[3]) || e.getSource().equals(sairButton)){	//Exit button - JMenuItem
			this.exit();
		}
		else if(e.getSource().equals(homeButton)){
			Login.janela.getContentPane().removeAll();

			Login.janela.getContentPane().add(setHomeBackground(), BorderLayout.CENTER);

			Login.janela.getContentPane().add(this.setNorthPanel(), BorderLayout.NORTH);

			if(side_status == 1){
				side_status = Login.janela.addMenuSide(side, BorderLayout.WEST, "Menu");
			}
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
				query = "SELECT * FROM " + __nivel_avaliacao + " WHERE id_estudante = ?";
				System.out.println(4);

				// pauta = new Pauta(query, tabela);
				hasId = false;
			}

			
			if(query_object.equals(query_objects[0])){
				Login.janela.getContentPane().remove(pauta);

				if(hasId){
					pauta = new Pauta(query, tabela, id_estudante);
				}
				else {
					pauta = new Pauta(query, tabela);
				}

				pauta.start_table();
				Login.janela.getContentPane().add(pauta, BorderLayout.CENTER);

			}
			else if(query_object.equals(query_objects[1])){
				Login.janela.getContentPane().remove(mensalidade);
				
				if(hasId){
					mensalidade = new Mensalidades("SELECT id_estudante, mes, status, divida FROM mensalidade WHERE id_estudante = ?", id_estudante);
				}
				else {
					mensalidade = new Mensalidades("SELECT id_estudante, mes, status, divida FROM mensalidade");
				}

				mensalidade.start_table();
				Login.janela.getContentPane().add(mensalidade, BorderLayout.CENTER);
			}


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
		else if(e.getSource().equals(listar_estudante)){
			if(nivel_combo.getSelectedItem() != nivel_combo.getItemAt(0)){
				String nivel = nivel_combo.getSelectedItem().toString();
				System.out.println(nivel);
				est_query = "SELECT * FROM estudante WHERE nivel = '"+ nivel +"'";
				

				Login.janela.getContentPane().remove(lista_est);

				lista_est = new ListaNominalEstudante(est_query);
				lista_est.start_list();

				Login.janela.getContentPane().add(lista_est, BorderLayout.CENTER);
				Login.janela.getContentPane().revalidate();
				Login.janela.getContentPane().repaint();
			}
			
		}

		if(side_status == 1){
			side_status = Login.janela.addMenuSide(side, BorderLayout.WEST, "Menu");
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {

	}


	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource().equals(pesquisar) || e.getSource().equals(listar_estudante)){
			try{
				pesquisar.setIcon(pesq_icon2);
			}catch(NullPointerException ex){
				listar_estudante.setIcon(pesq_icon2);
			}
		}
	}


	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource().equals(pesquisar) || e.getSource().equals(listar_estudante)){
			try{
				pesquisar.setIcon(pesq_icon);
			}catch(NullPointerException ex){
				listar_estudante.setIcon(pesq_icon);
			}
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {

	}


	@Override
	public void mouseReleased(MouseEvent e) {

	}
}

