<!DOCTYPE html>
<body>
	<c:forEach items="${events}" var="event">
		<h4>
			<c:out value="${event.name}" />
		</h4>
		<p>${event.description}</p>
	</c:forEach>
</body>
</html>
