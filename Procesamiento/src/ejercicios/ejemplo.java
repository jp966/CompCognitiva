package ejercicios;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//IMPORTS DE LIBRERIA STANFORD

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.*;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.sequences.DocumentReaderAndWriter;
import edu.stanford.nlp.util.Triple;

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
		    //String taggs[]=new String[texto.length];

		    ArrayList<String> taggs = new ArrayList<String>();
		    ArrayList<String> entities=new ArrayList<String>();

      		
		    //Classifier

		    String serializedClassifier = "C:/Users/Usuario/Desktop/Apache Tomcat/lib/classifiers/english.all.3class.distsim.crf.ser.gz";
		    AbstractSequenceClassifier<CoreLabel> classifier;
		    List<Triple<String, Integer, Integer>> listaEntidades;

			try {
				classifier = CRFClassifier.getClassifier(serializedClassifier);
				
				for(int i=0;i<texto.length;i++){
				
					String fileContents = texto[i];
					
				    listaEntidades = classifier.classifyToCharacterOffsets(fileContents);
	
				    for (Triple<String, Integer, Integer> item : listaEntidades) {
		        		entities.add(fileContents.substring(item.second(), item.third())+"_"+item.first());
		      		}
			    
				}

			} catch (ClassCastException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   

			//Tagger
			MaxentTagger tagger = new MaxentTagger(
			 
			"C:/Users/Usuario/Desktop/Apache Tomcat/lib/taggers/english-caseless-left3words-distsim.tagger");
			

			//Se aplica POS de Stanford
			
			for(int i=0;i<texto.length;i++){

				//taggs[i]=tagger.tagString(texto[i]);
				taggs.add(tagger.tagString(texto[i]));

				String str = taggs.get(i);

				String[] splited = str.split("\\s+");

				if(splited.length>1){
					taggs.remove(i);
					taggs.add(splited[0]);
					taggs.add(splited[1]);
				}

				System.out.println(taggs.get(i));
			}
			
			// Se busca en caso de que existan 's


			
			//*String respuesta=tagged;
			String respuesta="{\"palabras\" : [";
			
			for(int i=0;i<taggs.size();i++){
				String entidad="";
				int cont=0;
				String[] parts = taggs.get(i).split("_");
				String palabra = parts[0]; 
				String categoria = parts[1];
			
				//el string de categoría se lleva el espacio, por lo que debe eliminarse
				categoria = categoria.replaceAll("\\s+",""); 


				for(int k=0;k<taggs.size();k++){
					//no habrá diferencia entre palabras con letras mayúsculas y minúsculas
					if(taggs.get(k).toLowerCase().equals(taggs.get(i).toLowerCase())){
						cont++;
					}
				}

				
				for (int j=0;j<entities.size();j++) {
					
					String[] parts2 = entities.get(j).split("_");
					String palabraEnt = parts2[0]; 
					String ent = parts2[1];
					//System.out.println(palabraEnt+":" +ent);
					if(palabra.equals(palabraEnt)){
						entidad=ent;
						break;
					}
					
		      	}
			    
	
				respuesta+="{ \"palabra\" : \""+palabra+"\",\"categoria\" : \""+categoria+"\",\"repetida\": "+cont+",\"entidad\" : \""+entidad+"\"},";
				

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

