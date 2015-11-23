<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Carpool Inventory</title>
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
			<div class="container">
				<h2>Carpool Services</h2>
				<div class="searchbox">
				<form action="<c:url value="/inventory"/>" method="POST">
					<div class="col-lg-6">
						<div class="input-group">
							
							  <input type="text" name="searchString" class="form-control" placeholder="Search by Place...">
							  <span class="input-group-btn">
								<input class="btn btn-primary" type="submit" value="Go!" />
							  </span>
							 
						</div>
					  </div>
					  </form>
				</div>
				<c:forEach var="route" items="${routes}" varStatus="counter">
					<div class="rout">
						<h4>${route.name}</h4>
						<p>Carpool Service Owner: ${route.owner.fullName}</p>
						<p><label class="col-md-4 control-label" for="fname">Source Address</label>&nbsp;${route.sourceAddress}</p>
						<p><label class="col-md-4 control-label" for="fname">Destination Address</label>&nbsp;${route.destinationAddress}</p>
						<p><label class="col-md-4 control-label" for="fname">Start Time</label>&nbsp;${route.startTime}</p>
						<p><label class="col-md-4 control-label" for="fname">End Time</label>&nbsp;${route.endTime}</p>
						<p><label class="col-md-4 control-label" for="fname">Ride Comment</label>&nbsp;${route.comment}</p>
						<p><label class="col-md-4 control-label" for="fname">Total Passenger Seats</label>&nbsp;${route.numOfPassengers}</p>
						<p><label class="col-md-4 control-label" for="fname">Free Seats</label>&nbsp;${route.freePlaces}</p>
						<c:if test="${route.isApproved}">
							<p style="color:limegreen">Route has been approved!</p>
						</c:if>
						<c:if test="${not route.isApproved}">
							<p style="color:coral">Route hasn't been approved yet</p>
						</c:if>
						<h5>Passengers:</h5>
						<c:forEach var="pass" items="${route.passengers}" varStatus="counter">
						
							<p><label class="col-md-4 control-label" for="fname">${counter.index + 1}.</label>&nbsp;${pass.user.fullName} </p>
							<p><label class="col-md-4 control-label" for="fname">Pick Up Time: </label>&nbsp;${pass.pickTime}</p>
							
						</c:forEach>
						<a href="${route.mapUrl }" class="btns btn btn-primary" target="_blank">Look Route on the Map</a>
						<a href="<c:url value="/route/join/${route.routeId}" />" id="remove" class="btns btn btn-primary">Join Carpool</a>
					</div>
				</c:forEach>
		
			</div>
		</div>
	</div>	
	
	
	
	<%@ include file="footer.jsp" %>
	
</body>
</html>