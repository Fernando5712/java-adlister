<%--
  Created by IntelliJ IDEA.
  User: fernandoh
  Date: 7/17/20
  Time: 9:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ads</title>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Welcome to my site!" />
    </jsp:include>
</head>
<body>
<jsp:include page="../partials/navbar.jsp" />
<div class="container">
    <h1>Welcome to the Adlister!</h1>
    <c:forEach var="ad" items="${Ads}">
        <div class="list-group">
            <h2>${Ad.id}</h2>
        </div>
    </c:forEach>
</div>
</body>
</html>