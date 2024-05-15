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

function validarFormularioCrearOrden() {
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
    
    // Si todos los campos están llenos, enviar el formulario
    setTimeout(function() {
        form.submit(); // Envía el formulario de manera programática
    }, 1000);
    return true; // Devuelve true para indicar que el formulario se envió
}


function mostrarMensaje(mensaje, container) {
    container.innerHTML = mensaje;
}