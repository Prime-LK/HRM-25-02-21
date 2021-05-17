<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<html lang="en">
<head>
<%@include file="../../WEB-INF/jsp/head.jsp"%>
<link href="<c:url value='resources/hrm/css/processPayrollNew1.css'/>"
	rel="stylesheet">
<link href="<c:url value='/resources/hrm/css/popUp.css'/>"
	rel="stylesheet" type="text/css">
<!-- combined year and month picker css -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.2.0/css/datepicker.min.css"
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
/* form css */
* {
	text-transform: capitalize;
}

#hiddenRow {
	 display: none; 
	
}

.scrollable {
	height: 330px;
	overflow: scroll;
}

#tableProcessPayroll1 thead tr th, #tbl02Data, #sample, #formThreeLabel
	{
	font-size: 12px;
}

#lsBtn {
	color: #fff;
}

#detailsTbl1, #lsBtn {
	margin-left: -3rem;
	border: none;
}
</style>

</head>
<body onload="getCompletePage();checkStatusofDropdowns();">
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
							<div class="col-xl-12 col-lg-2">
								<h2 class="text-white pb-2 fw-bold">Process Payroll</h2>
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
								<blockquote class="text-danger">${MsgForPPDMonth}</blockquote>
								<form:form action="saveProcessPayRollData1" method="post"
									onSubmit="formValidation()" id="ProcessPayrollPage1"
									modelAttribute="processPayrollPage1">
									<div class="row">
										<div class="col-6">
											<div class="row">
												<div class="col-6">
													<div class="form-group">
														<label>Start Date</label> <input name="startDate"
															type="date" class="form-control" id="startDate" />
													</div>
												</div>
												<div class="col-6">
													<div class="form-group">
														<label>End Date</label> <input name="endDate" type="date"
															onchange="loadPayPeriod()" class="form-control"
															id="endDate" placeholder="End Date" />
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-6">
													<div class="form-group">
														<label>PayCode</label> <select
															class="form-control form-control text-capitalize"
															id="payCodeID" name="payCodeID" onchange="loadDetails()">
															<option value="">Pay Code</option>
															<c:forEach items="${payCodeList}" var="pc">
																<option value="${pc.payCodeID}">${payCode}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="col-6">
													<div class="form-group mt-4" id="payPeriodValDiv">
														<div class="input-group">
															<div class="input-group-prepend">
																<span class="input-group-text">PayPeriod</span>
															</div>
															<input type="text" class="form-control"
																name="periodIDVal" id="periodIDVal"
																placeholder="PayPeriod">
														</div>
													</div>
												</div>
											</div>
											<div class="row" id="hiddenRow">
												<div class="col-6">
													<div class="form-group">
														<label>Period</label> <input name="periodID" type="text"
															onchange="" class="form-control" id="periodID"
															placeholder="PayPeriod" readOnly />
													</div>
												</div>
											</div>
											<div class="row" id="hiddenRow">
												<div class="col-6">
													<div class="form-group">
														<label>Company ID</label> <input type="text" name="comID"
															class="form-control" id="comID"
															value="<%=session.getAttribute("company.comID")%>"
															placeholder="Company ID" readOnly />
													</div>
												</div>
												<div class="col-6">
													<div class="form-group">
														<label>Process User</label> <input name="processUser"
															type="text" class="form-control" id="processUser"
															value="<%=session.getAttribute("empID")%>" readOnly>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-8">
													<div class="form-group">
														<button type="submit" id="submitBtn"
															class="btn btn-success">
															<i class="fa fa-plus"></i> Process Details
														</button>
														<button type="reset" id="resetBtn"
															class="browse btn btn-danger">
															<i class="fa fa-arrow-circle-right" aria-hidden="true"></i>
															Reset
														</button>
													</div>
												</div>
											</div>
										</div>
										<div class="col-6">
											<div class="row" id="detailsTbl1Column">
												<div class="col-4">
													<ul class="list-group list-group-flush">
														<li class="list-group-item" id="lsTitle">Employees</li>
														<li class="list-group-item" id="lsTitle">Basic
															Salaries</li>
														<li class="list-group-item" id="lsTitle">Additions</li>
														<li class="list-group-item" id="lsTitle">Deductions</li>
														<li class="list-group-item" id="lsTitle">Others</li>
													</ul>
												</div>
												<div class="col-4">
													<ul class="list-group list-group-flush" id="detailsTbl1"></ul>
												</div>
												<div class="col-4 mt-5">
													<ul class="list-group list-group-flush mt-5">
														<li class="list-group-item mt-5" id="lsBtn">
															<div class="mt-4">
																<a type="button" class="btn btn-secondary btn-sm mt-2"
																	data-modal-target="#modal1"
																	onClick="getEmpDetailsRelatedPayCodeID1()"> <i
																	class="fa fa-info mr-2" aria-hidden="true"></i>More
																</a>
															</div>
														</li>
													</ul>
												</div>
											</div>
										</div>
									</div>
								</form:form>
								<div class="row">
									<div class="form-group">
										<div class="modal1" id="modal1">
											<a type="button" id="popUpBtnClose" data-close-button
												class="close-button1"> <img
												src="resources/hrm/img/cancel.png" alt="close"
												style="hieght: 35px; width: 35px;" /></a>
											<div class="modal-body1">
												<div class="row">
													<div class="col-9">
														<div class="scrollable mt-3">
															<table class="table table-hover table-bordered"
																width="100%" cellspacing="0" id="tableProcessPayroll1">
																<thead>
																	<tr>
																		<th>Emp. No.</th>
																		<th>Name</th>
																		<th>Basic Salary</th>
																		<th>Additions</th>
																		<th>Deductions</th>
																		<th>Others</th>
																		<th>More</th>
																	</tr>
																</thead>
																<tbody>
																	<tr></tr>
																</tbody>
															</table>
														</div>
													</div>
													<div class="col-3">
														<div id="sample">
															<div class="scrollable mt-3">
																<div class='row form-group'>
																	<label class="col-7" id="formThreeLabel">Emp.
																		No.</label> <span id="empidoftble3" class="col-5">Currently
																		No Data</span> <span id="empidoftble3" class="col-5"></span>
																</div>
																<div class='row form-group'>
																	<label class="col-7" id="formThreeLabel">Name</label> <span
																		id="empnameoftble3" class="col-5">Currently No
																		Data</span> <span id="empnameoftble3" class="col-5"></span>
																</div>
																<div class='row form-group'>
																	<label class="col-7" id="formThreeLabel">Basic
																		Salary</label> <span id="empssoftble3" class="col-5">Currently
																		No Data</span> <span id="empssoftble3" class="col-5"></span>
																</div>

																<div class="row form-group">
																	<label class="col-4" id="formThreeLabel">Additions</label>
																</div>
																<div class='row form-group' id="additions">
																	<span id="empssoftble3" class="col-5">Currently
																		No Data</span>
																</div>

																<div class="row form-group">
																	<label class="col-4" id="formThreeLabel">Deductions</label>
																</div>
																<div class='row form-group' id="deductions">
																	<span id="empssoftble3" class="col-5">Currently
																		No Data</span>
																</div>

																<div class="row form-group">
																	<label class="col-4" id="formThreeLabel">Others</label>
																</div>
																<div class='row form-group' id="others">
																	<span id="empssoftble3" class="col-5">Currently
																		No Data</span>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div id="overlay1"></div>
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
	<script src="<c:url value='resources/hrm/ajax/processPayrollNew1.js'/>"></script>
	<!-- popUp -->
	<script defer src="resources/hrm/js/popUp.js"></script>
	<!-- combined year and month picker js -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.min.js"></script>
</body>
<script>
	$("#datepicker").datepicker({
		format : "yyyy-mm",
		startView : "months",
		minViewMode : "months"
	});
</script>
</html>