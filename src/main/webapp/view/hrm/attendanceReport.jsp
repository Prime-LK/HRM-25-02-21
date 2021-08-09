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
							<div class="col-xl-4 col-lg-2">
								<h2 class="text-white pb-2 fw-bold">Manual Attendance
									Report</h2>
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
							<div class="col-sm-3">
								<div class="card">
									<div class="card-body">

										<!-- Page Heading -->
										<h1 class="h3 mb-4 text-gray-800"></h1>
										<form:form action="generateAttendanceReport"
											modelAttribute="" method="get"
											onsubmit="return validateForm()">
											<div class="form-group row">
												<div class="col-sm-12">
													<label>Start Date</label> <input id="startDate"
														name="startDate" type="date"
														class="form-control form-control-user col-12 foo text-capitalize"
														value="" required /> <span id="div1"></span>
												</div>
											</div>
											<div class="form-group row">
												<div class="col-sm-12">
													<label>End Date</label> <input id="endDate" name="endDate"
														type="date"
														class="form-control form-control-user col-12 foo text-capitalize"
														value="" required /> <span id="div2"></span>
												</div>
											</div>
											<div class="form-group row">
												<div class="col-sm-12">
													<label>Shift Name</label> <select id="shiftId"
														name="shiftId"
														class="form-control form-control-user col-12 foo text-capitalize">
														<option value="all" selected>ALL</option>
														<c:forEach items="${shiftList}" var="s">
															<option value="${s.shiftId}">${s.description}</option>
														</c:forEach>
													</select> <span id="div1"></span>
												</div>
											</div>
											<div class="form-group row">
												<div class="col-sm-12">
													<label>Department</label> <select id="selectDepartment"
														name="departmentId"
														class="form-control form-control-user col-12 foo text-capitalize"
														onchange="loadEmployeesByDepartment()">
														<option value="all" selected>ALL</option>
														<c:forEach items="${depList}" var="d">
															<option value="${d.depID}">${d.department}</option>
														</c:forEach>
													</select> <span id="div1"></span>
												</div>
											</div>
											<div class="form-group row">
												<div class="col-sm-12">
													<label>Employee Name</label> <select id="selectEmployeeId"
														name="employeeId"
														class="form-control form-control-user col-12 foo text-capitalize">
														<option value="all" selected>ALL</option>
													</select> <span id="div2"></span>
												</div>
											</div>
											<br>
											<div class="form-group row">
												<div class="col-sm-12">
													<input type="submit" class="btn btn-primary btn-sm-6"
														value="Generate Attendance Report" />
												</div>
											</div>


										</form:form>

										<br>

									</div>
								</div>
							</div>
							<div class="col-sm-9">

								<c:if test="${pdfViewEq != null }">
									<embed type="application/pdf"
										src="data:application/pdf;base64,${pdfViewEq}"
										style="height: 70vh; width: 100%">
									</embed>
								</c:if>
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
	<script src="<c:url value='/resources/hrm/ajax/shiftAllocation.js'/>"></script>
	<script src="<c:url value='/resources/hrm/js/shiftAllocation.js'/>"></script>
</body>
</html>