<!DOCTYPE html>
<html>
<head>
<title><c:out value="Buy tickets for ${event.name}" /></title>
</head>
<body>
	<div id="container" class="center">
		<h3>
			<c:out value="Buy tickets for ${event.name}" />
		</h3>
		<form:form method="post" modelAttribute="form">
			<form:label path="seats"># of seats</form:label>
			<form:input type="numeric" path="seats" />
			<br />
			<form:label path="seats">Confirmation email</form:label>
			<form:input type="email" path="email" />
			<br />
			<input type="submit" value="Post!" />
		</form:form>
	</div>
</body>
</html>