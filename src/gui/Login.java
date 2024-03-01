package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import gui.Janela;

public class Login extends JFrame implements MouseListener, KeyListener{
	JPanel conteiner, header, cadastro, nome_panel, senha_panel, submit_panel;
	JLabel title, nome_label, email_label, senha_label;
	JTextField nome_tf, email_tf, senha_tf;
	JCheckBox politica_p;
	JButton login_button;
	ImageIcon icon = new ImageIcon("./img/..");
	
	FlowLayout flow = new FlowLayout(FlowLayout.CENTER, 5, 35);
	FlowLayout flow_2 = new FlowLayout(FlowLayout.CENTER);
	BorderLayout border = new BorderLayout();
	GridLayout grid = new GridLayout(4,1);
	Dimension tf_size = new Dimension(350, 40);
	Dimension panel_size = new Dimension(350, 20);
	
	Font tf_font = new Font("Sans Serif", Font.PLAIN, 16);	//text field font
	Font consolas_16 = new Font("Consolas", Font.PLAIN, 16);
	Font tf_font2 = new Font("Sans Serif", Font.BOLD, 16);
	
	Color bg = new Color(0xdddddb); // Linux backgroung = 0x2c2c2c
	Color fg = new Color(0x3e455f);	// Orange linux foreground
	//Color tf_bg = new Color(0xed6817);	//text field background
	Color tf_bg = new Color(0xffffff);	//text-field background
	Color tf_fg = new Color(0x123456);	//text-field forground
	Color label_fg = new Color(0x000000);	//label text-field
	//Font tf_font = new Font("Consolas", Font.BOLD, 15);	//text field font
	
	String placeholder[] = new String[2];
	
	public Login () {
		
		placeholder[0] = "Seu nome de utilizador ou email";
		placeholder[1] = "Sua senha";
	
	
		this.setTitle("Login");
		this.setSize(1050, 550);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(border);
		this.getContentPane().setBackground(bg);
		this.setLocationRelativeTo(null);
		
		title = new JLabel();
		title.setFont(new Font("Consolas", Font.BOLD, 30));
		title.setText("TURMA DOS REVOLTADOS");
		title.setForeground(fg);
		title.setIcon(icon);
		title.setHorizontalTextPosition(JLabel.CENTER);
		title.setVerticalTextPosition(JLabel.BOTTOM);
		title.addMouseListener(this);
		
		header = new JPanel(flow);
		header.setPreferredSize(new Dimension(1,100));
		header.add(title);	//adding the title to the container
		header.setBackground(bg);
		
		formulario_cadastro();
		this.validate();
	}
	
	
	public void formulario_cadastro(){
		cadastro = new JPanel(grid);
		cadastro.setBackground(bg);
		cadastro.add(header);
		
		flow_2.setHgap(55);
		flow_2.setVgap(15);
		
		nome_label = new JLabel("UTILIZADOR: ");
		nome_label.setFont(consolas_16);
		nome_label.setForeground(label_fg);
		
		nome_tf = new JTextField(25);
		nome_tf.setFont(tf_font);
		nome_tf.setBackground(tf_bg);
		nome_tf.setForeground(new Color(0x123456));
		nome_tf.setPreferredSize(tf_size);
		nome_tf.setText(placeholder[0]);
		nome_tf.addMouseListener(this);
		nome_tf.addKeyListener(this);
		
		nome_panel = new JPanel(flow_2);
		nome_panel.setBackground(bg);
		nome_panel.add(nome_label);
		nome_panel.add(nome_tf);
		
		cadastro.add(nome_panel);
		
		
		senha_label = new JLabel("SENHA: ");
		senha_label.setFont(consolas_16);
		senha_label.setForeground(label_fg);
		
		
		senha_tf = new JTextField(25);
		senha_tf.setFont(tf_font);
		senha_tf.setBackground(tf_bg);
		senha_tf.setForeground(tf_fg);
		senha_tf.setPreferredSize(tf_size);
		senha_tf.setText(placeholder[1]);
		senha_tf.addMouseListener(this);
		senha_tf.addKeyListener(this);
		
		senha_panel = new JPanel(flow_2);
		senha_panel.setBackground(bg);
		senha_panel.add(senha_label);
		senha_panel.add(senha_tf);
		
		cadastro.add(senha_panel);
		
		
		login_button = new JButton("LOGIN");
		login_button.setFont(new Font("Arial Black", Font.PLAIN, 16));
		login_button.setBackground(new Color(0x3960a1));	//mid blue = 0x3960a1 | TR orange = 0xf2701a
		login_button.setForeground(Color.white);
		login_button.setFocusable(false);
		login_button.setPreferredSize(new Dimension(375, 50));
		login_button.addMouseListener(this);
		login_button.addKeyListener(this);
				
		submit_panel = new JPanel(flow_2);
		submit_panel.setBackground(bg);
		submit_panel.setPreferredSize(panel_size);
		
		submit_panel.add(login_button);
		cadastro.add(submit_panel);
		
		ImageIcon img = new ImageIcon("./img/__tr.jpg");
		JLabel img_label = new JLabel("Faca Login e torne-se um revoltado");
		img_label.setBackground(new Color(0x3960a1));
		img_label.setForeground(new Color(0xf2701a));
		img_label.setFont(new Font("Arial", Font.BOLD, 24));
		img_label.setIcon(img); 
		img_label.setHorizontalTextPosition(JLabel.CENTER);
		img_label.setVerticalTextPosition(JLabel.TOP);
		
		JPanel left_conteiner = new JPanel(new BorderLayout());
		left_conteiner.setBackground(bg);
		left_conteiner.add(img_label, BorderLayout.CENTER);
		
		
		//setting the container
		conteiner = new JPanel(new GridLayout(1,2));
		conteiner.setBackground(new Color(0xf2701a));
		conteiner.add(left_conteiner);
		conteiner.add(cadastro);
		this.add(conteiner, border.CENTER);
		cadastro.validate();
		cadastro.setBackground(new Color(0xf2701a));
	}
	
