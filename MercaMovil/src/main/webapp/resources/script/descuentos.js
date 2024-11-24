document.addEventListener('DOMContentLoaded', function() {
    cargarDescuentos();
});

function cargarDescuentos() {
    const xhr = new XMLHttpRequest();
    xhr.open('GET', '/api/descuentos', true);
    xhr.onload = function() {
        if (xhr.status === 200) {
            const descuentos = JSON.parse(xhr.responseText);
            const discountsList = document.getElementById('discountsList');
            discountsList.innerHTML = '';
            descuentos.forEach(descuento => {
                const listItem = document.createElement('li');
                listItem.classList.add('list-group-item', 'd-flex', 'justify-content-between', 'align-items-center');
                listItem.innerHTML = `
                    <span>${descuento.nombre} - ${descuento.porcentaje}% (Min. ${descuento.cantidadMinima})</span>
                    <div>
                        <button class="btn btn-warning btn-sm me-2" onclick="mostrarFormularioActualizar(${descuento.id})">Actualizar</button>
                        <button class="btn btn-danger btn-sm" onclick="confirmarEliminarDescuento(${descuento.id})">Eliminar</button>
                    </div>
                `;
                discountsList.appendChild(listItem);
            });
        } else {
            alert('Error al cargar los descuentos');
        }
    };
    xhr.onerror = function() {
        console.error('Error al cargar los descuentos:', xhr.statusText);
    };
    xhr.send();
}

function mostrarFormularioCrear() {
    document.getElementById('discountForm').style.display = 'block';
    document.getElementById('discountId').value = '';
    document.getElementById('discountName').value = '';
    document.getElementById('discountPercentage').value = '';
    document.getElementById('discountMinQuantity').value = '';
}

function mostrarFormularioActualizar(descuentoId) {
    const xhr = new XMLHttpRequest();
    xhr.open('GET', `/api/descuentos/${descuentoId}`, true);
    xhr.onload = function() {
        if (xhr.status === 200) {
            const descuento = JSON.parse(xhr.responseText);
            document.getElementById('discountForm').style.display = 'block';
            document.getElementById('discountId').value = descuento.id;
            document.getElementById('discountName').value = descuento.nombre;
            document.getElementById('discountPercentage').value = descuento.porcentaje;
            document.getElementById('discountMinQuantity').value = descuento.cantidadMinima;
        } else {
            alert('Error al cargar el descuento');
        }
    };
    xhr.onerror = function() {
        console.error('Error al cargar el descuento:', xhr.statusText);
    };
    xhr.send();
}

function ocultarFormulario() {
    document.getElementById('discountForm').style.display = 'none';
}

function guardarDescuento() {
    const descuentoId = document.getElementById('discountId').value;
    const nombre = document.getElementById('discountName').value;
    const porcentaje = document.getElementById('discountPercentage').value;
    const cantidadMinima = document.getElementById('discountMinQuantity').value;

    const descuento = {
        nombre: nombre,
        porcentaje: porcentaje,
        cantidadMinima: cantidadMinima
    };

    const xhr = new XMLHttpRequest();
    if (descuentoId) {
        // Actualizar descuento
        xhr.open('PUT', `/api/descuentos/${descuentoId}`, true);
    } else {
        // Crear nuevo descuento
        xhr.open('POST', '/api/descuentos', true);
    }
    xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    xhr.onload = function() {
        if (xhr.status === 200 || xhr.status === 201) {
            alert('Descuento guardado exitosamente');
            ocultarFormulario();
            cargarDescuentos();
        } else {
            alert('Error al guardar el descuento');
        }
    };
    xhr.onerror = function() {
        console.error('Error al guardar el descuento:', xhr.statusText);
    };
    xhr.send(JSON.stringify(descuento));
}

function confirmarEliminarDescuento(descuentoId) {
    const confirmacion = confirm("¿Está seguro de eliminar el descuento?");
    if (confirmacion) {
        eliminarDescuento(descuentoId);
    }
}

function eliminarDescuento(descuentoId) {
    const xhr = new XMLHttpRequest();
    xhr.open('DELETE', `/api/descuentos/${descuentoId}`, true);
    xhr.onload = function() {
        if (xhr.status === 200) {
            alert('Descuento eliminado exitosamente');
            cargarDescuentos();
        } else {
            alert('Error al eliminar el descuento');
        }
    };
    xhr.onerror = function() {
        console.error('Error al eliminar el descuento:', xhr.statusText);
    };
    xhr.send();
}