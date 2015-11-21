<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ticketeer</title>
</head>
<body>
	<div id="container" class="center">
		<h3>My Orders</h3>
		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Event</th>
						<th>Seats</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${orders}" var="order">
						<tr>
							<td><a href="<c:url value="/event/${order.event.id}/view"/>"><c:out
										value="${order.event.name}" /></a></td>
							<td><c:out value="${order.seats}" /></td>
							<td>Pending</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>