// Función para validar la modificación de un objeto
function validarEdicionCliente(selector, mensajeConfirmacion, urlRedireccion) {
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
// Función para validar la eliminación de un objeto cliente
function validarEliminacionCliente(selector, mensajeExito, mensajeError) {
    var deleteButtons = document.querySelectorAll(selector);
    deleteButtons.forEach(function (button) {
        button.addEventListener('click', function (event) {
            event.preventDefault();
            var url = this.querySelector('a').getAttribute('href');
            Swal.fire({
                title: '¿Estás seguro?',
                text: '¡No podrás revertir esto!',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Sí, eliminarlo!',
                cancelButtonText: 'Cancelar'
            }).then((result) => {
                if (result.isConfirmed) {
                    // Realizar la solicitud de eliminación
                    fetch(url, {
                        method: 'DELETE'
                    })
                    .then(response => response.json())
                    .then(data => {
                        if (data.success) {
                            // Mostrar el mensaje de éxito después de la redirección
                            mostrarToastConfirmacion(mensajeExito);
                            setTimeout(function () {
                                window.location.href = '/clientesApi/listar';
                            }, 1000); // 1000 milisegundos de retraso
                        } else {
                            // Mostrar el mensaje de error
                            mostrarToastError(data.message ? data.message : mensajeError);
                        }
                    })
                    .catch(error => {
                        console.error('Error:', error);
                        mostrarToastError('Ocurrió un error inesperado');
                    });
                }
            });
        });
    });
}
// Función PopUp Crear Nuevo Cliente
function popupCrearCliente() {
    var modalCrear = document.getElementById("myModalCrearCliente");
    var btnAgregar = document.querySelector(".add_new");
    var spanCrear = document.querySelector("#myModalCrearCliente .close");

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

// Función PopUp Actualizar Cliente
function popupActualizarCliente() {
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

// Función Ver Contraseña en Tabla 
function togglePasswordInTable() {
    const toggleButtons = document.querySelectorAll('.toggle-password');

    toggleButtons.forEach(button => {
        button.addEventListener('click', function () {
            const passwordField = this.parentNode.querySelector('.password');
            const eyeOpen = this.parentNode.querySelector('#eye-open');
            const eyeClose = this.parentNode.querySelector('#eye-close');

            if (passwordField.classList.contains('visible')) {
                passwordField.textContent = '******';
                passwordField.classList.remove('visible');
                eyeOpen.style.display = 'inline';
                eyeClose.style.display = 'none';
            } else {
                const password = passwordField.getAttribute('data-password');
                passwordField.textContent = password;
                passwordField.classList.add('visible');
                eyeOpen.style.display = 'none';
                eyeClose.style.display = 'inline';
            }
        });
    });
}

// Función Ver Contraseña en PopUp (Editar-Crear) 
function togglePasswordInPopup() {
    const toggleButtons = document.querySelectorAll('.toggle-password');

    toggleButtons.forEach(button => {
        button.addEventListener('click', function () {
            const passwordField = this.parentNode.querySelector('input[type="password"], input[type="text"]');
            const eyeOpen = this.querySelector('#eye-open');
            const eyeClose = this.querySelector('#eye-close');

            if (passwordField.type === 'password') {
                passwordField.type = 'text';
                eyeOpen.style.display = 'none';
                eyeClose.style.display = 'inline';
            } else {
                passwordField.type = 'password';
                eyeOpen.style.display = 'inline';
                eyeClose.style.display = 'none';
            }
        });
    });
}

// Función para manejar la edición de cliente
function handleClientEdit() {
    // Obtener todos los botones de editar
    const botonesEditar = document.querySelectorAll('.producto-editar');

    // Agregar un evento click a cada botón de editar
    botonesEditar.forEach(boton => {
        boton.addEventListener('click', () => {
            // Obtener los datos del cliente desde los atributos data-*
            const idCliente = boton.closest('tr').querySelector('.producto-eliminar a').href.split('=')[1];
            const nombre = boton.closest('tr').querySelector('td:nth-child(1)').textContent;
            const apellidos = boton.closest('tr').querySelector('td:nth-child(2)').textContent;
            const cedula = boton.closest('tr').querySelector('td:nth-child(3)').textContent;
            const email = boton.closest('tr').querySelector('td:nth-child(4)').textContent;
            const password = boton.closest('tr').querySelector('td:nth-child(5) .password').dataset.password;
            const telefono = boton.closest('tr').querySelector('td:nth-child(6)').textContent;
            const credencial = boton.closest('tr').querySelector('td:nth-child(7)').textContent === 'Cliente' ? 0 : 1;

            // Rellenar los campos del formulario con los datos del cliente
            document.querySelector('#modalEditar #idCliente').value = idCliente;
            document.querySelector('#modalEditar input[name="usuario.nombre"]').value = nombre;
            document.querySelector('#modalEditar input[name="usuario.apellidos"]').value = apellidos;
            document.querySelector('#modalEditar input[name="usuario.cedula"]').value = cedula;
            document.querySelector('#modalEditar input[name="usuario.email"]').value = email;
            document.querySelector('#modalEditar input[name="usuario.password"]').value = password;
            document.querySelector('#modalEditar input[name="usuario.telefono"]').value = telefono;
            document.querySelector('#modalEditar select[name="usuario.credencial"]').value = credencial;

            // Mostrar el modal de edición
            document.querySelector('#modalEditar').style.display = 'block';
        });
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

function buscarCliente() {
    var textoBuscar = document.getElementById("textoBuscar").value;
    var divClientes = document.getElementById("tablaClientes");
    fetch("/clientes/buscar?textoBuscar=" + textoBuscar)
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
        mostrarToastAdvertencia("El campo de búsqueda está vacío. Por favor, ingresa un la cédula del cliente a buscar.");
    } else {
        buscarCliente();
    }
}

function initializeEventHandlers() {
    validarEliminacionCliente('.producto-eliminar', 'Cliente eliminado exitosamente', 'No se pudo eliminar porque está asociado a un envío');
    validarEdicionCliente('.editar-cliente-form', '¿Estás seguro de continuar con la edición de este cliente?', '/clientesApi/listar');
    validarCreacion('.crear-cliente-form', '¿Estás seguro de continuar con la creación de este cliente?', '/clientesApi/listar');
    popupCrearCliente();
    popupActualizarCliente();
    togglePasswordInTable();
    togglePasswordInPopup();
    handleClientEdit();
    closeModal();
}

// Llamadas a las funciones
document.addEventListener("DOMContentLoaded", function () {
    initializeEventHandlers();
});
