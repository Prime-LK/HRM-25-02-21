<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<html lang="en">
<head>
<%@include file="../../WEB-INF/jsp/head.jsp"%>
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
								<form:form action="saveSalaryMonthEndForm04" method="post"
									onSubmit="formValidation()" id="salaryHisMasForm04"
									modelAttribute="salaryHisMasForm04">
									<div class="row">
										<div class="col-6">
											<div class="row">
												<div class="col-6">
													<div class="form-group">
														<label>Year & Month</label> <input type="text"
															class="form-control" name="datepicker" id="datepicker"
															placeholder="Pick a Year and Month"/>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-6" id="startDateDiv">
													<div class="form-group">
														<div class="input-group">
															<div class="input-group-prepend">
																<span class="input-group-text">From</span>
															</div>
															<input type="text" class="form-control" name="startDate"
																id="startDate" placeholder="Start Date">
														</div>
													</div>
												</div>
												<div class="col-6" id="endDateDiv">
													<div class="form-group">
														<div class="input-group">
															<div class="input-group-prepend">
																<span class="input-group-text">To</span>
															</div>
															<input type="text" class="form-control" name="endDate"
																id="endDate" placeholder="End Date">
														</div>
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
		src="<c:url value='resources/hrm/ajax/salaryMonthEndFor04.js'/>"></script>
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