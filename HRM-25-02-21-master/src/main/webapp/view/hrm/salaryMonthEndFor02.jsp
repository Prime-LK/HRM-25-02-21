<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<html lang="en">
<head>
<%@include file="../../WEB-INF/jsp/head.jsp"%>
<link href="<c:url value='resources/hrm/css/salaryMonthEndFor02.css'/>"
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
#hiddenRow {
	display: none; 	
}
</style>

</head>
<body onload="manageFields();checkStatusofDropdowns();">
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
								<h2 class="text-white pb-2 fw-bold">Salary Month End
									Details</h2>
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
								<form:form action="saveSalaryMonthEndForm02" method="post"
									onSubmit="formValidation()" id="salaryHisMasForm02"
									modelAttribute="salaryHisMasForm02">
									<div class="row">
										<div class="col-6">
											<div class="row">
												<div class="col-6">
													<div class="form-group">
														<label>Star Date</label>
														 <input name="startDate" type="date" class="form-control"
														id="startDate" />
													</div>
												</div>
												<div class="col-6">
													<div class="form-group">
														<label>End Date</label>
														 <input name="endDate" type="date" class="form-control"
														id="endDate" onchange="loadPayPeriod()" />
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-6">
													<div class="form-group" id="payPeriodValDiv">
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
												<div class="col-6">
													<div class="form-group" id="payCodeValDiv">
														<div class="input-group">
															<div class="input-group-prepend">
																<span class="input-group-text">PayCode</span>
															</div>
															<input type="text" class="form-control"
																name="payCodeIDVal" id="payCodeIDVal"
																placeholder="PayCode">
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
												<div class="col-6">
													<div class="form-group">
														<label>PayCode</label> <input type="text" id="payCodeID"
															name="payCodeID" class="form-control"
															placeholder="PayCode" readOnly />
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
									</div>
									<%-- <div class="row">
										<div class="col-6">
											<div class="form-group row">
												<label class="col-5 mt-1">Start Date</label>
												<div class="col-7">
													<input name="startDate" type="date" class="form-control"
														id="startDate" />
												</div>
											</div>
										</div>
										<div class="col-6">
											<div class="form-group row">
												<label class="col-2 mt-1">Period</label>
												<div class="col-7">
													<input name="periodID" type="text" class="form-control"
														id="periodID" readOnly />
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
													<input name="endDate" type="date" class="form-control"
														id="endDate" onchange="loadPayPeriod()" />
												</div>
											</div>
										</div>
										<div class="col-6">
											<div class="form-group row">
												<label class="col-2 mt-1">PayCode</label>
												<div class="col-7">
													<input type="text" id="payCodeID" name="payCodeID"
														class="form-control" readOnly />
												</div>
											</div>
										</div>
										<div class="col" id="processUserDiv">
											<div class="form-group row">
												<div class="col-7">
													<div class="form-group col-6 row ml-3">
														<!-- <label id="lcode">Company ID :</label> -->
														<div class="col">
															<input name="processUser" type="text"
																class="form-control" id="processUser"
																value="<%=session.getAttribute("empID")%>" readOnly>
														</div>
													</div>
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
									</div> --%>
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
	<script
		src="<c:url value='resources/hrm/ajax/salaryMonthEndFor02.js'/>"></script>

	<script src="<c:url value='resources/hrm/js/salaryMonthEndFor02.js'/>"></script>
</body>
</html>