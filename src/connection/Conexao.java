package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static String url = "jdbc:mysql://localhost:3306/turma_dos_revoltados";
    public static String user = "root";
    public static String password = "";

    public static Connection conn;

    public Conexao(){

        // url = "jdbc:mysql://localhost:3306/turma_dos_revoltados";
        // user = "root";
        // password = "";
    }


    public static Connection getConexao(){

        try{
            if (conn == null) {
                conn = DriverManager.getConnection(url, user, password);

                System.out.println("connected!!!");

                return conn;
            }
            else {
                return conn;
            }
        } catch(SQLException e) {
            System.out.println("connection failled!!!");
            e.printStackTrace();
            return null;
        }
    }
}
