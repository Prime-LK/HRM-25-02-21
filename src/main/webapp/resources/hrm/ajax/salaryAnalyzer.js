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
//				loadAllAllowances();
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
						   + "<th>" + data[i][1] + "</th>"
						   + "<th>" + data[i][2] + "</th>"
						   + "<th>" + data[i][3] + "</th>"
						   + "<th id='col01'><span class='abc'>" + data[i][4] + "</span></th>"
						   + "<th>" + data[i][5] + "</th>" 
						   + "<th>" + data[i][6] + "</th>"
						   + "<th>" + data[i][7] + "</th>"
						   + "<th>" + data[i][8] + "</th>"
						   + "<th>" + data[i][9] + "</th>"
						   + "<th>" + data[i][10] + "</th>" 
						   + "<th>" + data[i][11] + "</th>"
						   + "<th>" + data[i][12] + "</th>"
						   + "<th>" + data[i][13] + "</th>"
						   + "<th>" + data[i][14] + "</th>"
						   + "<th>" + data[i][15] + "</th>" 
						   + "<th>" + data[i][16] + "</th>"
						   + "<th>" + data[i][17] + "</th>"
						   + "<th>" + data[i][18] + "</th>"
						   + "<th>" + data[i][19] + "</th>"
						   + "<th>" + data[i][20] + "</th>" 
						   + "<th>" + data[i][21] + "</th>"
						   + "<th>" + data[i][22] + "</th>"
						   + "<th>" + data[i][23] + "</th>"
						   + "<th>" + data[i][24] + "</th>"
						   + "<th>" + data[i][25] + "</th>" 
						   + "<th>" + data[i][26] + "</th>"
						   + "<th>" + data[i][27] + "</th>"
						   + "<th>" + data[i][28] + "</th>"
						   + "<th>" + data[i][29] + "</th>"
						   + "<th>" + data[i][30] + "</th>"
						   + "<th>" + data[i][31] + "</th>"
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
				    		+ "<td id='theadData'>" + data[i][0] + "</td>" 
				    		+ "<td id='theadData'>" + data[i][1] + "</td>"
				    		+ "<td id='theadData'>" + data[i][2] + "</td>"
						    + "<td id='theadData'>" + data[i][3] + "</td>"
						    + "<td id='theadData'>" + data[i][4] + "</td>"
						    + "<td id='theadData'>" + data[i][5] + "</td>" 
						    + "<td id='theadData'>" + data[i][6] + "</td>"
						    + "<td id='theadData'>" + data[i][7] + "</td>"
						    + "<td id='theadData'>" + data[i][8] + "</td>"
						    + "<td id='theadData'>" + data[i][9] + "</td>"
						    + "<td id='theadData'>" + data[i][10] + "</td>" 
						    + "<td id='theadData'>" + data[i][11] + "</td>"
						    + "<td id='theadData'>" + data[i][12] + "</td>"
						    + "<td id='theadData'>" + data[i][13] + "</td>"
						    + "<td id='theadData'>" + data[i][14] + "</td>"
						    + "<td id='theadData'>" + data[i][15] + "</td>" 
						    + "<td id='theadData'>" + data[i][16] + "</td>"
						    + "<td id='theadData'>" + data[i][17] + "</td>"
						    + "<td id='theadData'>" + data[i][18] + "</td>"
						    + "<td id='theadData'>" + data[i][19] + "</td>"
						    + "<td id='theadData'>" + data[i][20] + "</td>" 
						    + "<td id='theadData'>" + data[i][21] + "</td>"
						    + "<td id='theadData'>" + data[i][22] + "</td>"
						    + "<td id='theadData'>" + data[i][23] + "</td>"
						    + "<td id='theadData'>" + data[i][24] + "</td>"
						    + "<td id='theadData'>" + data[i][25] + "</td>" 
						    + "<td id='theadData'>" + data[i][26] + "</td>"
						    + "<td id='theadData'>" + data[i][27] + "</td>"
						    + "<td id='theadData'>" + data[i][28] + "</td>"
						    + "<td id='theadData'>" + data[i][29] + "</td>"
						    + "<td id='theadData'>" + data[i][30] + "</td>"
						    + "<td id='theadData'>" + data[i][31] + "</td>"
						    + "</tr>";
				$("#detailsTbl tbody").append(result);
			}
		},
		error : function() {
			alert("Table Body Data Not Found !");
		}
	});
}

