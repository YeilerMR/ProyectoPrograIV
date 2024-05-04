document.addEventListener("DOMContentLoaded", function () {
    var modalCrear = document.getElementById("myModalCrearEnvio");
    var btnAgregar = document.querySelector(".add_newEnvio");
    var spanCrear = document.querySelector("#myModalCrearEnvio .close");

    btnAgregar.onclick = function () {
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
});
