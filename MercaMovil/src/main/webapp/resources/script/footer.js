// CAMBIAR EL IDIOMA DE LA APLICACIÓN
function changeLanguage() {
    var language = document.getElementById('languageSelect').value;
    window.location.search = '?lang=' + language;
}

// OBTENER PARAMETRO POR NOMBRE EN LA URL
function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, '\\$&');
    var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
}

// CAMBIAR EL IDIOMA DE LA APLICACIÓN AL CARGAR LA PÁGINA
window.onload = function() {
    var lang = getParameterByName('lang');
    if (lang) {
        document.getElementById('languageSelect').value = lang;
    }
}
