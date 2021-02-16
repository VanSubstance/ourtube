<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=euc-kr"/>
    <title>Home</title>
</head>

<body>
<h1>Project List</h1>

<table border="1">
<thead>
  <tr>
    <th>카테고리1</th>
    <th>카테고리2</th>
    <th>키워드</th>
  </tr>
</thead>
<tbody>
<c:forEach var="item" items="${list}">
  <tr>
    <td>${item.b_cat}</td>
    <td>${item.c_cat}</td>
    <td>${item.keyword}</td>
  </tr>
</c:forEach>
</tbody>
</table>
</body>
</html>