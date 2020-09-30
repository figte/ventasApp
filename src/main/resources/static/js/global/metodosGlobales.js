
$('body').on('click', '#btn-cancel-confirm', function (e) {
	$('#modalDelete').modal("hide");
});

// Ocultar alerts
function ocultarAlert() {
	setTimeout("$('#alerta').hide()", 5000);
};
// Remove previous errors
function removerErrors() {
	$('input').next('span').remove();
	$('.permisos').next('span').remove();
	$('textarea').next('span').remove();
	$('select').next('span').remove();
	$('#registrationForm').prev('div').remove();
	$('#general-content').prev('div').remove();
	$('#editForm').prev('div').remove();
	$('table').next('span').remove();
};

// limpiar inputs
function limpiarImput() {
	$('input[type="text"]').val("");
	$('input[type="number"]').val("");
	$('input[type="date"]').val("");
	$('textarea').val("");
	$('.modal select').val("");
	$('input[type="checkbox"').prop('checked', false);

};

// funcion para guardar formularios metodo gennerico
function saveFormPost(form) {
	$.post({

		url: form.prop('action'),

		data: form.serialize(),

		success: (res) => {

			if (res.validated) {

				limpiarImput();// clean
				let numbreRowDataTable = $('#dataTable tr').length;// 3 es por

				if (numbreRowDataTable >= 3) {
					try {
						table.DataTable().ajax.reload(null, false);// actualizar data table
					} catch (e) {
						//solventa el error al estar vacia la data table
						table.DataTable.ext.errMode = "none";
						$(table).on("error.dt", (e, settings, techNote, message) => {
							console.log("Error: " + message);
						});
					}

				}

				$('#modalAdd').fadeOut(() => {// Ocultar Modal
					$('#modalAdd').modal('hide');
				});
				$('#modalEdit').fadeOut(() => {// Ocultar Edit Modal
					$('#modalEdit').modal('hide');
				});

				successAlert("<strong>Éxito</strong>", "<br>Transacción realizada", "success");// Mostrar
				// la
				// alerta
			} else {

				// add alerta formulario vacio
				$('#registrationForm').before('<div class="alert alert-danger text-center mt-2" role="alert" id="alerta"> Complete el formulario </div>').animate(4000);

				// Mostrar los errores de validacion por parte de backend
				$.each(res.errorMessages, function (key, value) {
					if (key == 'permisos') $('.permisos').after('<span class="form-text text-danger text-justify">' + value + '</span>');
					if (key != 'permisos') $('input[name=' + key + ']').after('<span class="form-text text-danger text-justify">' + value + '</span>');
					$('textarea[name=' + key + ']').after('<span class="form-text text-danger">' + value + '</span>');
					$('select[name=' + key + ']').after('<span class="form-text text-danger">' + value + '</span>');
				});
			}
		}, error: (e) => {// Si hay errores o xceptions internas del servidor
			//Valida si el usr es repetido
			if(validarPerfil(e) == true){
				$('input[name = contacto]').after('<span class="form-text text-danger text-justify"> Registro duplicado</span>');
			}else{
				//Proceso general por defecto
				$('#modalAdd').fadeOut(() => {// ocultar modal
					$('#modalAdd').modal('hide');
				});
	
				$('#modalEdit').fadeOut(() => {// Ocultar Edit Modal
					$('#modalEdit').modal('hide');
				});

				successAlert("<strong>Error</strong>", "<br>" + e.responseJSON["message"] + "", "danger");// mostrar
			}
			console.log(e.responseJSON.message);// imprimir el error

		}
	})
}

function validarPerfil(error) {
	let response = false;
	if(error.responseJSON.status == 422 && error.responseJSON.message == "DUPLICADO"){
        response = true;
	}
    return response;
}



/* 
 * Recibe como parametro un objeto javascript "url":"url"
 * Delete elementos metodo generico*/
function deleteElement(params) {
	$.ajax({
		type: "delete",
		url: params.url,
		headers: { "X-CSRF-TOKEN": $("input[name='_csrf']").val() },
		contentType: "application/json",
		dataType: 'json',
		success: function (result) {
			/* Refrescamos la pagina del data table sin cambiar de pagina */
			table.DataTable().ajax.reload(null, false);

			// Detectamos si la pagina del Data table contiene Rows
			let numbreRowDataTable = $('#dataTable tr').length;

			ocultarAlert();
			$('#modalDelete').fadeOut(() => {
				$('#modalDelete').modal('hide');
			});
			successAlert("<strong>Éxito</strong>", "<br>Registro Eliminado", "success");
			if (numbreRowDataTable > 3) {

			} else {
				// Ir una pagina atras
				$('#dataTable').DataTable().page('previous').draw('page');
			}

		}, error: function (e) {
			console.log(e.responseJSON.status);
			$('#modalDelete').fadeOut(() => {
				$('#modalDelete').modal('hide');
			});

			if (e.responseJSON.status = "422") {
				successAlert("<strong >Error</strong>", "<div class='text-justify mt-0'>" + e.responseJSON.message + "</div>", "danger");
			} else {
				successAlert("<strong>Error Inesperado</strong>", "<br>Si el problema persiste consulte a su proveedor de servicios", "danger");
			}
		}
	});
}

//funcion para redondear decimales.
function redondearDecimales(numero, decimales) {
    numeroRegexp = new RegExp('\\d\\.(\\d){' + decimales + ',}');   // Expresion regular para numeros con un cierto numero de decimales o mas
    if (numeroRegexp.test(numero)) {         // Ya que el numero tiene el numero de decimales requeridos o mas, se realiza el redondeo
        return Number(numero.toFixed(decimales));
    } else {
        return Number(numero.toFixed(decimales)) === 0 ? 0 : numero;  // En valores muy bajos, se comprueba si el numero es 0 (con el redondeo deseado), si no lo es se devuelve el numero otra vez.
    }
}