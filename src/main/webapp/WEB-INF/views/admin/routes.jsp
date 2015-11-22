<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Unverified Routes</title>
</head>
<body>
	<h2>Unverified Routes</h2>
	<c:forEach var="route" items="${routes}" varStatus="counter">
		<p>${counter.index + 1}. ${route.name}</p>
		<h3>Route Information:</h3>
		<p>
		<span>Name: ${route.name}</span>
		</p>
		<p>
		<span>Source Address: ${route.sourceAddress}</span>
		</p>
		<p>
		<span>Destination Address: ${route.destinationAddress}</span>
		</p>
		<p>
		<span>Start Time: ${route.startTime}</span>
		</p>
		<p>
		<span>End Time: ${route.endTime}</span>
		</p>
		<p>
		<span>Comment: ${route.comment}</span>
		</p>
		<p>
		<span>Number of Passengers: ${route.numOfPassengers}</span>
		</p>
		<p>
		<span>Free Places: ${route.freePlaces}</span>
		</p>
		<p>Passengers:</p>
		<c:forEach var="pass" items="${route.passengers}" varStatus="counter">
			<p>${counter.index + 1}. ${pass.user.fullName} </p>
			<p>Picking time: ${pass.pickTime}</p>
		</c:forEach>
		<a href="<c:url value="/admin/routes/verify/${route.routeId}"/>">Verify Route</a>
	</c:forEach>
</body>
</html>