	int tentativas = 3;	
	private void login(String nome, String senha){
		
		if(nome.equals("Admin") && senha.equals("admin")){
			this.dispose();
			new Janela();
		}
		else {
			if(tentativas != 0)
				tentativas--;
			
			if (nome.equals(placeholder[0]) || senha.equals(placeholder[1])){
				JOptionPane.showMessageDialog(null, "Por favor, preencher todos os campos\n("+tentativas+") tentativas restantes", "Erro", JOptionPane.ERROR_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, "Credeenciais erradas\n("+tentativas+") tentativas restantes", "Erro", JOptionPane.ERROR_MESSAGE);
			}
			
			if(tentativas == 0){
				login_button.setEnabled(false);
			}
		}
	}
	
	//MouseListener Interface implementation
	@Override
    public void mouseClicked(MouseEvent e) {
		//handling text fields events
        if (e.getSource().equals(nome_tf)){
			if(nome_tf.getText().equals(placeholder[0])){
				nome_tf.setText("");
				nome_tf.setFont(tf_font2);
			}
        }
        
        if(e.getSource().equals(senha_tf)){
        	if(senha_tf.getText().equals(placeholder[1])){
				senha_tf.setText("");
				senha_tf.setFont(tf_font2);
        	}
        }

		//logging in
		if(e.getSource().equals(login_button)){
			login(nome_tf.getText(), senha_tf.getText());
		}
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
		
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    	//handling events from login button
        if(e.getSource().equals(login_button)){
        	login_button.setBackground(new Color(0xf2701a));	//set color to orange
        }

		//handling events from title JLabel
        if(e.getSource().equals(title)){
        	title.setForeground(new Color(0xf2701a));	//set color to orange
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
    
    	//hading events from text fields
        if (e.getSource().equals(nome_tf)){
			if(nome_tf.getText().equals("")){
				nome_tf.setText(placeholder[0]);
				nome_tf.setFont(tf_font);
			}
        }
        
        if (e.getSource().equals(senha_tf)){
			if(senha_tf.getText().equals("")){
				senha_tf.setText(placeholder[1]);
				senha_tf.setFont(tf_font);
			}
        }
        
        //handling events from login button
        if(e.getSource().equals(login_button)){
        	login_button.setBackground(new Color(0x3960a1));	//set color to blue
        }
        
        //handling events from title JLabel
        if(e.getSource().equals(title)){
        	title.setForeground(new Color(0x3960a1));	//set color to blue
        }
    }

	//KeyListener Interface implementation
	@Override
	public void keyTyped(KeyEvent e){
        if (e.getSource().equals(nome_tf)){
			if(nome_tf.getText().equals(placeholder[0])){
				nome_tf.setText("");
				nome_tf.setFont(tf_font2);
			}
        }
        if(e.getSource().equals(senha_tf)){
        	if(senha_tf.getText().equals(placeholder[1])){
				senha_tf.setText("");
				senha_tf.setFont(tf_font2);
        	}
        }
        
	}
	
	@Override
	public void keyPressed(KeyEvent e){
	
	}
	
	@Override
	public void keyReleased(KeyEvent e){
	
	}

	
}

