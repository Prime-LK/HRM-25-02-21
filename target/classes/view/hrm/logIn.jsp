<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Login</title>

<!-- Custom fonts for this template-->
<link href="resources/hrm/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="<c:url value='resources/hrm/css/sb-admin-2.min.css'/>" rel="stylesheet">

</head>

<body class="">

	<div class="container">

		<!-- Outer Row -->
		<div class="row justify-content-center">

			<div class="col-xl-10 col-lg-12 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="row">
							<div class="col-lg-6 d-none d-lg-block bg-gradient-light">
								<img alt="" src="resources/hrm/img/logImg.jpg" width="100%"
									height="100%">
							</div>
							<div class="col-lg-6 bg-gradient-light">
								<div class="p-5">
									<div class="text-center">
										<img class="img-profile  rounded-circle"
											src="resources/hrm/img/user-default.jpg" height="150" width="150"
											id="mydemo" />

										<h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
									</div>
									<form class="user" action="login" method="POST"
										modelAttribute="login">
										<div class="form-group">
											<input type="text" class="form-control form-control-user"
												id="name" name="name" aria-describedby="emailHelp"
												placeholder="Enter Username..." required
												onchange="getUserImage(this.value)">
										</div>
										<div class="form-group">
											<input type="password" class="form-control form-control-user"
												id="exampleInputPassword" name="password"
												placeholder="Enter Password...">
										</div>
										<div class="form-group">
											<select name="company.comID" class="custom-select" required>
												<option value="">Select Company</option>
												<c:forEach items="${allComMas}" var="add">
													<option value="${add.comID}">${add.comName}</option>
												</c:forEach>
											</select>
										</div>
										<table align="center">
											<tr>
												<td style="font-style: italic; color: red;"></td>
											</tr>
										</table>
										<!--                     <a href="index.html" class="btn btn-primary btn-user btn-block"> -->
										<!--                       Login -->
										<!--                     </a> -->
										<button type="submit"
											class="btn btn-primary btn-user btn-block">Login</button>
									</form>

								</div>
							</div>
						</div>
					</div>
				</div>

			</div>

		</div>

	</div>
	<!-- Bootstrap core JavaScript-->
	<script src="resources/hrm/vendor/jquery/jquery.min.js"></script>
	<script src="resources/hrm/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="resources/hrm/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="resources/hrm/js/sb-admin-2.min.js"></script>


	<script type="text/javascript">
		function getUserImage(str) {
			if (str == "") {
				//document.getElementById("txtHint").innerHTML="";
				//var slctSubcat=$('#visualProfileStageID'), option="";
				//slctSubcat.empty();

				return;
			} else {
				$.ajax({
					type : 'GET',
					url : "logingimage",
					data : {
						"name" : str
					},
					success : function(data) {
						//delete previous selected value
						var img = $('#mydemo'), value = "";
						img.empty();

						var x = document.getElementById("mydemo");
						x.setAttribute("src", "data:image/jpg;base64," + data
								+ "");

						document.getElementById("mydemo").appendChild(x);

					},
					error : function() {
						/* alert("Image Not Found"); */
					}

				});
			}
		}
	</script>




</body>

</html>
