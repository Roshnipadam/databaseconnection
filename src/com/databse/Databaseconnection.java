package com.databse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Databaseconnection {

	

		private Statement statement = null;
		private PreparedStatement preparedStatement = null;

		public Connection getConnection() {
			Connection connect = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				// Setup the connection with the DB
				connect = DriverManager.getConnection("jdbc:mysql://localhost:8088/islabs?user=root&password=");
				System.out.println(connect.toString());

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return connect;
		}

		private void writeResultSet(ResultSet resultSet) throws SQLException {
	        // ResultSet is initially before the first data set
	        while (resultSet.next()) {
	            // It is possible to get the columns via name
	            // also possible to get the columns via the column number
	            // which starts at 1
	            // e.g. resultSet.getSTring(2);
	            int id = resultSet.getInt("id");
	            String name = resultSet.getString("name");
	            String place = resultSet.getString("place");
	            
	            System.out.println("Id: " + id);
	            System.out.println("Name: " + name);
	            System.out.println("Department: " + place);
	            
	            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	        }
		}

		public void readEmployees(Connection connect) {
			ResultSet resultSet = null;
			try {
				statement = connect.createStatement();
				// Result set get the result of the SQL query
				resultSet = statement.executeQuery("select * from islabs.student");
				writeResultSet(resultSet);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public static void main(String[] args) {
			Databaseconnection dc = new Databaseconnection();
			Connection dbConnection = dc.getConnection();
			dc.readEmployees(dbConnection);
			try {
				dbConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
	
}
