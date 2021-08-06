<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<html lang="en">
<head>
<%@include file="../../WEB-INF/jsp/head.jsp"%>

<style>
.vidSty {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 30px;
	font-weight: bold;
	color: #02d41b;
}

.textred {
	font-family: Arial, Helvetica, sans-serif;
	border: 0px solid #b30000;
	font-size: 14px;
	font-weight: bold;
	text-align: center;
	color: #2c03fc;
}

.fontst {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	height: 30px;
}

.l-fontst {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	height: 5px;
	margin-top: 0px;
}

.iconali {
	position: absolute;
	top: 6px;
	right: -7px;
}

.capCam {
	height: 100px;
	width: 210px;
}

.cursiz {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	width: 48px;
	color: #ff8000;
}

.amt {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	width: 60px;
	color: blue;
	text-align: right;
}

.iconstyle {
	width: 7%;
	color: blue';
}

.icon-pre-ve {
	width: 150%;
}
</style>

</head>
<body onload="checkStatusofDropdowns();">
	<div class="wrapper">
		<div class="main-header">
			<!-- Logo Header -->
			<%@include file="../../WEB-INF/jsp/logoHeader.jsp"%>
			<!-- End Logo Header -->
			<!-- Navbar Header -->
			<%@include file="../../WEB-INF/jsp/navbar.jsp"%>
			<!-- End Navbar -->
		</div>
		<!-- slideBar -->
		<%@include file="../../WEB-INF/jsp/slideBar.jsp"%>
		<!-- End slideBar -->
		<div class="main-panel">
			<div class="content">
				<div class="panel-header bg-primary-gradient">
					<div class="page-inner py-3">
						<div
							class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
							<div class="col-xl-2 col-lg-2">
								<h2 class="text-white pb-2 fw-bold">Update Employee Attendance</h2>
							</div>
							<div class="col-xl-2 col-lg-2"></div>
							<div class="ml-md-auto py-2 py-md-4"></div>

							<div class="ml-md-auto py-2 py-md-4"></div>
						</div>
					</div>
				</div>

				<div class="page-inner mt--5">
					<div class="container-fluid">


						<div class="row">
							<div class="col-md-12">
								<div class="card">
									<div class="card-body">

										<!-- Page Heading -->
										<h1 class="h3 mb-4 text-gray-800"></h1>
										<c:if test="${success ==1}">
											<div class="alert alert-success alert-dismissible">
												<button type="button" class="close" data-dismiss="alert">&times;</button>
												<strong>Success!</strong> Data Successfully Saved.
											</div>
										</c:if>
										<c:if test="${success ==0}">
											<div class="alert alert-danger alert-dismissible">
												<button type="button" class="close" data-dismiss="alert">&times;</button>
												<strong>Warning!</strong>Something went wrong ! Please try
												again!
											</div>
										</c:if>
										<form:form action="updateEmployeeAttendance" modelAttribute="updateEmployeeAttendance"
											onsubmit="return validateForm()" method="post">
											<div class=" row">
												<div class="col-9">
													<div class="form-group row">
														<div class="col-sm-3">
															<label>Date</label>
															<form:input id="date" path="date"
																type="text" class="form-control" value=""
																required="true" readonly="true" onchange="" />
															<span id="div1"></span>
														</div>
														<div class="col-sm-3">
															<label>Attendance ID</label>
															<form:input id="attendanceId" path="attendanceId"
																type="text" class="form-control" value=""
																required="true" readonly="true" onchange="" />
															<span id="div1"></span>
														</div>
													</div>
												</div>
											</div>
											<div class=" row">
												<div class="col-9">
													<div class="form-group row">
														<div class="col-sm-3">
															<label>Department</label>
															<form:input id="departmentId" path="departmentId"
																type="text" class="form-control" value=""
																required="true" readonly="true" onchange="" />
															<span id="div1"></span>
														</div>
														<div class="col-sm-3">
															<label>Employee</label>
															<form:input id="employeeId"
																path="employee.empID" type="text"
																class="form-control" value=""
																required="true" readonly="true" onchange="" />
															<span id="div1"></span>
														</div>
														<div class="col-sm-1">
															<form:input type="hidden" id="companyId"
																path="company.comID"
																value="" />
														</div>
													</div>
												</div>
											</div>
											<div class=" row">
												<div class="col-9">
													<div class="form-group row">
														<div class="col-sm-3">
															<label>Shift</label> <form:select id="shiftId"
																path="shiftmaster.shiftId"
																class="form-control text-capitalize" required="true"
																readonly="true" onchange="">
																<form:option value="" selected="true">SELECT</form:option>
																<c:forEach items="${shiftList}" var="s">
																	<form:option value="${s.shiftId}">${s.description}</form:option>
																</c:forEach>
															</form:select>
														</div>
														<div class="col-sm-3">
															<label>On Time</label> <form:input id="onTime" path="onTime"
																type="time" class="form-control" value=""
																required="true" onchange="" />
														</div>
														<div class="col-sm-3">
															<label>Off Time</label> <form:input id="endTime" path="offTime"
																type="time" class="form-control" value=""
																required="true" onchange="" />
														</div>
													</div>
												</div>
											</div>
											<div class=" row">
												<div class="col-9">
													<div class="form-group row">
														<div class="col-sm-12">
															<input type="submit"
																class="btn btn-primary btn-sm-3 mr-3"
																value="Update Shift" /> <input type="reset"
																class="btn btn-danger btn-sm-3 mr-3" value="Reset" />
														</div>
													</div>
												</div>
											</div>

										</form:form>

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

	<!-- Datatable -->
	<script src="<c:url value='/resources/hrm/ajax/datatable.js'/>"></script>

	<!-- Page level custom scripts -->
	<script src="<c:url value='resources/hrm/js/shiftAllocation.js'/>"></script>
	<script src="<c:url value='resources/hrm/ajax/shiftAllocation.js'/>"></script>

</body>
</html>