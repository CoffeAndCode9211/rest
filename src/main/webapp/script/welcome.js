	
	$(document).ready(function(e){

		$("#linkDashboard").click(function(){
			loadScreen("D");
		});

		$("#linkEmployee").click(function(){
			loadScreen("E");
		});

		$("#linkFreeMarker").click(function(){
			loadScreen("F");
		});

		$("#linkReport").click(function(){
			loadScreen("R");
		});

		$("#linkFitbit").click(function(){
			loadScreen("FB");
		});

		$("#linkSendMail").click(function(){
			loadScreen("M");
		});

		$("#linkLogout").click(function(){
			loadScreen("L");
		});

	});
	


	function loadScreen(screenName){
		$(".srcContainer").empty();
		if(screenName == "E"){
			$(".srcContainer").load("employee.html");
		}else if(screenName == "R"){
			$(".srcContainer").load("report.html");
		}else if(screenName == "F"){
			$(".srcContainer").load("../rest/empservlet");
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
				url : '../rest/employee/logout',
				success : function(data) {

					var baseUrl = document.location.origin;
					baseUrl += "/webservice";
					window.location.replace(baseUrl);
		      	}, error : function(error){ alert(error.Error); }
			});
		}

	}

	function setAction(){	
		$.ajax({
			type: "GET",
			url: "../rest/send",
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

