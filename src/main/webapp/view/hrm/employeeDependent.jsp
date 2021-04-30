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

#tableDType {
	word-break: keep-all;
}

.error1 {
	color: red;
}

#comDiv {
	display: none;
}

#empDiv {
	display: none;
}

#firstColumn {
	margin-top: -10px;
}

.scrollable {
	height: 200px;
	overflow: scroll;
}
</style>

</head>
<body onload="loadGrid();checkStatusofDropdowns();">
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
												href="register">Employee</a></li>
											<li class="nav-item"><a class="nav-link"
												href="employeeDetails">Employment</a></li>
											<li class="nav-item"><a class="nav-link"
												href="employeeQualification">Qualification & Membership</a></li>
											<li class="nav-item"><a class="nav-link"
												href="workExperience"> Work Experience</a></li>
											<li class="nav-item"><a class="nav-link"
												href="employeeSkill">Skills,Language & Activity</a></li>
											<li class="nav-item active"><a class="nav-link"
												href="employeeDependent">Dependent</a></li>
											<li class="nav-item"><a class="nav-link"
												href="employeeID">Documents</a></li>
										</ul>
									</div>
								</nav>
								<hr>
								<!-- end of middle nav -->
								<div class="container">
									<form:form method="post" modelAttribute="saveDependent"
										action="saveDependent" onsubmit="formValidation()"
										enctype="multipart/form-data" id="formRegister">
										<div class="row col-12">
											<div class="col-10 ml-3">
												<div id="tableClass" class="scrollable">
													<table class="table table-hover" width="100%"
														cellspacing="0" id="tableDType">
														<thead>
															<tr>
																<th>Dependent Type</th>
																<th>name</th>
																<th>DOB</th>
																<th>Contact No.</th>
																<th></th>
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
										<div class="row">
											<div class="col-5 mt-2">
												<!-- <p>Dependents</p> -->
												<div class="ml-4">
													<div class="form-group mb-2" id="empDiv">
														<!-- <label>Employee ID</label> -->
														<form:input type="hidden" path="dependentPK.empID.empID"
															value='<%=session.getAttribute("eid")%>'
															placeholder="Enter Employee ID" id="empID"
															class="form-control" />
													</div>

													<div class="form-group mb-2" id="comDiv">
														<!-- <label>Company ID</label> -->
														<input type="hidden" name="company.comID"
															class="form-control" id="comID"
															value="<%=session.getAttribute("company.comID")%>"
															placeholder="Company ID" />
													</div>

													<div class="form-group mb-2" id="firstColumn">
														<label>Dependent Type</label>
														<form:select path="dependentPK.dTypeID.dTypeID"
															class="custom-select" required="true">
															<form:option value="">Select Dependent</form:option>
															<c:forEach items="${dType}" var="dt">
																<form:option value="${dt.dTypeID}">${dt.dType}</form:option>
															</c:forEach>
														</form:select>

													</div>

													<div class="form-group mb-2">
														<label>Date Of Birth</label>
														<div class="input-group-append">
															<form:input type="date" id="dob"
																class="form-control form-control-user" path="dob" />
															<form:errors path="dob" cssClass="error1 text-danger" />
														</div>
													</div>
												</div>
											</div>
											<div class="col-4 offset-1" id="rowTwo">
												<div class="form-group mb-2">
													<label>Name</label>
													<form:input class="form-control form-control-user"
														id="name" path="name" placeholder="Enter Name" />
													<form:errors path="name" cssClass="error1 text-danger" />
												</div>
												<div class="form-group mb-2">
													<label>Contact No</label>
													<form:input class="form-control form-control-user"
														id="conNo" path="conNo" placeholder="Enter Contact No" />
													<form:errors path="conNo" cssClass="error1 text-danger" />
												</div>
												<div class="form-group row">
													<!-- <label class="col-2"></label> -->
													<div class="col-12 offset-12">
														<button type="submit" id="submitBtn"
															class="btn btn-success">
															<i class="fa fa-plus"></i> Add Dependent
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

	<script src="<c:url value='/resources/hrm/ajax/employeeDependent.js'/>"></script>

</body>
</html>