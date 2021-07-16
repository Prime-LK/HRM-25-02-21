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
								 <h2 class="text-white pb-2 fw-bold">Employee Entitlements</h2>
							</div>

						</div>
					</div>
				</div>
				
				<div class="page-inner mt--5">	
					<div class="container-fluid">

			              <div class="card shadow">
			                <div class="card-body">

								<div class="row">
									<div class="col-xl">

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
					                
											<form:form action="saveEmpEntitlement" method="POST"
												onSubmit="return validateForm()" id="entitlement"
												modelAttribute="entitlement">
												
											<form:input type="hidden" path="employeeEntitlementId" />
					                		<div class="form-group row">
												<div class="col-lg-8">
													<label for="empType">Employee Type</label>
													<form:select class="form-control form-control-sm" id="empType" path="employeeType.tid" required="true">
														<form:option value="" selected="true">--Select--</form:option>
														<c:forEach items="${allEmpTypes}" var="empType">
															<form:option value="${empType.tid}">${empType.type}</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
											<div class="form-group row">
												<div class="col-lg-8">
													<label for="leaveType">Leave Type</label>
													<form:select class="form-control form-control-sm" id="leaveType" path="leaveType.leaveTypeID" required="true">
														<form:option value="" selected="true">--Select--</form:option>
														<c:forEach items="${allLeaveTypes}" var="leaveType">
															<form:option value="${leaveType.leaveTypeID}"> ${leaveType.leaveType}</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
											<div class="form-group row">
												<div class="col-lg-4">
													<label for="leaveAmount">No of Days</label>
													<form:input path="leaveAmount" class="form-control form-control-sm" id="leaveAmount" required="true" />
												</div>
											</div>
											<div class="form-group row">
												<div class="col-lg">
													<input type="submit" class="btn btn-success btn-sm" value="Add Employee Entitlement">
													<input type="reset" class="btn btn-danger btn-sm" value="Reset">
												</div>
											</div>
		
											</form:form>
										</div>
										<div class="col-xl">
										
											<div class="table-responsive">
											<table class="table table-sm table-bordered table-hover">
												<thead>
													<tr>
														<th scope="col">Employee Type</th>
														<th scope="col">Leave Type</th>
														<th scope="col">No of Days</th>
													</tr>
												</thead>

												<tbody>
													<c:forEach items="${entitlementAll}" var="et">
														<tr>
															<td>${et.employeeType.type}</td>
															<td>${et.leaveType.leaveType}</td>
															<td>${et.leaveAmount}</td>
															<td>
																<a href="editEmpEntitlement?id=${et.employeeEntitlementId}">
																	<i class="fas fa-pen"></i>
																</a>
															</td>
														</tr>
													</c:forEach>
												</tbody>

											</table>
											</div>
											
										</div>
									</div> <!-- Row end -->
			                </div><!-- Card body end -->
			              </div><!-- Card end -->
			            
					</div>	
				</div>
				
			</div>	
			<%@include file="../../WEB-INF/jsp/footer.jsp"%>			
		</div>
	</div>
<%@include file="../../WEB-INF/jsp/commJs.jsp"%>

</body>
</html>