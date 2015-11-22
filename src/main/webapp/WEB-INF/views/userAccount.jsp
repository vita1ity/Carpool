<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Account</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css" />">
	
	<link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
	<%@ include file="header.jsp" %>
	
	<div class="wrap">
		<div class="content">
			<div class="profl">
				<a href="<c:url value="/user/account/edit"/>" class="btn btn-primary">Edit Profile</a>
				<div class="photo"><img src="${userInfo.imageUrl}" height="200" width="200"></div>
				<div class="desc">
					<div class="usr">
						<h2>${userInfo.fullName}</h2>
						
						<br />
						<p><label class="col-md-4 control-label" for="fname">Username</label>&nbsp;${userInfo.username }</p>
						<p><label class="col-md-4 control-label" for="fname">Email</label>&nbsp;${userInfo.email }</p>
						<p><label class="col-md-4 control-label" for="fname">Phone Number</label>&nbsp;${userInfo.phone}</p>
						<br />
						<h4>Address Info</h4>
						<br />
						<p><label class="col-md-4 control-label" for="fname">Country</label>&nbsp;${userInfo.address.country}</p>
						<p><label class="col-md-4 control-label" for="fname">City</label>&nbsp;${userInfo.address.town}</p>
						<p><label class="col-md-4 control-label" for="fname">Street</label>&nbsp;${userInfo.address.street}</p>
						<p><label class="col-md-4 control-label" for="fname">House Number</label>&nbsp;${userInfo.address.houseNumber}</p>
					</div>
					
					<div class="vehicle">
						<h4>Vehicle Info</h4>
						<br />
						<p><label class="col-md-4 control-label" for="fname">Model</label>&nbsp;${userInfo.vehicle.model}</p>
						<p><label class="col-md-4 control-label" for="fname">Country</label>&nbsp;${userInfo.vehicle.country}</p>
						<p><label class="col-md-4 control-label" for="fname">Year</label>&nbsp;${userInfo.vehicle.year}</p>
					</div>
				</div>
			</div>
			
		</div>
	</div>
	
	<%@ include file="footer.jsp" %>
</body>
</html>