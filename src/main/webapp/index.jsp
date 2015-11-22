<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>Carpool Service</title>
	
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css" />">
	
	<link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script>
	$(document).ready(function(){
		$(".btn1").click(function(){
			$("div").slideUp();
		});
		$(".slide").click(function(){
			var descInfo = $(this).parent().parent().find('.descinfo');
			console.log(descInfo);
			$(descInfo).slideDown();
		});
	});
	</script>

<style type="text/css"></style></head>
<body class="landing">
<header class="headerwrap">
</header>
	<div class="wrap">
	<div class="loginzone">
		<a class="login" href="#" data-toggle="modal" data-target="#myModal">Login</a>
	</div>
		<div class="land_main">
			<h1 class="llogo landlogo">CAR POOL</h1>
			<a class="lnav" href="#"  data-toggle="modal" data-target="#myModalSignUp">GET STARTED!</a>
		</div>
	</div>

<!-- Modal SignIn -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">SignIn</h4>
      </div>
      <div class="modal-body">
       
		<form class="form-horizontal logn_f" action="<c:url value="/login-signup" />" method="POST">
			<fieldset>

				<!-- Form Name -->
				<!--<legend>SignIn</legend> -->

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="textinput">Username</label>  
					<div class="col-md-4">
						<input id="textinput" name="username" type="text" placeholder="Username" class="form-control input-md" required />
					</div>
				</div>

				<!-- Password input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="passwordinput">Password</label>
					<div class="col-md-4">
						<input id="passwordinput" name="password" type="password" placeholder="Password" class="form-control input-md" required />
					</div>
				</div>
				<c:if test="${not empty errorMessageLogin}">
					<span class="error-message">Your login attempt was not successful, try again.<br/>
					Reason: ${errorMessageLogin}</span>
				</c:if> 
				<!-- Button -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="singlebutton"></label>
					<div class="col-md-4">
						<input class="btn btn-success" type="submit" value="Login">
					</div>
				</div>

			</fieldset>
		</form>
	   
      </div>
    </div>
  </div>
</div>

<!-- Modal SignUp -->
<div class="modal fade" id="myModalSignUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">SignUp</h4>
      </div>
      <div class="modal-body">
       
		<form class="form-horizontal logn_f" action="<c:url value="/signup" />" method="POST">
			<fieldset>

				<!-- Form Name -->
				<!--<legend>SignIn</legend> -->

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="textinput">Username</label>  
					<div class="col-md-4">
						<input id="textinput" name="username" type="text" placeholder="Username" class="form-control input-md" required>
					</div>
				</div>
				
				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="textinput">Email</label>  
					<div class="col-md-4">
						<input id="textinput" name="email" type="text" placeholder="Email" class="form-control input-md" required>
					</div>
				</div>

				<!-- Password input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="passwordinput">Password</label>
					<div class="col-md-4">
						<input id="passwordinput" name="password" type="password" placeholder="Password" class="form-control input-md" required>
					</div>
				</div>
				
				<!-- Password input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="passwordinput">Confirm Password</label>
					<div class="col-md-4">
						<input id="passwordinput" name="confirmPassword" type="password" placeholder="Confirm Password" class="form-control input-md" required>
					</div>
				</div>
				<c:if test="${not empty errorMessageReg}">
				<span class="error-message">${errorMessageReg}</span>
			</c:if> 
				<!-- Button -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="singlebutton"></label>
					<div class="col-md-4">
						<input class="btn btn-success" type="submit" value="Let's get started!">
						
					</div>
				</div>

			</fieldset>
		</form>
	   
      </div>
    </div>
  </div>
</div>


</body>
</html>