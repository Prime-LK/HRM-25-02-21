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
	height: 330px;
	overflow: scroll;
}

#companyRow, #addedUserRow, #inactiveUserRow {
	 display: none; 
}

#filterEmp thead th, tbody{
	font-size: 12px;
}

#tableEmpId {
	border: none;
	width: 3rem;
}
</style>
</head>
<body onload="invisibleDataTable01();checkStatusofDropdowns();">
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
								<h2 class="text-white pb-2 fw-bold">Allocate Employee Salary Details</h2>
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
									modelAttribute="SalaryDetail" id="formEmpSalaryDetails">
									<div class="row">
										<div class="col-6">
											<div class="row">
												<div class="col-6">
													<div class="form-group">
														<label>Allowance Type</label>
														<form:select class="form-control form-control-user"
															path="empdetailPK.payAddeductTypes.deductTypeCode"
															id="addDedctTypeID">
															<form:option value="" selected="true">--SELECT--</form:option>
															<c:forEach items="${payAddDetuctType}" var="b">
																<form:option value="${b.deductTypeCode}">${b.desc}</form:option>
															</c:forEach>
														</form:select>
													</div>
												</div>
												<div class="col-6">
													<div class="form-group">
														<label>Add to employees in</label> <select
															class="form-control form-control-user"
															name="seperateSelect" id="sepSelect"
															onChange="loadRelatedSelect();">
															<option value="" selected>--SELECT--</option>
															<option value="department">Department</option>
															<option value="location">Location</option>
															<option value="category">Category</option>
															<option value="type">Type</option>
															<option value="employee">Employee</option>
															<option value="allEmployee">All Employee</option>
														</select>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-6">
													<div class="form-group">
														<label>Effective Date</label>
														<form:input path="effective_Date" type="date"
															class="form-control" placeholder="Effective Date"
															id="effectiveDate" />
													</div>
												</div>
												<div class="col-6">
													<div class="form-group" id="loadSepDiv"></div>
												</div>
											</div>
											<div class="row" id="addedUserRow">
												<div class="col-6">
													<div class="form-group">
														<label>Added User</label>
														<form:input class="form-control" id="addedUser"
															path="added_User" placeholder="Added Usere"
															value='<%=session.getAttribute("empName")%>'
															readOnly="true" />
													</div>
												</div>
												<div class="col-6">
													<div class="form-group">
														<label>Added Date</label>
														<form:input path="added_Date" type="date"
															class="form-control" placeholder="Added Date"
															id="added_Date" readOnly="true" />
													</div>
												</div>
											</div>
											<div class="row" id="inactiveUserRow">
												<div class="col-6">
													<div class="form-group">
														<label>Inactive User</label>
														<form:input class="form-control" id="inactiveUser"
															path="inactive_User" placeholder="Inactive Date"
															value='<%=session.getAttribute("empName")%>'
															readOnly="true" />
													</div>
												</div>
												<div class="col-6">
													<div class="form-group">
														<label>Inactive From</label>
														<form:input path="inactive_From" type="date"
															class="form-control" placeholder="Inactive From"
															id="inactive_From" readOnly="true" />
													</div>
												</div>
											</div>
											<div class="row" id="companyRow">
												<div class="col-6">
													<div class="form-group">
														<label>Company</label> <input type="text"
															name="company.comID" class="form-control" id="comID"
															value="<%=session.getAttribute("company.comID")%>"
															placeholder="Company ID" readOnly />
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-6">
													<div class="form-group">
														<label>Is active</label>
														<div class="selectgroup w-100">
															<label class="selectgroup-item"> <form:radiobutton
																	path="isActive" value="Active" id="exampleCheck3"
																	class="selectgroup-input" /> <span
																class="selectgroup-button">Active</span>
															</label> <label class="selectgroup-item"> <form:radiobutton
																	path="isActive" value="InActive" id="exampleCheck2"
																	class="selectgroup-input" /> <span
																class="selectgroup-button">Inactive</span>
															</label>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-8">
													<div class="form-group">
														<button type="submit" id="submitBtn"
															class="btn btn-success">
															<i class="fa fa-plus"></i> Add Details
														</button>
														<button type="reset" id="resetBtn"
															class="browse btn btn-danger ml-2" onClick="slideUpDatable01()">
															<i class="fa fa-arrow-circle-right" aria-hidden="true"></i>
															Reset
														</button>
													</div>
												</div>
											</div>
										</div>
										<div class="col-6">
											<div class="scrollable" id="dataTableBasic">
												<table class="table table-hover" width="100%"
													cellspacing="0" id="filterEmp">
													<thead>
														<tr>
															<th>Emp.No.</th>
															<th>Name</th>
															<th>Category</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</form:form>
								<div class="table-responsive  mt-3">
									<table id="basic-datatables"
										class="display table table-striped table-hover">
										<thead>
											<tr>
												<th>Emp. No.</th>
												<th>Full Name</th>
												<th>Allowance</th>
												<th>Calculation Type</th>
												<th> Amount</th>
												<th>Effective Date</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${getAllEmpSaDetails}" var="s">
												<tr>
													<td id="tPro"><div>${s.empdetailPK.empID.empID}</div></td>
													<td id="tItem"><div>${s.empdetailPK.empID.name}
															${s.empdetailPK.empID.lastname}</div></td>
													<td id="tItem"><div>${s.empdetailPK.payAddeductTypes.desc}</div></td>
													<td id="tItem"><div>${s.empdetailPK.payAddeductTypes.isPercentage}</div></td>
													<td id="tItem"><div>${s.empdetailPK.payAddeductTypes.addDeValue}</div></td>
													<td id="tItem"><div>${s.effective_Date}</div></td>
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
			<%@include file="../../WEB-INF/jsp/footer.jsp"%>
		</div>
	</div>
	<%@include file="../../WEB-INF/jsp/commJs.jsp"%>
	<script src="resources/hrm/ajax/employeeSalaryDetails.js"></script>
	<!-- jQuery Scrollbar -->
	<script
		src="resources/assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>
	<!-- Datatables -->
	<script src="resources/assets/js/plugin/datatables/datatables.min.js"></script>
	<!-- validation -->
	<script src="resources/hrm/validation/empSalaryDetailsValidation.js"></script>
	<!-- Bootstrap Notify -->
	<script
		src="resources/assets/js/plugin/bootstrap-notify/bootstrap-notify.min.js"></script>
