


$(document).ready(function(){
	var contenido=null;
	$(document).on("change","#upload",function(event){
		event.preventDefault();		
		
		//se le quitará al checked a todos los input de tipo checkbox
		$('input[type=checkbox]').each(function(){ 
			this.checked = false; 
		}); 

		var archivo = event.target.files[0];

 		var reader = new FileReader();

  		reader.onload = function(event) {
  			
  		contenido=event.target.result;

  		//ELIMINACIÓN DE PUNTUACIÓN
  		var nuevoContenido = contenido;
		var punctuationless = nuevoContenido.replace(/[.,\/#!$%\^&\*;:{}=\-_`~()/"/]/g,"");
		var finalString = punctuationless.replace(/\s{2,}/g," ");

		var arrayContenido=finalString.split(" ");
  		
  		$("#textoDocumento").html(contenido);
  		
/*
   		$.post("ejemplo",
  			{
  				texto:contenido
  			},
				function(respuesta){
					$("#keywords").html("<p>"+respuesta+"</p>");

	  			}
  			);
  			*/
   		
   		
   		$.ajax({ 
   			type: "POST",
			url: "ejemplo", 
			data: {
  				texto:arrayContenido
  			},
  			beforeSend: function() {
  				
   				 $("#keywords").html('<img id="img-loading" class="center-block" src="resources/imagenes/loading.gif">');
  			},
  			
			success: function(respuesta){
				

				var temp;
				//ordenamiento básico bubble sort invertido
				for(var i=0;i<respuesta.palabras.length;i++){
					for(var j=1;j<respuesta.palabras.length-i;j++){
						if(respuesta.palabras[j-1].repetida<respuesta.palabras[j].repetida){
							temp=respuesta.palabras[j-1];
							respuesta.palabras[j-1]=respuesta.palabras[j];
							respuesta.palabras[j]=temp;
						}
					}
				}

				//string de palabras claves
				var arrayKeywords=[];
				//contador de palabras claves
				var contKeywords=0;
				//booleano para arreglo keywords
				var existe=false;

				for(var i=0;i<respuesta.palabras.length;i++){
					if(contKeywords<10){
						if(respuesta.palabras[i].categoria=="NN" || respuesta.palabras[i].categoria=="NNP" ){


							//se verifica que la palabra no haya sido ingresada anteriormente
							for(var j=0;j<arrayKeywords.length;j++){

								if(respuesta.palabras[i].palabra.toLowerCase()==arrayKeywords[j].toLowerCase()){
									existe=true;
									break;
								}else{
									existe=false;
								}
							}
							

							//si la palabra no estaba dentro de las keywords

							if(existe==false){
								arrayKeywords.push(respuesta.palabras[i].palabra);
							}

							//keywords+=respuesta.palabras[i].palabra+", ";
							contKeywords++;
						}
					}
				}

				var keywords="";

				for(var i=0;i<arrayKeywords.length;i++){

					keywords+=arrayKeywords[i]+", ";
				}

				var keys=keywords.substring(0, keywords.length-2)+".";
				
				$("#keywords").html(keys);

				//aparecen los contenedores con checks de pos y ner
				$(".contenedor-pos").css("display","block");
				$(".contenedor-ner").css("display","block");

		
				 
			}	 
   		}); 
   		
   		
  		};
  		//finaliza load (se gatilla loadend)
  		//and the result attribute contains the contents of the file as a text string (extracto web)
  		reader.readAsText(archivo);
  		
		
	});


	
});




