// FUNCIÓM PARA CARGAR EL NAVBAR Y FOOTER EN TODAS LAS PÁGINAS
$(document).ready(function () {
    $('.navbarContainer').load('/views/modules/navbar.jsp');
    $('.footerContainer').load('/views/modules/footer.jsp');
});