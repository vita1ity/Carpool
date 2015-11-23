<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Unverified Passengers</title>
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css" />">
	<link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>

	<%@ include file="/WEB-INF/views/header.jsp" %>

	<div class="wrap">
		<div class="content">
			<h2>Unverified Passengers</h2>
			<c:forEach var="route" items="${routes}" varStatus="counter">
				<div class="usr">
					<h2>${counter.index + 1}. ${route.name} - Passenger: ${route.passengers[0].user.fullName}</h2>
					<br />
					<h3>Route Information</h3>
					<br />
					<p><label class="col-md-4 control-label" for="fname">Primary Address</label>&nbsp;${route.sourceAddress}</p>
					<p><label class="col-md-4 control-label" for="fname">Destination Address</label>&nbsp;${route.destinationAddress}</p>
					<p><label class="col-md-4 control-label" for="fname">Start Time</label>&nbsp;${route.startTime}</p>
					<p><label class="col-md-4 control-label" for="fname">End Time</label>&nbsp;${route.endTime}</p>
					<p><label class="col-md-4 control-label" for="fname">Ride Comment</label>&nbsp;${route.comment}</p>
					<p><label class="col-md-4 control-label" for="fname">Number of Seats</label>&nbsp;${route.numOfPassengers}</p>
					<p><label class="col-md-4 control-label" for="fname">Free Seats</label>&nbsp;${route.freePlaces}</p>
					
					
					<c:forEach var="pass" items="${route.passengers}">
						<h3>Passenger Information</h3>
						<br/>
						<p><label class="col-md-4 control-label" for="fname">Username</label>&nbsp;${pass.user.username}</p>
						<p><label class="col-md-4 control-label" for="fname">Full Name</label>&nbsp;${pass.user.fullName}</p>
						<p><label class="col-md-4 control-label" for="fname">Email</label>&nbsp;${pass.user.email}</p>
						<p><label class="col-md-4 control-label" for="fname">Phone</label>&nbsp;${pass.user.phone}</p>
						<h4>Address</h4>
						<p><label class="col-md-4 control-label" for="fname">Country</label>&nbsp;${pass.user.address.country}</p>
						<p><label class="col-md-4 control-label" for="fname">Town</label>&nbsp;${pass.user.address.town}</p>
						<p><label class="col-md-4 control-label" for="fname">Street</label>&nbsp;${pass.user.address.street}</p>
						<p><label class="col-md-4 control-label" for="fname">House Number</label>&nbsp;${pass.user.address.houseNumber}</p>
						
						
						<div class="verify_btn">
							<form action="<c:url value='/admin/passengers/verify' />" method="POST">
								<input type="hidden" name="passId" value="${pass.passengerId}"/>
								<div class="form-group">
								  <label class="col-md-4 control-label" for="pickTime">Pick Up Time</label>  
								  <div class="col-md-4">
								  <input name="pickTime" type="text" placeholder="Pick Time" class="form-control input-md" required>
									
								  </div>
								</div>
								
								<input type="submit" class="btn btn-primary" value="Verify Passenger" />
							</form>
						</div>
					</c:forEach>
					
					
				</div>
				
			</c:forEach>
		</div>
	</div>
	
	
	<%@ include file="/WEB-INF/views/footer.jsp" %>
	
</body>
</html>