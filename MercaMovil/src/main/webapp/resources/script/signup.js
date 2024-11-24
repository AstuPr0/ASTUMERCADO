let emailInput = document.getElementById('email');
let passwordInput = document.getElementById('password');
let usernameInput = document.getElementById('username');
let registerButton = document.getElementById('registerButton');
let helpBlockItems = document.querySelectorAll('#passwordHelpBlock li');
let passwordStrengthBar = document.getElementById('passwordStrengthBar');
let passwordStrengthText = document.getElementById('passwordStrengthText');

function validateEmail(email) {
    let re = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return re.test(String(email).toLowerCase());
}

function updatePasswordStrength(password) {
    let strength = 0;
    let hasUpperCase = /[A-Z]/.test(password);
    let hasLowerCase = /[a-z]/.test(password);
    let hasNumber = /\d/.test(password);
    let hasSpecialChar = /[!@#$%^&*(),.?":{}|<>;_+]/.test(password);
    let hasMinLength = password.length >= 8;

    if (hasMinLength) strength++;
    if (hasUpperCase) strength++;
    if (hasLowerCase) strength++;
    if (hasNumber) strength++;
    if (hasSpecialChar) strength++;

    let strengthPercentage = (strength / 5) * 100;
    passwordStrengthBar.style.width = strengthPercentage + '%';

    // Ocultar todos los textos de fuerza de la contraseña
    document.getElementById('veryWeakText').style.display = 'none';
    document.getElementById('weakText').style.display = 'none';
    document.getElementById('strongText').style.display = 'none';
    document.getElementById('veryStrongText').style.display = 'none';

    switch (strength) {
        case 1:
            passwordStrengthBar.className = 'progress-bar bg-danger';
            document.getElementById('veryWeakText').style.display = 'inline';
            break;
        case 2:
        case 3:
            passwordStrengthBar.className = 'progress-bar bg-warning';
            document.getElementById('weakText').style.display = 'inline';
            break;
        case 4:
            passwordStrengthBar.className = 'progress-bar bg-info';
            document.getElementById('strongText').style.display = 'inline';
            break;
        case 5:
            passwordStrengthBar.className = 'progress-bar bg-success';
            document.getElementById('veryStrongText').style.display = 'inline';
            break;
        default:
            passwordStrengthBar.className = 'progress-bar';
            break;
    }
}

function validateForm() {
    let email = emailInput.value;
    let password = passwordInput.value;
    let username = usernameInput.value;

    // Verifica si el email es válido
    let isEmailValid = validateEmail(email);

    // Verifica si la contraseña cumple con los criterios
    let hasUpperCase = /[A-Z]/.test(password);
    let hasLowerCase = /[a-z]/.test(password);
    let hasNumber = /\d/.test(password);
    let hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password);
    let hasMinLength = password.length >= 8;

    // Verifica si el nombre de usuario está llenado
    let isUsernameMinLength = username.length >= 8;
    let isUsernameMaxLenght = username.length <= 20;

    // Actualiza la barra de fuerza de la contraseña
    updatePasswordStrength(password);

    // Si todos los campos son válidos, habilita el botón de registro
    if (isEmailValid && hasUpperCase && hasLowerCase && hasNumber && hasMinLength && isUsernameMinLength && isUsernameMaxLenght) {
        registerButton.disabled = false;
    } else {
        registerButton.disabled = true;
    }
}

window.onload = function() {
    var usernameInput = document.getElementById('username');
    var emailInput = document.getElementById('email');

    usernameInput.addEventListener('input', function() {
        if (usernameInput.checkValidity()) {
            usernameInput.classList.remove('is-invalid');
            usernameInput.classList.add('is-valid');
        } else {
            usernameInput.classList.remove('is-valid');
            usernameInput.classList.add('is-invalid');
        }
    });

    emailInput.addEventListener('input', function() {
        if (emailInput.checkValidity()) {
            emailInput.classList.remove('is-invalid');
            emailInput.classList.add('is-valid');
        } else {
            emailInput.classList.remove('is-valid');
            emailInput.classList.add('is-invalid');
        }
    });
};

if (emailInput) emailInput.addEventListener('input', validateForm);
if (passwordInput) passwordInput.addEventListener('input', validateForm);
if (usernameInput) usernameInput.addEventListener('input', validateForm);

document.addEventListener('DOMContentLoaded', function () {
    var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'));
    var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
        return new bootstrap.Popover(popoverTriggerEl);
    });
});