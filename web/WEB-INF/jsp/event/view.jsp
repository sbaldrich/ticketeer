<!DOCTYPE html>
<body>
	<h2><c:out value="${event.name}"/></h2>
	<p><c:out value="${event.description}"/></p>
	<a href="<c:url value="/event/${event.id}/purchase" />">Buy general tickets</a>
	<br/>
	<a href="<c:url value="/event/${event.id}/purchase">
			<c:param name="section" value="vip"/>
		</c:url>">Buy vip tickets</a>
</body>
</html>
