<%@ page import="java.util.*,com.appspot.projectF.datastore.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
List<Climate> climates = (List<Climate>)request.getAttribute("climate");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>気候情報登録</title>
</head>
<body>
	<span style="font-size: 200%">気候情報登録</span>
	<p>都道府県名,年,月,気温,降水量,日照時間,降雪量</p>
	<p>の形式で入力してください．カンマは半角です．</p>
	<p>都道府県名は例えば「山梨県」など，後ろに都道府県をつけてください．</p>
	
	<form action="<%= request.getContextPath() %>/addclimate" method="post">
		<div>
			<textarea name="content" rows="2" cols="40"></textarea>
		</div>
		<input type="submit" value="Submit" />
	</form>
	<form action="/delclimate" method="post">
		<input type="submit" value="remove all" />
	</form>
	
	<h3>確認用出力</h3>
	<table>
		<tr>
			<th>都道府県名</th>
			<th>年</th>
			<th>月</th>
			<th>気温</th>
			<th>降水量</th>
			<th>日照時間</th>
			<th>降雪量</th>
		</tr>
		<% if (climates != null) for (Climate climate: climates) { %>
		<tr>
			<td><%= climate.getPrefectures() %></td>
			<td><%= climate.getYear() %></td>
			<td><%= climate.getMonth() %></td>
			<td><%= climate.getTemperature() %></td>
			<td><%= climate.getPrecipitation() %></td>
			<td><%= climate.getSunhour() %></td>
			<td><%= climate.getSnowfall() %></td>
		</tr>
		<% } %>
	</table>

</body>
</html>