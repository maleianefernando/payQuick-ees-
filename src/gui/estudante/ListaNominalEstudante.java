package gui.estudante;

import java.awt.BorderLayout;
import java.awt.Dimension;
//import java.awt.GridLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import connection.Conexao;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import gui.util.Style;

public class ListaNominalEstudante extends JPanel{
	//private GridLayout grid21 = new GridLayout(2, 3);
	private JTable table;
	private JScrollPane table_scroll;
	
	String[] table_columns = new String[] {"ID", "Nome Completo", "Idade", "Residencia", "Disciplinas", "Obs"};

	Object[][] table_data;
	
	public static Integer fill_table_status = -1;

	public ListaNominalEstudante(){
		

	}
	
	public void start_list(){
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setBackground(Style.bg);

		this.select_from_database();
		
		this.create_table();
		this.add(new JLabel("            "), BorderLayout.NORTH);
		this.add(new JLabel("            "), BorderLayout.EAST);
		this.add(table_scroll, BorderLayout.CENTER);
		this.add(new JLabel("            "), BorderLayout.WEST);
	}

	public void create_table(){
		//table model
		DefaultTableModel model = new DefaultTableModel();
		
		//add rows and columns
		add_columns(model, table_columns);
		add_row(model, table_data);
		table = new JTable(model);
		table.setBackground(Style.bg);
		
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
		String sql = "SELECT * FROM estudantes";
		String cout = "SELECT COUNT(id) FROM estudantes";
			PreparedStatement ps = null;
			PreparedStatement ps_count = null;

			try{
				ps = Conexao.getConexao().prepareStatement(sql);
				ResultSet resultSet = ps.executeQuery(sql);

				ps_count = Conexao.getConexao().prepareStatement(cout);
				ResultSet resultSet_count = ps_count.executeQuery(cout);
				
				try {
					//get the numeber of row
					resultSet_count.next();
					int table_tuples = resultSet_count.getInt(1);
					int table_attrs = 5;

					table_data = new Object[table_tuples][table_attrs];
					
					int i = 0;
					while(resultSet.next() && i < table_tuples){

						table_data[i][0] = resultSet.getString("id");	//get id
						//System.out.println(i + ": "+ table_data[i][0]);
						table_data[i][1] = resultSet.getString("nome"); // get name
						table_data[i][2] = resultSet.getInt("idade");	//get age
						table_data[i][3] = resultSet.getString("bairro");	//get bairro
						//table_data[i][4] = resultSet.getInt("identificacao_nr");	//get identification
						//table_data[i][4] = resultSet.getInt("ocupacao");	//get ocupacao
						table_data[i][4] = resultSet.getString("disciplinas");	//get subjects
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
