
	function loadScreen(screenName){
		console.log(screenName);
		if(screenName == "E"){
			$(".srcContainer").load("employee.html");
		}else if(screenName == "R"){
			$(".srcContainer").load("report.html");
		}else if(screenName == "F"){
			$(".srcContainer").load("rest/empservlet");
		}else if(screenName == "FB"){
			setAction();
			$(".srcContainer").load("details.html");
		}else if(screenName == "C"){
			$(".srcContainer").load("userchat.html");
		}else if(screenName == "M"){
			$(".srcContainer").load("email.html");
		}else if(screenName == "L"){
			$.ajax({
				type : "PUT",
				url : 'rest/employee/logout',
				success : function(data) {

					var baseUrl = document.location.origin;
					baseUrl += "/webservice";
					/*
					console.log("Output;");  
					console.log(location.hostname);
					console.log(document.domain);
					console.log(window.location.hostname)
					console.log("document.URL : "+document.URL);
					console.log("document.location.href : "+document.location.href);
					console.log("document.location.origin : "+document.location.origin);
					console.log("document.location.hostname : "+document.location.hostname);
					console.log("document.location.host : "+document.location.host);
					console.log("document.location.pathname : "+document.location.pathname);
					*/
					window.location.replace(baseUrl);
		      	}, error : function(error){ alert(error.Error); }
			});
		}

	}

	function setAction(){	
		$.ajax({
			type: "GET",
			url: "rest/send",
			success: function(jsonObject)
			{		
				window.open(jsonObject,"Fitbit", "width=500,height=600,left=400,top=200");
			}												
			,
			error: function (error)
			{
				alert("Error");
			} 
		});				
	}	

	
	toastr.options = {
	  "closeButton": true,
	  "debug": false,
	  "newestOnTop": false,
	  "progressBar": false,
	  "positionClass": "toast-top-right",
	  "preventDuplicates": false,
	  "onclick": null,
	  "showDuration": "300",
	  "hideDuration": "1000",
	  "timeOut": "2000",
	  "extendedTimeOut": "1000",
	  "showEasing": "swing",
	  "hideEasing": "linear",
	  "showMethod": "fadeIn",
	  "hideMethod": "fadeOut"
	}

	/*

	$( document ).ajaxSend(function( event, jqXHR) {
      $.blockUI({ message: "Please Wait..." }); 
	});
	$(document).ajaxComplete(function(){
		if($.active==2) {
			$.unblockUI();
		}
	});

*/

