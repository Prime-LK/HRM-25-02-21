function addDataToTbl() {
	var a = document.getElementById("year").value;
	var b = document.getElementById("month").value;
	var c = document.getElementById("depID").value;
	var d = document.getElementById("comID").value;

	var a = document.getElementById("addAllAllowance").checked;
	if (a == true) {
		$.ajax({
			type : 'POST',
			url : "saveSalaryAnalyzeList",
			data : {
				"year" : a,
				"month" : b,
				"depatment.depID" : c,
				"company.comID" : d
			},
			success : function(data) {

			},
			error : function() {
				alert("Couldn't data saved !");
			}
		});

	} else if (a == false) {
		$("#alloTable tbody").empty();
		clearAllowance();
	} else {
		console.log('function error');
	}
}

function clearAllowance() {
	$.ajax({
		type : 'DELETE',
		url : "deleteAllSAdetails",
		success : function(data) {
			$("#alloTable tbody").empty();
		},
		error : function() {
			alert("Couldn't data deleted !");
		}
	});
}

function getTableData() {
	$.ajax({
		type : 'GET',
		url : "salaryAnalyzeTableHeaderData",
		success : function(data) {
			$("#detailsTbl thead").empty();
			for (var i = 0; i < data.length; i++) {
				var result = "<tr>" 
						   + "<th>" + data[i][0] + "</th>" 
						   + "<th><span id='allowanceLBL1'>" + data[i][1] + "</span></th>"
						   + "<th><span id='allowanceLBL2'>" + data[i][2] + "</span></th>"
						   + "<th><span id='allowanceLBL3'>" + data[i][3] + "</span></th>"
						   + "<th><span id='allowanceLBL4'>" + data[i][4] + "</span></th>"
						   + "<th><span id='allowanceLBL5'>" + data[i][5] + "</span></th>" 
						   + "<th><span id='allowanceLBL6'>" + data[i][6] + "</span></th>"
						   + "<th><span id='allowanceLBL7'>" + data[i][7] + "</span></th>"
						   + "<th><span id='allowanceLBL8'>" + data[i][8] + "</span></th>"
						   + "<th><span id='allowanceLBL9'>" + data[i][9] + "</span></th>"
						   + "<th><span id='allowanceLBL10'>" + data[i][10] + "</span></th>" 
						   + "<th><span id='allowanceLBL11'>" + data[i][11] + "</span></th>"
						   + "<th><span id='allowanceLBL12'>" + data[i][12] + "</span></th>"
						   + "<th><span id='allowanceLBL13'>" + data[i][13] + "</span></th>"
						   + "<th><span id='allowanceLBL14'>" + data[i][14] + "</span></th>"
						   + "<th><span id='allowanceLBL15'>" + data[i][15] + "</span></th>" 
						   + "<th><span id='allowanceLBL16'>" + data[i][16] + "</span></th>"
						   + "<th><span id='allowanceLBL17'>" + data[i][17] + "</span></th>"
						   + "<th><span id='allowanceLBL18'>" + data[i][18] + "</span></th>"
						   + "<th><span id='allowanceLBL19'>" + data[i][19] + "</span></th>"
						   + "<th><span id='allowanceLBL20'>" + data[i][20] + "</span></th>" 
						   + "<th><span id='allowanceLBL21'>" + data[i][21] + "</span></th>"
						   + "<th><span id='allowanceLBL22'>" + data[i][22] + "</span></th>"
						   + "<th><span id='allowanceLBL23'>" + data[i][23] + "</span></th>"
						   + "<th><span id='allowanceLBL24'>" + data[i][24] + "</span></th>"
						   + "<th><span id='allowanceLBL25'>" + data[i][25] + "</span></th>" 
						   + "<th><span id='allowanceLBL26'>" + data[i][26] + "</span></th>"
						   + "<th><span id='allowanceLBL27'>" + data[i][27] + "</span></th>"
						   + "<th><span id='allowanceLBL28'>" + data[i][28] + "</span></th>"
						   + "<th><span id='allowanceLBL29'>" + data[i][29] + "</span></th>"
						   + "<th><span id='allowanceLBL30'>" + data[i][30] + "</span></th>"
						   + "<th><span id='allowanceLBL31'>" + data[i][31] + "</span></th>"
						   + "</tr>";
				$("#detailsTbl thead").append(result);
			}
		},
		error : function() {
			alert("Table Header Data Not Found !");
		}
	});
	$.ajax({
		type : 'GET',
		url : "salaryAnalyzeTableBodyData",
		success : function(data) {
			$("#detailsTbl tbody").empty();
			for (var i = 0; i < data.length; i++) {
				var result = "<tr>" 
							+ "<td>" + data[i][0] + "</td>" 
							+ "<td><span id='allowance1'>" + data[i][1] + "</span></td>"
							+ "<td><span id='allowance2'>" + data[i][2] + "</span></td>"
							+ "<td><span id='allowance3'>" + data[i][3] + "</span></td>"
							+ "<td><span id='allowance4'>" + data[i][4] + "</span></td>"
					   		+ "<td><span id='allowance5'>" + data[i][5] + "</span></td>" 
					   		+ "<td><span id='allowance6'>" + data[i][6] + "</span></td>"
					   		+ "<td><span id='allowance7'>" + data[i][7] + "</span></td>"
					   		+ "<td><span id='allowance8'>" + data[i][8] + "</span></td>"
					   		+ "<td><span id='allowance9'>" + data[i][9] + "</span></td>"
					   		+ "<td><span id='allowance10'>" + data[i][10] + "</span></td>" 
					   		+ "<td><span id='allowance11'>" + data[i][11] + "</span></td>"
					   		+ "<td><span id='allowance12'>" + data[i][12] + "</span></td>"
					   		+ "<td><span id='allowance13'>" + data[i][13] + "</span></td>"
					   		+ "<td><span id='allowance14'>" + data[i][14] + "</span></td>"
					   		+ "<td><span id='allowance15'>" + data[i][15] + "</span></td>" 
					   		+ "<td><span id='allowance16'>" + data[i][16] + "</span></td>"
					   		+ "<td><span id='allowance17'>" + data[i][17] + "</span></td>"
					   		+ "<td><span id='allowance18'>" + data[i][18] + "</span></td>"
					   		+ "<td><span id='allowance19'>" + data[i][19] + "</span></td>"
					   		+ "<td><span id='allowance20'>" + data[i][20] + "</span></td>" 
					   		+ "<td><span id='allowance21'>" + data[i][21] + "</span></td>"
					   		+ "<td><span id='allowance22'>" + data[i][22] + "</span></td>"
					   		+ "<td><span id='allowance23'>" + data[i][23] + "</span></td>"
					   		+ "<td><span id='allowance24'>" + data[i][24] + "</span></td>"
					   		+ "<td><span id='allowance25'>" + data[i][25] + "</span></td>" 
					   		+ "<td><span id='allowance26'>" + data[i][26] + "</span></td>"
					   		+ "<td><span id='allowance27'>" + data[i][27] + "</span></td>"
					   		+ "<td><span id='allowance28'>" + data[i][28] + "</span></td>"
					   		+ "<td><span id='allowance29'>" + data[i][29] + "</span></td>"
					   		+ "<td><span id='allowance30'>" + data[i][30] + "</span></td>"
					   		+ "<td><span id='allowance31'>" + data[i][31] + "</span></td>"
					   		+ "</tr>";
				$("#detailsTbl tbody").append(result);
			}
			visibleFields();
			getVal();
		},
		error : function() {
			alert("Table Body Data Not Found !");
		}
	});
}


