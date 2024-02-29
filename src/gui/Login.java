package gui;

import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;


public class Login extends JFrame implements ActionListener{
	JPanel header, cadastro, nome_panel, email_panel, senha_panel, politica_panel, submit_panel;
	JLabel title, nome_label, email_label, senha_label;
	JTextField nome_tf, email_tf, senha_tf;
	JCheckBox politica_p;
	JButton submit_button, clear_button;
	ImageIcon icon = new ImageIcon("./img/tr.jpg");
	
	FlowLayout flow = new FlowLayout(FlowLayout.CENTER, 5, 35);
	FlowLayout flow_2 = new FlowLayout(FlowLayout.CENTER);
	BorderLayout border = new BorderLayout();
	GridLayout grid = new GridLayout(6,1);
	
	Font consolas_16 = new Font("Consolas", Font.PLAIN, 16);
	
	Color bg = new Color(0x2c2c2c);
	Color fg = new Color(0xed6817);
	//Color tf_bg = new Color(0xed6817);
	Color tf_bg = new Color(0xb0a7a7);
	Color tf_fg = new Color(0x123456);
	Color label_fg = new Color(0xffffff);
	Font tf_font = new Font("Consolas", Font.BOLD, 15);
	
	public Login () {
		this.setTitle("Tela de cadastro");
		this.setSize(500, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(border);
		this.getContentPane().setBackground(bg);
		
		title = new JLabel();
		title.setFont(new Font("Consolas", Font.BOLD, 30));
		title.setText("Bem-Vindo");
		title.setForeground(fg);
		title.setIcon(icon);
		title.setHorizontalTextPosition(JLabel.CENTER);
		title.setVerticalTextPosition(JLabel.BOTTOM);
		
		header = new JPanel(flow);
		header.add(title);	//adding the title to the container
		header.setBackground(bg);
		
		this.add(header, border.NORTH);
		
		formulario_cadastro();
		this.validate();
	}
	
	
	public void formulario_cadastro(){
		cadastro = new JPanel(grid);
		cadastro.setBackground(bg);
		
		flow_2.setHgap(5);
		flow_2.setVgap(1);
		
		nome_label = new JLabel("Usuario: ");
		nome_label.setFont(consolas_16);
		nome_label.setForeground(label_fg);
		
		nome_tf = new JTextField(15);
		nome_tf.setFont(new Font("Sans Serif", Font.BOLD, 16));
		nome_tf.setBackground(tf_bg);
		nome_tf.setForeground(new Color(0x123456));
		
		nome_panel = new JPanel(flow_2);
		nome_panel.setBackground(bg);
		nome_panel.add(nome_label);
		nome_panel.add(nome_tf);
		
		cadastro.add(nome_panel);

		email_label = new JLabel("Email: ");
		email_label.setFont(consolas_16);
		email_label.setForeground(fg);
		
		email_tf = new JTextField(15);
		email_tf.setFont(nome_tf.getFont());
		email_tf.setBackground(tf_bg);
		email_tf.setForeground(tf_fg);
		
		email_panel = new JPanel(flow_2);
		email_panel.setBackground(bg);
		email_panel.add(email_label);
		email_panel.add(email_tf);
		
		//cadastro.add(email_panel);
		
		senha_label = new JLabel("Senha: ");
		senha_label.setFont(consolas_16);
		senha_label.setForeground(label_fg);
		
		
		senha_tf = new JTextField(15);
		senha_tf.setFont(nome_tf.getFont());
		senha_tf.setBackground(tf_bg);
		senha_tf.setForeground(tf_fg);
		
		senha_panel = new JPanel(flow_2);
		senha_panel.setBackground(bg);
		senha_panel.add(senha_label);
		senha_panel.add(senha_tf);
		
		cadastro.add(senha_panel);
		
		politica_p = new JCheckBox("Aceitar a politica de privacidade");
		politica_p.setForeground(new Color(0x2fcee0));
		politica_p.setBackground(bg);
		politica_p.setFont(new Font("Consolas", Font.ITALIC, 16));
		politica_p.setFocusable(false);
		politica_p.addActionListener(this);
		
		submit_button = new JButton("Login");
		submit_button.setBackground(Color.white);
		submit_button.setForeground(Color.black);
		submit_button.setFocusable(false);
		submit_button.setEnabled(false);
		
		clear_button = new JButton("clear form");
		clear_button.setBackground(Color.red);
		clear_button.setForeground(bg);
		clear_button.setFocusable(false);
		clear_button.addActionListener(this);
		
		politica_panel = new JPanel(flow_2);
		politica_panel.setBackground(bg);
		politica_panel.add(politica_p);
				
		submit_panel = new JPanel(flow_2);
		submit_panel.setBackground(bg);
		
		//submit_panel.add(clear_button);
		submit_panel.add(submit_button);
		
		cadastro.add(politica_panel);
		cadastro.add(submit_panel);
		
		this.add(cadastro, border.CENTER);
		cadastro.validate();
	}
	
	public void actionPerformed (ActionEvent e){
		if(e.getSource().equals(politica_p)){
				if(politica_p.isSelected()){
					submit_button.setEnabled(true);
				} else {
					submit_button.setEnabled(false);
				}
		}
		
		if (e.getSource().equals(clear_button)){
			nome_tf.setText("");
			email_tf.setText("");
			senha_tf.setText("");
		}
		
		
		this.validate();
	}
	
}