</body>

<script>
	$(document)
			.ready(
					function() {
						$('#basic-datatables').DataTable({});

						$('#multi-filter-select')
								.DataTable(
										{
											"pageLength" : 20,
											initComplete : function() {
												this
														.api()
														.columns()
														.every(
																function() {
																	var column = this;
																	var select = $(
																			'<select class="form-control"><option value=""></option></select>')
																			.appendTo(
																					$(
																							column
																									.footer())
																							.empty())
																			.on(
																					'change',
																					function() {
																						var val = $.fn.dataTable.util
																								.escapeRegex($(
																										this)
																										.val());

																						column
																								.search(
																										val ? '^'
																												+ val
																												+ '$'
																												: '',
																										true,
																										false)
																								.draw();
																					});

																	column
																			.data()
																			.unique()
																			.sort()
																			.each(
																					function(
																							d,
																							j) {
																						select
																								.append('<option value="'+d+'">'
																										+ d
																										+ '</option>')
																					});
																});
											}
										});

						// Add Row
						$('#add-row').DataTable({
							"pageLength" : 20,
						});

						var action = '<td> <div class="form-button-action"> <button type="button" data-toggle="tooltip" title="" class="btn btn-link btn-primary btn-lg" data-original-title="Edit Task"> <i class="fa fa-edit"></i> </button> <button type="button" data-toggle="tooltip" title="" class="btn btn-link btn-danger" data-original-title="Remove"> <i class="fa fa-times"></i> </button> </div> </td>';

						$('#addRowButton').click(
								function() {
									$('#add-row').dataTable().fnAddData(
											[ $("#addName").val(),
													$("#addPosition").val(),
													$("#addOffice").val(),
													action ]);
									$('#addRowModal').modal('hide');

								});
					});
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