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
				<div class="page-inner">	
					<div class="page-header">
							<h4 class="page-title">Report</h4>
					</div>
					<div class="container-fluid">

						<div style="width:990px;" >
		
							<c:if test="${pdfViewEq != null }">
								<embed type="application/pdf" src="data:application/pdf;headers=filename%3D;base64,${pdfViewEq}"
									style="height:615px; width:100%" >
								</embed>
							</c:if>
						</div>
					
						<!-- loader -->
						<div class="loader-wrapper">
						    <span class="loader"><span class="loader-inner"></span></span>
						</div>
					
					</div>
				</div>
				
			</div>	
			<%@include file="../../WEB-INF/jsp/footer.jsp"%>			
		</div>
	</div>
<%@include file="../../WEB-INF/jsp/commJs.jsp"%>

	<script>
		$(window).on("load",function(){
		    $(".loader-wrapper").fadeOut("slow");
		});
	</script>
</body>
</html>