<%@ page import="java.util.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="NO-STORE" >
<title>Provider Login</title>

<script type="text/javascript">

	function wait()
	{
		
	}
</script>
</head>
<body>
<FORM METHOD=POST ACTION=j_security_check> 
	
	<table align="center" border="1" BORDERCOLOR="#a6c9e2" cellspacing="0" cellpadding="1" style="background:#dfeffc;border-color:#a6c9e2;table-layout:fixed" width="50%">
	<tr>
	<td>Name: </td>
	<td><input type="text" name="j_username" id="j_username"/></td>
	</tr>
	<tr>
	<td>Password: </td>
	<td><input type="password" name="j_password" id="j_password"/></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" name="enter" id="cmdSubmit" onClick="submit();" value="Login"/></td>
	</tr>

	</table>
</FORM>
</body>
</html>