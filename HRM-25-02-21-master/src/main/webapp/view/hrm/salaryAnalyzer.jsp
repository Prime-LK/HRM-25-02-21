<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<html lang="en">
<head>
<%@include file="../../WEB-INF/jsp/head.jsp"%>
<link href="<c:url value='resources/hrm/css/yearpicker.css'/>"
	rel="stylesheet" type="text/css">
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
	color: 'blue';
}

.icon-pre-ve {
	width: 150%;
}
/* from css */
.scrollable {
	height: 200px;
	overflow: scroll;
}

* {
	text-transform: capitalize;
}

#hiddenDicv {
	display: none; 
	
}
</style>
</head>
<body onload="inVisibleFields()">
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
								<h2 class="text-white pb-2 fw-bold">Salary Analyze</h2>
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
								<form:form action="saveSalaryAnalyze"
									modelAttribute="salaryAnalyze">
									<div class="row">
										<div class="col-6">
											<div class="row form-group" id="yearDiv">
												<label class="col-5">Year</label>
												<div class="col-7">
													<form:input type="text" class="yearpicker form-control"
														path="year" autocomplete="off" placeholder="Pick a Year" id="year" />
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-6">
											<div class="row form-group" id="monthDiv">
												<label class="col-5">Month</label>
												<div class="col-7">
													<form:select id="month" class="form-control" path="month">
														<form:option value="">--SELECT--</form:option>
														<form:option value="01">January</form:option>
														<form:option value="02">February</form:option>
														<form:option value="03">march</form:option>
														<form:option value="04">April</form:option>
														<form:option value="05">May</form:option>
														<form:option value="06">June</form:option>
														<form:option value="07">July</form:option>
														<form:option value="08">August</form:option>
														<form:option value="09">September</form:option>
														<form:option value="10">October</form:option>
														<form:option value="11">November</form:option>
														<form:option value="12">December</form:option>
													</form:select>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-6">
											<div class="row form-group" id="depDIv">
												<label class="col-5">Department</label>
												<div class="col-7">
													<form:select id="depID" class="form-control"
														path="depatment.depID">
														<option value="">--SELECT--</option>
														<c:forEach items="${departmentsList}" var="b">
															<form:option value="${b.depID}">${b.department}</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-6">
											<div class="row form-group">
												<label class="col-5">Allowance Type</label>
												<div class="col-7">
													<form:select id="adjType" class="form-control"
														path="addDedType.deductTypeCode">
														<option value="">--SELECT--</option>
														<c:forEach items="${payAddDeductTypeList}" var="b">
															<form:option value="${b.deductTypeCode}">${b.desc}</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
										</div>
										<div class="row col-6">
											<div class="form-check">
												<div class="form-check">
													<label class="form-check-label"> <input
														onClick="addDataToTbl()" id="addAllAllowance"
														class="form-check-input" type="checkbox" value="checkbox">
														<span class="form-check-sign">All</span>
													</label>
												</div>
											</div>
											<div class="form-group mt-2">
												<button type="submit"
													class="btn btn-success btn-sm">Add</button>
											</div>
											<div class="form-group mt-2">
												<button type="reset" onclick="clearAllowance()"
													class="btn btn-danger btn-sm">Clear</button>
											</div>
										</div>
									</div>
									<div class="row" id="hiddenDicv">
										<div class="col-6">
											<div class="row form-group">
												<label class="col-5">Company No.</label>
												<div class="col-7">
													<input type="text" class="form-control"
														name="company.comID"
														value="<%=session.getAttribute("company.comID")%>"
														id="comID" readOnly>
												</div>
											</div>
										</div>
									</div>
									<div class="row" id="hiddenDicv">
										<div class="col-6">
											<div class="row form-group">
												<label class="col-5">Salary Analyze No.</label>
												<div class="col-7">
													<form:input type="text" class="form-control" path="saID"
														id="saID" readOnly="true"/>
												</div>
											</div>
										</div>
									</div>
								</form:form>
								<div class="row">
									<div class="col-12">
										<div class="row form-group">
											<label class="col-2 ml-4"></label>
											<div class="col-7 ml-2">
												<div id="alloTableDiv">
													<div class="scrollable">
														<table class="table table-bordered table-striped mb-0"
															id="alloTable">
															<thead>
																<tr>
																	<th scope="col">Allowance No.</th>
																	<th scope="col">Allowance Name</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach items="${allSalaryAnalizes}" var="na">
																	<tr>
																		<td id="tNid">${na.addDedType.deductTypeCode}</td>
																		<td id="tNa">${na.addDedType.desc}</td>
																	</tr>
																</c:forEach>
															</tbody>
														</table>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-6">
										<div class="row form-group">
											<label class="col-5"></label>
											<div class="col-7">
												<button type="submit" class="btn btn-info btn-sm"
													onClick="getTableData()">View</button>
												<a href="SalaryAnalyzeReport"
													class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm ml-3"><i
													class="fas fa-download fa-sm text-white-50"></i> Generate
													Report</a>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-12">
										<div class="form-group">
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
			<%@include file="../../WEB-INF/jsp/footer.jsp"%>
		</div>
	</div>
	<%@include file="../../WEB-INF/jsp/commJs.jsp"%>
	<script src="<c:url value='/resources/hrm/ajax/salaryAnalyzer.js'/>"></script>
	<!-- year picker js -->
	<script src="<c:url value='/resources/hrm/js/yearpicker.js'/>"></script>
	<!-- table scroller -->
	<script src="<c:url value='/resources/hrm/js/table-scroller.js'/>"></script>
</body>
</html>