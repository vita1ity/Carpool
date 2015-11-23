<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Edit Profile</title>
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
			<form class="form-horizontal" method="POST" action="<c:url value="/user/account/edit"/>">
				<fieldset>

					<!-- Form Name -->
					<legend>Profile</legend>

						<!-- Text input-->
						<input type="hidden" name="userId" value="${userInfo.userId}"/>
						<input type="hidden" name="username" value="${userInfo.username}"/>
						<input type="hidden" name="password" value="${userInfo.password}"/>
						<div class="form-group">
						  <label class="col-md-4 control-label" for="fname">Full Name</label>  
						  <div class="col-md-4">
						  <input id="fname" name="fullName" type="text" value="${userInfo.fullName}" placeholder="Full Name" class="form-control input-md" required>
							
						  </div>
						</div>

						<!-- Text input-->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="email">Email</label>  
						  <div class="col-md-4">
						  <input id="email" name="email" type="text" value="${userInfo.email}" placeholder="Email" class="form-control input-md" required>
							
						  </div>
						</div>

						<!-- Text input-->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="phone">Phone Number</label>  
						  <div class="col-md-4">
						  <input id="phone" name="phone" type="text" value="${userInfo.phone}" placeholder="Phone Number" class="form-control input-md">
							
						  </div>
						</div>
						
						<!-- Text input-->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="photobtn">Photo URL</label>
						  <div class="col-md-4">
							<input id="photobtn" name="imageUrl"  class="form-control input-md" type="text" value="${userInfo.imageUrl}" placeholder="Photo URL">
						  </div>
						</div>
						

						<input type="hidden" name="addressId" value="${userInfo.address.addressId}"/>
						<!-- Text input-->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="country">Country</label>  
						  <div class="col-md-4">
						  <input id="country" name="country" type="text" value="${userInfo.address.country}" placeholder="Country" class="form-control input-md">
							
						  </div>
						</div>

						<!-- Text input-->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="town">Town</label>  
						  <div class="col-md-4">
						  <input id="town" name="town" type="text" value="${userInfo.address.town}" placeholder="Town" class="form-control input-md">
							
						  </div>
						</div>

						<!-- Text input-->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="street">Street</label>  
						  <div class="col-md-4">
						  <input id="street" name="street" type="text" value="${userInfo.address.street}" placeholder="Street" class="form-control input-md">
							
						  </div>
						</div>

						<!-- Text input-->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="housenumber">House Number</label>  
						  <div class="col-md-4">
						  <input id="housenumber" name="houseNumber" type="text" value="${userInfo.address.houseNumber}" placeholder="House Number" class="form-control input-md">
							
						  </div>
						</div>
						
						<!-- Button -->
						<div class="form-group">
						  <label class="col-md-4 control-label" for="saveprofile"></label>
						  <div class="col-md-4">
							<input id="saveprofile" type="submit" class="btn btn-primary" value="Save Profile"/>
						  </div>
						</div>

				</fieldset>
				
			</form>
				
				<!--Vehicle form-->
				
				<form class="form-horizontal" method="POST" action="<c:url value="/user/edit-vehicle"/>">
					<fieldset>

					<!-- Form Name -->
					<legend>Vehicle Info</legend>
					
					<input type="hidden" name="userId" value="${userInfo.userId}"/>
					<input type="hidden" name="vehicleId" value="${userInfo.vehicle.vehicleId}"/>
					
					<!-- Text input-->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="model">Model</label>  
					  <div class="col-md-4">
					  <input id="model" name="model" type="text" value="${userInfo.vehicle.model}" placeholder="Model" class="form-control input-md">
						
					  </div>
					</div>

					<!-- Text input-->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="vehiclecountry">Country</label>  
					  <div class="col-md-4">
					  <input id="countryEst" name="countryEst" type="text" value="${userInfo.vehicle.country}" placeholder="Country" class="form-control input-md">
						
					  </div>
					</div>

					<!-- Text input-->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="year">Year</label>  
					  <div class="col-md-4">
					  <input id="year" name="year" type="text" value="${userInfo.vehicle.year}" placeholder="Year" class="form-control input-md">
						
					  </div>
					</div>

					<!-- Button -->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="appvehice"></label>
					  <div class="col-md-4">
						<input id="appvehice" type="submit" class="btn btn-primary" value="Approve Vehicle" />
					  </div>
					</div>

					</fieldset>
			</form>
		</div>
	</div>
	
	<%@ include file="footer.jsp" %>
	
</body>
</html>