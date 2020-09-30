
$.getScript( "../js/global/notification-custom.js");
$.getScript("../js/global/metodosGlobales.js");

// Id Row data table
let idRow = 0;
let idDelete = 0;
const table = $('#dataTable'); 

//pre cargado
$(document).ready(function() {
	init();
	// Mantiene el tamaño del dataTable
   $('.dataTables_scrollBody').css('height', '400px');
	 
});

function init(){
	
	table.DataTable( {
		 
	     "searching": true,   
	 "destroy": true,
	"conditionalPaging": true,
	 "scrollY":        '330px',
     "scrollCollapse": true,
     "paging":         true,
	"ajax":{
		  "method" : "GET",
		  "url"    : "../administracion/getPuestos"
	},
	// "deferRender": true,
	"columns":[
		{"data": "correlativo"},
		{"data": "nombre"},
		{"data": "detalle"},
		{"data": "editar"},
		{"data": "eliminar"},
		],
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
$('body').on('click', '#addPuesto',function(e){
	   e.preventDefault;
	   $("#modalAdd").modal("show");
	   // Remove previous errors
	   removerErrors();
	   // clean
	   limpiarImput();
	   
});
//Save Rol
$('body').on('click', '#submitButton',function(e){
           // Prevent default submission of form
        e.preventDefault();
        // Remove previous errors
        removerErrors();   
        saveFormPost($('#registrationForm'));

});

//eliminar
$('body').on('click', '.delete',function(e){
        // Prevent default submission of form
	    e.preventDefault();
	    
	    let id =  $(this).parent().parent().children('td:eq(0)').text();
	    let nombre =  $(this).parent().parent().children('td:eq(1)').text();
       
	     
        $('#modalDelete').modal("show");
       
        $('#nombreregistro').text("Eliminar el registro: "+nombre);
        
        idDelete =id;
    });

$('body').on('click', '#btn-delete-confirm',function(e){
		   const params = {"url":"../administracion/deletePuesto/"+idDelete,}
		   deleteElement(params);

});

//Show Modal Edit
$('body').on('click', '.openEdit',function(e){
   e.preventDefault;
  
   let id =  $(this).parent().parent().children('td:eq(0)').text();
   let nombre =  $(this).parent().parent().children('td:eq(1)').text();
   let detalle =  $(this).parent().parent().children('td:eq(2)').text();
    
   // Captura IDROW
   idRow = "row"+id;
   $('#modalEdit').modal('show');
   $('#editForm #id').val(id) ;
   $('#editForm #nombre').val(nombre);
   $('#editForm #detalle').val(detalle);
   // Remove previous errors
   removerErrors();
});
//Save Editar
$('body').on('click', '#submitButtonEdit',function(e){
        // Prevent default submission of form
        e.preventDefault();
       
        // Remove previous errors
        removerErrors();
        saveFormPost($('#editForm'));
});