function inVisibleFields() {
	$('#detailsTableDiv').hide();
}

function visibleFields() {
	$('#detailsTableDiv').slideDown();
}

function getVal() {
	var a1 = $('#allowanceLBL1').text();
	var a2 = $('#allowanceLBL2').text();
	var a3 = $('#allowanceLBL3').text();
	var a4 = $('#allowanceLBL4').text();
	var a5 = $('#allowanceLBL5').text();
	var a6 = $('#allowanceLBL6').text();
	var a7 = $('#allowanceLBL7').text();
	var a8 = $('#allowanceLBL8').text();
	var a9 = $('#allowanceLBL9').text();
	var a10 = $('#allowanceLBL10').text();
	var a11 = $('#allowanceLBL11').text();
	var a12 = $('#allowanceLBL12').text();
	var a13 = $('#allowanceLBL13').text();
	var a14 = $('#allowanceLBL14').text();
	var a15 = $('#allowanceLBL15').text();
	var a16 = $('#allowanceLBL16').text();
	var a17 = $('#allowanceLBL17').text();
	var a18 = $('#allowanceLBL18').text();
	var a19 = $('#allowanceLBL19').text();
	var a20 = $('#allowanceLBL20').text();
	var a21 = $('#allowanceLBL21').text();
	var a22 = $('#allowanceLBL22').text();
	var a23 = $('#allowanceLBL23').text();
	var a24 = $('#allowanceLBL24').text();
	var a25 = $('#allowanceLBL25').text();
	var a26 = $('#allowanceLBL26').text();
	var a27 = $('#allowanceLBL27').text();
	var a28 = $('#allowanceLBL28').text();
	var a29 = $('#allowanceLBL29').text();
	var a30 = $('#allowanceLBL30').text();
	var a31 = $('#allowanceLBL31').text();
	
	var b1 = $('#allowanceLBL1').text();
	var b2 = $('#allowanceLBL2').text();
	var b3 = $('#allowanceLBL3').text();
	var b4 = $('#allowanceLBL4').text();
	var b5 = $('#allowanceLBL5').text();
	var b6 = $('#allowanceLBL6').text();
	var b7 = $('#allowanceLBL7').text();
	var b8 = $('#allowanceLBL8').text();
	var b9 = $('#allowanceLBL9').text();
	var b10 = $('#allowanceLBL10').text();
	var b11 = $('#allowanceLBL11').text();
	var b12 = $('#allowanceLBL12').text();
	var b13 = $('#allowanceLBL13').text();
	var b14 = $('#allowanceLBL14').text();
	var b15 = $('#allowanceLBL15').text();
	var b16 = $('#allowanceLBL16').text();
	var b17 = $('#allowanceLBL17').text();
	var b18 = $('#allowanceLBL18').text();
	var b19 = $('#allowanceLBL19').text();
	var b20 = $('#allowanceLBL20').text();
	var b21 = $('#allowanceLBL21').text();
	var b22 = $('#allowanceLBL22').text();
	var b23 = $('#allowanceLBL23').text();
	var b24 = $('#allowanceLBL24').text();
	var b25 = $('#allowanceLBL25').text();
	var b26 = $('#allowanceLBL26').text();
	var b27 = $('#allowanceLBL27').text();
	var b28 = $('#allowanceLBL28').text();
	var b29 = $('#allowanceLBL29').text();
	var b30 = $('#allowanceLBL30').text();
	var b31 = $('#allowanceLBL31').text();
	//th data
	if(a1 === "undefined") {
		$('#allowanceLBL1').remove();
	}
	if(a2 === "undefined") {
		$('#allowanceLBL2').remove();
	}
	if(a3 === "undefined") {
		$('#allowanceLBL3').remove();
	}
	if(a4 === "undefined") {
		$('#allowanceLBL4').remove();
	}
	if(a5 === "undefined") {
		$('#allowanceLBL5').remove();
	}
	if(a6 === "undefined") {
		$('#allowanceLBL6').remove();
	}
	if(a7 === "undefined") {
		$('#allowanceLBL7').remove();
	}
	if(a8 === "undefined") {
		$('#allowanceLBL8').remove();
	}
	if(a9 === "undefined") {
		$('#allowanceLBL9').remove();
	}
	if(a10 === "undefined") {
		$('#allowanceLBL10').remove();
	}
	if(a11 === "undefined") {
		$('#allowanceLBL11').remove();
	}
	if(a12 === "undefined") {
		$('#allowanceLBL12').remove();
	}
	if(a13 === "undefined") {
		$('#allowanceLBL13').remove();
	}
	if(a14 === "undefined") {
		$('#allowanceLBL14').remove();
	}
	if(a15 === "undefined") {
		$('#allowanceLBL15').remove();
	}
	if(a16 === "undefined") {
		$('#allowanceLBL16').remove();
	}
	if(a17 === "undefined") {
		$('#allowanceLBL17').remove();
	}
	if(a18 === "undefined") {
		$('#allowanceLBL18').remove();
	}
	if(a19 === "undefined") {
		$('#allowanceLBL19').remove();
	}
	if(a20 === "undefined") {
		$('#allowanceLBL20').remove();
	}
	if(a21 === "undefined") {
		$('#allowanceLBL21').remove();
	}
	if(a22 === "undefined") {
		$('#allowanceLBL22').remove();
	}
	if(a23 === "undefined") {
		$('#allowanceLBL23').remove();
	}
	if(a24 === "undefined") {
		$('#allowanceLBL24').remove();
	}
	if(a25 === "undefined") {
		$('#allowanceLBL25').remove();
	}
	if(a26 === "undefined") {
		$('#allowanceLBL26').remove();
	}
	if(a27 === "undefined") {
		$('#allowanceLBL27').remove();
	}
	if(a28 === "undefined") {
		$('#allowanceLBL28').remove();
	}
	if(a29 === "undefined") {
		$('#allowanceLBL29').remove();
	}
	if(a30 === "undefined") {
		$('#allowanceLBL30').remove();
	}
	if(a31 === "undefined") {
		$('#allowanceLBL31').remove();
	}
	//td data
	if(b1 === "undefined") {
	    $('#allowance1').remove();
	}
	if(b2 === "undefined") {
	    $('#allowance2').remove();
	}
	if(b3 === "undefined") {
	    $('#allowance3').remove();
	}
	if(b4 === "undefined") {
	    $('#allowance4').remove();
	}
	if(b5 === "undefined") {
	    $('#allowance5').remove();
	}
	if(b6 === "undefined") {
	    $('#allowance6').remove();
	}
	if(b7 === "undefined") {
	    $('#allowance7').remove();
	}
	if(b8 === "undefined") {
	    $('#allowance8').remove();
	}
	if(b9 === "undefined") {
	    $('#allowance9').remove();
	}
	if(b10 === "undefined") {
	    $('#allowance10').remove();
	}
	if(b11 === "undefined") {
	    $('#allowance11').remove();
	}
	if(b12 === "undefined") {
	    $('#allowance12').remove();
	}
	if(b13 === "undefined") {
	    $('#allowance13').remove();
	}
	if(b14 === "undefined") {
	    $('#allowance14').remove();
	}
	if(b15 === "undefined") {
	    $('#allowance15').remove();
	}
	if(b16 === "undefined") {
	    $('#allowance16').remove();
	}
	if(b17 === "undefined") {
	    $('#allowance17').remove();
	}
	if(b18 === "undefined") {
	    $('#allowance18').remove();
	}
	if(b19 === "undefined") {
	    $('#allowance19').remove();
	}
	if(b20 === "undefined") {
	    $('#allowance20').remove();
	}
	if(b21 === "undefined") {
	    $('#allowance21').remove();
	}
	if(b22 === "undefined") {
	    $('#allowance22').remove();
	}
	if(b23 === "undefined") {
	    $('#allowance23').remove();
	}
	if(b24 === "undefined") {
	    $('#allowance24').remove();
	}
	if(b25 === "undefined") {
	    $('#allowance25').remove();
	}
	if(b26 === "undefined") {
	    $('#allowance26').remove();
	}
	if(b27 === "undefined") {
	    $('#allowance27').remove();
	}
	if(b28 === "undefined") {
	    $('#allowance28').remove();
	}
	if(b29 === "undefined") {
	    $('#allowance29').remove();
	}
	if(b30 === "undefined") {
	    $('#allowance30').remove();
	}
	if(b31 === "undefined") {
	    $('#allowance31').remove();
	}
}
