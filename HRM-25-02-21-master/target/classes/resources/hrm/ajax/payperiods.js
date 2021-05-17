$("#PayPeriodform").submit(function(e) {
    e.preventDefault();
});

function validateForm() {
  
  var startDate = document.getElementById("startDate").value;
  var endDate = document.getElementById("endDate").value;
  var payDate = document.getElementById("payDate").value;
  var status = document.getElementById("status").value;

  
  
  if(startDate=="")
  {
      document.getElementById("div1").innerHTML="Enter a value";
      document.getElementById("div1").style.color="Red";
     
  }
  else
  {
      document.getElementById("div1").innerHTML="";
  } 
  
  if(endDate=="")
  {
      document.getElementById("div2").innerHTML="Enter a value";
      document.getElementById("div2").style.color="Red";
      
  }
  else
  {
      document.getElementById("div2").innerHTML="";
  } 
  
  if(payDate=="")
  {
      document.getElementById("div3").innerHTML="Enter a value";
      document.getElementById("div3").style.color="Red";
      
  }
  else
  {
      document.getElementById("div3").innerHTML="";
  }
  
  if(status=="")
  {
      document.getElementById("div4").innerHTML="Enter a value";
      document.getElementById("div4").style.color="Red";
      
  }
  else
  {
      document.getElementById("div4").innerHTML="";
  } 
}