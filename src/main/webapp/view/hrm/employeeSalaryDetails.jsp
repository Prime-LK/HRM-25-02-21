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

#companyRow,#addedDateDiv,#addedUserDiv,#inactiveFromDiv,#inactiveUserDiv {
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
										<div class="form-group col-6">
											<p class="col-6" id="para01">Add to All Employees in</p>
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
													<!-- <li class="nav-item"><a class="nav-link"
															id="pills-contact-tab-nobd5" data-toggle="pill"
															href="#pills-contact-nobd5" role="tab"
															aria-controls="pills-contact-nobd5" aria-selected="false">Employee</a></li> -->
												</ul>
												<div class="tab-content mt-2 mb-3"
													id="pills-without-border-tabContent">
													<div class="tab-pane fade show" id="pills-home-nobd"
														role="tabpanel" aria-labelledby="pills-home-tab-nobd">
														<div class="row">
															<div class="col-6">
																<div class="form-group row">
																	<form:select class="form-control form-control-user"
																		onchange="loadRelatedDepEmployee();" id="depID"
																		path="">
																		<form:option value="" selected="true">--SELECT--</form:option>
																		<c:forEach items="${loadDep}" var="b">
																			<form:option value="${b.depID}">${b.department}</form:option>
																		</c:forEach>
																	</form:select>
																</div>
															</div>
														</div>
													</div>
													<div class="tab-pane fade" id="pills-profile-nobd"
														role="tabpanel" aria-labelledby="pills-profile-tab-nobd">
														<div class="row">
															<div class="col-6">
																<div class="form-group row">
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
													<div class="tab-pane fade" id="pills-contact-nobd"
														role="tabpanel" aria-labelledby="pills-contact-tab-nobd">
														<div class="row">
															<div class="col-6">
																<div class="form-group row">
																	<form:select class="form-control form-control-user"
																		onchange="loadRelatedcategoryEmployee();"
																		id="catgoryID" path="">
																		<form:option value="" selected="true">--SELECT--</form:option>
																		<c:forEach items="${loadcategory}" var="b">
																			<form:option value="${b.catgoryID}">${b.category}</form:option>
																		</c:forEach>
																	</form:select>
																</div>
															</div>
														</div>
													</div>
													<div class="tab-pane fade" id="pills-contact-nobd4"
														role="tabpanel" aria-labelledby="pills-contact-tab-nobd4">
														<div class="row">
															<div class="col-6">
																<div class="form-group row">
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
													<%-- <div class="tab-pane fade" id="pills-contact-nobd5"
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
														</div> --%>
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
										<div class="col-6" id="addedDateDiv"> 
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
										<div class="col-6" id="addedUserDiv">
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
										<div class="col-6" id="inactiveFromDiv">
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
										<div class="col-6" id="inactiveUserDiv">
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
														placeholder="Company ID" readOnly />
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
									<div class="row">
										<div class=" form-group col-12">
											<button type="submit" id="submitBtn" class="btn btn-success">
												<i class="fa fa-plus"></i> Add Salary Details
											</button>
											<button type="reset" id="resetBtn"
												class="browse btn btn-danger ml-2">
												<i class="fa fa-arrow-circle-right" aria-hidden="true"></i>
												Reset
											</button>
										</div>
									</div>
								</form:form>
								<div class="table-responsive  mt-3">
									<table id="basic-datatables"
										class="display table table-striped table-hover">
										<thead>
											<tr>
												<th>Emp. ID</th>
												<th>Full Name</th>
												<th>Allowance Name</th>
												<th>Allowance Amount</th>
												<th>Effective Date</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${getAllEmpSaDetails}" var="s">
												<tr>
													<td id="tPro"><div>${s.empdetailPK.empID.empID}</div></td>
													<td id="tItem"><div>${s.empdetailPK.empID.name} ${s.empdetailPK.empID.lastname}</div></td>
													<td id="tItem"><div>${s.empdetailPK.payAddeductTypes.desc}</div></td>
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