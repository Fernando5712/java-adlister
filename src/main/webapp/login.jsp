<%--
  Created by IntelliJ IDEA.
  User: fernandoh
  Date: 7/15/20
  Time: 11:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if(request.getMethod().equalsIgnoreCase("post")){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username.equals("admin") && password.equals("password")){
            response.sendRedirect("/profile");
        }
    }
%>
<html>
<head>
    <title>Profile</title>
    <jsp:include page="partials"
</head>
<body>

</body>
</html>
