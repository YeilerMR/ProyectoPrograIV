function editarOrden() {
    var editarOrdenContainer = document.getElementById("editar_orden_container");

    // Obtener todos los botones de detalles y agregar un evento clic a cada uno
    var botonesEditar = document.querySelectorAll('.btn_editar');
    botonesEditar.forEach(function (boton) {
        boton.addEventListener('click', function () {
            // Obtener el valor (ID del producto) del botón clicado
            var numeroReferencia = this.value;
            //alert("ID del producto: " + idProveedor);

            var xmlhttp = new XMLHttpRequest();
            xmlhttp.open("GET", "/ordenes_compra/editar?orden=" + numeroReferencia, true);
            xmlhttp.send();

            xmlhttp.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    editarOrdenContainer.innerHTML = this.responseText;
                }
            };
        });
    });
}

function submitForm(event) {
    event.preventDefault(); // Evita que el formulario se envíe automáticamente

    var container = document.getElementById('mensaje_container');
    var form = document.getElementById('editForm');
    var formData = new FormData(form);

    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open('GET', form.getAttribute('action') + '?' + new URLSearchParams(formData).toString(), true);
    xmlhttp.send();

    xmlhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            container.innerHTML = this.responseText;
        }
    };
}

function validarFormularioCrear(event) {
    event.preventDefault(); // Evitar que el evento por defecto se ejecute

    var form = document.getElementById('form-crear');
    var inputs = form.getElementsByTagName('input');
    var mensajeContainer = document.getElementById('texto_error');

    for (var i = 0; i < inputs.length; i++) {
        var input = inputs[i];
        if (input.value.trim() === '') {
            var mensaje = "Por favor, completa todos los campos.";
            mostrarMensaje(mensaje, mensajeContainer);
            return false; // Detener la validación y no enviar el formulario
        }
    }

    return true; // Devuelve true para indicar que el formulario se enviará después del retraso
}

function validarCreacionOrden(selector, mensajeConfirmacion, urlRedireccion) {
    var crearForm = document.querySelector(selector);
    crearForm.addEventListener('submit', function (event) {
        event.preventDefault();
        if (validarFormularioCrear(event)) {
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
                    //alert("Si llega aqui.");
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
        }
    });
}

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
function mostrarMensaje(mensaje, container) {
    container.innerHTML = mensaje;
}