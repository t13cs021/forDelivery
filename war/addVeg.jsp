<%@page import="java.util.*,com.appspot.projectF.servlet.*"%>
<%
List<Crops> crops = (List<Crops>)request.getAttribute("crops");
%>
<html>
<head>
  <title>登録用ページ</title>
</head>
<body>
  <span style="font-size: 200%">農作物登録</span>
  <p>農作物名,月,最低温度,最高温度,最短日照時間,最長日照時間,メモ</p>
  <p>の形式で入力してください(従わないと大変なことになりますよ)</p>
  <p>カンマは半角です</p>
  <p>とりあえず簡易版なので，データにカンマを含めないでください(区切りだと勘違いします)</p>


  <form action="<%= request.getContextPath() %>/AddVeg" method="post">
    <div>
      <textarea name="content" rows="2" cols="40"></textarea>
    </div>
    <input type="submit" value="Submit" />
  </form>
  <form action="/DelCrops" method="post">
    <input type="submit" value="Submit" />
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
    <tr><% System.out.println(crop.getMemo()); %>
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