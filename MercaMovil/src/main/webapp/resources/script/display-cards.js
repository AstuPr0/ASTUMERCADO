// FUNCIÓN PARA MOSTRAR SOLO SEIS TARJETAS A LA VEZ
function showSixCards() {
    // SELECCIONA TODAS LAS TARJETAS DE ANIMALES
    let cards = document.querySelectorAll('#products-cards .col');

    // ITERA SOBRE LAS TARJETAS Y OCULTA TODAS
    cards.forEach(card => {
        card.style.display = 'none';
    });

    // MOSTRAR SOLO LAS SEIS PRIMERAS TARJETAS
    for (let i = 0; i < 6; i++) {
        if (cards[i]) {
            cards[i].style.display = 'flex';
        }
    }
}

document.addEventListener('DOMContentLoaded', function () {
    // FUNCIÓN PARA CARGAR MÁS TARJETAS CUANDO SE HACE CLIC EN EL BOTÓN "VER MÁS"
    document.getElementById('ver-mas').addEventListener('click', function () {
        // SELECCIONA TODAS LAS TARJETAS DE ANIMALES
        let cards = document.querySelectorAll('#products-cards .col');
        let hiddenCards = Array.from(cards).filter(card => card.style.display === 'none');

        // MUESTRA LAS SIGUIENTES SEIS TARJETAS QUE ESTÉN OCULTAS
        for (let i = 0; i < 6; i++) {
            if (hiddenCards[i]) {
                hiddenCards[i].style.display = 'flex';
            }
        }

        // SI YA NO HAY MÁS TARJETAS OCULTAS, OCULTA EL BOTÓN "VER MÁS"
        if (hiddenCards.length <= 6) {
            document.getElementById('ver-mas').style.display = 'none';
        }
    });

    // MUESTRA LAS PRIMERAS SEIS TARJETAS AL CARGAR LA PÁGINA
    showSixCards();
});