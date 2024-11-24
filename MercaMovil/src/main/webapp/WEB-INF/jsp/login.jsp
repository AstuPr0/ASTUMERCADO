<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- ESTILOS LOCALES -->
    <link rel="stylesheet" href="<c:url value='/resources/styles/styles.css' />" type="text/css">
    <link rel="stylesheet" href="<c:url value='/resources/styles/login.css' />" type="text/css">
    <!-- SCRIPS LOCALES -->
    <script src="<c:url value='/resources/script/signup.js' />" type="text/javascript" defer></script>
    <%@ include file="views/modules/header.jsp" %>
    <title><fmt:message key="signup.title" /></title>
</head>

<body>
    <div class="container">
        <section class="row position-absolute top-50 start-50 translate-middle">
            <article class="col">
                <h2><fmt:message key="login.title" /></h2>
                <c:if test="${param.error != null}">
                    <div class="alert alert-danger">
                        <fmt:message key="login.error" />
                    </div>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div class="alert alert-success">
                        <fmt:message key="login.logout" />
                    </div>
                </c:if>
                <form action="/login" method="post">
                    <div class="form-group">
                        <label for="username"><fmt:message key="login.username" />:</label>
                        <input type="text" id="username" name="username" required>
                    </div>
                    <div class="form-group">
                        <label for="password"><fmt:message key="login.password" />:</label>
                        <input type="password" id="password" name="password" required>
                    </div>
                    <input type="submit" class="btn button-custom-link" value="<fmt:message key="login.submit" />">
                    <hr>
                </form>
                <div class="form-group">
                    <span><a href="/signup"><fmt:message key="signup.link" /></a></span>
                </div>
            </article>
        </section>
    </div>
</body>

</html>