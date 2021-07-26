<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<html lang="en">
<head>
<%@include file="../../WEB-INF/jsp/head.jsp"%>
<link
	href="<c:url value='resources/hrm/css/empMonthSalaryDetails01.css'/>"
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
	height: 340px;
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
<body onload="invisibleDataTable01();loadVariableTypes();checkStatusofDropdowns();"
	>
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
								<div class="container">
									<form:form action="saveEmpSalaryDetails" method="post"
										modelAttribute="SalaryDetail" id="formEmpMoSadetails">
										<div class="row">
										  <div class="col-6">
										
											


												<div class="row">
													<div class="col-6">
														<div class="form-group">
															<label>Allowance Type</label>
															<form:select path="empdetailPK.payAddeductTypes.deductTypeCode"
																id="deductTypeCode" class="form-control"
																onchange="getEmpAllowances('-1')">
																<form:option value="">--SELECT--</form:option>
																<c:forEach items="${addAllDedTypes}" var="b">
																	<form:option value="${b.deductTypeCode}">${b.desc}</form:option>
																</c:forEach>
															</form:select>
														</div>
													</div>
													
																									
													
													
												</div>
												<div class="row">
													<div class="col-6">
														<div class="form-group">
															<label>Add to employees in</label> <select
																class="form-control" name="seperateSelect"
																id="sepSelect" onChange="loadRelatedSelect();getEmpAllowances('-1');">
																<option value="" selected>--SELECT--</option>
<!-- 																<option value="department">Department</option> -->
<!-- 																<option value="location">Location</option> -->
<!-- 																<option value="category">Category</option> -->
<!-- 																<option value="type">Type</option> -->
																<option value="employee">Employee</option>
																<option value="allEmployee">All Employee</option>
															</select>
														</div>
													</div>
													
													<div class="col-6">
														<div class="row">
														
															
																<div class="form-group" id="loadSepDiv"></div>
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
													
													
													
													
													
													
							
												</div>

											<div class="col-6">
											
											
											
																					
												<div class="scrollable" >
													<table id="tableMoSaDetails" class="table table-hover"
														cellspacing="0" width="100%">
														<thead>
															<tr>
																<th></th>
																<th style="display:none;">Emp.No.</th>
																<th>EPF.No.</th>
																<th>Full Name</th>
																<th>Amount</th>
															</tr>
														</thead>
														<tbody>
															
														</tbody>
													</table>
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
																			
									</form:form>
									<div class="row">
									<div class="col-12">
										<div class="form-group">
																						<div class="scrollable" >
													<table id="empsaldetail" class="table table-hover"
														cellspacing="0" width="100%">
														<thead>
															<tr>
															<th>EPF.No.</th>
															<th>Full Name</th>
															<th>Allowance</th>															
															<th>Effective Date</th>
															<th>Amount</th>
															<th>Status</th>
															</tr>
														</thead>
														<tbody>
															
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

			</div>
			<%@include file="../../WEB-INF/jsp/footer.jsp"%>
		</div>
	</div>
	<%@include file="../../WEB-INF/jsp/commJs.jsp"%>
<!-- 	<script -->
<%-- 		src="<c:url value='resources/hrm/ajax/monthSalaryDetails01.js'/>"></script> --%>
	<!-- jQuery Scrollbar -->
	<script
		src="resources/assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>
	<!-- Datatables -->
	<script src="resources/assets/js/plugin/datatables/datatables.min.js"></script>
</body>
<script>




