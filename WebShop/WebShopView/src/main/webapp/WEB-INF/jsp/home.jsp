<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.ProdottoDto" %>
<%@page import="dto.ProduttoreDto" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<%	
    	ArrayList<ProdottoDto> lDto = (ArrayList<ProdottoDto>) session.getAttribute("lProdotti");
%>
	<table>
		<tr>
			<td>Modello</td>
			<td>Produttore</td>
			<td>Prezzo</td>
			<td>Pezzi disponibili</td>
		</tr>
		<c:forEach var="p" items="${lDto}">
		<tr>
			<td>${p.modello}</td>
			<td>${p.produttore.nome}</td>
			<td>${p.prezzo}</td>
			<td>${p.quantita}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>