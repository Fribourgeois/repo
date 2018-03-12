<%@ page contentType="text/html;charset=UTF-8" language="java"%>


<html>
<script>
	function move() {
		var elem = document.getElementById("myBar");
		var width = 1;
		var id = setInterval(frame, 0.1);
		function frame() {
			if (width >= 100) {
				clearInterval(id);
			} else {
				width++;
				elem.style.width = width + '%';
			}
		}
	}
</script>

<style>
#myProgress {
	width: 100%;
	background-color: #ddd;
}

#myBar {
	width: 1%;
	height: 30px;
	background-color: #4CAF50;
}
</style>

<head>

<link type="text/css" rel="stylesheet" href="inc/form.css" />
<link rel="stylesheet" type="text/css" href="inc/style.css">

<title>Chargement des n° postaux</title>

</head>

<body>
	<p>
	<h1>Chargement des n° postaux</h1>
	
		<p class="erreur">${resultat}</p>
		
		
	<fieldset>
		<legend>Charger les n° postaux</legend>

		<form action="uploadservlet" method="post"
			enctype="multipart/form-data">
			<label for="filename_1">Fichier :</label> 
			<input id="filename_1" type="file" name="filename_1" size="50" /> <br /> 
			<input type="submit" value="Charger" onclick="move()" />
		</form>
	</fieldset>
	
	
<div id="myProgress">
  <div id="myBar"></div>
</div>
</body>
</html>