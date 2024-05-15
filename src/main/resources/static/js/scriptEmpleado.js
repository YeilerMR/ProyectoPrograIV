// Función para validar la modificación de un objeto
function validarEdicionEmpleado(selector, mensajeConfirmacion, urlRedireccion) {
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
// Función PopUp Crear Nuevo Empleado
function popupCrearEmpleado() {
    var modalCrear = document.getElementById("myModalCrearEmpleado");
    var btnAgregar = document.querySelector(".add_new");
    var spanCrear = document.querySelector("#myModalCrearEmpleado .close");

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

// Función PopUp Actualizar Empleado
function popupActualizarEmpleado() {
    var editButtons = document.querySelectorAll('.producto-editar'); // Selecciona los elementos que abrirán el modal
    var closeButtons = document.querySelectorAll('#modalEditarEmpleado .close'); // Selecciona los botones de cierre del modal

    // Función para abrir los modals
    editButtons.forEach(function (btn) {
        btn.onclick = function () {
            var modal = document.getElementById('modalEditarEmpleado');
            modal.style.display = 'block';
        };
    });

    // Función para cerrar los modals
    closeButtons.forEach(function (btn) {
        btn.onclick = function () {
            var modal = document.getElementById('modalEditarEmpleado');
            modal.style.display = 'none';
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

// Función para manejar la edición de empleado
function handleEmpleadoEdit() {
    // Obtener todos los botones de editar
    const botonesEditar = document.querySelectorAll('.producto-editar');

    // Agregar un evento click a cada botón de editar
    botonesEditar.forEach(boton => {
        boton.addEventListener('click', () => {
            // Obtener los datos del empleado desde los atributos data-*
            const idEmpleado = boton.closest('tr').querySelector('.producto-eliminar a').href.split('=')[1];
            const nombre = boton.closest('tr').querySelector('td:nth-child(1)').textContent;
            const apellidos = boton.closest('tr').querySelector('td:nth-child(2)').textContent;
            const cedula = boton.closest('tr').querySelector('td:nth-child(3)').textContent;
            const email = boton.closest('tr').querySelector('td:nth-child(4)').textContent;
            const password = boton.closest('tr').querySelector('td:nth-child(5) .password').dataset.password;
            const telefono = boton.closest('tr').querySelector('td:nth-child(6)').textContent;
            const credencial = boton.closest('tr').querySelector('td:nth-child(7)').textContent === 'Empleado' ? 0 : 1;

            // Rellenar los campos del formulario con los datos del empleado
            document.querySelector('#modalEditarEmpleado #idEmpleado').value = idEmpleado;
            document.querySelector('#modalEditarEmpleado input[name="nombre"]').value = nombre;
            document.querySelector('#modalEditarEmpleado input[name="apellidos"]').value = apellidos;
            document.querySelector('#modalEditarEmpleado input[name="cedula"]').value = cedula;
            document.querySelector('#modalEditarEmpleado input[name="email"]').value = email;
            document.querySelector('#modalEditarEmpleado input[name="password"]').value = password;
            document.querySelector('#modalEditarEmpleado input[name="telefono"]').value = telefono;
            document.querySelector('#modalEditarEmpleado select[name="credencial"]').value = credencial;

            // Mostrar el modal de edición
            document.querySelector('#modalEditarEmpleado').style.display = 'block';
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

function buscarEmpleado() {
    var textoBuscar = document.getElementById("textoBuscar").value;
    var divEmpleados = document.getElementById("tablaEmpleados");
    fetch("/empleados/buscar?textoBuscar=" + textoBuscar)
            .then(function (response) {
                if (!response.ok) {
                    throw new Error("Error en la solicitud: " + response.status);
                }
                return response.text();
            })
            .then(function (data) {
                divEmpleados.innerHTML = data;
                initializeEventHandlers(); // Inicializa los eventos después de actualizar la tabla
            })
            .catch(function (error) {
                console.error('Error en la solicitud:', error);
            });
}

function validarYBuscar() {
    var textoBuscar = document.getElementById("textoBuscar").value;
    if (textoBuscar.trim() === "") {
        mostrarToastAdvertencia("El campo de búsqueda está vacío. Por favor, ingresa un la cédula del empleado a buscar.");
    } else {
        buscarEmpleado();
    }
}

function initializeEventHandlers() {
    
    validarEliminacion('.producto-eliminar', 'Empleado eliminado exitosamente');
    validarEdicionEmpleado('.editar-empleado-form', '¿Estás seguro de continuar con la edición de este empleado?', '/empleados/listar');
    validarCreacion('.crear-empleado-form', '¿Estás seguro de continuar con la creación de este empleado?', '/empleados/listar');
    popupCrearEmpleado();
    popupActualizarEmpleado();
    togglePasswordInTable();
    togglePasswordInPopup();
    handleEmpleadoEdit();
    closeModal();
}

// Llamadas a las funciones
document.addEventListener("DOMContentLoaded", function () {
    initializeEventHandlers();
});