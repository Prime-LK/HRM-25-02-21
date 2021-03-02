<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<html lang="en">
<head>
<%@include file="../../WEB-INF/jsp/head.jsp"%>
<link
	href="<c:url value='resources/hrm/css/empMonthSalaryDetails02.css'/>"
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
</style>

</head>
<body
	onload="validateDropDown(); validateSelectField(); loadVariableTypes();checkStatusofDropdowns();">
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
								<h2 class="text-white pb-2 fw-bold">Allocate Monthly
									Allowances</h2>
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
								<div class="container">
									<form:form action="saveEmpMoSaDetails02" method="post"
										modelAttribute="formMonthSalary02" id="formEmpMoSadetails">
										<div class="row">
											<div class="col-6">
												<div class="form-group row">
													<label class="col-5 mt-1">Start Date</label>
													<div class="col-7">
														<form:input type="date" id="pYear" path="pYear"
															class="form-control" />
														<span id="validateSD"></span>
													</div>
												</div>
											</div>
											<div class="col-6">
												<div class="form-group row">
													<label class="col-2 mt-1">Period</label>
													<div class="col-7">
														<input type="text" name="periodCode" class="form-control"
															id="periodCode" placeholder="PayPeriod"
															onchange="getRelatedDate()" readOnly>
													</div>
												</div>
											</div>
											<div class="col" id="comDiv">
												<div class="form-group row">
													<div class="col-7">
														<div class="form-group col-6 row ml-3">
															<!-- <label id="lcode">Company ID :</label> -->
															<div class="col">
																<input type="hidden" name="company.comID"
																	class="form-control" id="comID"
																	value="<%=session.getAttribute("company.comID")%>" />
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-6">
												<div class="form-group row">
													<label class="col-5 mt-1">End Date</label>
													<div class="col-7">
														<form:input type="date" class="form-control" id="pMonth"
															path="pMonth" onchange="getPeriodIDReDates()" />
														<span id="validateED"></span>
													</div>
												</div>
											</div>
											<div class="col-6">
												<div class="form-group row">
													<label class="col-2 mt-1">PayCode</label>
													<div class="col-7">
														<form:input type="text"
															path="monthDePk.payCodeid.payCodeID" class="form-control"
															id="pCode3" readOnly="true" placeholder="PayCode" />
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-6">
												<div class="form-group row">
													<label class="col-5 mt-1">Allowance Type</label>
													<div class="col-7">
														<form:select path="monthDePk.payType.deductTypeCode"
															id="deductTypeCode" class="custom-select"
															onchange="loadAddDed()">
															<form:option value="">--SELECT--</form:option>
															<c:forEach items="${addDedTypes}" var="b">
																<form:option value="${b.deductTypeCode}">${b.desc}</form:option>
															</c:forEach>
														</form:select>
														<span id="validateDT"></span>
													</div>
												</div>
											</div>
											<div class="col-6">
												<div class="form-group row">
													<label class="col-2 mt-1">Type</label>
													<div class="col-7">
														<input type="text" name="addDeType" class="form-control"
															id="addDeType" placeholder="type" readOnly>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-6">
												<p id="para01">Add To All Employees In</p>
											</div>
										</div>
										<div class="row">
											<div class="col-6">
												<div class="form-group row">
													<label class="col-3 mt-1">Department</label>
													<div class="col-2">
														<input class="form-check-input ml-5 mt-2" type="radio"
															name="inlineRadioOptions" id="inlineRadio1"
															value="option1" onchange="validateDropDown()">
													</div>
													<div class="col-7">
														<select name="depID" id="depID" class="custom-select"
															onchange="loadRelatedDep()">
															<option value="">--SELECT--</option>
															<c:forEach items="${departments}" var="b">
																<option value="${b.depID}">${b.department}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-6">
												<div class="form-group row">
													<label class="col-3 mt-1">Location</label>
													<div class="col-2">
														<input class="form-check-input ml-5 mt-2" type="radio"
															name="inlineRadioOptions" id="inlineRadio2"
															onchange="validateDropDown()" value="option2">
													</div>
													<div class="col-7">
														<select name="loid" id="loid" class="custom-select"
															onchange=" loadRelatedLoc()">
															<option value="">--SELECT--</option>
															<c:forEach items="${allLocs}" var="b">
																<option value="${b.loid}">${b.location}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-6">
												<div class="form-group row">
													<label class="col-3 mt-1">Category</label>
													<div class="col-2">
														<input class="form-check-input ml-5 mt-2" type="radio"
															name="inlineRadioOptions" id="inlineRadio3"
															value="option1" onchange="validateDropDown();">
													</div>
													<div class="col-7">
														<select name="catgoryID" id="catgoryID"
															class="custom-select" onchange=" loadRelatedCat()">
															<option value="">--SELECT--</option>
															<c:forEach items="${categories}" var="b">
																<option value="${b.catgoryID}">${b.category}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-6">
												<div class="form-group row">
													<label class="col-3 mt-1">Type</label>
													<div class="col-2">
														<input class="form-check-input ml-5 mt-2" type="radio"
															name="inlineRadioOptions" id="inlineRadio4"
															value="option1" onchange="validateDropDown()">
													</div>
													<div class="col-7">
														<select name="tid" id="tid" class="custom-select"
															onchange="loadRelatedType()">
															<option value="">--SELECT--</option>
															<c:forEach items="${types}" var="b">
																<option value="${b.tid}">${b.type}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-6">
												<div class="form-group row">
													<label class="col-3 mt-1">Add To All Employees</label>
													<div class="col-2">
														<input class="form-check-input ml-5 mt-2" type="radio"
															name="inlineRadioOptions" id="inlineRadio5"
															value="option1" onchange="loadAllEmps()">
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-6">
												<div class="form-group row">
													<label class="col-3 mt-1">Selected Employee</label>
													<div class="col-2">
														<input class="form-check-input ml-5 mt-2" type="radio"
															name="inlineRadioOptions" id="inlineRadio6"
															value="option2" onchange="validateDropDown()">
													</div>
													<div class="col-7">
														<select name="empID" id="empID" class="custom-select"
															onchange="loadEmpToTable()">
															<option value="">--SELECT--</option>
															<c:forEach items="${employees}" var="b">
																<option value="${b.empID}">${b.name}
																	${b.lastname}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
										</div>
										<div class="row" id="amtDiv">
											<div class="col-6">
												<div class="form-group row">
													<label class="col-3 mt-1">Amount</label>
													<div class="col-2"></div>
													<div class="col-7">
														<input type="text" class="form-control"
															id="vlivateAllEmps" name="vlivateAllEmps"
															placeholder="Enter Amount">
													</div>
												</div>
											</div>
										</div>
										<div class="row" id="tblEmpDiv">
											<div class="col-6">
												<div class="form-group row">
													<label class="col-3 mt-1">Table EmpID</label>
													<div class="col-2"></div>
													<div class="col-7">
														<input type="text" id="tEmpID1" class="form-control"
															name="monthDePk.empID.empID">
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-12">
												<button type="submit" id="submitBtn" class="btn btn-success">
													<i class="fa fa-plus"></i> Add Month Details
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
												<table id="tableMoSaDetails" class="table table-hover"
													cellspacing="0" width="100%">
													<thead>
														<tr>
															<th></th>
															<th>EmpID</th>
															<th>Name</th>
															<th>Amount</th>
														</tr>
													</thead>
													<tbody>
														<tr>
														</tr>
													</tbody>

												</table>

											</div>
										</div>
										<span id='validateAM'></span>
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
	<script
		src="<c:url value='resources/hrm/ajax/monthSalaryDetails02.js'/>"></script>
</body>
</html>