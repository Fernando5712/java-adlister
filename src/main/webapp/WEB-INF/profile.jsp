<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <div class="container">
        <h1>Welcome, ${sessionScope.user.username}!</h1>
        <br>
        <div class="container">
            <c:forEach var="ad" items="${ads}">
                <div class="col-md-6">
                    <div><h3>${ad.title}</h3></div>
                    <div><p>${ad.description}</p></div>
                </div>
                <form action="/ads/delete" method="post">
                    <input type="hidden" name="deleteAd" value="${ad.id}">
                    <input type="submit" value="Delete your ads" class="btn btn-danger stretched-link">
                </form>
            </c:forEach>
        </div>
    </div>

</body>
</html>
