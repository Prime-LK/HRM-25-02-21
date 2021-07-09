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
								 <h2 class="text-white pb-2 fw-bold">Attendance Process</h2>
							</div>

						</div>
					</div>
				</div>
				
				<div class="page-inner mt--5">	
					<div class="container-fluid">

			              <div class="card shadow">
			                <div class="card-body">
			                
								<div class="row">
									<div class="col-xl-8">
<h3>${filesuccess}</h3>  
<form action="saveAttendanceProcess"  method="POST">

<div class="form-group row">
	<div class="col-lg-5">
		<label for="payPeriod">Pay Period</label>
		<select class="form-control form-control-sm" id="payPeriod"
			name="payPeriodID" required>
			<option value="" selected>--Select--</option>
			<c:forEach items="${payPeriodsList}" var="a">
				<option value="${a.payPeriodID}">${a.startDate} to ${a.endDate}</option>
			</c:forEach>
		</select>
	</div>
	<div class="col-lg">
		<label for="department">Department</label>
		<select class="form-control form-control-sm" id="department"
			name="depID" onchange="getEmployees(this.value)">
			<option value="" selected>--Select--</option>
			<c:forEach items="${DepAll}" var="dp">
				<option value="${dp.depID}">${dp.department}</option>
			</c:forEach>
		</select>
	</div>
	<div class="col-lg">
		<label for="employee">Employee</label>
		<select class="form-control form-control-sm" id="employee"
			name="employeeID">
			<option value="" selected>--Select--</option>
			<c:forEach items="${EmpAll}" var="emp">
				<option value="${emp.empID}">${emp.name} ${emp.lastname}</option>
			</c:forEach>
		</select>
	</div>
</div>


<div class="form-group row">
	<div class="col-lg">
		<button type="submit" class="btn btn-success btn-sm" >
			Process
		</button>
		<button type="reset" class="btn btn-danger btn-sm" >
			Reset
		</button>
	</div>
</div>	
</form>
					              
									</div>
									<div class="col-xl">
									

																			
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

<script>

function getEmployees(str) {
	if (str=="") {
		var dropDown = $('#employee'), option="";
		dropDown.empty();
		return;
	}
	else{
		$.ajax({
        type: 'GET',
        url: "getEmployeesByDepID",
        data: {"depID" : str},
        success: function(data){

			var dropDown=$('#employee'), option="";
			dropDown.empty();
			selected_option = "<option>--Select--</option>"
			dropDown.append(selected_option);

            for(var i=0; i<data.length; i++){
                option = option + "<option value='"+data[i].detailsPK.empID.empID+ "'>"+data[i].detailsPK.empID.name+" "+data[i].detailsPK.empID.lastname+ "</option>";
            }
            dropDown.append(option);
        },
        error:function(){
            alert("error");
        }

    	});
	}	
}


function getPayCodes(str)
{
	if (str=="") {
		var dropDown = $('#payCode'), option="";
		dropDown.empty();
		return;
	}
	else{
		$.ajax({
        type: 'GET',
        url: "getPayCodes",
        data: {"payPeriodID" : str},
        success: function(data){

			var dropDown=$('#payCode'), option="";
			dropDown.empty();
			selected_option = "<option>--Select--</option>"
			dropDown.append(selected_option);

            for(var i=0; i<data.length; i++){
                option = option + "<option value='"+data[i].payCodeID + "'>"+data[i].payCode + "</option>";
            }
            dropDown.append(option);
        },
        error:function(){
            alert("error");
        }

    	});
	}
}

function getDatesBetweenPayPeriod()
{
	var payPeriod = document.getElementById("payPeriod").value;
	var employee = document.getElementById("employee").value;
	
	if (payPeriod=="" || employee=="") {
		return;
	}
	else{
		$.ajax({
        type: 'GET',
        url: "getDatesBetweenPayPeriod",
        data: {"payPeriodID":payPeriod,"employeeID":employee},
        success: function(data){

        },
        error:function(){
            alert("error");
        }

    	});
	}
}
</script>

</body>
</html>