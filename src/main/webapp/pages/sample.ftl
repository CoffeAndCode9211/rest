<style type="text/css">



.empContainer .header {
	text-align: left;
	padding: 5px;
}

.empContainer .h2 {
	font-family: arial, sans-serif;
	font-size: 22px;
}

.empContainer .row {
	width: 46%;
	float: left;
	padding: 2%;
}

.empContainer .row label {
	float: left;
	padding-right: 10px;
	width: 90px;
}

.empContainer .row input[type="text"] {
	float: left;
}

.empContainer .btnContainer {
	text-align: center;
}

.empContainer #tblEmpDetail {
	height: 400px;
	overflow-y: auto;
	width: 100%;
	padding-top: 10px;
}

.empContainer #tblEmpDetail table {
	width: 100%;
}

.empContainer #tblEmpDetail table tr:hover {
	background-color: lightblue;
}

.empContainer #tblEmpDetail .empTableHeader {
	font-weight: bold;
}
</style>
<script type="text/javascript">

var selectedEmpId = "";

function searchEmployee(){
	var url = "rest/empservlet?";
	url += $("#employeeForm").serialize();
	$(".srcContainer").load(url);
	
}


function saveEmployee(){
	var url = "rest/employee";
	var type = "POST";
	var data = {
			firstName : $("#txtEmpFirstName").val(),
			lastName : $("#txtEmpLastName").val(),
			email : $("#txtEmpEmail").val(),
			phone : $("#txtEmpPhone").val(),
			};
	if(selectedEmpId != ""){
		type = "PUT";
		url += "/"+selectedEmpId;
	}
	
	$.ajax({
		type : type,
		url : url,
		data : JSON.stringify(data),
		contentType: "application/json",
		success : function(data) {
	         alert(data.Success);
        	 resetEmployee();
      	}, error : function(error){ alert(JSON.stringify(error.responseText));}
	});
	
}



function setEmployee(employeeId){
	var url = "rest/employee/"+employeeId;
	$.get(url, function(data, status){
		selectedEmpId = employeeId;
		$("#btnSaveEmployee").val("Update");
		$("#txtEmpFirstName").val(data.firstName);
 		$("#txtEmpLastName").val(data.lastName);
 		$("#txtEmpEmail").val(data.email);
 		$("#txtEmpPhone").val(data.phone);
	});
}

function deleteEmployee(){
	if(selectedEmpId != ""){
		$.ajax({
			type : "DELETE",
			url : 'rest/employee/'+selectedEmpId,
			success : function(data) {
		         alert(data.Success);
		         resetEmployee();
	      	}, error : function(error){ alert(error.Error); }
		});
	}
}

function resetEmployee(){
	 $(".srcContainer").load("rest/empservlet");
}

</script>
<div class="empContainer">
	<form  id="employeeForm" >
		<div class="header">
			<h2>Employee</h2>
		</div>
		<div class="row">
			<label>First Name:</label>
			<div class="inputField">
				<input type="text" name="txtEmpFirstName" id="txtEmpFirstName">
			</div>
		</div>
		<div class="row">
			<label>Last Name:</label>
			<div class="inputField">
				<input type="text" name="txtEmpLastName" id="txtEmpLastName">
			</div>
		</div>
		<div class="row">
			<label>Email:</label>
			<div class="inputField">
				<input type="text" name="txtEmpEmail" id="txtEmpEmail">
			</div>
		</div>
		<div class="row">
			<label>Phone:</label>
			<div class="inputField">
				<input type="text" name="txtEmpPhone" id="txtEmpPhone">
			</div>
		</div>
		<div class="btnContainer">
			<input type="button" value="Search" onClick="searchEmployee()" > 
			<input type="button" onClick='saveEmployee()' value="Save" id="btnSaveEmployee"> 
			<input type="button" value="Delete" onclick="deleteEmployee()" > 
			<input type="button" value="Reset" onclick="resetEmployee()" >
		</div>
		<div id="tblEmpDetail">
			<table>
    			<tr class='empTableHeader'>
        			<td>First Name</td><td>Last Name</td><td>Email</td><td>Phone</td>
   				 </tr>
   				 <#list employee as emp>
    			 <tr onclick='setEmployee(${emp.id})' >
        			<td>${emp.firstName}</td> 
        			<td>${emp.lastName}</td>
					<td>${emp.email}</td>
					<td>${emp.phone}</td>
    			 </tr>
    			</#list>
  			</table>
		</div>
	</form>
</div>