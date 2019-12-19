<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="common/header.jspf"%>


<title>Survey</title>
<div class="container">
<div class="mainbody">

<c:url value="/Survey" var="surveyUrl" />
<div class="survey-text">
<h2><c:out value="National Parks Geek Survey"/></h2>
<img id="big-logo" src="<c:url value="img/npgeeklogo.png"/>" />
<div class="heading-text">
<p><h4><c:out value="Your input will help us determine how best to suggest which National Parks are suited for our visitors."/></h4>

<p><c:out value="By telling us a little about yourself, you can help others find the park they'll enjoy most. Your information will stay private,
and you will not receive any emails from us or outside sources. Thank you for taking the time to help!"/></p>

<div class="form-body">
<form:form modelAttribute="survey" action="${surveyUrl }" method="POST">

	<br>
	<label for="parkCode"><h5><c:out value="Favorite National Park:"/></h5></label>
	<form:select path="parkCode" class="form-control">
		<form:option value=""><c:out value="--Please Select a Park--"/></form:option>
		<form:option value="CVNP"><c:out value="Cuyahoga Valley National Park"/></form:option>
		<form:option value="ENP"><c:out value="Everglades National Park"/></form:option>
		<form:option value="GCNP"><c:out value="Grand Canyon National Park"/></form:option>
		<form:option value="GNP"><c:out value="Glacier National Park"/></form:option>
		<form:option value="GSMNP"><c:out value="Great Smoky Mountains National Park"/></form:option>
		<form:option value="GTNP"><c:out value="Grand Teton National Park"/></form:option>
		<form:option value="MRNP"><c:out value="Mount Rainier National Park"/></form:option>
		<form:option value="RMNP"><c:out value="Rocky Mountain National Park"/></form:option>
		<form:option value="YNP"><c:out value="Yellowstone National Park"/></form:option>
		<form:option value="YNP2"><c:out value="Yosemite National Park"/></form:option>
	</form:select>

	<form:errors path="parkCode" cssClass="needs-validation" />
	<br>

	<label for="email"><h5><c:out value="Your Email:"/></h5></label>
	<form:input path="email" class="form-control" />

	<form:errors path="email" cssClass="needs-validation" />
	<br>

	<label for="state"><h5><c:out value="State of Residence:"/></h5></label>
	<form:select path="state" class="form-control">
		<form:option value=""><c:out value="--Please Select Your State of Residence--"/></form:option>
		<form:option value="Alabama"><c:out value="Alabama"/></form:option>
		<form:option value="Alaska"><c:out value="Alaska"/></form:option>
		<form:option value="Arizona"><c:out value="Arizona"/></form:option>
		<form:option value="Arkansas"><c:out value="Arkansas"/></form:option>
		<form:option value="California"><c:out value="California"/></form:option>
		<form:option value="Colorado"><c:out value="Colorado"/></form:option>
		<form:option value="Connecticut"><c:out value="Connecticut"/></form:option>
		<form:option value="Delaware"><c:out value="Delaware"/></form:option>
		<form:option value="District of Columbia"><c:out value="District of Columbia"/></form:option>
		<form:option value="Florida"><c:out value="Florida"/></form:option>
		<form:option value="Georgia"><c:out value="Georgia"/></form:option>
		<form:option value="Guam"><c:out value="Guam"/></form:option>
		<form:option value="Hawaii"><c:out value="Hawaii"/></form:option>
		<form:option value="Idaho"><c:out value="Idaho"/></form:option>
		<form:option value="Illinois"><c:out value="Illinois"/></form:option>
		<form:option value="Indiana"><c:out value="Indiana"/></form:option>
		<form:option value="Iowa"><c:out value="Iowa"/></form:option>
		<form:option value="Kansas"><c:out value="Kansas"/></form:option>
		<form:option value="Kentucky"><c:out value="Kentucky"/></form:option>
		<form:option value="Louisiana"><c:out value="Louisiana"/></form:option>
		<form:option value="Maine"><c:out value="Maine"/></form:option>
		<form:option value="Maryland"><c:out value="Maryland"/></form:option>
		<form:option value="Massachusetts"><c:out value="Massachusetts"/></form:option>
		<form:option value="Michigan"><c:out value="Michigan"/></form:option>
		<form:option value="Minnesota"><c:out value="Minnesota"/></form:option>
		<form:option value="Mississippi"><c:out value="Mississippi"/></form:option>
		<form:option value="Missouri"><c:out value="Missouri"/></form:option>
		<form:option value="Montana"><c:out value="Montana"/></form:option>
		<form:option value="Nebraska"><c:out value="Nebraska"/></form:option>
		<form:option value="Nevada"><c:out value="Nevada"/></form:option>
		<form:option value="New Hampshire"><c:out value="New Hampshire"/></form:option>
		<form:option value="New Jersey"><c:out value="New Jersey"/></form:option>
		<form:option value="New Mexico"><c:out value="New Mexico"/></form:option>
		<form:option value="New York"><c:out value="New York"/></form:option>
		<form:option value="North Carolina"><c:out value="North Carolina"/></form:option>
		<form:option value="North Dakota"><c:out value="North Dakota"/></form:option>
		<form:option value="Northern Marianas Islands"><c:out value="Northern Marianas Islands"/></form:option>
		<form:option value="Ohio"><c:out value="Ohio"/></form:option>
		<form:option value="Oklahoma"><c:out value="Oklahoma"/></form:option>
		<form:option value="Oregon"><c:out value="Oregon"/></form:option>
		<form:option value="Pennsylvania"><c:out value="Pennsylvania"/></form:option>
		<form:option value="Puerto Rico"><c:out value="Puerto Rico"/></form:option>
		<form:option value="Rhode Island"><c:out value="Rhode Island"/></form:option>
		<form:option value="South Carolina"><c:out value="South Carolina"/></form:option>
		<form:option value="South Dakota"><c:out value="South Dakota"/></form:option>
		<form:option value="Tennessee"><c:out value="Tennessee"/></form:option>
		<form:option value="Texas"><c:out value="Texas"/></form:option>
		<form:option value="Utah"><c:out value="Utah"/></form:option>
		<form:option value="Vermont"><c:out value="Vermont"/></form:option>
		<form:option value="Virginia"><c:out value="Virginia"/></form:option>
		<form:option value="Virgin Islands"><c:out value="Virgin Islands"/></form:option>
		<form:option value="Washington"><c:out value="Washington"/></form:option>
		<form:option value="West Virginia"><c:out value="West Virginia"/></form:option>
		<form:option value="Wisconsin"><c:out value="Wisconsin"/></form:option>
		<form:option value="Wyoming"><c:out value="Wyoming"/></form:option>
	</form:select>
	
	<br>
	<form:errors path="state" cssClass="needs-validation" />
	<br>
	
	<label for="activityLevel"><h5><c:out value="Your Average Activity Level:"/></h5></label>
	<br>
	<div class="custom-control custom-radio">
	<form:radiobutton path="activityLevel" value="inactive"	class="custom-control-input" id="inactive" name="radio"/>
	<label class="custom-control-label" for="inactive"><c:out value="Inactive"/></label>
	</div>
	<div class="custom-control custom-radio">
	<form:radiobutton path="activityLevel" value="sedentary" class="custom-control-input" id="sedentary" name="radio"/>
	<label class="custom-control-label" for="sedentary"><c:out value="Sedentary"/></label>
	</div>
	<div class="custom-control custom-radio">
	<form:radiobutton path="activityLevel" value="active" class="custom-control-input" id="active" name="radio"/>
	<label class="custom-control-label" for="active"><c:out value="Active"/></label>
	</div>
	<div class="custom-control custom-radio">
	<form:radiobutton path="activityLevel" value="extremelyactive" class="custom-control-input" id="extremelyactive" name="radio"/>
	<label class="custom-control-label" for="extremelyactive"><c:out value="Extremely Active"/></label>
	</div>
	<form:errors path="activityLevel" cssClass="needs-validation" />
	<br>
	<p>
	<input type="submit" class="btn btn-outline-warning btn-lg" value="Submit Survey" />

</form:form>
</div>

</div>
</div>
</div>
</div>


<%@include file="common/footer.jspf"%>