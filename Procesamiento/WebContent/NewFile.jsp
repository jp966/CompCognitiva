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
							<label><input id="check-nouns" type="checkbox" name="nouns">Nouns</label>
						</div>

						<div class="checkbox display-checkbox">
							<label><input id="check-adjectives" type="checkbox" name="adjectives">Adjetives</label>
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