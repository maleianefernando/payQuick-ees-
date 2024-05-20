package gui.funcionario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import connection.Conexao;
import gui.Login;
import gui.util.Style;

public class ContaUser extends JPanel implements ActionListener{
    
    String name, newPasword, id, position;
    
    JPanel headerPanel, footerPanel, centerPanel;
    JLabel headerLabel;

    JLabel nameLabel, passwordLabel, newPasswordLabel, idLabel, positionLabel;
    JTextField nameTf, passwordTf, newPasswordTf, idTf, positionTf;

    JButton changePass, submitnewPassword;

    FlowLayout flowCenter = new FlowLayout(FlowLayout.CENTER);

    Font labelFont = new Font("Consolas", Font.PLAIN, 24);
    Font textFieldFont = new Font("Consolas", Font.ITALIC, 24);

    public ContaUser(){
        this.setPanel();
        this.add(this.setHeader(), BorderLayout.NORTH);
        this.add(this.setCenterPanel(labelFont, textFieldFont), BorderLayout.CENTER);
        this.add(this.setFooter(), BorderLayout.SOUTH);
    }

    private void setPanel(){
        this.setVisible(true);
        this.setBackground(Style.bg);
        this.setLayout(new BorderLayout());
    }

    private JPanel setHeader(){
        headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(Style.bg);
        headerPanel.setPreferredSize(new Dimension(255, 180));

        headerPanel.add(setHeaderLabel());

        return headerPanel;
    }

    private JPanel setFooter(){
        footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(Style.blue);
        footerPanel.setPreferredSize(new Dimension(0, 75));

        return footerPanel;
    }

    private JLabel setHeaderLabel(){

        headerLabel = new JLabel("Perfil");
        headerLabel.setIcon(new ImageIcon("./img/profileAccount.png"));
        headerLabel.setVerticalTextPosition(JLabel.BOTTOM);
        headerLabel.setHorizontalTextPosition(JLabel.CENTER);
        headerLabel.setFont(new Font("Consolas", Font.ITALIC, 20));

        return headerLabel;
    }

    private JPanel setCenterPanel(Font labelFont, Font tfFont){
        Dimension labelDim = new Dimension(0, 50);

        JPanel namePanel, idPanel, passwordPanel, newPasswordPanel, positionPanel;
        JPanel blankPanel = new JPanel();

        centerPanel = new JPanel(new GridLayout(7, 1));
        
        centerPanel.add(blankPanel);

        namePanel = new JPanel(flowCenter);
        nameLabel = new JLabel("Nome do utilizador: ");
        nameLabel.setFont(labelFont);
        nameTf = new JTextField(Login.atualUser.name, 30);
        nameTf.setFont(tfFont);
        nameTf.setPreferredSize(labelDim);
        nameTf.setEditable(false);
        
        namePanel.add(nameLabel);
        namePanel.add(nameTf);
        centerPanel.add(namePanel);

        idPanel = new JPanel(flowCenter);
        idLabel = new JLabel("                ID: ");
        idLabel.setFont(labelFont);
        idTf = new JTextField(Login.atualUser.id, 30);
        idTf.setFont(tfFont);
        idTf.setPreferredSize(labelDim);
        idTf.setEditable(false);

        idPanel.add(idLabel);
        idPanel.add(idTf);
        centerPanel.add(idPanel);

        positionPanel = new JPanel(flowCenter);


        positionPanel = new JPanel(flowCenter);
        positionLabel = new JLabel("             Cargo: ");
        positionLabel.setFont(labelFont);
        positionTf = new JTextField(Login.atualUser.position, 30);
        positionTf.setFont(tfFont);
        positionTf.setPreferredSize(labelDim);
        positionTf.setEditable(false);

        positionPanel.add(positionLabel);
        positionPanel.add(positionTf);
        centerPanel.add(positionPanel);


        passwordPanel = new JPanel(flowCenter);
        passwordLabel = new JLabel("                 Senha: ");
        passwordLabel.setFont(labelFont);
        passwordTf = new JTextField(this.atualUserPassword(), 30);
        passwordTf.setFont(tfFont);
        passwordTf.setPreferredSize(labelDim);
        passwordTf.setEditable(false);

        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordTf);
        centerPanel.add(passwordPanel);

        changePass = new JButton();
        changePass.setBackground(Color.white);
        changePass.setIcon(new ImageIcon("./img/security.png"));
        changePass.setPreferredSize(new Dimension(50, 50));
        changePass.setFont(labelFont);
        changePass.setOpaque(false);
        changePass.addActionListener(this);
        passwordPanel.add(changePass);

        newPasswordPanel = new JPanel(flowCenter);
        newPasswordLabel = new JLabel("            Nova Senha: ");
        newPasswordLabel.setFont(labelFont);
        newPasswordTf = new JTextField(30);
        newPasswordTf.setFont(tfFont);
        newPasswordTf.setEditable(false);
        newPasswordTf.setPreferredSize(labelDim);

        newPasswordPanel.add(newPasswordLabel);
        newPasswordPanel.add(newPasswordTf);
        centerPanel.add(newPasswordPanel);

        submitnewPassword = new JButton();
        submitnewPassword.setBackground(Color.white);
        submitnewPassword.setIcon(new ImageIcon("./img/save.png"));
        submitnewPassword.setPreferredSize(new Dimension(50,50));
        submitnewPassword.setFont(labelFont);
        submitnewPassword.setOpaque(false);
        submitnewPassword.addActionListener(this);
        newPasswordPanel.add(submitnewPassword);

        return centerPanel;
    }

    private String atualUserPassword(){
        String pass, p = "";
        
        pass = Login.atualUser.password.substring(Login.atualUser.password.length()-2);

        for(int i = 0; i < Login.atualUser.password.length()-2; i++){
            p += "*";
        }

        return p+pass;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //change password
        if(e.getSource().equals(changePass)){
            passwordTf.setText("");
            passwordTf.setEditable(true);

            newPasswordTf.setText("");
            newPasswordTf.setEditable(true);
        }
        //save new password
        else if(e.getSource().equals(submitnewPassword)){

            if(passwordTf.getText().equals(Login.atualUser.password)){
                if(saveToDb()){
                    Login.atualUser.password = newPasswordTf.getText();
                    passwordTf.setText(atualUserPassword());
                    passwordTf.setEditable(false);

                    newPasswordTf.setText("");
                    newPasswordTf.setEditable(false);

                }
            } else {
                JOptionPane.showMessageDialog(Login.janela, "Senha antiga está errada", "FALHOU!!!", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private Boolean saveToDb(){
        Boolean r = false;
        String query = "UPDATE utilizadores SET senha = ? WHERE id_utilizador = ?";
        PreparedStatement ps = null;

        Integer changePassStatus = JOptionPane.showConfirmDialog(Login.janela, "Alterar senha?", "", JOptionPane.YES_NO_OPTION);

        if(changePassStatus.equals(0)){

            try {

                ps = Conexao.getConexao_ees().prepareStatement(query);
                ps.setString(1, newPasswordTf.getText());
                ps.setString(2, Login.atualUser.id);

                ps.executeUpdate();

                r = true;
    
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e){

            }
        }

        return r;
    }
}
