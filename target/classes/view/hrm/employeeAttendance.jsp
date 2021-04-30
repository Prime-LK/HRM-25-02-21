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
								<h2 class="text-white pb-2 fw-bold">Manual Employee
									Attendance</h2>
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
										<form:form action="saveAttendance"
											modelAttribute="EmployeeAttendance" method="post">
											<div class=" row">
												<div class="col-9">
													<div class="form-group row">
														<div class="col-sm-3">
															<label>Date</label> <input id="date" name="date"
																placeholder="dd-mm-yyyy" type="date"
																class="form-control" value=""
																onchange="loadEmployeeShiftDetails(); loadAttendanceRecords()"
																required="true" />
														</div>
														<div class="col-sm-3">
															<label>Shift</label> <select id="shiftId" name="shiftId"
																class="form-control text-capitalize" required
																onchange="loadEmployeeShiftDetails(); loadAttendanceRecords()">
																<option value="" disabled selected>- Select
																	Shift -</option>
																<c:forEach items="${shiftList}" var="s">
																	<option value="${s.shiftId}">${s.description}</option>
																</c:forEach>
															</select>
														</div>
														<div class="col-sm-3">
															<label>Department</label> <select id="selectDepartment"
																name="departmentId" class="form-control text-capitalize"
																required
																onchange="loadEmployeesByDepartment(); loadAttendanceRecords()">
																<option value="" disabled selected>- Select
																	Department -</option>
																<c:forEach items="${depList}" var="d">
																	<option value="${d.depID}">${d.department}</option>
																</c:forEach>
															</select> <span id="div1"></span>
														</div>
														<div class="col-sm-3">
															<label>Employee Name</label> <select
																id="selectEmployeeId" name="employeeId"
																class="form-control text-capitalize" required="true"
																onchange="loadEmployeeShiftDetails(); loadAttendanceRecords()">
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
														<div class="col-sm-3">
															<label>Start Time</label> <input id="startTime"
																name="startTime" type="time" class="form-control"
																value="" readonly /> <span id="div1"></span>
														</div>
														<div class="col-sm-3">
															<label>End Time</label> <input id="endTime"
																name="endTime" type="time" class="form-control" value=""
																readonly /> <span id="div2"></span>
														</div>
														<div class="col-sm-3">
															<label>On Time</label> <input id="onTime" name="onTime"
																type="time" class="form-control" value="" /> <span
																id="div1"></span>
														</div>
														<div class="col-sm-3">
															<label>Off Time</label> <input id="offTime"
																name="offTime" type="time" class="form-control" value="" />
															<span id="div2"></span>
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
																value="Add Attendance" /> <input type="reset"
																class="btn btn-danger btn-sm-3 mr-3" value="Reset" />
														</div>
														<div class="col-sm-3">
															<label style="display: none">Attendance ID</label><input
																id="attendanceId" name="attendanceId" type="hidden"
																class="form-control" value="${attendanceId}" readOnly />
														</div>
														<div class="col-sm-3">
															<input type="hidden" id="approved" name="approved"
																value="0" /> <input type="hidden" id="companyId"
																name="companyId"
																value="<%=session.getAttribute("company.comID")%>" />
														</div>
													</div>
												</div>
											</div>

										</form:form>

										<!-- DataTables Example -->
										<div class="card shadow mb-4">
											<div class="card-header py-3">
												<h6 class="m-0 font-weight-bold text-primary">Attendance
													Records</h6>
											</div>
											<div class="card-body">
												<div class="table-responsive" style="max-height: 500px">
													<table class="table table-sm table-hover table-bordered"
														id="dataTable" width="100%" cellspacing="0">
														<thead>
															<tr>
																<th style="display: none">Attendance ID</th>
																<th>Date</th>
																<th style="display: none">Shift ID</th>
																<th>Shift Description</th>
																<th style="display: none">Department ID</th>
																<th>Department</th>
																<th style="display: none">Employee ID</th>
																<th>Employee Name</th>
																<th style="display: none">Start Time</th>
																<th style="display: none">End Time</th>
																<th>On Time</th>
																<th>Off Time</th>
																<th>Approved</th>
																<th></th>
															</tr>
														</thead>
														<tfoot>
															<tr>
																<th style="display: none">Attendance ID</th>
																<th>Date</th>
																<th style="display: none">Shift ID</th>
																<th>Shift Description</th>
																<th style="display: none">Department ID</th>
																<th>Department</th>
																<th style="display: none">Employee ID</th>
																<th>Employee Name</th>
																<th style="display: none">Start Time</th>
																<th style="display: none">End Time</th>
																<th>On Time</th>
																<th>Off Time</th>
																<th>Approved</th>
																<th></th>
															</tr>
														</tfoot>
														<tbody>

															<%-- <c:forEach items="${attendanceList}" var="attendance">
															<tr>
																<td id="aId" style="display: none">${attendance[0]}</td>
																<td id="dte">${attendance[7]}</td>
																<td id="sId" style="display: none">${attendance[5]}</td>
																<td id="sft" >${attendance[6]}</td>
																<td id="dId" style="display: none">${attendance[3]}</td>
																<td id="dName">${attendance[4]}</td>
																<td id="eId" style="display: none">${attendance[1]}</td>
																<td id="eName">${attendance[2]}</td>
																<td id="sTime" style="display: none">${attendance[8]}</td>
																<td id="eTime" style="display: none">${attendance[9]}</td>
																<td id="on">${attendance[10]}</td>
																<td id="off">${attendance[11]}</td>
																<td id="approval"><c:if
																		test="${attendance[12] == 'true'}">Yes</c:if> <c:if
																		test="${attendance[12] == 'false'}">No</c:if></td>
																<td><a href="#" onclick="updateEmployeeAttendance('${attendance[0]}', '${attendance[2]}', '${attendance[3]}', '${attendance[4]}', '${attendance[5]}', '${attendance[6]}', new Date('${attendance[7]}'), '${attendance[8]}', '${attendance[9]}' )"><i class="far fa-edit"></i></a></td>
															</tr>
														</c:forEach> --%>

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
		src="<c:url value='/resources/hrm/ajax/employeeAttendance.js'/>"></script>
	<script
		src="<c:url value='/resources/hrm/ajax/employeeAttendance1.js'/>"></script>

</body>
</html>