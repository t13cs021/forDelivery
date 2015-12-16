<%
String pref = (String)request.getAttribute("prefName");
String[] mozis = (String[]) request.getAttribute("mozis");
%>

<html>
<head>
	<title>recommend</title>
</head>
<body>
<h1><%= pref %>のおすすめ</h1>
<table>
	<tr><th>mozisの内容</th></tr>
	<% if (mozis != null) for (int i = 0; i < mozis.length; i++) { %>
	<tr><td><a href="<%= request.getContextPath() %>/detail?pref=<%= pref %>&yasai=<%= mozis[i] %>"><%= mozis[i] %></a></td></tr>
	<% } %>
</table>
<form action="<%= request.getContextPath() %>/graph" method="get">
	<input type="hidden" name="pref" value="<%= pref %>">
	<input type="submit" value="goto Graph">
</form>
</body>
</html>