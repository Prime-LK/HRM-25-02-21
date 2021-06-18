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
								<h2 class="text-white pb-2 fw-bold">Shift Allocation</h2>
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
										<c:if test = "${success ==1}">
											<div class="alert alert-success alert-dismissible">
											  <button type="button" class="close" data-dismiss="alert">&times;</button>
											  <strong>Success!</strong> Data Successfully Saved.
											</div>
										</c:if>
										<c:if test = "${success ==0}">
										  <div class="alert alert-danger alert-dismissible">
										    <button type="button" class="close" data-dismiss="alert">&times;</button>
										    <strong>Warning!</strong>Something went wrong ! Please try again!
										  </div>
										</c:if>
										<form:form action="assignShift"
											onsubmit="return validateForm()" method="post">
											<div class=" row">
												<div class="col-9">
													<div class="form-group row">
														<div class="col-sm-3">
															<label>Start Date</label> <input id="startDate"
																name="startDate" type="date" class="form-control"
																value="" required onchange="" />
															<span id="div1"></span>
														</div>
														<div class="col-sm-3">
															<label>End Date</label> <input id="endDate"
																name="endDate" type="date" class="form-control" value=""
																required onchange="" /> <span
																id="div2"></span>
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
																required
																onchange="loadEmployeeIdByDepartmentId();">
																<option value="" disabled selected>Select
																	Department</option>
																<c:forEach items="${depList}" var="d">
																	<option value="${d.depID}">${d.department}</option>
																</c:forEach>
															</select> <span id="div1"></span>
														</div>
														<div class="col-sm-5">
															<label>Employee</label> <select id="selectEmployeeId"
																name="employeeId" class="form-control text-capitalize">
																<option value="" disabled selected>Select
																	Employee</option>
															</select>
														</div>
														<div class="col-sm-1">
															<br> <label for="check">All Employees</label> <input
																type="checkbox" id="allEmployees" name="allEmployees"
																value="1" /> <input type="hidden"
																id="allEmployeesHidden" name="allEmployees" value="0" />
															<input type="hidden" id="companyId" name="companyId"
																value="<%=session.getAttribute("company.comID")%>" />
														</div>
													</div>
												</div>
											</div>
											<div class=" row">
												<div class="col-9">
													<div class="form-group row">
														<div class="col-sm-3">
															<label>Shift</label> <select id="shiftId" name="shiftId"
																class="form-control text-capitalize" required
																onchange="loadShiftById()">
																<option value="" disabled selected>Select Shift</option>
																<c:forEach items="${shiftList}" var="s">
																	<option value="${s.shiftId}">${s.description}</option>
																</c:forEach>
															</select>
														</div>
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
															<label>Duration</label> <input id="duration"
																name="duration" type="text" class="form-control"
																value="" readonly /> <span id="div1"></span>
														</div>
													</div>
												</div>
											</div>
											<div class=" row">
												<div class="col-9">
													<div class="form-group row">
														<div class="col-sm-2">
															<input type="submit"
																class="btn btn-primary btn-sm-3 mr-3"
																value="Allocate Shift" />
														</div>
														<div class="col-sm-2">
															<input type="reset" class="btn btn-danger btn-sm-3 mr-3"
																value="Reset" />
														</div>
													</div>
												</div>
											</div>

										</form:form>

										<!-- DataTables Example -->
										<!-- <div class="card shadow mb-4">
											<div class="card-header py-3">
												<h6 class="m-0 font-weight-bold text-primary">Allocated
													Shifts</h6>
											</div>
											<div class="card-body">
												<div class="table-responsive" style="max-height: 500px">
													<table class="table table-sm table-hover table-bordered"
														id="dataTable" style="width:100%" cellspacing="0">

														<thead>
															<tr>
																<th>Date</th>
																<th>Shift Name</th>
																<th>Start Time</th>
																<th>End Time</th>
																<th>Employee Name</th>
																<th>Department</th>
															</tr>
														</thead>
														<tfoot>
															<tr>
																<th>Date</th>
																<th>Shift Name</th>
																<th>Start Time</th>
																<th>End Time</th>
																<th>Employee Name</th>
																<th>Department</th>
															</tr>
														</tfoot>
														<tbody>

														</tbody>
													</table>
												</div>
											</div>
										</div> -->

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