<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<spring:url var="css" value="/resources/css" />
<spring:url var="images" value="/resources/images" />
<spring:url var="js" value="/resources/js" />

<!DOCTYPE html>
<html lang="en">

<head>


<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Online Shopping Website Using Spring MVC">
<meta name="author" content="Tejasvi M Arya">


<title>${title}</title>

<script>
	window.menu = '${title}'
	window.contextRoot = '${contextRoot}'
</script>

<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.css" rel="stylesheet" type="text/css">

<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet"
	type="text/css">



<!-- data table Style sheet -->
<link href="${css}/dataTables.bootstrap4.css" rel="stylesheet">


<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div class="wrapper">
		<!-- Navigation -->

		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="${contextRoot}/home">Home
					</a>
				</div>
			</div>
		</nav>
		
		<c:if test="${not empty message}">
		
		<div class="content">
			<div class="container">
				<div class="alert alert-danger">
					 ${message} 
				</div>
			</div>
		</div>
		</c:if>
		
		
		<c:if test="${not empty logout}">
		
		<div class="content">
			<div class="container">
				<div class="alert alert-success">
					 ${logout} 
				</div>
			</div>
		</div>
		</c:if>
		
		
		<div class="content">
			<div class="container">
				<div class="row">
					<div class="col-md-offset-3 col-md-6">
						<div class="card card-primary">
							<div class="panel-heading">
								<h4>Login Forum</h4>
							</div>
							<div class="card-block">
							
							<form action="${contextRoot}/login" method="POST" class="form-horizontal" id="loginForm">
								<div class="form-group">
									<label for="username" class="col-md-4 control-label">Username</label>
									<div class="col-md-8">
									<input type="text" name="username" id="username" class="form-control"/>
									</div>
								</div>
								
								<div class="form-group">
									<label for="password" class="col-md-4 control-label">Password</label>
									<div class="col-md-8">
									<input type="Password" name="password" id="password" class="form-control"/>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-md-offset-8 col-md-8">
									<input type="submit" value="Login" class="btn btn-primary"/>
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
									</div>
								</div>
								
							</form>
							</div>
						</div>
					</div>
				</div>
			
			</div>
		
		</div>
	</div>
	<!-- Footer -->

	<!-- jquery -->

	<script src="${js}/jquery.js"></script>

	<!-- Bootstrap core JavaScript -->

	<script src="${js}/bootstrap.min.js" type="text/javascript"></script>

	<script src="${js}/menuhighlight.js"></script>
	</div>
</body>

</html>
