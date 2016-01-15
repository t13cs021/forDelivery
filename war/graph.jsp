<%
String pref = (String)request.getAttribute("prefName");
String shurui = request.getParameter("shurui");
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.appspot.projectF.datastore.Climate" %>
<%@ page import="com.appspot.projectF.datastore.PMF" %>
<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="javax.jdo.Query" %>
<%@ page import="java.text.MessageFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="com.appspot.projectF.util.*" %>
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
	    	 PersistenceManager pm = null;
	        // 気象データ予測関数を使うためのアレ
	        Forecast forecast = new Forecast();
	        // 予測関数で返ってきた配列をいれるやつ
	        Climate [] prefClim = forecast.getForecast(pref);

	        for (int i = 1; i < 13; i++) {
	        	if(shurui != null) { 
	        		if(shurui.equals("気温")){
	    	   %>  	  
	    	   data.addRows([
	    	                 [ <%= i %>,<%= prefClim[i].getTemperature() %>],
	    		]);
	 	    	
	    	  <%
	    	  }
	        		if(shurui.equals("日照時間")){
	 	    	   %>  	  
	 	    	   data.addRows([
	 	    	                 [ <%= i %>,<%= prefClim[i].getSunhour() %>],
	 	    	                 ]);
	 	    	  <%
	 	    	  }
	        		if(shurui.equals("降水量")){
		 	    	   %>
		 	    	   data.addRows([
		 	    	                 [ <%= i %>,<%= prefClim[i].getPrecipitation() %>],
		 	    	                 ]);
		 	    	  <%
		 	 	    	}
	        		if(shurui.equals("降雪量")){
		 	    	   %>  	  
		 	    	   data.addRows([
		 	    	                 [ <%= i %>,<%= prefClim[i].getSnowfall() %>],
		 	    	                 ]);
		 	    	  <%
		 	    	  }
	        		}
	        	}
	    	  %>
	        var options = {
	          hAxis: {
	            title: '月'
	          },
	          vAxis: {
	            title: '量'
	          }
	        };

	        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
	        chart.draw(data, options);
	      }
	  </script>
</head>
<body>
<h1>気象予測</h1>
<p>過去3年分のデータを基に算出した今年の気象予測データです</p>
<p>表示したい項目のボタンをクリックしてください</p>

 <form action="/graph" method="get">
 <INPUT TYPE="HIDDEN" NAME="pref" VALUE=<%= pref %>>
 	<input type="submit" name = "shurui" value="気温" />
 	<input type="submit" name = "shurui" value="日照時間" />
 	<input type="submit" name = "shurui" value="降水量" />
 	<input type="submit" name = "shurui" value="降雪量" />
 </form>
<div id="chart_div"></div>
<div style="position:absolute;top:500;left:150px;">
<FORM>
<INPUT type="button" value="戻る" onClick="history.back()">
</FORM>
</div>
</body>
</html>