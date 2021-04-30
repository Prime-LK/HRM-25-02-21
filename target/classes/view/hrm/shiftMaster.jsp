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
		<!-- slideBar -->
		<%@include file="../../WEB-INF/jsp/slideBar.jsp"%>
		<!-- End slideBar -->
		<div class="main-panel">
			<div class="content">
				<div class="panel-header bg-primary-gradient">
					<div class="page-inner py-3">
						<div
							class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
							<div class="col-xl-2 col-lg-2">
								<h2 class="text-white pb-2 fw-bold">Shifts</h2>
							</div>
							<div class="col-xl-2 col-lg-2"></div>
							<div class="ml-md-auto py-2 py-md-4"></div>

							<div class="ml-md-auto py-2 py-md-4"></div>
						</div>
					</div>
				</div>

				<div class="page-inner mt--5">
					<div class="container-fluid">


						<div class="row">
							<div class="col-md-12">
								<div class="card">
									<div class="card-body">

										<!-- Page Heading -->
										<h1 class="h3 mb-4 text-gray-800"></h1>
										<form:form id="shiftMaster" onsubmit="return validateForm()"
											action="saveShiftMaster" modelAttribute="ShiftMaster"
											method="post">
											<div class=" row">
												<div class="col-9">
													<div class="form-group row">
														<div class="col-sm-8">
															<label>Description</label>
															<form:input id="description" path="description"
																type="text" class="form-control" value="" />
														</div>
														<div class="col-sm-2">
															<!-- <label>Shift ID</label> -->
															<form:input id="shiftId" path="shiftId" type="hidden"
																class="form-control" value="${sMaxID.shiftId}"
																readonly="true" />
														</div>
													</div>
												</div>
											</div>
											<div class=" row">
												<div class="col-9">
													<div class="form-group row">
														<div class="col-sm-3">
															<label>Start Time</label>
															<form:input id="startTime" path="startTime" type="time"
																class="form-control" value="" />
															<span id="div1"></span>
														</div>
														<div class="col-sm-3">
															<label>End Time</label>
															<form:input id="endTime" path="endTime" type="time"
																class="form-control" value="" />
															<span id="div2"></span>
														</div>
														<div class="col-sm-2">
															<label for="check">Continuing</label> <br>
															<form:checkbox path="recurring" value="True" />
															<span id="div2"></span>
														</div>
														<div class="col-sm-1">
															<!-- <label>Company ID</label> -->
															<input type="hidden" name="companyId"
																class="form-control" id="companyId"
																value="<%=session.getAttribute("company.comID")%>" />
														</div>
													</div>
												</div>
											</div>
											<div class=" row">
												<div class="col-9">
													<div class="form-group row">
														<div class="col-sm-6">
															<input type="submit"
																class="btn btn-primary btn-sm-3 mr-3" value="Add Shift">
															<input type="reset" class="btn btn-danger btn-sm-3 mr-3"
																value="Reset">
														</div>
													</div>
												</div>
											</div>

										</form:form>

										<div class="col-12">
											<div class="scrollable">
												<table id="basic-datatables"
													class="display table table-striped table-hover table-bordered"
													width="100%" cellspacing="0" name="shiftList">
													<thead>
														<tr>
															<th style="display: none">Shift ID</th>
															<th>Description</th>
															<th>Start Time</th>
															<th>End Time</th>
															<th>Continuing</th>
															<th></th>
														</tr>

													</thead>
													<tbody>

														<c:forEach items="${shiftList}" var="shifts">
															<tr>
																<td id="shiftcode" style="display: none">${shifts.shiftId}</td>
																<td id="description" width="300rem">${shifts.description}</td>
																<td id="starttime">${shifts.startTime}</td>
																<td id="endtime">${shifts.endTime}</td>
																<td id="recurring"><c:if
																		test="${shifts.recurring == 'true'}">Yes</c:if> <c:if
																		test="${shifts.recurring == 'false'}">No</c:if></td>
																<td width="25rem"><a
																	href="updateShiftMaster?id=${shifts.shiftId}"> <i
																		class="far fa-edit"></i>
																</a></td>
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

			</div>
			<%@include file="../../WEB-INF/jsp/footer.jsp"%>
		</div>
	</div>
	<%@include file="../../WEB-INF/jsp/commJs.jsp"%>

	<!-- Datatable -->
	<script src="<c:url value='/resources/hrm/ajax/datatable.js'/>"></script>


</body>
</html>