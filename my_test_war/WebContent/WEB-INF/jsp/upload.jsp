<%@ page contentType="text/html;charset=UTF-8" language="java"%>


<html>
<head>

<link type="text/css" rel="stylesheet" href="inc/form.css"/>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.1/themes/ui-lightness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-2.1.1.js"></script>
<script src="http://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>


<title>Chargement des n° postaux</title>


 <script>
  $(function() {
    $( "#progressbar" ).progressbar({
      value: 60
    });
  });
  </script>
  
</head>

<body>

<p>

	<h1>Chargement des n° postaux</h1>


	<fieldset>
		<legend>Charger les n° postaux</legend>

		<form action="uploadservlet" method="post" enctype="multipart/form-data">
			<label for="filename_1">Fichier :</label> 
			<input id="filename_1" type="file" name="filename_1" size="50" />
			 <br /> 
			<input type="submit" value="Upload File" />
		</form>
		
	</fieldset>
	<div id="progressbar"></div>
</body>
</html>