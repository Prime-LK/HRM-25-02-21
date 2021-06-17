<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<html lang="en">
<head>
<%@include file="../../WEB-INF/jsp/head.jsp"%>
<link href="<c:url value='/resources/hrm/css/userCommonDetails.css'/>"
	rel="stylesheet">
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

/* p {
	font-weight: bold;
	color: #4169E1;
	font-size: 15px;
} */

#tableEmpSkill {
	font-size: 12px;
	word-break: keep-all;
}

#tableEmpSkill #edit {
	width: 100px;
}

#tableEmpSkill #skill {
	width: 100px;
	color: black;
	font-weight: bold;
}

#tableEmpSkill #profeciency {
	color: #4169E1;
	font-weight: bold;
}

#tableEmpLan {
	font-size: 12px;
	word-break: keep-all;
}

#tableEmpLan #lan {
	width: 100px;
	color: black;
	font-weight: bold;
}

#tableEmpLan #desc {
	color: #4169E1;
	font-weight: bold;
}

#tableEmpAtc {
	font-size: 12px;
	word-break: keep-all;
}

#tableEmpAtc #act {
	width: 100px;
	color: black;
	font-weight: bold;
}

#tableEmpAtc #actDetails {
	color: #4169E1;
	font-weight: bold;
}

#formEmpSkill #desc1 {
	color: red;
}

#formEmpLan #desc2 {
	color: red;
}

#formEmpAct #desc3 {
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
								<div class="container mt--2">
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
											<li class="nav-item"><a class="nav-link"
												href="EmployeeQualification">Qualification & Membership</a></li>
											<li class="nav-item"><a class="nav-link"
												href="WorkExperience"> Work Experience</a></li>
											<li class="nav-item active"><a class="nav-link"
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
									<%-- <%@include file="userDefault.jsp"%> --%>
									<div class="container">
									</div>

									<div class="col-5 offset-3">
										<!-- <div class="col-12 offset-11"> -->
										<form:form method="post" modelAttribute="saveEmpActivity"
											action="saveEet" onsubmit="formValidation()"
											enctype="multipart/form-data" id="formEmpAct">

											<p>Activities</p>
											<div class="ml-3">
												<!-- <label>Employee ID</label> -->
												<form:input type="hidden" path="actPK.empID.empID"
													id="empID" value='<%=session.getAttribute("eid")%>'
													class="form-control" placeholder="Enter Employee ID" />

												<!-- <label>Company ID</label> -->
												<input type="hidden" name="company.comID"
													class="form-control" id="comID"
													value="<%=session.getAttribute("company.comID")%>"
													placeholder="Company ID" />

												<div class="form-group mb-2">
													<label>Activity Type</label>
													<form:select path="actPK.eType.actTypeID"
														class="custom-select" required="true">
														<form:option value="">Select Activity</form:option>
														<c:forEach items="${aTypes}" var="add">
															<form:option value="${add.actTypeID}">${add.type}</form:option>
														</c:forEach>
													</form:select>
												</div>

												<div class="form-group mb-2">
													<label>Activity Details</label>
													<form:textarea class="form-control form-control-user"
														id="act" path="act" placeholder="Enter Details" />
													<span id="desc3"></span>
												</div>

												<div class="col-12">
													<div class="form-group row">
														<div class="col-12 ml-5">
															<button type="submit" id="submitBtn"
																class="btn btn-success">
																<i class="fa fa-plus"></i> Add Activity
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
												id="tableEmpAtc">
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
										<!-- </div> -->
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