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
								 <h2 class="text-white pb-2 fw-bold">Leave Apply</h2>
							</div>

						</div>
					</div>
				</div>
				
				<div class="page-inner mt--5">	
					<div class="container-fluid">

			              <div class="card shadow mb-4 border-left-primary">
			                <div class="card-body">
			                
								<div class="row">
									<div class="col-xl col-md-6 mb-4">

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
										
										<form:form action="applyLeave" method="post" onSubmit="return validateForm()" modelAttribute="applyleave">
										
											<form:input type="hidden" path="leaveID"/>
											<form:input type="hidden" path="company.comID"/>
											
											
					                		<div class="form-group row">
												<div class="col-lg-8">
													<label>Department</label>
													<form:select class="form-control form-control-sm" id="department"
														path="department.depID" required="">
														<form:option value="" selected="true">--Select--</form:option>
														<c:forEach items="${DepAll}" var="dp">
															<form:option value="${dp.depID}">${dp.department}</form:option>
														</c:forEach>
													</form:select>

												</div>

											</div>
											<div class="form-group row">
												<div class="col-lg-8">
													<label>Employee</label>
													<form:select class="form-control form-control-sm" id="employee"
														path="employee.empID" required="">
														<form:option value="" selected="true">--Select--</form:option>
														<c:forEach items="${EmpAll}" var="emp">
															<form:option value="${emp.empID}">${emp.name}</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
											<div class="form-group row">
												<div class="col-lg-8">
													<label>Leave Type</label>
													<form:select class="form-control form-control-sm" id="leaveType"
														path="leaveType.leaveCode" required="">
														<form:option value="" selected="true">--Select--</form:option>
														<c:forEach items="${leaveAll}" var="l">
															<form:option value="${l.leaveCode}">${l.leaveType}</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>

											<div class="form-group row">
												<div class="col-lg-8">
													<label>Type</label>
													<form:select class="form-control form-control-sm" id="type"
														path="type" required="">
														<form:option value="" selected="true">--Select--</form:option>
														<form:option value="Full">Full</form:option>
														<form:option value="Half">Half</form:option>
													</form:select>
												</div>
											</div>
											<div class="form-group row">
												<div class="col-lg-8">
													<label>From</label>
													<input type="date" class="form-control form-control-sm" id="from"  required/>
												</div>
											</div>
											<div class="form-group row">
												<div class="col-lg-8">
													<label>To</label>
													<input type="date" class="form-control form-control-sm"  id="to" onchange="getDayCount()" required/>
												</div>
											</div>
											<form:input type="hidden" path="days" id="days"/>
											<div class="form-group row">
												<div class="col-lg-8">
													<label>Description</label>
													<form:textarea class="form-control" id="dec" path="desc" placeholder="" required="" />
												</div>
											</div>
											<div class="form-group row">
												<div class="col-lg-8">
													<input type="submit" class="btn btn-success btn-sm" value="Apply Leave">
													<input type="reset" class="btn btn-warning btn-sm" value="Clear">
												</div>
											</div>
										</form:form>
					                

									</div>
									<div class="col-xl col-md-6 mb-4">
									

																			
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

<script>
function getDayCount() {
	
	var s = document.getElementById("from").value;
	var start = new Date(s);
	var e = document.getElementById("to").value;
	var end = new Date(e);

	var res = Math.abs(start - end) / 1000;
	var days = Math.floor(res / 86400);
	
	document.getElementById("days").value = days;
}
</script>

</body>
</html>