<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Carpool Inventory</title>
</head>
<body>
	<h2>List of Services</h2>	
	<c:forEach var="route" items="${routes}" varStatus="counter">
		<p>${counter.index + 1}. ${route.name}</p>
	
		<p>Source Address: ${route.sourceAddress}</p>
		<p>Destination Address: ${route.destinationAddress}</p>
		<p>Start time: ${route.startTime}</p>
		<p>End time: ${route.endTime}</p>
		<p>Ride Comment: </p>
		<p>${route.comment}</p>
		<p>Number of passengers: ${route.numOfPassengers}</p>
		<p>Number of free places: ${route.freePlaces}</p>
		
		<p>Passengers:</p>
		<c:forEach var="pass" items="${route.passengers}" varStatus="counter">
			<p>${counter.index + 1}. ${passenger.user.fullName} </p>
			<p>Picking time: ${passenger.pickTime}</p>
		</c:forEach>
		<form>
			<a href="<c:url value="/route/join/${route.routeId}" />">Join Carpool</a>
		</form>
	</c:forEach>
</body>
</html>