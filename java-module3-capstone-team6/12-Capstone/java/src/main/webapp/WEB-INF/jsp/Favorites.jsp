<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="common/header.jspf"%>

<title>Most Popular Parks</title>
<div class="container">
<div class="mainbody">
<div class="survey-favorites">
<h1><c:out value="Survey Favorites"/></h1></div>
<div class="favorites-text">

<c:forEach var="park" items="${parks}">
<div class="fav-pic">
<img id="fav-photo" src="<c:url value="img/parks/${park.parkCode.toLowerCase()}.jpg"/>" />
</div>
<div class ="info-favorites">
	<p><h5><c:out value="${park.parkName}"/></h5></p>
	<p><c:out value="Surveys: ${park.parkCount}"/></p>
	<p><c:out value="Annual Visitors: "/><fmt:formatNumber type="number" groupingUsed="true" value="${park.annualVisitorCount}"/></p>
	<p><c:out value="Entry Fee: $${park.entryFee}"/></p>
	</div>

</c:forEach>
</div>
</div>
</div>





<%@include file="common/footer.jspf"%>