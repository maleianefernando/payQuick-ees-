package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static String url;
    public static String user;
    public static String password;

    private static Connection conn;

    public Conexao(){

        url = "jdbc:mysql://localhost:3306/turma_dos_revoltados";
        user = "root";
        password = "";
    }


    public static Connection getConexao(){
        Conexao dbParams = new Conexao();

        System.out.println("connection");

        try{
            if (conn == null) {
                conn = DriverManager.getConnection(dbParams.url, dbParams.user, dbParams.password);

                return conn;
            }
            else {
                return conn;
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
