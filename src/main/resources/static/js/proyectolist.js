$(document).ready(function(){
  $("#proInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#tablaPro tr ").filter(function() {
      $(this).toggle($(this).find("td.nombrePro").text().toLowerCase().indexOf(value) > -1)
    });
  });
});

$(document).ready(function(){

    $("#tabla").find('div.dropdown-menu').find('a.rBtn').on('click',function(event){
            event.preventDefault();
            var href = $(this).attr('href');
            $('#renomSubmit').text('Renombrar');
            $('#renomSubmit').attr('disabled',false);
            $('#isrenom').hide();
            $.get(href,function(proyecto,status){
               $('#nombreRenom').val(proyecto.titulo);
               $('#iddocrenom').val(proyecto.id);
            });
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

            $.get(href,function(proyecto,status){
               $('#iddocborrar').val(proyecto.id);
            });
            $('.FormBorrar #modalBorrar').modal();
    });
});

$(document).ready(function(){

    $("#tabla").find('th.tda').find('a.aBtn').on('click',function(event){
              event.preventDefault();
              var href = $(this).attr('href');
              $('#isagregable').hide();
            $('#agregarSubmit').text('Agregar');
            $('#agregarSubmit').attr('disabled',false)
            $('.FormAgregar #modalAgregar').modal();
    });
});

$(document).ready(function(){
  $('[data-toggle="tooltip"]').tooltip();
});

$(document).ready(function(){

    $("#tabla").find('div.dropdown-menu').find('a.dBtn').on('click',function(event){
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

    $('.FormAgregar #modalAgregar').find('#submitAgregar').submit(function(event){
                var botoncarga = $('#agregarSubmit');
                var loadingText = "Cargando&nbsp;<i class='fas fa-sync-alt fa-spin'></i>";
                botoncarga.data('original-text',botoncarga.html());
                botoncarga.html(loadingText);
                botoncarga.attr('disabled',true);
                var ID = $(this).attr('action');
                var form = $(this);
                var formdata = {};
                var error = $('#isagregable');
                formdata["titulo"] = $('#titulo').val();
                var modal = $('.FormAgregar #modalAgregar');
                event.preventDefault();
              $.ajax({
               type: "POST",
               url: ID,
               contentType: 'application/json; charset=utf-8',
               data: JSON.stringify(formdata),
               success : function(response){
               if(response == 'FAIL'){
                 botoncarga.html(botoncarga.data('original-text'));
                 botoncarga.attr('disabled',false);
                 error.text('El nombre del proyecto ya existe, porfavor elija otro.');
                 error.fadeIn().delay(2000).fadeOut();
               }else if(response == 'FAIL2'){
                 botoncarga.html(botoncarga.data('original-text'));
                 botoncarga.attr('disabled',false);
                 error.text('Hubo un problema con la base de datos, espere un momento.');
                 error.fadeIn().delay(2000).fadeOut();
               }else{
               window.location.reload();
               }
               },error: function(e){
                                                    botoncarga.attr('disabled',false);
                                                    botoncarga.html(botoncarga.data('original-text'));
                                                    error.text('Hubo un error al agregar el proyecto,intentelo denuevo.\n Si el problema continua, le agradeceriamos que lo reportara para solucionarlo.');
                                                    error.fadeIn().delay(4000).fadeOut();
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
                 botoncarga.html(botoncarga.data('original-text'));
                 botoncarga.attr('disabled',false);
                 error.text('El nombre del proyecto ya existe, porfavor elija otro.');
                 error.fadeIn().delay(2000).fadeOut();
               }else if(response == 'FAIL2'){

                modal.modal('hide');
               }else{
               window.location.reload();
               }
               },error: function(e){
                                    botoncarga.attr('disabled',false);
                                    botoncarga.html(botoncarga.data('original-text'));
                                    error.text('Hubo un error al renombrar el proyecto,intentelo denuevo.\n Si el problema continua, le agradeceriamos que lo reportara para solucionarlo.');
                                    error.fadeIn().delay(4000).fadeOut();
                                    }
              });
    });
});