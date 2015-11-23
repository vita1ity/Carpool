<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty sessionScope.admin}">
	<header class="adm main_head">
</c:if>
<c:if test="${not empty sessionScope.user}">
	<header class="main_head">
</c:if>

	<div class="wrap">
		<div class="head">
			<p class="logo">CAR POOL</p>
			<div class="menu">
				<ul class="nav nav-pills">
					<c:if test="${not empty sessionScope.admin}">
						<li><a href="<c:url value="/admin/accounts" />">Accounts</a></li>
						<li><a href="<c:url value="/admin/vehicles" />">Vehicles</a></li>
						<li><a href="<c:url value="/admin/routes" />">Routes</a></li>
						<li><a href="<c:url value="/admin/passengers" />">Passengers</a></li>
					</c:if>
					<c:if test="${not empty sessionScope.user}">
						<li><a href="<c:url value="/route/manage-carpool"/>">Routes</a></li>
						<li><a href="<c:url value="/inventory" />">Inventory</a></li>
						<li><a href="<c:url value="/user/account" />">Profile</a></li>
						<li><a href="<c:url value="/notifications" />">Messages</a></li>
					</c:if>
					
					
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