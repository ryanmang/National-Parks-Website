<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="common/header.jspf"%>

<title>National Park Geek</title>
<div class="container">
<div class="mainbody">
	<h2><c:out value="National Parks"/></h2>
		<p>
			<c:forEach var="parks" items="${parkMap}">
				
				<div class="park-tile">	
					<div class="park-pic">
			<c:url var="parkDetailsById" value="/ParkDetail?parkcode=${parks.parkCode}" />
	<a href="${parkDetailsById}">
	 <img id="main-photo" src="<c:url value="img/parks/${parks.parkCode.toLowerCase()}.jpg"/>" />
					</a></div>
				
				
					<div class="park-tile-name-description">
					<a href = "<c:url value = "/ParkDetail?parkcode=${parks.parkCode}"/>">
					<h4><c:out value="${parks.parkName}"/></h4></a>
					<br>
					

						
						<p><c:out value="${parks.description}"/></p>
						<p><a href = "<c:url value = "/ParkDetail?parkcode=${parks.parkCode}"/>"><c:out value="Learn More"/></a>
						
					</div>
				
</div>
				
			</c:forEach>
	
</div>
</div>
<%@include file="common/footer.jspf"%>