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
<style>
.scrollable { .dataTables_filter { display:none;
	
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
								<h2 class="text-white pb-2 fw-bold">Employee Attendance
									Approval</h2>
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
										<form:form action="loadAttendance"
											modelAttribute="EmployeeAttendanceApproval" method="get"
											onsubmit="">
											<div class=" row">
												<div class="col-9">
													<div class="form-group row">
														<div class="col-sm-3">
															<label>Start Date</label> <input id="startDate"
																name="startDate" type="date"
																class="form-control form-control-user col-12 foo text-capitalize"
																value="" required /> <span id="div1"></span>
														</div>
														<div class="col-sm-3">
															<label>End Date</label> <input id="endDate"
																name="endDate" type="date"
																class="form-control form-control-user col-12 foo text-capitalize"
																value="" required /> <span id="div2"></span>
														</div>
														<div class="col-sm-3">
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
												</div>
											</div>
											<div class=" row">
												<div class="col-9">
													<div class="form-group row">
														<div class="col-sm-3">
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
														<div class="col-sm-3">
															<label>Employee Name</label> <select
																id="selectEmployeeId" name="employeeId"
																class="form-control form-control-user col-12 foo text-capitalize">
																<option value="all" selected>ALL</option>
															</select> <span id="div2"></span>
														</div>
														<div class="col-sm-3">
															<label class="form-label">Approval Status</label>
															<div class="selectgroup w-100">
																<label class="selectgroup-item"> <input
																	type="radio" id="approved" name="approvalStatus"
																	value="1" class="selectgroup-input"> <span
																	class="selectgroup-button">Approved</span>
																</label> <label class="selectgroup-item"> <input
																	type="radio" checked id="unapproved"
																	name="approvalStatus" value="0"
																	class="selectgroup-input"> <span
																	class="selectgroup-button">Unapproved</span>
																</label>
															</div>
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

										<form:form method="post" action="approveAttendance"
											modelAttribute="approveForm">
											<!-- DataTables Example -->
											<div class="card shadow mb-4">
												<div class="card-header py-3">
													<h6 class="m-0 font-weight-bold text-primary">Attendance
														Approvals</h6>
												</div>
												<div class="card-body">
													<div class="table-responsive">
														<table
															class="display table table-striped table-hover table-bordered"
															id="basic-datatables" width="100%" cellspacing="0">
															<thead>
																<tr>
																	<th style="display: none">Attendance ID</th>
																	<th>Date</th>
																	<th style="display: none">Employee ID</th>
																	<th>Employee Name</th>
																	<th style="display: none">Department ID</th>
																	<th>Department</th>
																	<th style="display: none">Shift ID</th>
																	<th>Shift Name</th>
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
																	<th style="display: none">Employee ID</th>
																	<th>Employee Name</th>
																	<th style="display: none">Department ID</th>
																	<th>Department</th>
																	<th style="display: none">Shift ID</th>
																	<th>Shift Name</th>
																	<th>On Time</th>
																	<th>Off Time</th>
																	<th>Approved</th>
																	<th style="display: none">Company ID</th>
																</tr>
															</tfoot>
															<tbody>

																<c:forEach items="${filteredAttendanceList}"
																	var="attendance" varStatus="status">
																	<tr>

																		<td id="aId" style="display: none"><input
																			id="attendanceId"
																			name="attendances[${status.index}].attendanceId"
																			value="${attendance[0]}" readonly /></td>
																		<td id="dte"><input id="date"
																			name="attendances[${status.index}].date"
																			value="${attendance[1]}" readonly /></td>
																		<td id="eId" style="display: none"><input
																			id="employeeId"
																			name="attendances[${status.index}].employee.empID"
																			value="${attendance[2]}" readonly /></td>
																		<td id="eName"><input id="employeeName"
																			name="employeeName"
																			value="${attendance[3]} ${attendance[4]}" readonly /></td>
																		<td id="dId" style="display: none"><input
																			id="departmentId"
																			name="attendances[${status.index}].departmentId"
																			value="${attendance[5]}" readonly /></td>
																		<td id="dName"><input id="departmentName"
																			name="departmentName" value="${attendance[6]}"
																			readonly /></td>
																		<td id="sId" style="display: none"><input
																			id="shiftId"
																			name="attendances[${status.index}].shiftmaster.shiftId"
																			value="${attendance[7]}" readonly /></td>
																		<td id="sName"><input id="shiftName"
																			name="shiftName" value="${attendance[8]}" readonly /></td>
																		<td id="onT"><input id="onTime"
																			name="attendances[${status.index}].onTime"
																			value="${attendance[9]}" readonly /></td>
																		<td id="offT"><input id="offTime"
																			name="attendances[${status.index}].offTime"
																			value="${attendance[10]}" readonly /></td>
																		<td id="aprvetd"><input type="checkbox"
																			id="aprve"
																			name="attendances[${status.index}].approved"
																			<c:if test="${attendance[11] == 'true'}">checked</c:if>></td>
																		<td class="cId" style="display: none"><input
																			id="companyId"
																			name="attendances[${status.index}].company.comID"
																			value="${attendance[12]}" type="hidden" readonly /></td>
																	</tr>
																</c:forEach>

															</tbody>
														</table>
													</div>
												</div>
											</div>

											<input type="submit" class="btn btn-success btn-sm-3 mr-3"
												value="Approve Attendances" />

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
	<script src="<c:url value='/resources/hrm/ajax/shiftAllocation.js'/>"></script>
	<script src="<c:url value='/resources/hrm/js/shiftAllocation.js'/>"></script>
</body>
</html>