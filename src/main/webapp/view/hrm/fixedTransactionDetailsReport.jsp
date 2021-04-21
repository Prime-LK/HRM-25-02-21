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
/* form css */
.card {
	height: 600px;
	text-transform: capitalize;
}

#detailsTbl td {
	font-size: 12px;
}

.scrollable {
	height: 380px;
	overflow: scroll;
}
</style>
</head>
<body onload="inVisibleFields();">
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
							<div class="col-xl-12 col-lg-2">
								<h2 class="text-white pb-2 fw-bold">Fixed Transactional
									Details Report</h2>
							</div>
							<div class="col-xl-2 col-lg-2"></div>
							<div class="ml-md-auto py-2 py-md-4"></div>
							<div class="ml-md-auto py-2 py-md-4"></div>
						</div>
					</div>
				</div>
				<div class="page-inner mt--5">
					<div class="container-fluid">
						<div class="card">
							<div class="card-body">
								<div class="row">
									<div class="col-7">
										<div class="row">
											<div class="col-6">
												<div class="form-group">
													<label>Department</label>
													<div class="input-group">
														<select id="depID" class="form-control"
															name="saPK.depatment.depID"
															onchange="loadTableRelatedDepartment(this.value)">
															<option value="">--SELECT--</option>
															<c:forEach items="${allDepartmentForFTDR}" var="b">
																<option value="${b.depID}">${b.department}</option>
															</c:forEach>
														</select>
														<div class="input-group-addon">
															<div class="form-check">
																<label class="form-check-label"> <input
																	onchange="loadTableAllDepartments()"
																	class="form-check-input" id="depsCheckBox"
																	type="checkbox" value="checkebox"> <span
																	class="form-check-sign">All</span>
																</label>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="col-6">
												<div class="form-group">
													<label>Employee</label>
													<div class="input-group">
														<select id="empID" class="form-control" name="empID"
															onchange="getDataRelatedEmployee(this.value)">
															<option value="">--SELECT--</option>
															<c:forEach items="${getAllEmps}" var="b">
																<option value="${b.detailsPK.empID.empID}">
																	${b.detailsPK.empID.name} ${b.detailsPK.empID.lastname}</option>
															</c:forEach>
														</select>
														<div class="input-group-addon">
															<div class="form-check">
																<label class="form-check-label"> <input
																	onchange="getAllEmpHeaderData()" id="empsCheckBox"
																	class="form-check-input" type="checkbox"
																	value="checkbox"> <span class="form-check-sign">All</span>
																</label>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<hr>
										<div class="row">
											<div class="col-5">
												<div class="form-group">
													<label>Report Filters</label> <select class="form-control"
														name="seperateSelect" id="sepSelect"
														onChange="loadRelatedSelect();">
														<option value="" selected>--SELECT--</option>
														<option value="allDeps">All Departments</option>
														<option value="dep">Related Department</option>
														<option value="allEmps">All Employees</option>
														<option value="emp">Related Employee</option>
													</select>
												</div>
											</div>
											<div class="col-5">
												<div id="loadSepDiv"></div>
											</div>
										</div>
									</div>
									<div class="col-5">
										<div class="form-group" id="detailsTableDiv">
											<div class="scrollable">
												<table class="table table-hover table-bordered" width="100%"
													cellspacing="0" id="detailsTbl">
													<thead>
														<tr></tr>
													</thead>
													<tbody>
														<tr></tr>
													</tbody>
												</table>
											</div>
										</div>
										<!-- <div class="row form-group">
											<label>Report Filters</label> <select class="form-control"
												name="seperateSelect" id="sepSelect"
												onChange="loadRelatedSelect();">
												<option value="" selected>--SELECT--</option>
												<option value="allDeps">All Departments</option>
												<option value="dep">Related Department</option>
												<option value="allEmps">All Employees</option>
												<option value="emp">Related Employee</option>
											</select>
										</div>
										<div class="row form-group">
											<div class="col-6">
												<div id="loadSepDiv"></div>
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
	<script src="<c:url value='resources/hrm/ajax/FTDReport.js'/>"></script>

	<!-- table scroller -->
	<script src="<c:url value='resources/hrm/js/table-scroller.js'/>"></script>
</body>
</html>