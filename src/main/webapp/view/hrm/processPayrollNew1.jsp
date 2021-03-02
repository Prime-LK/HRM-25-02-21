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
								<form:form action="saveProcessPayRollData1" method="post"
									onSubmit="formValidation()" id="ProcessPayrollPage1"
									modelAttribute="processPayrollPage1">
									<div class="row">
										<div class="col-6">
											<div class="form-group row">
												<label class="col-5 mt-1">Start Date</label>
												<div class="col-7">
													<input name="startDate" type="date" class="form-control"
														id="startDate" /> <span id="div1"></span>
												</div>
											</div>
										</div>
										<div class="col-6">
											<div class="form-group row">
												<label class="col-2 mt-1">Period</label>
												<div class="col-7">
													<input name="periodID" type="text" onchange=""
														class="form-control" id="periodID" readOnly /> <span
														id="div2"></span>
												</div>
											</div>
										</div>
										<div class="col" id="comDiv">
											<div class="form-group row">
												<div class="col-7">
													<div class="form-group col-6 row ml-3">
														<!-- <label id="lcode">Company ID :</label> -->
														<div class="col">
															<input type="hidden" name="comID" class="form-control"
																id="comID"
																value="<%=session.getAttribute("company.comID")%>"
																placeholder="Company ID" readOnly />
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-6">
											<div class="form-group row">
												<label class="col-5 mt-1">End Date</label>
												<div class="col-7">
													<input name="endDate" type="date"
														onchange="loadPayPeriod()" class="form-control "
														id="endDate" /> <span id="div2"></span>
												</div>
											</div>
										</div>
										<div class="col-6" id="processUserDiv">
											<div class="form-group row">
												<label class="col-2 mt-1">processUser</label>
												<div class="col-7">
													<input name="processUser" type="text" class="form-control"
														placeholder="" id="processUser"
														value="<%=session.getAttribute("empID")%>" readOnly>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-6">
											<div class="form-group row">
												<label class="col-5 mt-1">PayCode</label>
												<div class="col-7">
													<select class="form-control form-control text-capitalize"
														id="payCodeID" name="payCodeID" onchange="loadDetails()"
														required>
														<option value="">Pay Code</option>
														<c:forEach items="${payCodeList}" var="pc">
															<option value="${pc.payCodeID}">${payCode}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-12">
											<button type="submit" id="submitBtn" class="btn btn-success">
												<i class="fa fa-plus"></i> Process Details
											</button>
											<button type="reset" id="resetBtn"
												class="browse btn btn-danger">
												<i class="fa fa-arrow-circle-right" aria-hidden="true"></i>
												Reset
											</button>
										</div>
									</div>
									<div class="form-group row">
										<div class="col-6">
											<div class="mt-3" id="detailsTbl1">
												<table class="table table-hover" cellspacing="0"
													id="tableProcessPayroll">
													<thead>
														<tr>
															<th>Employees</th>
															<th>Basic Salary</th>
															<th>Additions</th>
															<th>Deductions</th>
															<th>Others</th>
															<th>More</th>
														</tr>
													</thead>
													<tbody>
														<tr>

														</tr>
													</tbody>
												</table>
											</div>

										</div>

									</div>

									<div class="row form-group">
										<div class="col-3 row">
											<div class="mt-3" id="detailsTbl">
												<div class="scrollable">
													<table class="table table-hover bordered header-fixed"
														width="100%" cellspacing="0" id="tableProcessPayroll1">
														<thead>
															<tr>
																<th>ID</th>
																<th>Name</th>
																<th>Basic Salary</th>
																<th>Additions</th>
																<th>Deductions</th>
																<th>Others</th>
																<th></th>
															</tr>
														</thead>
														<tbody>
															<tr>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
											<hr>
										</div>

										<div class="form-group col-3 offset-4 row" id="sample">
											<p id="tLabel">Employee Details</p>
											<div class='row'>
												<div class='row form-group'>
													<label id="lbl1">Employee ID</label> <input
														id='empidoftble3' name="empidoftble3"
														class='form-control col-5' readOnly>
												</div>
											</div>
											<div class='row'>
												<div class='form-group row'>
													<label id="lbl2">Name</label> <input id='empnameoftble3'
														class='form-control col-5' readOnly>
												</div>
											</div>
											<div class='row'>
												<div id='' class='form-group row'>
													<label id="lbl3">Basic Salary</label> <input
														id='empssoftble3' class='form-control col-5' readOnly>
												</div>
											</div>
											<div class='row'>
												<table id="miniTable1">
													<thead>
														<tr>
															<th>Additions</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
											</div>

											<div class='row'>
												<table id="miniTable2">
													<thead>
														<tr>
															<th>Deductions</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
											</div>

											<div class='row'>
												<table id="miniTable3">
													<thead>
														<tr>
															<th>Others</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</form:form>
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
</body>
</html>