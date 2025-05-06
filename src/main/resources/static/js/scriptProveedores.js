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
    // Obtener el valor (ID del producto) del botón clicado
}

function detallesProveedor() {
    var editarProveedorContainer = document.getElementById("editar_proveedor_container");

    // Obtener todos los botones de detalles y agregar un evento clic a cada uno
    var botonesEditar = document.querySelectorAll('.btn_detalles');
    botonesEditar.forEach(function (boton) {
        boton.addEventListener('click', function () {
            var idProveedor = this.value;
            //alert("ID del producto: " + idProveedor);

            var xmlhttp = new XMLHttpRequest();
            xmlhttp.open("GET", "/proveedoresApi/detalles?proveedor=" + idProveedor, true);
            xmlhttp.send();

            xmlhttp.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    editarProveedorContainer.innerHTML = this.responseText;
                }
            };
        });
    });
}

function submitFormProveedor() {
    var crearForm = document.querySelector('.editForm');
    crearForm.addEventListener('submit', function (event) {
        event.preventDefault();
        Swal.fire({
            title: '¿Desea continuar con la edición de este proveedor?',
            text: '¡Asegúrate de tener los datos correctos!',
            icon: 'question',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sí, continuar',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                fetch(crearForm.action, {
                    method: 'POST',
                    headers: {
                        'X-HTTP-Method-Override': 'PUT'
                    },
                    body: new FormData(crearForm)
                })
                        .then(response => response.json())
                        .then(data => {
                            if (data.success) {
                                mostrarToastConfirmacion(data.message);
                                setTimeout(function () {
                                    window.location.href = "./listar";
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

    return true;
}

function mostrarMensaje(mensaje, container) {
    container.innerHTML = mensaje;
}

function validarCreacionProveedor(selector, mensajeConfirmacion, urlRedireccion) {
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

function validarEliminacionProveedor(selector, mensajeConfirmacion, urlRedireccion) {
    var deleteButtons = document.querySelectorAll(selector);
    deleteButtons.forEach(function (button) {
        button.addEventListener('click', function (event) {
            event.preventDefault();
            var url = this.querySelector('a').getAttribute('href');
            Swal.fire({
                title: mensajeConfirmacion,
                text: '¡No podrás revertir esto!',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Sí, eliminarlo!',
                cancelButtonText: 'Cancelar'
            }).then((result) => {
                if (result.isConfirmed) {
                    // Realizar una petición AJAX para eliminar el proveedor
                    fetch(url, {
                        method: 'DELETE'
                    })
                            .then(response => response.json())
                            .then(data => {
                                if (data.success) {
                                    // Si el proceso de eliminación fue exitoso, mostrar mensaje de éxito
                                    mostrarToastConfirmacion(data.message);
                                    // Redirigir después de un pequeño retraso
                                    setTimeout(function () {
                                        window.location.href = urlRedireccion;
                                    }, 1000); // 1000 milisegundos de retraso
                                } else {
                                    // Si el proceso de eliminación falló, mostrar mensaje de error
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
