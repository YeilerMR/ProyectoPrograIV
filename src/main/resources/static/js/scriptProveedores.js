function editarProveedor() {
    var editarProveedorContainer = document.getElementById("editar_proveedor_container");

    // Obtener todos los botones de detalles y agregar un evento clic a cada uno
    var botonesEditar = document.querySelectorAll('.btn_editar');
    botonesEditar.forEach(function (boton) {
        boton.addEventListener('click', function () {
            // Obtener el valor (ID del producto) del botón clicado
            var idProveedor = this.value;
            //alert("ID del producto: " + idProveedor);

            var xmlhttp = new XMLHttpRequest();
            xmlhttp.open("GET", "/proveedores/editar?proveedor=" + idProveedor, true);
            xmlhttp.send();

            xmlhttp.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    editarProveedorContainer.innerHTML = this.responseText;
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

    // Si todos los campos están llenos, enviar el formulario después de 3 segundos
    //setTimeout(function() {
    //form.submit(); // Envía el formulario de manera programática

    //}, 2000); // 3000 milisegundos = 3 segundos

    return true; // Devuelve true para indicar que el formulario se enviará después del retraso
}



function mostrarMensaje(mensaje, container) {
    container.innerHTML = mensaje;
}

function validarCreacionProveedor(selector, mensajeConfirmacion, urlRedireccion) {
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
            if (validarFormularioCrear(event)) {
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
            }
        });
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
