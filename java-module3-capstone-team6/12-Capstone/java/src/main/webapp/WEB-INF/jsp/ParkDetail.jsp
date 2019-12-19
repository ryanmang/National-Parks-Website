<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="common/header.jspf"%>

<title>Park Details</title>
<div class="container">
	<div class="mainbody">
		<div class="grid-container">

			<div class="park-name-and-short-details">
				<h2><c:out value="${park.parkName}"/>,<br><c:out value=" ${park.state}"/>
				</h2>
				<br>

				<div class="short-details">
					<div class="acres"><fmt:formatNumber type="number" groupingUsed="true" value="${park.acreage}"/><c:out value=" Acres"/></div>
					<div class="founded"><c:out value="Founded in ${park.yearFounded}"/></div>
					<div class="entry-fee">
						<c:out value="Entry fee: $${park.entryFee}"/>
						<p>
					</div>
				</div>

				<div class="full-description">
					<p><c:out value="${park.description}"/></p>
				</div>
			</div>
			<div id="details-table">
				<table>
					<tr>
						<td><i class="fas fa-route"></i></td>
						<td><c:out value="Miles of Trail: ${park.milesTrail}"/></td>
					</tr>
					<tr>
						<td><i class="fas fa-mountain"></i></td>
						<td><c:out value="Elevation: ${park.elevation} ft."/></td>
					</tr>
					<tr>
						<td><i class="fas fa-campground"></i></td>
						<td><c:out value="Campsites: ${park.numberSites}"/></td>
					</tr>
					<tr>
						<td><i class="fas fa-sun"></i></td>
						<td><c:out value="Climate: ${park.climate}"/></td>
					</tr>
					<tr>
						<td><i class="fas fa-users"></i></td>
						<td><c:out value="Annual Visitors: "/><fmt:formatNumber type="number" groupingUsed="true" value="${park.annualVisitorCount}"/></td>
					</tr>
					<tr>
						<td><i class="fas fa-paw"></i></td>
						<td><c:out value="Animal Species: ${park.numberSpecies}"/></td>
					</tr>
				</table>
			</div>

			<div class="pic">
				<img src="<c:url value="img/parks/${park.parkCode.toLowerCase()}.jpg"/>" alt="parkimg" class="park-detail-pic" /> <br>
				<p><br><c:out value="${park.quote}"/><br>-----<c:out value="${park.quoteSource}"/>-----</p>
			</div>
			
		</div>
		
		<br>
		
		<div class="forecast">
			<h2><c:out value="5-Day Forecast"/></h2>
		</div>
		
		<div class="allweather">
			<c:forEach var="weather" items="${weathers}">
				<c:set var="parkcode" value="${weather.parkCode}" />
				<c:set var="low" value="${weather.low}" />
				<c:set var="high" value="${weather.high}" />

				<c:set var="day" value="future" />
				<c:set var="weatherpic" value="futurepic" />

				<c:if test="${weather.fiveDay == 1}">
					<c:set var="day" value="today" />
					<c:set var="weatherpic" value="todaypic" />
				</c:if>

				<div class="${day}">
					<h2><c:out value="${weather.forecast.toUpperCase()}"/></h2>
					<p>
					<img src="<c:url value="img/weather/${weather.forecast}.jpg"/>" alt="weatherimg" class="${weatherpic}" /><br>
					
					<div class="high">
						HIGH:
						<c:choose>
							<c:when test="${celcius}">
								<fmt:formatNumber maxFractionDigits="0">
									<c:out value="${(weather.high - 32) * 5 / 9}" />
								</fmt:formatNumber>°C
							</c:when>
							<c:otherwise>
							<fmt:formatNumber maxFractionDigits="0">
								<c:out value="${weather.high}" />
								</fmt:formatNumber>°F
							</c:otherwise>
						</c:choose>
					</div>
					
					<div class="low">
						LOW:
    				    <c:choose>
        				    <c:when test="${celcius}">
								<fmt:formatNumber maxFractionDigits="0">
									<c:out value="${(weather.low - 32) * 5 / 9}" />
								</fmt:formatNumber>°C
							</c:when>
							<c:otherwise>
							<fmt:formatNumber maxFractionDigits="0">
								<c:out value="${weather.low}" />
								</fmt:formatNumber>°F
								
							</c:otherwise>
						</c:choose>
					</div>
					<br>
					<p>
						<c:choose>
							<c:when test="${weather.forecast == 'sunny'}">
								<br>
								<p>
									<c:out value="Pack sunblock!" />
							</c:when>
							<c:when test="${weather.forecast == 'rain'}">
								<br>
								<p>
									<c:out value="Pack rain gear and wear waterproof shoes!" />
							</c:when>
							<c:when test="${weather.forecast == 'thunderstorms'}">
								<br>
								<p>
									<c:out value="Seek shelter and avoid hiking on exposed ridges!" />
							</c:when>
							<c:when test="${weather.forecast == 'snow'}">
								<br>
								<p>
									<c:out value="Pack snowshoes!" />
							</c:when>
							<c:when test="${weather.high > 75}">
								<br>
								<p>
									<c:out value="Bring an extra gallon of water!" />
							</c:when>
							<c:when test="${weather.low < 20 }">
								<br>
								<p>
									<c:out
										value="Warning! Danger of exposure to figid temperatures!" />
							</c:when>
							<c:when test="${weather.high - weather.low > 20 }">
								<br>
								<p>
									<c:out value="Wear breathable layers!" />
							</c:when>
						</c:choose>
				</div>
			</c:forEach>


		</div>
		<c:url var="formAction" value="/ParkDetail" />
		<form method="POST" action="${formAction}">
			<div class="btn-fahrenheit">
				<input type="hidden" name="tempFC" value="Fahrenheit" /> <input
					type="hidden" name="parkCode" value="${park.parkCode}" /> <input
					type="submit" class="btn btn-outline-danger" name="false" value="Fahrenheit" />
			</div>
		</form><p>
		<form method="POST" action="${formAction}">
			<div class="btn-celsius">
				<input type="hidden" name="tempFC" value="Celsius" /> <input
					type="hidden" name="parkCode" value="${park.parkCode}" /> <input
					type="submit" class="btn btn-outline-primary" name="true" value="Celsius" />
			</div>
		</form>
	</div>
</div>



<%@include file="common/footer.jspf"%>