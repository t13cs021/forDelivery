<%
String pref = (String)request.getAttribute("prefName");
String yasai = (String) request.getAttribute("vegName");
%>

<html>
<head>
	<title>index</title>
</head>
<body>
<h1>さくもつしょうさい</h1>
<%= yasai %>のとくちょうをかいてね
</body>
</html>