function getDetais() {
	var a = document.getElementById("itemcode").value;
	$.ajax({
		type : "GET",
		url : "getDesc",
		data : {
			"itemcode" : a
		},
		success : function(data) {
			document.getElementById("serialnumber").value = data.description;
		},
		error : function() {
			alert("Item ID Error");
		}
	});
}