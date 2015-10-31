<!DOCTYPE html>
<html>
	<head>
		<title>Register</title>
		<style>
			.center {
				margin-left: auto;
				margin-right:auto; 
				width: 70%;
				/*background-color: #b0e0e6;*/
				border-style: solid;
				border-color:black;
			}
		</style>
	</head>
	<body class="center">
		<div style="padding:2% 5%">
			<form:form method="post" modelAttribute="signupForm">
				<form:label path="handle">handle</form:label><br/>
				<form:input path="handle"/><br/>
				<form:label path="password">password</form:label><br/>
				<form:password path="password"/><br/>
				<input type="submit" value="Sign Up"/>
			</form:form>
		</div>
	</body>
</html>