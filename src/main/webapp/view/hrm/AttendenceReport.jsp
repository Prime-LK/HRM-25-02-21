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
* {
	text-transform: capitalize;
}

</style>

</head>
<body onload="getEmployee();">
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
								<h2 class="text-white pb-2 fw-bold">Attendance Report</h2>
							</div>
							<div class="col-xl-2 col-lg-2"></div>
							<div class="ml-md-auto py-2 py-md-4"></div>

							<div class="ml-md-auto py-2 py-md-4"></div>
						</div>
					</div>
				</div>
				
				
			<div class="row">				
	<div class="col-xl-3 col-lg-5" >			
					<!-- Card -->
			<form:form action="attendenceSheet"  method="GET">		
					<div class="card shadow mb-4" style="height:600px;">
						<div class="card border-left-primary shadow h-100 py-2" >
						
							<div class="card-body">
								<div class="form-group row">
								<div class="col-sm-12">
												<label class="l-fontst">Pay Period</label> <select
													class="custom-select custom-select-sm" id="payperodid" 
													name="payPeriod" required onchange="getEmployee();setdate(this.value)" >
													<option value="">Select Pay Period</option>
													<c:forEach items="${payPeriodPayShip}" var="payPeriodPayShip" >
														<option value="${payPeriodPayShip.payPeriodID}">${pship.desc}
															(${payPeriodPayShip.startDate}-${payPeriodPayShip.endDate})</option>
													</c:forEach>
												</select>
								</div>
									
								</div>
								<div class="form-group row">
								
										<div class="col-sm-12">																					
															<label>From Date</label> <input
																type="Date" name="fromdate"
																class="form-control form-control-sm" id="fromdate"
																required onchange="fromdate(this.value);"
																>
																		
										</div>
									</div>	
									<div class="form-group row">									
										<div class="col-sm-12">
															<label>To Date</label> <input
																type="Date" name="todate"
																class="form-control form-control-sm" id="todate"
																required onchange="todate(this.value);"
																>
														</div>
								
								</div>
							<div class="form-group row">
										<div class="col-sm-12">
										<label class="l-fontst">Department</label>
												<select class="custom-select custom-select-sm" id="dept" name="dep" onchange="getEmployee();"
													 required>
													<option value="%"> All Department</option>
													<c:forEach items="${departmentEmpAttLisRpt}" var="dept">
														<option value="${dept.depID}">${dept.department}</option>
													</c:forEach>
												</select>	
										</div>
									
										
							</div>
						
							
									<div class="form-group row">
										<div class="col-sm-12">
										<label class="l-fontst">Designation</label>
												<select class="custom-select custom-select-sm" name="dis" id="dis"
													required onchange="getEmployee();" >
													<option value="%"> All Designation</option>
													<c:forEach items="${designationAttenEmpLisRpt}" var="decma">
														<option value="${decma.did}">${decma.designation}</option>
													</c:forEach>
												</select>
										</div>
									
										
							</div>
							
							<div class="form-group row">
										<div class="col-sm-12">
										<label class="l-fontst">Employee</label>
										<select class="custom-select custom-select-sm" id="empid" name="employeeId"
													 required>
<!-- 													<option value=""> All Employee</option> -->
<%-- 													<c:forEach items="${designationMasterEmpLisRpt}" var="decma"> --%>
<%-- 														<option value="${decma.did}">${decma.designation}</option> --%>
<%-- 													</c:forEach> --%>
												</select>
										</div>
									
										
							</div>
							<div class="form-group row">
								<div class="col-sm-7">
							
									<label class="l-fontst">one sheet per Employee</label>
							
								</div>
								<div class="col-sm-5">	
									<select class="custom-select custom-select-sm" name="empgroup" id="empgroup" required >
										<option value="Yes">YES</option>
										<option value="No">No</option>
									</select>
									
								</div>
										
							</div>
							
							
							
							
							
							
<!-- 							<div class="form-group row"> -->
<!-- 										<div class="col-sm-12"> -->
<!-- 													<input type="checkbox" class="form-control custom-control-input fontst fontstc" id="withCheck" name="repStatu" value="INACTIVE" onclick="customerCredit()"> -->
<!-- 								    				<label class="custom-control-label l-fontst fontstc" for="withCheck">With Cancel</label> -->
<!-- 										</div> -->
									
										
<!-- 							</div> -->
								
							<br>	
							<hr>								
							<div class="form-group row">
								
									<div class="col-sm-12">
									<button type="submit" class="btn  btn-block btn-danger btn-rounded tabStyle" >Print Preview</button>
<!-- 											<a href="#" class="btn btn-primary" onclick="runCancelInvoice();">Invoice Cancel</a>																 -->
									</div>		
							
								</div>
					

							</div>
							<!-- End of card body -->
						</div>
					</div>
		</form:form>
		</div>

		<div class="col-xl-9 col-lg-5">			
			<div class="col-sm-12">
					<c:if test="${pdfViewEq != null }">
									<embed type="application/pdf" src="data:application/pdf;base64,${pdfViewEq}"
										style="height:600px; width:100%">
										</embed>
										</c:if>
			</div>
								
		</div>
</div>	
				
			
				
				
				
			</div>
			<%@include file="../../WEB-INF/jsp/footer.jsp"%>
		</div>
	</div>
	<%@include file="../../WEB-INF/jsp/commJs.jsp"%>
	
	
		<script type="text/javascript">
		
		
		function getEmployee(){
		
			var dep=document.getElementById("dept").value;
			var dis=document.getElementById("dis").value;
			
			
		
			
			
			$.ajax({
		        type: 'GET',
		        url: " getEmployeeListrptAttnds",
		        data: {"dep" : dep, "dis" : dis},
		        success: function(data){
		        
		            var slctSubcat=$('#empid'), option="";
		            slctSubcat.empty();
		            selected_option = "<option value='%'>All Employee</option>";
		            slctSubcat.append(selected_option);

		            for(var i=0; i<data.length; i++){
		                option = option + "<option value='"+data[i].empID + "'>"+data[i].epfNo +" - "+data[i].lastname + "</option>";
		            }
		            slctSubcat.append(option);
		        },
		        error:function(){
		        	
		           // alert("No return Model data for this Make ID");
		        }

		    });
		}
		var frd="";
		var tod="";
		function setdate(st){//
	
			
			$.ajax({
		        type: 'GET',
		        url: " getpayperoideAttends",
		        data: {"payPeriodID" : st},
		        success: function(data){
		        	
		        	frd=data.startDate;
		        	tod=data.endDate;
		        	document.getElementById("fromdate").value=data.startDate;
		        	document.getElementById("todate").value=data.endDate;
		        	
		        	
		        },
		        error:function(){
		        	
		           // alert("No return Model data for this Make ID");
		        }

		    });
		}
		
		function fromdate(st){//
// 	      if(frd<=st){
	    	  
// 	      }else{
// 	    	  alert("eeeddddd");
// 	    	  document.getElementById("fromdate").value=""; 
// 	      }
	
		}
		
		function todate(st){//
// 			 alert(st);
// 		      if(tod>=st){
		    	  
// 		      }else{
		    	 
// 		    	  document.getElementById("todate").value="";  
// 		      }
			
		}
		
		
		
		
		
		
		
		
		
		
		
			</script>
	
	
	
	
</body>
</html>