package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

import model.Employee;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document; 

@Path("/Employee")
public class EmployeeService {
	
	
	//notice object 
	Employee employeeObj = new Employee(); 
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)   //output as html 
	public String readEmployee(){
	 return employeeObj.readEmployee();
	 
  }

//******************************************************************************************************//	
		
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)   //@Consumes specify the input type; here as form data
	@Produces(MediaType.TEXT_PLAIN)					   //@Produces status message; here output as plain text	
	public String insertEmployee(@FormParam("name") String Name,
			@FormParam("address") String Address,
			@FormParam("phone") String Phone,
			@FormParam("nic") String NIC)
	{
		String output = employeeObj.insertEmployee(Name, Address, Phone,NIC);
		return output;
  }


//*******************************************************************************************************//
//----used json to update operation-----------------	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateEmployee(String employeeData){
		
		//Convert the input string to a JSON object
		JsonObject employeeObject = new JsonParser().parse(employeeData).getAsJsonObject();
		
		//Read the values from the JSON object
		String EID = employeeObject.get("EID").getAsString();
		String Name = employeeObject.get("name").getAsString();
		String Address = employeeObject.get("address").getAsString();
		String Phone = employeeObject.get("phone").getAsString();
		String NIC = employeeObject.get("nic").getAsString();
		String output = employeeObj.updateEmployee(EID, Name, Address, Phone, NIC) ;
	
		return output;
  }


//*********************************************************************************************************//
//----------------used xml to delete operation--------------------	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteEmployee(String employeeData){
		
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(employeeData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String EID = doc.select("EID").text();
	 String output = employeeObj.deleteEmployee(EID);
	
	 return output;
  }

}





