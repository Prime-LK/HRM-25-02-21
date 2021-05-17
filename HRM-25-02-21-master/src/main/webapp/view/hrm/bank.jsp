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

#bbIDDiv {
	display: none;
}

.scrollable {
	height: 400px;
	overflow: scroll;
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
							<div class="col-xl-2 col-lg-2">
								<h2 class="text-white pb-2 fw-bold">Bank Branch Details</h2>
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
								<form:form action="saveBank" method="post" modelAttribute="bank">
									<div class="form-group row">
										<div class="col-sm-5">
											<div class="col-sm-60 mb-1 mb-sm-3">
												<div class="col-sm-30 mb-1 mb-sm-3">
													<label>Bank Name</label>
													<form:select
														class="form-control form-control-user  text-capitalize"
														path="bankid.bankid" required="true">
														<form:option value="" selected="true">Select Bank Name</form:option>
														<c:forEach items="${bankmastertable}" var="b">
															<form:option value="${b.bankid}">${b.bankName}</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
										</div>
										<div class="col-sm-2 mt-4">
											<!-- <div class="col-sm-5"> -->
											<div class="col-sm-60 mb-1 mb-sm-3" id="bbIDDiv">
												<!-- <label>Branch Code</label> -->
												<form:input class="form-control form-control-user"
													id="branchID" path="branchID"
													placeholder="Enter Branch Code" />
												<form:errors path="branchID" cssClass="error1 text-danger" />
												<!-- </div> -->
											</div>
										</div>

										<div class="col-sm-3 mt-4">
											<div class="col-sm-30 mb-1 mb-sm-3">
												<!-- <label>Company ID</label> -->
												<input type="hidden" name="company.comID"
													class="form-control" id="comID"
													value="<%=session.getAttribute("company.comID")%>"
													placeholder="Company ID" />
											</div>
										</div>
									</div>

									<div class="form-group row">
										<div class="col-sm-5">
											<div class="col-sm-60 mb-1 mb-sm-3">
												<label>Branch Name</label>
												<form:input class="form-control form-control-user"
													id="branch" path="branch" placeholder="Enter Branch Name" />
												<form:errors path="branch" cssClass="error1 text-danger" />
											</div>
										</div>
									</div>

									<div class="form-group row">
										<div class="col-sm-5">
											<div class="col-sm-60 mb-1 mb-sm-3">
												<label>Contact Number</label>
												<form:input class="form-control form-control-user"
													id="contactNo" path="contactNo"
													placeholder="Enter Contact Number" />
												<form:errors path="contactNo" cssClass="error1 text-danger" />
											</div>
										</div>
									</div>

									<div class="form-group row">
										<div class="col-sm-5">
											<div class="col-sm-60 mb-1 mb-sm-3">
												<label>Address</label>
												<form:textarea class="form-control form-control-user"
													id="address" path="address" placeholder="Enter Address" />
												<form:errors path="address" cssClass="error1 text-danger" />
											</div>
										</div>
									</div>

									<div class="form-group row">
										<div class="col-sm-5">
											<div class="col-sm-60 mb-1 mb-sm-3">
												<label>Email</label>
												<form:input class="form-control form-control-user"
													id="email" path="email" placeholder="Enter Email" />
												<form:errors path="email" cssClass="error1 text-danger" />
											</div>
										</div>

									</div>

									<!-- <div class="row "> -->
									<div class="col-3 mb-4">
										<input type="submit" class="btn btn-success btn-sm"
											value="Add Bank Branch"> <input type="reset"
											class="btn btn-danger btn-sm" value="Reset">
									</div>
									<!-- </div> -->

								</form:form>
								<div class="col-10">
									<div class="scrollable">
										<table class="table table-hover" width="100%" cellspacing="0"
											id="tableEmpAdd">
											<thead>
											</thead>
											<tbody>
												<c:forEach items="${bankLists}" var="bank1">
													<tr>
														<td><a href="updateBank?branchID=${bank1.branchID}"><i
																class="far fa-edit"></i></a></td>
														<td id="tNid">${bank1.bankid.bankName}</td>
														<td id="tNa">${bank1.branch}</td>
														<td id="tNa2">${bank1.address}</td>
														<td id="tNa2">${bank1.contactNo}</td>
														<td id="tNa2">${bank1.email}</td>
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