document.addEventListener("DOMContentLoaded", function () {
    const openPopupBtn = document.getElementById("openPopupBtn");
    const closePopupBtn = document.getElementById("closePopupBtn");
    const popup = document.getElementById("popup");

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



// Función para validar la creación de un objeto
function validarCreacion(selector, mensajeConfirmacion, urlRedireccion) {
    var crearForm = document.querySelector(selector);
    crearForm.addEventListener('submit', function (event) {
        event.preventDefault();

        Swal.fire({
            title: mensajeConfirmacion,
            text: '¡Asegurate de tener los datos correctos!',
            icon: 'question',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sí, continuar',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                // Realizar una petición AJAX o enviar el formulario de forma asíncrona
                fetch(crearForm.action, {
                    method: 'POST',
                    body: new FormData(crearForm)
                })
                        .then(response => response.json())
                        .then(data => {
                            if (data.success) {
                                // Si el proceso de agregar el cliente fue exitoso, mostrar mensaje de éxito
                                mostrarToastConfirmacion(data.message);
                                // Redirigir después de un pequeño retraso
                                setTimeout(function () {
                                    window.location.href = urlRedireccion;
                                }, 1000); // 1000 milisegundos de retraso
                            } else {
                                // Si el proceso de agregar el cliente falló, mostrar mensaje de error
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



// Función para validar la eliminación de un objeto
function validarEliminacion(selector, mensajeExito) {
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
                    // Redirigir después de un pequeño retraso
                    setTimeout(function () {
                        window.location.href = url;
                    }, 1000); // 1000 milisegundos de retraso

                    // Mostrar el mensaje de éxito después de la redirección
                    mostrarToastConfirmacion(mensajeExito);
                }
            });
        });
    });
}

// Función para validar la modificación de un objeto
function validarEdicion(selector, mensajeConfirmacion, urlRedireccion) {
    // Seleccionar todos los formularios que coincidan con el selector
    var editForms = document.querySelectorAll(selector);

    // Agregar un event listener de submit a cada formulario
    editForms.forEach(function (editForm) {
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
    });
}
// -----------------------------------------------------------------------------
// Función para mostrar un Toast de error
function mostrarToastError(mensaje) {
    const Toast = Swal.mixin({
        toast: true,
        position: 'bottom-end',
        showConfirmButton: false,
        timer: 2500
    });
    Toast.fire({
        icon: 'error',
        title: mensaje
    });
}

// Función para mostrar un Toast de advertencia
function mostrarToastAdvertencia(mensaje) {
    const Toast = Swal.mixin({
        toast: true,
        position: 'bottom-end',
        showConfirmButton: false,
        timer: 2500
    });
    Toast.fire({
        icon: 'warning',
        title: mensaje
    });
}

// Función para mostrar un Toast de confirmación
function mostrarToastConfirmacion(titulo) {
    const Toast = Swal.mixin({
        toast: true,
        position: 'bottom-end',
        showConfirmButton: false,
        timer: 2500
    });
    Toast.fire({
        icon: 'success',
        title: titulo
    });
}


document.addEventListener("DOMContentLoaded", function () {
    validarEliminacion('.producto-eliminar', '¡Oferta eliminada exitosamente!');
    validarEdicion('.editar-oferta-form', '¿Estás seguro de continuar con la edición de esta oferta?', '/ofertas/listar');
    validarCreacion('.crear-oferta-form', '¿Estás seguro de continuar con la creación de esta oferta?', '/ofertas/listar');
});