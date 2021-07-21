<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<html lang="en">
<head>
<%@include file="../../WEB-INF/jsp/head.jsp"%>
<link href="<c:url value='resources/hrm/css/payaddDeductTypes.css'/>"
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
								<h2 class="text-white pb-2 fw-bold">Allowance Type</h2>
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

			                	<c:if test = "${success ==1}">
									<div class="alert alert-success alert-dismissible">
									  <button type="button" class="close" data-dismiss="alert">&times;</button>
									  <strong>Success!</strong> Data Successfully Saved.
									</div>
								</c:if>
								<c:if test = "${success ==0}">
								  <div class="alert alert-danger alert-dismissible">
								    <button type="button" class="close" data-dismiss="alert">&times;</button>
								    <strong>Warning!</strong>Something went wrong ! Please try again!
								  </div>
								</c:if>
							
								<div class="container">
									<form:form action="saveDeType" method="post"
										modelAttribute="deductForm" onsubmit="formValidation()"
										id="deductForm">
										<form:input type="hidden" path="deductTypeCode" id="deductTypeCode"/>
										<form:input type="hidden" path="company.comID" id="comID"/>
											
										<div class="row">
											<div class="col">
												<div class="form-group row">
													<label class="col-4">description</label>
													<form:input type="text" path="desc"
														class="form-control col-5" id="desc"
														placeholder="enter type" />
												</div>
											</div>
											<div class="col">
												<div class="form-group row">
													<label class="col-4">Ledger code</label>
													<form:input type="text" path="ledgerCode"
														class="form-control col-5" id="ledgerCode"
														placeholder="enter ledger code" readOnly="true" />
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-4">
												<div class="form-group row">
													<div id="div1">
														<label>Addition or deduction</label> <br>
														<form:radiobutton path="addDeStatus" value="addition"
															checked="checked" />
														Addition
														<form:radiobutton path="addDeStatus" class="ml-4"
															value="deduction" />
														Deduction
														<form:radiobutton path="addDeStatus" class="ml-4"
															value="other" />
														Other
													</div>
												</div>
											</div>
											<div class="col-4">
												<div class="form-group row">
													<div id="div2">
														<label>calculation cycle</label> <br>
														<form:radiobutton path="addDePeriod" value="month"
															checked="checked" />
														monthly
														<form:radiobutton path="addDePeriod" class="ml-4"
															value="day" />
														daily
														<form:radiobutton path="addDePeriod" class="ml-4"
															value="hour" />
														hourly
													</div>
												</div>
											</div>
											<div class="col-4">
												<div class="form-group row">
													<div id="div3">
														<label>calculated as</label> <br>
											<form:select class="form-control form-control-user fontst"
												path="calculationMethod" id="calculationMethod">
												
													<form:option value="N/A">Value</form:option>
												<form:option value="OT Formula">OTFormula</form:option>
												<form:option value="NoPayFormula">NoPay Formula</form:option>
												<form:option value="ETFFormula">ETF Formula</form:option>
												<form:option value="StampFormula">Stamp Formula</form:option>
											</form:select>				
														
														
<%-- 														<form:radiobutton path="isPercentage" --%>
<%-- 															onchange="checkIfPercentage()" value="percentage" --%>
<%-- 															checked="checked" id="isPercentage" /> --%>
<!-- 														percentage -->
<%-- 														<form:radiobutton path="isPercentage" class="ml-4" --%>
<%-- 															value="value" id="isPercentage2" --%>
<%-- 															onchange="checkIfPercentage2()" /> --%>
<!-- 														value -->
														
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-4">
												<div class="form-group row">
													<div id="div4">
													
<!-- 													<label>is on basic salary</label> -->
<!-- 													<div class="btn-group btn-group-toggle" data-toggle="buttons"> -->
<!-- 												  <label class="btn btn-outline-success btn-sm"> -->
<%-- 												    <form:radiobutton path="onBaSalary" value="THRUE" />ACTIVE --%>
<!-- 												  </label> -->
<!-- 												  <label class="btn btn-outline-primary btn-sm"> -->
<%-- 												    <form:radiobutton path="onBaSalary"  value="FALSE"  />INACTIVE --%>
<!-- 												  </label> -->
												 
<!-- 												</div>	 -->




													<label>Is on basic salary</label>	
													<form:checkbox path="onBaSalary" value="TRUE" id="onbasicsal"/>
													<br/>	<label>Is on Gross Salary</label>
													<form:checkbox path="onGrSalary" value="TRUE" />
													<br/>	<label>Is On EPF Salary</label>
													<form:checkbox path="isOnEPFSalary" value="TRUE" />
														
										
													
													
													
													
													
