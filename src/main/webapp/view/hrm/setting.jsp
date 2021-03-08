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
#conDiv {
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
								<h2 class="text-white pb-2 fw-bold">Allocate PayPeriods & PayCodes</h2>
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
								<form:form action="saveSetting" method="post"
									modelAttribute="setting">
									<div class="row">
										<div class="col-4">
											<div class="form-group row">
												<label>Do You want create multiple Pay Periods?</label>
											</div>
										</div>
										<div class="col-2">
											<div class="selectgroup w-100">
												<label class="selectgroup-item"> <input type="radio"
													name="multipayperiod" value="Yes" class="selectgroup-input">
													<span class="selectgroup-button">Yes</span>
												</label> <label class="selectgroup-item"> <input
													type="radio" name="multipayperiod" value="No"
													class="selectgroup-input"> <span
													class="selectgroup-button">No</span>
												</label>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-4">
											<div class="form-group row">
												<label>Do You want create multiple Pay Codes?</label>
											</div>
										</div>
										<div class="col-2 ">
											<div class="selectgroup w-100">
												<label class="selectgroup-item"> <input type="radio"
													name="multipaycode" value="Yes" class="selectgroup-input">
													<span class="selectgroup-button">Yes</span>
												</label> <label class="selectgroup-item"> <input
													type="radio" name="multipaycode" value="No"
													class="selectgroup-input"> <span
													class="selectgroup-button">No</span>
												</label>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-6">
											<div class="form-group row">
												<button type="submit" id="submitBtn" class="btn btn-success">
													<i class="fa fa-plus"></i> Allocate PayPeriod
												</button>
												<button type="reset" id="resetBtn"
													class="browse btn btn-danger ml-3">
													<i class="fa fa-arrow-circle-right" aria-hidden="true"></i>
													Reset
												</button>
											</div>
										</div>
										<div class="col-5">
											<div class="form-group row" id="conDiv">
												<form:input type="text" path="companyid" value="00001"
													class="border-0" />
											</div>
										</div>
									</div>
								</form:form>
								<div class="card">
								<div class="card-header">
										<div class="card-title">Current PayPeriods & PayCodes
											Allocation</div>
									</div> 
									<div class="card-body">
										<div class="col-6">
											<div class="table-responsive">
												<table class="table table-bordered">
													<thead>
														<tr>
															<th>Multiple PayPeriods</th>
															<th>Multiple PayCodes</th>
														</tr>
													</thead>
													<tbody>
														<tr>
															<c:forEach items="${getCurrentStatus}" var="na">
																<tr>
																	<td id="tNid">${na.multipayperiod}</td>
																	<td id="tNa">${na.multipaycode}</td>
																</tr>
															</c:forEach>
														</tr>
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
			<%@include file="../../WEB-INF/jsp/footer.jsp"%>
		</div>
	</div>
	<%@include file="../../WEB-INF/jsp/commJs.jsp"%>



</body>
</html>