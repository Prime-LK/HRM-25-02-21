<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<html lang="en">
<head>
	<%@include file="../../WEB-INF/jsp/head.jsp"%>
	
	<link href="<c:url value='/resources/hrm/css/CalenderPicker.css'/>" rel="stylesheet">	
	<link href="/resources/hrm/css/fullcalendar.css" rel="stylesheet" />
	<link href="/resources/hrm/css/fullcalendar.print.css" rel="stylesheet" />
	
<style>

#external-events p input {
	margin: 0;
	vertical-align: middle;
}

#calendar {
	/* 		float: right; */
	margin: 0 auto;
	width: 900px;
	background-color: #FFFFFF;
	border-radius: 6px;
	box-shadow: 0 1px 2px #C3C3C3;
}
</style>
	
</head>
<body>
	<div class="wrapper">
		<div class="main-header">
			<!-- Logo Header -->
				<%@include file="../../WEB-INF/jsp/logoHeader.jsp"%>
			<!-- End Logo Header -->
			<!-- Navbar Header -->
				<%@include file="../../WEB-INF/jsp/navbar.jsp"%>
			<!-- End Navbar -->
		</div>
		<!-- Sidebar -->
			<%@include file="../../WEB-INF/jsp/slideBar.jsp"%>
		<!-- End Sidebar -->
		<div class="main-panel">
			<div class="content">
				<div class="panel-header bg-primary-gradient">
					<div class="page-inner py-3">
						<div class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
							<div class="col-xl col-lg">
								 <h2 class="text-white pb-2 fw-bold">Create Calendar</h2>
							</div>

						</div>
					</div>
				</div>
				
				<div class="page-inner mt--5">	
					<div class="container-fluid">

			              <div class="card shadow mb-4 border-left-primary">
			                <div class="card-body">
			                
								<div class="row">
									<div class="col-xl col-md-6 mb-4">

					                
									<form:form action="saveCalander" method="POST"
										onSubmit="return validateForm()" id="calander"
										modelAttribute="calander">


										<div id='wrap'>

											<div id='calendar'></div>

											<div style='clear: both'></div>
										</div>

										<div class=" form-group row">
											<div class="col-lg-4">
												<label>Start date</label>
												<form:input type="date" class="form-control form-control-sm" id="pickYear" path="" required="true" />
											</div>
											<div class="col-lg-4">
												<label>End Date</label>
												<form:input type="date" class="form-control form-control-sm" id="endYear" path="" onchange="load()"  required="true" />
											</div>
										</div>
										<div class=" form-group row">
											<div class="col-lg">
												<input type="submit" class="btn btn-success btn-sm mr-2"
																	value="Create year">
											</div>
										</div>
											<div class="form-group row">
														<div class="col-12" hidden="true">
															<div class="scrollable" hidden="true">
																<table id="tableMoSaDetails" class="table table-hover"
																	cellspacing="0" width="100%" hidden="true">
																	<thead>
																	</thead>
																	<tbody>
																	</tbody>
																</table>
															</div>
														</div>
													</div>
										<br>
										<div id="app"></div>
									</form:form>

									</div>
									<div class="col-xl col-md-6 mb-4">
									

																			
									</div>
								</div>

			                </div>
			              </div>
			            
					</div>	
				</div>
				
			</div>	
			<%@include file="../../WEB-INF/jsp/footer.jsp"%>			
		</div>
	</div>
<%@include file="../../WEB-INF/jsp/commJs.jsp"%>


<script src="<c:url value='/resources/hrm/js/CalenderPicker.js'/>"></script>

<script src="/resources/hrm/js/jquery-1.10.2.js"
	type="text/javascript"></script>
<script src="/resources/hrm/js/jquery-ui.custom.min.js"
	type="text/javascript"></script>
<script src="/resources/hrm/js/fullcalendar.js"
	type="text/javascript"></script>

<script>
function load() {

	var s = document.getElementById("pickYear").value;
	var start = new Date(s);
	var x = start.getFullYear();
	var e = document.getElementById("endYear").value;
	var end = new Date(e);

	while (start <= end) {
		start.setDate(start.getDate() + 1);

		function convert(str) {
			var date = new Date(start), mnth = ("0" + (date.getMonth() + 1))
					.slice(-2), day = ("0" + date.getDate()).slice(-2);
		
			return [ date.getFullYear(), mnth, day ].join("-");
			
		}
		var y = convert("Thu Jun 09 2011 00:00:00 GMT+0530 (India Standard Time)");
		
		console.log(x);

		var result = "<tr><td><input name='date' type'text' value=" + y + " />"
				+ "<td><input name='description' type'text' value="
				+ "Working Day" + " />"
				+ "<td><input name='types' type'text' value=" + "full" + " />"
				+ "<td><input name='calander_ID' type'text' value=" + x 
				+ " />" + "</td></tr>";
		$("#tableMoSaDetails tbody").append(result);

	}

}
</script>

</body>
</html>