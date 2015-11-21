<!DOCTYPE html>
<body>
	<div id="container" class="center">
		<c:forEach items="${events}" var="event">
			<h4>
				<a href="<c:url value="/event/${event.id}/view"/>"><c:out
						value="${event.name}" /></a>
			</h4>
			<p>${event.description}</p>
		</c:forEach>
	</div>
</body>
</html>
