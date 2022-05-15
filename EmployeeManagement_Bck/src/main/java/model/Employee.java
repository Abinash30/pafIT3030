package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Employee {
	
	//A common method to connect to the DB
	private Connection connect(){
		
		 Connection con = null;
		 try{
			 Class.forName("com.mysql.jdbc.Driver");

			 //Provide the correct details: DBServer/DBName,username, password
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electroemp", "root", "");
			 		
		 }
		 catch (Exception e){
			 e.printStackTrace();
		 	}
		 	
		 	return con;
		 }
		
	//Method to insert notices to the system
	public String insertEmployee(String name, String address, String phone, String nic){
		
		 String output = "";
		 
		 try{
			 
			 Connection con = connect();
		
			 if (con == null){
				 return "Error while connecting to the database for inserting."; 
				}
		 
			 // create a prepared statement
			 String query = "insert into employee(`EID`,`Name`,`Address`,`Phone`,`NIC)" + " values (?, ?, ?, ?, ?)";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, name);
			 preparedStmt.setString(3, address);
			 preparedStmt.setString(4, phone);
			 preparedStmt.setString(5, nic);
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			  
			 String newEmployee = readEmployee() ;
			 output = "{\"status\":\"success\", \"data\": \"" + newEmployee + "\"}"; 
		 }
		 	catch (Exception e){	
		 		output = "{\"status\":\"error\", \"data\": \"Error while inserting the notice.\"}";
		 				 System.err.println(e.getMessage()); 
		 				 e.printStackTrace();
		 		}
		 	
		 return output;
	}
		

	//---------------method to view all the Employees------------------------
	public String readEmployee(){
		
		 String output = "";
		
		 try{
			 Connection con = connect(); 
			 if (con == null){
				 return "Error while connecting to the database for reading."; }
		
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Name</th><th>Address</th>" +
					 "<th>Phone</th>" +
					 "<th>NIC</th>" +
					 "<th>Update</th><th>Remove</th></tr>";

			 String query = "select * from employee";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 
			 // iterate through the rows in the result set
			 while (rs.next()){			 
				 String EID = Integer.toString(rs.getInt("EID"));
				 String name = rs.getString("Name");
				 String address = rs.getString("Address");
				 String phone = rs.getString("Phone");
				 String nic = rs.getString("NIC");
				 	
				 // Add into the html table
				 output += "<tr><td>" + name + "</td>";
				 output += "<td>" + address + "</td>";
				 output += "<td>" + phone + "</td>";
				 output += "<td>" + nic + "</td>";
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' "
						 + "class='btnUpdate btn btn-secondary' data-employeeid='" + EID + "'></td>"
						 + "<td><input name='btnRemove' type='button' value='Remove' "
						 + "class='btnRemove btn btn-danger' data-employeeid='" + EID + "'></td></tr>"; 
			 }
			 	
			 con.close();
			 // Complete the html table
			 output += "</table>";
		 }
		 	catch (Exception e){
		 		output = "Error while reading the notices.";
		 		System.err.println(e.getMessage());
		 	}
		 
		 	return output;
	}	
	//-------------------------method to update notice informations by id---------------------
	public String updateEmployee(String EID, String name, String address, String phone, String nic){
		
			 String output = "";
			
			 try{
				  Connection con = connect();
				  if (con == null){
					  return "Error while connecting to the database for updating."; }
			
				  // create a prepared statement
				  String query = "UPDATE employee SET Name=?,Address=?,Phone=?,NIC=? WHERE EID=?";
			 	  PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 	  // binding values
			 	  preparedStmt.setString(1, name);
			 	  preparedStmt.setString(2, address);
			 	  preparedStmt.setString(3, phone);
			 	  preparedStmt.setString(4,nic);
			 	  preparedStmt.setInt(5, Integer.parseInt(EID));
			 
			 	  // execute the statement
			 	  preparedStmt.execute();
			 	  con.close();
			 	  String newEmployee = readEmployee();
				 	 output = "{\"status\":\"success\", \"data\": \"" + newEmployee + "\"}";
				 }
				 	catch (Exception e){
				 		output = "{\"status\":\"error\", \"data\":\"Error while updating the employee.\"}";
				 				 System.err.println(e.getMessage());
				 	}
			 return output;
	}
			

	//--------------------method to delete by id ----------------------------------
	public String deleteEmployee(String EID){
		
			 String output = "";
			 
			 try{	 
				 Connection con = connect();
				  if (con == null){
					  return "Error while connecting to the database for deleting."; }
			 
				  // create a prepared statement
				  String query = "delete from Employee where EID=?";
				  PreparedStatement preparedStmt = con.prepareStatement(query);
			 
				  // binding values
				  preparedStmt.setInt(1, Integer.parseInt(EID));
			 
				  // execute the statement
				  preparedStmt.execute();
				  con.close();
				  
				  String newEmployee = readEmployee();
				  output = "{\"status\":\"success\", \"data\": \"" + newEmployee + "\"}"; 
			 }
			 	catch (Exception e){
			 		output = "{\"status\":\"error\", \"data\": \"Error while deleting the employee.\"}";
			 				 System.err.println(e.getMessage());
			 	  }
			 return output;
	  } 
	//-----------------------------------------------------------------------------------
}
