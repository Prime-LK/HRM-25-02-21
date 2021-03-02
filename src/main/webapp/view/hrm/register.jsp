<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<html lang="en">
<head>
<%@include file="../../WEB-INF/jsp/head.jsp"%>
<link href="<c:url value='/resources/hrm/css/userCommonDetails.css'/>"
	rel="stylesheet"> 
<link href="<c:url value='/resources/hrm/css/register.css'/>"
	rel="stylesheet"> 
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
		<!-- Sidebar -->
		<%@include file="../../WEB-INF/jsp/sideBar.jsp"%>
		<!-- End Sidebar -->
		<div class="main-panel">
			<form:form action="saveEmployee" method="post"
				modelAttribute="saveRegister" enctype="multipart/form-data"
				id="formRegister">
				<div class="content">
					<div class="panel-header bg-primary-gradient">
						<div class="page-inner py-3">
							<div
								class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
								<div class="col-xl-12 col-lg-2">
									<div class="container mt-4">
										<div class="row">
											<div class="col-sm mt-4">
												<div class="row">
													<div class="col-2">
														<input type="file" class="file" accept="image/*"
															id="profileImg" name="profileImg">

														<c:if test="${EImg != null}">
															<img src="data:image/jpg;base64,${EImg}" id="preview"
																class="border border-dark rounded-lg"
																style="width: 80px; height: 80px;">
														</c:if>
														<c:if test="${EImg == null}">
															<img src="resources/img/user-default.jpg" id="preview"
																class="border border-dark rounded-lg"
																style="width: 80px; height: 80px;">
														</c:if>
														<div class="input-group mt--2">
															<div class="input-group-append mt-3">
																<button type="button" id="button"
																	class="browse btn btn-success btn-sm">Upload</button>
															</div>
														</div>
													</div>
													<div class="col-10 mt--3">
														<div class="row ml-3">
															<div class="col-12">
																<div class="form-group">
																	<span> <c:if test="${eid != null}">
																			<div>
																				<span id="comDeId"><%=session.getAttribute("eid")%></span><br>
																			</div>
																		</c:if>
																	</span> <span> <c:if
																			test="${ename != null || lastName != null }">
																			<div>
																				<span id="comDeId"><%=session.getAttribute("ename")%></span>
																				<span id="comDeId"><%=session.getAttribute("lastName")%></span><br>
																				<span id="comDeId"><%=session.getAttribute("addLine01")%></span><br>
																				<span id="comDeId"><%=session.getAttribute("addLine02")%></span><br>
																			</div>
																		</c:if>
																	</span>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											<!-- <div class="col-sm">One of three columns</div> -->
											<div class="col-sm mt-5">
												<div class="container-fluid">
													<div class="collapse" id="search-nav">
														<form autocomplete="off"
															class="navbar-left navbar-form nav-search mr-md-3">
															<div class="input-group">
																<input id="myInput" type="text"
																	placeholder="Search Employee..." class="form-control"
																	autocomplete="off" onkeyup="sample()">
																<div class="input-group-append">
																	<a class="btn btn-secondary btn-sm"
																		onclick="getValue()"> <span class="btn-label">
																			<i class="fa fa-search-plus mt-3" aria-hidden="true"></i>
																	</span>
																	</a>
																</div>
															</div>
														</form>
													</div>
												</div>
											</div>
										</div>
									</div>
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
									<!-- end of middle bar -->
									<nav class="navbar navbar-expand-sm">
										<button class="navbar-toggler" data-toggle="collapse"
											data-target="#navbarMenu">
											<span class="navbar-toggler-icon"></span>
										</button>
										<div class="collapse navbar-collapse" id="navbarMenu">
											<ul class="navbar-nav mr-auto">
												<li class="nav-item active" id=""><a class="nav-link"
													href="register">Employee</a></li>
												<li class="nav-item"><a class="nav-link"
													href="employeeDetails">Employment</a></li>
												<li class="nav-item"><a class="nav-link"
													href="employeeQualification">Qualification & Membership</a></li>
												<li class="nav-item"><a class="nav-link"
													href="workExperience"> Work Experience</a></li>
												<li class="nav-item"><a class="nav-link"
													href="employeeSkill">Skills,Language & Activity</a></li>
												<li class="nav-item"><a class="nav-link"
													href="employeeDependent">Dependent</a></li>
												<li class="nav-item"><a class="nav-link"
													href="employeeID">Documents</a></li>
												<!-- 			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="empDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false">Others</a>
				<div class="dropdown-menu" aria-labelledby="empDropdown">	 
				</div></li> -->
											</ul>
										</div>
									</nav>
									<hr>
									<!-- end of middle bar -->
									<div class="row">
										<div class="col-sm-4">
											<div class="form-group row mb--2">
												<label class="col-4">Employee ID</label>
												<form:input path="empID" type="text"
													class="form-control col-8" placeholder="Employee ID"
													id="empID" onchange="loadEmpDetails()" />
												<p id="msg"></p>
											</div>
										</div>
										<div class="col-4">
											<div class="form-group row">
												<label class="col-4">First Name</label>
												<form:input path="name" type="text" id="firstName"
													class="form-control col-8" placeholder="First Name" />
												<p id="msgFname"></p>
											</div>
										</div>
										<div class="col-4">
											<div class="form-group row">
												<label class="col-4">Last Name</label>
												<form:input path="lastname" type="text" id="lastName"
													class="form-control col-8" placeholder="last Name" />
												<p id="msgLname"></p>
											</div>
										</div>
									</div>
									<div class="row mt--2">
										<div class="col-8">
											<div class="form-group row">
												<label class="col-2">Address <i
													class="fa fa-map-marker" aria-hidden="true"></i></label>
												<form:input path="address" type="text" id="address"
													class="form-control col-10" placeholder="Address" />
											</div>
										</div>
										<div class="col-4">
											<div class="form-group row">
												<label class="col-4">City</label>
												<form:input path="city" type="text"
													class="form-control col-8" placeholder="city" />
											</div>
										</div>
									</div>
									<div class="row mt--2">
										<div class="col-4">
											<div class="form-group row">
												<label class="col-4">State</label>
												<form:select class="form-control col-8" path="state"
													required="true">
													<form:option value="" selected="true">--SELECT--</form:option>
													<form:option value="central">Central province</form:option>
													<form:option value="eastern">Eastern Province</form:option>
													<form:option value="northen">Northern Province</form:option>
													<form:option value="southern">Southern Province</form:option>
													<form:option value="wenstern">Western Province</form:option>
													<form:option value="north weston">North Western Province</form:option>
													<form:option value="north central">North Central Province</form:option>
													<form:option value="uva">Uva Province</form:option>
													<form:option value="sabaragamuwa">Sabaragamuwa Province</form:option>
												</form:select>
											</div>
										</div>
										<div class="col-4">
											<div class="form-group row">
												<label class="col-4">Con. No. <i
													class="fa fa-phone-square" aria-hidden="true"></i></label>
												<form:input path="contact_num1" type="number"
													class="form-control col-8" id="conNo01"
													placeholder="Contact Number" />
												<p id="msgConNo01"></p>
											</div>
										</div>
										<div class="col-4">
											<div class="form-group row">
												<label class="col-4">Con. No. <i class="fa fa-home"
													aria-hidden="true"></i></label>
												<form:input type="number" path="contact_num2"
													class="form-control col-8" id="conNo02"
													placeholder="Contact Number" />
												<p id="msgConNo02"></p>
											</div>
										</div>
									</div>
									<div class="row mt--2">
										<div class="col-4">
											<div class="form-group row">
												<label class="col-4">Email <i class="fa fa-envelope"
													aria-hidden="true"></i></label>
												<form:input path="email" type="email" id="email"
													class="form-control col-8" placeholder="Email" />
											</div>
										</div>
										<div class="col-4">
											<div class="form-group row">
												<label class="col-4">Password</label>
												<form:input path="password" type="password"
													class="form-control col-8" placeholder="Password" />
											</div>
										</div>
									</div>
									<div class="row mt--2">
										<div class="col-4" id="comDiv">
											<div class="form-group row">
												<!-- <label>Employee Password</label> -->
												<input type="text" name="company.comID" class="form-control"
													id="comID"
													value="<%=session.getAttribute("company.comID")%>"
													placeholder="Company ID" readOnly />
											</div>
										</div>
									</div>
									<hr>
									<div class="row">
										<div class="col-4">
											<div class="form-group row mb--2">
												<label class="col-4">DOB</label>
												<form:input path="dob" type="date"
													class="form-control col-8"
													placeholder="Enter Date Of Birth" id="dob" />
											</div>
										</div>
										<div class="col-4">
											<div class="form-group row">
												<label class="col-4">ID Number</label>
												<form:input path="id_Number" type="text"
													placeholder="ID number"
													class="form-control col-8" />
											</div>
										</div>
										<div class="col-4">
											<div class="form-group row">
												<label class="col-4">Bank</label>
												<form:select class="form-control col-8"
													onfocusout="getbranchData();" id="bank_Code"
													path="bank_Code">
													<form:option value="" selected="true">--SELECT--</form:option>
													<c:forEach items="${bankmastertable}" var="b">
														<form:option value="${b.bankid}">${b.bankName}</form:option>
													</c:forEach>
												</form:select>
											</div>
										</div>
									</div>
									<div class="row mt--2">
										<div class="col-4">
											<div class="form-group row">
												<label class="col-4">Gender</label>
												<form:select class="form-control col-8"
													path="gender" required="true">
													<form:option value="" selected="true">--SELECT--</form:option>
													<form:option value="male">Male</form:option>
													<form:option value="female">Female</form:option>
													<form:option value="other">Other</form:option>
												</form:select>
											</div>
										</div>
										<div class="col-4">
											<div class="form-group row">
												<label class="col-4">License <i
													class="fas fa-car ml-2"></i></label>
												<form:input path="dl_number" type="text"
													placeholder="license number" class="form-control col-8" />
											</div>
										</div>
										<div class="col-4">
											<div class="form-group row">
												<label class="col-4">Branch </label>
												<form:select class="form-control col-8" id="bankBranch_Code"
													path="bankBranch_Code.branchID">
													<form:option value="" selected="true">--SELECT--</form:option>
													<c:forEach items="${bankBranch}" var="b">
														<form:option value="${b.branchID}">${b.branch}</form:option>
													</c:forEach>
												</form:select>
											</div>
										</div>
									</div>
									<div class="row mt--2">
										<div class="col-4">
											<div class="form-group row">
												<label class="col-4">Nationality</label>
												<form:select
													class="form-control col-8"
													path="nationality.nId" required="true">
													<form:option value="">--SELECT--</form:option>
													<c:forEach items="${nationalities}" var="na">
														<form:option value="${na.nId}">${na.nationality}</form:option>
													</c:forEach>
												</form:select>

											</div>
										</div>
										<div class="col-4">
											<div class="form-group row">
												<label class="col-4">Pass. No.</label>
												<form:input path="passport_Number" type="text"
													placeholder="passport number"
													class="form-control col-8" />
											</div>
										</div>
										<div class="col-4">
											<div class="form-group row">
												<label class="col-4">Account No.</label>
												<form:input path="bank_Account" type="text"
													class="form-control col-8" id="name"
													placeholder="Account number" />
											</div>
										</div>
									</div>
									<div class="row mt--2">
										<div class="col-4">
											<div class="form-group row">
												<label class="col-4">Religion</label>
												<form:select class="form-control col-8"
													path="religion.rid" required="true">
													<form:option value="">--SELECT--</form:option>
													<c:forEach items="${religion}" var="r">
														<form:option value="${r.rid}">${r.religion}</form:option>
													</c:forEach>
												</form:select>
											</div>
										</div>
										<div class="col-4">
											<div class="form-group row">
												<label class="col-4">Emerg. <i
													class="fas fa-phone ml-2"></i></label>
												<form:input path="emergency_Contact_No" type="text"
													placeholder="emergency number"
													class="form-control col-8" />
											</div>
										</div>
									</div>
									<div class="row mt--2">
										<div class="col-4">
											<div class="form-group row">
												<label class="col-4">Marital status</label>
												<form:select class="form-control col-8"
													path="mStatus" required="true">
													<form:option value="" selected="true">--SELECT--</form:option>
													<form:option value="single">Single</form:option>
													<form:option value="married">Married</form:option>
													<form:option value="other">Other</form:option>
												</form:select>
											</div>
										</div>
										<div class="col-4">
											<div class="form-group row">
												<label class="col-4">Blood Group </label>
												<form:select path="blood_Group"
													class="form-control col-8">
													<form:option value="" selected="true">--SELECT--</form:option>
													<form:option value="a+">A+</form:option>
													<form:option value="a-">A-</form:option>
													<form:option value="B+">B+</form:option>
													<form:option value="B-">B-</form:option>
													<form:option value="O+">O+</form:option>
													<form:option value="O-">O-</form:option>
													<form:option value="AB+">AB+</form:option>
													<form:option value="AB-">AB-</form:option>
												</form:select>
											</div>
										</div>
										<div class="col-4">
											<div class="form-group row">
												<!-- <label class="col-2"></label> -->
												<div class="col-12">
													<button type="submit" id="submitBtn"
														class="btn btn-success">
														<i class="fa fa-plus"></i> Add Employee
													</button>
													<button type="reset" id="resetBtn"
														class="browse btn btn-danger">
														<i class="fa fa-arrow-circle-right" aria-hidden="true"></i>
														Reset
													</button>
												</div>
											</div>
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>

				</div>
			</form:form>
			<%@include file="../../WEB-INF/jsp/footer.jsp"%>
		</div>
	</div>
	<%@include file="../../WEB-INF/jsp/commJs.jsp"%>
	<script src="<c:url value='/resources/hrm/ajax/register.js'/>"></script>

	<script
		src="<c:url value='/resources/validation/registerValidation.js'/>"></script>

	<script src="<c:url value='/resources/hrm/ajax/searchBar.js'/>"></script>
</body>
<!-- --image browser -->
<script type="text/javascript">
	$(document).on("click", ".browse", function() {
		var file = $(this).parents().find(".file");
		file.trigger("click");
	});
	$('#profileImg').change(function(e) {
		var fileName = e.target.files[0].name;
		$("#file").val(fileName);

		var reader = new FileReader();
		reader.onload = function(e) {
			// get loaded data and render thumbnail.
			document.getElementById("preview").src = e.target.result;
		};
		// read the image file as a data URL.
		reader.readAsDataURL(this.files[0]);
	})
</script>
</html>