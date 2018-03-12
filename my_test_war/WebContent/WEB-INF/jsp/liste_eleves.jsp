
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<html>
<head>
<link rel="stylesheet" type="text/css" href="inc/style.css">

<script type="text/javascript" charset="utf8" src="inc/jquery-1.12.4.js"></script>
<script type="text/javascript" charset="utf8" src="inc/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css" href="inc/jquery.dataTables.min.css">


<meta charset="utf-8" />
<title>Liste des éleves</title>
<body>
	<p class="${empty erreurs ? 'succes' : 'erreur'}">${resultat}</p>

	<table id="datatable" class="display" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>Liste des élèves</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="row" items="${eleves}">
				<tr>
					<td class="action">
                        <a href="<c:url value="/editEleve"><c:param name="eleveId" value="${row.id}" /></c:url>">
                         <c:out value="${row.id}" />
						</a>
						</td>
					<td width="30%"><c:out value="${row.nom}" /></td>
					<td width="20%"><c:out value="${row.prenom}" /></td>
					<td width="10%"><fmt:formatDate type = "DATE" dateStyle = "short" pattern = "dd.MM.yyyy"  value = "${row.dateNaissance}" />
					<td width="30%"><c:out value="${row.adresse}" /></td>

  					<td class="action">
                        <a href="<c:url value="/deleteEleve"><c:param name="eleveId" value="${row.id}" /></c:url>">
                            <img src="<c:url value="/inc/supprimer.png"/>" alt="Supprimer" />
						</a>


				</tr>
			</c:forEach>
		</tbody>
	</table>

	<script>
		$(document).ready(function() {
			$('#datatable').DataTable({


			});
		});
	</script>
	<p>
</body>
</html>