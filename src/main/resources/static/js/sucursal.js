//Importamos los metodos
$.getScript("../js/global/notification-custom.js");
$.getScript("../js/global/metodosGlobales.js");

// Id Row data table
let idRow = 0;
let idDelete = 0;
const table = $('#dataTable');


//Cargar al inicio
$(document).ready(function() {
	init();
	// Mantiene el tamaño del dataTable
	$('.dataTables_scrollBody').css('height', '400px');
	
});



//Abrir agregar Sucursal
$('body').on('click', '#addsucursal',function(e){
	   e.preventDefault;
	   $("#modalAdd").modal("show");
	   // Remove previous errors
	   removerErrors();
	   // clean
	   limpiarImput();
	   
});


// Save Sucursal
$('body').on('click', '#submitButton',function(e){
        // Prevent default submission of form
        e.preventDefault();
        // Remove previous errors
        removerErrors();   

		saveFormPost($('#registrationForm'));

}); 	

//abrir Editar
$('body').on('click', '.openEdit',function(e){
	  e.preventDefault;
	  // Remove previous errors
	   removerErrors();
	   // clean
	   limpiarImput();
	  $("#modalEdit").modal("show");//Abrir modal

	  //Asignar valores
	  $('#editForm  input[name="id"]').val($(this).parent().parent().children('td:eq(0)').text());
	  $('#editForm  input[name="nombre"]').val( $(this).parent().parent().children('td:eq(1)').text());
	  $('#editForm  input[name="direccion"]').val($(this).parent().parent().children('td:eq(2)').text());
	  $('#editForm  input[name="telefono"]').val($(this).parent().parent().children('td:eq(3)').text());
	  $('#editForm  input[name="email"]').val($(this).parent().parent().children('td:eq(4)').text());
	  $('#editForm  textarea[name="detalle"]').val($(this).parent().parent().children('td:eq(5)').text());
});

// Edit Sucursal
$('body').on('click', '#submitButtonEdit',function(e){
        // Prevent default submission of form
        e.preventDefault();
        // Remove previous errors
        removerErrors();   
        //aolicar el update
		saveFormPost($('#editForm'));

}); 


//abrir eliminar
$('body').on('click', '.openDelete',function(e){
	  e.preventDefault;
	  // Remove previous errors
	   removerErrors();
	   // clean
	   limpiarImput();
	  $("#modalDelete").modal("show");//Abrir modal
	  //Asignar valores
	  $('#nombreregistro').text("Eliminar el registro: "+$(this).parent().parent().children('td:eq(1)').text());
	  idDelete = $(this).parent().parent().children('td:eq(0)').text();
});

//Eliminar
$('body').on('click', '#btn-delete-confirm',function(e){
   const params = {"url":"../administracion/deleteSucursal/"+idDelete,}
   deleteElement(params);
	
});
//Inicio de dataTable
function init() {
	table.DataTable({

		"searching" : true,
		"destroy" : true,
		"conditionalPaging" : true,
		"scrollY" : '330px',
		"scrollCollapse" : true,
		"paging" : true,
		"ajax" : {
			"method" : "GET",
			"url" : "/administracion/getsucursales"
		},
		// "deferRender": true,
		"columns" : [ {
			"data" : "correlativo"
		}, {
			"data" : "nombre"
		}, {
			"data" : "direccion"
		}, {
			"data" : "tel"
		}, {
			"data" : "email"
		}, {
			"data" : "detalle"
		}, {
			"data" : "editar"
		}, {
			"data" : "eliminar"
		}, ],

		"buttons" : [ {
			"copy" : 'copy'
		} ],
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
