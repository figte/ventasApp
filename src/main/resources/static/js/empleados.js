$.getScript("../js/global/notification-custom.js");
$.getScript("../js/global/metodosGlobales.js");
// Show Modal Add

// Id Row data table
let idRow = 0;
let idDelete = 0;
const table = $('#dataTable');

$(document).ready(function () {
	init();
	// Mantiene el tamaño del dataTable
	$('.dataTables_scrollBody').css('height', '400px');

});

$('body').on('click', '#btn-frm-search-empleado', (e) => {
	e.preventDefault;
	let sucursales = $('#sucursales').val();
	let empleos = $('#empleos').val();
	// console.log(empleos+sucursales);
	getSucursales(empleos, sucursales);
});

function init() {
	table.DataTable( {
		"ajax": {
			"method": "GET",
			"url": "../administracion/initEmpleado"
		},
		
		"columns": [{
			"data": "null"
		}, {
			"data": "null"
		}, {
			"data": "null"
		}, {
			"data": "null"
		}, {
			"data": "null"
		}, {
			"data": "null"
		}, {
			"data": "null"
		}, {
			"data": "null"
		}, {
			"data": "null"
		}, {
			"data": "null"
		},],
	"searching": true,   
	 "destroy": true,
	"conditionalPaging": true,
	 "scrollY":        '330px',
     "scrollCollapse": true,
     "paging":         true,
	 "deferRender": true,
	"lengthMenu": [[5, 10, 20, 30], [5, 10, 20, 30]],
	"language": {
        "lengthMenu": "Mostrar _MENU_ ",
        "zeroRecords": "Datos no encontrados - upss",
        "info": "Mostar páginas _PAGE_ de _PAGES_",
        "infoEmpty": "Datos no encontrados",
        "infoFiltered": "(Filtrados por _MAX_ total registros)",
        "search":         "Buscar:",
        "paginate": {
                "first":      "Primero",
                "last":       "Anterior",
                "next":       "Siguiente",
                "previous":   "Anterior"
            },
            }
    } );
}
function getSucursales(empleos, sucursales) {
	/* "/administracion/getEmpleados/1/Gerente funciona" */
	let peticionGet = "/administracion/getEmpleados/";
	if (sucursales.length > 0) {
		peticionGet = peticionGet + sucursales;
		if (empleos.length > 0)
			peticionGet = peticionGet + "/" + empleos;
	}
	console.log(peticionGet);
	table.DataTable({
		"searching": true,
		"destroy": true,
		"conditionalPaging": true,
		"scrollY": '400px',
		"scrollCollapse": true,
		"paging": true,
		"ajax": {
			"method": "GET",
			"url": peticionGet,
		},
		"drawCallback": function (settings) { 
	        // Here the response
	        var response = settings.json;
	        console.log(response);
	    },

		// "deferRender": true,
		"columns": [{
			"data": "correlativo"
		}, {
			"data": "nombre"
		}, {
			"data": "apellido"
		}, {
			"data": "direccion"
		}, {
			"data": "contacto"
		}, {
			"data": "cargo"
		}, {
			"data": "sucursal"
		}, {
			"data": "estadoPerfil"
		}, {
			"data": "editar"
		}, {
			"data": "eliminar"
		},],
		"lengthMenu": [[5, 10, 20, 30], [5, 10, 20, 30]],
		"language": {
			"lengthMenu": "Mostrar _MENU_ ",
			"zeroRecords": "Datos no encontrados - upss",
			"info": "Mostar páginas _PAGE_ de _PAGES_",
			"infoEmpty": "Datos no encontrados",
			"infoFiltered": "(Filtrados por _MAX_ total registros)",
			"search": "Buscar:",
			"paginate": {
				"first": "Primero",
				"last": "Anterior",
				"next": "Siguiente",
				"previous": "Anterior"
			},
		}

	});

}
// add Empleado
$('body').on('click', '#btnNuevoEmpleado', function (e) {
	e.preventDefault;
	$("#modalAdd").modal("show");
	// Remove previous errors
	removerErrors();
	// clean
	limpiarImput();

});

// Guardar empleado
$('body').on('click', '#btnGuardarEmpleado', function (e) {
	// Prevent default submission of form
	e.preventDefault();
	removerErrors();
	// alert("#");
	saveFormPost($('#registrationForm'));

});


//Abrir Edit
$('body').on('click', '.openEdit', function (e) {
	e.preventDefault();
	// Remove previous errors
	removerErrors();
	// clean
	limpiarImput();
	let id = $(this).parent().parent().children('td:eq(0)').text();
	$.get({
		url: '../administracion/getEmpleadoById/' + id,
		success: function (res) {
			console.log(res);
			$('#editForm input[name="id"]').val(id);
			$('#editForm input[name="nombre"]').val(res.data.nombre);
			$('#editForm input[name="apellido"]').val(res.data.apellido);
			$('#editForm input[name="edad"]').val(res.data.fecha_nac);
			$('#editForm input[name="profesion"]').val(res.data.profesion);
			$('#editForm input[name="contacto"]').val(res.data.contacto);
			$('#editForm input[name="direccion"]').val(res.data.direccion);
			$('#editForm select[name="empresa"] option[value="' + res.data.sucursal_id + '"]').prop('selected', true);
			$('#editForm select[name="cargo"] option[value="' + res.data.puesto + '"]').prop('selected', true);
			
			//asigno los roles
			var roles = res.data.roles;
			//convierto el litado a json array
			roles = JSON.parse(roles);
			//Activat check box
            for(var i=0; i<roles.length; i++){
				   $('#editForm input[value="'+roles[i]+'"]').prop('checked',true);	
			}
			//asignar permisos
			$('#editForm input[name="permisos"]').val(listarChecked());
			
			$('#modalEdit').modal('show');
			console.log(res);
		}, error: function (e) {
			var response = e['responseJSON'];
			if(response['error']=="UNPROCESSABLE_ENTITY"){
               successAlert("<strong>Error</strong>", "<br>Registro corrupto, contactar con el administrador de BD", "danger");
			}else{
				successAlert("<strong>Error</strong>", "<br>Si el problema persiste consulte a su proveedor de servicios", "danger");
			//console.log(console.log(JSON.stringify(e)));
			}
			
		}
	})
});

	// Editar guardar empleado
	$('body').on('click', '#btnEditarEmpleado', function (e) {
		// Prevent default submission of form
		e.preventDefault();
		removerErrors();
	
		saveFormPost($('#editForm'));
	});




//Abrir eliminar
$('body').on('click', '.openDelete', function (e) {
	// Prevent default submission of form
	let id = $(this).parent().parent().children('td:eq(0)').text();
	let nombre = $(this).parent().parent().children('td:eq(1)').text();
	let apellido = $(this).parent().parent().children('td:eq(2)').text();
	e.preventDefault();
	$('#modalDelete').modal("show");
	$('#nombreregistro').text("Eliminar el registro(Perfil y Usuario): " + nombre + " " + apellido);
	idDelete = id;
});

//Eliminar registro
$('body').on('click', '#btn-delete-confirm', function (e) {
	const params = {"url":"../administracion/deleterempleado/"+idDelete,}
	deleteElement(params);

});
//Asigna los permisos seleccionados a un input oculto en formato array list
$('body').on('click', 'input[type="checkbox"]',()=>{
    $('input[name="permisos"]').val(listarChecked());
   console.log(listarChecked());
});

//listado de checkbox activos
function listarChecked(){
	let rolesArray = [];
	$('input:checkbox:checked').each(function(){
		rolesArray.push($(this).val());
	});
	return rolesArray;
}
