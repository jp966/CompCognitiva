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
		String respuesta="";

		//arreglo con las categorias de sustantivos
		String [] arrayNouns={"NN","NNS","NNP","NNPS"};
		
	
       //Obtener json del archivo y hacer un arreglo con las palabras pertenecientes a la categoría
		JSONParser parser = new JSONParser();
		try {
			JSONObject a = (JSONObject) parser.parse(new FileReader("C:/Users/Usuario/Desktop/Apache Tomcat/lib/json/archivo.json"));
			
			//arreglo de palabras del documento
			JSONArray palabras = (JSONArray) a.get("palabras");
			
			//si lo que se desea marcar en el texto son los NN
			if(tag.equals("nouns")){

				for(int i=0;i<palabras.size();i++){
					
					JSONObject palabra1=(JSONObject) palabras.get(i);
					String categoria=(String) palabra1.get("categoria");
					
					for(int j=0;j<arrayNouns.length;j++){

						if(categoria.equals(arrayNouns[j])){
							String palabra=(String) palabra1.get("palabra");
							respuesta+=palabra+"_noun,";
							break;
						}
					}
					
					
					
				}
				
				respuesta=respuesta.substring(0, respuesta.length()-1);
				
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(respuesta);
			}
			
			
		} catch (ParseException e) {
			
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
}
