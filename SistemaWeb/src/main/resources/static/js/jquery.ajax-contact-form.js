$(document).ready(function() {
	
	//Auto complete off
	$("input.autocomplete-off").attr("autocomplete", "off");
	
	$("#frm_contact").validate({
        rules: {
     	   direccion: {
                        required: true
                }
        },
        messages: {
     	   direccion: {
                        required: "La dirección es requerida"
        }
      }   
    });
	
    $("#map").googleMap({
        zoom: 70, // Initial zoom level (optional)
        coords: [-12.0520571,-76.96617379999999], // Map center (optional)
        type: "ROADMAP" // Map type (optional)
      });
	
	
	$("#evaluar").click(function(){
		//frm_contact
        if($("#frm_contact").valid()){   // test for validity
          
        } else {
        	
        }
		
		
		var direccionVal = $("#direccion").val();
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
								 alert("La solicitud es factible");
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
//	$("#registrar").click(function(){
//		  $.ajax({
//			   type:"get", 
//			   url:"/sefisf", 
//			   async : false,
//			   data: {
//				   direccion: function() {
//			              return $("#direccion").val();
//			            }
//			          }	,facti
//			          
//			     success : function(data){
//			    	 $("#formul").html(data);	
//						}
//		  			});
//		
//	});
	
	
//	$("#btnRegistrar").click(function(){
//		alert("HOLA");
//		  $.ajax({
//			   type:"get", 
//			   url:"/registrar", 
//			   async : false,
//		   data: {
//				   
//				   idServicio: function() {
//			              return $("#servicios").val();
//			            }
//			          ,
//			        nombre: function() {
//			              return $("#name").val();
//			            }
//			         ,   
//		          
//			          apellido: function() {
//			              return $("#apellido").val();
//			            }
//			        ,     
//			          idTipDoc: function() {
//			              return $("#tipDoc").val();
//			            }
//			         	,     
//			          
//			          numDoc: function() {
//			              return $("#numDoc").val();
//			            }
//			       	,    
//			          
//				   direccion: function() {
//			              return $("#direccion").val();
//			            }
//				},
//			          
//			     success : function(data){
//			    			alert(data);
//						}
//		  			});
//		
//	});
	
	

	
	
	
});