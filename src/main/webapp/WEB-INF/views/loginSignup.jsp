<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Log in / sign up</title>
</head>
<body>
	<div class="login">
		<h2>Login</h2>
		<form method="POST" action="login-signup">
			<p>
			<span>Username</span>
			<input type="text" name="username" />
			</p>
			<p>
			<span>Password</span>
			<input type="password" name="password" />
			</p>
			<c:if test="${not empty requestScope.errorMessageLogin}">
				<span class="error--message">Your login attempt was not successful, try again.
				Reason: ${requestScope.errorMessageLogin}</span>
			</c:if> 
			
			<input class="btn1" name="commit" type="submit" value="Log In"/>
			
		</form>
	</div>
	
	<div class="signup">
		<h2>Sign Up</h2>
		<form method="POST" action="signup">
			<p>
			<span>Username</span>
			<input type="text" name="username" />
			</p>
			<span>E-mail</span>
			<input type="text" name="email" />
			<p>
			<span>Phone</span>
			<input type="text" name="phone" />
			</p>
			<p>
			<span>Password</span>
			<input type="password" name="password" />
			</p>
			<p>
			<span>Confirm Password</span>
			<input type="password" name="confirmPassword" />
			</p>
			<c:if test="${not empty requestScope.errorMessageReg}">
				<span class="error--message">${requestScope.errorMessageReg}</span>
			</c:if> 
			
			<input class="btn1" name="commit" type="submit" value="Sign Up"/>
		</form>
	</div>
</body>
</html>