<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Carpools</title>
</head>
<body>
	<a href="<c:url value="/user/account" />">User Account</a>
	<a href="<c:url value="/inventory" />">Carpool Inventory</a>
	
	<a href="<c:url value="/route/add" />">Add route</a>
	
	<h2>My Routes</h2>
	<c:forEach var="route" items="${userRoutes}">
		<h3>${route.name}</h3>
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
		<a href="<c:url value="/route/delete/${route.routeId}"/>">Delete Route</a>
	</c:forEach>
	
	<h2>Joined Routes</h2>
	<c:forEach var="route" items="${joinedRoutes}">
		<h3>${route.name}</h3>
		<p>Source Address: ${route.sourceAddress}</p>
		<p>Destination Address: ${route.destinationAddress}</p>
		<p>Start time: ${route.startTime}</p>
		<p>End time: ${route.endTime}</p>
		<p>Carpool Service Owner: ${route.owner.fullName}</p>
		<p>Ride Comment: </p>
		<p>${route.comment}</p>
		<p>Number of passengers: ${route.numOfPassengers}</p>
		<p>Number of free places: ${route.freePlaces}</p>
		
		<p>Passengers:</p>
		<c:forEach var="pass" items="${route.passengers}" varStatus="counter">
			<p>${counter.index + 1}. ${pass.user.fullName}</p>
			<p>Picking time: ${pass.pickTime}</p>
			<c:if test="${pass.user.userId == user.userId}">
				<c:if test="${pass.isApproved}">
					<p>You have been approved for this carpool</p>
				</c:if>
				<c:if test="${not pass.isApproved}">
					<p>You haven't been approved for this carpool yet</p>
				</c:if>
				
			</c:if>
			
		</c:forEach>
		<a href="<c:url value="/route/unjoin/${route.routeId}"/>">Unjoin Route</a>
		<form action="<c:url value='route/reminder' />" method="POST" >
			<p><span>Driver Reminder: </span>
			<textarea name="reminder"></textarea></p>
			<input type="submit" value="Remind the Driver"/>
		</form>
	</c:forEach>
</body>
</html>