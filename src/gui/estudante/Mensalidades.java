package gui.estudante;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connection.Conexao;
import gui.Login;
import gui.util.Style;

public class Mensalidades  extends JPanel implements ActionListener{
    JPanel footerPanel = new JPanel(Style.flow_center);
    JPanel header, title_panel;

    JLabel title;

    JButton pagar = new JButton("Realizar Pagamento");
    
    Object[][] table_data;
    JTable table;
    JScrollPane table_scroll;

    public Mensalidades(){

    }

    public void start_table(){
        this.setBackground(Style.bg);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        this.setHeader();
        this.select_from_database();
        this.create_table();
        this.setFooter();
    }

    private void setFooter(){
        pagar.setBackground(Style.btn_bg);
        pagar.setFont(Style.btn_font);
        pagar.setForeground(Style.tf_bg);
        pagar.setPreferredSize(new Dimension(300, 50));
        pagar.setFocusable(false);
        pagar.addActionListener(this);

        footerPanel.setPreferredSize(new Dimension(0, 100));
        footerPanel.setBackground(Style.bg);
        footerPanel.add(pagar);
        this.add(footerPanel, BorderLayout.SOUTH);
    }

    private void setHeader(){
        header = new JPanel(new GridLayout(3, 3));
        header.setBackground(Style.bg);

        title = new JLabel("Mensalidades".toUpperCase());
        title.setFont(new Font("Consolas", Font.BOLD, 30));
        title.setForeground(new Color(0x039e18));

        title_panel = new JPanel(Style.flow_center);
        title_panel.setBackground(Style.bg);
        title_panel.add(title);

        header.add(new JLabel("")); //1x1
        header.add(new JLabel("")); //1x2
        header.add(new JLabel("")); //1x3
        header.add(new JLabel("")); //2x1
        header.add(title_panel); //2x2
        header.add(new JLabel("")); //2x3
        header.add(new JLabel("")); //3x1
        header.add(new JLabel("")); //3x2
        header.add(new JLabel("")); //3x3

        this.add(new JLabel("            "), BorderLayout.WEST);
		this.add(new JLabel("            "), BorderLayout.EAST);

        this.add(header, BorderLayout.NORTH);
    }

    private void create_table(){
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Id do estudante");
        model.addColumn("Mês");
        model.addColumn("Estado da mensalidade");
        model.addColumn("Dívida total");

        for(int i = 0; i < table_data.length; i++){
            model.addRow(table_data[i]);
        }

        table = new JTable(model);
        table.setBackground(Style.table_bg);

        //update
        model.fireTableDataChanged();
		model.fireTableStructureChanged();

		table.revalidate();
		table.repaint();

        table_scroll = new JScrollPane(table);
        table_scroll.setPreferredSize(new Dimension(500, 500));
		table_scroll.revalidate();
		table_scroll.repaint();
        //add
        this.add(table_scroll, BorderLayout.CENTER);
    }

    private void select_from_database(){

        String query = "SELECT id_estudante, mes, status, divida FROM mensalidade";
		String cout = "SELECT COUNT(id_mensalidade) FROM mensalidade";
			PreparedStatement ps = null;
			PreparedStatement ps_count = null;

			try{
				ps = Conexao.getConexao_ees().prepareStatement(query);
				ResultSet resultSet = ps.executeQuery(query);

				ps_count = Conexao.getConexao_ees().prepareStatement(cout);
				ResultSet resultSet_count = ps_count.executeQuery(cout);
				
				try {
					//get the numeber of row
					resultSet_count.next();
					int table_tuples = resultSet_count.getInt(1);
					int table_attrs = 4;

					table_data = new Object[table_tuples][table_attrs];
					
					int i = 0;
					while(resultSet.next() && i < table_tuples){

						table_data[i][0] = resultSet.getString("id_estudante");
						table_data[i][1] = resultSet.getInt("mes");
						table_data[i][2] = resultSet.getString("status");
						table_data[i][3] = resultSet.getDouble("divida");
						i++;
						
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO!", JOptionPane.INFORMATION_MESSAGE);
				}
				ps.close();
			}
			catch(SQLException ex){
				ex.printStackTrace();
			}

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(pagar)){
            PreparedStatement ps = null;

            String id_est = "";
            ResultSet divida = null;
            ResultSet id_estudante = null;

            id_est = JOptionPane.showInputDialog(Login.janela, "ID do estudante?", "Pagar mensalidade", JOptionPane.INFORMATION_MESSAGE);

            try {
                ps = Conexao.getConexao_ees().prepareStatement("SELECT id_estudante FROM mensalidade WHERE id_estudante = ?");

                ps.setString(1, id_est);
                id_estudante = ps.executeQuery();

                id_estudante.next();
                try{
                    if(id_estudante.getString(1).equals(id_est)){

                        ps = Conexao.getConexao_ees().prepareStatement("SELECT divida FROM mensalidade WHERE id_estudante = ?");

                        ps.setString(1, id_est);

                        divida = ps.executeQuery();
                        divida.next();

                        Double valor = 0.0;
                        int r = 0;
                        String title_joption = "Pagar mensalidade";
                        do{
                            if(r == 1){
                                title_joption = "ERRO! Tentar novamente";
                            }
                            if(divida.getDouble(1) == 0.0){
                                JOptionPane.showMessageDialog(Login.janela, "A situação financeira do estudante está regularizada", "Mensalidade", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else{
                                try{
                                    valor = Double.parseDouble(JOptionPane.showInputDialog(Login.janela, "Valor da mensalidade?", title_joption, JOptionPane.YES_NO_OPTION));
                                r = 1;
                                } catch(NullPointerException nullPointer){
                                    System.out.println(nullPointer);
                                }
                            }
                        }while(divida.getDouble(1) != valor);

                        
                        ps = Conexao.getConexao_ees().prepareStatement("UPDATE mensalidade SET divida = ?, status = 'pago' WHERE  id_estudante = ?");

                        ps.setDouble(1, divida.getDouble(1) - valor);
                        ps.setString(2, id_est);

                        if(ps.executeUpdate() == 1){
                            JOptionPane.showConfirmDialog(Login.janela, "Sucesso!!!", "Mensalidade suprida com sucesso", JOptionPane.YES_OPTION);
                        }
                        else {
                            JOptionPane.showConfirmDialog(Login.janela, "ERRO!", "Não foi possivel atualizar a base de dados", JOptionPane.OK_OPTION);
                        }
                    }else {
                        id_est = JOptionPane.showInputDialog(Login.janela, "ID do estudante?", "ERRO! Tentar novamente", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException e2){
                    id_est = JOptionPane.showInputDialog(Login.janela, "ID do estudante?", "ERRO! Tentar novamente", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(Login.janela, "" + e1, "ERROR!", JOptionPane.ERROR_MESSAGE);

                e1.printStackTrace();
            }
        }
    }
}
