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
		<form:form method="post" modelAttribute="ticketPurchaseForm">
			<form:label path="seats"># of seats</form:label>
			<form:input type="number" min="1" max="10" path="seats" />
			<br />
			<form:errors path="seats" cssClass="error" />
			<br />
			<form:label path="seats">Confirmation email</form:label>
			<form:input type="email" path="email" />
			<br />
			<form:errors path="email" cssClass="error" />
			<br />
			<input type="submit" value="Post!" />
		</form:form>
	</div>
</body>
</html>