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
//import java.awt.GridLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import connection.Conexao;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import gui.Login;
import gui.util.Style;

public class ListaNominalEstudante extends JPanel  implements ActionListener, MouseListener {

	String __query__ = "";

	//private GridLayout grid21 = new GridLayout(2, 3);
	private JTable table;
	private JScrollPane table_scroll;
    JTableHeader table_header;

	private JButton save;
	
	private JPanel header, title_panel;
	private JLabel title;

	String[] table_columns = new String[] {"ID", "Nome Completo", "Bairro", "Email", "Data de nascimento", "Sexo", "Celular", "Nivel", "Horario"};

	Object[][] table_data;
	
	public static Integer fill_table_status = -1;

	public ListaNominalEstudante(String query){
		this.__query__ = query;
	}
	
	public void start_list(){
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setBackground(Style.bg);

		this.select_from_database(__query__);
		
		this.create_table();
		this.setHeader();
		this.add(table_scroll, BorderLayout.CENTER);

		save = new JButton("Salvar alterações");
		save.setFont(Style.btn_font);
		save.setPreferredSize(new Dimension(300, 50));
		save.setBackground(Style.btn_bg);
		save.setForeground(Style.tf_bg);
		save.setFocusable(false);
		save.addActionListener(this);save.setMnemonic('S');

		JPanel footer = new JPanel(Style.flow_center);
		footer.setBackground(Style.bg);
		footer.setPreferredSize(new Dimension(0, 100));
		footer.add(save);

		this.add(footer, BorderLayout.SOUTH);
	}

	private void setHeader(){
        header = new JPanel(new GridLayout(3, 3));
        header.setBackground(Style.bg);

        title = new JLabel("Lista Nominal".toUpperCase());
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
		//System.out.println("table created" + "\n" + table_data);
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
	}


	private void select_from_database(String query){
		// String query = "SELECT * FROM estudante";
		query = __query__;
		String cout = "SELECT COUNT(id_estudante) FROM estudante";
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
					int table_attrs = 9;

					table_data = new Object[table_tuples][table_attrs];
					
					int i = 0;
					while(resultSet.next() && i < table_tuples){

						table_data[i][0] = resultSet.getString("id_estudante");	//get id
						//System.out.println(i + ": "+ table_data[i][0]);
						table_data[i][1] = resultSet.getString("nome_completo"); // get name
						table_data[i][2] = resultSet.getString("bairro");	//get age
						table_data[i][3] = resultSet.getString("email");	//get bairro
						table_data[i][4] = resultSet.getDate("data_nascimento");	//get identification
						table_data[i][5] = resultSet.getString("sexo");	//get ocupacao
						table_data[i][6] = resultSet.getString("numero_celular");	//get subjects
						table_data[i][7] = resultSet.getString("nivel");
						table_data[i][8] = resultSet.getString("horario");
						i++;
						
					}
					//System.out.println("select query");
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

	@Override
	public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(save)){
            TableModel model = table.getModel();
            String query_update = "";
            PreparedStatement ps_update = null;
			Integer row = 0;

            query_update = "UPDATE estudante SET nome_completo = ?, bairro = ?, email = ?, data_nascimento = ?, sexo = ?, numero_celular = ?, nivel = ?, horario = ? WHERE id_estudante = ?";

            try{
                ps_update = Conexao.getConexao_ees().prepareStatement(query_update);
                System.out.println(model.getRowCount());

                for(int i = 0; i < model.getRowCount(); i++){
					row = i;

                    ps_update.setString(1, model.getValueAt(i, 1).toString());

                    ps_update.setString(2, model.getValueAt(i, 2).toString());

                    ps_update.setString(3, model.getValueAt(i, 3).toString());

					ps_update.setString(4, model.getValueAt(i, 4).toString());

					ps_update.setString(5, model.getValueAt(i, 5).toString());

					ps_update.setString(6, model.getValueAt(i, 6).toString());

					ps_update.setString(7, model.getValueAt(i, 7).toString());

					ps_update.setTime(8, Time.valueOf(LocalTime.parse(model.getValueAt(i, 8).toString())));

					ps_update.setString(9, model.getValueAt(i, 0).toString());

                    if(ps_update.executeUpdate() == 1){
                        System.out.println("lista atualizada");
                    }
                }
                
				JOptionPane.showMessageDialog(Login.janela, "Lista de estudantes actualizada com sucesso", "SUCESSO!!!", JOptionPane.INFORMATION_MESSAGE);
            }catch(com.mysql.cj.jdbc.exceptions.MysqlDataTruncation e2){
				String err_str = e2.toString();
				System.out.println(err_str);
				String err = "";
				
				try{
					err = err_str.substring(err_str.indexOf('`'), err_str.lastIndexOf('a')-1);
					
					System.out.println(err_str.substring(err_str.indexOf('`'), err_str.lastIndexOf('a')-1));	//115, 67

				}catch(Exception e3){
					// System.out.println(e3);
					err = err_str;
				}

				if(err.equals("`emmanuel_english_school`.`estudante`.`data_nascimento`")){
					JOptionPane.showMessageDialog(Login.janela, "Verifique a data de nascimento no estudante " + model.getValueAt(row, 0), "ERRO!", JOptionPane.ERROR_MESSAGE);
				}

				else if(err.equals("com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column 'sexo' at row 1")){
					JOptionPane.showMessageDialog(Login.janela, "Verifique o sexo no estudante " + model.getValueAt(row, 0), "ERRO!", JOptionPane.ERROR_MESSAGE);
				}

				else if(err.equals("`emmanuel_english_school`.`estudante`.`horario`")){
					JOptionPane.showMessageDialog(Login.janela, "Certifique-se de colocar o formato certo do horário no estudante " + model.getValueAt(row, 0), "ERRO!", JOptionPane.ERROR_MESSAGE);
				}

			}catch(java.time.format.DateTimeParseException e2){

				JOptionPane.showMessageDialog(Login.janela, "Certifique-se de colocar o formato certo do horário no estudante " + model.getValueAt(row, 0), "ERRO!", JOptionPane.ERROR_MESSAGE);

			}catch(Exception e2){
                JOptionPane.showMessageDialog(Login.janela, e2, "ERRO!", JOptionPane.OK_OPTION);
                e2.printStackTrace();
            }
			System.out.println("fim----------");
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
	public void mouseReleased(MouseEvent e) {

	}
}
