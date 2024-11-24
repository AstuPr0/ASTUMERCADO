function aplicarFiltros() {
    const searchName = document.getElementById('searchName').value.toLowerCase();
    const filterCategory = document.getElementById('filterCategory').value;
    const filterDiscount = document.getElementById('filterDiscount').value;
    const sortOrder = document.getElementById('sortOrder').value || 'asc';

    const productos = document.querySelectorAll('#products-cards .card');

    productos.forEach(producto => {
        const nombre = producto.querySelector('.card-title').textContent.toLowerCase();
        const categoria = producto.querySelector('.categoria-nombre').textContent.trim();
        const tieneDescuento = producto.querySelector('.card-text').textContent.includes('%');

        let mostrar = true;

        // Filtrar por nombre
        if (searchName && !nombre.includes(searchName)) {
            mostrar = false;
        }

        // Filtrar por categoría
        if (filterCategory && filterCategory !== 'all' && categoria !== filterCategory) {
            mostrar = false;
        }

        // Filtrar por descuento
        if (filterDiscount === 'con-descuento' && !tieneDescuento) {
            mostrar = false;
        } else if (filterDiscount === 'sin-descuento' && tieneDescuento) {
            mostrar = false;
        }

        // Mostrar u ocultar el producto
        producto.style.display = mostrar ? 'block' : 'none';
    });

    // Ordenar productos
    if (sortOrder) {
        const sortedProductos = Array.from(productos).sort((a, b) => {
            const nombreA = a.querySelector('.card-title').textContent.toLowerCase();
            const nombreB = b.querySelector('.card-title').textContent.toLowerCase();

            if (sortOrder === 'asc') {
                return nombreA.localeCompare(nombreB);
            } else if (sortOrder === 'desc') {
                return nombreB.localeCompare(nombreA);
            }
        });

        const container = document.querySelector('#products-cards .card-container');
        container.innerHTML = '';
        sortedProductos.forEach(producto => container.appendChild(producto));
    }
}

document.addEventListener('DOMContentLoaded', aplicarFiltros);