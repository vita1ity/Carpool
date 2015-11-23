<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Add Route</title>
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
				<h3>Add route</h3>
				<div class="rout">
					<form class="form-horizontal" method="POST" action="<c:url value="/route/add"/>">
					<fieldset>

					<!-- Text input-->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="padress">Route Name</label>  
					  <div class="col-md-4">
					  <input id="routeName" name="routeName" type="text" placeholder="Route Name" class="form-control input-md">
						
					  </div>
					</div>

					<!-- Text input-->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="padress">Source Address</label>  
					  <div class="col-md-4">
					  <input id="paddress" name="sourceAddress" type="text" placeholder="Primary Address" class="form-control input-md">
						
					  </div>
					</div>

					<!-- Text input-->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="dadress">Destination Address</label>  
					  <div class="col-md-4">
					  <input id="daddress" name="destinationAddress" type="text" placeholder="Destination Address" class="form-control input-md">
						
					  </div>
					</div>

					<!-- Text input-->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="sttime">Start Time</label>  
					  <div class="col-md-4">
					  <input id="sttime" name="startTime" type="text" placeholder="hh:mm, ex: 20:00" class="form-control input-md">
						
					  </div>
					</div>

					<!-- Text input-->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="endtime">End Time</label>  
					  <div class="col-md-4">
					  <input id="endtime" name="endTime" type="text" placeholder="hh:mm, ex: 20:30" class="form-control input-md">
						
					  </div>
					</div>

					<!-- Text input-->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="comment">Ride Comment</label>  
					  <div class="col-md-4">
					  <input id="comment" name="comment" type="text" placeholder="Comment" class="form-control input-md">
						
					  </div>
					</div>

					<!-- Text input-->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="comment">Number of Seats</label>  
					  <div class="col-md-4">
					  <input id="numOfPassengers" name="numOfPassengers" type="text" placeholder="Available Seats" class="form-control input-md">
						
					  </div>
					</div>
					<div class="form-group">
					<c:if test="${not empty errorMessage}">
						<label class="col-md-4 control-label error-message" for="comment">${errorMessage}</label>
						<%-- <span class="error-message">${errorMessage}</span> --%>
					</c:if>
					</div>
					<!-- Button -->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="addrout"></label>
					  <div class="col-md-4">
						<input id="addrout" type="submit" class="btn btn-primary" value="Add Route"/>
					  </div>
					</div>

					</fieldset>
					</form>

				</div>
			</div>
		</div>
	</div>
	
	<%@ include file="footer.jsp" %>
	
</body>	
</html>