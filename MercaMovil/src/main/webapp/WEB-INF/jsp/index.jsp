<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AstuMercado</title>
    <%@ include file="views/modules/header.jsp" %>
    <link rel="stylesheet" href="<c:url value='/resources/styles/inicio.css' />"  type="text/css">
</head>

<body>
    <div class="navbar-index">
        <%@ include file="views/modules/navbar.jsp" %>
    </div>
    
    <div class="container-fluid w-100">
        <div id="carouselExampleCaptions" class="carousel slide carousel-index" data-bs-ride="carousel">
            <div class="carousel-indicators">
                <c:forEach var="producto" items="${topProductos}" varStatus="status">
                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="${status.index}" class="${status.index == 0 ? 'active' : ''}" aria-current="${status.index == 0 ? 'true' : 'false'}" aria-label="Slide ${status.index + 1}"></button>
                </c:forEach>
            </div>
            <div class="carousel-inner">
                <c:forEach var="producto" items="${topProductos}" varStatus="status">
                    <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                        <div class="row h-100">
                            <div class="col-6 d-flex align-items-center justify-content-center">
                                <img src="/product-image/${producto.id}" class="d-block img-fluid" alt="${producto.name}">
                            </div>
                            <div class="col-3 d-flex align-items-center justify-content-center">
                                <div class="text-center">
                                    <h5>${producto.name}</h5>
                                    <c:choose>
                                        <c:when test="${producto.descuento != null}">
                                            <p class="discount-message">${producto.descuento.nombre}</p>
                                            <p class="discount-message">Compra ${producto.descuento.cantidadMinima} productos y obten ${producto.descuento.porcentajeDescuento}% de Descuento</p>
                                        </c:when>
                                        <c:otherwise>
                                            <p>Producto frecuente</p>
                                        </c:otherwise>
                                    </c:choose>                                    
                                    <a href="/api/productos"class="btn button-custom-link">Comprar</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
    </div>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-6">
                <div class="info-item">
                    <i class="info-icon bi bi-truck"></i>
                    <p><fmt:message key="info.shipping" /></p>
                </div>
            </div>
            <div class="col-md-6">
                <div class="info-item">
                    <i class="info-icon bi bi-people"></i>
                    <p><fmt:message key="info.customerService" /></p>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="info-item">
                    <i class="info-icon bi bi-tags"></i>
                    <p><fmt:message key="info.offers" /></p>
                </div>
            </div>
            <div class="col-md-6">
                <div class="info-item">
                    <i class="info-icon bi bi-shield-lock"></i>
                    <p><fmt:message key="info.securePayment" /></p>
                </div>
            </div>
        </div>
    </div>
    
    <%@ include file="views/modules/footer.jsp" %>
</body>
</html>