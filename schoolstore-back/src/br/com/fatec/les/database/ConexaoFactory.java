package br.com.fatec.les.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoFactory {
	
	public static Connection getConnection() {
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost/lesProject";
		String username = "root";
		String password = "CG1d)*J13m@*";
			
		try {
			Class.forName(driverName);
			java.sql.Connection conexao = DriverManager.getConnection(url, username, password);
			return conexao;
		}catch (SQLException | ClassNotFoundException e) {
            return null;
        } 
	}
	
	public static boolean closeConnection(Connection con){
        if(con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                System.err.println("Erro: closeConnection(Connection con)");
                return false;
            }
        }
        return true;
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt){
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.err.println("Erro: closeConnection(Connection con, PreparedStatement stmt)");
            }
        }
        closeConnection(con);
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException ex) {
                System.err.println("Erro: closeConnection(Connection con, PreparedStatement stmt, ResultSet rs)");
            }
        }
        closeConnection(con, stmt);
    }

}
