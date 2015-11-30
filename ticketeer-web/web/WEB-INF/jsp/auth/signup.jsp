<!DOCTYPE html>
<html>
<head>
<title>Register</title>
</head>
<body>
	<div id="container" class="center">
		<spring:message code="validation.handle.length"/>
		<form:form method="post" modelAttribute="signupForm">
			<form:label path="handle">handle</form:label>
			<br />
			<form:input path="handle" />
			<br />
			<form:errors path="handle" cssClass="error"/>
			<br />
			<form:label path="email">email</form:label>
			<br />
			<form:input path="email" />
			<br />
			<form:errors path="email" cssClass="error"/>
			<br />
			<form:label path="password">password</form:label>
			<br />
			<form:password path="password" />
			<br />
			<form:errors path="password" cssClass="error"/>
			<br />
			<input type="submit" value="Sign Up" />
		</form:form>
	</div>
</body>
</html>