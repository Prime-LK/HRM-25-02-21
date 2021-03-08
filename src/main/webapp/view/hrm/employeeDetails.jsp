<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<html lang="en">
<link href="<c:url value='/resources/hrm/css/employeeDetails.css'/>"
	rel="stylesheet">
<link href="<c:url value='/resources/hrm/css/userCommonDetails.css'/>"
	rel="stylesheet"> 
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
</style>

</head>
<body onload="getDetails();checkStatusofDropdowns()">
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
								<!-- end of middle bar -->
								<nav class="navbar navbar-expand-sm">
									<button class="navbar-toggler" data-toggle="collapse"
										data-target="#navbarMenu">
										<span class="navbar-toggler-icon"></span>
									</button>
									<div class="collapse navbar-collapse" id="navbarMenu">
										<ul class="navbar-nav mr-auto">
											<li class="nav-item" id=""><a class="nav-link"
												href="register">Employee</a></li>
											<li class="nav-item active"><a class="nav-link"
												href="employeeDetails">Employment</a></li>
											<li class="nav-item"><a class="nav-link"
												href="employeeQualification">Qualification & Membership</a></li>
											<li class="nav-item"><a class="nav-link"
												href="workExperience"> Work Experience</a></li>
											<li class="nav-item"><a class="nav-link"
												href="employeeSkill">Skills,Language & Activity</a></li>
											<li class="nav-item"><a class="nav-link"
												href="employeeDependent">Dependent</a></li>
											<li class="nav-item"><a class="nav-link"
												href="employeeID">Documents</a></li>
										</ul>
									</div>
								</nav>
								<hr>
								<!-- end of middle bar -->
								<div class="col-12">
									<div class="card" id="userPreviousDetails">
										<div class="card-body">
											<div class="row">
												<div class="col-4">
													<div class="form-group row">
														<div class="col-6" id="card001">
															<ul class="list-group list-group-flush">
																<li class="list-group-item" id="lsTitle">EPF No</li>
																<li class="list-group-item" id="lsTitle">Joined
																	Date</li>
																<li class="list-group-item" id="lsTitle">Status</li>
																<li class="list-group-item" id="lsTitle">Basic
																	Salary</li>
																<li class="list-group-item" id="lsTitle">Designation</li>
															</ul>
														</div>
														<div class="col-6">
															<div class="" id="card001Details"></div>
														</div>
													</div>
												</div>
												<div class="col-4">
													<div class="form-group row">
														<div class="col-6" id="card002">
															<ul class="list-group list-group-flush">
																<li class="list-group-item" id="lsTitle">Category</li>
																<li class="list-group-item" id="lsTitle">Type</li>
																<li class="list-group-item" id="lsTitle">Department</li>
																<li class="list-group-item" id="lsTitle">Salary
																	Grade</li>
																<li class="list-group-item" id="lsTitle">Salary
																	Range</li>
															</ul>
														</div>
														<div class="col-6">
															<div class="" id="card002Details"></div>
														</div>
													</div>
												</div>
												<div class="col-4">
													<div class="form-group row">
														<div class="col-6" id="card003">
															<ul class="list-group list-group-flush">
																<li class="list-group-item" id="lsTitle">Reporter</li>
																<li class="list-group-item" id="lsTitle">Job
																	profile</li>
																<li class="list-group-item" id="lsTitle">Location</li>
																<li class="list-group-item" id="lsTitle">Resign
																	Date</li>
																<li class="list-group-item" id="lsTitle"><a
																	href="updateDetails?empID=<%=session.getAttribute("eid")%>"
																	class="btn btn-secondary btn-sm"> <span
																		class="btn-label"> <i class="fa fa-pen"></i>
																	</span> Edit Details
																</a></li>
															</ul>
														</div>
														<div class="col-6">
															<div class="" id="card003Details"></div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<form:form method="post" modelAttribute="EmpDetails"
										action="saveEmpDetails" enctype="multipart/form-data"
										id="formRegister">
										<div class="row" id="hiddenRow">
											<div class="col-3 ml-1" id="dePKDiv">
												<!-- <label>Employee Details ID</label> -->
												<form:input type="text" path="detailsPK.detailID"
													class="form-control" placeholder="Enter Detail ID"
													value="${id.detailID}" id="detailID" readOnly="true" />
											</div>
											<div class="col-3 ml-5" id="comDiv">
												<div class="form-group row">
													<!-- <label>Company ID</label> -->
													<input type="text" name="company.comID"
														class="form-control" id="comID"
														value="<%=session.getAttribute("company.comID")%>"
														placeholder="Company ID" readOnly />

												</div>
											</div>
											<div class="col-3 ml-5" id="empIDDiv">
												<div class="form-group row">
													<!-- <label>Employee ID</label> -->
													<input type="text" name="detailsPK.empID.empID"
														class="form-control" placeholder="Enter Employee ID"
														id="empID" value='<%=session.getAttribute("eid")%>'
														readOnly />
												</div>
											</div>
										</div>
										<div class="row ml-1">
											<div class="col-3">
												<div class="form-group row">
													<label>EPF No</label>
													<form:input path="epfNo" type="text" class="form-control"
														placeholder="Enter EPF Number" id="epfNo" />

												</div>
											</div>
											<div class="col-3 ml-5">
												<div class="form-group row">
													<label>Designation</label>
													<form:select class="form-control" path="designation.did">
														<form:option value="" selected="true">--SELECT--</form:option>
														<c:forEach items="${designations}" var="ds">
															<form:option value="${ds.did}">${ds.designation}</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
											<div class="col-3 ml-5">
												<div class="form-group row">
													<label>Joined Date</label>
													<form:input path="joinedDate" type="date"
														class="form-control" placeholder="Enter Joined Date"
														id="joinedDate" />
												</div>
											</div>
										</div>
										<div class="row ml-1">
											<div class="col-3">
												<div class="form-group row">
													<label>Employee Status</label>
													<form:select class="form-control" path="status">
														<form:option value="" selected="true">--SELECT--</form:option>
														<form:option value="active">Active</form:option>
														<form:option value="inactive">Inactive</form:option>
													</form:select>

												</div>
											</div>
											<div class="col-3 ml-5">
												<div class="form-group row">
													<label>Location</label>
													<form:select class="form-control form-control-user"
														path="location.loid">
														<form:option value="" selected="true">--SELECT--</form:option>
														<c:forEach items="${locations}" var="l">
															<form:option value="${l.loid}">${l.location}</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
											<div class="col-3 ml-5">
												<div class="form-group row">
													<label>Job Profile</label>
													<form:select class="form-control form-control-user"
														path="jobProfile.profileID">
														<form:option value="" selected="true">--SELECT--</form:option>
														<c:forEach items="${jobProfile}" var="jp">
															<form:option value="${jp.profileID}">${jp.profile}</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
										</div>
										<div class="row ml-1">
											<div class="col-3">
												<div class="form-group row">
													<label>Department</label>
													<form:select class="form-control form-control-user"
														path="department.depID">
														<form:option value="" selected="true">--SELECT--</form:option>
														<c:forEach items="${dAll}" var="d">
															<form:option value="${d.depID}">${d.department}</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
											<div class="col-3 ml-5">
												<div class="form-group row">
													<label>Employee Category</label>
													<form:select class="form-control form-control-user"
														path="category.catgoryID">
														<form:option value="" selected="true">--SELECT--</form:option>
														<c:forEach items="${empCategories}" var="ct">
															<form:option value="${ct.catgoryID}">${ct.category}</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
											<div class="col-3 ml-5">
												<div class="form-group row">
													<label>Employee Type</label>
													<form:select class="form-control form-control-user"
														path="empType.tid">
														<form:option value="" selected="true">--SELECT--</form:option>
														<c:forEach items="${allEmpTypes}" var="ty">
															<form:option value="${ty.tid}">${ty.type}</form:option>
														</c:forEach>
													</form:select>
												</div>

											</div>
										</div>

										<div class="row ml-1">
											<div class="col-3">
												<div class="form-group row">
													<label>Basic Salary</label>
													<form:input type="text" path="basicSalary"
														class="form-control" placeholder="Enter Basic Salary" />

												</div>
											</div>
											<div class="col-3 ml-5">
												<div class="form-group row">
													<label>Salary Grade</label>
													<form:select class="form-control form-control-user"
														path="salaryGrade.gradeID">
														<form:option value="" selected="true">--SELECT--</form:option>
														<c:forEach items="${salaryGrade}" var="sg">
															<form:option value="${sg.gradeID}">${sg.grade}</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
											<div class="col-3 ml-5">
												<div class="form-group row">
													<label>Salary Range</label>
													<form:select class="form-control form-control-user"
														path="salaryRange.salaryRangeID">
														<form:option value="" selected="true">--SELECT--</form:option>
														<c:forEach items="${salaryRange}" var="sr">
															<form:option value="${sr.salaryRangeID}">${sr.range}</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
										</div>

										<div class="row ml-1">
											<div class="col-3">
												<div class="form-group row">
													<label>Direct Reporting</label>
													<form:select class="form-control form-control-user"
														path="reporting">
														<form:option value="" selected="true">--SELECT--</form:option>
														<c:forEach items="${emps}" var="sr">
															<form:option value="${sr.empID}">${sr.name}</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
											<div class="col-3 ml-5">
												<div class="form-group row">
													<label>Resign Date</label>
													<form:input path="resignDate" type="date"
														class="form-control" placeholder="Enter Resign Date"
														id="resignDate" />
												</div>
											</div>
											<div class="col-4 ml-5 mt-4">
												<div class="form-group row mt-1">
													<label></label>
													<button type="submit" id="submitBtn"
														class="btn btn-success">
														<i class="fa fa-plus"></i> Add Details
													</button>
													<button type="reset" id="resetBtn"
														class="browse btn btn-danger  ml-1">
														<i class="fa fa-arrow-circle-right" aria-hidden="true"></i>
														Reset
													</button>
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
	<script src="<c:url value='/resources/hrm/ajax/employeeDetails.js'/>"></script>
</body>
</html>