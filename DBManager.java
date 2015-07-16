package exTwitter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBManager {
	private Connection con;
	private Statement smt;
	private ResultSet rs;
	
	public void getConnection(){
		String url      = "jdbc:mysql://localhost/excite";
		String user     = "root";
		String password = "nosm1648";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,user, password);
		}catch(Exception e){
			System.err.println("conect_error");
			throw new IllegalStateException(e);
		}
	}
	
	public ResultSet getResultSet(String qry){
		try {
		smt  = con.createStatement();
		rs = smt.executeQuery(qry);
		
		}catch (Exception e){
			System.err.println("Select_Error");
		}
		return rs;
	}
	
	public int exeUpdate(String qry){
		int update_count = 0;
		try {
			smt = con.createStatement();
			update_count = smt.executeUpdate(qry);
		}catch (Exception e){
			System.err.println("Update_Error");
		}
		return update_count;
		
	}
	
	public void closeConnection(){
		try {
			rs.close();
			smt.close();
			con.close();
		}catch (Exception e){
			System.err.println("Close_Error");
		}
	}
	
}