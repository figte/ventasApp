$.getScript("../js/global/notification-custom.js");
$.getScript("../js/global/metodosGlobales.js");
//constantes
const tableEntradas = $('#dataTableEntradas');
const tableSalidas = $('#dataTableSalidas');

/***
 * Grafico chart JS
 * 
 */
$(document).ready(function() {
	/***
	 * Grafico chart JS
	 * 
	 */	
	new Chart(document.getElementById("radar-chart"), {
	    type: 'radar',
	    data: {
	      labels: ["Tornillos", "Ferreteria", "Hogar", "Consentrado", "Ganado"],
	      datasets: [
	        {
	          label: "1950",
	          fill: true,
	          backgroundColor: "rgba(179,181,198,0.2)",
	          borderColor: "rgba(179,181,198,1)",
	          pointBorderColor: "#fff",
	          pointBackgroundColor: "rgba(179,181,198,1)",
	          data: [8.77,55.61,21.69,6.62,6.82]
	        }, {
	          label: "2050",
	          fill: true,
	          backgroundColor: "rgba(255,99,132,0.2)",
	          borderColor: "rgba(255,99,132,1)",
	          pointBorderColor: "#fff",
	          pointBackgroundColor: "rgba(255,99,132,1)",
	          pointBorderColor: "#fff",
	          data: [25.48,54.16,7.61,8.06,4.45]
	        }
	      ]
	    },
	    options: {
	      title: {
	        display: true,
	        text: 'Distribusion en % de las categorías más vendidas'
	      }
	    }
	});
	initComprasTable();
	initSalidasTable();
	
	
	
	
});

/*
 *Metodo de inicializacion de tabla 
 *compras para reporte 
 * */
function initComprasTable() {
	tableEntradas.DataTable( {
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


function initSalidasTable() {
	tableSalidas.DataTable( {
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
