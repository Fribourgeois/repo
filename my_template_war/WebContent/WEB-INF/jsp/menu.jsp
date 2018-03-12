<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div id="menu">
    <p><a href="<c:url value="/creationClient"/>">Créer un nouveau client</a></p>
    <p><a href="<c:url value="/creationCommande"/>">Créer une nouvelle commande</a></p>
    <p><a href="<c:url value="/listeEleves"/>">Voir les élèves existants</a></p>
    <p><a href="<c:url value="/listeCommandes"/>">Voir les commandes existantes</a></p>
    
      <p><a href="<c:url value="/inscription"/>">Inscription</a></p>
</div>
