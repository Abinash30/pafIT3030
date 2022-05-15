 $(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
 
// Form validation-------------------
var status = validateEmployeeForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidEmployeeIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "EmployeeAPI",
 type : type,
 data : $("#formEmployee").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onEmployeeSaveComplete(response.responseText, status);
 }
 });
});

function onEmployeeSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divEmployeesGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 }  $("#hidEmployeeIDSave").val("");
 $("#formEmployee")[0].reset();
}


function onEmployeeDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divEmployeesGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}

$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "EmployeeAPI",
 type : "DELETE",
 data : "EID=" + $(this).data("noticeid"),
 dataType : "text",
 complete : function(response, status)
 {
 onEmployeeDeleteComplete(response.responseText, status);
 }
 });
});


$(document).on("click", ".btnUpdate", function(event)
{
$("#hidEmployeeIDSave").val($(this).data("eid"));
 $("#name").val($(this).closest("tr").find('td:eq(0)').text());
 $("#address").val($(this).closest("tr").find('td:eq(1)').text());
 $("#phone").val($(this).closest("tr").find('td:eq(2)').text());
 $("#nic").val($(this).closest("tr").find('td:eq(3)').text());
});


function validateNoticeForm()
{
// CODE
if ($("#name").val().trim() == "")
 {
 return "Insert Employee Name.";
 }
// NAME
if ($("#address").val().trim() == "")
 {
 return "Insert Employee Address.";
 } 
 // PRICE-------------------------------
if ($("#phone").val().trim() == "")
 {
 return "Insert Employee Phone.";
 }

if ($("#nic").val().trim() == "")
 {
 return "Insert Employee NIC.";
 }
return true;
}