package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    public static Connection conexao;
    
    public static void conectar() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver"); 
        Conexao.conexao = DriverManager.getConnection("jdbc:mysql://localhost/img","root","97130084");
        System.out.println("Conectado! ");
    }
    
}
