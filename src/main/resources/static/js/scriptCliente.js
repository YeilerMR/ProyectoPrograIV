// Función PopUp Crear Nuevo Cliente
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

// Función PopUp Actualizar Cliente
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

// Función Ver Contraseña en Tabla 
document.addEventListener("DOMContentLoaded", function () {
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
});

// Función Ver Contraseña en PopUp (Editar-Crear) 
document.addEventListener("DOMContentLoaded", function () {
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
});


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

// Código para cerrar el modal cuando se hace clic en el botón de cerrar
const spanCerrar = document.querySelectorAll('.close');
spanCerrar.forEach(span => {
    span.addEventListener('click', () => {
        document.querySelectorAll('.modal').forEach(modal => {
            modal.style.display = 'none';
        });
    });
});