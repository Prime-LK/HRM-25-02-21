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