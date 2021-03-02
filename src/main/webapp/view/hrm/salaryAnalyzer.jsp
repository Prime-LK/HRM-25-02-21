<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<html lang="en">
<head>
<%@include file="../../WEB-INF/jsp/head.jsp"%>
<link href="<c:url value='resources/hrm/css/salaryAnalyze.css'/>"
	rel="stylesheet">
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
								<form:form action="saveSalaryAnalyzeData" method="post"
									modelAttribute="salaryAnalyzeForm" id="formSalaryAnalyze">
									<div class="row">
										<div class="row form-group col-7" id="yearDiv">
											<label id="lsd1">Year</label>
											<div class="col">
												<form:input type="text" class="yearpicker form-control"
													path="saPK.year" placeholder="Enter Process Year" id="year" />
											</div>
										</div>

										<div class="row form-group col-4" id="yearDiv">
											<div>
												<a href="SalaryAnalyzeReport"
													class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
													class="fas fa-download fa-sm text-white-50"></i> Generate
													Report</a>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="row form-group col-7" id="monthDiv">
											<label id="led1">Month</label>
											<div class="col">
												<form:select id="month" class="form-control"
													path="saPK.month">
													<form:option value="">Select Month</form:option>
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

									<div class="row">
										<div class="row form-group col-7" id="depDIv">
											<label id="dLbl">Department</label>
											<div class="col">
												<form:select id="depID" class="form-control"
													path="saPK.depatment.depID">
													<form:option value="">Select Department</form:option>
													<c:forEach items="${departments}" var="b">
														<form:option value="${b.depID}">${b.department}</form:option>
													</c:forEach>
												</form:select>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="row form-group col-7" id="adjType">
											<label id="dLbl">Salary Adjustment Type</label>
											<div class="col">
												<form:select id="adjType" class="form-control"
													path="saPK.addDedType.deductTypeCode">
													<form:option value="">Select Allowance Type</form:option>
													<c:forEach items="${Allowances}" var="b">
														<form:option value="${b.deductTypeCode}">${b.desc}</form:option>
													</c:forEach>
												</form:select>
											</div>
										</div>

										<!-- <div class="col-4 form-group" id="alloIdDiv">
															<input type="text" class="form-control"
																id="allowanceID" readOnly>
														</div> -->

										<div class="col-4 form-group" id="alloIdDiv">
											<input type="text" class="form-control" name="company.comID"
												value="<%=session.getAttribute("company.comID")%>"
												id="comID" readOnly>
										</div>

										<div class="form-check ml-3 mr-3" id="cbDiv">
											<input type="checkbox" class="form-check-input"
												id="addAllAllowance" onchange="loadAllAllowances()"
												onclick="saveListOfData()"> <label
												class="form-check-label" for="addAllAllowance">All</label>
										</div>

										<div class="form-group ml-1" id="addBtn">
											<button type="submit" id="btnOne"
												class="btn btn-success btn-sm">Add</button>
										</div>

										<div class="form-group ml-1" id="clearBtn">
											<button type="button" id="btnTwo" onclick="clearAllowance()"
												class="btn btn-danger btn-sm">Clear</button>
										</div>
									</div>
								</form:form>
								<div class="row">
									<div class="col-7" id="alloTableDiv">
										<div class="table-wrapper-scroll-y my-custom-scrollbar">
											<table class="table table-bordered table-striped mb-0"
												id="alloTable">
												<thead>
													<tr>
														<th scope="col">Allowance ID</th>
														<th scope="col">Allowance Name</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${allSalaryAnalize}" var="na">
														<tr>
															<td id="tNid">${na.saPK.addDedType.deductTypeCode}</td>
															<td id="tNa">${na.saPK.addDedType.desc}</td>

														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group ml-3 mt-3 mb-3">
										<button type="button" id="viewBtn" class="btn btn-info btn-sm"
											onclick="getTable02Data()">View</button>
									</div>
								</div>

								<div class="rwo">
									<div id="detailsTableDiv">
										<div class="scrollable">
											<table class="table table-hover" width="100%" cellspacing="0"
												id="detailsTbl">
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