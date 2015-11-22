<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Unverified Passengers</title>
</head>
<body>
	<h2>Unverified Passengers:</h2>
	<c:forEach var="route" items="${routes}" varStatus="counter">
	
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
	
		<c:forEach var="pass" items="${route.passengers}">
			<h3>Passenger Information</h3>
			<p>
			<span>Username: ${pass.user.username}</span>
			</p>
			<p>
			<span>Full Name: ${pass.user.fullName}</span>
			</p>
			<p>
			<span>Email: ${pass.user.email}</span>
			</p>
			<p>
			<span>Phone: ${pass.user.phone}</span>
			</p>
			<h3>Address: </h3>
			<p>
			<span>Country: ${pass.user.address.country}</span>
			</p>
			<p>
			<span>Town: ${pass.user.address.town}</span>
			</p>
			<p>
			<span>Street: ${pass.user.address.street}</span>
			</p>
			<p>
			<span>House number: ${pass.user.address.houseNumber}</span>
			</p>
			
			
			
			<form action="<c:url value='/admin/passengers/verify' />" method="POST">
				<input type="hidden" name="passId" value="${pass.passengerId}"/>
				<p><input type="text" name="pickTime" required/></p>
				<input type="submit" value="Verify Passenger" />
			</form>
		</c:forEach>
	</c:forEach>
</body>
</html>