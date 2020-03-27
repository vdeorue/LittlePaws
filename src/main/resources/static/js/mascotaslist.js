
var files = [];
$(document).ready(function(){

    $("div.row").find('a.eBtn').on('click',function(event){
            event.preventDefault();
            var href = $(this).attr('href');
            $.get(href,function(mascota,status){
               $('#mascotaborrar').val(mascota.id);
            });
            $('.FormBorrar #modalBorrar').modal();
    });
});

$(document).ready(function(){

    $("div.main").find('a.bBtn').on('click',function(event){
              event.preventDefault();
            $('#mascotaSubmit').text('Buscar Mascota');
            $('#mascotaSubmit').attr('disabled',false);
            $('.FormBuscar #modalBuscar').modal();
    });
});

$(document).ready(function(){
        $('.FormBuscar #modalBuscar').on('shown.bs.modal', function(){
    });
});


$(document).ready(function(){

    $("div.main").find('a.aBtn').on('click',function(event){
              event.preventDefault();
            $('#mascotaSubmit').text('Buscar Mascota');
            $('#mascotaSubmit').attr('disabled',false);
            $('.FormAgregar #modalAgregar').modal();
    });
});

$(document).ready(function(){
           $('.FormAgregar #modalAgregar').on('shown.bs.modal', function(){
           $('#nombre').select();
                   $('upFile').val("");
                   files = [];
       });
   });

   $(document)
           .on(
                   "change",
                   "#upFile",
                   function(event) {
                    files=event.target.files;
                   });

$(document).on( "submit","#agregarform",function() {
          var loadingText = "Cargando&nbsp;<i class='fas fa-sync-alt fa-spin'></i>";
          var botoncarga = $('#agregarSubmit');
          botoncarga.data('original-text',botoncarga.html());
          botoncarga.html(loadingText);
          botoncarga.attr('disabled',true);
          var oMyForm = new FormData();
          var url = $(this).attr('action');
          var error = $('#isagregable');
          oMyForm.append("fileUpload", files[0]);
          oMyForm.append("nombre", $('#nombre').val());
          oMyForm.append("meses", $('#meses').val());
          oMyForm.append("anos", $('#anos').val());
          oMyForm.append("raza", $('#raza').val());
          oMyForm.append("descripcion", $('#descripcion').val());
          oMyForm.append("tipo", $('#tipo').val());
          oMyForm.append("tipoHogarSugerido", $('#tipoHogarSugerido').val());
          oMyForm.append("sexo", $('#sexo').val());
          if ($('#tieneChip').is(":checked")){
           oMyForm.append("tieneChip", true);
           }else{
           oMyForm.append("tieneChip", false);
           }
          oMyForm.append("numeroChip", $('#numeroChip').val());
          if ($('#urgente').is(":checked"))
          {
           oMyForm.append("urgente", true);
          }else{
          oMyForm.append("urgente", false);
          }
          if ($('#estirilizado').is(":checked"))
          {
          oMyForm.append("estirilizado", true);
          }else{
          oMyForm.append("estirilizado", false);
                    }
          event.preventDefault();
          $.ajax({
             url : url,
             data : oMyForm,
             type : "POST",
             enctype: 'multipart/form-data',
             processData: false,
             contentType:false,
             success : function(result) {
                if(result == 'Fail'){
                   botoncarga.attr('disabled',false);
                   botoncarga.html(botoncarga.data('original-text'));
                   error.text('La mascota ingresada ya existe,revise el numero de chip.');
                   error.fadeIn().delay(3000).fadeOut();
                }else{
                   window.location.href = "/LPU/mismascotas";
                }
             },error : function(result){
                           botoncarga.attr('disabled',false);
                           botoncarga.html(botoncarga.data('original-text'));
                           error.text('Hubo un error con el ingreso de su archivo.\nIntentelo denuevo y asegurece que su archivo sea menor a los 100MB.\nSi este no es el problema le agradeceriamos que lo reportara para solucionar su problema.');
                           error.fadeIn().delay(9000).fadeOut();
               }
             });
           });



    function myFunction() {
      // Get the checkbox
      var checkBox = document.getElementById("tieneChip");
      // Get the output text
      var input = document.getElementById("numeroChip");
      var text = document.getElementById("textchip");
      // If the checkbox is checked, display the output text
      if (checkBox.checked == true){
        text.style.display = "block";
        input.style.display = "block";
        input.attr("minlenght","6");
      } else {
        text.style.display = "none";
        input.style.display = "none";
        input.removeAttr("minlenght");
      }
    }

function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#blah')
                    .attr('src', e.target.result)
                    .width(150)
                    .height(150);
            };

            reader.readAsDataURL(input.files[0]);
        }
    }


