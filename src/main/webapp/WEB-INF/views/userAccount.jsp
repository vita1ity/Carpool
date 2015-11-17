<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Profile</title>
</head>
<body>
	<h2>User Profile</h2>
	<form method="POST" action="user/edit">
		
		<h3>General Information:</h3>
		<p>
		<span>Username: ${user.username}</span>
		</p>
		<p>
		<span>Full Name</span>
		<input type="text" name="fullname" value="${user.fullName}"/>
		</p>
		<p>
		<span>Email</span>
		<input type="text" name="email" value="${user.email}"/>
		</p>
		<p>
		<span>Phone</span>
		<input type="text" name="phone" value="${user.phone}"/>
		</p>
		
		<h3>Address:</h3>
		<p>
		<span>Country</span>
		<input type="text" name="country" value="${user.address.country}"/>
		</p>
		<p>
		<span>Town</span>
		<input type="text" name="town" value="${user.address.town}"/>
		</p>
		<p>
		<span>Street</span>
		<input type="text" name="street" value="${user.address.street}"/>
		</p>
		<p>
		<span>House number</span>
		<input type="text" name="houseNumber" value="${user.address.houseNumber}"/>
		</p>
		<p>
		
		<input type="submit" value="Save Profile"/>
		</p>
	</form>
	<form method="POST" action="user/edit-vehicle">
		<h3>Vehicle Information:</h3>
		<p>
		<span>Car model</span>
		<input type="text" name="model" value="${user.vehicle.model}"/>
		</p>
		<p>
		<span>Coutry of establishment</span>
		<input type="text" name="model" value="${user.vehicle.country}"/>
		</p>
		<p>
		<span>Year</span>
		<input type="text" name="year" value="${user.vehicle.year}"/>
		</p>
		<p>
		<input type="submit" value="Approve Vehicle"/>
		</p>
		
	</form>
	
</body>
</html>