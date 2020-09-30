$.getScript("../js/global/notification-custom.js");
$.getScript("../js/global/metodosGlobales.js");
// Show Modal Add

// Id Row data table
let idRow = 0;
let idDelete = 0;
const table = $('#dataTable');

$(document).ready(function() {
	init();
	// Mantiene el tamaño del dataTable
	$('.dataTables_scrollBody').css('height', '400px');
	// OCULTAR
	$('#entradas').hide();
	$('#devoluciones').hide();
	
	switch ($("input[name=proceso]:checked").val()) {
	case "1":
		$('#entradas').show();
		
		break;
    case "2":
    	$('#devoluciones').show();
    	
		break;	

	default:
		
		break;
	}

});

function init() {
	table.DataTable({
		"ajax" : {
			"method" : "GET",
			"url" : "../inventario/initEntradas/"
		},
		"drawCallback" : function(settings) {
			// Here the response
			var response = settings.json;
			console.log(response);
		},
		"columns" : [ {
			"data" : "null"
		}, {
			"data" : "null"
		}, {
			"data" : "null"
		}, {
			"data" : "null"
		}, {
			"data" : "null"
		}, {
			"data" : "null"
		}, {
			"data" : "null"
		}, {
			"data" : "null"
		}, {
			"data" : "null"
		}, {
			"data" : "null"
		}, ],
		"searching" : true,
		"destroy" : true,
		"conditionalPaging" : true,
		"scrollY" : '330px',
		"scrollCollapse" : true,
		"paging" : true,
		"deferRender" : true,
		"lengthMenu" : [ [ 5, 10, 20, 30 ], [ 5, 10, 20, 30 ] ],
		"language" : {
			"lengthMenu" : "Mostrar _MENU_ ",
			"zeroRecords" : "Datos no encontrados - upss",
			"info" : "Mostar páginas _PAGE_ de _PAGES_",
			"infoEmpty" : "Datos no encontrados",
			"infoFiltered" : "(Filtrados por _MAX_ total registros)",
			"search" : "Buscar:",
			"paginate" : {
				"first" : "Primero",
				"last" : "Anterior",
				"next" : "Siguiente",
				"previous" : "Anterior"
			},
		}
	});
}

$('body').on('click', '#btn-buscar', function(e) {
	e.preventDefault;
	getEntradas();
});

function getEntradas() {

	removerErrors();

	// Validar fecha
	var fecha1 = $('#fecha1').val();
	var fecha2 = $('#fecha2').val();
	var fecha1validate = true;
	var fecha2validate = true;
	// mensajes de error para fechas vacias
	if (fechaVacia(fecha1) == 0) {
		$('.form-group #fecha1')
				.after(
						'<span class="form-text text-danger text-justify"> * Campo obligatorio </span>');
		fecha1validate = false;
	}
	if (fechaVacia(fecha2) == 0) {
		$('.form-group #fecha2')
				.after(
						'<span class="form-text text-danger text-justify"> * Campo obligatorio </span>');
		fecha2validate = false;
	}
	// validar que fecha 2 sea mayor a fecha 1

	if (fecha1 > fecha2) {
		if (fecha2 == "") {
			// pass
		} else {
			$('.form-group #fecha2')
					.after(
							'<span class="form-text text-danger text-justify"> * La fecha final no puede ser menor a la fecha inicial </span>');
			fecha1validate = false;
		}
	}

	if (fecha1validate == true && fecha2validate == true) {
		// Data table
		table.DataTable({
			"searching" : true,
			"destroy" : true,
			"conditionalPaging" : true,
			"scrollY" : '400px',
			"scrollCollapse" : true,
			"paging" : true,
			"ajax" : {
				"method" : "GET",
				"url" : "../inventario/list_entradas/"+fecha1+"/"+fecha2,
				"data" : function(d) {
					console.log(d);
				}
			},
			"drawCallback" : function(settings) {
				// Here the response
				var response = settings.json;
				console.log(response);
			},
			// "deferRender": true,
			"columns" : [ {
				"data" : "correlativo"
			}, {
				"data" : "codigo_producto"
			}, {
				"data" : "producto"
			}, {
				"data" : "cantidad"
			}, {
				"data" : "unidad_medida"
			}, {
				"data" : "fecha_ingreso"
			}, {
				"data" : "tipo_entrada"
			}, {
				"data" : "documento"
			}, {
				"data" : "codigo"
			},{
				"data" : "detalle"
			}, ],
			"lengthMenu" : [ [ 5, 10, 20, 30 ], [ 5, 10, 20, 30 ] ],
			"language" : {
				"lengthMenu" : "Mostrar _MENU_ ",
				"zeroRecords" : "Datos no encontrados - upss",
				"info" : "Mostar páginas _PAGE_ de _PAGES_",
				"infoEmpty" : "Datos no encontrados",
				"infoFiltered" : "(Filtrados por _MAX_ total registros)",
				"search" : "Buscar:",
				"paginate" : {
					"first" : "Primero",
					"last" : "Anterior",
					"next" : "Siguiente",
					"previous" : "Anterior"
				},
			}

		});

	}

}

// fecha vacia
function fechaVacia(fecha) {
	if (fecha == "") {
		return 0;
	} else {
		return 1;
	}
}

// abrir modal add entrada
$("body").on("click", "#addEntrada",(e)=>{
	e.preventDefault;
	$("#modalAdd").modal("show");
	
});

// Entradas mostrar formulario
$("body").on("click", "input[name=proceso]", (e)=>{
	$('#entradas').hide();
	$('#devoluciones').hide();
	switch ($("input[name=proceso]:checked").val()) {
	case "1":
		$('#entradas').show();
		break;
    case "2":
    	$('#devoluciones').show();
		break;	

	default:
	   break;
	}
});

// selected categoria llenado de productos

$("#categoria" ).change(()=>{
	$('#optionProductodefault').next('option').remove();
	$.ajax({
	    url: '../inventario/getproductos_categoria/'+$("#categoria" ).val(),
	    type: 'GET',
	    success: function(data){ 
	    	for(dataRow in data["data"]){
	    		console.log(data["data"][dataRow]["codigo"]);
	    		console.log(data["data"][dataRow]["nombre"]);
	    		$('select[name="codigoProducto"] option').after('<option value="'+data["data"][dataRow]["correlativo"]+'">'+data["data"][dataRow]["nombre"]+'</option>');
	    	}
	    },
	    error: function(error) {
	    	  console.log("error");
	        // 7a//lert('woops!'); //or whatever
	    }
	});
	
});
//Guardar Entrada
$('body').on('click', '#btnGuardarEntrada', function (e) {
	// Prevent default submission of form
	e.preventDefault();
	removerErrors();
	// alert("#");
	saveFormPost($('#registrationFormCompra'));

});

//opciones al cerrar el modal
$("#modalAdd").on('hidden.bs.modal', function () {
	removerErrors();
	$('#registrationFormCompra').trigger("reset");
	$('#optionProductodefault').next('option').remove();
});

//Limpiar errores con boton retet
$("input[type='reset']").click(()=>{
	removerErrors();	
	$('#optionProductodefault').next('option').remove();
});



