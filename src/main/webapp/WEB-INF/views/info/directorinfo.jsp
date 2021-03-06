<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="./resources/images/logo4.png">
<meta charset="EUC-KR">
<c:forEach var="d" items="${directorinfos}" end="0">
<title>${d.director} - 필모그래피</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</head>
<body>
<%@include file="../top.jsp" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<br><br>

<h3>${d.director}</h3>
</c:forEach>
<hr>
<table width="100%">
<% int i = 0;%>
<c:forEach var="di" items="${directorinfos}">
<%
if(i == 0){
%>
<tr>
<%}%>
	<td>
		<table align="center">
		<tr>
			<td>
			<a href="goinfo?name=${di.name}">
			<img src="./resources/images/${di.image}" width="300">
			</a>
			</td>
		</tr>
		<tr>
			<td>
			${di.name}
			</td>
		</tr>
		<tr>
			<td>
			평균 ★ ${di.cnt}
			</td>
		</tr>
		<tr>
			<td>
			</td>
		</tr>
		</table>
	</td>
<%
i++;
if(i == 6){
%>
</tr>
<%
i=0;
}
%>
</c:forEach>
</table>
</body>
</html>