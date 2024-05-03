document.addEventListener("DOMContentLoaded", function() {
    const openPopupBtn = document.getElementById("openPopupBtn");
    const closePopupBtn = document.getElementById("closePopupBtn");
    const popup = document.getElementById("popup");

    openPopupBtn.addEventListener("click", function() {
        popup.style.display = "block";
    });

    closePopupBtn.addEventListener("click", function() {
        popup.style.display = "none";
    });

    // Cerrar el popup haciendo clic fuera del mismo
    window.addEventListener("click", function(event) {
        if (event.target == popup) {
            popup.style.display = "none";
        }
    });
});

document.addEventListener("DOMContentLoaded", function() {
    const openPopupBtn = document.getElementById("openPopupBtn2");
    const closePopupBtn = document.getElementById("closePopupBtn2");
    const popup = document.getElementById("popup2");

    openPopupBtn.addEventListener("click", function() {
        popup.style.display = "block";
    });

    closePopupBtn.addEventListener("click", function() {
        popup.style.display = "none";
    });

    // Cerrar el popup haciendo clic fuera del mismo
    window.addEventListener("click", function(event) {
        if (event.target == popup) {
            popup.style.display = "none";
        }
    });
});