<!-- 														<label>IS on</label> <br> -->
<%-- 														<form:radiobutton path="onBaSalary" value="basicSalary" --%>
<%-- 															checked="checked" /> --%>
<!-- 														basic salary -->
<%-- 														<form:radiobutton path="onGrSalary" class="ml-4" --%>
<%-- 															value="grossSalary" /> --%>
<!-- 														gross salary -->
													</div>
												</div>
											</div>
											<div class="col-4">
												<div class="form-group row">
													<div id="div5">
														<label>Type</label> <br>
														<form:radiobutton path="addDeType" value="fixedType"
															checked="checked" />
														fixed type
														<form:radiobutton path="addDeType" class="ml-4"
															value="variableType" />
														variable type
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-6">
												<div class="form-group row">
													<label class="col-4">value</label>
														<form:input type="text" path="addDeValue"
															class="form-control col-5" id="addDeValue"
															placeholder="Value" />
														<!-- <span id="percentage1">%</span> -->
												</div>
											</div>
											<div class="col-6">
												<div class="form-group row">
													<label class="col-4">Multiply</label>
													<form:input type="text" path="multipiyValue"
														class="form-control col-5" id="multipiyValue"
														placeholder="value" />
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-6">
												<div class="form-group row">
													<label class="col-4">calculation priority</label>
													<form:input type="text" path="calPriSeq"
														class="form-control col-5" id="calPriSeq"
														placeholder="cal priority" />
												</div>
											</div>
											<div class="col-6">
												<div class="form-group row">
													<label class="col-4">Print priority</label>
													<form:input type="text" path="printPriSeq"
														class="form-control col-5" id="printPriSeq"
														placeholder="print priority" />
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-6">
												<div class="form-group row">
													<label class="col-3">Is active</label>
													<div class="selectgroup w-100 col-5 ml-4">
														<label class="selectgroup-item"> <form:radiobutton
																path="isActive" value="active" class="selectgroup-input" />
															<span class="selectgroup-button">Active</span>
														</label> <label class="selectgroup-item"> <form:radiobutton
																path="isActive" value="Inactive" class="selectgroup-input" />
															<span class="selectgroup-button">Inactive</span>
														</label>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-6">
												<div class="form-group row">
													<button type="submit" id="submitBtn"
														class="btn btn-success ml-3">
														<i class="fa fa-plus"></i> Add Allowance
													</button>
													<button type="reset" id="resetBtn"
														class="browse btn btn-danger ml-3">
														<i class="fa fa-arrow-circle-right" aria-hidden="true"></i>
														Reset
													</button>
												</div>
											</div>
										</div>
									</form:form>
								</div>
								<div class="col-12 mt-3">
										<div class="table-responsive">
											<table id="basic-datatables"
												class="display table table-striped table-hover">
												<thead>
													<tr>
														<th>Description</th>
														<th>Type</th>
														
														<th>calculated as</th>
														<th>deduct value</th>
<!-- 														<th>multiply value</th> -->
														<th>Basic Salary</th>
														<th>Gross Salary</th>
														<th>ETF/EPF Salary</th>
														
<!-- 														<th>deduct period</th> -->
														<th>type</th>
														<th>cal priority</th>
														<th>ledger code</th>
														<th>print priority</th>
														<th>Status</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${allAddDed}" var="s">
														<tr>
															<td id="tPro"><div>${s.desc}</div></td>
															<td id="tItem"><div>${s.addDeStatus}</div></td>
														
															<td id="tItem"><div>${s.calculationMethod}</div></td>
															<td id="tItem"><div>${s.addDeValue}</div></td>
<%-- 															<td id="tItem"><div>${s.multipiyValue}</div></td> --%>
															<td id="tItem"><div>${s.onBaSalary}</div></td>
															<td id="tItem"><div>${s.onGrSalary}</div></td>
															<td id="tItem"><div>${s.isOnEPFSalary}</div></td>
															
<%-- 															<td id="tItem"><div>${s.addDePeriod}</div></td> --%>
															<td id="tItem"><div>${s.addDeType}</div></td>
															<td id="tItem"><div>${s.calPriSeq}</div></td>
															<td id="tItem"><div>${s.ledgerCode}</div></td>
															<td id="tItem"><div>${s.printPriSeq}</div></td>
															<td id="tItem"><div>${s.isActive}</div></td>
															<td><a href="updateDeductForm?id=${s.deductTypeCode}"><i
																	class="far fa-edit"></i></a></td>
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
			<%@include file="../../WEB-INF/jsp/footer.jsp"%>
		</div>
	</div>
	<%@include file="../../WEB-INF/jsp/commJs.jsp"%>
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
		
		
		function display() {
			if(document.getElementById("check").checked) {
			    document.getElementById('checkHidden').disabled = true;
			}
			  }
			 
			
	
			
		
	</script>
</html>