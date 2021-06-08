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
								<h2 class="text-white pb-2 fw-bold">Attendance Log</h2>
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
										<form:form action="loadAttendanceLog"
											modelAttribute="AttendanceLog" method="get"
											onsubmit="return validateForm()">
											<div class=" row">
												<div class="col-9">
													<div class="form-group row">
														<div class="col-sm-4">
															<label>Start Date</label> <input id="startDate"
																name="startDate" type="date"
																class="form-control form-control-user col-12 foo text-capitalize"
																value="" required /> <span id="div1"></span>
														</div>
														<div class="col-sm-4">
															<label>End Date</label> <input id="endDate"
																name="endDate" type="date"
																class="form-control form-control-user col-12 foo text-capitalize"
																value="" required /> <span id="div2"></span>
														</div>
														<div class="col-sm-4">
															<label>Shift Name</label> <select id="shiftId"
																name="shiftId"
																class="form-control form-control-user col-12 foo text-capitalize">
																<option value="All" selected>- All Shifts -</option>
																<c:forEach items="${shiftList}" var="s">
																	<option value="${s.shiftId}">${s.description}</option>
																</c:forEach>
															</select> <span id="div1"></span>
														</div>
													</div>
												</div>
											</div>
											<div class=" row">
												<div class="col-9">
													<div class="form-group row">
														<div class="col-sm-4">
															<label>Department</label> <select id="selectDepartment"
																name="departmentId"
																class="form-control form-control-user col-12 foo text-capitalize"
																onchange="loadEmployeesByDepartment()">
																<option value="All" selected>- All Departments
																	-</option>
																<c:forEach items="${depList}" var="d">
																	<option value="${d.depID}">${d.department}</option>
																</c:forEach>
															</select> <span id="div1"></span>
														</div>
														<div class="col-sm-4">
															<label>Employee Name</label> <select
																id="selectEmployeeId" name="employeeId"
																class="form-control form-control-user col-12 foo text-capitalize">
																<option value="All" selected>- All Employees -</option>
															</select> <span id="div2"></span>
														</div>
													</div>
												</div>
											</div>
											<div class=" row">
												<div class="col-9">
													<div class="form-group row">
														<div class="col-sm-6">
															<input type="submit"
																class="btn btn-primary btn-sm-3 mr-3"
																value="Load Attendances" /> <input type="reset"
																class="btn btn-danger btn-sm-3 mr-3" value="Reset" />
														</div>
													</div>
												</div>
											</div>

										</form:form>

										<br>

										<!-- DataTables Example -->
										<div class="table-responsive">
											<table
												class="display table table-striped table-hover table-bordered"
												id="basic-datatables" width="100%" cellspacing="0">
												<thead>
													<tr>
														<th style="display: none">Attendance ID</th>
														<th>Date</th>
														<th style="display: none">Shift ID</th>
														<th>Shift Name</th>
														<th>Department</th>
														<th style="display: none">Employee ID</th>
														<th>Employee Name</th>
														<th>On Time</th>
														<th>Off Time</th>
														<th>Approved</th>
														<th style="display: none">Company ID</th>
													</tr>
												</thead>
												<tfoot>
													<tr>
														<th style="display: none">Attendance ID</th>
														<th>Date</th>
														<th style="display: none">Shift ID</th>
														<th>Shift Name</th>
														<th>Department</th>
														<th style="display: none">Employee ID</th>
														<th>Employee Name</th>
														<th>On Time</th>
														<th>Off Time</th>
														<th>Approved</th>
														<th style="display: none">Company ID</th>
													</tr>
												</tfoot>
												<tbody>

													<c:forEach items="${attendanceList}" var="attendance">
														<tr>
															<td style="display: none">${attendance[0]}</td>
															<td>${attendance[1]}</td>
															<td style="display: none">${attendance[2]}</td>
															<td>${attendance[6]}</td>
															<td>${attendance[4]}</td>
															<td style="display: none">${attendance[5]}</td>
															<td>${attendance[3]}</td>
															<td>${attendance[7]}</td>
															<td>${attendance[8]}</td>
															<td><c:if test="${attendance[9] == 'true'}">Yes</c:if>
																<c:if test="${attendance[9] == 'false'}">No</c:if></td>
															<td style="display: none">${attendance[10]}</td>
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
			<%@include file="../../WEB-INF/jsp/footer.jsp"%>
		</div>
	</div>
	<%@include file="../../WEB-INF/jsp/commJs.jsp"%>

	<!-- Datatable -->
	<script src="<c:url value='/resources/hrm/ajax/datatable.js'/>"></script>
	<script	src="<c:url value='/resources/hrm/ajax/employeeAttendance.js'/>"></script>

</body>
</html>