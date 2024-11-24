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
                    <link rel="stylesheet" href="<c:url value='/resources/styles/card.css' />" type="text/css">
                    <link rel="stylesheet" href="<c:url value='/resources/styles/element-space.css' />" type="text/css">

                    <!-- SCRIPS LOCALES -->
                    <script src="<c:url value='/resources/script/fullscreen-img.js' />" type="text/javascript"
                        defer></script>
                    <script src="<c:url value='/resources/script/display-cards.js' />" type="text/javascript"
                        defer></script>
                    <script src="<c:url value='/resources/script/products.js' />" type="text/javascript" defer></script>
                    <script src="<c:url value='/resources/script/descuentos.js' />" type="text/javascript"
                        defer></script>
                    <script src="<c:url value='/resources/script/filter.js' />" type="text/javascript" defer></script>

                    <%@ include file="views/modules/header.jsp" %>

                        <title>
                            <fmt:message key="page.title" />
                        </title>
                </head>

                <body>
                    <!-- NAVBAR -->
                    <%@ include file="views/modules/navbar.jsp" %>

                        <main>
                            <!-- PRIMERA PARTE -->
                            <div class="mid-screen-section">
                                <!-- IMAGEN QUE EN PANTALLAS PEQUEÑAS ES EL FUNDO DEL APARTADO -->
                                <div class="d-lg-none d-sm-block section-toggle img-toggle-productos">
                                    <article
                                        class="container-fluid text-center d-flex justify-content-center align-items-center mid-screen-section">
                                        <section class="row">
                                            <!-- TEXTO DE LA PRIEMRA PARTE EN PANTALLAS PEQUEÑAS -->
                                            <div class="col text-contrast">
                                                <div class="centered-element">
                                                    <h1 class="principal-title">
                                                        <fmt:message key="page.section1.title" />
                                                    </h1>
                                                    <p class="principal-text">
                                                        <fmt:message key="page.section1.description" />
                                                    </p>
                                                </div>
                                            </div>
                                        </section>
                                    </article>

                                </div>
                                <!-- CONTENIDO DE LA PRIMERA PARTE -->
                                <article
                                    class="container-fluid text-center d-flex justify-content-center align-items-center mid-screen-section d-lg-block d-sm-none">
                                    <section class="row">
                                        <!-- TEXTO DE LA PRIEMRA PARTE -->
                                        <div class="col">
                                            <div class="centered-element">
                                                <h1 class="principal-title">
                                                    <fmt:message key="page.section1.title" />
                                                </h1>
                                                <p class="principal-text">
                                                    <fmt:message key="page.section1.description" />
                                                </p>
                                            </div>
                                        </div>
                                        <!-- IMAGEN BARNER DE LA PAGINA -->
                                        <div class="col">
                                            <img src="<c:url value='/resources/img/LOGO4.png' />"
                                                class="rounded img-fluid styled-image"
                                                alt="<fmt:message key='img.alt.banner' />">
                                        </div>
                                    </section>
                                </article>
                            </div>

                            <!-- SEGUNDA PARTE -->
                            <div class="elements">
                                <!-- TITULO DE LA SEGUNDA PARTE -->
                                <h2 class="title">
                                    <fmt:message key="page.section2.title" />
                                </h2>
                                <br>
                                <!-- BOTÓN PARA CREAR PRODUCTO (SOLO VISIBLE PARA ADMINISTRADORES) -->
                                <c:if test="${usuario.roles[0].rol_nombre == 'ROLE_ADMIN'}">
                                    <div class="grid gap-3 ms-4">
                                        <button type="button" class="btn btn-primary mb-3" data-bs-toggle="modal"
                                            data-bs-target="#createProductModal">
                                            <fmt:message key="button.create.product" />
                                        </button>
                                        <button type="button" class="btn btn-primary mb-3" data-bs-toggle="modal"
                                            data-bs-target="#manageDiscountsModal" onclick="cargarDescuentos()">
                                            <fmt:message key="button.manage.discounts" />
                                        </button>
                                    </div>
                                </c:if>
                                <!-- MODAL PARA ACTUALIZAR PRODUCTO -->
                                <div class="modal fade" id="updateProductModal" tabindex="-1"
                                    aria-labelledby="updateProductModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="updateProductModalLabel">
                                                    <fmt:message key="modal.update.product.title" />
                                                </h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <form id="updateProductForm">
                                                    <input type="hidden" id="updateProductId">
                                                    <div class="mb-3">
                                                        <label for="updateProductImage" class="form-label">
                                                            <fmt:message key="label.product.image" />
                                                        </label>
                                                        <input type="file" class="form-control" id="updateProductImage"
                                                            accept="image/*">
                                                        <img id="updateProductImagePreview" src="#"
                                                            class="card-img-top img-scale"
                                                            alt="Previsualización de la imagen"
                                                            style="display: none; width: 286px; height: 161px; margin-left: auto; margin-right: auto; padding-top: 10px;">
                                                    </div>
                                                    <div class="mb-3">
                                                        <label for="updateProductName" class="form-label">
                                                            <fmt:message key="label.product.name" />
                                                        </label>
                                                        <input type="text" class="form-control" id="updateProductName"
                                                            name="productName" required>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label for="updateProductCategory" class="form-label">
                                                            <fmt:message key="label.product.category" />
                                                        </label>
                                                        <select class="form-control" id="updateProductCategory"
                                                            name="productCategory" required>
                                                            <option value="">
                                                                <fmt:message key="option.all.categories" />
                                                            </option>
                                                            <c:forEach var="categoria" items="${categorias}">
                                                                <option value="${categoria.id}">${categoria.nombre}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label for="updateProductPrice" class="form-label">
                                                            <fmt:message key="label.product.price" />
                                                        </label>
                                                        <input type="number" class="form-control"
                                                            id="updateProductPrice" name="productPrice" required>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label for="updateProductDiscount" class="form-label">
                                                            <fmt:message key="label.product.discount" />
                                                        </label>
                                                        <select class="form-control" id="updateProductDiscount"
                                                            name="productDiscount">
                                                            <option value="">
                                                                <fmt:message key="option.all" />
                                                            </option>
                                                            <c:forEach var="descuento" items="${descuentos}">
                                                                <option value="${descuento.id}">${descuento.nombre}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                                    <fmt:message key="button.close" />
                                                </button>
                                                <button type="button" class="btn btn-primary" onclick="updateProduct()">
                                                    <fmt:message key="button.save.changes" />
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- MODAL PARA CREAR PRODUCTO -->
                                <div class="modal fade" id="createProductModal" tabindex="-1"
                                    aria-labelledby="createProductModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="createProductModalLabel">
                                                    <fmt:message key="modal.create.product.title" />
                                                </h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <form id="createProductForm">
                                                    <div class="mb-3">
                                                        <label for="productImage" class="form-label">
                                                            <fmt:message key="label.product.image" />
                                                        </label>
                                                        <input type="file" class="form-control" id="productImage"
                                                            accept="image/*">
                                                        <img id="productImagePreview" src="#"
                                                            class="card-img-top img-scale"
                                                            alt="Previsualización de la imagen"
                                                            style="display: none; width: 286px; height: 161px; margin-left: auto; margin-right: auto; padding-top: 10px;">
                                                    </div>
                                                    <div class="mb-3">
                                                        <label for="productName" class="form-label">
                                                            <fmt:message key="label.product.name" />
                                                        </label>
                                                        <input type="text" class="form-control" id="productName"
                                                            name="productName" required>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label for="productCategory" class="form-label">
                                                            <fmt:message key="label.product.category" />
                                                        </label>
                                                        <select class="form-control" id="productCategory"
                                                            name="productCategory" required>
                                                            <option value="">
                                                                <fmt:message key="option.all.categories" />
                                                            </option>
                                                            <c:forEach var="categoria" items="${categorias}">
                                                                <option value="${categoria.id}">${categoria.nombre}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label for="productPrice" class="form-label">
                                                            <fmt:message key="label.product.price" />
                                                        </label>
                                                        <input type="number" class="form-control" id="productPrice"
                                                            name="productPrice" required>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label for="productDiscount" class="form-label">
                                                            <fmt:message key="label.product.discount" />
                                                        </label>
                                                        <select class="form-control" id="productDiscount"
                                                            name="productDiscount">
                                                            <option value="">
                                                                <fmt:message key="option.all" />
                                                            </option>
                                                            <c:forEach var="descuento" items="${descuentos}">
                                                                <option value="${descuento.id}">${descuento.nombre}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                                    <fmt:message key="button.close" />
                                                </button>
                                                <button type="button" class="btn btn-primary" onclick="saveProduct()">
                                                    <fmt:message key="button.save.changes" />
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- MODAL PARA GESTIONAR DESCUENTOS -->
                                <div class="modal fade" id="manageDiscountsModal" tabindex="-1"
                                    aria-labelledby="manageDiscountsModalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="manageDiscountsModalLabel">
                                                    <fmt:message key="modal.manage.discounts.title" />
                                                </h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <!-- Botón para crear un nuevo descuento -->
                                                <button class="btn btn-primary mb-3" onclick="mostrarFormularioCrear()">
                                                    <fmt:message key="button.create.discount" />
                                                </button>

                                                <!-- Lista de descuentos -->
                                                <ul id="discountsList" class="list-group mb-3">
                                                    <!-- Aquí se listarán los descuentos -->
                                                </ul>

                                                <!-- Formulario para crear/actualizar descuento -->
                                                <form id="discountForm" style="display: none;">
                                                    <input type="hidden" id="discountId">
                                                    <div class="mb-3">
                                                        <label for="discountName" class="form-label">
                                                            <fmt:message key="label.discount.name" />
                                                        </label>
                                                        <input type="text" class="form-control" id="discountName"
                                                            name="discountName" required>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label for="discountPercentage" class="form-label">
                                                            <fmt:message key="label.discount.percentage" />
                                                        </label>
                                                        <input type="number" class="form-control"
                                                            id="discountPercentage" name="discountPercentage" required>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label for="discountMinQuantity" class="form-label">
                                                            <fmt:message key="label.discount.min.quantity" />
                                                        </label>
                                                        <input type="number" class="form-control"
                                                            id="discountMinQuantity" name="discountMinQuantity" required>
                                                    </div>
                                                    <button type="button" class="btn btn-secondary"
                                                        onclick="ocultarFormulario()">
                                                        <fmt:message key="button.cancel" />
                                                    </button>
                                                    <button type="button" class="btn btn-primary"
                                                        onclick="guardarDescuento()">
                                                        <fmt:message key="button.save" />
                                                    </button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <article id="products-cards" class="container-fluid text-center d-flex flex-wrap">
                                    <!-- ASIDE PARA FILTROS -->
                                    <aside class="aside-filters col-lg-3 col-md-4 mb-4">
                                        <div class="aside-header">
                                            <h5>
                                                <fmt:message key="label.filters" />
                                            </h5>
                                        </div>
                                        <div class="aside-body">
                                            <!-- Buscar por nombre -->
                                            <div class="mb-3">
                                                <label for="searchName" class="form-label">
                                                    <fmt:message key="label.search.name" />
                                                </label>
                                                <input type="text" class="form-control" id="searchName"
                                                    placeholder="<fmt:message key='label.search.name' />"
                                                    onchange="aplicarFiltros()">
                                            </div>
                                            <!-- Filtrar por categoría -->
                                            <div class="mb-3">
                                                <label for="filterCategory" class="form-label">
                                                    <fmt:message key="label.filter.category" />
                                                </label>
                                                <select class="form-control" id="filterCategory"
                                                    onchange="aplicarFiltros()">
                                                    <option value="">
                                                        <fmt:message key="option.all.categories" />
                                                    </option>
                                                    <c:forEach var="categoria" items="${categorias}">
                                                        <option value="${categoria.nombre}">${categoria.nombre}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <!-- Filtrar por descuento -->
                                            <div class="mb-3">
                                                <label for="filterDiscount" class="form-label">
                                                    <fmt:message key="label.filter.discount" />
                                                </label>
                                                <select class="form-control" id="filterDiscount"
                                                    onchange="aplicarFiltros()">
                                                    <option value="">
                                                        <fmt:message key="option.all" />
                                                    </option>
                                                    <option value="con-descuento">
                                                        <fmt:message key="option.with.discount" />
                                                    </option>
                                                    <option value="sin-descuento">
                                                        <fmt:message key="option.without.discount" />
                                                    </option>
                                                </select>
                                            </div>
                                            <!-- Ordenar alfabéticamente -->
                                            <div class="mb-3">
                                                <label for="sortOrder" class="form-label">
                                                    <fmt:message key="label.sort.order" />
                                                </label>
                                                <select class="form-control" id="sortOrder" onchange="aplicarFiltros()">
                                                    <option value="asc">
                                                        <fmt:message key="option.asc" />
                                                    </option>
                                                    <option value="desc">
                                                        <fmt:message key="option.desc" />
                                                    </option>
                                                </select>
                                            </div>
                                        </div>
                                    </aside>

                                    <!-- SECCIÓN DE TARJETAS DE PRODUCTOS -->
                                    <section class="col row card-container">
                                        <!-- TARJETAS DE PRODUCTOS -->
                                        <c:forEach var="producto" items="${productos}">
                                            <c:if test="${producto.estado == true}">
                                                <div class="card">
                                                    <img src="/product-image/${producto.id}"
                                                        class="card-img-top img-scale"
                                                        alt="<fmt:message key='img.alt.product' /> ${producto.name}">
                                                    <div class="card-body">
                                                        <p class="text-body-secondary categoria-nombre fs-6">
                                                            ${producto.categoria.nombre}
                                                        </p>
                                                        <h5 class="card-title">${producto.name}</h5>
                                                        <c:if test="${producto.descuento != null}">
                                                            <p class="discount-message">${producto.descuento.nombre}</p>
                                                            <p>Compra ${producto.descuento.cantidadMinima} a ${producto.descuento.porcentaje}% de Descuento</p>
                                                        </c:if>
                                                        <p class="card-text">
                                                            <span id="precio-original-${producto.id}"
                                                                class="precio-original">$ ${producto.price}</span>
                                                            <span id="precio-descuento-${producto.id}"
                                                                class="precio-descuento"></span>
                                                        </p>
                                                        <div class="d-flex justify-content-center align-items-center">
                                                            <button class="btn btn-secondary"
                                                                onclick="decrementar(${producto.id}, ${producto.price}, ${producto.descuento != null ? producto.descuento.porcentaje : 0}, ${producto.descuento != null ? producto.descuento.cantidadMinima : 0})">-</button>
                                                            <input type="text" id="contador-${producto.id}" value="1"
                                                                class="form-control text-center mx-2"
                                                                style="width: 50px;" readonly>
                                                            <button class="btn btn-secondary"
                                                                onclick="incrementar(${producto.id}, ${producto.price}, ${producto.descuento != null ? producto.descuento.porcentaje : 0}, ${producto.descuento != null ? producto.descuento.cantidadMinima : 0})">+</button>
                                                        </div>
                                                        <div class="btn-group mt-3" role="group"
                                                            aria-label="Acciones de producto">
                                                            <button class="btn btn-primary"
                                                                onclick="añadirAlCarrito(${producto.id})">
                                                                <fmt:message key="button.add.to.cart" />
                                                            </button>
                                                            <!-- BOTONES DE ACTUALIZAR Y ELIMINAR (SOLO VISIBLES PARA ADMINISTRADORES) -->
                                                            <c:if test="${usuario.roles[0].rol_nombre == 'ROLE_ADMIN'}">
                                                                <button class="btn btn-warning"
                                                                    onclick="abrirModalActualizar(${producto.id})"><i
                                                                        class="bi bi-arrow-clockwise"></i></button>
                                                                <button class="btn btn-danger"
                                                                    onclick="confirmarEliminarProducto(${producto.id})"><i
                                                                        class="bi bi-trash"></i></button>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                        <section class="row d-flex justify-content-center align-items-center">
                                            <button class="btn button-custom-link col-2" id="ver-mas">
                                                <fmt:message key="button.see.more" />
                                            </button>
                                        </section>
                                    </section>
                                </article>
                                <br>
                            </div>
                        </main>


                        <!-- FOOTER -->
                        <div>
                            <%@ include file="views/modules/footer.jsp" %>
                        </div>

                </body>

                </html>