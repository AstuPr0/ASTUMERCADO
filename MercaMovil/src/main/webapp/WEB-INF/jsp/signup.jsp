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
                    <link rel="stylesheet" href="<c:url value='/resources/styles/signup.css' />" type="text/css">
                    <!-- SCRIPS LOCALES -->
                    <script src="<c:url value='/resources/script/signup.js' />" type="text/javascript" defer></script>
                    <%@ include file="views/modules/header.jsp" %>
                        <title>
                            <fmt:message key="signup.title" />
                        </title>
                </head>

                <body>
                    <div class="container-fluid mt-3 d-flex justify-content-center align-items-center">
                        <section class="row">
                            <article class="col">
                                <h2>
                                    <fmt:message key="signup.title" />
                                </h2>
                                <form action="/signup" method="post">
                                    <c:if test="${not empty errorMessage}">
                                        <div class="alert alert-danger">
                                            <fmt:message key="signup.errorMessage" />
                                        </div>
                                    </c:if>
                                    <div class="form-group">
                                        <label for="username">
                                            <fmt:message key="signup.username" />
                                        </label>
                                        <input type="text" class="form-control" id="username" name="username" required
                                            minlength="8" maxlength="20">
                                        <div class="invalid-feedback">
                                            <fmt:message key="signup.username.invalid" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="email">
                                            <fmt:message key="signup.email" />
                                        </label>
                                        <input type="email" class="form-control" id="email" name="email"
                                            pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" required>
                                        <div class="invalid-feedback">
                                            <fmt:message key="signup.email.invalid" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="password">
                                            <fmt:message key="signup.password" />
                                        </label>
                                        <input type="password" class="form-control" id="password" name="password"
                                            required minlength="8" data-bs-toggle="popover" data-bs-trigger="focus"
                                            data-bs-html="true" data-bs-content="
                            <ul>
                                <li><fmt:message key='signup.password.rule1' /></li>
                                <li><fmt:message key='signup.password.rule2' /></li>
                                <li><fmt:message key='signup.password.rule3' /></li>
                                <li><fmt:message key='signup.password.rule4' /></li>
                            </ul>
                        ">
                                    </div>
                                    <div id="passwordStrengthText" class="mt-2">
                                        <span id="veryWeakText" style="display: none;">
                                            <fmt:message key="passwordStrength.veryWeak" />
                                        </span>
                                        <span id="weakText" style="display: none;">
                                            <fmt:message key="passwordStrength.weak" />
                                        </span>
                                        <span id="strongText" style="display: none;">
                                            <fmt:message key="passwordStrength.strong" />
                                        </span>
                                        <span id="veryStrongText" style="display: none;">
                                            <fmt:message key="passwordStrength.veryStrong" />
                                        </span>
                                    </div>
                                    <div class="progress">
                                        <div id="passwordStrengthBar" class="progress-bar" role="progressbar"
                                            style="width: 0%;" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">
                                        </div>
                                    </div>
                                    <br>
                                    <input type="submit" id="registerButton" class="btn button-custom-link"
                                        value="<fmt:message key='signup.registerButton' />" disabled>
                                    <hr>
                                    <c:if test="${not empty successMessage}">
                                        <div class="alert alert-success">
                                            <fmt:message key="signup.successMessage" />
                                        </div>
                                    </c:if>
                                </form>
                                <div class="form-group">
                                    <span><a href="/login">
                                            <fmt:message key="signup.loginLink" />
                                        </a></span>
                                </div>
                            </article>
                        </section>
                    </div>
                </body>

                </html>