
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.appspot.projectF.datastore.Crops"%>
<%@ page import="java.util.List"%>
<%@ page import="com.appspot.projectF.servlet.AddCrops"%>
<%
	String pref = (String) request.getAttribute("prefName");
	String yasai = (String) request.getAttribute("vegName");
	List<Crops> crops = (List<Crops>)request.getAttribute("crops");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><%= yasai %>の詳細情報</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--Load the AJAX API-->
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
	google.load('visualization', '1.0', {
		'packages' : [ 'corechart' ]
	});
	var data = new google.visualization.DataTable();
</script>

<style type="text/css">
body {
	background-color: silver;
}
</style>
</head>
<body>


	<h1>作物詳細ページ</h1>
	<div style="border-style: solid; border-width: 1px; width: 200px; background-color: #008000; padding: 10px 10px 10px 10px;">
		<FONT color="white">
			<%=yasai%>に適した気温条件
		</FONT>
	</div>
	<div style="position: absolute; top: 250; left: 50px; text-align:center;">
		<img src="<%=yasai%>.png" width="250" height="250"><br><br><br>
		<form action="/recommend" method="get">
			<input type="hidden" name="pref" value="<%=pref%>">
			<input type="submit" value="戻る" >
		</form>
	</div>
	<br>
	<div style="border-style: solid; border-width: 1px; width: 200px; background-color: white; padding: 10px 250px 200px 10px; position: absolute; top: 150; left: 350px;">
		<FONT color="aqua">気温</FONT>
		<%
		for (Crops sr : crops) {
			// enable lower and upper -> +11
			// enable lower           -> +10
			// enable upper           -> + 1
			switch (sr.getTemp_lLimit() != AddCrops.LDUMMY ? 10 : 0
					+ sr.getTemp_uLimit() != AddCrops.UDUMMY ? 1 : 0) {
			case 11:
				%>
				<br>
				<br>
				<%=sr.getMonth()%>月の下限<%=sr.getTemp_lLimit()%>℃<br>
				上限<%=sr.getTemp_uLimit()%>℃
				<%
				break;
			case 1:
				%>
				<br>
				<br>
				<%=sr.getMonth()%>月の上限<%=sr.getTemp_uLimit()%>℃
				<%
				break;
			case 10:
				%>
				<br>
				<br>
				<%=sr.getMonth()%>月の下限<%=sr.getTemp_lLimit()%>℃
				<%
				break;
			default:
				break;
			}
		}
		%>
		<br> <br> <br> <FONT color="FF9900">日照</FONT>
		<%
		for (Crops sr : crops) {
			// enable lower and upper -> +11
			// enable lower           -> +10
			// enable upper           -> + 1
			switch (sr.getSunhour_lLimit() != AddCrops.LDUMMY ? 10 : 0
					+ sr.getSunhour_uLimit() != AddCrops.UDUMMY ? 1 : 0) {
			case 11:
				%>
				<br>
				<br>
				<%=sr.getMonth()%>月の下限<%=sr.getSunhour_lLimit()%>時間<br>
				上限<%=sr.getSunhour_uLimit()%>時間
				<%
				break;
			case 1:
				%>
				<br>
				<br>
				<%=sr.getMonth()%>月の上限<%=sr.getSunhour_uLimit()%>時間
				<%
				break;
			case 10:
				%>
				<br>
				<br>
				<%=sr.getMonth()%>月の下限<%=sr.getSunhour_lLimit()%>時間	<br>
				<%
				break;
			default:
				break;
			}
		}
		%>
		<br>
		<br>
		
		<br> <br> <br> <FONT color="green">メモ</FONT>

		<% 
		for (Crops sr : crops) {
			if (sr.getMemo().equals("")) continue;
			%>
			<br>
			<br>
			<%=sr.getMemo()%>
		<%
		}
		%>

		<br>
	</div>
	<div style="position: absolute; top: 500; left: 150px;">
	</div>
</body>
</html>
