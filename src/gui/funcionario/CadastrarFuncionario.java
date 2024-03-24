package gui.funcionario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import connection.Conexao;
import gui.util.Style;

public class CadastrarFuncionario extends JPanel implements ActionListener{
    JPanel panels[];
    JPanel title_panel = new JPanel(Style.flow_center);
    JLabel labels[];
    JLabel title = new JLabel("REGISTAR FUNCIONARIOS");
    JTextField text_fields[];
    JCheckBox is_user = new JCheckBox("É utilizador da aplicação?");

    GridLayout grid151 = new GridLayout(15, 1);

    JButton submit = new JButton("Salvar");
	JButton clear = new JButton("Limpar Formulario");

    String[] form_text = new String[] {"Nome Completo", "Idade", "Residencia", "Documento de identificacao", "Experiencia Profissional", "Habilidades", "Funcao"};

    public CadastrarFuncionario(){
        panels = new JPanel[15];
        labels = new JLabel[form_text.length];
        text_fields = new JTextField[form_text.length];

        this.setLayout(grid151);
		this.setBackground(Style.bg);
		
		title.setFont(new Font("Consolas", Font.BOLD, 30));
		title.setForeground(Style.fg);
		title_panel.add(title);
		title_panel.setBackground(Style.bg);
		
		this.add(new JLabel(""));
		this.add(title_panel);
		this.add(new JLabel(""));
		this.addPanel(panels, labels, text_fields);
        
        this.addButtons(submit);
		this.addButtons(clear);

        this.add(new JLabel(""));

        JPanel footer = new JPanel();
        footer.setBackground(Style.tf_fg);
        //this.add(footer);
    }


    private void addPanel(JPanel[] panel, JLabel[] label, JTextField[] text_field){
        //Adding all panels
        // for(int i = 0; i < panels.length; i++){
        //     panel[i] = new JPanel(Style.flow_center);
        //     this.add(panel[i]);
        // }

        //adding the components to the panels
        for(int i = 0; i < form_text.length+1; i++){
            try{
                label[i] = new JLabel(form_text[i]);
                label[i].setFont(Style.tf_font);
                
                text_field[i] = new JTextField(25);
                text_field[i].setFont(Style.tf_font);
                
                panel[i] = new JPanel(Style.flow_center);
                panel[i].setBackground(Style.bg);
                panel[i].add(label[i]);
                panel[i].add(text_field[i]);
                
                panel[i].setPreferredSize(new Dimension(200, 50));
                
                this.add(panel[i]);
            }catch (ArrayIndexOutOfBoundsException ex){
                panel[i] = new JPanel(Style.flow_center);
                panel[i].setBackground(Style.bg);

                is_user.setBackground(Style.bg);
                is_user.setFocusable(false);
                is_user.setFont(Style.tf_font);
                panel[i].add(is_user);

                panel[i].setPreferredSize(new Dimension(200, 50));

                this.add(panel[i]);
            }
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

    private void save_data(String name, Integer age, String adress, String id_nr, String experience, String skills, String position){

        String id = "@";
        Random r = new Random();

        for(int i = 0; i < 8; i ++){
            if(i == 7){
                id += "#";
            }else{
                id += r.nextInt(9);
            }
        }

        if(is_user.isSelected()){

        } else{
            String query = "INSERT INTO funcionarios (id_funcionarios, nome, idade, identificacao, habilidades, experiencia_profissional, funcao) VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = null;
            try {
                ps = Conexao.getConexao().prepareStatement(query);

                ps.setString(1, id);
                ps.setString(2, name);
                ps.setInt(3, age);
                ps.setString(4, id_nr);
                ps.setString(5, skills);
                ps.setString(6, experience);
                ps.setString(7, position);

                ps.execute();
                
            } catch (Exception e) {
                System.out.println("Insert failed!!");
            }

        }
    }

    private void clear_form(JTextField[] text_field){
        for(int i = 0; i < text_field.length; i++){
            text_field[i].setText("");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(submit)){
            Integer age = Integer.parseInt(text_fields[1].getText());

            System.out.println("submit");
            save_data(text_fields[0].getText(), age, text_fields[2].getText(), text_fields[3].getText(), text_fields[4].getText(), text_fields[5].getText(), text_fields[6].getText());
        }

        else if(e.getSource().equals(clear)){
            clear_form(text_fields);
        }
    }
}
