package gui.estudante;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import connection.Conexao;
import gui.Login;
import gui.util.Style;

public class Pauta  extends JPanel implements ActionListener, MouseListener{
    
    String __query = "";
    String __tabela = "";
    String __id = "";

    JPanel footerPanel = new JPanel(Style.flow_center);
    JPanel header, title_panel;

    JLabel title;

    JButton save = new JButton("Salvar Alterações");
    
    Object[][] table_data;
    JTable table;
    JScrollPane table_scroll;
    JTableHeader table_header;

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
        save.setBackground(Style.btn_bg);
        save.setFont(Style.btn_font);
        save.setForeground(Style.tf_bg);
        save.setPreferredSize(new Dimension(300, 50));
        save.setFocusable(false);
        save.addActionListener(this);
        save.addMouseListener(this);
        save.setMnemonic('S');

        footerPanel.setPreferredSize(new Dimension(0, 100));
        footerPanel.setBackground(Style.bg);
        footerPanel.add(save);
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
		table.setFont(Style.table_font);
		table.setRowHeight(Style.table_row_height);
		table.setIntercellSpacing(Style.cell_spacing);
		table.setBorder(Style.table_border);

        table_header = table.getTableHeader();
		table_header.setFont(Style.table_head_font);
        table_header.setBackground(Style.table_head_bg);


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

                //Setting the id_estudante to look for on the DB
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
					while(resultSet.next()){    //resultSet.next() && i < table_tuples

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
        if(e.getSource().equals(save)){
            TableModel model = table.getModel();
            String query = "";
            // String __nivel_avaliacao = "";
            PreparedStatement ps = null;
            
            Double media = 0.0;
            // String id_estudante = "";

            query = "UPDATE " + __tabela + " SET teste_escrito1 = ?, teste_escrito2 = ?, teste_escrito3 = ?, teste_oral1 = ?, teste_oral2 = ?, teste_oral3 = ?, teste_oral4 = ?, teste_oral5 = ?, media = ? WHERE id_estudante = ?";

            // Object nota = new Object();

            try{
                ps = Conexao.getConexao_ees().prepareStatement(query);
                System.out.println(model.getColumnCount());
                for(int i = 0; i < model.getRowCount(); i++){

                    ps.setDouble(1, Double.valueOf(model.getValueAt(i, 1).toString()));

                    ps.setDouble(2, Double.valueOf(model.getValueAt(i, 2).toString()));

                    ps.setDouble(3, Double.valueOf(model.getValueAt(i, 3).toString()));

                    ps.setDouble(4, Double.valueOf(model.getValueAt(i, 4).toString()));

                    ps.setDouble(5, Double.valueOf(model.getValueAt(i, 5).toString()));

                    ps.setDouble(6, Double.valueOf(model.getValueAt(i, 6).toString()));

                    ps.setDouble(7, Double.valueOf(model.getValueAt(i, 7).toString()));

                    ps.setDouble(8, Double.valueOf(model.getValueAt(i, 8).toString()));

                    for(int j = 1; j < 9; j++){
                        media += Double.valueOf(model.getValueAt(i, j).toString());
                    }

                    media = media/8;
                    String media_format = String.format("%.2f", media);
                    media = Double.valueOf(media_format);

                    ps.setDouble(9, media);

                    ps.setString(10, model.getValueAt(i, 0).toString());

                    if(ps.executeUpdate() == 1){
                        System.out.println("pauta atualizada");
                    }
                    // for(int j = 1; j < model.getColumnCount(); j++){
                        
                    //     if(k < 8){
                    //         nota = model.getValueAt(i, j);
                    //         System.out.println("Object: "+ nota);


                    //         ps.setDouble(k, Double.valueOf(nota.toString()));
                    //         media += (Double)model.getValueAt(i, j);

                    //     }else if(k == 8){
                    //         ps.setString(9, (String)model.getValueAt(i, 0));
                    //         media = media/8;
                    //         ps.setDouble(k, media);
                    //     }
                        

                    //     System.out.println("K: "+k+"- J "+j+ " "+model.getValueAt(i, j));

                    //     k++;

                    // }
                }
                
            }catch(Exception e2){
                JOptionPane.showMessageDialog(Login.janela, e2, "ERRO!", JOptionPane.OK_OPTION);
                e2.printStackTrace();
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
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {

    }
}
