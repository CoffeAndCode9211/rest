	var fitbitAccessCode = "";	

// Configured for UnblockUI
$.blockUI.defaults.fadeOut = 0; 
$.blockUI.defaults.fadeIn = 0; 
$.blockUI.defaults.message="<h4>Loading... </h4>"
var noOfCalls = 1;	

	$(document).ready(function(e){


	 	$( document ).ajaxSend(function() {
			//$.blockUI({ boxed: true});
		});

		$(document).ajaxComplete(function(){
			console.log(noOfCalls);
	      if(noOfCalls > 0){
				noOfCalls = noOfCalls - 1;
			}
			if(noOfCalls == 0) {
				$.unblockUI();
			}
		});



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

		$("#linkMyTimer").click(function(){
			loadScreen("MT");
		});
		
		$("#linkProperty").click(function(){
			loadScreen("PR");
		});
		

		$("#linkDrug").click(function(){
			loadScreen("DR");
		});
		
		$("#linkSentimentAnalysis").click(function(){
			loadScreen("SA");
		});

	});
	


	function loadScreen(screenName){
		noOfCalls = 1;	
		if(screenName != "L"){
			$(".srcContainer").empty();
		}
		if(screenName == "D"){
			$(".srcContainer").load("dashboard.html");
		}else if(screenName == "E"){
			$(".srcContainer").load("employee.html");
		}else if(screenName == "R"){
			$(".srcContainer").load("report.html");
		}else if(screenName == "F"){
			$(".srcContainer").load("../rest/empservlet");
		}else if(screenName == "FB"){
			$(".srcContainer").load("fitbitlogin.html");
		}else if(screenName == "C"){
			$(".srcContainer").load("userchat.html");
		}else if(screenName == "M"){
			$(".srcContainer").load("email.html");
		}else if(screenName == "MT"){
			$(".srcContainer").load("mytimer.html");
		}else if(screenName == "DR"){
			$(".srcContainer").load("drug.html");
		}else if(screenName == "L"){
			confirmLogout("Are you sure you want to logout? ")
		}else if(screenName == "PR"){
			$(".srcContainer").load("property.html");
		}else if(screenName == "SA"){
			$(".srcContainer").load("sentimentAnalysis.html");
		}
		

	}

	
	function confirmLogout(msg) {
		bootbox.confirm({
			message: msg,
			buttons: {
				confirm: {
					label: "OK",
					className: "btn btn-primary margin-right-10"
				},
				cancel: {
					label: "Cancel",
					className: "btn-default pull-right"
				}
			},
			callback: function(result) {
				if(result){
					$.blockUI();
					logoutMain();
				}
			}
		});
	}

	function logoutMain(){
		
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


	toastr.options = {
	  "closeButton": true,
	  "debug": false,
	  "newestOnTop": false,
	  "progressBar": false,
	  "positionClass": "toast-bottom-right",
	  "preventDuplicates": false,
	  "onclick": null,
	  "showDuration": "300",
	  "hideDuration": "1000",
	  "timeOut": "4000",
	  "extendedTimeOut": "1000",
	  "showEasing": "swing",
	  "hideEasing": "linear",
	  "showMethod": "fadeIn",
	  "hideMethod": "fadeOut"
	}

	loadScreen("D");