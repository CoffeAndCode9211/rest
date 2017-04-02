<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="NO-STORE">
<title>Welcome</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<style type="text/css">

.fieldContainer {
	float: none;
	margin-left: auto;
	margin-right: auto;
	max-width: 480px;
	transform: translateY(50%);
}

.fieldWrapper {
	width: 100%;
}

label {
	color: #484848;
	font-weight: 600;
	display: block;
	max-width: 635px;
	padding-bottom: .8em;
	-webkit-user-select: none;
	font-family: "Open Sans", "Helvetica Neue", Arial, Helvetica, Verdana,
		sans-serif;
	font-size: 15px;
	line-height: 18px;
}

input[type="text"], input[type="password"] {
	display: inline-block;
	padding: 0 .4em 0 .4em;
	margin-bottom: 2em;
	vertical-align: middle;
	border-radius: 3px;
	min-width: 50px;
	max-width: 635px;
	width: 100%;
	min-height: 32px;
	background-color: #fff;
	border: 2px solid #c9c9c9;
	margin: 0 0 24px 0;
}

label, input, select, textarea {
	font-family: "Open Sans", "Helvetica Neue", Arial, Helvetica, Verdana,
		sans-serif;
	font-size: 15px;
	line-height: 18px;
	font-weight: normal;
}

.btnShow {
	width: 33px;
	color: #fff;
	background-color: #737373;
	font-family: "Open Sans", "Helvetica Neue", Arial, Helvetica, Verdana,
		sans-serif;
	text-align: center;
	vertical-align: middle;
	text-transform: capitalize;
	-webkit-user-select: none;
	white-space: nowrap;
	overflow: hidden;
	cursor: pointer;
	border: 0 none;
	border-radius: 3px;
	display: inline-block;
	font-size: 13px;
	padding: 6px;
}

.button-large {
	color: #fff;
	background-color: #737373;
	font-family: "Open Sans", "Helvetica Neue", Arial, Helvetica, Verdana,
		sans-serif;
	text-align: center;
	vertical-align: middle;
	text-transform: capitalize;
	-webkit-user-select: none;
	white-space: nowrap;
	overflow: hidden;
	padding: 0 55px;
	cursor: pointer;
	margin-right: 18px;
	border: 0 none;
	border-radius: 3px;
	display: inline-block;
	font-size: 15px;
	height: 48px;
	line-height: 49px;
}

.btnContainer {
	width: 100%;
}

.btnWrapper {
	padding-left: 40%;
}

.button-large:hover {
	background-color: #686868;
}

.hide-show {
	width: 7%;
	position: relative;
	z-index: 5;
	margin-top: -11.5%;
	float: right;
}
</style>
<script type="text/javascript">
	$(function() {

		$('.hide-show span').click(function() {
			if ($(this).hasClass('show')) {
				$(this).text('Hide');
				$('#j_password').attr('type', 'text');
				$(this).removeClass('show');
			} else {
				$(this).text('Show');
				$('#j_password').attr('type', 'password');
				$(this).addClass('show');
			}
		});

	});
</script>
</head>
<body class="mainContainer">
	<form method="POST" action="j_security_check">
		<div class="fieldContainer">
			<div class="fieldWrapper">
				<label>Username:</label> <input type="text" name="j_username"
					id="j_username" />
			</div>
			<div>
				<label>Password:</label> <input type="password" name="j_password"
					id="j_password" />
				<div class="hide-show">
					<span class="btnShow show">Show</span>
				</div>
			</div>
			<div class="btnContainer">
				<div class="btnWrapper">
					<button type="submit" value="log in" class="button-large"
						tabindex="3">Log in</button>
				</div>
			</div>
		</div>
	</form>
</body>
</html>