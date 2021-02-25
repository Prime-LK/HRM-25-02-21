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
								<h2 class="text-white pb-2 fw-bold">Attendance Revise</h2>
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
										<form:form action="submitAttendanceRevise" method="post"
											onsubmit="return validateForm()">
											<div class=" row">
												<div class="col-9">
													<div class="form-group row">
														<div class="col-sm-3">
															<label>Date</label><input id="date" name="date"
																type="date" class="form-control" value="" required
																onchange="loadEmployeeAttendanceDetails()"
																required="true" />
														</div>
														<div class="col-sm-3">
															<label>Shift ID</label> <select id="shiftId"
																name="shiftId" class="form-control text-capitalize"
																required onchange="loadEmployeeAttendanceDetails()">
																<option value="" disabled selected>Shift Code</option>
																<c:forEach items="${shiftList}" var="s">
																	<option value="${s.shiftId}">${s.description}</option>
																</c:forEach>
															</select>
														</div>
														<div class="col-sm-3">
															<label>Department</label> <select id="selectDepartment"
																name=""
																class="form-control form-control-user col-12 foo text-capitalize"
																onchange="loadEmployeesByDepartment()">
																<option value="" disabled selected>Department</option>
																<c:forEach items="${depList}" var="d">
																	<option value="${d.depID}">${d.department}</option>
																</c:forEach>
															</select>
														</div>
														<div class="col-sm-3">
															<label>Employee Name</label> <select
																id="selectEmployeeId" name="employeeId"
																class="form-control form-control-user col-12 foo text-capitalize"
																onchange="loadEmployeeAttendanceDetails()"
																required="true">
															</select>
														</div>
													</div>
												</div>
											</div>
											<div class=" row">
												<div class="col-9">
													<div class="form-group row">
														<div class="col-sm-3">
															<label>On Time</label> <input id="onTime" name="onTime"
																type="text" class="form-control" readOnly />
														</div>
														<div class="col-sm-3">
															<label>Off Time</label> <input id="offTime"
																name="offTime" type="text" class="form-control" readOnly />
														</div>
														<div class="col-sm-3">
															<label>New On Time</label> <input id="newOnTime"
																name="newOnTime" type="time" class="form-control"
																value="" />
														</div>
														<div class="col-sm-3">
															<label>New Off Time</label> <input id="newOffTime"
																name="newOffTime" type="time" class="form-control"
																value="" />
														</div>
													</div>
												</div>
											</div>
											<div class=" row">
												<div class="col-9">
													<div class="form-group row">
														<div class="col-sm-6">
															<label>Remark</label> <input id="remark" name="remark"
																type="text" class="form-control" /><span id="div1"></span>
														</div>
														<div class="col-sm-3">
															<label style="display: none">Revise ID</label> <input
																id="reviseId" name="reviseId" type="hidden"
																class="form-control" value="${reviseId}" readOnly />
														</div>
														<div class="col-sm-3">
															<label style="display: none">Attendance ID</label> <input
																id="attendanceId" name="attendanceId" type="hidden"
																class="form-control" readOnly />
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
																value="Submit Attendance Revise" /> <input type="reset"
																class="btn btn-danger btn-sm-3 mr-3" value="Reset"
																onclick="resetEmployeeList()" />
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
													Revises</h6>
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
															</tr>
														</tfoot>
														<tbody>

															<c:forEach items="${revisesList}" var="revise">
																<tr>
																	<td style="display: none">${revise[0]}</td>
																	<td>${revise[1]}</td>
																	<td style="display: none">${revise[2]}</td>
																	<td>${revise[3]}</td>
																	<td style="display: none">${revise[4]}</td>
																	<td>${revise[5]}</td>
																	<td style="display: none">${revise[6]}</td>
																	<td>${revise[7]}</td>
																	<td>${revise[8]}</td>
																	<td>${revise[9]}</td>
																	<td>${revise[10]}</td>
																	<td>${revise[11]}</td>
																	<td>${revise[12]}</td>
																	<td>${revise[13]}</td>
																	<td><c:if test="${revise[14] == 'true'}">Yes</c:if>
																		<c:if test="${revise[14] == 'false'}">No</c:if></td>
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

	<!-- Page level custom scripts -->

	<script
		src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/jquery-ui.min.js"></script>
	<script src="<c:url value='/resources/hrm/js/employeeAttendance.js'/>"></script>
	<script src="<c:url value='/resources/hrm/ajax/attendanceRevise.js'/>"></script>
	<script src="<c:url value='/resources/hrm/ajax/employeeAttendance.js'/>"></script>
	
	<!-- Datatable -->
	<script src="<c:url value='/resources/hrm/ajax/datatable.js'/>"></script>

</body>
</html>