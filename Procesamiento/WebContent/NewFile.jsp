<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Procesamiento lenguaje natural</title>
</head>
<script src="resources/js/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="resources/css/estilonlp.css">
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/bootstrap.js"></script>

<body>
<div class="row-fluid" style="margin-top: 30px;">

	<div class="col-md-1">
		<div class="contenedor-upload">
		
			<span class="btn btn-primary btn-file">
    			Upload <input id="upload" type="file">
			</span>
	
		</div>
	</div>
	
	<div class="col-md-7">
		<div id="textoDocumento" class="documento" readonly>

 		</div>

		<div class="contenedor-keywords">
			<label for="keywords">Keywords:</label>
			
	 		<div id="keywords"></div>
 		</div>
	</div>
	
	<div class="col-md-4">
		<div class="contenedor-pos">
			<div class="row-fluid">
				<div class=col-md-12>
					<h2>POS</h2>
				</div>
				<div class=col-md-12>
					<form>
						<div class="checkbox display-checkbox">
							<label><input id="check1" type="checkbox" name="noun"><span id="noun">Nouns</span></label>
						</div>

						<div class="checkbox display-checkbox">
							<label><input type="checkbox" name="adjective"><span id="adjective">Adjectives</span></label>
						</div>

						<div class="checkbox display-checkbox">
							<label><input type="checkbox" name="verb"><span id="verb">Verbs</span></label>
						</div>

						<div class="checkbox display-checkbox">
							<label><input type="checkbox" name="adverb"><span id="adverb">Adverbs</span></label>
						</div>

						<div class="checkbox display-checkbox">
							<label><input type="checkbox" name="pronoun"><span id="pronoun">Pronouns</span></label>
						</div>

						<div class="checkbox display-checkbox">
							<label><input type="checkbox" name="conjunction"><span id="conjunction">Coordinating Conjunctions</span></label>
						</div>
						
						<div class="checkbox display-checkbox">
							<label><input type="checkbox" name="cardinalnum"><span id="cardinalnum">Cardinal Numbers</span></label>
						</div>

						<div class="checkbox display-checkbox">
							<label><input type="checkbox" name="determiner"><span id="determiner">Determiners</span></label>
						</div>

						<div class="checkbox display-checkbox">
							<label><input type="checkbox" name="preposition"><span id="preposition">Prepositions</span></label>
						</div>

						<div class="checkbox display-checkbox">
							<label><input type="checkbox" name="to"><span id="to">To</span></label>
						</div>

						<div class="checkbox display-checkbox">
							<label><input type="checkbox" name="foreignword"><span id="foreignword">Foreign Words</span></label>
						</div>
						
						<div class="checkbox display-checkbox">
							<label><input type="checkbox" name="possessive"><span id="possessive">Possessive Endings</span></label>
						</div>
						
						<div class="checkbox display-checkbox">
							<label><input type="checkbox" name="existential"><span id="existential">Existential there</span></label>
						</div>


						<div class="checkbox display-checkbox">
							<label><input type="checkbox" name="listItem"><span id="listItem">List item markers</span></label>
						</div>

						<div class="checkbox display-checkbox">
							<label><input type="checkbox" name="modal"><span id="modal">Modals</span></label>
						</div>

						<div class="checkbox display-checkbox">
							<label><input type="checkbox" name="predeterminer"><span id="predeterminer">Predeterminers</span></label>
						</div>

						<div class="checkbox display-checkbox">
							<label><input type="checkbox" name="particle"><span id="particle">Particles</span></label>
						</div>

						<div class="checkbox display-checkbox">
							<label><input type="checkbox" name="symbol"><span id="symbol">Symbols</span></label>
						</div>
						
						<div class="checkbox display-checkbox">
							<label><input type="checkbox" name="interjection"><span id="interjection">Interjections</span></label>
						</div>


					</form>
				</div>
			</div>
		</div>
		

		<div class="contenedor-ner">
			<div class="row-fluid">
				<div class=col-md-12>
					<h2>NER</h2>
				</div>
				<div class=col-md-12>
					<form>
						<div class="checkbox display-checkbox">
							<label><input type="checkbox" name="check1">Localizations</label>
						</div>

						<div class="checkbox display-checkbox">
							<label><input type="checkbox" name="check2">Organizations</label>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

</div>


<script src="resources/js/ejemploScript.js"></script>
<script src="resources/js/pos.js"></script>

</body>
</html>