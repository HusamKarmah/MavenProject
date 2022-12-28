package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DButilsMain {
private static String dbHostName = "jdbc:mysql://stack-overflow.cfse9bqqndon.us-east-1.rds.amazonaws.com:3306/crater"; 
	
	private static String username = "primetechadmin"; 
	private static String password = "password1234"; 
	
	public static void main(String[] args) {
		
		String query = "select id, name, email, phone from customers where name='Adam King'"; 
		
	try {
		Connection connect = DriverManager.getConnection(dbHostName,username, password  );
		System.out.println("Connection is successful");
		Statement statement = connect.createStatement(); 
		ResultSet resultset = statement.executeQuery(query); 
		ResultSetMetaData rsmd = resultset.getMetaData();
		resultset.next(); 
		
		System.out.println("1st index id is:" + resultset.getByte(1));
		System.out.println("Columns count is: " + rsmd.getColumnCount());
		
		List<String> adamKing = new ArrayList<>(); 
		for (int i=1; i<=rsmd.getColumnCount(); i++){
			
			adamKing.add(resultset.getString(i)); 	
		}
		for (String str:adamKing) {
			System.out.println(str);
		}

		connect.close();
		
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	} 
		
	
	String query1 = "update customers set name  = 'Polat' where id ='1'"; 
	
	try {
		Connection connect = DriverManager.getConnection(dbHostName,username, password);
		Statement statement = connect.createStatement(); 
		statement.executeUpdate(query1);
		connect.close();
		
	} catch (SQLException e) {
		System.out.println("DB connection Not established.");
		e.printStackTrace();
	}
	
	String query2 = "select code, name, phonecode from countries where id ='104'"; 
	try {
		Connection connect = DriverManager.getConnection(dbHostName,username, password );
		Statement statement = connect.createStatement();
		ResultSet resultset = statement.executeQuery(query2); 
		ResultSetMetaData rsmd = resultset.getMetaData();
		resultset.next(); 
		
		List<String> mylist = new ArrayList<>(); 

		
		for (int i =1; i<= rsmd.getColumnCount(); i++) {
			mylist.add(resultset.getString(i)); 	

		}
			System.out.println("Country Code is: " + resultset.getString(1));
			System.out.println("Country Name is: " + resultset.getString(2));
			System.out.println("Country Phone Code is: " + resultset.getString(3)); 

		connect.close();

	} catch (Exception e) {
		// TODO: handle exception
	}
		
		
	}

}
