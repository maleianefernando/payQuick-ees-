package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static String url = "jdbc:mysql://localhost:3306/turma_dos_revoltados";
    public static String user = "root";
    public static String password = "";

    public static Connection conn;
    public static Connection ees_conn;

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


    public static Connection getConexao_ees(){

        try {
            if(ees_conn == null){
                ees_conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/emmanuel_english_school", user, password);

                System.out.println("emmanuel engish school database connected");

                return ees_conn;
            }
            else{
                return ees_conn;
            }
        } catch (SQLException e) {
            System.out.println("cannot connect to the emmanuel ensglish school database");
            System.out.println(e);

            
            return null;
        }
    }
}
