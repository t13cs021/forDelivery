<%@page import="java.util.*,com.appspot.projectF.datastore.*"%>
<%@page language="java" contentType="text/html; charset=utf-8"%>
<%
List<Crops> crops = (List<Crops>)request.getAttribute("crops");
%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"> 
  <title>農作物登録用ページ</title>
</head>
<body>
  <span style="font-size: 200%">農作物登録</span>
  <p>農作物名,月,最低温度,最高温度,最短日照時間,最長日照時間,メモ</p>
  <p>の形式で入力してください．カンマは半角です．</p>
  <p>コメントなどのデータの中には半角カンマを含めないでください．全角カンマなら良いです．</p>


  <form action="<%= request.getContextPath() %>/addcrops" method="post">
    <div>
      <textarea name="content" rows="2" cols="40"></textarea>
    </div>
    <input type="submit" value="Submit" />
  </form>
  <form action="/delcrops" method="post">
    <input type="submit" value="remove all" />
  </form>

  <h3>確認用出力</h3>
  <table>
    <tr>
      <th>名前</th>
      <th>月</th>
      <th>最低温度</th>
      <th>最高温度</th>
      <th>最短日照時間</th>
      <th>最長日照時間</th>
      <th>メモ</th>
    </tr>
    <% if (crops != null) for (Crops crop: crops) { %>
    <tr>
      <td><%= crop.getName() %></td>
      <td><%= crop.getMonth() %></td>
      <td><%= crop.getTemp_lLimit() %></td>
      <td><%= crop.getTemp_uLimit() %></td>
      <td><%= crop.getSunhour_lLimit() %></td>
      <td><%= crop.getSunhour_uLimit() %></td>
      <td><%= crop.getMemo() %></td>
    </tr>
    <% } %>
  </table>

</body>
</html>