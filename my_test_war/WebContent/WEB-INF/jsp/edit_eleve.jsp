
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
<head>

<link type="text/css" rel="stylesheet"
	href="<c:url value="inc/form.css"/>" />


<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="inc/jquery-ui.css">
<script src="inc/jquery-1.12.4.js"></script>
<script src="inc/jquery-ui.js"></script>
<script src="inc/datepicker-fr.js"></script>

<script>
	$(function() {
		$.datepicker.setDefaults($.datepicker.regional["fr"]);
		$("#datepicker").datepicker({
			changeMonth : true,
			changeYear : true
		});
	});
</script>

<meta charset="utf-8" />
<title>Editer élève</title>
<body>

	<fmt:formatDate value="${eleve.dateNaissance}" pattern="dd.MM.yyyy"
		var="myDate" />

	<form method="post" action="editEleve">
		<fieldset>
		
			<legend>Informations eleve</legend>
			  <p>Vous pouvez vous modifier l'élèvevioa ce formulaire.</p>
			<input id="id" name="id" type="hidden" value="${eleve.id}">
		
			 <br />	
			 	
		<label for="nomClient">Nom <span class="requis">*</span></label> 
				
		<input type="text" id="nomEleve" name="nom" value="${eleve.nom}" size="20" maxlength="20" /> 
		<span class="erreur">${erreurs['nom']}</span>
		 <span class="erreur">${form.erreurs['nom']}</span>
		 <br />
				
			 <label
				for="prenomClient">Prénom <span class="requis">*</span></label> 
			<input
				type="text" id="prenomEleve" name="prenom" value="${eleve.prenom}"
				size="20" maxlength="20" /> 
			
				<br/> 
				
			<label for="dateNaissance">Date de naissance <span class="requis">*</span> </label>
			
			<input type="text" id="datepicker" name="dateNaissance"
				value="${myDate}" size="10" maxlength="10" /> <br /> 
			<input type="submit" value="Valider" /> <input type="reset" value="Remettre à zéro" /> <br />

			<p class="${empty erreurs ? 'succes' : 'erreur'}">${resultat}</p>
		</fieldset>
	</form>

</body>
</html>