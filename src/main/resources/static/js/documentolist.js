var files = [];
$(document).ready(function(){
  $("#docInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#tablaDoc tr ").filter(function() {
      $(this).toggle($(this).find("td.nombreDoc").text().toLowerCase().indexOf(value) > -1)
    });
  });
});

$(document).ready(function(){

    $("#tabla").find('div.dropdown-menu').find('a.rBtn').on('click',function(event){
            event.preventDefault();
            var href = $(this).attr('href');

            $.get(href,function(documento,status){
               $('#nombreRenom').val(documento.titulo);
               $('#iddocrenom').val(documento.id);
            });
            $('#isrenom').hide();
            $('#renomSubmit').text('Renombrar');
            $('#renomSubmit').attr('disabled',false);
            $('.FormRenom #modalRenombrar').modal();

    });
});

$(document).ready(function(){
        $('.FormRenom #modalRenombrar').on('shown.bs.modal', function(){
        $('#nombreRenom').select();
    });
});

$(document).ready(function(){

    $("#tabla").find('div.dropdown-menu').find('a.bBtn').on('click',function(event){
            event.preventDefault();
            var href = $(this).attr('href');

            $.get(href,function(documento,status){
               $('#iddocborrar').val(documento.id);
            });
            $('.FormBorrar #modalBorrar').modal();
    });
});

$(document).ready(function(){

    $("#tabla").find('div.dropdown-menu').find('a.mBtn').on('click',function(event){
            event.preventDefault();
            var href = $(this).attr('href');
            $('#ismov').hide();
            $('#moverSubmit').attr('disabled',false);
            $('#moverSubmit').text('Mover');
            $.get(href,function(documento,status){
               $('#iddocmov').val(documento.id);
            });
            $('.FormMover #modalMover').modal();
    });
});

$(document).ready(function(){

    $("#tabla").find('th.tda').find('a.aBtn').on('click',function(event){
              event.preventDefault();
              $('#isagregable').hide();
              var href = $(this).attr('href');
            $('#fileSubmit').text('Subir Archivo');
            $('#fileSubmit').attr('disabled',false);
            $('.FormAgregar #modalAgregar').modal();
    });
});

$(document).ready(function(){
        $('.FormAgregar #modalAgregar').on('shown.bs.modal', function(){
        $('upFile').val("");
        files = [];
    });
});

$(document).ready(function(){
  $('[data-toggle="tooltip"]').tooltip();
});

$(document).ready(function(){

    $("#tabla").find('th.tda').find('a.dBtn').on('click',function(event){
                var ID = $(this).attr('href');
                event.preventDefault();
              $.ajax({
              context: this,
               type: "GET",
               url: ID,
               success : function(response){
               if(response == 'FAIL'){
               var mymodal = $('.FormDescargar #modalDescargar');
               mymodal.modal('show');
               mymodal.data('hideInterval', setTimeout(function(){
               mymodal.modal('hide');
                }, 3000));
               }else{
               document.location = ID;
               }
               }
              });
    });
});

