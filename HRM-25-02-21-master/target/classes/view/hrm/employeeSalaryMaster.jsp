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

#processUserDiv, #comDiv {
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
								<h2 class="text-white pb-2 fw-bold">Employee Salary Master</h2>
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
								<form:form action="saveEmpSalaryMaster" method="post"
									onSubmit="return validateForm()" id="EmpSalaryMasterform"
									modelAttribute="empSalaryMaster">
									<div class="form-group row">
										<div class="col-sm-3">
											<label>Employee ID</label>
											<form:input class="form-control form-control-user" id="empID"
												path="empSalaryPK.empID.empID" placeholder="Employee ID"
												value='<%=session.getAttribute("eid")%>' />
											<span id="div1"></span>
										</div>
										<div class="col-sm-3 mt-4" id="processUserDiv">
											<div class="col-sm-30 mb-1 mb-sm-3">
												<!-- <label>Process_User_ID</label> -->
												<form:input type="hidden" path="empSalaryPK.processID"
													class="form-control" placeholder="Enter Detail ID"
													value="${id.processID}" id="processID" readOnly="true" />
											</div>
										</div>
										<div class="col-sm-30 mb-1 mb-sm-3" id="comDiv">
												<!-- <label>Company ID</label> -->
												<input type="hidden" name="company.comID"
													class="form-control" id="comID"
													value="<%=session.getAttribute("company.comID")%>"
													placeholder="Company ID" />
											</div>
										<div class="col-sm-3">
												<label>Basic Salary</label>
												<form:input class="form-control form-control-user"
													id="basicSlary" path="basicSalary" placeholder="Basic Salary" />
												<span id="div2"></span>
											</div>
											<div class="col-sm-3">
												<label>Process Date</label>
												<form:input path="processDate" type="date"
													class="form-control"
													placeholder="Enter Date Of Birth" id="processDate" />
												<span id="div3"></span>
											</div>
									</div>
									<div class="form-group row ">
										<div class="col-6 ">
											<button type="submit" id="submitBtn" class="btn btn-success">
												<i class="fa fa-plus"></i> Add Details
											</button>
											<button type="reset" id="resetBtn"
												class="browse btn btn-danger">
												<i class="fa fa-arrow-circle-right" aria-hidden="true"></i>
												Reset
											</button>
										</div>
									</div>
								</form:form>
								<div class="col-9">
									<div class="scrollable">
										<table class="table table-hover" width="100%" cellspacing="0"
											id="tableEmpSalaryMaster">
											<thead>
												<tr>
													<th>Employee ID</th>
													<th>Basic Salary</th>
													<th>Process Date</th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${getAllEmpSaMaster}" var="a">
													<tr>
														<td>${a.empSalaryPK.empID.empID}</td>
														<td>${a.basicSalary}</td>
														<td>${a.processDate}</td>
														<td><a
															href="updateSalaryMaster?empID=${a.empSalaryPK.empID.empID} &processID=${a.empSalaryPK.processID}">
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