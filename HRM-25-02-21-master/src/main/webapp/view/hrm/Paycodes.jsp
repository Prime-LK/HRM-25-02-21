<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<html lang="en">
<head>
<%@include file="../../WEB-INF/jsp/head.jsp"%>

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
#payPeriodIDDiv {
	display:none;
}

* {
	text-transform: capitalize;
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
								<h2 class="text-white pb-2 fw-bold">Pay Code Details</h2>
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
								<form:form action="savePaycodes" method="post"
									modelAttribute="Paycodes">
									<blockquote class="text-danger">${mesg}</blockquote>
									<div class="form-group row">
										<div class="col-3" id="payPeriodIDDiv">
											<label>Pay Period</label>
											<form:input path="PeriodID.payPeriodID" type="text"
												onChange="loadPeriodData();loadSavedData();"
												class="form-control " id="payPeriodID" readOnly="true"/>
											<span id="div1"></span>
										</div>
										<div class="col-3">
											<label>Start Date</label>
											<form:input path="startDate" type="date"
												class="form-control " placeholder="Enter Date Of Birth"
												id="startDate" />

											<span id="div2"></span>
										</div>
										<div class="col-3">
											<label>End Date</label>
											<form:input path="endDate" type="date"
												onchange="loadpayperiodfromdates();" class="form-control "
												placeholder="Enter Date Of Birth" id="endDate" />
											<span id="div3"></span>
										</div>
									</div>
									<div class="form-group row">
										<div class="col-3">
											<label>Pay Code</label>
											<form:input path="payCode" type="text" class="form-control "
												id="payCode" placeholder="payCode Name"/>
											<span id="div4"></span>
										</div>
										<div class="col-3">
											<label>Remarks</label>
											<form:input path="remarks" type="text" class="form-control "
												id="remarks" placeholder="Remark"/>
											<span id="div5"></span>
										</div>
										<div class="col-4 ">
											<form:input path="payCodeID" type="hidden" readOnly="true"
												class="form-control " id="payPeriodID" />
										</div>
										<div class="col-4 ">
											<input type="hidden" name="company.comID"
												class="form-control" id="comID"
												value="<%=session.getAttribute("company.comID")%>"
												placeholder="Company ID" />
										</div>
									</div>
									<div class="form-group row">
										<div class="col-3">
											<div class="col-sm-60">
												<label>Status</label>
												<form:input path="status" type="text" readOnly="true"
													class="form-control" value="Open" id="status" />
												<span id="div6"></span>

											</div>
										</div>
									</div>
									<div class="form-group row">
										<div class="col-12 mb-4 offset-12">
											<button type="submit" id="submitBtn" class="btn btn-success">
												<i class="fa fa-plus"></i> Create PayCode
											</button>
											<button type="reset" id="resetBtn"
												class="browse btn btn-danger">
												<i class="fa fa-arrow-circle-right" aria-hidden="true"></i>
												Reset
											</button>
										</div>
									</div>
								</form:form>
								<div class="col-12">
									<div class="scrollable">
										<table class="table table-hover" width="100%" cellspacing="0"
											id="tablePayCode">
											<thead>
												<tr>
													<th>PayCode</th>
													<th>Start Date</th>
													<th>End Date</th>
													<th>Remark</th>
													<th>Status</th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody>
											</tbody>
										</table>
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
	<script src="<c:url value='resources/hrm/ajax/payCode.js'/>"></script>


</body>
</html>