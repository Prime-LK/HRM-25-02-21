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

* {
	text-transform: capitalize;
}

#para01 {
	color: #0000FF;
	font-weight: bold;
}

#companyRow {
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
								<h2 class="text-white pb-2 fw-bold">Employee Salary Details</h2>
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
								<form:form action="saveEmpSalaryDetails" method="post"
									modelAttribute="SalaryDetail">
									<div class="row">
										<div class="col-6">
											<div class="form-group row">
												<label class="col-5 mt-1">Allowance Type</label>
												<div class="col-7">
													<form:select class="form-control form-control-user"
														path="empdetailPK.payAddeductTypes.deductTypeCode"
														required="true" id="addDedctTypeID">
														<form:option value="" selected="true">--SELECT--</form:option>
														<c:forEach items="${payAddDetuctType}" var="b">
															<form:option value="${b.deductTypeCode}">${b.desc}</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-6">
											<p id="para01">Add to All Employees in</p>
										</div>
									</div>
									<div class="row">
										<div class="col-6">
											<div class="form-group row">
												<label class="col-3 mt-1">Department</label>
												<div class="col-2">
													<input type="radio" class="form-check-input ml-5 mt-2"
														onchange="validateDropDown();" id="inlineRadio1">
												</div>
												<div class="col-7">
													<form:select class="form-control form-control-user"
														onchange="loadRelatedDepEmployee();" id="depID" path="">
														<form:option value="" selected="true">--SELECT--</form:option>
														<c:forEach items="${loadDep}" var="b">
															<form:option value="${b.depID}">${b.department}</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-6">
											<div class="form-group row">
												<label class="col-3 mt-1">Location</label>
												<div class="col-2">
													<input type="radio" class="form-check-input ml-5 mt-2"
														onchange="validateDropDown();" id="inlineRadio2">
												</div>
												<div class="col-7">
													<form:select class="form-control form-control-user"
														id="loid" onchange="loadRelatedLocationEmployee();"
														path="">
														<form:option value="" selected="true">--SELECT--</form:option>
														<c:forEach items="${loadlocations}" var="b">
															<form:option value="${b.loid}">${b.location}</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-6">
											<div class="form-group row">
												<label class="col-3 mt-1">Category</label>
												<div class="col-2">
													<input type="radio" class="form-check-input ml-5 mt-2"
														onchange="validateDropDown();" id="inlineRadio3">
												</div>
												<div class="col-7">
													<form:select class="form-control form-control-user"
														onchange="loadRelatedcategoryEmployee();" id="catgoryID"
														path="">
														<form:option value="" selected="true">--SELECT--</form:option>
														<c:forEach items="${loadcategory}" var="b">
															<form:option value="${b.catgoryID}">${b.category}</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-6">
											<div class="form-group row">
												<label class="col-3 mt-1">Type</label>
												<div class="col-2">
													<input type="radio" class="form-check-input ml-5 mt-2"
														onchange="validateDropDown();" id="inlineRadio4">
												</div>
												<div class="col-7">
													<form:select class="form-control form-control-user"
														onchange="loadRelatedEmployeebasedOnTypes();" id="tid"
														path="">
														<form:option value="" selected="true">--SELECT--</form:option>
														<c:forEach items="${loadcatypes}" var="b">
															<form:option value="${b.tid}">${b.type}</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-6">
											<div class="form-group row">
												<label class="col-3 mt-1">Add to All Employees</label>
												<div class="col-2">
													<input type="radio" class="form-check-input ml-5 mt-2"
														onChange="loadAllEmployee();" id="inlineRadio5">
												</div>
												<div class="col-7"></div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-6">
											<div class="form-group row">
												<label class="col-3 mt-1">Selected Employee</label>
												<div class="col-2">
													<input type="radio" class="form-check-input ml-5 mt-2"
														onchange="validateDropDown();" id="inlineRadio6">
												</div>
												<div class="col-7">
													<input class="form-control form-control-user" type="text"
														value='<%=session.getAttribute("empID")%>' id="empID1"
														name="loginEmp" readOnly>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-6">
											<div class="form-group row">
												<label class="col-5 mt-1">Effective Date</label>
												<div class="col-7">
													<form:input path="effective_Date" type="date"
														class="form-control " placeholder="Enter Date Of Birth"
														id="effective_Date" />
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-6">
											<div class="form-group row">
												<label class="col-5 mt-1">Added Date</label>
												<div class="col-7">
													<form:input path="added_Date" type="date"
														class="form-control" placeholder="Enter Date Of Birth"
														id="added_Date" readOnly="true" />
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-6">
											<div class="form-group row">
												<label class="col-5 mt-1">Added User</label>
												<div class="col-7">
													<form:input class="form-control form-control-user"
														id="added_User" path="added_User"
														value='<%=session.getAttribute("empName")%>'
														readOnly="true" />
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-6">
											<div class="form-group row">
												<label class="col-5 mt-1">Inactive From</label>
												<div class="col-7">
													<form:input path="inactive_From" type="date"
														class="form-control" placeholder="Enter Date Of Birth"
														id="inactive_From" readOnly="true" />
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-6">
											<div class="form-group row">
												<label class="col-5 mt-1">Inactive User</label>
												<div class="col-7">
													<form:input class="form-control form-control-user"
														id="inactive_User" path="inactive_User"
														value='<%=session.getAttribute("empName")%>'
														readOnly="true" />
												</div>
											</div>
										</div>
									</div>
									<div class="row" id="companyRow">
										<div class="col-6">
											<div class="form-group row">
												<label class="col-5">Company</label>
												<div class="col-7">
													<input type="text" name="company.comID"
														class="form-control" id="comID"
														value="<%=session.getAttribute("company.comID")%>"
														placeholder="Company ID" readOnly/>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-6">
											<div class="form-group row">
												<div class="col-12 ml-3">
													<input type="radio" class="form-check-input"
														onchange="validateDropDown2();" id="exampleCheck3"
														value="Active" name="isActive"> <label
														class="font-weight-bold mr-3">Active </label> <input
														type="radio" class="form-check-input ml-5"
														onchange="validateDropDown1();" value="InActive"
														name="isActive" id="exampleCheck2"> <label
														class="font-weight-bold">Inactive </label>
												</div>
												<!-- 	<div class="col-5">
													
												</div> -->
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-12">
											<button type="submit" id="submitBtn" class="btn btn-success">
												<i class="fa fa-plus"></i> Add Salary Details
											</button>
											<button type="reset" id="resetBtn"
												class="browse btn btn-danger">
												<i class="fa fa-arrow-circle-right" aria-hidden="true"></i>
												Reset
											</button>
										</div>
									</div>
									<div class="col-12">
										<div class="scrollable">
											<table class="table table-hover" width="100%" cellspacing="0"
												id="filterEmp">
												<thead>
													<tr>
														<th>Emp ID</th>
														<th>Employee Name</th>
														<th>Employee Type</th>
														<th>Employee Category</th>
														<th></th>
													</tr>
												</thead>
												<tbody>
												</tbody>
											</table>
										</div>
									</div>
								</form:form>
							</div>
						</div>
					</div>
				</div>

			</div>
			<%@include file="../../WEB-INF/jsp/footer.jsp"%>
		</div>
	</div>
	<%@include file="../../WEB-INF/jsp/commJs.jsp"%>
	<script src="resources/hrm/ajax/employeeSalaryDetails.js"></script>
