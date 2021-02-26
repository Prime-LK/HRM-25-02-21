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

#lIDDiv {
	display: none;
}

.scrollable {
	height: 400px;
	overflow: scroll;
}

* {
	text-transform: capitalize;
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
		<!-- Sidebar -->
		<%@include file="../../WEB-INF/jsp/sideBar.jsp"%>
		<!-- End Sidebar -->
		<div class="main-panel">
			<div class="content">
				<div class="panel-header bg-primary-gradient">
					<div class="page-inner py-3">
						<div
							class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
							<div class="col-xl-2 col-lg-2">
								<h2 class="text-white pb-2 fw-bold">Language Details</h2>
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
								<form:form action="saveLanguage" method="post"
									modelAttribute="saveLanguage" onsubmit="formValidation()"
									enctype="multipart/form-data" id="formLanguage">

									<div class="row">
										<div class="col-sm-4 ml-3">
											<div class="col-sm-60 mb-1 mb-sm-3">
												<label>Language</label>
												<form:input class="form-control form-control-user"
													id="language" path="language" placeholder="Enter Language" />
												<form:errors path="language" cssClass="error1 text-danger" />
											</div>
										</div>
										<div class="col-sm-2 pb-5">
											<div class="col-sm-30 mb-1 mb-sm-3" id="lIDDiv">
												<label class="invisible">ID</label>
												<form:input class="text-bold border-0 " id="lid" path="lid"
													readOnly="true" />
											</div>
										</div>

										<div class="col-sm-3 mt-4">
											<div class="col-sm-30 mb-1 mb-sm-3">
												<!-- <label>Company ID</label> -->
												<input type="hidden" name="company.comID"
													class="form-control" id="comID"
													value="<%=session.getAttribute("company.comID")%>"
													placeholder="Company ID" />
											</div>
										</div>
									</div>

									<div class="col-8 mb-3">
										<input type="submit" class="btn btn-success btn-sm"
											value="Add Language"> <input type="reset"
											class="btn btn-danger btn-sm" value="Reset">
									</div>

								</form:form>
								<div class="col-5">
									<div class="scrollable">
										<table class="table table-hover" width="100%" cellspacing="0"
											id="tableLanguageMaster">
											<thead>
												<!-- 																<tr> -->
												<!-- 																</tr> -->
											</thead>
											<tbody>
												<c:forEach items="${lMaster}" var="lm">
													<tr>
														<td><a href="updateLanguage?lid=${lm.lid}"> <i
																class="far fa-edit"></i></a></td>
														<td id="tLa">${lm.language}</td>

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