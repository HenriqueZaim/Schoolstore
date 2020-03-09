package br.com.fatec.les.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoFactory {
	
	private static Connection conexao;
	
	public static Connection getConnection() {
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost/les-project";
		String username = "root";
		String password = "CG1d)*J13m@*";
			
		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}catch(ClassNotFoundException c) {
			System.err.println(c.getMessage());
		}
		return conexao;
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
