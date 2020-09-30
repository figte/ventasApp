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
			"url" : "/inventario/getproductos"
		},
		// "deferRender": true,
		"columns" : [ {
			"data" : "correlativo"
		}, {
			"data" : "nombre"
		}, {
			"data" : "precio"
		}, {
			"data" : "categoria"
		}, {
			"data" : "proveedor"
		}, {
			"data" : "existencias"
		}, {
			"data" : "stock"
		}, {
			"data" : "unidadDeMedida"
		},{
			"data" : "estado"
		},{
			"data" : "impuesto_CESC"
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

//Abrir agregar Sucursal
$('body').on('click', '#addSerie',function(e){
    e.preventDefault();
    $("#modalAdd").modal("show");
    // Remove previous errors
    removerErrors();
    // clean
    limpiarImput();
    
});

// Save Producto
$('body').on('click', '#submitButton',function(e){
    // Prevent default submission of form
     e.preventDefault();
    // Remove previous errors
    removerErrors();   

	saveFormPost($('#registrationForm'));
	
	// $.ajax({
	// 	url:"/administracion/saveSerie",
	// 	method:"Post",
	// 	data:$('#registrationForm').serialize(),
	// 	success:function(data){
	// 		console.log(data);
	// 	},
	// 	error:function(data){
	// 		console.log("Error: "+JSON.stringify(data));
	// 	},
	// });

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

// //Eliminar
$('body').on('click', '#btn-delete-confirm',function(e){
 const params = {"url":"../inventario/deleteProducto/"+idDelete,}
 deleteElement(params);
  
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
	$('#editForm  input[name="codigo"]').val($(this).parent().parent().children('td:eq(0)').text());
	$('#editForm  input[name="nombre"]').val( $(this).parent().parent().children('td:eq(1)').text());
	$('#editForm  input[name="precio"]').val($(this).parent().parent().children('td:eq(2)').text());
	
	var categoria=$(this).parent().parent().children('td:eq(3)').text();
	// $('#editForm  select[name="idCategoria"] option').val();
	$('#editForm  select[name="idCategoria"] option').each(function () {
		if ($(this).text().toLowerCase() == categoria.toLowerCase()) {
			this.selected = true;
			return;
		} });

	var proveedor=$(this).parent().parent().children('td:eq(4)').text();
	// $('#editForm  select[name="idProveedor"]').val();
	$('#editForm  select[name="idProveedor"] option').each(function () {
		if ($(this).text().toLowerCase() == proveedor.toLowerCase()) {
			this.selected = true;
			return;
		} });

	$('#editForm  input[name="existencias"]').val($(this).parent().parent().children('td:eq(5)').text());
	$('#editForm  input[name="stock"]').val($(this).parent().parent().children('td:eq(6)').text());
	$('#editForm  input[name="unidadDeMedida"]').val($(this).parent().parent().children('td:eq(7)').text());
	$('#editForm  input[name="estado"]').val($(this).parent().parent().children('td:eq(8)').text());

	var impuesto_CESC=$(this).parent().parent().children('td:eq(9)').text();
	// $('#editForm  select[name="idProveedor"]').val();
	$('#editForm  select[name="impuesto_CESC"] option').each(function () {
		if ($(this).text().toLowerCase() == impuesto_CESC.toLowerCase()) {
			this.selected = true;
			return;
		} });
});

// Edit 
$('body').on('click', '#submitButtonEdit',function(e){
	// Prevent default submission of form
	e.preventDefault();
	// Remove previous errors
	removerErrors();   
	//aolicar el update
	saveFormPost($('#editForm'));

});