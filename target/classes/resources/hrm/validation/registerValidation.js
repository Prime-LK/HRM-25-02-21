$(function () {
	  $("#formRegister").submit(function(event) {
	    var id = $("#empID").val();
	    var conNo01 = $("#conNo01").val();
	    var conNo02 = $("#conNo02").val();
	    var email = $("#email").val();
	    var fname = $("#firstName").val();
	    var lname = $("#lastName").val();
	    
	     validationempid(id, event);
//		 validationempidNumberOnly(id, event);
	     validateConNo01(conNo01, event);
	     validateConNo02(conNo02, event);
	     validateEmail(email, event);
	     validateFname(fname, event);
	     validateLname(lname, event)
	  });

	  function isValidEmpID(id) {
	    return id.length > 1 && id.length < 10;
	  }
	  
	  function isValiedNumbers(id) {
		  return id.which != 8 && id.which != 0 && (id.which < 48 || id.which > 57);
	  }

	  function validationempid(id, event) {
	    if(!isValidEmpID(id)) {
	      $("#msg").text("Required the Employee ID");
	      event.preventDefault();
	    } else {
	      $("#msg").text("");
	    }
	  }
	  
	  function validationempidNumberOnly(id, event) {
		    if(!isValiedNumbers(id)) {
		      $("#msg").text("Add numeric values only");
		      event.preventDefault();
		    } else {
		      $("#msg").text("");
		    }
		  }
	  
	  function isValidConNo01(conNo01) {
		  return conNo01.length >= 1 && conNo01.length <= 10;
	  }
	  
	  function validateConNo01(conNo01, event) {
		    if(!isValidConNo01(conNo01)) {
		      $("#msgConNo01").text("Required the contact number");
		      event.preventDefault();
		    } else {
		      $("#msgConNo01").text("");
		    }
		  }
	  
	  function isValidConNo02(conNo02) {
		  return conNo02.length >= 1 && conNo02.length <= 10;
	  }
	  
	  function validateConNo02(conNo02, event) {
		    if(!isValidConNo02(conNo02)) {
		      $("#msgConNo02").text("Required the contact number");
		      event.preventDefault();
		    } else {
		      $("#msgConNo02").text("");
		    }
		  }
	  
	  function isValidEmail(email) {
		  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		  return regex.test(email);
	  }
	  
	  function validateEmail(email, event) {
		    if(!isValidEmail(email)) {
		      $("#msgEmail").text("Required the valid email");
		      event.preventDefault();
		    } else {
		      $("#msgEmail").text("");
		    }
		  }
	  
	  function isValidFname(fname) {
		 return fname.length >= 1;
	  }
	  
	  function validateFname(fname, event) {
		    if(!isValidFname(fname)) {
		      $("#msgFname").text("Required the first name");
		      event.preventDefault();
		    } else {
		      $("#msgFname").text("");
		    }
		  }
	  
	  function isValidLname(lname) {
			 return lname.length >= 1;
		  }
		  
		  function validateLname(lname, event) {
			    if(!isValidLname(lname)) {
			      $("#msgLname").text("Required the last name");
			    } else {
			      $("#msgLname").text("");
			    }
			  }
	  
	});



