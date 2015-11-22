<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Verify Vehicle</title>
</head>
<body>
	<h2>Unverified Vehicles</h2>
	<c:forEach var="vehicle" items="${vehicles}" varStatus="counter">
		<p>${counter.index + 1}. ${vehicle.model}</p>
		<h3>Vehicle Information:</h3>
		<p>
		<span>Car model: ${vehicle.model}</span>
		</p>
		<p>
		<span>Country of establishment: ${vehicle.country}</span>
		</p>
		<p>
		<span>Year: ${vehicle.year}</span>
		</p>
		
		<a href="<c:url value="/admin/vehicles/verify/${vehicle.vehicleId}"/>">Verify Vehicle</a>
	</c:forEach>
</body>
</html>