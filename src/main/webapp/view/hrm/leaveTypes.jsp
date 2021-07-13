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
								 <h2 class="text-white pb-2 fw-bold">Leave Types</h2>
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
					                
											<form:form action="saveLeaveType" method="POST"
												onSubmit="return validateForm()" id="leaveTypeForm"
												modelAttribute="leaveTypeForm">
		
													<div class="form-group row">
														<div class="col-lg-8">
															<label for="leaveCode">Leave Code</label>
															<form:input path="leaveCode" class="form-control form-control-sm" id="leaveCode" required="true" />
														</div>
													</div>
		
													<div class="form-group row">
														<div class="col-lg-8">
															<label for="leaveType">Leave Type</label>
															<form:input path="leaveType" class="form-control form-control-sm" id="leaveType" required="true" />
														</div>
													</div>

													<div class="form-group row">
														<div class="col-lg-8">
															<input type="submit" class="btn btn-success btn-sm" value="Save">
															<input type="reset" class="btn btn-danger btn-sm" value="Reset">
														</div>
													</div>

		
											</form:form>
									</div>
									<div class="col-xl col-md-6 mb-4">
									
										<div class="table-responsive">
											<table class="table table-striped">
												<thead>
													<tr>
														<th>Leave Code</th>													
														<th>Leave Type</th>
														<th></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${typeList}" var="type">
														<tr>
															<td>${type.leaveCode}</td>															
															<td>${type.leaveType}</td>
															<td><a href="editLeaveType?id=${type.leaveCode}"><i
																class="fas fa-pen"></i></a></td>
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
			<%@include file="../../WEB-INF/jsp/footer.jsp"%>			
		</div>
	</div>
<%@include file="../../WEB-INF/jsp/commJs.jsp"%>

</body>
</html>