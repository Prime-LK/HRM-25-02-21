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
								<form:form action="saveSalaryMonthEndForm03" method="post"
									onSubmit="formValidation()" id="salaryHisMasForm03"
									modelAttribute="salaryHisMasForm03">
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
													<div class="form-group">
														<label>PayCode</label> <select
															class="form-control text-capitalize" id="payCodeID"
															name="payCodeID">
															<option value="">--SELECT--</option>
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
									</div>
									<%-- <div class="row">
										<div class="col-6">
											<div class="form-group row">
												<label class="col-5 mt-1">Year</label>
												<div class="col-7">
													<input type="text" class="yearpicker form-control "
														placeholder="Enter Process Year" id="startDate"
														onchange="getPeriodID3()">
												</div>
											</div>
										</div>
										<div class="col-6">
											<div class="form-group row">
												<label class="col-2 mt-1">Start Date</label>
												<div class="col-7">
													<input name="startDate" type="text" class="form-control"
														id="startDateValue" placeholder="Start Date" readOnly />
												</div>
											</div>
										</div>
										<div class="col" id="comDiv">
											<div class="form-group row">
												<div class="col-7">
													<div class="form-group col-6 row ml-3">
														 <label id="lcode">Company ID</label> 
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
												<label class="col-5 mt-1">Month</label>
												<div class="col-7">
													<select id="sa" class="form-control"
														onchange="loadPayPeriod()">
														<option value="">--SELECT--</option>
														<option value="01">January</option>
														<option value="02">February</option>
														<option value="03">march</option>
														<option value="04">April</option>
														<option value="05">May</option>
														<option value="06">June</option>
														<option value="07">July</option>
														<option value="08">August</option>
														<option value="09">September</option>
														<option value="10">October</option>
														<option value="11">November</option>
														<option value="12">December</option>
													</select>
												</div>
											</div>
										</div>
										<div class="col-6">
											<div class="form-group row">
												<label class="col-2 mt-1">End Date</label>
												<div class="col-7">
													<input name="endDate" type="text" class="form-control "
														id="endDateValue" placeholder="End Date" readOnly />
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
										<div class="col-6">
											<div class="form-group row">
												<label class="col-5 mt-1">PayCode</label>
												<div class="col-7">
													<select class="form-control text-capitalize" id="payCodeID"
														name="payCodeID" required>
														<option value="">--SELECT--</option>
														<c:forEach items="${payCodeList}" var="pc">
															<option value="${pc.payCodeID}">${payCode}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="col-6">
											<div class="form-group row">
												<label class="col-2 mt-1">Period</label>
												<div class="col-7">
													<input name="periodID" type="text" class="form-control"
														id="periodID" placeholder="PayPeriod" readOnly /> <span
														id="div2"></span>
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
	<script src="<c:url value='/resources/hrm/ajax/salaryMonthEndFor03.js'/>"></script>
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