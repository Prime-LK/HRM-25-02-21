<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<html lang="en">
<head>
<link href="<c:url value='/resources/hrm/css/userCommonDetails.css'/>"
	rel="stylesheet">
<%-- <link href="<c:url value='/resources/hrm/css/empQualification&Membership.css'/>"
	rel="stylesheet"> --%>
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
#titleMem, #titleQua {
	font-weight: bold;
	color: #4169E1;
	font-size: 15px;
}

* {
	text-transform: capitalize;
}

#tableEmpQua #editQua {
	text-align: right;
}

#formEmpQua #descFeedback {
	color: red;
}

#formEmpQua #awardFeedback {
	color: red;
}

#formEmpMem #descriptionFeedback {
	color: red;
}

#formEmpQua #memSinceFeedback {
	color: red;
}

#empIDDiv {
	display: none;
}

#comDiv {
	display: none;
}

#tableClass th, td {
	font-size: 8px;
}

.scrollable {
	height: 300px;
	overflow: scroll;
}

#comDeId {
	color: #FFFFFF;
	font-weight: bold;
	font-size: 12px;
}

#btnDiv {
	display: none
}

.main-panel {
	text-transform: capitalize;
}
</style>

</head>
<body onload="loadGrid(); loadMemGrid(); checkStatusofDropdowns();">
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
								<div class="container mt--2">
									<div class="row">
										<div class="col-sm">
											<div class="row">
												<div class="col-2">

													<c:if test="${eImg != null}">
														<img
															src="data:image/jpg;base64,<%=session.getAttribute("eImg")%>"
															id="preview" class="border border-dark rounded-lg"
															style="width: 80px; height: 80px;">
													</c:if>
													<c:if test="${eImg == null}">
														<img src="resources/img/user-default.jpg" id="preview"
															class="border border-dark rounded-lg"
															style="width: 80px; height: 80px;">
													</c:if>
												</div>
												<div class="col-10 mt--2">
													<div class="row ml-3">
														<div class="col-12">
															<div class="form-group">
																<span> <c:if test="${eid != null}">
																		<div>
																			<span id="comDeId"><%=session.getAttribute("eid")%></span>
																		</div>
																	</c:if>
																</span> <span> <c:if
																		test="${ename != null || lastName != null }">
																		<div>
																			<span id="comDeId"><%=session.getAttribute("ename")%></span>
																			<span id="comDeId"><%=session.getAttribute("lastName")%></span><br>
																			<span id="comDeId"><%=session.getAttribute("addLine01")%></span><br>
																			<span id="comDeId"><%=session.getAttribute("addLine02")%></span>
																		</div>
																	</c:if>
																</span>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-sm mt-5"></div>
									</div>
								</div>
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
								<nav class="navbar navbar-expand-sm">
									<button class="navbar-toggler" data-toggle="collapse"
										data-target="#navbarMenu">
										<span class="navbar-toggler-icon"></span>
									</button>
									<div class="collapse navbar-collapse" id="navbarMenu">
										<ul class="navbar-nav mr-auto">
											<li class="nav-item" id=""><a class="nav-link"
												href="Register">Employee</a></li>
											<li class="nav-item"><a class="nav-link"
												href="EmployeeDetails">Employment</a></li>
											<li class="nav-item active"><a class="nav-link"
												href="EmployeeQualification">Qualification & Membership</a></li>
											<li class="nav-item"><a class="nav-link"
												href="WorkExperience"> Work Experience</a></li>
											<li class="nav-item"><a class="nav-link"
												href="EmployeeSkill">Skills,Language & Activity</a></li>
											<li class="nav-item"><a class="nav-link"
												href="EmployeeDependent">Dependent</a></li>
											<li class="nav-item"><a class="nav-link"
												href="EmployeeDocument">Documents</a></li>
										</ul>
									</div>
								</nav>
								<hr>
								<div class="container">
									<form:form method="post" modelAttribute="empQua"
										action="saveEmpQua" onsubmit="formValidation()"
										enctype="multipart/form-data" id="formEmpQua">
										<div class="row">
											<div class="col-6">
												<div class="form-group mb-2">
													<p id="titleQua">Qualification</p>
													<div class="ml-4">
														<div id="tableEmpQua" class="scrollable">
															<table class="table table-hover" width="100%">
																<thead>
																	<tr>
																		<th scope="col">Qualification Type</th>
																		<th scope="col">Description</th>
																		<th scope="col">Awarded on</th>
																		<th scope="col"></th>
																	</tr>
																</thead>
																<tbody>
																</tbody>
															</table>
														</div>
														<!-- <label>Employee ID</label> -->
														<form:input type="hidden" path="quaPK.empID.empID"
															id="empID" class="form-control form-control-user"
															placeholder="Enter Employee ID"
															value='<%=session.getAttribute("eid")%>' />

														<input type="hidden" name="company.comID"
															class="form-control" id="comID"
															value="<%=session.getAttribute("company.comID")%>"
															placeholder="Company ID" readOnly />


														<div class="form-group mb-2">
															<label>Qualification</label>
															<form:select path="quaPK.qMaster.qid" id="qua"
																class="custom-select" required="true">
																<form:option value="">--SELECT--</form:option>
																<c:forEach items="${qMaster}" var="q">
																	<form:option value="${q.qid}">${q.qualification}</form:option>
																</c:forEach>
															</form:select>
														</div>

														<div class="form-group mb-2">
															<label>Description</label>
															<form:textarea class="form-control form-control-user"
																id="desc" path="desc" placeholder="Enter Description" />
															<span id="descFeedback"></span>
														</div>

														<div class="form-group mb-2">
															<label>Awarded On</label>
															<div class="input-group-append">
																<form:input type="date"
																	class="form-control form-control-user" id="award"
																	path="award" />
															</div>
															<span id="awardFeedback"></span>
														</div>

														<div class="col-12">
															<div class="form-group row">
																<div class="col-12">
																	<button type="submit" id="submitBtn"
																		class="btn btn-success">
																		<i class="fa fa-plus"></i> Add Qualification
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

								<div class="col-6 mt-2">
									<form:form method="post" modelAttribute="empMem"
										action="empMembershipACT" onsubmit="formValidation()"
										enctype="multipart/form-data" id="formEmpMem">
										<p id="titleMem">Membership</p>
										<div class="ml-4">
											<div id="tableEmpMem" class="scrollable">
												<table class="table table-hover" width="100%">
													<thead>
														<tr>
															<th scope="col">Membership Type</th>
															<th scope="col">Description</th>
															<th scope="col">Since</th>
															<th scope="col"></th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
											</div>
											<div class="form-group mb-2" id="empIDDiv">
												<!-- <label>Employee ID</label> -->
												<form:input type="hidden"
													path="employeeMembershipPK.empID.empID" id="empID"
													class="form-control" placeholder="Enter Employee ID"
													value='<%=session.getAttribute("eid")%>' readOnly="true" />
											</div>

											<div class="form-group mb-2" id="comDiv">
												<!-- <label>Employee ID</label> -->
												<input type="hidden" name="company.comID"
													class="form-control" id="comID"
													value="<%=session.getAttribute("company.comID")%>"
													placeholder="Company ID" readOnly />
											</div>

											<div class="form-group mb-2">
												<label>Membership</label>
												<form:select
													path="employeeMembershipPK.membershipInformation.memID"
													class="custom-select" required="true">
													<form:option value="">--SELECT--</form:option>
													<c:forEach items="${allMi}" var="add">
														<form:option value="${add.memID}">${add.memType}</form:option>
													</c:forEach>
												</form:select>
											</div>

											<div class="form-group mb-2">
												<label>Membership Details</label>
												<form:textarea class="form-control form-control-user"
													id="description" path="description"
													placeholder="Enter Details" />
												<span id="descriptionFeedback"></span>
											</div>

											<div class="form-group mb-2">
												<label>Member Since</label>
												<div class="input-group-append">
													<form:input id="memSince" type="date"
														class="form-control form-control-user" path="since" />
												</div>
												<span id="memSinceFeedback"></span>
											</div>

											<div class="col-12">
												<div class="form-group row">
													<div class="col-10">
														<button type="submit" id="submitBtn"
															class="btn btn-success">
															<i class="fa fa-plus"></i> Add Membership
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
									</form:form>
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
	<script src="<c:url value='/resources/hrm/ajax/employeeQuaMem.js'/>"></script>

</body>
</html>