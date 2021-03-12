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
.dataTables_filter {
	display: none;
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
		<!-- Sidebar -->
		<%@include file="../../WEB-INF/jsp/sideBar.jsp"%>
		<!-- End Sidebar -->
		<div class="main-panel">
			<div class="content">
				<div class="panel-header bg-primary-gradient">
					<div class="page-inner py-3">
						<div
							class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
							<div class="col-xl-4 col-lg-4">
								<h2 class="text-white pb-2 fw-bold">Attendance Revise
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
										<form:form action="loadAttendanceRevises"
											modelAttribute="AttendanceReviseApproval" method="get"
											onsubmit="return validateForm()">
											<div class=" row">
												<div class="col-9">
													<div class="form-group row">
														<div class="col-sm-4">
															<label>Start Date</label> <input id="startDate"
																name="startDate" type="date"
																class="form-control form-control-user col-12 foo text-capitalize"
																value="" required />
														</div>
														<div class="col-sm-4">
															<label>End Date</label> <input id="endDate"
																name="endDate" type="date"
																class="form-control form-control-user col-12 foo text-capitalize"
																value="" required />
														</div>
														<div class="col-sm-4">
															<label class="form-label">Approval Status</label>
															<div class="selectgroup w-100">
																<label class="selectgroup-item"> <input
																	type="radio" id="approved" name="approvalStatus"
																	value="1" class="selectgroup-input"> <span
																	class="selectgroup-button">Approved</span>
																</label> <label class="selectgroup-item"> <input
																	type="radio" id="unapproved" name="approvalStatus"
																	value="0" class="selectgroup-input"> <span
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
														<div class="col-sm-4">
															<input type="submit"
																class="btn btn-primary btn-sm-3 mr-3"
																value="Load Revises" /> <input type="reset"
																class="btn btn-danger btn-sm-3 mr-3" value="Reset" />
														</div>
														<div class="col-sm-4"></div>
													</div>
												</div>
											</div>

										</form:form>

										<form:form method="post" action="approveAttendanceRevises"
											modelAttribute="approveAttendanceRevisesForm">
											<!-- DataTables Example -->
											<div class="card shadow mb-4">
												<div class="card-header py-3">
													<h6 class="m-0 font-weight-bold text-primary">Attendance
														Revise Approval</h6>
												</div>
												<div class="card-body">
													<div class="table-responsive">
														<table
															class="display table table-striped table-hover table-bordered"
															id="basic-datatables" width="100%" cellspacing="0">
															<thead>
																<tr>
																	<th style="display: none">Revise ID</th>
																	<th>Submit Date</th>
																	<th style="display: none">Attendance ID</th>
																	<th>Department</th>
																	<th style="display: none">Employee ID</th>
																	<th>Employee Name</th>
																	<th style="display: none">Shift ID</th>
																	<th>Shift Name</th>
																	<th>Date</th>
																	<th>On Time</th>
																	<th>Off Time</th>
																	<th>New On Time</th>
																	<th>New Off Time</th>
																	<th>Remark</th>
																	<th>Approved</th>
																	<th style="display: none"></th>
																</tr>
															</thead>
															<tfoot>
																<tr>
																	<th style="display: none">Revise ID</th>
																	<th>Submit Date</th>
																	<th style="display: none">Attendance ID</th>
																	<th>Department</th>
																	<th style="display: none">Employee ID</th>
																	<th>Employee Name</th>
																	<th style="display: none">Shift ID</th>
																	<th>Shift Name</th>
																	<th>Date</th>
																	<th>On Time</th>
																	<th>Off Time</th>
																	<th>New On Time</th>
																	<th>New Off Time</th>
																	<th>Remark</th>
																	<th>Approved</th>
																	<th style="display: none"></th>
																</tr>
															</tfoot>
															<tbody>

																<c:forEach items="${filteredRevisesList}" var="revise"
																	varStatus="status">
																	<tr>
																		<td class="rId" style="display: none"><input
																			id="reviseId"
																			name="attendanceRevises[${status.index}].reviseId"
																			value="${revise[0]}" readonly /></td>
																		<td class="sDte"><input id="submitDate"
																			name="attendanceRevises[${status.index}].submitDate"
																			value="${revise[1]}" readonly /></td>
																		<td class="aId" style="display: none"><input
																			id="attendanceId"
																			name="attendanceRevises[${status.index}].attendanceId"
																			value="${revise[2]}" readonly /></td>
																		<td class="dep"><input id="department"
																			name="department" value="${revise[3]}" readonly /></td>
																		<td style="display: none"><input class="eId"
																			id="employeeId"
																			name="attendanceRevises[${status.index}].employeeId"
																			value="${revise[4]}" readonly /></td>
																		<td class="eName"><input id="employeeName"
																			name="employeeName" value="${revise[5]}" readonly /></td>
																		<td style="display: none"><input class="sId"
																			id="shiftId"
																			name="attendanceRevises[${status.index}].shiftId"
																			value="${revise[6]}" readonly /></td>
																		<td class="sName"><input id="shiftName"
																			name="shiftName" value="${revise[7]}" readonly /></td>
																		<td><input class="dte" id="date"
																			name="attendanceRevises[${status.index}].date"
																			value="${revise[8]}" readonly /></td>
																		<td class="onT"><input id="onTime"
																			name="attendanceRevises[${status.index}].onTime"
																			value="${revise[9]}" readonly /></td>
																		<td class="ofT"><input id="offTime"
																			name="attendanceRevises[${status.index}].offTime"
																			value="${revise[10]}" readonly /></td>
																		<td><input class="nOnT" id="newOnTime"
																			name="attendanceRevises[${status.index}].newOnTime"
																			value="${revise[11]}" readonly /></td>
																		<td><input class="nOfT" id="newOffTime"
																			name="attendanceRevises[${status.index}].newOffTime"
																			value="${revise[12]}" readonly /></td>
																		<td class="rmk"><input id="remark"
																			name="attendanceRevises[${status.index}].remark"
																			value="${revise[13]}" readonly /></td>
																		<td class="aprvetd"><input type="checkbox"
																			id="aprve"
																			name="attendanceRevises[${status.index}].approved"
																			<c:if test="${revise[14] == 'true'}">checked</c:if>></td>
																		<td class="cId" style="display: none"><input
																			id="companyId"
																			name="attendanceRevises[${status.index}].companyId"
																			value="${revise[15]}" type="hidden" readonly /></td>
																	</tr>
																</c:forEach>

															</tbody>
														</table>
													</div>
												</div>
											</div>

											<input type="submit" class="btn btn-success btn-sm-3 mr-3"
												value="Approve Revises" />

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

	<!-- Page level custom scripts -->
	<script src="<c:url value='/resources/hrm/js/employeeAttendance.js'/>"></script>
	<script
		src="<c:url value='/resources/hrm/ajax/employeeAttendance.js'/>"></script>

	<!-- Datatable -->
	<script src="<c:url value='/resources/hrm/ajax/datatable.js'/>"></script>


</body>
</html>