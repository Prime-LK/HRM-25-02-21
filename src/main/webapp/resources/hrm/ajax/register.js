function loadEmpDetails() {
	var e = document.getElementById("empID");
	$.ajax({
		type : "GET",
		url : "updateEmp",
		data : {"id" : e.value},
		success : function(data) {
				window.location.href = "updateEmp?id=" + e.value;
		},
		error : function(e) {
//			 alert("This Employee ID Not Saved Yet");
		}
	});
}

function getbranchData(str) {
	var bankCode = document.getElementById("bank_Code").value;
	if (str == "") {
		var slctSubcat = $('#bankBranch_Code'), option = "";
		slctSubcat.empty();
		return;
	} else {
		$
				.ajax({
					type : 'GET',
					url : "getbranchcombo",
					data : {"bank_Code" : bankCode},
					success : function(data) {
						var slctSubcat = $('#bankBranch_Code'), option = "";
						slctSubcat.empty();
						selected_option = "<option value='' selected>--SELECT--</option>"
						slctSubcat.append(selected_option);

						for (var i = 0; i < data.length; i++) {
							option = option + "<option value='"
									+ data[i].branchID + "'>" + data[i].branch
									+ "</option>";
						}
						slctSubcat.append(option);
					},
					error : function() {
						alert("error");
					}
				});
	}
}

function loadBankBranchesByBankAndCompany() {

	var bankId = document.getElementById("bank_Code").value;
	if (bankId == "") {
		var selectBankBranch = $("#bankBranch_Code"), option = "";
		selectBankBranch.empty();
		return;
	} else {

		$
				.ajax({
					type : 'GET',
					url : "getAllBankBranchByBankAndCompany",
					data : {
						"bankId" : bankId
					},
					success : function(data) {
						if (data.length == 0) {
							swal("No data is available for the selected parameters!", "", {
								icon : "info",
								buttons : {
									confirm : {
										className : 'btn btn-primary'
									}
								},
							});
						}
						var selectBankBranch = $("#bankBranch_Code"), option = "";
						selectBankBranch.empty();
						selected_option = "<option value='' selected>--SELECT--</option>";
						selectBankBranch.append(selected_option);

						for (var i = 0; i < data.length; i++) {
							option = option + "<option value='"
									+ data[i].branchID + "'>"
									+ data[i].branch
									+ "</option>";
						}
						selectBankBranch.append(option);
					},
					error : function() {
						swal("Error!", "", {
							icon : "error",
							buttons : {
								confirm : {
									className : 'btn btn-danger'
								}
							},
						});
					}

				});
	}

}