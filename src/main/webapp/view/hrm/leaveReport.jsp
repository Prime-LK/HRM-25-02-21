<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<html lang="en">
<head>
	<%@include file="../../WEB-INF/jsp/head.jsp"%>
	
</head>
<body>
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
			<%@include file="../../WEB-INF/jsp/slideBar.jsp"%>
		<!-- End Sidebar -->
		<div class="main-panel">
			<div class="content">
				<div class="panel-header bg-primary-gradient">
					<div class="page-inner py-3">
						<div class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
							<div class="col-xl col-lg">
								 <h2 class="text-white pb-2 fw-bold">Leave Summary Report</h2>
							</div>

						</div>
					</div>
				</div>
				
				<div class="page-inner mt--5">	
					<div class="container-fluid">
					
						<div class="row">
							<div class="col-xl-4 col-md-6 mb-4">

				              <div class="card shadow mb-4 border-left-primary">
				                <div class="card-body">
				                
				                <form action="leaveReport"  method="POST">
				                
			 
			 						<div class="form-group row">
										<div class="col-sm-12">
										<label class="l-fontst">Department</label>
												<select class="custom-select custom-select-sm" id="dept" name="dep" onchange="getEmployee();"
													 required>
													<option value="%"> All Department</option>
													<c:forEach items="${departmentETFAttLisRpt}" var="dept">
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
													<c:forEach items="${designationAttenETFLisRpt}" var="decma">
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
 													<option value=""> All Employee</option> 
 													<c:forEach items="${designationMasterEmpLisRpt}" var="decma"> 
														<option value="${decma.did}">${decma.designation}</option> 
													</c:forEach> 
												</select>
										</div>					
									</div>
									<div class="form-group row">
								<div class="col-sm-7">
							
									<label class="l-fontst">Approve Status</label>
							
								</div>
								<div class="col-sm-5">	
									<select class="custom-select custom-select-sm" name="empgroup" id="empgroup" required >
										<option value="0,1" >All</option>										
										<option value="1">YES</option>
										<option value="0">NO</option>
									</select>
									
								</div>
										
							</div>
			 
			 
											
			                		<div class="form-group row">
										<div class="col-lg">
											<button type="submit" class="btn  btn-block btn-danger btn-rounded" >
												Print Preview
											</button>
										</div>
									</div>								
									
				                
				                </form>
	
				                </div>
				              </div>							

							</div>
							<div class="col-xl-8 col-md-6 mb-4">

								<c:if test="${pdfViewEq != null }">
									<embed type="application/pdf"
										src="data:application/pdf;base64,${pdfViewEq}"
										style="height: 600px; width: 100%">
									</embed>
								</c:if>

							</div>
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
		        url: " getEmployeeListETFRpt",
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

	
		
		
			</script>
	

</body>
</html>