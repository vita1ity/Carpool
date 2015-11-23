<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Unverified Routes</title>

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
			<h2>Unverified Routes</h2>
			<c:forEach var="route" items="${routes}" varStatus="counter">
				<div class="usr">
					<h2>${counter.index + 1}. ${route.name}</h2>
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
					<h5>Passengers:</h5>
					<c:forEach var="pass" items="${route.passengers}" varStatus="counter">
						<p><label class="col-md-4 control-label" for="fname">${counter.index + 1}. </label>&nbsp;${pass.user.fullName}</p>
						<p><label class="col-md-4 control-label" for="fname">Picking time: </label>&nbsp;${pass.pickTime}</p>
					</c:forEach>
					<div class="verify_btn">
						<form action="<c:url value='/admin/routes/verify' />" method="POST">
							<input type="hidden" name="routeId" value="${route.routeId}"/>
							<div class="form-group">
							  <label class="col-md-4 control-label" for="mapUrl">Map Url</label>  
							  <div class="col-md-4">
							  	<input name="mapUrl" type="text" placeholder="Map Url" class="form-control input-md" required>
								
							  </div>
							</div>
							
							<input type="submit" class="btn btn-primary" value="Verify Route" />
						</form>
					</div>
				</div>
				<%-- <div class="verify_btn">
					<a href="<c:url value="/admin/routes/verify/${route.routeId}"/>" class="btn btn-primary">Verify Route</a>
				</div> --%>
				
			</c:forEach>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/views/footer.jsp" %>
	
</body>
</html>