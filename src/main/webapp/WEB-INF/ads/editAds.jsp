<%--
  Created by IntelliJ IDEA.
  User: fernandoh
  Date: 8/2/20
  Time: 2:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="&{ad.title}"/>
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>
<br>
<div class="container">
    <h1>Update your ad</h1>
    <form action="/ads/updateAds" method="post">
        <input type="hidden" name="updateAd" value="${ad.id}">
        <div class="form-group">
            <label for="title">Title</label>
            <input id="title" value="${ad.title}" name="title" class="form-control" type="text">
            <br>
            <input type="submit" name="updateTitle" value="Title Update" class="btn btn-success stretched-link">
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <textarea id="description" name="updatedDescription" class="form-control">${ad.description}</textarea>
            <br>
            <input type="submit" name="updatedDescriptionAd" class="btn btn-success stretched-link">
        </div>
    </form>
</div>

</body>
</html>
