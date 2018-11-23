<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<spring:url var="css" value="/resources/css" />

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>${title}</title>


<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">




<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>
	<div class="wrapper">
	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
	<div class="navbar-header">
		<a class="navbar-brand" href="${contextRoot}/home">Home</a>
		
	</div>
	</div>
	
	</nav>	
	
	<div class="content">
		<div class="container">
			<div class="row">
            	<div class="col-xs-12">
            		<div class="jumbotron">
            			<h1> ${errorTitle}</h1>
            			<hr/>
            			<blockquote style="word-wrap:break-word">
            				 ${errorDescription}
            			</blockquote>
            		</div>
            	</div>	
	
			</div>		
		</div>
	</div>
	
	
	</div>
</body>

</html>

