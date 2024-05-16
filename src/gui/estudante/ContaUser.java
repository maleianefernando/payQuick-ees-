package gui.estudante;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.util.Style;

public class ContaUser extends JPanel {
    
    JPanel headerPanel, footerPanel;
    JLabel headerLabel;

    public ContaUser(){
        this.setPanel();
        this.add(this.setHeader(), BorderLayout.NORTH);
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
}
