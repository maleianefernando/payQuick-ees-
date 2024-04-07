package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import gui.estudante.ListaNominalEstudante;
import gui.estudante.Mensalidades;
import gui.funcionario.CadastrarFuncionario;
import gui.funcionario.ListaNominalFuncionario;
import gui.util.Style;
import xutil.Utilitario;

public class Menu extends JPanel implements ActionListener, MouseListener, Utilitario{
	public JPanel panel_menu = new JPanel();

	//side bar JMenuIems titles
	private String[] side_bar_items = new String[] {"Menu"};	//
	private String[] est_items = new String[] {"Lista Nominal", "Desempenho", "Mensalidades", "Disciplinas Lecionadas", "Cadastrar"};	//items of the menu 'estudante'
	private String[] func_items = new String[] {"Lista Nominal", "Salários", "Pendentes", "Cadastrar"};	//items of the menu 'funcionario'
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

		botoes(estudante_cadastrado, "Estudante Cadastrado", Style.blue, Color.white, Style.ft);
		botoes(funcionario_cadastrado, "Funcionario Cadastrado", Style.blue, Color.white, Style.ft);
		botoes(cadastrar, "Cadastrar", Style.blue, Color.white, Style.ft);
		botoes(turmas, "Turmas", Style.blue, Color.white, Style.ft);
		
		this.revalidate();
		this.repaint();
	}
	
	
	//setting the buttons
	private void botoes(JButton btn, String btn_txt, Color bg, Color fg, Font ft){
		btn = new JButton(btn_txt);
		btn.setFont(ft);
		btn.setBackground(bg);	//mid blue = 0x3960a1 | TR orange = 0xf2701a
		btn.setForeground(fg);
		btn.setFocusable(false);
		//btn.setPreferredSize(new Dimension(375, 50));
		btn.addActionListener(e -> {

			System.out.println("Estudante cadastrado");
		});
		btn.addActionListener(this);
		//System.out.println(btn.getActionListeners() + "\n" + btn);

		this.add(btn);

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
	
	int side_status = 0;
	int list_status = 0;
	int add_status = 0;
	int reg_func_status = 0;
	int list_func_status = 0;
	int mens_status = 0;

	
	Login login;

	ListaNominalEstudante lista_est = new ListaNominalEstudante();
	Mensalidades mensalidade;

	CadastrarFuncionario reg_func = new CadastrarFuncionario();
	ListaNominalFuncionario list_func = new ListaNominalFuncionario();

	@Override
	public void actionPerformed(ActionEvent e){

		//manange left side menu
		if(e.getSource().equals(side_JMenuItem[0])){
			
			
			if(side_status == -1 || side_status == 0){
				side_status = Login.janela.addMenuSide(Utilitario.menu_ref, BorderLayout.WEST, "Menu");
				//System.out.println("menu removido,  status: " + side_status);
				
			}
			else{
				side_status = Login.janela.removeConteiner(Utilitario.menu_ref);
				//System.out.println("menu adicionado,  status: " + side_status);
				
			}
		}
		
		//list added students
		else if(e.getSource().equals(est_JMenuItem[0])){

			// System.out.println(e.getSource().equals(estudante_cadastrado));
			// if(list_status == 0 || list_status == -1){
				lista_est = new ListaNominalEstudante();
				list_status = Login.janela.addConteiner(lista_est, BorderLayout.CENTER, "Lista Nominal De Estudantes");

				lista_est.start_list();
				side_status = -1;
			// }
			// else {
			// 	list_status = Login.janela.removeConteiner(lista_est);
			// 	lista_est.fill_table_status = -1;
			// }
			
		}
		
		//monthly
		else if(e.getSource().equals(est_JMenuItem[2])){
			// String pesq_nome = JOptionPane.showInputDialog(Login.janela, "Nome do esudante", "Pesquisar Mensalidades", JOptionPane.INFORMATION_MESSAGE);

			// if(mens_status == 0 || mens_status == -1){
				mensalidade = new Mensalidades();
				Login.janela.addConteiner(mensalidade, BorderLayout.CENTER, "Controlo de mensalidades");

				mensalidade.start_table();
				
				side_status = -1;
			// }
			// else {
			// 	mens_status = Login.janela.removeConteiner(mensalidade);
			// }
			//Login.janela.getContentPane().removeAll();
		}

		//add students
		else if(e.getSource().equals(est_JMenuItem[4])){	//Register student
			
//			if(add_status == 0 || add_status == -1){
				add_status = Login.janela.addConteiner(Utilitario.cadastrar_estudante_ref, BorderLayout.CENTER, "Cadastrar Estudantes");

				side_status = -1;
			// }
			// else {
			// 	add_status = Login.janela.removeConteiner(Utilitario.cadastrar_estudante_ref);
			// }
		}
		else if(e.getSource().equals(func_JMenuItem[0])){	//List registered officers
//			if(list_func_status == 0 || list_func_status == -1){
				list_func_status = Login.janela.addConteiner(list_func, BorderLayout.CENTER, "Listar funcionarios");

				list_func.start_list();

				side_status = -1;
			// }else{
			// 	list_func_status = Login.janela.removeConteiner(list_func);
			// }
		}
		else if(e.getSource().equals(func_JMenuItem[3])){	//register a offical
			// if(reg_func_status == 0 || reg_func_status == -1){
				reg_func_status = Login.janela.addConteiner(reg_func, BorderLayout.CENTER, "Registar funcionarios");

				side_status = -1;
			// }
			// else {
			// 	reg_func_status = Login.janela.removeConteiner(reg_func);
			// }
		}

		else if(e.getSource().equals(ajuda_JMenuItem[0])){
			JOptionPane.showMessageDialog(Login.janela, "Esta funcionalidade ainda está em desenvolvimento", "Ajuda", JOptionPane.INFORMATION_MESSAGE);
		}

		else if(e.getSource().equals(ajuda_JMenuItem[1])){	//About us
			JOptionPane.showMessageDialog(Login.janela, "Somos a Emmanuel English School, uma escola especializada em ensinar a lingua inglesa atravês de métodos funcionais e eficazes", "Quem Somos Nós?", JOptionPane.INFORMATION_MESSAGE);
		}

		else if(e.getSource().equals(ajuda_JMenuItem[2])){	//Logout button - JMenuItem
			//Utilitario.login_ref.janela.dispose();
			Login.janela.dispose();
			login = new Login();
			login.start();
		}
		
		else if(e.getSource().equals(ajuda_JMenuItem[3])){	//Exit button - JMenuItem
			Integer exit_status = JOptionPane.showConfirmDialog(Login.janela, "Sair?", "Confirmar saida", JOptionPane.YES_NO_OPTION);

			if(exit_status.equals(0)){
				System.exit(0);
			}
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
	public void mousePressed(MouseEvent e) {

	}


	@Override
	public void mouseReleased(MouseEvent e) {

	}
}

