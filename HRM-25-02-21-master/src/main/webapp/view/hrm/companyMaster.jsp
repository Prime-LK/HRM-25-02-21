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

/* custom css */
#conIDDiv {
	display: none;
}

.scrollable {
	height: 400px;
	overflow: scroll;
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
							<div class="col-xl-2 col-lg-2">
								<h2 class="text-white pb-2 fw-bold">Company Details</h2>
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
								<form:form action="saveCompanyMasterData" method="post"
									modelAttribute="companyMasterObject" id="companyMasterForm"
									onsubmit="return formCountry()" name="formCountryMaster">
									<div class="form-group row">
										<div class="col-sm-5">
											<div class="col-sm-60">
												<label>Company Name</label>
												<form:input class="form-control form-control-user"
													id="comName" path="comName" placeholder="Enter Company" />
												<form:errors path="comName" cssClass="error1 text-danger" />
											</div>
										</div>

										<div class="col-sm-3 mt-4" id="conIDDiv">
											<div class="col-sm-30">
												<!-- <label>Company ID</label> -->
												<form:input class="form-control form-control-user"
													id="comID" path="comID" placeholder="Enter Company ID"
													readOnly="true" />
											</div>
										</div>
									</div>

									<div class="form-group row">
										<div class="col-sm-5">
											<div class="col-sm-60">
												<label>Address</label>
												<form:input class="form-control form-control-user"
													id="address" path="address" placeholder="Enter Address" />
												<form:errors path="address" cssClass="error1 text-danger" />
											</div>
										</div>
									</div>

									<div class="form-group row">
										<div class="col-sm-5">
											<div class="col-sm-60">
												<label>Contact number</label>
												<form:input class="form-control form-control-user"
													id="conNo" path="conNo" placeholder="Enter Contact Number" />
												<form:errors path="conNo" cssClass="error1 text-danger" />
											</div>
										</div>
									</div>

									<div class="col-7 mb-4 offset-5">
										<button type="submit" class="btn btn-success btn-sm"
											id="alert_demo_3_3">Add Company Details</button>
										<input type="reset" class="btn btn-danger btn-sm"
											value="Reset">
									</div>

								</form:form>
								<div class="col-10">
									<div class="scrollable">
										<table class="table table-hover" width="100%" cellspacing="0"
											id="companyTable">
											<thead>
												<tr>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${listOfCompanyDetails}" var="na">
													<tr>
														<td><a href="UpdateCompany?id=${na.comID}"> <i
																class="far fa-edit"></i>
														</a></td>
														<td id="tNa">${na.comName}</td>
														<td id="tNa">${na.address}</td>
														<td id="tNa">${na.conNo}</td>
													</tr>
												</c:forEach>
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