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

* {
	text-transform: capitalize;
}

#srIDDiv {
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
								<h2 class="text-white pb-2 fw-bold">Salary Range Details</h2>
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
								<form:form action="salaryRangeAct" method="post"
									modelAttribute="salaryRange" id="formSRange">

									<div class="form-group row">
										<div class="col-sm-5">
											<div class="col-sm-60 mb-1 mb-sm-3">
												<label>Salary Grade</label>
												<form:select path="salaryGrade.gradeID"
													class="custom-select" required="true">
													<form:option value="">Select Salary Grade</form:option>
													<c:forEach items="${salaryGrade}" var="salaryG">
														<form:option value="${salaryG.gradeID}">${salaryG.grade}</form:option>
													</c:forEach>
												</form:select>
											</div>
										</div>
										<div class="col-sm-3">
											<div class="col-sm-30 mb-1 mb-sm-3 offset-4" id="srIDDiv">
												<label class="invisible">Salary Range ID</label>
												<form:input type="text" path="salaryRangeID"
													class="form-control" id="sRangeID" />
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
												<label>Salary Range</label>
												<form:input path="range" class="form-control"
													placeholder="Enter range" />
												<form:errors path="range" cssClass="error1 text-danger" />

											</div>
										</div>
									</div>

									<div class="form-group row">
										<div class="col-sm-5">
											<div class="col-sm-60 mb-1 mb-sm-3">
												<label>Maximum Salary</label>
												<form:input path="maxSalary" class="form-control" />

											</div>
										</div>
									</div>

									<div class="form-group row">
										<div class="col-sm-5">
											<div class="col-sm-60 mb-1 mb-sm-3">
												<label>Minimum Salary</label>
												<form:input path="minSalary" class="form-control" />

											</div>
										</div>
									</div>

									<div class="col-8 mb-3">
										<input type="submit" class="btn btn-success btn-sm"
											value="Add Salary Range"> <input type="reset"
											class="btn btn-danger btn-sm" value="Reset">
									</div>

								</form:form>
								<div class="col-9">
									<div class="scrollable">
										<table id="tableSRange" class="table table-hover"
											cellspacing="0" width="100%">
											<c:forEach items="${salaryRList}" var="salary">
												<tr>
													<td><a
														href="editSalaryRange?salaryRangeID=${salary.salaryRangeID}"><i
																class="far fa-edit"></i></a></td>
													<td id="tSGrade"><div>${salary.salaryGrade.grade}</div></td>
													<td id="tRange"><div>${salary.range}</div></td>
													<td id="tMin"><div>${salary.minSalary}</div></td>
													<td id="tMax"><div>${salary.maxSalary}</div></td>

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