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
								 <h2 class="text-white pb-2 fw-bold">Import Daily Employee Earnings Report (Excel)</h2>
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

<form action="importExcelFile"  method="GET">

<div class="form-group row">
	<div class="col-lg-8">
	
		<label>Pay Period</label>
		<select class="form-control form-control-sm" id="payPeriod"
			name="payPeriod" onchange="getPayCodes(this.value)">
			<option value="" selected>--Select--</option>
			<c:forEach items="${payPeriodsList}" var="a">
				<option value="${a.payPeriodID}">start date ${a.startDate} / end date ${a.endDate}</option>
			</c:forEach>
		</select>

	</div>
</div>
<div class="form-group row">
	<div class="col-lg-8">
	
		<label>Pay Code</label>
		<select class="form-control form-control-sm" id="payCode"
			name="payCode">
			<option value="" selected>--Select--</option>
		</select>
		
	</div>
</div>

<div class="form-group row">
	<div class="col-lg-8">
		<button type="submit" class="btn  btn-block btn-danger btn-rounded" >
			Import
		</button>
	</div>
</div>	
</form>
					              
									</div>
									<div class="col-xl col-md-6 mb-4">
									

																			
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
</script>

</body>
</html>