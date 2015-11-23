<!DOCTYPE html>
<html>
<head>
<title>Register</title>
</head>
<body>
	<div id="container" class="center">
		<c:if test="${loginFailed}">
			<div class="alert alert-danger" role="alert">
				<strong>Oh snap!</strong> Your login credentials seem to be invalid
			</div>
		</c:if>
		<div>
			<form:form method="post" modelAttribute="signupForm">
				<form:label path="handle">handle</form:label>
				<br />
				<form:input path="handle" />
				<br />
				<form:label path="password">password</form:label>
				<br />
				<form:password path="password" />
				<br />
				<input type="submit" value="Log in" />
			</form:form>
			<span>Don't have an account yet? <a href="<c:url value="/signup"/>">sign up</a></span>
		</div>
	</div>
</body>
</html>