<%
String pref = (String)request.getAttribute("prefName");
String yasai = (String) request.getAttribute("vegName");


%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="com.appspot.projectF.datastore.Crops" %>
<%@ page import="com.appspot.projectF.datastore.PMF" %>
<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="javax.jdo.Query" %>
<%@ page import="java.text.MessageFormat" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>index</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!--Load the AJAX API--> 
<script type="text/javascript" 
src="https://www.google.com/jsapi"></script> 
<script type="text/javascript">
google.load('visualization', '1.0', {'packages':['corechart']}); 
var data = new google.visualization.DataTable(); 
</script>

	<style type="text/css">
body{
background-color:silver;
}
</style>
</head>
<body>


<h1>作物詳細ページ</h1>
<FONT color="white">
<div style="border-style: solid; border-width:1px;width:200px;background-color:#008000;padding:10px 10px 10px 10px;" >
<%=yasai%>に適した気温条件
</div>
</FONT>
<div style="position:absolute;top:250;left:50px;">
<img src="<%=yasai %>.jpeg"width="250"height="250" >
</div>
<br>
<div style="border-style:solid; border-width:1px; width:200px; background-color:white; padding:10px 250px 200px 10px; position:absolute; top:150; left:350px;">
<FONT color="aqua">気温</FONT>
<% 
PersistenceManager pm = null;
       //DB開けなかったら，開けてもデータストアを閉じる
       try {
           pm = PMF.get().getPersistenceManager();
           Query query = pm.newQuery(Crops.class);

           List<Crops> crops = (List<Crops>) query.execute();
           request.setAttribute("crops", crops);
           int i=1;
              for(Crops sr:crops){ if(yasai.equals(sr.getName())){%><br> <br>　　<%=sr.getMonth()%>月の下限<%=sr.getTemp_lLimit()%>℃<br>　　　　　上限<%=sr.getTemp_uLimit()%>℃<%}} 
        
              } finally {
           if (pm != null && !pm.isClosed())
               pm.close();
       }%>
<br>
<br>
<br>
<FONT color="FF9900">日照</FONT>
<% 
PersistenceManager pm2 = null;
       //DB開けなかったら，開けてもデータストアを閉じる
       try {
           pm2 = PMF.get().getPersistenceManager();
           Query query = pm2.newQuery(Crops.class);

           List<Crops> crops = (List<Crops>) query.execute();
           request.setAttribute("crops", crops);
           int i=1;
              for(Crops sr:crops){ if(yasai.equals(sr.getName())){%><br> <br>　　<%=sr.getMonth()%>月の下限<%=sr.getSunhour_lLimit()%>時間<br>　　　　　上限<%=sr.getSunhour_uLimit()%>時間<%}} 
        
              } finally {
           if (pm2 != null && !pm2.isClosed())
               pm2.close();
       }%>
<br>
<br>
<br>
<FONT color="green">メモ</FONT>
<% 
PersistenceManager pm3 = null;
       //DB開けなかったら，開けてもデータストアを閉じる
       try {
           pm3 = PMF.get().getPersistenceManager();
           Query query = pm3.newQuery(Crops.class);

           List<Crops> crops = (List<Crops>) query.execute();
           request.setAttribute("crops", crops);
           int i=1;
              for(Crops sr:crops){ if(yasai.equals(sr.getName())){%><br><br>　　<%=sr.getMemo()%><%}} 
        
              } finally {
           if (pm3 != null && !pm3.isClosed())
               pm3.close();
       }%>
<br>
</div>
<div style="position:absolute;top:500;left:150px;">
<FORM>
<INPUT type="button" value="戻る" onClick="history.back()">
</FORM>
</div>
</body>
</html>