getEmpAllowances("-1");
function getEmpAllowances(empid) {
	
	var type = document.getElementById("sepSelect").value;
	var adddedtype = document.getElementById("deductTypeCode").value;

	
	
	$.ajax({
		type : "GET",
		url : "getEmpAllowancesGrid",
		data : {
			"type" : type,"adddedtype" : adddedtype,"empid" : empid
		},success : function(data) {
			$("#empsaldetail tbody").empty();
			for (var i = 0; i < data.length; i++) {
				//alert("----"+data[i].empdetailPK.empID.lastname);
				
				
				var result1 ="<td>"+data[i].empdetailPK.empID.empID+"</td>"+						
				"<td>"+data[i].empdetailPK.empID.name+' '+data[i].empdetailPK.empID.lastname+"</td>"+
				"<td>"+data[i].empdetailPK.payAddeductTypes.desc+"</td>"+
				"<td>"+data[i].effective_Date+"</td>"+
				"<td>"+data[i].amount+"</td>"+
				"<td>"+data[i].isActive+"</td>";
				var result ="";
				
				if(data[i].isActive=="Active"){
				 result = "<tr>"+result1+"</tr>";
				}else{
					 result = "<tr style='background-color: #bd0202; color:black;'>"+result1+"</tr>";
					
				}			
								
								
								
				$("#empsaldetail tbody").append(result);
			
			}
		
		},
		error : function(e) {
			alert("Not Found Employees Or Employee Type");
		}
	});
}













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
	
	
	function loadPayCode(){
		var yx = document.getElementById("payperodid").value;
	$.ajax({
		type: "GET",
		url: "empMoMaAlComboList",
		data : {
			"payPeriodID" : yx
		},
		success:function(data) {
			$('#payCode').empty();
		
			
			var slctSubcat = $('#payCode'), option = "";
			slctSubcat.empty();
			selected_option = "<option value='' selected>--SELECT--</option>"
			slctSubcat.append(selected_option);
			
			for (var i = 0; i < data.length; i++) {
				option = option
						+ "<option value='"+data[i].payCodeID + "'>"
					
						+ data[i].payCode +"</option>";
			}
			slctSubcat.append(option);

		},
		error:function(e) {
			alert("Error Found Loading Employee Data");
		}
	});
	
	
	}
	
	
	function loadRelatedSelect() {
		var sltVal = $('#sepSelect').val();
		if(sltVal == "department") {
			$.ajax({
				type: "GET",
				url: "departments",
				success:function(data) { 
					$('#loadSepDiv').empty();
					var result = '<label>Department</label>'
						       + '<select class="form-control" name="dep" id="depID"' 
						       + 'onChange="loadRelatedDep();visibleDataTable01();getEmpAllowances(this.value)">'
							   + '<option value="" selected="true">--SELECT--</option>'
							   + '</select>';
					$('#loadSepDiv').append(result);
					
					var slctSubcat = $('#depID'), option = "";
					slctSubcat.empty();
					selected_option = "<option value='' selected>--SELECT--</option>"
					slctSubcat.append(selected_option);
					
					for (var i = 0; i < data.length; i++) {
						option = option
								+ "<option value='"+data[i].depID + "'>"
								+ data[i].department + "</option>";
					}
					slctSubcat.append(option);

				},
				error:function(e) {
					alert("Error Found Loading Department Data");
				}
			});

		} else if(sltVal === "location") {
			$.ajax({
				type: "GET",
				url: "loadlocations",
				success:function(data) {
					$('#loadSepDiv').empty();
					var result = '<label>Location</label>'
						       + '<select class="form-control" name="lo" id="loid"'
						       + 'onChange="loadRelatedLoc();visibleDataTable01();getEmpAllowances(this.value)">'
							   + '<option value="" selected="true">--SELECT--</option>'
							   + '</select>';
					$('#loadSepDiv').append(result);
					
					var slctSubcat = $('#loid'), option = "";
					slctSubcat.empty();
					selected_option = "<option value='' selected>--SELECT--</option>"
					slctSubcat.append(selected_option);
					
					for (var i = 0; i < data.length; i++) {
						option = option
								+ "<option value='"+data[i].loid + "'>"
								+ data[i].location + "</option>";
					}
					slctSubcat.append(option);

				},
				error:function(e) {
					alert("Error Found Loading Location Data");
				}
			});
		} else if(sltVal === "category") {
			$.ajax({
				type: "GET",
				url: "categories",
				success:function(data) {
					$('#loadSepDiv').empty();
					var result = '<label>Category</label>'
						       + '<select class="form-control" name="cat" id="categoryID"' 
						       + 'onchange="loadRelatedCat();visibleDataTable01();getEmpAllowances(this.value)">'
							   + '<option value="" selected="true">--SELECT--</option>'
							   + '</select>';
					$('#loadSepDiv').append(result);
					
					var slctSubcat = $('#categoryID'), option = "";
					slctSubcat.empty();
					selected_option = "<option value='' selected>--SELECT--</option>"
					slctSubcat.append(selected_option);
					
					for (var i = 0; i < data.length; i++) {
						option = option
								+ "<option value='"+data[i].catgoryID + "'>"
								+ data[i].category + "</option>";
					}
					slctSubcat.append(option);

				},
				error:function(e) {
					alert("Error Found Loading Category Data");
				}
			});
		} else if(sltVal === "type") {
			$.ajax({
				type: "GET",
				url: "types",
				success:function(data) {
					$('#loadSepDiv').empty();
					var result = '<label>Type</label>'
						       + '<select class="form-control" name="type" id="tid"' 
						       + 'onchange="loadRelatedType();visibleDataTable01();getEmpAllowances(this.value)">'
							   + '<option value="" selected="true">--SELECT--</option>'
							   + '</select>';
					$('#loadSepDiv').append(result);
					
					var slctSubcat = $('#tid'), option = "";
					slctSubcat.empty();
					selected_option = "<option value='' selected>--SELECT--</option>"
					slctSubcat.append(selected_option);
					
					for (var i = 0; i < data.length; i++) {
						option = option
								+ "<option value='"+data[i].tid + "'>"
								+ data[i].type + "</option>";
					}
					slctSubcat.append(option);

				},
				error:function(e) {
					alert("Error Found Loading Type Data");
				}
			});
		} else if(sltVal === "employee") {
			$.ajax({
				type: "GET",
				url: "loadAllEmpInEmpDetails",
				success:function(data) {
					$('#loadSepDiv').empty();
					var result = '<label>Employee</label>'
						       + '<select class="form-control" name="emp" id="empID"' 
						       + 'onchange="loadEmpToTable();visibleDataTable01();getEmpAllowances(this.value)">'
							   + '<option value="" selected="true">--SELECT--</option>'
							   + '</select>';
					$('#loadSepDiv').append(result);
					
					var slctSubcat = $('#empID'), option = "";
					slctSubcat.empty();
					selected_option = "<option value='' selected>--SELECT--</option>"
					slctSubcat.append(selected_option);
					
					for (var i = 0; i < data.length; i++) {
						option = option
								+ "<option value='"+data[i].detailsPK.empID.empID + "'>"
								+data[i].epfNo+" - "
								+ data[i].detailsPK.empID.name +" "+ data[i].detailsPK.empID.lastname +"</option>";
					}
					slctSubcat.append(option);

				},
				error:function(e) {
					alert("Error Found Loading Employee Data");
				}
			});
		} else {
			loadAllEmps();
			visibleDataTable01();
			$('#loadSepDiv').empty();
		}
	}

	function loadVariableTypes() {
		
	}

	function getPeriodIDReDates() {
		var x = document.getElementById("pYear").value;
		var y = document.getElementById("pMonth").value;

		$.ajax({
			type : "GET",
			url : "getPayCodeReDates",
			data : {
				"Start_Date" : x,
				"End_Date" : y
			},
			success : function(data) {
				document.getElementById("periodCode").value = data.payPeriodID;
				document.getElementById("periodCodeVal").value= data.desc;
				getRelatedPayCodes2();
				divsVisible();

			},
			error : function(e) {
				alert("Pay Period not Found");
			}
		});
	}

	function getRelatedPayCodes2() {
		var a = document.getElementById("periodCode").value;

		var slctSubcat = $('#payCode'), option = "";
		slctSubcat.empty();

		$.ajax({
			type : "GET",
			url : "getPayCodesRelatedPeriod",
			data : {
				"periodID" : a
			},
			success : function(data) {

				var slctSubcat = $('#payCode'), option = "";
				slctSubcat.empty();
				selected_option = "<option value='' selected>--SELECT--</option>"
				slctSubcat.append(selected_option);

				for (var i = 0; i < data.length; i++) {
					option = option + "<option value='" + data[i].payCodeID + "'>"
							+ data[i].payCode + "</option>";
				}

				slctSubcat.append(option);
			},
			error : function(e) {
				alert("Cant load pay codes");
			}
		});
	}

	function loadAddDed() {

	}

	function loadRelatedDep() {
	var y = document.getElementById("depID").value;
	var z = document.getElementById("deductTypeCode").value;
	$.ajax({
		type : "GET",
		url : "loadReDep",
		data : {
			"dep" : y,
			"deductTypeCode" : z
		},
		success : function(data) {
			$("#tableMoSaDetails tbody").empty();
			for (var i = 0; i < data.length; i++) {
				var result = "<tr><td><input type='checkbox' id='cb1' onchange='setValues()'" +
						" name='cb1' value='inactive'>" +
						"</td>" +
						"<td><input name='empID' id='empidTable'" +
						"value=" + data[i].empdetailPK.empID.empID
						+ " readOnly></td><td>" + data[i].empdetailPK.empID.name + ""
						+ data[i].empdetailPK.empID.lastname + "</td><td>"
						+ "<input id='amount' name='amount' autocomplete='off' placeholder='Amount'" +
						 + " ></td></tr>";
								
					$("#tableMoSaDetails tbody").append(result);
			}
		},
		error : function(e) {
			alert("Not Found Employees Or Departments");
		}
	});
	}

	function loadRelatedLoc() {
	var z = document.getElementById("deductTypeCode").value;
	var y = document.getElementById("loid").value;
	$
			.ajax({
				type : "GET",
				url : "loadEmpRelatedLocation",
				data : {
					"loid" : y,
					"deductTypeCode" : z
				},
				success : function(data) {
					$("#tableMoSaDetails tbody").empty();
					for (var i = 0; i < data.length; i++) {
						var result = "<tr><td><input type='checkbox' onchange='setValues()'" +
								" id='cb1' name='cb1' value='inactive'>" +
								"</td><td><input name='empID' id='empidTable'"
								+ "value=" + data[i].empdetailPK.empID.empID
								+ " ></td><td>"
								+ data[i].empdetailPK.empID.name
								+ " "
								+ data[i].empdetailPK.empID.lastname
								+ "</td><td><input id='amount' name='amount' autocomplete='off' placeholder='Amount'" +
								+ " ></td></tr>";
						$("#tableMoSaDetails tbody").append(result);

					}
				},
				error : function(e) {
					alert("Not Found Employees Or Location");
				}
			});
	}

	function loadRelatedCat() {
	var z = document.getElementById("deductTypeCode").value;
	var y = document.getElementById("categoryID").value;
	$
			.ajax({
				type : "GET",
				url : "loadEmpRelatedCat",
				data : {
					"categoryID" : y,
					"deductTypeCode" : z
				},
				success : function(data) {
					$("#tableMoSaDetails tbody").empty();
					for (var i = 0; i < data.length; i++) {
						var result = "<tr><td><input type='checkbox' id='cb1' name='cb1'>" +
								"</td><td><input name='empID' id='empidTable' value="
								+ data[i].empdetailPK.empID.empID
								+ " ></td><td>"
								+ data[i].empdetailPK.empID.name
								+ " "
								+ data[i].empdetailPK.empID.lastname
								+ "</td><td><input id='amount' name='amount' autocomplete='off' placeholder='Amount'" +
								+ " ></td></tr>";
						$("#tableMoSaDetails tbody").append(result);

						var result2 = "<input type='text' name='monthDePk.empID.empID' " +
						"value=" + data[i].empdetailPK.empID.empID + ">";
						$("#tEmpID").append(result2);
					}
				},
				error : function(e) {
					alert("Not Found Employees Or Employee Type");
				}
			});
	}
	//onclick='activePerson() getEmpMonthSalaryDetailsPage01
	function activePerson() {
		$("#empidTable").prop("disabled", (_, val) => !val);
	}

	function loadRelatedType() {
	var y = document.getElementById("tid").value;
	var z = document.getElementById("deductTypeCode").value;
	$
			.ajax({
				type : "GET",
				url : "loadEmpRelatedType",
				data : {
					"tid" : y,
					"deductTypeCode" : z
				},
				success : function(data) {
					$("#tableMoSaDetails tbody").empty();
					for (var i = 0; i < data.length; i++) {
						var result = "<tr><td><input type='checkbox' id='cb1' name='cb1' value='inactive'>" +
								"</td><td><input name='empID' id='empidTable' value="
								+ data[i].empdetailPK.empID.empID
								+ " ></td><td>"
								+ data[i].empdetailPK.empID.name
								+ " "
								+ data[i].empdetailPK.empID.lastname
								+ "</td><td><input id='amount' name='amount' autocomplete='off' placeholder='Amount'" +
								+ " ></td></tr>";
						$("#tableMoSaDetails tbody").append(result);
						
					}
				},
				error : function(e) {
					alert("Not Found Employees Or Employee Type");
				}
			});
	}

	function loadAllEmps() {
		$.ajax({
			type : "GET",
			url : "loadAllEmployeesToGrid",
			success : function(data) {
				$("#tableMoSaDetails tbody").empty();
				for (var i = 0; i < data.length; i++) {
					var result = "<tr><td><input type='checkbox' id='cb1' name='cb1' value='inactive'>" +
							"</td>"
							
							+"<td>" + data[i].epfNo + "</td><td>"
							
							
							+ data[i].detailsPK.empID.name + " " + data[i].detailsPK.empID.lastname
							+ "</td><td><input id='amount' name='amount' autocomplete='off' placeholder='Amount'" +
							" ></td>" +
							"<td><input name='empID' id='empidTable'" +
								"value=" + data[i].detailsPK.empID.empID + " readOnly='true' type='hidden'></td><td>"
							+"</tr>";
					$("#tableMoSaDetails tbody").append(result);
				
				}
				var x = document.getElementById("vlivateAllEmps");
				 x.setAttribute("value", "abc");
			},
			error : function(e) {
				alert("Not Found Employees Or Employee Type");
			}
		});
	}

	function loadEmpToTable() {
	var y = document.getElementById("empID").value;
	$.ajax({
		type : "GET",
		url : "loadEmpTable",
		data : {
			"empID" : y
		},
		success : function(data) {

			$("#tableMoSaDetails tbody").empty();
			for (var i = 0; i < data.length; i++) {
				var result = "<tr><td><input type='checkbox' id='cb1' name='cb1' value='inactive'>" +
						"</td>"
						
						+"<td>" + data[i].epfNo + "</td><td>"
						
						
						+ data[i].detailsPK.empID.name + " " + data[i].detailsPK.empID.lastname
						+ "</td><td><input id='amount' name='amount' autocomplete='off' placeholder='Amount'" +
						" ></td>" +
						"<td><input name='empID' id='empidTable'" +
							"value=" + data[i].detailsPK.empID.empID + " readOnly='true' type='hidden'></td><td>"
						+"</tr>";
				$("#tableMoSaDetails tbody").append(result);
				
				 $("#tEmpID1").empty();
				 var a = document.getElementById("tEmpID1");
				 a.setAttribute("value", data[i].empID);

			}
		},
		error : function(e) {
			alert("Not Found Employees Or Departments");
		}
	});
	}

	function divsInvisible() {
		$('#payPeriodValDiv').hide();
		$('#alloTypeDiv').hide();
	}

	function divsVisible() {
		$('#payPeriodValDiv').slideDown();
	}

	function alloTypeDivVisible() {
		$('#alloTypeDiv').slideDown();
	}

	function invisibleDataTable01() {
		$('#dataTableBasic').hide();
	}

	function visibleDataTable01() {
		$('#dataTableBasic').slideDown();
	}

	function slideUpDatable01() {
		$('#dataTableBasic').slideUp();
		$('#loadSepDiv').empty();
	}
	
</script>
</html>