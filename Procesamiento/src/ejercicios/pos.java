package ejercicios;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Servlet implementation class pos
 */
@WebServlet("/pos")
public class pos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tag = request.getParameter("categoria");

		String [] array=null;

		//arreglo con las categorías
		String [] categorias ={"noun","adjective","verb","adverb","pronoun","conjunction","cardinalnum","determiner"
			,"preposition","to","foreignword","possessive","existential","listItem","modal","predeterminer","particle","symbol",
			"interjection"};

		if(tag.equals("noun")){
		//arreglo con las categorias de sustantivos
			array=new String[] {"NN","NNS","NNP","NNPS"};
		}

		if(tag.equals("adjective")){
		//arreglo con las categorias de adjetivos
			array=new String[] {"JJ","JJR","JJS"};
		}

		if(tag.equals("verb")){
		//arreglo con las categorias de verbos
			array=new String[] {"VB","VBD","VBG","VBN","VBP","VBZ"};
		}

		if(tag.equals("adverb")){
		//arreglo con las categorias de adverbios
			array=new String[] {"RB","RBR","RBS","WRB"};
		}

		if(tag.equals("pronoun")){
		//arreglo con las categorías de pronombres
			array=new String[] {"PRP","PRP$","WP","WP$"};
		}

		if(tag.equals("conjunction")){
			array=new String[] {"CC"};
		}

		if(tag.equals("cardinalnum")){
			array=new String[] {"CD"};
		}

		if(tag.equals("determiner")){
			array=new String[] {"DT","WDT"};
		}

		if(tag.equals("preposition")){
			array=new String[] {"IN"};
		}

		if(tag.equals("to")){
			array=new String[] {"TO"};
		}
		
		if(tag.equals("foreignword")){
			array=new String[] {"FW"};
		}
		
		if(tag.equals("possessive")){
			array=new String[] {"POS"};
		}

		if(tag.equals("existential")){
			array=new String[] {"EX"};
		}
		
		if(tag.equals("listItem")){
			array=new String[] {"LS"};
		}

		if(tag.equals("modal")){
			array=new String[] {"MD"};
		}

		if(tag.equals("predeterminer")){
			array=new String[] {"PDT"};
		}

		if(tag.equals("particle")){
			array=new String[] {"RP"};
		}

		if(tag.equals("symbol")){
			array=new String[] {"SYM"};
		}

		if(tag.equals("interjection")){
			array=new String[] {"UH"};
		}
		
	
       //Obtener json del archivo y hacer un arreglo con las palabras pertenecientes a la categoría
		JSONParser parser = new JSONParser();
		try {
			JSONObject a = (JSONObject) parser.parse(new FileReader("C:/Users/Usuario/Desktop/Apache Tomcat/lib/json/archivo.json"));
			
			//arreglo de palabras del documento
			JSONArray palabras = (JSONArray) a.get("palabras");
			
			for(int k=0;k<categorias.length;k++){			
				if(tag.equals(categorias[k])){

					String respuesta="";

					for(int i=0;i<palabras.size();i++){
						
						JSONObject palabra1=(JSONObject) palabras.get(i);
						
						JSONObject palabra2=null;
						//palabra anterior (aplicable al caso del 's)
						
						if(i>0){
							palabra2=(JSONObject) palabras.get(i-1);
						}

						
						String categoria=(String) palabra1.get("categoria");
						
						for(int j=0;j<array.length;j++){

							if(categoria.equals(array[j])){
								String palabra=(String) palabra1.get("palabra");
								//Aqui podria hacer algo
								//si lleva 's->'s_categoria_palabra
								if(palabra.equals("'s")){
									respuesta+=palabra+"_"+categorias[k]+"_"+palabra2.get("palabra")+",";
									System.out.println(palabra+"_"+categorias[k]+"_"+palabra2.get("palabra")+",");
								}else{
									respuesta+=palabra+"_"+categorias[k]+",";
								}
								break;
							}
						}			
					}
					
					if(respuesta.length()>0){
						respuesta=respuesta.substring(0, respuesta.length()-1);
					}else{
						respuesta="";
					}
					
					response.setContentType("text/plain");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(respuesta);
				}
			}

			/*
			//SI LO QUE SE DESEA MARCAR EN EL TEXTO SON LOS ADJETIVOS

			if(tag.equals("adjectives")){

				String respuesta="";

				for(int i=0;i<palabras.size();i++){
					
					JSONObject palabra1=(JSONObject) palabras.get(i);
					String categoria=(String) palabra1.get("categoria");
					
					for(int j=0;j<arrayAdj.length;j++){

						if(categoria.equals(arrayAdj[j])){
							String palabra=(String) palabra1.get("palabra");
							respuesta+=palabra+"_adjective,";
							break;
						}
					}			
				}
				
				respuesta=respuesta.substring(0, respuesta.length()-1);
				
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(respuesta);
			}

			//SI LO QUE SE DESEA MARCAR EN EL TEXTO SON LOS VERBOS

			if(tag.equals("verbs")){

				String respuesta="";

				for(int i=0;i<palabras.size();i++){
					
					JSONObject palabra1=(JSONObject) palabras.get(i);
					String categoria=(String) palabra1.get("categoria");
					
					for(int j=0;j<arrayVerb.length;j++){

						if(categoria.equals(arrayVerb[j])){
							String palabra=(String) palabra1.get("palabra");
							respuesta+=palabra+"_verb,";
							break;
						}
					}			
				}
				
				respuesta=respuesta.substring(0, respuesta.length()-1);
				
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(respuesta);
			}

			//SI LO QUE SE DESEA MARCAR EN EL TEXTO SON LOS ADVERBIOS
			if(tag.equals("adverbs")){

				String respuesta="";

				for(int i=0;i<palabras.size();i++){
					
					JSONObject palabra1=(JSONObject) palabras.get(i);
					String categoria=(String) palabra1.get("categoria");
					
					for(int j=0;j<arrayAdverb.length;j++){

						if(categoria.equals(arrayAdverb[j])){
							String palabra=(String) palabra1.get("palabra");
							respuesta+=palabra+"_adverb,";
							break;
						}
					}			
				}
				
				respuesta=respuesta.substring(0, respuesta.length()-1);
				
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(respuesta);
			}

			//SI LO QUE DESEA MARCAR EN EL TEXTO SON LOS PRONOMBRES

			if(tag.equals("pronouns")){

				String respuesta="";

				for(int i=0;i<palabras.size();i++){
					
					JSONObject palabra1=(JSONObject) palabras.get(i);
					String categoria=(String) palabra1.get("categoria");
					
					for(int j=0;j<arrayPronoun.length;j++){

						if(categoria.equals(arrayPronoun[j])){
							String palabra=(String) palabra1.get("palabra");
							respuesta+=palabra+"_pronoun,";
							break;
						}
					}			
				}
				
				respuesta=respuesta.substring(0, respuesta.length()-1);
				
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(respuesta);
			}

			*/
			
			
		} catch (ParseException e) {
			
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
}