</body>
<script>
	function validateDropDown() {
		if (document.getElementById("inlineRadio1").checked) {
			document.getElementById("depID").disabled = false;
		} else if (document.getElementById("inlineRadio2").checked) {
			document.getElementById("loid").disabled = false;
		} else if (document.getElementById("inlineRadio3").checked) {
			document.getElementById("catgoryID").disabled = false;
		} else if (document.getElementById("inlineRadio4").checked) {
			document.getElementById("tid").disabled = false;
		} else if (document.getElementById("inlineRadio6").checked) {
			document.getElementById("empID1").disabled = false;
		}

	}

	function validateSelectField() {
		document.getElementById("depID").disabled = true;
		document.getElementById("loid").disabled = true;
		document.getElementById("catgoryID").disabled = true;
		document.getElementById("tid").disabled = true;
		document.getElementById("empID1").disabled = true;
	}

	function validateDropDown1() {
		if (document.getElementById("exampleCheck2").checked) {
			document.getElementById("added_Date").disabled = true;
			document.getElementById("added_User").disabled = true;
		}

	}
</script>

<script>
	$(document).ready(function() {
		var date = new Date();

		var day = date.getDate();
		var month = date.getMonth() + 1;
		var year = date.getFullYear();

		if (month < 10)
			month = "0" + month;
		if (day < 10)
			day = "0" + day;

		var today = year + "-" + month + "-" + day;
		$("#added_Date").attr("value", today);
		$("#inactive_From").attr("value", today);
	});
</script>

<script>
	function validateDropDown2() {
		if (document.getElementById("exampleCheck3").checked) {
			document.getElementById("inactive_From").disabled = true;
			document.getElementById("inactive_User").disabled = true;
		}

	}
</script>
</html>