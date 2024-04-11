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
import gui.util.Style;

public class Pauta  extends JPanel implements ActionListener{
    
    String __query = "";
    String __tabela = "";
    String __id = "";

    JPanel footerPanel = new JPanel(Style.flow_center);
    JPanel header, title_panel;

    JLabel title;

    JButton pagar = new JButton("Salvar Alterações");
    
    Object[][] table_data;
    JTable table;
    JScrollPane table_scroll;

    public Pauta(String query, String tabela){
        this.__query = query;
        this.__tabela = tabela;
    }

    public Pauta(String query, String tabela, String id){
        this.__query = query;
        this.__tabela = tabela;
        this.__id = id;
    }

    public void start_table(){
        this.setBackground(Style.bg);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        this.setHeader();
        this.select_from_database(__query, __tabela);
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

        title = new JLabel("Pauta".toUpperCase());
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
        model.addColumn("Teste 1");
        model.addColumn("Teste 2");
        model.addColumn("Teste 3");
        model.addColumn("Teste Oral 1");
        model.addColumn("Teste Oral 2");
        model.addColumn("Teste Oral 3");
        model.addColumn("Teste Oral 4");
        model.addColumn("Teste Oral 5");
        model.addColumn("Media");

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

    private void select_from_database(String query, String tabela){

        //String query = "SELECT * FROM avaliacao_nivel1";
		String count = "SELECT COUNT(id_estudante) FROM " + tabela;
			PreparedStatement ps = null;
			PreparedStatement ps_count = null;

			try{
				ps = Conexao.getConexao_ees().prepareStatement(query);

                if(!this.__id.equals("")){
                    ps.setString(1, __id);
                }

				ResultSet resultSet = ps.executeQuery();

				ps_count = Conexao.getConexao_ees().prepareStatement(count);

				ResultSet resultSet_count = ps_count.executeQuery(count);
				System.out.println(query);
				try {
					//get the numeber of row
					resultSet_count.next();
					int table_tuples = resultSet_count.getInt(1);
					int table_attrs = 10;

					table_data = new Object[table_tuples][table_attrs];
					
					int i = 0;
					while(resultSet.next() && i < table_tuples){

                        for(int l = 0; l < 10; l++){
                            if(l == 0){
                                table_data[i][l] = resultSet.getString(l+1);
                            }else{
                                table_data[i][l] = resultSet.getDouble(l+1);
                            }

                        }

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

    }
}
