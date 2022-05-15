<%@page import="model.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.js"></script>
<script src="Components/employees.js"></script>

</head>
<body>	
		<div class="container"><div class="row"><div class="col-6">
				
				<h1>Employee Management</h1>
			<form id="formEmployee" name="formEmployee">
				 Name:
				 <input id="name" name="name" type="text" class="form-control form-control-sm">
				 
				 <br> Address:
				 <input id="address" name="address" type="text" class="form-control form-control-sm">
				 
				 <br> Phone :
				 <input id="phone" name="phone" type="text" class="form-control form-control-sm">
				 
				 <br> NIC :
				 <input id="nic" name="nic" type="text" class="form-control form-control-sm">
				 
				 <br>
				 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
				 <input type="hidden" id="hidEmployeeIDSave" name="hidEmployeeIDSave" value="">
			</form>
			
			<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			<br>
			
			<div id="divEmployeesGrid">
			 <%
			 	Employee employeeObj = new Employee();
			 	out.print(employeeObj.readEmployee());
			 %>
</div>
</div> </div> </div> 
			

</body>
</html>