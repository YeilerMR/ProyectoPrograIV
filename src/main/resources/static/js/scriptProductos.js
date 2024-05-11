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
            })
            .catch(function (error) {
                console.error('Error en la solicitud:', error);
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


