$(document).ready(function(){

	//var esClickeado=false;
	$(document).on("change","input:checkbox",function(event){
		
		event.preventDefault();

		//esClickeado=!esClickeado;

		$.ajax({
			type:"POST",
			url:"pos",
			data: {categoria:$(this).attr("name")},
			success: function(respuesta){

				if(respuesta==""){

				}else{

					var arrayPalabras=respuesta.split(",");

					//si hay span de la clase, lo elimino, si no, lo pongo

					if($("."+arrayPalabras[0].split("_")[1]+"").length){

						$("."+arrayPalabras[0].split("_")[1]+"").contents().unwrap();
						/*
						for(var i=0;i<arrayPalabras.length;i++){

							$("#textoDocumento").html(function(index,html){
								return html.replace(arrayPalabras[i].split("_")[0],
									"<span class='"+arrayPalabras[i].split("_")[1]+"'>"+arrayPalabras[i].split("_")[0]+"</span>");
							});
						}
						*/
					}else{

							for(var i=0;i<arrayPalabras.length;i++){

								$("#textoDocumento").html(function(index,html){

									//con esta expresión regular solo se tomarán las palabras completas, y no secciones de otras
									var changing_value = arrayPalabras[i].split("_")[0];
									var re;

									//ya que POS categoriza las palabras anteriores al 's, y a este ultimo como dos distintas
									if( arrayPalabras[i+1].split("_")[0]=="'s"){
										//se considera la palabra anterior al 's como una distinta
										re = new RegExp("\\b" + changing_value,"g");
									}else{

										re = new RegExp("\\b" + changing_value + "\\b","g");
									}

									//si la palabra es un 's, entonces se considera como una distinta
									if(arrayPalabras[i].split("_")[0]==" 's"){

										re = new RegExp(changing_value + "\\b","g");
									}else{
										re = new RegExp("\\b" + changing_value + "\\b","g");
									}


									//Buscar traer la palabra anterior, para realizar una distincion entre las distinas 's
									//COMPLETAR CON EL RESTO DE CATEGORÍAS-->POS TERMINADO

									return html.replace(re,
										"<span class='"+arrayPalabras[i].split("_")[1]+"'>"+arrayPalabras[i].split("_")[0]+"</span>");
								});
							}					

						/*
							if(esClickeado===false){
								//se eliminan todos los <span> con la clase correspondiente
								$("."+arrayPalabras[0].split("_")[1]+"").contents().unwrap();
						
							}
							
						*/	
						
					}
				}

				//se llamara al contenedor del documento y se buscarán
				//se pondra un <span> a las palabras para setearlas con la clase css correspondiente
			}



		});

	});
		

});