$(document).ready(function(){

    $('.FormRenom #modalRenombrar').find('#submitRenom').submit(function(event){
                var botoncarga = $('#renomSubmit');
                var loadingText = "Cargando&nbsp;<i class='fas fa-sync-alt fa-spin'></i>";
                botoncarga.data('original-text',botoncarga.html());
                botoncarga.html(loadingText);
                botoncarga.attr('disabled',true);
                var ID = $(this).attr('action');
                var form = $(this);
                var formdata = {};
                var error = $('#isrenom');
                formdata["id"] = $('#iddocrenom').val();
                formdata["titulo"] = $('#nombreRenom').val();
                var modal = $('.FormRenom #modalRenombrar');
                event.preventDefault();
              $.ajax({
               type: "POST",
               url: ID,
               contentType: 'application/json; charset=utf-8',
               data: JSON.stringify(formdata),
               success : function(response){
               if(response == 'FAIL1'){
                 botoncarga.attr('disabled',false);
                 botoncarga.html(botoncarga.data('original-text'));
                 error.text('El nombre del archivo ya existe, porfavor elija otro.');
                 error.fadeIn().delay(3000).fadeOut();
               }else if(response == 'FAIL2'){
                modal.modal('hide');
               }else{
               window.location.reload();
               }
               },error: function(e){
                                    botoncarga.attr('disabled',false);
                                    botoncarga.html(botoncarga.data('original-text'));
                                    error.text('Hubo un error al renombrar el archivo,intentelo denuevo.\n Si el problema continua, le agradeceriamos que lo reportara para solucionarlo.');
                                    error.fadeIn().delay(4000).fadeOut();
                }
              });
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
   var botoncarga = $('#fileSubmit');
   botoncarga.data('original-text',botoncarga.html());
   botoncarga.html(loadingText);
   botoncarga.attr('disabled',true);
   var oMyForm = new FormData();
   var url = $(this).attr('action');
   var error = $('#isagregable');
   oMyForm.append("fileUpload", files[0]);
   oMyForm.append("titulo", $('#titulo').val());
   event.preventDefault();
   $.ajax({
      url : url,
      data : oMyForm,
      type : "POST",
      enctype: 'multipart/form-data',
      processData: false,
      contentType:false,
      success : function(result) {
         if(result == 'FAIL'){
            botoncarga.attr('disabled',false);
            botoncarga.html(botoncarga.data('original-text'));
            error.text('El nombre del archivo ya existe, porfavor elija otro.');
            error.fadeIn().delay(3000).fadeOut();
         }else if(result == 'FAIL2'){
            botoncarga.attr('disabled',false);
            botoncarga.html(botoncarga.data('original-text'));
            error.text('El nombre original del archivo no es legible, porfavor renombrelo.');
            error.fadeIn().delay(3000).fadeOut();
         }else if(result == "FAIL3"){
            botoncarga.attr('disabled',false);
            botoncarga.html(botoncarga.data('original-text'));
            error.text('El formato del archivo no es legible. Formatos aceptados: doc,docx,txt.');
            error.fadeIn().delay(3000).fadeOut();
         }else{
            window.location.reload();

         }
      },error : function(result){
                    botoncarga.attr('disabled',false);
                    botoncarga.html(botoncarga.data('original-text'));
                    error.text('Hubo un error con el ingreso de su archivo.\nIntentelo denuevo y asegurece que su archivo sea menor a los 100MB.\nSi este no es el problema le agradeceriamos que lo reportara para solucionar su problema.');
                    error.fadeIn().delay(9000).fadeOut();
        }
      });
    });


$(document).ready(function(){

    $('.FormMover #modalMover').find('#submitMover').submit(function(event){
                var botoncarga = $('#moverSubmit');
                var loadingText = "Cargando&nbsp;<i class='fas fa-sync-alt fa-spin'></i>";
                botoncarga.data('original-text',botoncarga.html());
                botoncarga.html(loadingText);
                botoncarga.attr('disabled',true);
                var ID = $(this).attr('action');
                var form = $(this);
                var oMyForm = new FormData();
                var error = $('#ismov');
                oMyForm.append("id",$('#iddocmov').val());
                oMyForm.append("titulo",$('#nuevonombre').val());
                oMyForm.append("idpromov",$('#idpromov').val());
                var modal = $('.FormMover #modalMover');
                event.preventDefault();
              $.ajax({
               type: "POST",
               url: ID,
               processData: false,
               contentType:false,
               data: oMyForm,
               success : function(response){
               if(response == 'FAIL'){
               botoncarga.attr('disabled',false);
               botoncarga.html(botoncarga.data('original-text'));
                 error.text('El nombre del archivo ya existe en el proyecto destino, porfavor elija otro.');
                 error.fadeIn().delay(3000).fadeOut();
               }else{
               window.location.reload();
               }
               },error: function(e){
                    botoncarga.attr('disabled',false);
                    botoncarga.html(botoncarga.data('original-text'));
                    error.text('Hubo un error al mover el archivo,intentelo denuevo.\n Si el problema continua, le agradeceriamos que lo reportara para solucionarlo.');
                    error.fadeIn().delay(4000).fadeOut();                }
              });
    });
});
