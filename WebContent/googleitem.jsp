<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
body {
	background-image:
		url('https://www.wangyetuku.com/d/file/2019/cb83b37dfd712c3ecb6203f0021e2cb5.jpg');
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: cover;
	background-position: center;
}


</style>
<meta charset="UTF-8">
<title>GoogleSearch</title>
</head>
<body>
	<%
		String[][] orderList = (String[][]) request.getAttribute("query");
	for (int i = 0; i < orderList.length; i++) {
	%>
	<div><a href='<%=orderList[i][1]%>' style="color:white;font-weight:bold;font-size:x-large;font-family:monospace;"><%=orderList[i][0]%></a></div>
	<br>
	<h style="font-size:5px ;"><%=orderList[i][1]%></h>
	<br>
	<br>
	<%
}
%>
</body>
</html>