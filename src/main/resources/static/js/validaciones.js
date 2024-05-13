
function validarFormulario(event) {
    // Obtener el formulario actual que desencadenó el evento submit
    var formulario = event.target.closest('form');
    var inputs = formulario.querySelectorAll('input');
    
    var codigo;
    var fecha;
    var precio;
    var descuento;
    var impuesto;
    var observacion;
    //obtengo los valores de los inputs
    inputs.forEach(function (input) {
        if (input.id === "codigo") {
            codigo = input.value;
        }
        if (input.id === "fecha"){
            fecha = input.value;
        }
        if(input.id === "precio"){
            precio = input.value;
        }
        if(input.id === "descuento"){
            descuento = input.value;
        }
        if(input.id ==="impuesto"){
            impuesto = input.value;
        }
        if(input.id === "observacion"){
            observacion = input.value;
        }
    });
    //analizo si estan todos los campos llenos
    var texto = "";
    if (codigo === '' || fecha === '' || precio === '' || impuesto === '' || descuento === '' || observacion === '') {
        texto = "Necesitas llenar todos los campos.";
        event.preventDefault();
        alert(texto);
    }
}


//function validarFormulario2(event) {
//    alert("validando");
//    var codigo = document.getElementById("codigo");
//    var fecha = document.getElementById("fecha").value;
//    var precio = document.getElementById("precio").value;
//    var descuento = document.getElementById("descuento").value;
//    var impuesto = document.getElementById("impuesto").value;
//    var observacion = document.getElementById("observacion").value;
//    var texto = "";
//    if (codigo === '' || fecha === '' || precio === '' || impuesto === '' || descuento === '' || observacion === '') {
//        texto = "Necesitas llenar todos los datos.";
//        event.preventDefault();
//        alert(texto);
//    }
//}

