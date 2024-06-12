function validarEdicionProducto(selector, mensajeConfirmacion, urlRedireccion) {
    var editForms = document.querySelectorAll(selector);
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
                                    // Si el proceso de modificar el cliente fue exitoso, mostrar mensaje de éxito
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

function popupCrearProducto() {
    console.log("La función popupCrearProducto() se está ejecutando.");
    var modalCrear = document.getElementById("modalCrearProducto");
    var btnAgregar = document.querySelector(".add_new");
    var spanCrear = document.querySelector("#modalCrearProducto .close");

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

function popupActualizarProducto() {
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
/**
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
 });**/

//function buscarProducto() {
//    var textoBuscar = document.getElementById("textoBuscar").value;
//    var contenedor = document.getElementById("tablaBusqueda");
//    //var divProductos= document.getElementById("tablaProductos");
//    contenedor.innerHTML = "Procesando...";
//
//    var xmlhttp = new XMLHttpRequest();
//
//    // Corrección: Agrega el signo de igual antes del valor de textoBuscar
//    xmlhttp.open("GET", "/productos/buscar?textoBuscar=" + textoBuscar, true);
//    xmlhttp.send();
//    
//    //Cambia el contenido del contenedor 'tablaProductos'
//    //por el resultado de la busqueda
//    
//    //divProductos.innerHTML= contenedor.innerHTML;
//
//    // Corrección: Cambia "xmlhttp.onreadystatechange()" a "xmlhttp.onreadystatechange"
//    xmlhttp.onreadystatechange = function() {
//        if (this.readyState === 4 && this.status === 200) {
//            alert("listo");
//            contenedor.innerHTML = this.responseText;
//        }
//    };
//    
//}


function buscarProducto() {
    var textoBuscar = document.getElementById("textoBuscar").value;
    //var contenedor = document.getElementById("/resultadoBusqueda/tablaBusqueda");
    var divProductos = document.getElementById("tablaProductos");
    //contenedor.innerHTML = "Procesando...";

    fetch("/productos/buscar?textoBuscar=" + textoBuscar)
            .then(function (response) {
                if (!response.ok) {
                    throw new Error("Error en la solicitud: " + response.status);
                }
                return response.text();
            })
            .then(function (data) {
                // Actualizar el contenido del contenedor con la respuesta del servidor

                //contenedor.innerHTML = data;

                // Cambiar el contenido de divProductos al contenido actualizado de contenedor
                divProductos.innerHTML = data;//contenedor.innerHTML;
                initializeEventHandlers();
            })
            .catch(function (error) {
                console.error('Error en la solicitud:', error);
            });
}

function validarEliminacionProducto(selector, mensajeExito, mensajeError) {
    var deleteLinks = document.querySelectorAll(selector);
    deleteLinks.forEach(function (link) {
        link.addEventListener('click', function (event) {
            event.preventDefault();
            var url = this.getAttribute('href');
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
                    fetch(url, {
                        method: 'DELETE'
                    })
                    .then(response => response.json())
                    .then(data => {
                        if (data.success) {
                            mostrarToastConfirmacion(mensajeExito);
                            setTimeout(function () {
                                window.location.href = '/productosApi/listar';
                            }, 1000);
                        } else {
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

function validarYBuscar() {
    var textoBuscar = document.getElementById("textoBuscar").value;

    // Verificar si el campo de texto está vacío
    if (textoBuscar.trim() === "") {
        // Mostrar un mensaje de error o una ventana emergente
        alert("El campo de búsqueda está vacío. Por favor, ingresa un término de búsqueda.");
    } else {
        // Si el campo de texto no está vacío, llamar a la función buscarProducto
        buscarProducto();
    }
}

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

function initializeEventHandlers() {
    popupCrearProducto();
    popupActualizarProducto();
    validarCreacion('.crear-producto-form', '¿Estás seguro de continuar con la creación de este producto?', '/productosApi/listar');
    validarEdicionProducto('.editar-producto-form', '¿Estás seguro de continuar con la edición de este producto?', '/productosApi/listar');
    validarEliminacionProducto('.producto-eliminarLink', 'Producto eliminado exitosamente', '');
    //validarYBuscar();
    closeModal();
}

document.addEventListener("DOMContentLoaded", function () {
    initializeEventHandlers();
});

