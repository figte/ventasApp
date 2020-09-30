//Importamos los metodos
$.getScript("../js/global/notification-custom.js");
$.getScript("../js/global/metodosGlobales.js");

// Id Row data table
let idRow = 0;
let idDelete = 0;
const table = $('#dataTable');

let totales={sumas:0.00,noSujetas:0.00,exentas:0.00,iva:0.00,cesc:0.00};

let  serieActual={id:0,serie:"",correlativo:0};
const producto={codigo:"",nombre:""};

var fecha = new Date(); //Fecha actual
var mes = fecha.getMonth()+1; //obteniendo mes
var dia = fecha.getDate(); //obteniendo dia
var ano = fecha.getFullYear(); //obteniendo año
if(dia<10)
  dia='0'+dia; //agrega cero si el menor de 10
if(mes<10)
  mes='0'+mes //agrega cero si el menor de 10

var fecha=ano+"-"+mes+"-"+dia;


//Cargar al inicio
$(document).ready(function() {
    $("#tProductos").DataTable({
        
		// "searching" : true,
		// "destroy" : true,
		// "conditionalPaging" : true,
		// "scrollY" : '330px',
		// "scrollCollapse" : true,
        // "paging" : true,
        
	
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
	// Mantiene el tamaño del dataTable
    $('.dataTables_scrollBody').css('height', '400px');
    
    //se carga la serie y el correlativo correspondiente
    cargarSerie();
    
    //se carga la fecha actual
    $("#fecha").val(fecha);

    //funcion para cargar producto
    $(".agregarProducto").click(function(){
        cargarProducto($(this).parent().parent().children('td:eq(0)').text(),$(this).parent().parent().children('td:eq(1)').text());
        // alert(producto.codigo);
    });

    $("#agregarProducto").click(agregarProducto);

    $("#guardarVenta").click(guardar);

    $("body").on('click', '.eliminarDetalle', function(){
        var $d = $(this).parent("td");     
      //  var col = $d.parent().children().index($d);
         var row = $d.parent().parent().children().index($d.parent());
            //alert(col + ' ' + row);
       eliminarDetalle(row);
    });

    cargarTotales();
   
});

function erroPeticion(response){
    successAlert("<strong>Error</strong>", "<br>" + response+ "", "danger");// mostrar
    console.log(response);
}

function cargarSerie() {
    $.ajax({
        url:"/administracion/allSeries",
        method:"Get",
        success:function(response){
             response.forEach(i => {
                 if(i.estado=="activo"){
                    serieActual.id=i.id;
                    serieActual.serie=i.serie;
                    serieActual.correlativo=i.correlativoActual;
                     $("#serie").val(serieActual.serie);
                     $("#correlativo").val(serieActual.correlativo);
                 }
             });
        },
        error:null
    });

  
    // $("#serie").val(serie.serie);
    // $("#correlativo").val(serie.correlativo);
}

function cargarProducto(codigo, nombre){
    producto.codigo=codigo;
    producto.nombre=nombre;
    $("#producto").val(producto.nombre);
}

function agregarProducto() {
    if(producto.codigo=="" || $("#cantidad").val()<1){   
        successAlert("<strong>Error</strong>", "<br> No se agrego el detalle, asegurese de seleccionar un producto, y que la cantidad sea mayor a cero", "danger");// mostrar
    }else{
        $.ajax({
            headers: { "X-CSRF-TOKEN": $("input[name='_csrf']").val() },        
            url:"/ventas/agregarDetalle",
            method:"Post",
            data:{
                cantidad:$("#cantidad").val(),
                idProducto:producto.codigo,
                tipoDetalle:$("#tipoDetalle").val()
            },
            success:function(response){
                // alert(response.mensaje);
                successAlert("<strong>Éxito</strong>", "<br>Detalle agregado ", "success");// Mostrar
                $("#cantidad").val(0);
                $("#producto").val("");
                producto.codigo="";
                producto.nombre="";
                cargarDetalles();
            },
            error:function(response){
            //  alert("NO SE AGREGO EL DETALLE: "+response.mensaje);
             successAlert("<strong>Error</strong>", "<br>Detalle no agregado", "danger");// mostrar
             console.log(response);
            }   
        });
    }
   
}

function cargarDetalles() {
    $.ajax({
        url:"/ventas/allDetalles",
        method:"Get",
        success:peticionDetalles,
        error:function(response){

         }
    });
}

function peticionDetalles(response){

    $("#tDetalles").html("");
     totales.sumas=0.00;
     totales.exentas=0.00;
     totales.noSujetas=0.00;
     totales.iva=0.00;
     totales.cesc=0.00;
    
      response.forEach(i => {
        var tipoDetalle=i.tipo_detalle;
        let gravado=0.00;
        let exento=0.00;
        let noSujeto=0.00;
        let total=0.00;
    
        if(tipoDetalle=="gravado"){
          gravado=redondearDecimales((i.producto.precio*1.13),2);
          total=redondearDecimales(((i.producto.precio*1.13)*i.cantidad),2);

          totales.sumas=totales.sumas+((i.producto.precio)*i.cantidad);
          totales.iva=totales.iva+((i.producto.precio*0.13)*i.cantidad);

          if(i.producto.impuesto_CESC){
              totales.cesc=totales.cesc+((i.producto.precio*0.05)*i.cantidad);
          }

        
        }
        if(tipoDetalle=="exento"){
            exento=redondearDecimales((i.producto.precio),2);
            total=redondearDecimales(((i.producto.precio)*i.cantidad),2);

            totales.exentas=totales.exentas+total;

          
        }
        if(tipoDetalle=="noSujeto"){
            noSujeto=redondearDecimales((i.producto.precio),2);
            total=redondearDecimales(((i.producto.precio)*i.cantidad),2);
            totales.noSujetas=totales.noSujetas+total;

           
        }
            $("#tDetalles").append(""
            +"<tr>"
                +"<td>"+i.cantidad+"</td>"
                +"<td>"+i.producto.codigo+"</td>"
                +"<td>"+i.producto.nombre+"</td>"
                +"<td>"+i.producto.unidadDeMedida+"</td>"
                +"<td>$"+gravado+"</td>"
                +"<td>"+noSujeto+"</td>"
                +"<td>"+exento+"</td>"
                +"<td>$"+total+"</td>"
                +"<td><button type='button' class='btn btn-danger eliminarDetalle'>Eliminar</button></td>"
            +"</tr>"
            +"");  
            
          

            console.log(totales);
      });
      cargarTotales();
}

function eliminarDetalle(id){
   
   // alert(id);
    $.ajax({
        url:"/ventas/eliminarDetalle/"+id,
        method:"Get",
        success:function(response){
            cargarDetalles();
        },
        error:erroPeticion
    });
}

function cargarTotales() {
    $("#sumas_comprobante").val("$"+redondearDecimales((totales.sumas),2));
    $("#totalNoSujetas").val("$"+redondearDecimales((totales.noSujetas),2));
    $("#totalExentas").val("$"+redondearDecimales((totales.exentas),2));
    $("#iva_comprobante").val("$"+redondearDecimales((totales.iva),2));
    $("#cesc_comprobante").val("$"+redondearDecimales((totales.cesc),2));
    $("#total_comprobante").val("$"+redondearDecimales((totales.sumas+totales.exentas+totales.noSujetas+totales.cesc+totales.iva),2));
 //    alert(total);
}  

function guardar() {

        // Prevent default submission of form
      //  e.preventDefault();
        // Remove previous errors
        removerErrors();  

   let tipo_documento;
   if( $('#factura').prop('checked') ) {
    tipo_documento="factura";
   }
   if( $('#ticket').prop('checked') ) {
    tipo_documento="ticket";
   }

    $.ajax({
        headers: { "X-CSRF-TOKEN": $("input[name='_csrf']").val() },
         url:"/ventas/save",
         method:"Post",
         data:{
             fecha:$("#fecha").val(),
             cliente:$("#cliente").val(),
             tipoDocumento:tipo_documento,
             observacion:$("#observación_comprobante").val(),
             idSerie:serieActual.id,
             sumas:redondearDecimales(totales.sumas,2),
             iva:redondearDecimales(totales.iva,2),
             cesc:redondearDecimales(totales.cesc,2),
             exento:redondearDecimales(totales.exentas,2),
             noSujeto:redondearDecimales(totales.noSujetas,2),
             total:redondearDecimales((totales.sumas+totales.iva+totales.cesc+totales.exentas+totales.noSujetas),2)
         },
         success:function(res){
            //  alert("VENTA GUARDADA CORRECTAMENTE...");
             //successAlert("<strong>Éxito</strong>", "<br>VENTA GUARDADA CORRECTAMENTE... ", "success");// Mostrar
         
           if (res.validated) {

            // limpiarImput();// clean
            // let numbreRowDataTable = $('#dataTable tr').length;// 3 es por

            // if (numbreRowDataTable >= 3) {
            //     try {
            //         table.DataTable().ajax.reload(null, false);// actualizar data table
            //     } catch (e) {
            //         //solventa el error al estar vacia la data table
            //         table.DataTable.ext.errMode = "none";
            //         $(table).on("error.dt", (e, settings, techNote, message) => {
            //             console.log("Error: " + message);
            //         });
            //     }

            // }

            // $('#modalAdd').fadeOut(() => {// Ocultar Modal
            //     $('#modalAdd').modal('hide');
            // });
            // $('#modalEdit').fadeOut(() => {// Ocultar Edit Modal
            //     $('#modalEdit').modal('hide');
            // });

            successAlert("<strong>Éxito</strong>", "<br>Transacción realizada", "success");// Mostrar
            // la
            // alerta
            //abrir vista de impresion de factura.
            var id;
            
            $.each(res.errorMessages, function (key, value) {
                if (key == 'idVenta'){
                    id=value;
                }
            });

            url = "/ventas/report/"+id;
            window.open(url, '_blank');

           location.reload();
        } else {

            // add alerta formulario vacio
            $('#general-content').before('<div class="alert alert-danger text-center mt-2" role="alert" id="alerta"> Complete el formulario </div>').animate(4000);

            // Mostrar los errores de validacion por parte de backend
            $.each(res.errorMessages, function (key, value) {
                if (key == 'permisos') $('.permisos').after('<span class="form-text text-danger text-justify">' + value + '</span>');
                if (key != 'permisos') $('input[name=' + key + ']').after('<span class="form-text text-danger text-justify">' + value + '</span>');
                $('textarea[name=' + key + ']').after('<span class="form-text text-danger">' + value + '</span>');
                $('select[name=' + key + ']').after('<span class="form-text text-danger">' + value + '</span>');
                $('table[id=' + key + ']').after('<span class="form-text text-danger">' + value + '</span>');
            });
        }
         },
         error:function(response){
             console.log(response);
             successAlert("<strong>Éxito</strong>", "<br>VENTA NO GUARDADA CORRECTAMENTE... ", "danger");// Mostrar
            //  alert("VENTA NO GUARDADA ...");
         }

    });
}

function calculoTotal() {
}

