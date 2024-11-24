<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- ESTILOS LOCALES -->
    <link rel="stylesheet" href="<c:url value='/resources/styles/styles.css' />" type="text/css">
    <link rel="stylesheet" href="<c:url value='/resources/styles/card.css' />" type="text/css">
    <link rel="stylesheet" href="<c:url value='/resources/styles/navbar.css' />" type="text/css">
    <link rel="stylesheet" href="<c:url value='/resources/styles/element-space.css' />" type="text/css">
    <link rel="stylesheet" href="<c:url value='/resources/styles/footer.css' />" type="text/css">

    <!-- SCRIPS LOCALES -->
    <script src="<c:url value='/resources/script/footer.js' />" type="text/javascript" defer></script>
    <script src="<c:url value='/resources/script/fullscreen-img.js' />" type="text/javascript" defer></script>
    <script src="<c:url value='/resources/script/index.js' />" type="text/javascript" defer></script>
    <script src="<c:url value='/resources/script/display-cards.js' />" type="text/javascript" defer></script>
    <script src="<c:url value='/resources/script/products.js' />" type="text/javascript" defer></script>
    <script src="<c:url value='/resources/script/descuentos.js' />" type="text/javascript" defer></script>
    <script src="<c:url value='/resources/script/filter.js' />" type="text/javascript" defer></script>

    <%@ include file="views/modules/header.jsp" %>

    <title>
        <fmt:message key="page.profile.title" />
    </title>
</head>

<body>
    <!-- NAVBAR -->
    <%@ include file="views/modules/navbar.jsp" %>
    <main>
        <div class="container">
            <h1 class="mb-4"><fmt:message key="page.profile.title" /></h1>

            <div class="accordion" id="accordionExample">
                <div class="accordion-item">
                    <h2 class="accordion-header">
                        <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                            <fmt:message key="page.profile.invoices" />
                        </button>
                    </h2>
                    <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <table class="table table-striped">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>ID</th>
                                        <th><fmt:message key="page.profile.products" /></th>
                                        <th><fmt:message key="page.profile.quantity" /></th>
                                        <th><fmt:message key="page.profile.total" /></th>
                                        <th><fmt:message key="page.profile.date" /></th>
                                        <th><fmt:message key="page.profile.status" /></th>
                                        <th><fmt:message key="page.profile.paymentId" /></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="factura" items="${facturas}">
                                        <c:if test="${factura.usuario == usuario.username}">
                                            <tr class="<c:choose>
                                                <c:when test='${factura.estado == "failure"}'>table-danger</c:when>
                                                <c:when test='${factura.estado == "success"}'>table-success</c:when>
                                                <c:otherwise>table-light</c:otherwise>
                                            </c:choose>">
                                                <td>${factura.id}</td>
                                                <td>
                                                    <c:forEach var="producto" items="${factura.productos}">
                                                        ${producto.nombreProducto}<br />
                                                    </c:forEach>
                                                </td>
                                                <td>
                                                    <c:forEach var="producto" items="${factura.productos}">
                                                        ${producto.cantidad}<br />
                                                    </c:forEach>
                                                </td>
                                                <td>${factura.total}</td>
                                                <td>${factura.fecha}</td>
                                                <td>${factura.estado}</td>
                                                <td>${factura.paymentId}</td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <c:if test="${usuario.roles[0].rol_nombre == 'ROLE_ADMIN'}">
                    <div class="accordion-item">
                        <h2 class="accordion-header">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                <fmt:message key="page.profile.allInvoices" />
                            </button>
                        </h2>
                        <div id="collapseTwo" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
                            <div class="accordion-body">
                                <table class="table table-striped">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th>ID</th>
                                            <th><fmt:message key="page.profile.user" /></th>
                                            <th><fmt:message key="page.profile.products" /></th>
                                            <th><fmt:message key="page.profile.total" /></th>
                                            <th><fmt:message key="page.profile.date" /></th>
                                            <th><fmt:message key="page.profile.status" /></th>
                                            <th><fmt:message key="page.profile.paymentId" /></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="factura" items="${facturas}">
                                            <tr class="<c:choose>
                                                <c:when test='${factura.estado == "failure"}'>table-danger</c:when>
                                                <c:when test='${factura.estado == "success"}'>table-success</c:when>
                                                <c:otherwise>table-light</c:otherwise>
                                            </c:choose>">
                                                <td>${factura.id}</td>
                                                <td>${factura.usuario}</td>
                                                <td>
                                                    <c:forEach var="producto" items="${factura.productos}">
                                                        ${producto.nombreProducto}<br />
                                                    </c:forEach>
                                                </td>
                                                <td>${factura.total}</td>
                                                <td>${factura.fecha}</td>
                                                <td>${factura.estado}</td>
                                                <td>${factura.paymentId}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div class="accordion-item">
                        <h2 class="accordion-header">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                <fmt:message key="page.profile.allCarts" />
                            </button>
                        </h2>
                        <div id="collapseThree" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
                            <div class="accordion-body">
                                <table class="table table-striped">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th><fmt:message key="page.profile.cartId" /></th>
                                            <th><fmt:message key="page.profile.user" /></th>
                                            <th><fmt:message key="page.profile.products" /></th>
                                            <th><fmt:message key="page.profile.quantity" /></th>
                                            <th><fmt:message key="page.profile.total" /></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="carrito" items="${carritos}">
                                            <tr>
                                                <td>${carrito.id}</td>
                                                <td>${carrito.usuario.username}</td>
                                                <td>
                                                    <c:forEach var="producto" items="${carrito.productos}">
                                                        ${producto.producto.name}<br />
                                                    </c:forEach>
                                                </td>
                                                <td>
                                                    <c:forEach var="producto" items="${carrito.productos}">
                                                        ${producto.cantidad}<br />
                                                    </c:forEach>
                                                </td>
                                                <td>${carrito.total}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </main>
</body>

</html>