package gui.funcionario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
//import java.awt.GridLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import connection.Conexao;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import gui.util.Style;

public class ListaNominalFuncionario extends JPanel{
	//private GridLayout grid21 = new GridLayout(2, 3);
	private JTable table;
	private JScrollPane table_scroll;
	private JTableHeader table_header;

	private JPanel header, title_panel;
	private JLabel title;
	
	String[] table_columns = new String[] {"ID Funcionario", "ID Utilizador", "Nome", "Funcao", "Obs"};

	Object[][] table_data;
	
	public static Integer fill_table_status = -1;

	public ListaNominalFuncionario(){
		

	}
	
	public void start_list(){
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setBackground(Style.bg);
		
		this.setHeader();
		
		this.select_from_database();
		this.create_table();
		// this.add(new JLabel("            "), BorderLayout.NORTH);
		this.add(new JLabel("            "), BorderLayout.EAST);
		this.add(table_scroll, BorderLayout.CENTER);
		this.add(new JLabel("            "), BorderLayout.WEST);
	}


	private void setHeader(){
        header = new JPanel(new GridLayout(3, 3));
        header.setBackground(Style.bg);

        title = new JLabel("Funcionarios".toUpperCase());
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

        // this.add(new JLabel("            "), BorderLayout.WEST);
		// this.add(new JLabel("            "), BorderLayout.EAST);

        this.add(header, BorderLayout.NORTH);
		this.revalidate();
		this.repaint();
    }


	public void create_table(){
		//table model
		DefaultTableModel model = new DefaultTableModel();
		
		//add rows and columns
		add_columns(model, table_columns);
		add_row(model, table_data);
		table = new JTable(model);
		table.setBackground(Style.table_bg);
		table.setFont(Style.table_font);
		table.setRowHeight(Style.table_row_height);
		table.setIntercellSpacing(Style.cell_spacing);
		table.setBorder(Style.table_border);
		

		table_header = table.getTableHeader();
		table_header.setFont(Style.table_head_font);
		table_header.setBackground(Style.table_head_bg);

		//update table
		model.fireTableDataChanged();
		model.fireTableStructureChanged();
		//

		table.revalidate();
		table.repaint();

		//setting the jscrollpane to add the table
		table_scroll = new JScrollPane(table);
		table_scroll.setSize(new Dimension(500, 500));
		table_scroll.revalidate();
		table_scroll.repaint();
		//table_scroll.setBackground(Color.red);
		System.out.println("table created" + "\n" + table_data);
	}
	
	private void add_columns(DefaultTableModel model, String[] columns){
		for(int i = 0; i < columns.length; i++){
			model.addColumn(columns[i]);
		}
	}

	private void add_row(DefaultTableModel model, Object[][] rows){
		for(int i = 0; i < rows.length; i++){
			model.addRow(rows[i]);
		}
		System.out.println("adding row");
	}


	private void select_from_database(){
		String sql = "SELECT id_funcionarios, id_utilizador, nome, funcao FROM funcionarios";
		String cout = "SELECT COUNT(id_funcionarios) FROM funcionarios";
			PreparedStatement ps = null;
			PreparedStatement ps_count = null;

			try{
				ps = Conexao.getConexao_ees().prepareStatement(sql);
				ResultSet resultSet = ps.executeQuery(sql);

				ps_count = Conexao.getConexao_ees().prepareStatement(cout);
				ResultSet resultSet_count = ps_count.executeQuery(cout);
				
				try {
					//get the numeber of row
					resultSet_count.next();
					int table_tuples = resultSet_count.getInt(1);
					int table_attrs = 5;

					table_data = new Object[table_tuples][table_attrs];
					
					int i = 0;
					while(resultSet.next() && i < table_tuples){

						table_data[i][0] = resultSet.getString("id_funcionarios");	//get officer id
						//System.out.println(i + ": "+ table_data[i][0]);
						table_data[i][1] = resultSet.getString("id_utilizador"); // get name
						table_data[i][2] = resultSet.getString("nome");	//get name
						table_data[i][3] = resultSet.getString("funcao");	//get position
						//table_data[i][4] = resultSet.getInt("identificacao_nr");	//get identification
						//table_data[i][4] = resultSet.getInt("ocupacao");	//get ocupacao
						// table_data[i][4] = resultSet.getString("disciplinas");	//get subjects
						i++;
						
					}
					System.out.println("select query");
					//JOptionPane.showMessageDialog(null, "Visualizando!", "Sucesso!!!", JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO!", JOptionPane.INFORMATION_MESSAGE);
				}
				ps.close();
			}
			catch(SQLException ex){
				ex.printStackTrace();
			}

		}
}
