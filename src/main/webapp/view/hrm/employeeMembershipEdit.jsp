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
* {
	text-transform: capitalize;
}

table td {
	font-size: 12px;
	font-color: black;
	font-weight: bold;
}

#tableEmpQua #award {
	color: #8B008B;
}

#tableEmpQua #detials {
	color: #4169E1;
}

#tableEmpQua #qua {
	color: black;
}

#tableEmpMem #mem {
	color: black;
}

#tableEmpMem #de {
	color: #4169E1;
}

#tableEmpMem #since {
	color: #8B008B;
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
								<div class="container mt--5">
									<div class="row">
										<div class="col-sm">
											<div class="row" id="comDeRow">
												<div class="col-2">
													<!-- <input type="file" class="file" accept="image/*"
													id="profileImg" name="profileImg"> -->

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
													<!-- <div class="input-group mt--2">
													<div class="input-group-append mt-3">
														<button type="button" id="button"
															class="browse btn btn-success btn-sm">Upload</button>
													</div>
												</div> -->
												</div>
												<div class="col-10 mt--2">
													<div class="row ml-3">
														<div class="col-12">
															<div class="form-group">
																<span> <c:if test="${eid != null}">
																		<span id="comDeId"><%=session.getAttribute("eid")%></span>
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
								<!-- begin of middle nav -->
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
								<!-- end of middle nav -->
								<div class="col-6">
									<div class="col-12">
										<form:form method="post" modelAttribute="empMem"
											action="empMembershipACT" onsubmit="formValidation()"
											enctype="multipart/form-data" id="formEmpMem">

											<!-- <p id="title">Membership</p> -->
											<div class="">
												<div class="form-group mb-2">
													<!-- <label>Employee ID</label> -->
													<form:input type="hidden"
														path="employeeMembershipPK.empID.empID" id="empID"
														class="form-control" placeholder="Enter Employee ID"
														value='<%=session.getAttribute("eid")%>' />
												</div>

												<div class="form-group mb-2">
													<!-- <label>Company ID</label> -->
													<input type="hidden" name="company.comID"
														class="form-control" id="comID"
														value="<%=session.getAttribute("company.comID")%>"
														placeholder="Company ID" />
												</div>

												<div class="form-group mb-2">
													<label>Membership</label>
													<form:select
														path="employeeMembershipPK.membershipInformation.memID"
														class="custom-select" required="true">
														<form:option value="">Select Membership Type</form:option>
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
														<div class="col-12">
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
										<div id="tableClass" class="scrollable">
											<table class="table table-hover" width="100%" cellspacing="0"
												id="tableEmpMem">
												<thead>
													<tr>
													</tr>
												</thead>
												<tbody>
													<tr>

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
			<%@include file="../../WEB-INF/jsp/footer.jsp"%>
		</div>
	</div>
	<%@include file="../../WEB-INF/jsp/commJs.jsp"%>



</body>
</html>