$(document).ready(function() {
	
	//Auto complete off
	$("input.autocomplete-off").attr("autocomplete", "off");
	
    $("#map").googleMap({
        zoom: 70, // Initial zoom level (optional)
        coords: [-12.0520571,-76.96617379999999], // Map center (optional)
        type: "ROADMAP" // Map type (optional)
      });
	
	//Refresh captcha image
	$(".change-captcha").click(function(){
		var rnd = new Date().getTime();
		var src = $("img.captcha-img").attr("src");
		
		if (src.indexOf("?")!=-1) {
			var ind = src.indexOf("?");
			src = src.substr(0, ind);
		}
		
		src += "?"+rnd;
		$("img.captcha-img").attr("src", src);
		$("#verify").val("");
	});	
	
	$("#evaluar").click(function(){
		var direccion = $("#direccion").val();
		  $.ajax({
			   type:"get", 
			   url:"/evaluarFactibilidad", 
			   async : false,
			   data: {
				   direccion: function() {
			              return $("#direccion").val();
			            }
			          },
			          
						success : function(data){
							 //alert(data.listaUbicacion[0].distrito);
							 if(data.codigoResp == "1"){
								 alert("No es factible");
							 }else{
								 for(let i = 0; i < data.listaUbicacion.length; i++){
									    $("#map").addMarker({
									        coords: [data.listaUbicacion[i].y, data.listaUbicacion[i].x], // GPS coords
									        title: 'Marker n°'+i, // Title
									        text:  '<b>Lorem ipsum</b> dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.' // HTML content
									      });
								 }
								 $("#registrar").prop('disabled', false);
							 }
							}		          
		  			});
	});
	
	// Boton registrar
	$("#registrar").click(function(){
		  $.ajax({
			   type:"get", 
			   url:"/sefisf", 
			   async : false,
			   data: {
				   direccion: function() {
			              return $("#direccion").val();
			            }
			          }	,
			          
			     success : function(data){
			    	 $("#formul").html(data);	
						}
		  			});
		
	});
	
	//Closing divs - used on Notification boxes
	notificationReady = function(cls) {
		//Hide button event
		if (!$(".canhide").find("close").length) {
			$(".canhide").append('<a class="close" href="#">Close</a>');
			
			$(".notification .close").click(function(e) {
				e.preventDefault();
				$(this).parent().fadeOut(300);
			});
		}
		
		//Notification type
		$(".notification").addClass(cls);
	}
	
	//Submit form
	$("#frm_contact").submit(function(){
		var action = $(this).attr("action");
		$("#submit").attr("disabled", "disabled");
		
		//Add preloader
		if (!$(".form-submit").find("img.preloader").length) {
			$("#submit").after('<img src="images/preloader.gif" class="preloader" />');
		}
		
		//Post form
		$(".notification").fadeOut(300, function() {
			$.post(action, {
				name: 		$("#name").val(),
				email: 		$("#email").val(),
				phone: 		$("#phone").val(),
				subject: 	$("#subject").val(),
				message: 	$("#message").val(),
				verify: 	$("#verify").val()
			},
				function(data) {
					//Show notification
					$(".notification .inner").html(data);
					$(".notification").fadeIn(300);
					
					//Remove preloader
					$(".form-submit img.preloader").fadeOut("fast", function() {
						$(this).remove();
					});
					
					//Enable submit button
					$("#submit").removeAttr("disabled");
					
					//Hide form if success
					if(data.match("success")!=null) {
						$("#frm_contact").fadeOut("slow");
					}
				}
			);
		});
		
		return false;
	});
	
});