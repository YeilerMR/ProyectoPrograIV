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
    var editButtons = document.querySelectorAll('.openPopupBtn');
    var closeButtons = document.querySelectorAll('.popup .close');

    // Función para abrir los popups
    editButtons.forEach(function (btn) {
        btn.onclick = function () {
            var popup = this.closest('tr').querySelector('.popup');
            popup.style.display = 'block';
        };
    });

    // Función para cerrar los popups
    closeButtons.forEach(function (btn) {
        btn.onclick = function () {
            var popup = this.closest('.popup');
            popup.style.display = 'none';
        };
    });
});


document.addEventListener("DOMContentLoaded", function () {
    const openPopupBtn = document.getElementById("openPopupBtn");
    const container = document.getElementById("editar_Productos");

    // Mostrar el formulario como un popup cuando se haga clic en el botón "Editar"
    openPopupBtn.addEventListener("click", function () {
        // Cargar el contenido del archivo "editar_Productos.html" dentro del contenedor
        fetch("editar_Productos.html")
                .then(response => response.text())
                .then(html => {
                    container.innerHTML = html;
                    // Mostrar el popup
                    container.style.display = "block";
                })
                .catch(error => console.error("Error al cargar el formulario:", error));
    });

    // Ocultar el popup cuando se hace clic fuera del mismo
    window.addEventListener("click", function (event) {
        if (event.target == container) {
            container.style.display = "none";
        }
    });
});