//function loadAllAllowances() {
//	$.ajax({
//		type : 'GET',
//		url : "allSalaryAnalize",
//		success : function(data) {
//			$("#alloTable tbody").empty();
//			for (var i = 0; i < data.length; i++) {
//				var result = "<tr><td>"
//						+ data[i].saPK.addDedType.deductTypeCode + "</td>"
//						+ "<td>" + data[i].saPK.addDedType.desc + "</td></tr>";
//				$("#alloTable tbody").append(result);
//			}
//		},
//		error : function() {
//			alert("Allowance Data Not Found !");
//		}
//	});
//}
//function getTableData() {
//	$.ajax({
//		type : 'GET',
//		url : "salaryAnalyzeTableHeaderData",
//		success : function(data) {
//			$("#detailsTbl thead").empty();
//			for (var i = 0; i < data.length; i++) {
//				if(data[i][31] === "undefined") {
////					$('#detailsTbl th').hide();
//					console.log('papaya found');
//				}
//				var result = "<tr>" 
//						   + "<th>" + data[i][0] + "</th>" 
//						   + "<th>" + data[i][1] + "</th>"
//						   + "<th>" + data[i][2] + "</th>"
//						   + "<th>" + data[i][3] + "</th>"
//						   + "<th>" + data[i][4] + "</th>"
//						   + "<th>" + data[i][5] + "</th>" 
//						   + "<th>" + data[i][6] + "</th>"
//						   + "<th>" + data[i][7] + "</th>"
//						   + "<th>" + data[i][8] + "</th>"
//						   + "<th>" + data[i][9] + "</th>"
//						   + "<th>" + data[i][10] + "</th>" 
//						   + "<th>" + data[i][11] + "</th>"
//						   + "<th>" + data[i][12] + "</th>"
//						   + "<th>" + data[i][13] + "</th>"
//						   + "<th>" + data[i][14] + "</th>"
//						   + "<th>" + data[i][15] + "</th>" 
//						   + "<th>" + data[i][16] + "</th>"
//						   + "<th>" + data[i][17] + "</th>"
//						   + "<th>" + data[i][18] + "</th>"
//						   + "<th>" + data[i][19] + "</th>"
//						   + "<th>" + data[i][20] + "</th>" 
//						   + "<th>" + data[i][21] + "</th>"
//						   + "<th>" + data[i][22] + "</th>"
//						   + "<th>" + data[i][23] + "</th>"
//						   + "<th>" + data[i][24] + "</th>"
//						   + "<th>" + data[i][25] + "</th>" 
//						   + "<th>" + data[i][26] + "</th>"
//						   + "<th>" + data[i][27] + "</th>"
//						   + "<th>" + data[i][28] + "</th>"
//						   + "<th>" + data[i][29] + "</th>"
//						   + "<th>" + data[i][30] + "</th>"
//						   + "<th>" + data[i][31] + "</th>"
//						   + "</tr>";
//				$("#detailsTbl thead").append(result);
//			}
//		},
//		error : function() {
//			alert("Table Header Data Not Found !");
//		}
//	});
//	$.ajax({
//		type : 'GET',
//		url : "salaryAnalyzeTableBodyData",
//		success : function(data) {
//			$("#detailsTbl tbody").empty();
//			for (var i = 0; i < data.length; i++) {
//				var result = "<tr>" 
//				    		+ "<td id='theadData'>" + data[i][0] + "</td>" 
//				    		+ "<td id='theadData'>" + data[i][1] + "</td>"
//				    		+ "<td id='theadData'>" + data[i][2] + "</td>"
//						    + "<td id='theadData'>" + data[i][3] + "</td>"
//						    + "<td id='theadData'>" + data[i][4] + "</td>"
//						    + "<td id='theadData'>" + data[i][5] + "</td>" 
//						    + "<td id='theadData'>" + data[i][6] + "</td>"
//						    + "<td id='theadData'>" + data[i][7] + "</td>"
//						    + "<td id='theadData'>" + data[i][8] + "</td>"
//						    + "<td id='theadData'>" + data[i][9] + "</td>"
//						    + "<td id='theadData'>" + data[i][10] + "</td>" 
//						    + "<td id='theadData'>" + data[i][11] + "</td>"
//						    + "<td id='theadData'>" + data[i][12] + "</td>"
//						    + "<td id='theadData'>" + data[i][13] + "</td>"
//						    + "<td id='theadData'>" + data[i][14] + "</td>"
//						    + "<td id='theadData'>" + data[i][15] + "</td>" 
//						    + "<td id='theadData'>" + data[i][16] + "</td>"
//						    + "<td id='theadData'>" + data[i][17] + "</td>"
//						    + "<td id='theadData'>" + data[i][18] + "</td>"
//						    + "<td id='theadData'>" + data[i][19] + "</td>"
//						    + "<td id='theadData'>" + data[i][20] + "</td>" 
//						    + "<td id='theadData'>" + data[i][21] + "</td>"
//						    + "<td id='theadData'>" + data[i][22] + "</td>"
//						    + "<td id='theadData'>" + data[i][23] + "</td>"
//						    + "<td id='theadData'>" + data[i][24] + "</td>"
//						    + "<td id='theadData'>" + data[i][25] + "</td>" 
//						    + "<td id='theadData'>" + data[i][26] + "</td>"
//						    + "<td id='theadData'>" + data[i][27] + "</td>"
//						    + "<td id='theadData'>" + data[i][28] + "</td>"
//						    + "<td id='theadData'>" + data[i][29] + "</td>"
//						    + "<td id='theadData'>" + data[i][30] + "</td>"
//						    + "<td id='theadData'>" + data[i][31] + "</td>"
//						    + "</tr>";
//				$("#detailsTbl tbody").append(result);
//				
//				$('#detailsTbl tr').each(function() {
//				    var customerId = $(this).find("#theadData").html();    
//				    console.log(customerId);
//				});	
//			}
//		},
//		error : function() {
//			alert("Table Body Data Not Found !");
//		}
//	});
//}
//
//
//
// //function saveSaData() {
// // var a = document.getElementById("year").value;
// // var b = document.getElementById("month").value;
// // var c = document.getElementById("depID").value;
// // var d = document.getElementById("comID").value;
// // var e = document.getElementById("adjType").value;
// // $.ajax({
// // type : 'POST',
// // url : "saveSalaryAnalyzeData",
// // data: { "saPK.year":a, "saPK.month":b, "saPK.depatment.depID":c,
// "company.comID":d, "saPK.addDedType.deductTypeCode":e },
// // success : function(data) {
// // console.log('data saved successfully');
// // },
// // error : function() {
// // alert("Couldn't data saved !");
// // }
// // });
// //}
//
// function getTable02Data() {
// $.ajax({
// type : 'GET',
// url : "saTblData02Header",
// success : function(data) {
// //set table header (allowances name) if exists
// $("#detailsTbl thead").empty();
// for (var i = 0; i < data.length; i++) {
// var result = "<tr>"
// + "<th id='depHeader'>" + data[i][0] + "</th>"
// + "<th id='budHeader'>" + data[i][1] + "</th>"
// + "<th id='attHeader'>" + data[i][2] + "</th>"
// + "<th id='riskHeader'>" + data[i][3] + "</th>"
// + "<th id='perfoHeader'>" + data[i][4] + "</th>"
// + "<th id='nightHeader'>" + data[i][5] + "</th>"
// + "<th id='targetHeader'>" + data[i][6] + "</th>"
// + "<th id='traineeHeader'>" + data[i][7] + "</th>"
// + "<th id='otherHeader'>" + data[i][8] + "</th>"
// + "<th id='riggerHeader'>" + data[i][9] + "</th>"
// + "<th id='salesHeader'>" + data[i][10] + "</th>"
// + "<th id='transHeader'>" + data[i][11] + "</th>"
// + "<th id='siteHeader'>" + data[i][12] + "</th>"
// + "<th id='nopayHeader'>" + data[i][13] + "</th>"
// + "<th id='festivalHeader'>" + data[i][14] + "</th>"
// + "<th id='insuHeader'>" + data[i][15] + "</th>"
// + "<th id='mobileHeader'>" + data[i][16] + "</th>"
// + "<th id='saAdHeader'>" + data[i][17] + "</th>"
// + "<th id='epf8Header'>" + data[i][18] + "</th>"
// + "<th id='welfareHeader'>" + data[i][19] + "</th>"
// + "<th id='lapHeader'>" + data[i][20] + "</th>"
// + "<th id='bikeHeader'>" + data[i][21] + "</th>"
// + "<th id='pmHeader'>" + data[i][22] + "</th>"
// + "<th id='epf12Header'>" + data[i][23] + "</th>"
// + "<th id='epf3Header'>" + data[i][24] + "</th></tr>";
// $("#detailsTbl thead").append(result);
//		
// // if not found allowances name hide table column
// //dep
// if(data[i][0] == null) {
// $('#detailsTbl th#depHeader').hide();
// } else {
// //bud
// if(data[i][1] == null) {
// $('#detailsTbl th#budHeader').hide();
// }
// //att
// if(data[i][2] == null) {
// $('#detailsTbl th#attHeader').hide();
// }
// //risk
// if(data[i][3] == null) {
// $('#detailsTbl th#riskHeader').hide();
// }
// //perfo
// if(data[i][4] == null) {
// $('#detailsTbl th#perfoHeader').hide();
// }
// //night
// if(data[i][5] == null) {
// $('#detailsTbl th#nightHeader').hide();
// }
// //target
// if(data[i][6] == null) {
// $('#detailsTbl th#targetHeader').hide();
// }
// //trainee
// if(data[i][7] == null) {
// $('#detailsTbl th#traineeHeader').hide();
// }
// //other
// if(data[i][8] == null) {
// $('#detailsTbl th#otherHeader').hide();
// }
// //rigger
// if(data[i][9] == null) {
// $('#detailsTbl th#riggerHeader').hide();
// }
// //sales
// if(data[i][10] == null) {
// $('#detailsTbl th#salesHeader').hide();
// }
// //trans
// if(data[i][11] == null) {
// $('#detailsTbl th#transHeader').hide();
// }
// //site
// if(data[i][12] == null) {
// $('#detailsTbl th#siteHeader').hide();
// }
// //nopay
// if(data[i][13] == null) {
// $('#detailsTbl th#nopayHeader').hide();
// }
// //festival
// if(data[i][14] == null) {
// $('#detailsTbl th#festivalHeader').hide();
// }
// //insurance
// if(data[i][15] == null) {
// $('#detailsTbl th#insuHeader').hide();
// }
// //mobile
// if(data[i][16] == null) {
// $('#detailsTbl th#mobileHeader').hide();
// }
// //salary ad
// if(data[i][17] == null) {
// $('#detailsTbl th#saAdHeader').hide();
// }
// //epf8
// if(data[i][18] == null) {
// $('#detailsTbl th#epf8Header').hide();
// }
// //welfare
// if(data[i][19] == null) {
// $('#detailsTbl th#welfareHeader').hide();
// }
// //lap
// if(data[i][20] == null) {
// $('#detailsTbl th#lapHeader').hide();
// }
// //bike
// if(data[i][21] == null) {
// $('#detailsTbl th#bikeHeader').hide();
// }
// //pm
// if(data[i][22] == null) {
// $('#detailsTbl th#pmHeader').hide();
// }
// //epf12
// if(data[i][23] == null) {
// $('#detailsTbl th#epf12Header').hide();
// }
// //epf3
// if(data[i][24] == null) {
// $('#detailsTbl th#epf3Header').hide();
// }
// }
// }
// },
// error : function() {
// alert("Details Table 2 Header Error Found !");
// }
// });
//
// $.ajax({
// type : 'GET',
// url : "saTblData02",
// success : function(data) {
// //set table data (allowances amount) if exists
// $("#detailsTbl tbody").empty();
// for (var i = 0; i < data.length; i++) {
// var result = "<tr>"
// + "<td id='dep'>" + data[i][0] + "</td>"
// + "<td id='bud'>" + data[i][1] + "</td>"
// + "<td id='att'>" + data[i][2] + "</td>"
// + "<td id='risk'>" + data[i][3] + "</td>"
// + "<td id='perfo'>" + data[i][4] + "</td>"
// + "<td id='night'>" + data[i][5] + "</td>"
// + "<td id='target'>" + data[i][6] + "</td>"
// + "<td id='trainee'>" + data[i][7] + "</td>"
// + "<td id='other'>" + data[i][8] + "</td>"
// + "<td id='rigger'>" + data[i][9] + "</td>"
// + "<td id='sales'>" + data[i][10] + "</td>"
// + "<td id='trans'>" + data[i][11] + "</td>"
// + "<td id='site'>" + data[i][12] + "</td>"
// + "<td id='nopay'>" + data[i][13] + "</td>"
// + "<td id='festival'>" + data[i][14] + "</td>"
// + "<td id='insu'>" + data[i][15] + "</td>"
// + "<td id='mobile'>" + data[i][16] + "</td>"
// + "<td id='saAd'>" + data[i][17] + "</td>"
// + "<td id='epf8'>" + data[i][18] + "</td>"
// + "<td id='welfare'>" + data[i][19] + "</td>"
// + "<td id='lap'>" + data[i][20] + "</td>"
// + "<td id='bike'>" + data[i][21] + "</td>"
// + "<td id='pm'>" + data[i][22] + "</td>"
// + "<td id='epf12'>" + data[i][23] + "</td>"
// + "<td id='epf3'>" + data[i][24] + "</td></tr>";
// $("#detailsTbl tbody").append(result);
//				
// // if not found allowances amount hide table column
// //dep
// if(data[i][0] == null) {
// $('#detailsTbl td#dep').hide();
// } else {
// //bud
// if(data[i][1] == null) {
// $('#detailsTbl td#bud').hide();
// }
// //att
// if(data[i][2] == null) {
// $('#detailsTbl td#att').hide();
// }
// //risk
// if(data[i][3] == null) {
// $('#detailsTbl td#risk').hide();
// }
// //perfo
// if(data[i][4] == null) {
// $('#detailsTbl td#perfo').hide();
// }
// //night
// if(data[i][5] == null) {
// $('#detailsTbl td#night').hide();
// }
// //target
// if(data[i][6] == null) {
// $('#detailsTbl td#target').hide();
// }
// //trainee
// if(data[i][7] == null) {
// $('#detailsTbl td#trainee').hide();
// }
// //other
// if(data[i][8] == null) {
// $('#detailsTbl td#other').hide();
// }
// //rigger
// if(data[i][9] == null) {
// $('#detailsTbl td#rigger').hide();
// }
// //sales
// if(data[i][10] == null) {
// $('#detailsTbl td#sales').hide();
// }
// //trans
// if(data[i][11] == null) {
// $('#detailsTbl td#trans').hide();
// }
// //site
// if(data[i][12] == null) {
// $('#detailsTbl td#site').hide();
// }
// //nopay
// if(data[i][13] == null) {
// $('#detailsTbl td#nopay').hide();
// }
// //festival
// if(data[i][14] == null) {
// $('#detailsTbl td#festival').hide();
// }
// //insurance
// if(data[i][15] == null) {
// $('#detailsTbl td#insu').hide();
// }
// //mobile
// if(data[i][16] == null) {
// $('#detailsTbl td#mobile').hide();
// }
// //salary ad
// if(data[i][17] == null) {
// $('#detailsTbl td#saAd').hide();
// }
// //epf8
// if(data[i][18] == null) {
// $('#detailsTbl td#epf8').hide();
// }
// //welfare
// if(data[i][19] == null) {
// $('#detailsTbl td#welfare').hide();
// }
// //lap
// if(data[i][20] == null) {
// $('#detailsTbl td#lap').hide();
// }
// //bike
// if(data[i][21] == null) {
// $('#detailsTbl td#bike').hide();
// }
// //pm
// if(data[i][22] == null) {
// $('#detailsTbl td#pm').hide();
// }
// //epf12
// if(data[i][23] == null) {
// $('#detailsTbl td#epf12').hide();
// }
// //epf3
// if(data[i][24] == null) {
// $('#detailsTbl td#epf3').hide();
// }
// }
// }
// },
// error : function() {
// alert("Details Table 2 Data Error Found !");
// }
// });
//
// }
