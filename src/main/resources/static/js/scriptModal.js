document.addEventListener("DOMContentLoaded", function () {
    var modalCrear = document.getElementById("myModalCrearCliente");
    var btnAgregar = document.querySelector(".add_new");
    var spanCrear = document.querySelector("#myModalCrearCliente .close");

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

function cancelarCreacionButton(){
    document.getElementById("form-crear").reset();
    document.getElementById('texto_error').innerHTML = '';
    document.getElementById("myModalCrearCliente").style.display = "none";
}