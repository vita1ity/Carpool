<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="main_head">
	<div class="wrap">
		<div class="head">
			<p class="logo">CAR POOL</p>
			<div class="menu">
				<ul class="nav nav-pills">
					
					<li><a href="<c:url value="/route/manage-route"/>">Routes</a></li>
					<li><a href="<c:url value="/inventory" />">Inventory</a></li>
					<li><a href="<c:url value="/user/account" />">Profile</a></li>
					<li><a href="<c:url value="/notifications" />">Messages</a></li>
					
				</ul>
			</div>
			<div class="btn">
				<a id="logout" class="btn btn-primary" href="<c:url value="/logout" />">Logout</a>
			</div>
			<div class="clean">
			</div>
		</div>
	</div>
</header>