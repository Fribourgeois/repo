<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Tests JDBC</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="WEB-INF/inc/style.css"/>" />
</head>
<body>
	<h1>Tests JDBC</h1>

	<p>Ceci est une page générée depuis une JSP.</p>
	
	et voilà  qqqqqq
	<p>
		<%
			String attribut = (String) request.getAttribute("test");
		    out.println("attribut");
			out.println(attribut);
		%>
		1. <c:out value="${test}" />
		2. <c:out value="${message}" />
		3. <c:out value="test" />
		4. <c:out value="${test}" />
		
		<c:out value="${attribut}" />
	</p>

	
	<ul>
		E L E V E
		<li><c:out value="${test}" /></li>
	</ul>
	<form method="get" action="creationEleve">
		<fieldset>
			<legend>Informations client</legend>

			<label for="nomClient">Nom <span class="requis">*</span></label> <input
				type="text" id="nomEleve" name="nomEleve" value="" size="20"
				maxlength="20" /> <br /> <label for="prenomClient">Prénom
			</label> <input type="text" id="prenomEleve name=" prenomEleve" value=""
				size="20" maxlength="20" /> <br /> <label for="adresseClient">Adresse
				de livraison <span class="requis">*</span>
			</label> <input type="text" id="adresseEleve" name="adresseEleve" value=""
				size="20" maxlength="20" /> <br />


		</fieldset>
		<input type="submit" value="Valider" /> <input type="reset"
			value="Remettre à zéro" /> <br />
	</form>
</body>
</html>