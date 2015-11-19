<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Profile</title>
</head>
<body>
	<h2>User Profile</h2>
	<form method="POST" action="<c:url value="/user/edit"/>">
		<input type="hidden" name="userId" value="${userInfo.userId}"/>
		<input type="hidden" name="username" value="${userInfo.username}"/>
		<input type="hidden" name="password" value="${userInfo.password}"/>
		<h3>General Information:</h3>
		<p>
		<span>Username: ${userInfo.username}</span>
		</p>
		<p>
		<span>Full Name</span>
		<input type="text" name="fullName" value="${userInfo.fullName}"/>
		</p>
		<p>
		<span>Email</span>
		<input type="text" name="email" value="${userInfo.email}"/>
		</p>
		<p>
		<span>Phone</span>
		<input type="text" name="phone" value="${userInfo.phone}"/>
		</p>
		
		<h3>Address:</h3>
		<input type="hidden" name="addressId" value="${userInfo.address.addressId}"/>
		<p>
		<span>Country</span>
		<input type="text" name="country" value="${userInfo.address.country}"/>
		</p>
		<p>
		<span>Town</span>
		<input type="text" name="town" value="${userInfo.address.town}"/>
		</p>
		<p>
		<span>Street</span>
		<input type="text" name="street" value="${userInfo.address.street}"/>
		</p>
		<p>
		<span>House number</span>
		<input type="text" name="houseNumber" value="${userInfo.address.houseNumber}"/>
		</p>
		<p>
		
		<input type="submit" value="Save Profile"/>
		</p>
	</form>
	<form method="POST" action="/carpool/user/edit-vehicle">
		<input type="hidden" name="userId" value="${userInfo.userId}"/>
		<h3>Vehicle Information:</h3>
		<input type="hidden" name="vehicleId" value="${userInfo.vehicle.vehicleId}"/>
		<p>
		<span>Car model</span>
		<input type="text" name="model" value="${userInfo.vehicle.model}"/>
		</p>
		<p>
		<span>Coutry of establishment</span>
		<input type="text" name="countryEst" value="${userInfo.vehicle.country}"/>
		</p>
		<p>
		<span>Year</span>
		<input type="text" name="year" value="${userInfo.vehicle.year}"/>
		</p>
		<p>
		<input type="submit" value="Approve Vehicle"/>
		</p>
		
	</form>
	
</body>
</html>