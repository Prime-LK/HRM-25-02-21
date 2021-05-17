$(function () {
	  $("#formEmpSalaryDetails").submit(function(event) {
	    var id = $("#addDedctTypeID").val();
	    var sepSel = $('#sepSelect').val();
	    var addUser = $("#addedUser").val();
	    var inaUser = $("#inactiveUser").val();
	    var cb1 = $("#exampleCheck3").val();
	    var cb2 = $("#exampleCheck2").val();
	    
	    
	    validateAdjusmentID(id, event);
//	    validateSeperateID(sepSel, event);
	    validateAddUser(addUser, event);
	    validateInaUser(inaUser, event);

	  });
	  
	  //Adjusment SELECT validation
	  function isValidAdjusmentID(id) {
	    return id.length > 1 && id.length < 10;
	  }
	  function validateAdjusmentID(id, event) {
		    if(!isValidAdjusmentID(id)) {
					var placementFrom = 'top';
					var placementAlign = 'right';
					var state = 'danger';
					var style = 'withicon';
					var content = {};

					content.message = 'Required the Adjusment Type';
					content.title = 'Required';
					if (style == "withicon") {
						content.icon = 'fa fa-bell';
					} else {
						content.icon = 'none';
					}
					content.url = 'getEmployeeSalaryDetailsPage';
					content.target = '_self';

					$.notify(content,{
						type: state,
						placement: {
							from: placementFrom,
							align: placementAlign
						},
						time: 1000,
						delay: 0,
					});
		      event.preventDefault();
		    } else {
//		      $("#msgAdj").text("");
		    }
		  }
	  
	  //seperate SELECT validation
	  function isValidSeperateID(sepSel) {
	    return sepSel.length > 1 && sepSel.length < 10;
	  }
	  function validateSeperateID(sepSel, event) {
		    if(!isValidSeperateID(sepSel)) {
					var placementFrom = 'top';
					var placementAlign = 'right';
					var state = 'danger';
					var style = 'withicon';
					var content = {};

					content.message = 'Required the Employees';
					content.title = 'Required';
					if (style == "withicon") {
						content.icon = 'fa fa-bell';
					} else {
						content.icon = 'none';
					}
					content.url = 'getEmployeeSalaryDetailsPage';
					content.target = '_self';

					$.notify(content,{
						type: state,
						placement: {
							from: placementFrom,
							align: placementAlign
						},
						time: 1000,
						delay: 0,
					});
		      event.preventDefault();
		    } else {
//		      $("#msgAdj").text("");
		    }
		  }
	  
	  //Added user Date Validaion
	  function isValidAddUser(addUser) {
		  return addUser.length > 1 && addUser.length < 10;
	  }
	  function validateAddUser(addUser, event) {
		    if(!isValidAddUser(addUser)) {
					var placementFrom = 'top';
					var placementAlign = 'right';
					var state = 'danger';
					var style = 'withicon';
					var content = {};

					content.message = 'Required the Added User';
					content.title = 'Required';
					if (style == "withicon") {
						content.icon = 'fa fa-bell';
					} else {
						content.icon = 'none';
					}
					content.url = 'index.html';
					content.target = '_blank';

					$.notify(content,{
						type: state,
						placement: {
							from: placementFrom,
							align: placementAlign
						},
						time: 1000,
						delay: 0,
					});
		      event.preventDefault();
		    } else {
//		      $("#msgAdj").text("");
		    }
		  }
	  
	  //Inactive User validation
	  function isValidInactiveUser(inaUser) {
	    return inaUser.length > 1 && inaUser.length < 10;
	  }
	  function validateInaUser(inaUser, event) {
		    if(!isValidInactiveUser(inaUser)) {
					var placementFrom = 'top';
					var placementAlign = 'right';
					var state = 'danger';
					var style = 'withicon';
					var content = {};

					content.message = 'Required the Inactive User';
					content.title = 'Required';
					if (style == "withicon") {
						content.icon = 'fa fa-bell';
					} else {
						content.icon = 'none';
					}
					content.url = 'getEmployeeSalaryDetailsPage';
					content.target = '_blank';

					$.notify(content,{
						type: state,
						placement: {
							from: placementFrom,
							align: placementAlign
						},
						time: 1000,
						delay: 0,
					});
		      event.preventDefault();
		    } else {
//		      $("#msgAdj").text("");
		    }
		  }
	  
	  //Check box validation
	  function isCheckBoxValid(cb1, cb2) {
		  return cb1.checked = false;
	  }
	  function validationCheckBox(cb1, cb2, event) {
	    if(!isCheckBoxValid(cb1, cb2)) {
	    	var placementFrom = 'top';
			var placementAlign = 'right';
			var state = 'danger';
			var style = 'withicon';
			var content = {};

			content.message = 'Set Allowance Active or Inactive';
			content.title = 'Required';
			if (style == "withicon") {
				content.icon = 'fa fa-bell';
			} else {
				content.icon = 'none';
			}
			content.url = 'getEmployeeSalaryDetailsPage';
			content.target = '_blank';

			$.notify(content,{
				type: state,
				placement: {
					from: placementFrom,
					align: placementAlign
				},
				time: 1000,
				delay: 0,
			});
      event.preventDefault();
	    } else {
//	      $("#msg").text("");
	    }
	  }
//	  
//	  function validationempidNumberOnly(id, event) {
//		    if(!isValiedNumbers(id)) {
//		      $("#msg").text("Add numeric values only");
//		      event.preventDefault();
//		    } else {
//		      $("#msg").text("");
//		    }
//		  }
//	  
//	  function isValidConNo01(conNo01) {
//		  return conNo01.length >= 1 && conNo01.length <= 10;
//	  }
	  
//	  function isValidConNo02(conNo02) {
//		  return conNo02.length >= 1 && conNo02.length <= 10;
//	  }
//	  
//	  function validateConNo02(conNo02, event) {
//		    if(!isValidConNo02(conNo02)) {
//		      $("#msgConNo02").text("Required the contact number");
//		      event.preventDefault();
//		    } else {
//		      $("#msgConNo02").text("");
//		    }
//		  }
	  
//	  function isValidEmail(email) {
//		  var regex = ^(?:(?:31(\/|-|\.)(?:0?[13578]|1[02]))\1|(?:(?:29|30)(\/|-|\.)(?:0?[13-9]|1[0-2])\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:29(\/|-|\.)0?2\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\d|2[0-8])(\/|-|\.)(?:(?:0?[1-9])|(?:1[0-2]))\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$;
//		  return regex.test(email);
//	  }
	  
//	  function validateEmail(email, event) {
//		    if(!isValidEmail(email)) {
//		      $("#msgEmail").text("Required the valid email");
//		      event.preventDefault();
//		    } else {
//		      $("#msgEmail").text("");
//		    }
//		  }
//	  
//	  function isValidFname(fname) {
//		 return fname.length >= 1;
//	  }
//	  
//	  function validateFname(fname, event) {
//		    if(!isValidFname(fname)) {
//		      $("#msgFname").text("Required the first name");
//		      event.preventDefault();
//		    } else {
//		      $("#msgFname").text("");
//		    }
//		  }
//	  
//	  function isValidLname(lname) {
//			 return lname.length >= 1;
//		  }
//		  
//		  function validateLname(lname, event) {
//			    if(!isValidLname(lname)) {
//			      $("#msgLname").text("Required the last name");
//			    } else {
//			      $("#msgLname").text("");
//			    }
//			  }
	  
	});



