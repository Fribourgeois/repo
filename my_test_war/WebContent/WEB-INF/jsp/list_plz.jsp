
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
<head>
<link rel="stylesheet" type="text/css" href="inc/style.css">

<script type="text/javascript" charset="utf8"
	src="https://code.jquery.com/jquery-1.12.4.js"></script>

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>


<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<meta charset="utf-8" />
<title>Liste des numéros postaux en suisse</title>



<body>
	<h3>Numéros postaux</h3>
	<br>
	
	<p class="succes">${resultat}</p>
	
	
	<form>
		<div class="ui-widget">
			<label for="country">Localité: </label> <input id="country" />
		</div>
	</form>
	<br>

	<table id="datatable" class="display">

		<thead>
			<tr>
				<th>ORNP</th>
				<th>NPA</th>
				<th>Description 18</th>
				<th>Description 27</th>
				<th>Valable depuis le</th>
				<th>Supprimer</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th>ORNP</th>
				<th>NPA</th>
				<th>Description 18</th>
				<th>Description 27</th>
				<th>Valable depuis le</th>
				<th>Supprimer</th>
			</tr>
		</tfoot>
		<tbody>
			<c:forEach var="row" items="${plz}">
				<tr>
					<td class="action"><a
						href="<c:url value="/editOrnp"><c:param name="ornp" value="${row.ornp}" /></c:url>">
							<c:out value="${row.ornp}" />
					</a></td>
					<td width="5%"><c:out value="${row.postLeitZahl}" /></td>
					<td width="20%"><c:out value="${row.ortBez18}" /></td>
					<td width="20%"><c:out value="${row.ortBez27}" /></td>

					<td width="10%"><fmt:formatDate type="DATE" dateStyle="short"
							pattern="dd.MM.yyyy" value="${row.giltAbDatum}" />
					<td class="action"><a
						href="<c:url value="/deleteEleve"><c:param name="eleveId" value="${row.ornp}" /></c:url>">
							<img src="<c:url value="/inc/supprimer.png"/>" alt="Supprimer" />
					</a>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<script>
		$(document).ready(function() {
			$('#datatable').DataTable({});
		});

		$(document).ready(function() {
			$(function() {

				$("#country").autocomplete({
					source : function(request, response) {
						$.ajax({
							url : "/my_test_war/autocomplete/",
							type : "POST",
							data : {
								term : request.term
							},

							dataType : "json",

							success : function(data) {
								response(data);
							}
						});
					}
				});
			});
		});
	</script>
	<p>
</body>
</html>