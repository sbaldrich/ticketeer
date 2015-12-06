<!DOCTYPE html>
<body>
	<div id="container" class="center">
		<h2>
			<c:out value="${event.name}" />
		</h2>
		<p>
			<c:out value="${event.description}" />
		</p>
		<a href="<c:url value="/secure/purchase">
				<c:param name="event" value="${event.id}"/>
				<c:param name="section" value="general"/>
				</c:url>">Buy
			general tickets</a> <br /> <a
			href="<c:url value="/secure/purchase">
			<c:param name="event" value="${event.id}"/>
			<c:param name="section" value="vip"/>
		</c:url>">Buy vip tickets</a>
	</div>
</body>
</html>
