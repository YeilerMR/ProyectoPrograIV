// Función para validar la modificación de un objeto
function validarEdicionApartado(selector, mensajeConfirmacion, urlRedireccion) {
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
                        mostrarToastConfirmacion(data.message);
                        setTimeout(function () {
                            window.location.href = urlRedireccion;
                        }, 1000);
                    } else {
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

// Función PopUp Crear Nuevo Apartado
function popupCrearApartado() {
    var modalCrear = document.getElementById("myModalCrearApartado");
    var btnAgregar = document.querySelector(".add_new");
    var spanCrear = document.querySelector("#myModalCrearApartado .close");

    btnAgregar.onclick = function (event) {
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

// Función PopUp Actualizar Apartado
function popupActualizarApartado() {
    var editButtons = document.querySelectorAll('.producto-editar');
    var closeButtons = document.querySelectorAll('#modalEditarApartado .close');

    editButtons.forEach(function (btn) {
        btn.onclick = function () {
            var modal = document.getElementById('modalEditarApartado');
            modal.style.display = 'block';
        };
    });

    closeButtons.forEach(function (btn) {
        btn.onclick = function () {
            var modal = document.getElementById('modalEditarApartado');
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

// Función para manejar la edición de apartado
function handleApartadoEdit() {
    const botonesEditar = document.querySelectorAll('.producto-editar');

    botonesEditar.forEach(boton => {
        boton.addEventListener('click', () => {
            const idApartado = boton.closest('tr').querySelector('td:nth-child(1)').textContent;
            const idCliente = boton.closest('tr').querySelector('td:nth-child(2)').textContent;
            const idProducto = boton.closest('tr').querySelector('td:nth-child(3)').textContent;
            const fechaInicioApartado = boton.closest('tr').querySelector('td:nth-child(4)').textContent;
            const fechaFinalApartado = boton.closest('tr').querySelector('td:nth-child(5)').textContent;
            const abono = boton.closest('tr').querySelector('td:nth-child(6)').textContent;
            const estadoApartado = boton.closest('tr').querySelector('td:nth-child(7)').textContent;

            document.querySelector('#modalEditarApartado #idApartado').value = idApartado;
            document.querySelector('#modalEditarApartado input[name="idCliente"]').value = idCliente;
            document.querySelector('#modalEditarApartado input[name="idProducto"]').value = idProducto;
            document.querySelector('#modalEditarApartado input[name="fechaInicioApartado"]').value = fechaInicioApartado;
            document.querySelector('#modalEditarApartado input[name="fechaFinalApartado"]').value = fechaFinalApartado;
            document.querySelector('#modalEditarApartado input[name="abono"]').value = abono;
            document.querySelector('#modalEditarApartado input[name="estadoApartado"]').value = estadoApartado;

            document.querySelector('#modalEditarApartado').style.display = 'block';
        });
    });
}

function buscarApartado() {
    var textoBuscar = document.getElementById("textoBuscar").value;
    var divApartados = document.getElementById("tablaApartados");
    fetch("/apartados/buscar?textoBuscar=" + textoBuscar)
        .then(function (response) {
            if (!response.ok) {
                throw new Error("Error en la solicitud: " + response.status);
            }
            return response.text();
        })
        .then(function (data) {
            divApartados.innerHTML = data;
            initializeEventHandlers();
        })
        .catch(function (error) {
            console.error('Error en la solicitud:', error);
        });
}

function validarYBuscar() {
    var textoBuscar = document.getElementById("textoBuscar").value;
    if (textoBuscar.trim() === "") {
        mostrarToastAdvertencia("El campo de búsqueda está vacío. Por favor, ingresa el ID del apartado a buscar.");
    } else {
        buscarApartado();
    }
}

function initializeEventHandlers() {
    validarEliminacion('.producto-eliminar', 'Apartado eliminado exitosamente');
    validarEdicionApartado('.editar-apartado-form', '¿Estás seguro de continuar con la edición de este apartado?', '/apartados/listar');
    validarCreacion('.crear-apartado-form', '¿Estás seguro de continuar con la creación de este apartado?', '/apartados/listar');
    popupCrearApartado();
    popupActualizarApartado();
    handleApartadoEdit();
    closeModal();
}

document.addEventListener("DOMContentLoaded", function () {
    initializeEventHandlers();
});
