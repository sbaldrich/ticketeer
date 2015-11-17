<!DOCTYPE html>
<html>
	<head>
		<title>Register</title>
	</head>
	<body class="center">
		<h3>Please login to continue with your purchase</h3>
		<c:if test="${loginFailed}">
			<div class="alert alert-danger" role="alert">
				<strong>Oh snap!</strong> Your login credentials seem to be invalid
			</div>
		</c:if>
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