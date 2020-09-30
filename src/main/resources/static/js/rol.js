
$.getScript( "../js/global/notification-custom.js");
$.getScript("../js/global/metodosGlobales.js");

// Id Row data table
let idRow = 0;
let idDelete = 0;
const table = $('#dataTable'); 


$('body').on('click', '#addRol',function(e){
   e.preventDefault;
   $("#modalAdd").modal("show");
   // Remove previous errors
   removerErrors();
   // clean
   limpiarImput();
   
});


// Save Rol
$('body').on('click', '#submitButton',function(e){
           // Prevent default submission of form
        e.preventDefault();
        // Remove previous errors
        removerErrors();   
        saveFormPost($('#registrationForm'));

    }); 


// Show Modal Edit
$('body').on('click', '.openEditRol',function(e){
   e.preventDefault;
  
   let id =  $(this).parent().parent().children('td:eq(0)').text();
   let nombre =  $(this).parent().parent().children('td:eq(1)').text();
   let detalle =  $(this).parent().parent().children('td:eq(2)').text();
  
   // Agregando clase para editar fila data table
   $(this).parent().parent().addClass( "updateRow" );
   
   // Captura IDROW
   idRow = "row"+id;
   $('#modalEdit').modal('show');
   $('#editForm #id').val(id) ;
   $('#editForm #nombre').val(nombre);
   $('#editForm #detalle').val(detalle);
   // Remove previous errors
   removerErrors();
});


// Save Editar
$('body').on('click', '#submitButtonEdit',function(e){
        // Prevent default submission of form
        e.preventDefault();
       
        // Remove previous errors
        removerErrors();
        saveFormPost($('#editForm'));
    });
 
// eliminar
$('body').on('click', '.deleteRol',function(e){
        // Prevent default submission of form
	    e.preventDefault();
	    
	    let id =  $(this).parent().parent().children('td:eq(0)').text();
	    let nombre =  $(this).parent().parent().children('td:eq(1)').text();
       
	     
        $('#modalDelete').modal("show");
       
        $('#nombreregistro').text("Eliminar el registro: "+nombre);
        
        idDelete =id;
    });

$('body').on('click', '#btn-delete-confirm',function(e){
		   const params = {"url":"../administracion/deleterol/"+idDelete,}
		   deleteElement(params);

})

//pre cargado
$(document).ready(function() {
	init();
	// Mantiene el tamaño del dataTable
   $('.dataTables_scrollBody').css('height', '400px');
	 
});

function init(){
	
	table.DataTable( {
		
		 //para usar los botones   
        responsive: "true",
        dom: 'Bfrtilp',       
        buttons:[ 
			{
				extend:    'excelHtml5',
				text:      '<i class="fas fa-file-excel"></i> ',
				titleAttr: 'Exportar a Excel',
				className: 'btn btn-success'
			},
			{
				extend:    'pdfHtml5',
				text:      '<i class="fas fa-file-pdf"></i> ',
				titleAttr: 'Exportar a PDF',
				className: 'btn btn-danger'
			},
			{
				extend:    'print',
				text:      '<i class="fa fa-print"></i> ',
				titleAttr: 'Imprimir',
				className: 'btn btn-info'
			},
		],	        
	"searching": true,   
	 "destroy": true,
	"conditionalPaging": true,
	 "scrollY":        '330px',
     "scrollCollapse": true,
     "paging":         true,
	"ajax":{
		  "method" : "GET",
		  "url"    : "../administracion/getroles"
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
