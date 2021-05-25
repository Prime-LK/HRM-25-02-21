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
				                
				                <form action="leaveReport"  method="GET">
				                
			                		<div class="form-group row">
										<div class="col-lg">
											<label>Department</label>
											<select class="form-control form-control-sm" id="department"
												name="dep_id" required>
												<option value="" selected>--Select--</option>
												<c:forEach items="${DepAll}" var="dp">
													<option value="${dp.depID}">${dp.department}</option>
												</c:forEach>
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

</body>
</html>