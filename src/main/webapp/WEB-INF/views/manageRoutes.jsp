<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Manage Carpool</title>
	
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css" />">
	<link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>

	<%@ include file="header.jsp" %>


	
	<div class="wrap">
		<div class="content">
			<div class="container">
				<h2>Manage Routes</h2>
				<a href="<c:url value="/route/add"/>" class="btn btn-primary">Add Route</a>
				<ul class="nav nav-tabs">
					<li class="active blue"><a data-toggle="pill" href="#home">My Routes</a></li>
					<li class="blue"><a data-toggle="pill" href="#menu1">Joined Routes</a></li>
				</ul>
			  
			  
				<div class="tab-content">
					<div id="home" class="tab-pane fade in active">
						<h3>My Routes</h3>
						<c:forEach var="route" items="${userRoutes}">
							<div class="rout">
								<h4>${route.name}</h4>
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
								<a href="<c:url value="/route/delete/${route.routeId}"/>" id="remove" class="btns btn btn-primary">Remove from Carpool</a>
							</div>
						</c:forEach>
						
					</div>
				
					<div id="menu1" class="tab-pane fade">
						<h3>Joined Routes</h3>
						<c:forEach var="route" items="${joinedRoutes}">
							<h4>${route.name}</h4>
							<p>Carpool Service Owner: ${route.owner.fullName}</p>
							<p><label class="col-md-4 control-label" for="fname">Source Address</label>&nbsp;${route.sourceAddress}</p>
							<p><label class="col-md-4 control-label" for="fname">Destination Address</label>&nbsp;${route.destinationAddress}</p>
							<p><label class="col-md-4 control-label" for="fname">Start Time</label>&nbsp;${route.startTime}</p>
							<p><label class="col-md-4 control-label" for="fname">End Time</label>&nbsp;${route.endTime}</p>
							<p><label class="col-md-4 control-label" for="fname">Ride Comment</label>&nbsp;${route.comment}</p>
							<p><label class="col-md-4 control-label" for="fname">Total Passenger Seats</label>&nbsp;${route.numOfPassengers}</p>
							<p><label class="col-md-4 control-label" for="fname">Free Seats</label>&nbsp;${route.freePlaces}</p>
							<h5>Passengers:</h5>
							<c:forEach var="pass" items="${route.passengers}" varStatus="counter">
									
								<p><label class="col-md-4 control-label" for="fname">${counter.index + 1}.</label>&nbsp;${pass.user.fullName} </p>
								<p><label class="col-md-4 control-label" for="fname">Pick Up Time: </label>&nbsp;${pass.pickTime}</p>
								<c:if test="${pass.user.userId == user.userId}">
									<c:if test="${pass.isApproved}">
										<p style="color:limegreen">You have been approved for this carpool</p>
									</c:if>
									<c:if test="${not pass.isApproved}">
										<p style="color:coral">You haven't been approved for this carpool yet</p>
									</c:if>
									
								</c:if>
							</c:forEach>
							<a href="${route.mapUrl }" class="btns btn btn-primary" target="_blank">Look Route on the Map</a>
							<a href="<c:url value="/route/unjoin/${route.routeId}"/>" class="btns btn btn-primary">Unjoin Route</a>
							<form action="<c:url value='/route/reminder' />" method="POST" >
								<div class="reminder">
									<p><span class="rem">Driver Reminder: </span>
									<input type="hidden" name="to" value="${route.owner.userId}" />
									<textarea class="message_area" name="message"></textarea>
									<input type="submit" class="send btns btn btn-primary" value="Send Message"/></p>
								</div>
							</form>
							
						</c:forEach>
					</div>
				</div>
			
			</div>
		</div>
	</div>
	
	
	
	<%@ include file="footer.jsp" %>
	
</body>
</html>