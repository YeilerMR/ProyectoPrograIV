// Función PopUp Crear Nuevo Envío
function popupCrearEnvio() {
    var modalCrear = document.getElementById("myModalCrearEnvio");
    var btnAgregar = document.querySelector(".add_newEnvio");
    var spanCrear = document.querySelector("#myModalCrearEnvio .close");

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
}

// Función PopUp Actualizar Envío
function popupActualizarEnvio() {
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
}

// Función para cerrar los modales
function closeModal() {
    const spanCerrar = document.querySelectorAll('.close');
    spanCerrar.forEach(span => {
        span.addEventListener('click', () => {
            document.querySelectorAll('.modal').forEach(modal => {
                modal.style.display = 'none';
            });
        });
    });
}

// Llamadas a las funciones
document.addEventListener("DOMContentLoaded", function () {
    validarEliminacion('.producto-eliminar', 'Envío eliminado exitosamente');
    validarEdicion('.editar-envio-form', '¿Estás seguro de continuar con la edición de este envío?', '/envios/listar');
    validarCreacion('.crear-envio-form', '¿Estás seguro de continuar con la creación de este envío?', '/envios/listar');
    popupCrearEnvio();
    popupActualizarEnvio();
    closeModal();
});