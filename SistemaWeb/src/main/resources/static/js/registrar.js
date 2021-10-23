$(function() {
	
//	$("#frm_contact").validate({
//        rules: {
//        	name: {
//                        required: true
//                },
//             apellido: {
//                    required: true
//            }, 
//            numDoc: {
//                required: true
//           }            
//        },
//        messages: {
//        	name: {
//                        required: "El nombre es requerido"
//            },
//            apellido: {
//                required: "El apellido es requerido"
//           },
//           numDoc: {
//               required: "El numero de documento es requerido"
//          }            
//      }   
//    });
	//btnRegistrar
	$("#btnRegistrar").click(function(){
		//alert("en el boton");
//        if($("#frm_contact").valid()){   // test for validity
  		  $.ajax({
			   type:"get", 
			   url:"/registrar", 
			   async : false,
		   data: {
				   
				   idServicio: $('select[name=servicios] option').filter(':selected').val()
			          ,
			        nombre: function() {
			              return $("#name").val();
			            }
			         ,   
		          
			          apellido: function() {
			              return $("#apellido").val();
			            }
			        ,     
			          idTipDoc: $('select[name=tipDoc] option').filter(':selected').val()
			         	,     
			          
			          numDoc: function() {
			              return $("#numDoc").val();
			            }
			       	,    
			          
				   direccion: function() {
			              return $("#direccion").val();
			            }
				},
			          
			     success : function(data){
			    	 		if(data == 0){
			    	 			alert("Se registró con éxito");
			    	 		}else{
			    	 			alert("Error en el registro");
			    	 		}
			    			
						}
		  			}); 
//        } else {
//        	alert("Completar los datos");
//        }

		
	});
});