<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/ads">AdLister</a>
        </div>
        <ul class="nav navbar-nav navbvar-right">
            <c:choose>
                <c:when test="${sessionScope['user'] != null}">
                    <li><a href="/logout">Logout</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="/login">Login</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>
