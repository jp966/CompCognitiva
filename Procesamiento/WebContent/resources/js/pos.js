$(document).ready(function(){

	var esClickeado=false;
	$(document).on("click","input:checkbox",function(event){
		event.preventDefault();

		esClickeado=!esClickeado;

		$.ajax({
			type:"POST",
			url:"pos",
			data: {categoria:$(this).attr("name")},
			success: function(respuesta){

				var arrayPalabras=respuesta.split(",");

				if(esClickeado){
					for(var i=0;i<arrayPalabras.length;i++){

						$("#textoDocumento").html(function(index,html){
							return html.replace(arrayPalabras[i].split("_")[0],
								"<span class='"+arrayPalabras[i].split("_")[1]+"'>"+arrayPalabras[i].split("_")[0]+"</span>");
						});
					}
				}else{
						if(esClickeado===false){
							//se eliminan todos los <span> con la clase correspondiente
							$("."+arrayPalabras[0].split("_")[1]+"").contents().unwrap();
					
						}
						
						
					
				}

				//se llamara al contenedor del documento y se buscarán
				//se pondra un <span> a las palabras para setearlas con la clase css correspondiente
			}



		});

	});
		

});