<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

                <script src="<c:url value='/resources/script/navbar.js' />"></script>
                <script src="<c:url value='/resources/script/MercadoPago.js' />" type="text/javascript" defer></script>
                <script src="https://sdk.mercadopago.com/js/v2"></script>

                <!-- CONTAINER PARA ORGANIZAR NAVBAR -->
                <header class="container-fluid header-navbar" id="header-navbar">
                    <nav class="navbar navbar-expand-lg" id="header-navbar">
                        <!-- PRIMERA SECCIÓN: LOGO -->
                        <section class="col-md-2 col-sm-4 d-flex justify-content-center">
                            <a class="navbar-brand" href="/home">
                                <img src="<c:url value='/resources/img/2LOGO.png' />"
                                    alt="<fmt:message key='alt.logo'/>">
                            </a>
                        </section>
                        <!-- TOGGLE DE NAVBAR -->
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false"
                            aria-label="<fmt:message key='aria.toggle_navigation'/>">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <!-- SEGUNDA SECCIÓN: ELEMENTOS DE LA NAVBAR -->
                        <div class="col collapse navbar-collapse justify-content-center navbar-list"
                            id="navbarNavDropdown">
                            <ul class="navbar-nav">
                                <li class="nav-item">
                                    <a class="nav-link" href="/home" id="inicio">
                                        <fmt:message key="nav.home" />
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="/api/productos" id="anim-necesitados">
                                        <fmt:message key="nav.productos" />
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="/about" id="apadrinamiento">
                                        <fmt:message key="nav.misionvision" />
                                    </a>
                                </li>
                            </ul>
                            <c:choose>
                                <c:when test="${not empty usuario}">
                                    <!-- BOTÓN DE PERFIL DENTRO DE LA NAVBAR EN PANTALLAS PEQUEÑAS-->
                                    <section class="nav-item d-lg-none d-sm-block section-toggle">
                                        <a class="btn button-custom-link d-block btn-lg-small btn-toggle"
                                            href="/profile">
                                            <fmt:message key="button.perfil" />
                                        </a>
                                        <a class="btn button-custom-link d-block btn-lg-small btn-toggle"
                                            href="/logout">
                                            <fmt:message key="button.cerrar_sesion" />
                                        </a>
                                    </section>
                        </div>
                        <!-- SECCIÓN DEL CARRITO PARA PANTALLAS GRANDES -->
                        <section class="col-2 d-none d-lg-block text-center">
                            <div class="dropdown" id="carrito-dropdown-container-lg">
                                <button class="btn button-custom-link dropdown-toggle position-relative" type="button"
                                    data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="bi bi-cart"></i>
                                    <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger"
                                        id="carrito-badge-lg" style="display: none;">
                                        0
                                        <span class="visually-hidden">productos en el carrito</span>
                                    </span>
                                </button>
                                <ul class="dropdown-menu" id="carrito-dropdown-lg">
                                    <div id="carrito-items-container" style="max-height: 300px; overflow-y: auto;">
                                        <!-- Aquí se llenará el contenido del carrito -->
                                    </div>
                                    <div id="carrito-footer-container">
                                        <!-- Aquí se agregarán los botones de interacción -->
                                    </div>
                                </ul>
                            </div>
                        </section>
                        <div id="wallet_container_lg"></div>
                        <!-- CUARTA SECCIÓN: BOTÓN DE PERFIL Y CERRAR SESIÓN-->
                        <section class="col-2 d-none d-lg-block text-center">
                            <div class="dropdown">
                                <button class="btn button-custom-link dropdown-toggle" type="button"
                                    data-bs-toggle="dropdown" aria-expanded="false">
                                    ${usuario.username}
                                </button>
                                <p hidden id="usuarioId">${usuario.usuid}</p>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="/profile">
                                            <fmt:message key="button.perfil" />
                                        </a></li>
                                    <li><a class="dropdown-item" href="/logout">
                                            <fmt:message key="button.cerrar_sesion" />
                                        </a></li>
                                </ul>
                            </div>
                        </section>
                        </c:when>
                        <c:otherwise>
                            <!-- BOTÓN DE INICIAR SESIÓN DENTRO DE LA NAVBAR EN PANTALLAS PEQUEÑAS-->
                            <section class="nav-item d-lg-none d-sm-block section-toggle">
                                <a class="btn button-custom-link d-block btn-lg-small btn-toggle" href="/login">
                                    <fmt:message key="button.iniciar_sesion" />
                                </a>
                            </section>
                            </div>
                            <!-- TERCERA SECCIÓN: BOTÓN DE LOGIN Y SIGN UP-->
                            <section class="col-2 d-none d-lg-block text-center">
                                <div class="dropdown">
                                    <button class="btn button-custom-link dropdown-toggle" type="button"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                        <i class="bi bi-person-circle"></i>
                                    </button>
                                    <p hidden id="usuarioId">${usuario.usuid}</p>
                                    <ul class="dropdown-menu">
                                        <li><a class="dropdown-item" href="/login">
                                                <fmt:message key="button.login" />
                                            </a></li>
                                        <li><a class="dropdown-item" href="/signup">
                                                <fmt:message key="button.signup" />
                                            </a></li>
                                    </ul>
                                </div>
                            </section>
                        </c:otherwise>
                        </c:choose>
                        </div>
                    </nav>
                </header>