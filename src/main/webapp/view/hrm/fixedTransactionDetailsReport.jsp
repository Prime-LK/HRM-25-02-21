<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<html lang="en">
<head>
<%@include file="../../WEB-INF/jsp/head.jsp"%>
<link href="<c:url value='/resources/hrm/css/FTDReport.css'/>"
	rel="stylesheet">
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
								<div
									class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">Fixed
										Transactional Details</h6>
									<div class="dropdown no-arrow">
										<a class="dropdown-toggle" href="#" role="button"
											id="dropdownMenuLink" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="false"> <i
											class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
										</a>
										<div
											class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
											aria-labelledby="dropdownMenuLink">
											<div class="dropdown-header">Generate Report All
												Departments</div>
											<a class="dropdown-item" href="FTDRDepartments">Generate
												Report</a>
											<div class="dropdown-divider"></div>
											<div class="dropdown-header">Generate Report Related
												Department</div>
											<c:forEach items="${allDepartmentForFTDR}" var="na">
												<a class="dropdown-item"
													href="FTDRDepartment?depID=${na.depID}">
													${na.department}</a>
											</c:forEach>
											<div class="dropdown-divider"></div>
											<div class="dropdown-header">Generate Report All
												Employees</div>
											<a class="dropdown-item" href="FTDREmployees">Generate
												Report</a>
											<div class="dropdown-divider"></div>
											<div class="dropdown-header">Generate Report Related
												Employee</div>
											<a class="dropdown-item" href="#">Generate Report</a>
											<form action="FTDREmployee">
												<div class="row">
													<input type="text" class="form-control" id="empIDForReport"
														name="empID" placeholder="Emp No">
													<button type="submit" id="rBtn01"
														class="btn btn-danger btn-sm">
														<i class="fa fa-search" aria-hidden="true"></i>
													</button>
												</div>
											</form>
											<!-- <a class="dropdown-item" href="#">Generate Report Related Employee</a> -->
										</div>
									</div>
								</div>
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">

											<div class="row">
												<div class="row form-group col-7" id="depDIv">
													<label id="dLbl">Department</label>
													<div class="col">
														<select id="depID" class="form-control"
															name="saPK.depatment.depID"
															onchange="loadTableRelatedDepartment(this.value)">
															<option value="">Select Department</option>
															<c:forEach items="${allDepartmentForFTDR}" var="b">
																<option value="${b.depID}">${b.department}</option>
															</c:forEach>
														</select>
													</div>
												</div>

												<div class="form-check" id="depDiv">
													<input type="checkbox" class="form-check-input"
														id="addAllDepartments"
														onchange="loadTableAllDepartments()"> <label
														class="form-check-label" for="addAllDepartments">All</label>
												</div>
											</div>

											<div class="row">
												<div class="row form-group col-7" id="adjType">
													<label id="dLbl">Employee</label>
													<div class="col">
														<select id="empID" class="form-control" name="empID"
															onchange="getDataRelatedEmployee(this.value)">
															<option value="">Select Employee</option>
															<c:forEach items="${allEmployeeForFTDR}" var="b">
																<option value="${b.empID}">${b.name}</option>
															</c:forEach>
														</select>
													</div>
												</div>

												<div class="form-check" id="cbDiv">
													<input type="checkbox" class="form-check-input"
														id="addAllAllowance" onchange="getAllEmpHeaderData()">
													<label class="form-check-label" for="addAllAllowance">All</label>
												</div>
											</div>

											<div class="row"></div>

											<div class="rwo">
												<div id="detailsTableDiv">
													<div class="scrollable">
														<table class="table table-hover" width="100%"
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
	<script src="<c:url value='resources/hrm/ajax/FTDReport.js'/>"></script>

	<!-- table scroller -->
	<script src="<c:url value='resources/hrm/js/table-scroller.js'/>"></script>
</body>
</html>