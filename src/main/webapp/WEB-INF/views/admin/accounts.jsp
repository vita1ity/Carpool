<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html >
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Verify Accounts</title>
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
			<h2>Unverified Accounts</h2>
			<c:forEach var="account" items="${accounts}" varStatus="counter">
				<div class="usr">
					<h2>${counter.index + 1}. ${account.username}</h2>
					<br />
					<h3>General Informalion</h3>
					<br />
					<p><label class="col-md-4 control-label" for="fname">Username:</label>&nbsp;${account.username}</p>
					<p><label class="col-md-4 control-label" for="fname">Full Name:</label>&nbsp;${account.fullName}</p>
					<p><label class="col-md-4 control-label" for="fname">Email</label>&nbsp;${account.email}</p>
					<p><label class="col-md-4 control-label" for="fname">Phone Number</label>&nbsp;${account.phone}</p>
					<br />
					<h4>Adress Info</h4>
					<br />
					<p><label class="col-md-4 control-label" for="fname">Country</label>&nbsp;${account.address.country}</p>
					<p><label class="col-md-4 control-label" for="fname">City</label>&nbsp;${account.address.town}</p>
					<p><label class="col-md-4 control-label" for="fname">Street</label>&nbsp;${account.address.street}</p>
					<p><label class="col-md-4 control-label" for="fname">House Number</label>&nbsp;${account.address.houseNumber}</p>
					
					
				</div>
				<div class="verify_btn">
					<a href="<c:url value="/admin/accounts/verify/${account.userId}"/>" class="btn btn-primary">Verify Account</a>
				</div>
			</c:forEach>
		</div>
	</div>
	
	
	<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>