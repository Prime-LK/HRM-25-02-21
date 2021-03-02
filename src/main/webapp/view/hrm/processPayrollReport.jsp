<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<html lang="en">
<head>
<%@include file="../../WEB-INF/jsp/head.jsp"%>
<%-- <link href="<c:url value='resources/hrm/css/viewProcessPayrollReport.css'/>"
	rel="stylesheet"> --%>
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
								<h2 class="text-white pb-2 fw-bold">Process Payroll Report</h2>
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
									<div class="container">
										<div class="row mt-4">
											<div class="col-3">
												<form:form action="sampleReport">
													<div class="row">
														<div class="col-12">
															<div class="form-group">
																<label>Generate Report Related Employee</label>

															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-12">
															<div class="form-group">
																<label>Employee</label> <select name="empID"
																	id="empID" class="custom-select" required>
																	<option value="">--SELECT--</option>
																	<c:forEach items="${allEmployeeForFTDR}" var="dt">
																		<option value="${dt.empID}">${dt.name} ${dt.lastname}</option>
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-12">
															<div class="form-group">
																<div class="form-group">
																	<button type="submit" id="submitBtn"
																		class="btn btn-success">
																		<i class="fa fa-print" aria-hidden="true"></i>
																		Generate Report
																	</button>
																</div>
															</div>
														</div>
													</div>
												</form:form>
											</div>
											<div class="col-3">
												<form:form action="generateEmpAllAllowanceReport">
													<div class="row">
														<div class="col-12">
															<div class="form-group">
																<label>Generate Report All Employees</label>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-12 mt-5">
															<div class="form-group mt-5">
																<div class="form-group mt-3">
																	<button type="submit" id="submitBtn"
																		class="btn btn-success">
																		<i class="fa fa-print" aria-hidden="true"></i>
																		Generate Report
																	</button>
																</div>
															</div>
														</div>
													</div>
												</form:form>
											</div>
										</div>
									</div>
								</div>
								<%-- <div>
									<a href="generateEmpAllAllowanceReport"
										class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
										class="fas fa-download fa-sm text-white-50"></i> Generate
										Report</a>
								</div>

								<div class="scrollable">
									<table class="table table-hover" width="100%" cellspacing="0"
										id="processPayrollReportTable">
										<thead>
											<tr>
												<th>Employee ID</th>
												<th>Employee Name</th>
												<th></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${getAllEmps}" var="e">
												<tr>
													<td>${e.detailsPK.empID.empID}</td>
													<td>${e.detailsPK.empID.name}
														${e.detailsPK.empID.lastname}</td>
													<td class=""><div>
															<a href="sampleReport?empID=${e.detailsPK.empID.empID}"
																class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
																class="fas fa-download fa-sm text-white-50"></i>
																Generate Report</a>
														</div></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div> --%>
							</div>
						</div>
					</div>
				</div>

			</div>
			<%@include file="../../WEB-INF/jsp/footer.jsp"%>
		</div>
	</div>
	<%@include file="../../WEB-INF/jsp/commJs.jsp"%>
	<script
		src="<c:url value='resources/hrm/ajax/processPayrollReport.js'/>"></script>
</body>
</html>