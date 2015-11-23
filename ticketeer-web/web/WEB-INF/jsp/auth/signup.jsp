<!DOCTYPE html>
<html>
<head>
<title>Register</title>
</head>
<body>
	<div id="container" class="center">
		<form:form method="post" modelAttribute="signupForm">
			<form:label path="handle">handle</form:label>
			<br />
			<form:input path="handle" />
			<br />
			<form:label path="email">email</form:label>
			<br />
			<form:input path="email" />
			<br />
			<form:label path="password">password</form:label>
			<br />
			<form:password path="password" />
			<br />
			<input type="submit" value="Sign Up" />
		</form:form>
	</div>
</body>
</html>