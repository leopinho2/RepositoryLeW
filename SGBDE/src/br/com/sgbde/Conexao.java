package br.com.sgbde;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {

	public static Connection connection;
	
	public static Statement conectar(){
		
		Statement s = null;
		
		try {	
	        String driverName = "com.mysql.jdbc.Driver";
	        Class.forName(driverName);
	
	        String serverName = "localhost";
	        String mydatabase ="sgbde";
	        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
	        String username = "root";      
	        String password = "root"; 
	
	        connection = DriverManager.getConnection(url, username, password);
	        
	        try {
				s = connection.createStatement();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

        } catch (Exception e) {
        	System.out.println("Deu Merda!!!!");
        }
		
		return s;  
	}
	
	public static void desconectar(Connection c){
		if(c != null){
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
