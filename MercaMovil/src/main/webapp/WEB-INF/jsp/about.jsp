<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="about.title"/></title>
    <%@ include file="views/modules/header.jsp" %>
    <link rel="stylesheet" href="<c:url value='/resources/styles/about.css' />" type="text/css">
</head>
<body>
    <div>
        <%@ include file="views/modules/navbar.jsp" %>
    </div>
    <div class="container container-about">
        <div class="header-section">
            <h2><fmt:message key="about.header"/></h2>
            <p class="lead"><fmt:message key="about.intro"/></p>
        </div>
        <div class="row">
            <div class="col-md-4">
                <div id="list-example" class="list-group">
                    <a class="list-group-item list-group-item-action" href="#list-mission"><fmt:message key="about.mission"/></a>
                    <a class="list-group-item list-group-item-action" href="#list-vision"><fmt:message key="about.vision"/></a>
                    <a class="list-group-item list-group-item-action" href="#list-values"><fmt:message key="about.values"/></a>
                    <a class="list-group-item list-group-item-action" href="#list-goals"><fmt:message key="about.goals"/></a>
                </div>
            </div>
            <div class="col-md-8">
                <div data-bs-spy="scroll" data-bs-target="#list-example" data-bs-smooth-scroll="true" class="scrollspy-example" tabindex="0">
                    <h4 id="list-mission" class="section-title"><fmt:message key="about.mission"/></h4>
                    <p><fmt:message key="about.missionText"/></p>
                    
                    <h4 id="list-vision" class="section-title"><fmt:message key="about.vision"/></h4>
                    <p><fmt:message key="about.visionText"/></p>
                    
                    <h4 id="list-values" class="section-title"><fmt:message key="about.values"/></h4>
                    <p><fmt:message key="about.valuesText"/></p>
                    
                    <h4 id="list-goals" class="section-title"><fmt:message key="about.goals"/></h4>
                    <p><fmt:message key="about.goalsText"/></p>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="views/modules/footer.jsp" %>
</body>
</html>
