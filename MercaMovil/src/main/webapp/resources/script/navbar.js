document.addEventListener('DOMContentLoaded', function() {
    let currentPage = window.location.pathname;
    let usuarioIdElement = document.getElementById('usuarioId');
    let usuarioId = usuarioIdElement ? usuarioIdElement.textContent : null;

    let navLinks = document.querySelectorAll(".nav-link");
    navLinks.forEach(function(navLink) {
        let link = navLink.getAttribute("href");
        if (link === currentPage) {
            navLink.classList.add("active");
        } else {
            navLink.classList.add("inactive");
        }
    });

    if (usuarioId) {
        fetchCarrito(usuarioId);
    }

    // Evitar que el dropdown se cierre automáticamente
    const carritoDropdownContainer = document.querySelector('#carrito-dropdown-container-lg');
    carritoDropdownContainer.addEventListener('click', function(event) {
        event.stopPropagation();
    });
});

function fetchCarrito(usuarioId) {
    fetch(`/carrito/${usuarioId}`)
        .then(response => response.json())
        .then(data => {
            const carritoItemsContainer = document.getElementById('carrito-items-container');
            const carritoFooterContainer = document.getElementById('carrito-footer-container');
            carritoItemsContainer.innerHTML = '';
            carritoFooterContainer.innerHTML = '';

            if (data.productos.length > 0) {
                data.productos.forEach(producto => {
                    let precioFinal = producto.producto.price * producto.cantidad;
                    if (producto.producto.descuento && producto.cantidad >= producto.producto.descuento.cantidadMinima) {
                        precioFinal = precioFinal - (precioFinal * producto.producto.descuento.porcentaje / 100);
                    }
                    const itemLg = document.createElement('li');
                    itemLg.classList.add('dropdown-item');
                    itemLg.innerHTML = `
                        <div class="d-flex align-items-center">
                            <img src="/product-image/${producto.producto.id}" alt="${producto.producto.name}" style="width: 50px; height: 50px; margin-right: 10px;">
                            <div>
                                <span>${producto.producto.name}</span><br>
                                <span>Cantidad: ${producto.cantidad}</span><br>
                                <span>Precio: $${precioFinal.toFixed(2)}</span>
                            </div>
                        </div>
                    `;
                    carritoItemsContainer.appendChild(itemLg);
                });

                // Mostrar el total del carrito
                const totalItemLg = document.createElement('li');
                totalItemLg.classList.add('dropdown-item', 'text-end', 'fw-bold');
                totalItemLg.innerHTML = `Total: $${data.total.toFixed(2)}`;
                carritoFooterContainer.appendChild(totalItemLg);

                // Agregar botón de vaciar carrito
                const vaciarButtonLg = document.createElement('button');
                vaciarButtonLg.classList.add('btn', 'btn-danger', 'w-100');
                vaciarButtonLg.textContent = 'Vaciar Carrito';
                vaciarButtonLg.onclick = function() {
                    vaciarCarrito(data.id);
                };

                const walletContainerLg = document.createElement('div');
                walletContainerLg.id = 'wallet_container_lg';

                const buttonContainerLg = document.createElement('div');
                buttonContainerLg.classList.add('d-flex', 'justify-content-between', 'mt-2');
                buttonContainerLg.appendChild(vaciarButtonLg);

                carritoFooterContainer.appendChild(buttonContainerLg);
                carritoFooterContainer.appendChild(walletContainerLg);

                // Actualizar la notificación del carrito
                actualizarNotificacionCarrito(data.productos.length);

                // Crear botones de Mercado Pago
                createCheckoutButton(data.id);
            } else {
                const emptyMessageLg = document.createElement('li');
                emptyMessageLg.classList.add('dropdown-item');
                emptyMessageLg.textContent = 'El carrito está vacío';
                carritoItemsContainer.appendChild(emptyMessageLg);

                // Ocultar la notificación del carrito
                actualizarNotificacionCarrito(0);
            }
        })
        .catch(error => console.error('Error al obtener el carrito:', error));
}

function actualizarNotificacionCarrito(numeroProductos) {
    const badgeLg = document.getElementById('carrito-badge-lg');
    if (numeroProductos > 0) {
        badgeLg.textContent = numeroProductos > 99 ? '99+' : numeroProductos;
        badgeLg.style.display = 'inline';
    } else {
        badgeLg.style.display = 'none';
    }
}

function vaciarCarrito(carritoId) {
    const xhr = new XMLHttpRequest();
    xhr.open('DELETE', `/carrito/vaciar/${carritoId}`, true);
    xhr.onload = function() {
        if (xhr.status === 200) {
            fetchCarrito(document.getElementById('usuarioId').textContent);
        } else {
            alert('Error al vaciar el carrito');
        }
    };
    xhr.onerror = function() {
        console.error('Error al vaciar el carrito:', xhr.statusText);
    };
    xhr.send();
}

// LLAMAR A LA FUNCIÓN "NAVBAR STICKY"
window.onscroll = function () { scrollFunction(); };

function scrollFunction() {
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        document.getElementById("header-navbar").classList.add("scrolled");
    } else {
        document.getElementById("header-navbar").classList.remove("scrolled");
    }
}