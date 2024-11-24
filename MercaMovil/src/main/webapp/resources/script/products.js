function añadirAlCarrito(productoId, precio) {
  const usuarioId = document.getElementById('usuarioId').textContent;
  const cantidad = document.getElementById(`contador-${productoId}`).value;

  if (!usuarioId) {
    window.location.href = '/login';
  }

  const producto = {
    producto: { id: productoId },
    cantidad: parseInt(cantidad)
  };

  const carritoDTO = {
    usuario: { id: usuarioId },
    productos: [producto],
    estado: "activo"
  };

  const xhr = new XMLHttpRequest();
  xhr.open('POST', '/carrito/add', true);
  xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
  xhr.onload = function() {
    if (xhr.status === 200) {
      fetchCarrito(usuarioId);
    } else {
      alert('Error al añadir el producto al carrito');
    }
  };
  xhr.onerror = function() {
    console.error('Error al añadir el producto al carrito:', xhr.statusText);
  };
  xhr.send(JSON.stringify(carritoDTO));
}

function incrementar(id, precio, descuento, cantidadMinima) {
    var contador = document.getElementById('contador-' + id);
    contador.value = parseInt(contador.value) + 1;
    actualizarPrecio(id, precio, descuento, cantidadMinima, contador.value);
}

function decrementar(id, precio, descuento, cantidadMinima) {
    var contador = document.getElementById('contador-' + id);
    if (contador.value > 1) {
        contador.value = parseInt(contador.value) - 1;
        actualizarPrecio(id, precio, descuento, cantidadMinima, contador.value);
    }
}

function actualizarPrecio(id, precio, descuento, cantidadMinima, cantidad) {
    var precioOriginal = document.getElementById('precio-original-' + id);
    var precioDescuento = document.getElementById('precio-descuento-' + id);

    var precioTotal = precio * cantidad;

    if (cantidad >= cantidadMinima && descuento > 0) {
        var nuevoPrecio = precioTotal * (1 - descuento / 100);
        precioOriginal.style.textDecoration = "line-through";
        precioOriginal.style.fontSize = "small";
        precioDescuento.innerHTML = "$ " + nuevoPrecio.toFixed(2);
    } else {
        precioOriginal.style.textDecoration = "none";
        precioOriginal.style.fontSize = "inherit";
        precioDescuento.innerHTML = "";
    }

    precioOriginal.innerHTML = "$ " + precioTotal.toFixed(2);
}

function saveProduct() {
    const form = document.getElementById('createProductForm');
    const formData = new FormData(form);

    const nombre = formData.get('productName');
    const categoriaId = formData.get('productCategory');
    const precio = parseFloat(formData.get('productPrice'));
    const descuentoId = formData.get('productDiscount') ? formData.get('productDiscount') : null;
    const imageFile = document.getElementById('productImage').files[0]; // Obtener el archivo de imagen directamente
    const estado = true;

    // Validar que los campos obligatorios no sean nulos o vacíos
    if (!nombre || !categoriaId || isNaN(precio)) {
        alert('Por favor, completa todos los campos obligatorios.');
        return;
    }

    // Validar que se haya seleccionado una imagen
    if (!imageFile) {
        alert('Por favor, selecciona una imagen.');
        return;
    }

    // Leer la imagen como base64
    const reader = new FileReader();
    reader.onloadend = function() {
        const base64Image = reader.result.split(',')[1]; // Obtener solo la parte base64

        const producto = {
            name: nombre,
            price: precio,
            categoria: { id: categoriaId },
            descuento: descuentoId ? { id: descuentoId } : null,
            descuentos: [],
            image: base64Image,
            estado: estado
        };

        const xhr = new XMLHttpRequest();
        xhr.open('POST', '/api/productos', true);
        xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
        xhr.onload = function() {
            if (xhr.status === 200) {
                alert('Producto creado exitosamente');
                window.location.reload();
            } else {
                alert('Error al crear el producto');
            }
        };
        xhr.onerror = function() {
            console.error('Error al crear el producto:', xhr.statusText);
        };
        xhr.send(JSON.stringify(producto));
    };

    reader.readAsDataURL(imageFile);
}

