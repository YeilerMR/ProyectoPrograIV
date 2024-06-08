// Función para validar la modificación de un objeto
function validarEdicionEnvio(selector, mensajeConfirmacion, urlRedireccion) {
    var editForm = document.querySelector(selector);
    editForm.addEventListener('submit', function (event) {
        event.preventDefault();

        Swal.fire({
            title: mensajeConfirmacion,
            text: '¡No podrás revertir esto!',
            icon: 'question',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sí, continuar',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                // Realizar una petición AJAX o enviar el formulario de forma asíncrona
                fetch(editForm.action, {
                    method: 'POST',
                    body: new FormData(editForm)
                })
                        .then(response => response.json())
                        .then(data => {
                            if (data.success) {
                                // Si el proceso de moficar el cliente fue exitoso, mostrar mensaje de éxito
                                mostrarToastConfirmacion(data.message);
                                // Redirigir después de un pequeño retraso
                                setTimeout(function () {
                                    window.location.href = urlRedireccion;
                                }, 1000); // 1000 milisegundos de retraso
                            } else {
                                // Si el proceso de modificar el cliente falló, mostrar mensaje de error
                                mostrarToastError(data.message);
                            }
                        })
                        .catch(error => {
                            console.error('Error:', error);
                        });
            }
        });
    });
}
// Función PopUp Crear Nuevo Envío
function popupCrearEnvio() {
    var modalCrear = document.getElementById("myModalCrearEnvio");
    var btnAgregar = document.querySelector(".add_new");
    var spanCrear = document.querySelector("#myModalCrearEnvio .close");

    btnAgregar.onclick = function () {
        event.preventDefault();
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
    var editButtons = document.querySelectorAll('.producto-editar'); // Selecciona los elementos que abrirán el modal
    var closeButtons = document.querySelectorAll('#modalEditarEnvio .close'); // Selecciona los botones de cierre del modal

    // Función para abrir los modals
    editButtons.forEach(function (btn) {
        btn.onclick = function () {
            var modal = document.getElementById('modalEditarEnvio');
            modal.style.display = 'block';
        };
    });

    // Función para cerrar los modals
    closeButtons.forEach(function (btn) {
        btn.onclick = function () {
            var modal = document.getElementById('modalEditarEnvio');
            modal.style.display = 'none';
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


            document.querySelector('#modalEditarEnvio input[name="direccionEnvio"]').value = direccionEnvio;
            // Convertir el texto de fecha al formato requerido ('yyyy-MM-ddThh:mm')
            const fechaEnvio = fechaEnvioTexto.replace(/(\d{2})\/(\d{2})\/(\d{4}) (\d{2}):(\d{2}):(\d{2})/, '$3-$2-$1T$4:$5');
            // Asignar la fecha al input datetime-local
            document.querySelector('#modalEditarEnvio input[name="fechaEnvio"]').value = fechaEnvio;
            document.querySelector('#modalEditarEnvio input[name="observacion"]').value = observacion;

            // Seleccionar el estado correcto en el select
            const selectEstadoEnvio = document.querySelector('#modalEditarEnvio select[name="estadoEnvio"]');
            selectEstadoEnvio.value = estadoEnvio;

            // Mostrar el modal de edición
            document.querySelector('#modalEditarEnvio').style.display = 'block';
        });
    });
}

function buscarEnvio() {
    var textoBuscar = document.getElementById("textoBuscar").value;
    var divClientes = document.getElementById("tablaEnvios");
    fetch("/envios/buscar?textoBuscar=" + textoBuscar)
            .then(function (response) {
                if (!response.ok) {
                    throw new Error("Error en la solicitud: " + response.status);
                }
                return response.text();
            })
            .then(function (data) {
                divClientes.innerHTML = data;
                initializeEventHandlers(); // Inicializa los eventos después de actualizar la tabla
            })
            .catch(function (error) {
                console.error('Error en la solicitud:', error);
            });
}

function validarYBuscar() {
    var textoBuscar = document.getElementById("textoBuscar").value;
    if (textoBuscar.trim() === "") {
        mostrarToastAdvertencia("El campo de búsqueda está vacío. Por favor, ingresa el código del envío a buscar.");
    } else {
        buscarEnvio();
    }
}

function initializeEventHandlers() {
    validarEliminacion('.producto-eliminar', 'Envío eliminado exitosamente');
    validarEdicionEnvio('.editar-envio-form', '¿Estás seguro de continuar con la edición de este envío?', '/envios/listar');
    validarCreacion('.crear-envio-form', '¿Estás seguro de continuar con la creación de este envío?', '/envios/listar');
    popupCrearEnvio();
    popupActualizarEnvio();
    handleEnvioEdit();
    closeModal();
}

// Llamadas a las funciones
document.addEventListener("DOMContentLoaded", function () {
    initializeEventHandlers();
});