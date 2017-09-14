package ejercicios;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//IMPORTS DE LIBRERIA STANFORD

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

/**
 * Servlet implementation class ejemplo
 */
@WebServlet("/ejemplo")
public class ejemplo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ejemplo() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			 
		}
	
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// reading the user input
		    String texto[]= request.getParameterValues("texto[]");    
		    String taggs[]=new String[texto.length];
			//COPY DE EJEMPLO
			MaxentTagger tagger = new MaxentTagger(
			 
			"C:/Users/Usuario/Desktop/Apache Tomcat/lib/taggers/english-caseless-left3words-distsim.tagger");
			
			
			// The sample string
			 
			//String sample = "This is a sample text";
			 
			// The tagged string
	
			//*String tagged = tagger.tagString(texto);
			
			for(int i=0;i<texto.length;i++){
				taggs[i]=tagger.tagString(texto[i]);
			}
			
			// Output the result
			
			//*String respuesta=tagged;
			String respuesta="{\"palabras\" : [";
			
			for(int i=0;i<taggs.length;i++){
				int cont=0;
				String[] parts = taggs[i].split("_");
				String palabra = parts[0]; 
				String categoria = parts[1];
				
				//el string de categoría se lleva el espacio, por lo que debe eliminarse
				categoria = categoria.replaceAll("\\s+",""); 


				for(int k=0;k<taggs.length;k++){
					//no habrá diferencia entre palabras con letras mayúsculas y minúsculas
					if(taggs[k].toLowerCase().equals(taggs[i].toLowerCase())){
						cont++;
					}
				}
	
				respuesta+="{ \"palabra\" : \""+palabra+"\",\"categoria\" : \""+categoria+"\",\"repetida\": "+cont+"},";
				

		}

		respuesta=respuesta.substring(0,respuesta.length()-1);

		respuesta+="]}";
		
		//el json se guarda 
		PrintWriter out = new PrintWriter("C:/Users/Usuario/Desktop/Apache Tomcat/lib/json/archivo.json");
		out.println(respuesta);
		out.close();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(respuesta);
	}

}

