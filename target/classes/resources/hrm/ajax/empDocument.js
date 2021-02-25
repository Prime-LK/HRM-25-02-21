	function loadGrid() {

			var x = document.getElementById("empID").value;
			$("#tableDocument tbody").empty();

			$.ajax({
						type : 'GET',
						url : "geteidDtails",
						data : {
							"empID" : x
						},
						success : function(data) {

							$("#tableDocument tbody").empty();
							for (var i = 0; i < data.length; i++) {

								var result = "<tr><td>"
										+ "<a href=updateEid?eid="
										+ data[i].employeeIDPK.empID.empID
										+ "&empdoc="
										+ data[i].employeeIDPK.employeeIdDocument.docTypeId
										+ "&docid="
										+ data[i].employeeIDPK.docid
										+ "><img src='resources/img/edit.png' width='25px' height='25px'></a>"
										+ "</td><td id='type'> "
										+ data[i].employeeIDPK.employeeIdDocument.docType
										+ " </td><td id='desc'>"
										+ data[i].description + "</div><br><div id='preview'>"
										+ "<a href=empdocView?eid="
										+ data[i].employeeIDPK.empID.empID
										+ "&empdoc="
										+ data[i].employeeIDPK.employeeIdDocument.docTypeId
										+ "&docid="
										+ data[i].employeeIDPK.docid 
										+ "><i class='fa fa-download'>Preview</a></i></div></td></tr>";
								$("#tableDocument tbody").append(result);
							}
						},
						error : function() {
							alert("EMPLOYEE ID NOT FOUND!!");
						}

					});

		}