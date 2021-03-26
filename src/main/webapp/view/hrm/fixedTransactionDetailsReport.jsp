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
<body onload="tableInvisible();checkStatusofDropdowns();">
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
									<div class="col-8">
										<div class="row form-group">
											<label class="col-3">Department</label>
											<div class="col-7">
												<select id="depID" class="form-control"
													name="saPK.depatment.depID"
													onchange="loadTableRelatedDepartment(this.value)">
													<option value="">--SELECT--</option>
													<c:forEach items="${allDepartmentForFTDR}" var="b">
														<option value="${b.depID}">${b.department}</option>
													</c:forEach>
												</select>
											</div>
											<div class="form-check">
												<label class="form-check-label"> <input
													onchange="loadTableAllDepartments()"
													class="form-check-input" id="depsCheckBox" type="checkbox"
													value=""> <span class="form-check-sign">All</span>
												</label>
											</div>
										</div>
										<div class="row form-group">
											<label class="col-3">Employee</label>
											<div class="col-7">
												<select id="empID" class="form-control" name="empID"
													onchange="getDataRelatedEmployee(this.value)">
													<option value="">--SELECT--</option>
													<c:forEach items="${getAllEmps}" var="b">
														<option value="${b.detailsPK.empID.empID}">
															${b.detailsPK.empID.name} ${b.detailsPK.empID.lastname}</option>
													</c:forEach>
												</select>
											</div>
											<div class="form-check">
												<label class="form-check-label"> <input
													onchange="getAllEmpHeaderData()" id="empsCheckBox"
													class="form-check-input" type="checkbox" value="">
													<span class="form-check-sign">All</span>
												</label>
											</div>
										</div>
										<div id="detailsTableDiv">
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
									</div>
									<div class="col-4">
										<div class="contanier">
											<div class="accordion" id="accordionExample">
												<div class="card-header" id="headingOne">
													<h5 class="mb-0">
														<button class="btn btn-outline-dark" type="button"
															data-toggle="collapse" data-target="#collapseOne"
															aria-expanded="false" aria-controls="collapseOne">
															All Departments</button>
													</h5>
												</div>
												<div id="collapseOne" class="collapse"
													aria-labelledby="headingOne"
													data-parent="#accordionExample">
													<div class="card-body">
														<form:form action="FTDRDepartments">
															<div class="row">
																<div class="col-6">
																	<button type="submit" id="submitBtn"
																		class="btn btn-success">
																		<i class="fa fa-print" aria-hidden="true"></i>
																		Generate Report
																	</button>
																</div>
															</div>
														</form:form>
													</div>
												</div>
												<div class="card-header" id="headingTwo">
													<h5 class="mb-0">
														<button class="btn btn-outline-dark collapsed"
															type="button" data-toggle="collapse"
															data-target="#collapseTwo" aria-expanded="false"
															aria-controls="collapseTwo">Related Department</button>
													</h5>
												</div>
												<div id="collapseTwo" class="collapse"
													aria-labelledby="headingTwo"
													data-parent="#accordionExample">
													<div class="card-body">
														<form:form action="FTDRDepartment">
															<div class="row">
																<div class="col-9">
																	<label>Department</label> <select name="depID"
																		id="depID" class="custom-select mt-2 mb-2" required>
																		<option value="">--SELECT--</option>
																		<c:forEach items="${allDepartmentForFTDR}" var="dt">
																			<option value="${dt.depID}">${dt.department}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
															<div class="row">
																<div class="col-4">
																	<button type="submit" id="submitBtn"
																		class="btn btn-success">
																		<i class="fa fa-print" aria-hidden="true"></i>
																		Generate Report
																	</button>
																</div>
															</div>
														</form:form>
													</div>
												</div>
												<div class="card-header" id="headingThree">
													<h5 class="mb-0">
														<button class="btn btn-outline-dark collapsed"
															type="button" data-toggle="collapse"
															data-target="#collapseThree" aria-expanded="false"
															aria-controls="collapseThree">All Employees</button>

													</h5>
												</div>
												<div id="collapseThree" class="collapse"
													aria-labelledby="headingThree"
													data-parent="#accordionExample">
													<div class="card-body">
														<form:form action="FTDREmployees">
															<div class="row">
																<div class="col-4">
																	<button type="submit" id="submitBtn"
																		class="btn btn-success">
																		<i class="fa fa-print" aria-hidden="true"></i>
																		Generate Report
																	</button>
																</div>
															</div>
														</form:form>
													</div>
												</div>
												<div class="card-header" id="headingFour">
													<h5 class="mb-0">
														<button class="btn btn-outline-dark collapsed"
															type="button" data-toggle="collapse"
															data-target="#collapseFour" aria-expanded="false"
															aria-controls="collapseFour">Related Employee</button>
													</h5>
												</div>
												<div id="collapseFour" class="collapse"
													aria-labelledby="headingFour"
													data-parent="#accordionExample">
													<div class="card-body">
														<form:form action="FTDREmployee">
															<div class="row">
																<div class="col-8">
																	<div class="input-group">
																		<input type="text" class="form-control"
																			id="empIDForReport" name="empID"
																			placeholder="Emp. No..." autocomplete="off">
																		<div class="input-group-append">
																			<button type="submit" id="searchBtn"
																				class="btn btn-success btn-sm">
																				<i class="fa fa-search" aria-hidden="true"></i>
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