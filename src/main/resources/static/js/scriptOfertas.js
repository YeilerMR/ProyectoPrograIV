document.addEventListener("DOMContentLoaded", function() {
    const openPopupBtn = document.getElementById("openPopupBtn");
    const closePopupBtn = document.getElementById("closePopupBtn");
    const popup = document.getElementById("popup");

    openPopupBtn.addEventListener("click", function() {
        popup.style.display = "block";
    });

    closePopupBtn.addEventListener("click", function() {
        popup.style.display = "none";
    });

    // Cerrar el popup haciendo clic fuera del mismo
    window.addEventListener("click", function(event) {
        if (event.target == popup) {
            popup.style.display = "none";
        }
    });
});
document.addEventListener("DOMContentLoaded", function () {
    const openPopupBtn = document.getElementById("add_new");
    const closePopupBtn = document.getElementById("closeModalNuevo");
    const popup = document.getElementById("modal");

    openPopupBtn.addEventListener("click", function () {
        popup.style.display = "block";
    });

    closePopupBtn.addEventListener("click", function () {
        popup.style.display = "none";
    });

    // Cerrar el popup haciendo clic fuera del mismo
    window.addEventListener("click", function (event) {
        if (event.target == popup) {
            popup.style.display = "none";
        }
    });
});

document.addEventListener("DOMContentLoaded", function () {
    var editButtons = document.querySelectorAll('.producto-editar');
    var closeButtons = document.querySelectorAll('.modal .close');

    // Función para abrir los popups
    editButtons.forEach(function (btn) {
        btn.onclick = function () {
            var popup = this.closest('tr').querySelector('.modal');
            popup.style.display = 'block';
        };
    });

    // Función para cerrar los popups
    closeButtons.forEach(function (btn) {
        btn.onclick = function () {
            var popup = this.closest('.modal');
            popup.style.display = 'none';
        };
    });
});