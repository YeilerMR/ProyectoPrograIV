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


// Función para manejar la edición de cliente
function handleEnvioEdit() {
    // Obtener todos los botones de editar
    const botonesEditar = document.querySelectorAll('.producto-editar');

    // Agregar un evento click a cada botón de editar
    botonesEditar.forEach(boton => {
        boton.addEventListener('click', () => {
            // Obtener los datos del envío desde los atributos data-*
            const idEnvio = boton.closest('tr').querySelector('.producto-eliminar a').href.split('=')[1];
            const codigoEnvio = boton.closest('tr').querySelector('td:nth-child(1)').textContent;
            const idPedido = boton.closest('tr').querySelector('td:nth-child(2)').textContent;
            /*const idCliente = boton.closest('tr').querySelector('td:nth-child(3) span').textContent.split('Cédula: ')[1].trim();*/
            const direccionEnvio = boton.closest('tr').querySelector('td:nth-child(4)').textContent;
            const fechaEnvioTexto = boton.closest('tr').querySelector('td:nth-child(5)').textContent; // Obtener el texto de la fecha
            const observacion = boton.closest('tr').querySelector('td:nth-child(6)').textContent;
            const estadoEnvio = boton.closest('tr').querySelector('td:nth-child(7)').textContent;

            // Rellenar los campos del formulario con los datos del envío
            document.querySelector('#modalEditarEnvio #idEnvio').value = idEnvio;
            document.querySelector('#modalEditarEnvio input[name="codigoEnvio"]').value = codigoEnvio;
            document.querySelector('#modalEditarEnvio input[name="idPedido"]').value = idPedido;
            /*document.querySelector('#modalEditarEnvio select[name="idCliente"]').value = idCliente;*/
            document.querySelector('#modalEditarEnvio input[name="direccionEnvio"]').value = direccionEnvio;
            // Convertir el texto de fecha al formato requerido ('yyyy-MM-ddThh:mm')
            const fechaEnvio = fechaEnvioTexto.replace(/(\d{2})\/(\d{2})\/(\d{4}) (\d{2}):(\d{2}):(\d{2})/, '$3-$2-$1T$4:$5');
            // Asignar la fecha al input datetime-local
            document.querySelector('#modalEditarEnvio input[name="fechaEnvio"]').value = fechaEnvio;
            document.querySelector('#modalEditarEnvio input[name="observacion"]').value = observacion;
            document.querySelector('#modalEditarEnvio input[name="estadoEnvio"]').value = estadoEnvio;

            // Mostrar el modal de edición
            document.querySelector('#modalEditarEnvio').style.display = 'block';
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
    handleEnvioEdit();
    closeModal();
});