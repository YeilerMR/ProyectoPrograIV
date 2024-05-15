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
            document.querySelector('#modalEditar #idEmpleado').value = idEmpleado;
            document.querySelector('#modalEditar input[name="nombre"]').value = nombre;
            document.querySelector('#modalEditar input[name="apellidos"]').value = apellidos;
            document.querySelector('#modalEditar input[name="cedula"]').value = cedula;
            document.querySelector('#modalEditar input[name="email"]').value = email;
            document.querySelector('#modalEditar input[name="password"]').value = password;
            document.querySelector('#modalEditar input[name="telefono"]').value = telefono;
            document.querySelector('#modalEditar select[name="credencial"]').value = credencial;

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
    validarEdicion('.editar-cliente-form', '¿Estás seguro de continuar con la edición de este empleado?', '/empleados/listar');
    validarCreacion('.crear-cliente-form', '¿Estás seguro de continuar con la creación de este empleado?', '/empleados/listar');
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

