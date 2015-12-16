<html>
<head>
	<title>index</title>
</head>
<body>
<h1>さんぷる</h1>
<form action="<%= request.getContextPath() %>/recommend" method="get">
	<input type="hidden" name="pref" value="yamanashi">
	<input type="submit" value="goto Recommend">
</form>
</body>
</html>