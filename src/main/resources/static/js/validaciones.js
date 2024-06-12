//

document.addEventListener("DOMContentLoaded", function() {
    // Función para abrir el modal
    function openModal(modal) {
        if (modal) {
            modal.style.display = "block";
        }
    }

    // Función para cerrar el modal
    function closeModal(modal) {
        if (modal) {
            modal.style.display = "none";
        }
    }

    // Añadir evento de clic a todos los botones para abrir el modal
    var buttons = document.querySelectorAll(".btn_detalles");
    buttons.forEach(function(button) {
        button.addEventListener("click", function() {
            //Encontrar el modal correspondiente en la misma fila
            var modal = button.closest("td").querySelector(".modal");
            
 
            openModal(modal);
        });
    });

    // cierre de los modales
    var closeButtons = document.querySelectorAll(".modal .close");
    closeButtons.forEach(function(closeButton) {
        closeButton.addEventListener("click", function() {
            // Cerrar el modal correspondiente
            var modal = closeButton.closest(".modal");
            closeModal(modal);
        });
    });

    // Cerrar el modal clic fuera del modal
    window.addEventListener("click", function(event) {
        var modals = document.querySelectorAll(".modal");
        modals.forEach(function(modal) {
            if (event.target === modal) {
                closeModal(modal);
            }
        });
    });
});
