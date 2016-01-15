<%@ page import="com.appspot.projectF.datastore.Climate" %>
<%@ page import="com.appspot.projectF.util.*" %>
<%@ page import="java.text.MessageFormat" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
String pref = (String)request.getAttribute("prefName");
String shurui = request.getParameter("shurui");
Climate [] prefClim = (Climate[])request.getAttribute("climate");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>グラフ</title>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript">
	google.charts.load('current', {packages: ['corechart', 'line']});
	google.charts.setOnLoadCallback(drawBasic);

	function drawBasic() {
		var data = new google.visualization.DataTable();
		data.addColumn('number', 'X');
		data.addColumn('number', '予測値');
		<%
		for (int i = 1; i < 13; i++) {
			if(shurui != null) {
				if(shurui.equals("気温")){
				%>
					data.addRows([[ <%= i %>,<%= prefClim[i].getTemperature() %>],]);
				<%
				}
				else if(shurui.equals("日照時間")){
				%>
					data.addRows([[ <%= i %>,<%= prefClim[i].getSunhour() %>],]);
				<%
				}
				else if(shurui.equals("降水量")){
				%>
					data.addRows([[ <%= i %>,<%= prefClim[i].getPrecipitation() %>],]);
				<%
				}
				else if(shurui.equals("降雪量")){
				%>
					data.addRows([[ <%= i %>,<%= prefClim[i].getSnowfall() %>],]);
				<%
				}
			}
		}
		%>
		var options = {hAxis: {title: '月'},vAxis: {title: '量'}};
		var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
		chart.draw(data, options);
	}
	</script>
</head>
<body>
	<h1><%= pref %>の気象予測</h1>
	<p>過去3年分のデータを基に算出した今年の気象予測データです</p>
	<p>表示したい項目のボタンをクリックしてください</p>

	<form action="/graph" method="get">
		<input type="hidden" name="pref" value="<%= pref %>">
		<input type="submit" name = "shurui" value="気温" />
		<input type="submit" name = "shurui" value="日照時間" />
		<input type="submit" name = "shurui" value="降水量" />
		<input type="submit" name = "shurui" value="降雪量" />
	</form>
	<div id="chart_div"></div>
	<div style="position:absolute;top:500;left:150px;">
		<form>
			<input type="button" value="戻る" onClick="history.back()">
		</form>
	</div>
</body>
</html>
