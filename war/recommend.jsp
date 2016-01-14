<%@ page import="java.util.*,com.appspot.projectF.datastore.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String pref = (String)request.getAttribute("prefName");
String[] rank1_crops = (String[]) request.getAttribute("rank1_crops");
String[] rank2_crops = (String[]) request.getAttribute("rank2_crops");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>選出結果</title>
</head>
<body>
<h1><%= pref %>のおすすめ</h1>
<!-- 選択した都道府県におすすめの野菜一覧 -->
<table>
	<tr><th>星1つ</th></tr>
	<% if (rank1_crops != null) for (int i = 0; i < rank1_crops.length; i++) { %>
	<tr><td><a href="<%= request.getContextPath() %>/detail?pref=<%= pref %>&yasai=<%= rank1_crops[i] %>"><%= rank1_crops[i] %></a></td></tr>
	<% } %>
	<tr><th>星2つ</th></tr>
	<% if (rank2_crops != null) for (int i = 0; i < rank2_crops.length; i++) { %>
	<tr><td><a href="<%= request.getContextPath() %>/detail?pref=<%= pref %>&yasai=<%= rank2_crops[i] %>"><%= rank2_crops[i] %></a></td></tr>
	<% } %>
</table>
<!-- 選択した都道府県の気候のグラフを表示 -->
<form action="<%= request.getContextPath() %>/graph" method="get">
	<input type="hidden" name="pref" value="<%= pref %>">
	<input type="submit" value="goto Graph">
</form>
</body>
</html>