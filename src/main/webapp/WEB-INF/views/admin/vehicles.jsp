<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Verify Vehicle</title>
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
			<h2>Unverified Vehicles</h2>
			<c:forEach var="vehicle" items="${vehicles}" varStatus="counter">
			<div class="usr">
				<h2>${counter.index + 1}. ${vehicle.model}</h2>
				<br />
				<h3>Vehicle Informalion</h3>
				<br />
				<p><label class="col-md-4 control-label" for="fname">Model</label>&nbsp;${vehicle.model}</p>
				<p><label class="col-md-4 control-label" for="fname">Country</label>&nbsp;${vehicle.country}</p>
				<p><label class="col-md-4 control-label" for="fname">Year</label>&nbsp;${vehicle.year}</p>
			</div>
			<div class="verify_btn">
				<a href="<c:url value="/admin/vehicles/verify/${vehicle.vehicleId}"/>" class="btn btn-primary">Verify Vehicle</a>
			</div>
			</c:forEach>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/views/footer.jsp" %>
	
</body>
</html>