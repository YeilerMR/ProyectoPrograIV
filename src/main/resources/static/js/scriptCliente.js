document.addEventListener("DOMContentLoaded", function () {
    var modalCrear = document.getElementById("myModalCrearCliente");
    var btnAgregar = document.querySelector(".add_new");
    var spanCrear = document.querySelector("#myModalCrearCliente .close");

    btnAgregar.onclick = function () {
        modalCrear.style.display = "block";
    };

    spanCrear.onclick = function () {
        modalCrear.style.display = "none";
    };

    window.onclick = function (event) {
        if (event.target === modalCrear) {
            modalCrear.style.display = "none";
        }
    };
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