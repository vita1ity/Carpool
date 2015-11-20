<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Route</title>
</head>
<body>
	<h2>Add Route</h2>
	<!-- <form action="<c:url value='route/add'/>" method="POST"> -->
	<form action="add" method="POST">
		<p><label>Route name</label>
		<input type="text" name="routeName" /></p>
		<p><label>Source Address</label>
		<input type="text" name="sourceAddress"/></p>
		<p><label>Destination Address</label>
		<input type="text" name="destinationAddress"/></p>
		<p><label>Start time (format: hours:minutes; ex: 20:20)</label>
		<input type="text" name="startTime"/></p>
		<p><label>End time (ex. 20:35)</label>
		<input type="text" name="endTime"/></p>
		<p><label>Ride Comment</label>
		<textarea name="comment"></textarea></p>
		<p><label>Number of passengers</label>
		<input type="text" name="numOfPassengers"/></p>
		
		<c:if test="${not empty errorMessage}">
			<span class="error--message">${errorMessage}</span>
		</c:if> 
		
		<input type="submit" value="Add Route"/>
	</form>
</body>	
</html>