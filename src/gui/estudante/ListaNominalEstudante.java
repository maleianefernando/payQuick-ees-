package gui.estudante;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import gui.util.Style;

public class ListaNominalEstudante extends JPanel{
	private GridLayout grid21 = new GridLayout(2, 1);
	private JTable table;
	private JScrollPane table_scroll;
	
	String[] table_columns = new String[] {"ID", "Nome Completo", "Disciplinas", "Estado da mensalidade"};
	
	public ListaNominalEstudante(){
		
		this.setVisible(true);
		this.setLayout(grid21);
		this.setBackground(Style.bg);
		
		
		create_table();
		this.add(table_scroll);
	}
	
	public void create_table(){
		//table model
		DefaultTableModel model = new DefaultTableModel();
		
		//add columns
		add_columns(model, table_columns);
		table = new JTable(model);
	
		//setting the jscrollpane to add the table
		table_scroll = new JScrollPane(table);
	}
	
	private void add_columns(DefaultTableModel model, String[] columns){
		for(int i = 0; i < columns.length; i++){
			model.addColumn(columns[i]);
		}
	}
}
