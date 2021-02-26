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

* {
	text-transform: capitalize;
}

#jpdIDDiv {
	display: none;
}

.scrollable {
	height: 400px;
	overflow: scroll;
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
								<h2 class="text-white pb-2 fw-bold">Job Profile Details</h2>
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
								<form:form action="jobProfileDetailsAct" method="post"
									modelAttribute="jobProfileDetails" id="formProDetails">

									<div class="form-group row">
										<div class="col-sm-4">
											<div class="col-sm-60">
												<label>Job Profile</label>
												<form:select path="jobprofilemaster.profileID"
													class="custom-select" required="true">
													<form:option value="">Select Job Profile</form:option>
													<c:forEach items="${profileMaster}" var="job">
														<form:option value="${job.profileID}">${job.profile}</form:option>
													</c:forEach>
												</form:select>
											</div>
										</div>

										<div class="col-sm-4 mt-4" id="jpdIDDiv">
											<!-- <label class="invisible">Job profile Item ID</label> -->
											<form:input path="jobProfileID"
												value="${maxJobProfile.jobProfileID}" class=" border-0"
												id="jdID" />
										</div>

										<div class="col-sm-3 mt-4">
											<div class="col-sm-30">
												<!-- <label>Company ID</label> -->
												<input type="hidden" name="company.comID"
													class="form-control" id="comID"
													value="<%=session.getAttribute("company.comID")%>"
													placeholder="Company ID" />
											</div>
										</div>
									</div>
									<div class="form-group row">
										<div class="col-sm-4">
											<div class="col-sm-60">
												<label>Job Profile Item</label>
												<form:input path="profileItem" class="form-control"
													placeholder="Enter Profile Item" />
												<form:errors path="profileItem"
													cssClass="error1 text-danger" />
											</div>
										</div>
									</div>
									<div class="col-sm-8 mb-5">
										<div class="col-8">
											<input type="submit" class="btn btn-success btn-sm"
												value="Add Job Profile Detail"> <input type="reset"
												class="btn btn-danger btn-sm" value="Reset">
										</div>
									</div>
								</form:form>
								<div class="col-7">
									<div class="scrollable">
										<table id="tableProfileDetails" class="table table-hover"
											cellspacing="0" width="100%">
											<c:forEach items="${detaillist}" var="list">
												<tr>
													<td><a
														href="editjobProfile?jobProfileID=${list.jobProfileID}"><i
																class="far fa-edit"></i></a></td>
													<td id="tPro"><div>${list.jobprofilemaster.profile}</div></td>
													<td id="tItem"><div>${list.profileItem}</div></td>

												</tr>
											</c:forEach>
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