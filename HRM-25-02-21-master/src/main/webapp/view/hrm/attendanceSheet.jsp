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
							<div class="col-xl-4 col-lg-4">
								<h2 class="text-white pb-2 fw-bold">Attendance Sheet</h2>
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
										<form:form action="getSheet" modelAttribute="AttendanceSheet"
											method="get" onsubmit="return validateForm()">
											<div class=" row">
												<div class="col-9">
													<div class="form-group row">
														<div class="col-sm-4">
															<label>Start Date</label><input id="startDate"
																name="startDate" type="date" class="form-control"
																value="" required onchange="" required="true" />
														</div>
														<div class="col-sm-4">
															<label>End Date</label><input id="endDate" name="endDate"
																type="date" class="form-control" value="" required
																onchange="" required="true" />
														</div>
													</div>
												</div>
											</div>
											<div class=" row">
												<div class="col-9">
													<div class="form-group row">
														<div class="col-sm-4">
															<label>Department</label> <select id="selectDepartment"
																name=""
																class="form-control form-control-user col-12 foo text-capitalize"
																onchange="loadEmployeesByDepartment()">
																<option value="" disabled selected>- Select
																	Department -</option>
																<c:forEach items="${depList}" var="d">
																	<option value="${d.depID}">${d.department}</option>
																</c:forEach>
															</select>
														</div>
														<div class="col-sm-4">
															<label>Employee Name</label> <select
																id="selectEmployeeId" name="employeeId"
																class="form-control form-control-user col-12 foo text-capitalize"
																required="true" onchange="">
																<option value="" disabled selected>- Select
																	Employee -</option>
															</select>
														</div>
													</div>
												</div>
											</div>
											<div class=" row">
												<div class="col-9">
													<div class="form-group row">
														<div class="col-sm-9">
															<input type="submit"
																class="btn btn-primary btn-sm-3 mr-3"
																value="Generate Attendance Sheet" /> <input
																type="reset" class="btn btn-danger btn-sm-3 mr-3"
																value="Reset" onclick="resetEmployeeList()" />
														</div>
														<div class="col-sm-3"></div>
													</div>
												</div>
											</div>

										</form:form>

										<!-- DataTables Example -->
										<div class="card shadow mb-4">
											<div class="card-header py-3">
												<h6 class="m-0 font-weight-bold text-primary">Attendance
													Sheet</h6>
											</div>
											<div class="card-body">
												<div class="table-responsive">
													<table
														class="display table table-striped table-hover table-bordered"
														id="basic-datatables" width="100%" cellspacing="0">
														<thead>
															<tr>
																<th>Date</th>
																<th>Weekday</th>
																<th>Day Type</th>
																<th>On Time</th>
																<th>Off Time</th>
																<th>Worked Time</th>
																<th>Over Time</th>
																<th>Short Time</th>
																<th>Description</th>
															</tr>
														</thead>
														<tfoot>
															<tr>
																<th>Date</th>
																<th>Weekday</th>
																<th>Day Type</th>
																<th>On Time</th>
																<th>Off Time</th>
																<th>Worked Time</th>
																<th>Over Time</th>
																<th>Short Time</th>
																<th>Description</th>
															</tr>
														</tfoot>
														<tbody>

															<c:forEach items="${attendanceSheet}" var="sheet">
																<tr>
																	<td>${sheet[0]}</td>
																	<td>${sheet[1]}</td>
																	<td>${sheet[2]}</td>
																	<td>${sheet[3]}</td>
																	<td>${sheet[4]}</td>
																	<td>${sheet[5]}</td>
																	<td>${sheet[6]}</td>
																	<td>${sheet[7]}</td>
																	<td>${sheet[8]}</td>
																</tr>
															</c:forEach>

														</tbody>
													</table>
												</div>
											</div>
										</div>

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

	<script
		src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/jquery-ui.min.js"></script>
	<script src="<c:url value='/resources/hrm/js/employeeAttendance.js'/>"></script>
	<script
		src="<c:url value='/resources/hrm/ajax/employeeAttendance.js'/>"></script>

</body>
</html>