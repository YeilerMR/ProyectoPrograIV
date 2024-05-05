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