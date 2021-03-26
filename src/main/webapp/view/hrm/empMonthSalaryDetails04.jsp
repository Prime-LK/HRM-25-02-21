<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<html lang="en">
<head>
<%@include file="../../WEB-INF/jsp/head.jsp"%>
<link
	href="<c:url value='resources/hrm/css/empMonthSalaryDetails04.css'/>"
	rel="stylesheet">
<!-- combined year and month picker css -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.2.0/css/datepicker.min.css"
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
.scrollable {
	height: 380px;
	overflow: scroll;
}

#hiddenRow {
	display: none;
}

* {
	text-transform: capitalize;
}

#empidTable {
	width: 3rem;
	border: none;
}

#amount {
	width: 5rem;
}
</style>

</head>
<body
	onload="invisibleDataTable01();loadVariableTypes();checkStatusofDropdowns();">
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
									<form:form action="saveEmpMoSaDetails04" method="post"
										modelAttribute="formMonthSalary04" id="formEmpMoSadetails">
										<div class="row">
											<div class="col-6">
												<div class="row">
													<div class="col-6">
														<div class="form-group">
															<label>Year & Month</label> <input type="text"
																class="form-control" name="datepicker" id="datepicker"
																placeholder="Pick a Year and Month"
																onchange="loadRePeriodCode2()" />
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-6" id="startDateDiv">
														<div class="form-group">
															<div class="input-group">
																<div class="input-group-prepend">
																	<span class="input-group-text">From</span>
																</div>
																<input type="text" class="form-control" name="pYear"
																	id="pYear" placeholder="Start Date">
															</div>
														</div>
													</div>
													<div class="col-6">
														<div class="form-group" id="endDateDiv">
															<div class="input-group">
																<div class="input-group-prepend">
																	<span class="input-group-text">To</span>
																</div>
																<input type="text" class="form-control" name="pMonth"
																	id="pMonth" placeholder="End Date">
															</div>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-6">
														<div class="form-group" id="payPeriodValDiv">
															<div class="input-group">
																<div class="input-group-prepend">
																	<span class="input-group-text">PayPeriod</span>
																</div>
																<input type="text" class="form-control"
																	name="periodCodeVal" id="periodCodeVal"
																	placeholder="PayPeriod">
															</div>
														</div>
													</div>
													<div class="col-6">
														<div class="form-group" id="payCodeValDiv">
															<div class="input-group">
																<div class="input-group-prepend">
																	<span class="input-group-text">PayCode</span>
																</div>
																<input type="text" class="form-control"
																	name="PayCodeVal" id="PayCodeVal"
																	placeholder="PayCode">
															</div>
														</div>
													</div>
												</div>
												<div class="row" id="hiddenRow">
													<div class="col-6" id="payPeriodDiv">
														<div class="form-group">
															<label>PayPeriod</label> <input type="text"
																name="periodCode" class="form-control" id="periodCode"
																placeholder="PayPeriod" readOnly>
														</div>
													</div>
													<div class="col-6" id="payCodeDiv">
														<div class="form-group">
															<label>PayCode</label>
															<form:input type="text"
																path="monthDePk.payCodeid.payCodeID"
																class="form-control" id="pCode3" readOnly="true"
																placeholder="PayCode" />
														</div>
													</div>
												</div>
												<div class="row" id="hiddenRow">
													<div class="col-6" id="companyDiv">
														<div class="form-group">
															<label>Company ID </label> <input type="text"
																name="company.comID" class="form-control" id="comID"
																value="<%=session.getAttribute("company.comID")%>"
																readOnly />
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-6">
														<div class="form-group">
															<label>Allowance Type</label>
															<form:select path="monthDePk.payType.deductTypeCode"
																id="deductTypeCode" class="form-control"
																onchange="loadAddDed()">
																<form:option value="">--SELECT--</form:option>
																<c:forEach items="${addDedTypes}" var="b">
																	<form:option value="${b.deductTypeCode}">${b.desc}</form:option>
																</c:forEach>
															</form:select>
														</div>
													</div>
													<div class="col-6" id="alloTypeDiv">
														<div class="form-group mt-4">
															<div class="input-group">
																<div class="input-group-prepend">
																	<span class="input-group-text">Type</span>
																</div>
																<input type="text" name="addDeType" class="form-control"
																	id="addDeType" placeholder="type">
															</div>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-6">
														<div class="form-group">
															<label>Add to employees in</label> <select
																class="form-control" name="seperateSelect"
																id="sepSelect" onChange="loadRelatedSelect();">
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
													<div class="col-6">
														<div class="form-group" id="loadSepDiv"></div>
													</div>
												</div>
												<div class="row" id="hiddenRow">
													<div class="col-6">
														<div class="form-group">
															<label>Amount</label> <input type="text"
																class="form-control" id="vlivateAllEmps"
																name="vlivateAllEmps" placeholder="All Emp Value"
																readOnly>
														</div>
													</div>
													<div class="col-6">
														<div class="form-group">
															<label>Table EmpID</label> <input type="text"
																id="tEmpID1" class="form-control"
																placeholder="Emp Value" name="monthDePk.empID.empID"
																readonly>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-10">
														<div class="form-group">
															<button type="submit" id="submitBtn"
																class="btn btn-success">
																<i class="fa fa-plus"></i> Add Month Details
															</button>
															<button type="reset" id="resetBtn"
																class="browse btn btn-danger ml-2"
																onClick="slideUpDatable01()">
																<i class="fa fa-arrow-circle-right" aria-hidden="true"></i>
																Reset
															</button>
														</div>
													</div>
												</div>
											</div>
											<div class="col-6">
												<div class="scrollable" id="dataTableBasic">
													<table id="tableMoSaDetails" class="table table-hover"
														cellspacing="0" width="100%">
														<thead>
															<tr>
																<th></th>
																<th>Emp. No.</th>
																<th>Full Name</th>
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
										</div>
									</form:form>
									<div class="row">
										<div class="form-group">
											<div class="table-responsive mt-3">
												<table id="basic-datatables"
													class="display table table-striped table-hover border border-primary">
													<thead>
														<tr>
															<th>Emp.No.</th>
															<th>Full Name</th>
															<th>Allowance</th>
															<th>PayCode</th>
															<th>Start Date</th>
															<th>End Date</th>
															<th>Amount</th>
															<th>Action</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${findAllEmpMoSaDetails}" var="s">
															<tr>
																<td id="tPro"><div>${s.monthDePk.empID.empID}</div></td>
																<td id="tItem"><div>${s.monthDePk.empID.name}
																		${s.monthDePk.empID.lastname}</div></td>
																<td id="tItem"><div>${s.monthDePk.payType.desc}</div></td>
																<td id="tItem"><div>${s.monthDePk.payCodeid.payCode}</div></td>
																<td id="tItem"><div>${s.pYear}</div></td>
																<td id="tItem"><div>${s.pMonth}</div></td>
																<td id="tItem"><div>${s.amount}</div></td>
																<td id="tItem"><div>
																		<a
																			href="updateDeductForm?id=${s.monthDePk.empID.empID}"><i
																			class="far fa-edit"></i></a>
																	</div></td>
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
				</div>

			</div>
			<%@include file="../../WEB-INF/jsp/footer.jsp"%>
		</div>
	</div>
	<%@include file="../../WEB-INF/jsp/commJs.jsp"%>
	<script
		src="<c:url value='resources/hrm/ajax/monthSalaryDetails04.js'/>"></script>
	<script src="<c:url value='resources/hrm/js/yearpicker.js'/>"></script>
	<!-- jQuery Scrollbar -->
	<script
		src="resources/assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>
	<!-- Datatables -->
	<script src="resources/assets/js/plugin/datatables/datatables.min.js"></script>
	<!-- combined year and month picker js -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.min.js"></script>
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
	$("#datepicker").datepicker({
		format : "yyyy-mm",
		startView : "months",
		minViewMode : "months"
	});
</script>
</html>