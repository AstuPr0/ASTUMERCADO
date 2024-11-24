function validateFileInput(input) {
    var file = input.files[0];
    var fileType = file["type"];
    var validImageTypes = ["image/gif", "image/jpeg", "image/png"];
    if (!validImageTypes.includes(fileType)) {
        alert("Por favor, selecciona una imagen.");
        input.value = '';
        return false;
    }
    return true;
}

window.onload = function () {
    var nameInput = document.getElementById('name');
    var descriptionInput = document.getElementById('description');
    var bitacoraTratamientoInput = document.getElementById('bitacoraTratamiento');

    nameInput.addEventListener('input', function () {
        if (nameInput.checkValidity()) {
            nameInput.classList.remove('is-invalid');
            nameInput.classList.add('is-valid');
        } else {
            nameInput.classList.remove('is-valid');
            nameInput.classList.add('is-invalid');
        }
    });

    descriptionInput.addEventListener('input', function () {
        if (descriptionInput.checkValidity()) {
            descriptionInput.classList.remove('is-invalid');
            descriptionInput.classList.add('is-valid');
        } else {
            descriptionInput.classList.remove('is-valid');
            descriptionInput.classList.add('is-invalid');
        }
    });

    bitacoraTratamientoInput.addEventListener('input', function () {
        if (bitacoraTratamientoInput.checkValidity()) {
            bitacoraTratamientoInput.classList.remove('is-invalid');
            bitacoraTratamientoInput.classList.add('is-valid');
        } else {
            bitacoraTratamientoInput.classList.remove('is-valid');
            bitacoraTratamientoInput.classList.add('is-invalid');
        }
    });
};

function previewFile() {
    const preview = document.getElementById('preview');
    const file = document.getElementById('animalImage').files[0];
    const reader = new FileReader();

    reader.addEventListener("load", function () {
        // CONVERTIR IMAGEN A BASE64
        const image = new Image();
        image.src = reader.result;

        image.onload = function() {
            const canvas = document.createElement('canvas');
            const max_size = 500;
            let width = image.width;
            let height = image.height;

            if (width > height) {
                if (width > max_size) {
                    height *= max_size / width;
                    width = max_size;
                }
            } else {
                if (height > max_size) {
                    width *= max_size / height;
                    height = max_size;
                }
            }

            canvas.width = width;
            canvas.height = height;
            canvas.getContext('2d').drawImage(image, 0, 0, width, height);
            const dataUrl = canvas.toDataURL('image/jpeg');

            preview.src = dataUrl;
        }
    }, false);

    if (file) {
        reader.readAsDataURL(file);
    }
}