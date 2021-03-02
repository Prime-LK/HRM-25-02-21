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
.scrollable {
	height: 400px;
	overflow: scroll;
}

* {
	text-transform: capitalize;
}

#ppIDDiv {
	display: none;
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
								<h2 class="text-white pb-2 fw-bold">Pay Period Details</h2>
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
								<form:form action="savePayPeriods" method="post"
									modelAttribute="createPayPeriod">
									<blockquote class="text-danger">${mesg}</blockquote>
									<div class="form-group row">
										<div class="col-sm-3 mt-4">
											<label>Start Date</label>
											<form:input path="startDate" type="date"
												class="form-control " placeholder="Enter Date Of Birth"
												id="startDate" />
											<span id="div1"></span>
										</div>
										<div class="col-sm-3 mt-4 ">
											<label>End Date</label>
											<form:input path="endDate" type="date" class="form-control "
												placeholder="Enter Date Of Birth" id="endDate" />
											<span id="div2"></span>
										</div>
										<div class="col-sm-3 mt-4 offset-2" id="ppIDDiv">
											<label>PayPeriod ID</label>
											<form:input path="payPeriodID" type="text" class="border-0"
												id="payPeriodID" />
											<span id="div3"></span>
										</div>
										<div class="col-sm-3 mt-4 offset-2">
											<!-- <label>Company ID</label> -->
											<input name="company.comID" type="hidden" class="border-0"
												id="comID"
												value="<%=session.getAttribute("company.comID")%>" /> <span
												id="div3"></span>
										</div>
									</div>
									<div class="form-group row">
										<div class="col-sm-3 mt-4">
											<label>Pay Date</label>
											<form:input path="payDate" type="date" class="form-control "
												placeholder="Enter Date Of Birth" id="payDate" />
											<span id="div3"></span>
										</div>
										<div class="col-sm-3 mt-4">
											<label class="">status</label>
											<form:input path="status" type="text" value="Open"
												class="form-control " id="status" />
										</div>
									</div>
									<div class="form-group row">
										<div class="col-12 mb-4">
											<button type="submit" id="submitBtn" class="btn btn-success">
												<i class="fa fa-plus"></i> Create PayPeriod
											</button>
											<button type="reset" id="resetBtn"
												class="browse btn btn-danger">
												<i class="fa fa-arrow-circle-right" aria-hidden="true"></i>
												Reset
											</button>
										</div>
									</div>
								</form:form>
								<div class="col-10">
									<div class="scrollable">
										<table class="table table-hover" width="100%" cellspacing="0"
											id="tableNaMaster">
											<thead>
												<tr>
													<!-- <th>PayPeriodID</th> -->
													<th>Start Date</th>
													<th>End Date</th>
													<th>Pay Date</th>
													<th>status</th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${payPeriodsList}" var="p">
													<tr>
														<%-- <td>${p.payPeriodID}</td> --%>
														<td>${p.startDate}</td>
														<td>${p.endDate}</td>
														<td>${p.payDate}</td>
														<td>${p.status}</td>
														<td><a
															href="updatepayPeriods?payPeriodID=${p.payPeriodID}">
																<i class="far fa-edit"></i>
														</a></td>
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

			</div>
			<%@include file="../../WEB-INF/jsp/footer.jsp"%>
		</div>
	</div>
	<%@include file="../../WEB-INF/jsp/commJs.jsp"%>



</body>
</html>