function abrirModalActualizar(productId) {
    // Obtener los datos del producto usando AJAX
    const xhr = new XMLHttpRequest();
    xhr.open('GET', `/api/productos/${productId}`, true);
    xhr.onload = function() {
        if (xhr.status === 200) {
            const producto = JSON.parse(xhr.responseText);
            // Llenar los campos del modal con los datos del producto
            document.getElementById('updateProductId').value = producto.id;
            document.getElementById('updateProductName').value = producto.name;
            document.getElementById('updateProductCategory').value = producto.categoria.id;
            document.getElementById('updateProductPrice').value = producto.price;
            document.getElementById('updateProductDiscount').value = producto.descuento ? producto.descuento.id : '';
            document.getElementById('updateProductImagePreview').src = `/product-image/${producto.id}`;
            document.getElementById('updateProductImagePreview').style.display = 'block';

            // Abrir el modal
            const updateProductModal = new bootstrap.Modal(document.getElementById('updateProductModal'));
            updateProductModal.show();
        } else {
            alert('Error al obtener los datos del producto');
        }
    };
    xhr.onerror = function() {
        console.error('Error al obtener los datos del producto:', xhr.statusText);
    };
    xhr.send();
}

function updateProduct() {
    const form = document.getElementById('updateProductForm');
    const formData = new FormData(form);

    const productId = document.getElementById('updateProductId').value;
    const nombre = formData.get('productName');
    const categoriaId = formData.get('productCategory');
    const precio = parseFloat(formData.get('productPrice'));
    const descuentoId = formData.get('productDiscount') ? formData.get('productDiscount') : null;
    const imageFile = document.getElementById('updateProductImage').files[0];
    const estado = true;

    // Validar que los campos obligatorios no sean nulos o vacíos
    if (!nombre || !categoriaId || isNaN(precio)) {
        alert('Por favor, completa todos los campos obligatorios.');
        return;
    }

    const producto = {
        id: productId,
        name: nombre,
        price: precio,
        categoria: { id: categoriaId },
        descuento: descuentoId ? { id: descuentoId } : null,
        descuentos: [],
        estado: estado
    };

    if (imageFile) {
        // Leer la imagen como base64
        const reader = new FileReader();
        reader.onloadend = function() {
            const base64Image = reader.result.split(',')[1]; // Obtener solo la parte base64
            producto.image = base64Image;

            enviarActualizacionProducto(producto);
        };
        reader.readAsDataURL(imageFile);
    } else {
        enviarActualizacionProducto(producto);
    }
}

function enviarActualizacionProducto(producto) {
    const xhr = new XMLHttpRequest();
    xhr.open('PUT', `/api/productos/${producto.id}`, true);
    xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    xhr.onload = function() {
        if (xhr.status === 200) {
            alert('Producto actualizado exitosamente');
            window.location.reload(); // Recargar la página
        } else {
            alert('Error al actualizar el producto');
        }
    };
    xhr.onerror = function() {
        console.error('Error al actualizar el producto:', xhr.statusText);
    };
    xhr.send(JSON.stringify(producto));
}

function confirmarEliminarProducto(productId) {
    const confirmacion = confirm("¿Está seguro de eliminar el producto?");
    if (confirmacion) {
        console.log("Eliminar producto con ID:", productId);
        eliminarProducto(productId);
    }
}

function eliminarProducto(productId) {
    const xhr = new XMLHttpRequest();
    xhr.open('DELETE', `/api/productos/${productId}`, true);
    xhr.onload = function() {
        if (xhr.status === 204) {
            alert('Producto eliminado exitosamente');
            window.location.reload();
        } else {
            alert('Error al eliminar el producto');
        }
    };
    xhr.onerror = function() {
        console.error('Error al eliminar el producto:', xhr.statusText);
    };
    xhr.send();
}

document.getElementById('productImage').addEventListener('change', function(event) {
    const input = event.target;
    if (validateFileInput(input)) {
        previewFile(input, 'productImagePreview');
    }
});

document.getElementById('updateProductImage').addEventListener('change', function(event) {
    const input = event.target;
    if (validateFileInput(input)) {
        previewFile(input, 'updateProductImagePreview');
    }
});

function previewFile(input, previewElementId) {
    const preview = document.getElementById(previewElementId);
    const file = input.files[0];
    const reader = new FileReader();

    reader.addEventListener("load", function () {
        // Mostrar la imagen previsualizada
        preview.src = reader.result;
        preview.style.display = 'block';
    }, false);

    if (file) {
        reader.readAsDataURL(file);
    }
}

function validateFileInput(input) {
    const file = input.files[0];
    const fileType = file["type"];
    const validImageTypes = ["image/gif", "image/jpeg", "image/png"];
    if (!validImageTypes.includes(fileType)) {
        alert("Por favor, selecciona una imagen.");
        input.value = '';
        return false;
    }
    return true;
}