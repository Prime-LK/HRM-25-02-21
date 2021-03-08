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
<body onload="loadVariableTypes();checkStatusofDropdowns();">
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
											<div class="col-5">
												<div class="form-group row">
													<label class="col-5 mt-1">Start Date</label>
													<div class="col-7">
														<form:input type="date" id="pYear" path="pYear"
															class="form-control" />
														<span id="validateSD"></span>
													</div>
												</div>
											</div>
											<div class="col-4">
												<div class="form-group row" id="payPeriodDiv">
													<label class="col-3 mt-1">Period</label>
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
											<div class="col-5">
												<div class="form-group row">
													<label class="col-5 mt-1">End Date</label>
													<div class="col-7">
														<form:input type="date" class="form-control" id="pMonth"
															path="pMonth" onchange="getPeriodIDReDates()" />
														<span id="validateED"></span>
													</div>
												</div>
											</div>
											<div class="col-4">
												<div class="form-group row" id="payCodeValDiv">
													<label class="col-3 mt-1">PayCode</label>
													<div class="col-7">
														<input type="text"
															name="payCodeVal" class="form-control"
															id="payCodeVal" readOnly placeholder="PayCode Value" />
													</div>
												</div>
											</div>
											<div class="col">
												<div class="form-group row" id="payCodeDiv">
													<label class="col-3 mt-1">PayCode</label>
													<div class="col-7">
														<form:input type="text"
															path="monthDePk.payCodeid.payCodeID" class="form-control"
															id="pCode3" readOnly="true" placeholder="PayCode" />
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-5">
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
											<div class="col-4">
												<div class="form-group row" id="alloTypeDiv">
													<label class="col-3 mt-1">Type</label>
													<div class="col-7">
														<input type="text" name="addDeType" class="form-control"
															id="addDeType" placeholder="type" readOnly>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="form-group col-12">
												<p class="col-3" id="para01">Add To All Employees In</p>
											</div>
										</div>
										<div class="row">
											<div class="col-8">
												<div class="form-group">
													<ul class="nav nav-pills nav-secondary nav-pills-no-bd"
														id="pills-tab-without-border" role="tablist">
														<li class="nav-item"><a class="nav-link"
															id="pills-home-tab-nobd" data-toggle="pill"
															href="#pills-home-nobd" role="tab"
															aria-controls="pills-home-nobd" aria-selected="true">Department</a>
														</li>
														<li class="nav-item"><a class="nav-link"
															id="pills-profile-tab-nobd" data-toggle="pill"
															href="#pills-profile-nobd" role="tab"
															aria-controls="pills-profile-nobd" aria-selected="false">Location</a>
														</li>
														<li class="nav-item"><a class="nav-link"
															id="pills-contact-tab-nobd" data-toggle="pill"
															href="#pills-contact-nobd" role="tab"
															aria-controls="pills-contact-nobd" aria-selected="false">Category</a>
														</li>
														<li class="nav-item"><a class="nav-link"
															id="pills-contact-tab-nobd4" data-toggle="pill"
															href="#pills-contact-nobd4" role="tab"
															aria-controls="pills-contact-nobd" aria-selected="false">Type</a>
														</li>
														<li class="nav-item"><a class="nav-link"
															id="pills-contact-tab-nobd5" data-toggle="pill"
															href="#pills-contact-nobd5" role="tab"
															aria-controls="pills-contact-nobd5" aria-selected="false">Employee</a></li>
													</ul>
													<div class="tab-content mt-2 mb-3"
														id="pills-without-border-tabContent">
														<div class="tab-pane fade show" id="pills-home-nobd"
															role="tabpanel" aria-labelledby="pills-home-tab-nobd">
															<div class="row">
																<div class="col-6">
																	<div class="form-group row">
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
														<div class="tab-pane fade" id="pills-profile-nobd"
															role="tabpanel" aria-labelledby="pills-profile-tab-nobd">
															<div class="row">
																<div class="col-6">
																	<div class="form-group row">
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
														<div class="tab-pane fade" id="pills-contact-nobd"
															role="tabpanel" aria-labelledby="pills-contact-tab-nobd">
															<div class="row">
																<div class="col-6">
																	<div class="form-group row">
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
														<div class="tab-pane fade" id="pills-contact-nobd4"
															role="tabpanel" aria-labelledby="pills-contact-tab-nobd4">
															<div class="row">
																<div class="col-6">
																	<div class="form-group row">
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
														<div class="tab-pane fade" id="pills-contact-nobd5"
															role="tabpanel" aria-labelledby="pills-contact-tab-nobd5">
															<div class="row">
																<div class="col-6">
																	<div class="form-group row">
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
													<div class="col-7"></div>
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
										<div class="row">
											<div class="form-group col-12 mt-3">
												<button type="submit" id="submitBtn" class="btn btn-success">
													<i class="fa fa-plus"></i> Add Month Details
												</button>
												<button type="reset" id="resetBtn"
													class="browse btn btn-danger ml-2">
													<i class="fa fa-arrow-circle-right" aria-hidden="true"></i>
													Reset
												</button>
											</div>
										</div>
										<div class="table-responsive mt-3">
											<table id="basic-datatables"
												class="display table table-striped table-hover">
												<thead>
													<tr>
														<th>Emp. ID</th>
														<th>Full Name</th>
														<th>Allowance Name</th>
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
	<script src="<c:url value='resources/hrm/ajax/monthSalaryDetails02.js'/>"></script>
		<!-- jQuery Scrollbar -->
	<script
		src="resources/assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>
	<!-- Datatables -->
	<script src="resources/assets/js/plugin/datatables/datatables.min.js"></script>
</body>
<script>
		$(document).ready(function() {
			$('#basic-datatables').DataTable({
			});

			$('#multi-filter-select').DataTable( {
				"pageLength": 20,
				initComplete: function () {
					this.api().columns().every( function () {
						var column = this;
						var select = $('<select class="form-control"><option value=""></option></select>')
						.appendTo( $(column.footer()).empty() )
						.on( 'change', function () {
							var val = $.fn.dataTable.util.escapeRegex(
								$(this).val()
								);

							column
							.search( val ? '^'+val+'$' : '', true, false )
							.draw();
						} );

						column.data().unique().sort().each( function ( d, j ) {
							select.append( '<option value="'+d+'">'+d+'</option>' )
						} );
					} );
				}
			});

			// Add Row
			$('#add-row').DataTable({
				"pageLength": 20,
			});

			var action = '<td> <div class="form-button-action"> <button type="button" data-toggle="tooltip" title="" class="btn btn-link btn-primary btn-lg" data-original-title="Edit Task"> <i class="fa fa-edit"></i> </button> <button type="button" data-toggle="tooltip" title="" class="btn btn-link btn-danger" data-original-title="Remove"> <i class="fa fa-times"></i> </button> </div> </td>';

			$('#addRowButton').click(function() {
				$('#add-row').dataTable().fnAddData([
					$("#addName").val(),
					$("#addPosition").val(),
					$("#addOffice").val(),
					action
					]);
				$('#addRowModal').modal('hide');

			});
		});
	</script>
</html>