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
					                
											<form:form action="saveentitlement" method="post"
												onSubmit="return validateForm()" id="entitlement"
												modelAttribute="entitlement">
		
					                		<div class="form-group row">
												<div class="col-lg-8">
													<label>Entitlement Code</label>
													<form:input class="form-control form-control-sm" id="ent_ID" path="ent_ID" required="true" />
												</div>
											</div>
					                		<div class="form-group row">
												<div class="col-lg-8">
													<label>Employee Category</label>
													<form:select class="form-control form-control-sm" id="empCategory" path="empCategory.catgoryID" required="true" onchange="myFunction();">
														<form:option value="" selected="true">--Select--</form:option>
														<c:forEach items="${allCat}" var="cat">
															<form:option value="${cat.catgoryID}">${cat.category}</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
											<div class="form-group row">
												<div class="col-lg-8">
													<label>Leave Type</label>
													<form:select class="form-control form-control-sm" id="leaveType" path="leaveType.leaveCode" required="true">
														<form:option value="" selected="true">--Select--</form:option>
														<c:forEach items="${leaveAll}" var="lea">
															<form:option value="${lea.leaveCode}"> ${lea.leaveType}</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
											<div class="form-group row">
												<div class="col-lg-8">
													<label>No of Days</label>
													<form:input path="leaveAmount" class="form-control form-control-sm" id="leaveAmount" required="true" />
												</div>
											</div>
											<div class="form-group row">
												<div class="col-lg-8">
													<input type="submit" class="btn btn-success btn-sm" value="Save">
													<input type="reset" class="btn btn-warning btn-sm" value="Clear">
												</div>
											</div>
		
											</form:form>
										</div>
										<div class="col-xl col-md-6 mb-4">
										
											<table class="table table-striped">
												<thead>
													<tr>
														<th scope="col">Code</th>
														<th scope="col">Employee Category</th>
														<th scope="col">Leave Type</th>
														<th scope="col">No of Days</th>
													</tr>
												</thead>

												<tbody>
													<c:forEach items="${entitlementAll}" var="et">
														<tr>
															<td id="trCode">${et.ent_ID}</td>
															<td id="trCat">${et.empCategory.category}</td>
															<td id="trType">${et.leaveType.leaveType}</td>
															<td id="trAmou">${et.leaveAmount}</td>
															<td>
																<a href="UpdateEmp?id=${et.ent_ID}">
																	<i class="fas fa-pen"></i>
																</a>
															</td>
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

</body>
</html>