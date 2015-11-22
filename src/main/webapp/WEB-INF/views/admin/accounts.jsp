<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Verify Accounts</title>
</head>
<body>
	<a href="<c:url value="/admin/vehicles" />">Unverified Vehicles</a>
	<a href="<c:url value="/admin/routes" />">Unverified Routes</a>
	<a href="<c:url value="/admin/passengers" />">Unverified Passengers</a>
	<a href="<c:url value="/logout" />">Log Out</a>

	<h2>Unverified Accounts</h2>
	<c:forEach var="account" items="${accounts}" varStatus="counter">
		<p>${counter.index + 1}. ${account.username}</p>
		<h3>General Information:</h3>
		<p>
		<span>Username: ${account.username}</span>
		</p>
		<p>
		<span>Full Name: ${account.fullName}</span>
		</p>
		<p>
		<span>Email: ${account.email}</span>
		</p>
		<p>
		<span>Phone: ${account.phone}</span>
		</p>
		<h3>Address: ${account.address.addressId}</h3>
		<p>
		<span>Country: ${account.address.country}</span>
		</p>
		<p>
		<span>Town: ${account.address.town}</span>
		</p>
		<p>
		<span>Street: ${account.address.street}</span>
		</p>
		<p>
		<span>House number: ${account.address.houseNumber}</span>
		</p>
		
		<a href="<c:url value="/admin/accounts/verify/${account.userId}"/>">Verify Account</a>
	</c:forEach>
</body>
</html>