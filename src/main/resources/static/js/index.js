    $(document).ready(function(){

        $("div.index-head").find('a.lBtn').on('click',function(event){
                  event.preventDefault();
                $('#loginSubmit').text('Iniciar Sesión');
                $('#loginSubmit').attr('disabled',false);
                $('.FormLogin #modalLogin').modal();
        });
    });

    $(document).ready(function(){
            $('.FormLogin #modalLogin').on('shown.bs.modal', function(){
            $('#rut').select();
        });
    });

 $(document).on( "submit","#loginform",function() {
       var loadingText = "Cargando&nbsp;<i class='fas fa-sync-alt fa-spin'></i>";
       var botoncarga = $('#loginSubmit');
       botoncarga.data('original-text',botoncarga.html());
       botoncarga.html(loadingText);
       botoncarga.attr('disabled',true);
       var oMyForm = new FormData();
       var url = $(this).attr('action');
       var errores = $('#warning');
       oMyForm.append("rut", $('#rut').val());
       oMyForm.append("contraseña", $('#contraseña').val());
       event.preventDefault();
       $.ajax({
          url : url,
          data : oMyForm,
          type : "POST",
          processData: false,
          contentType:false,
          success : function(result) {
             if(result == 'http://localhost:8080/login?error'){
                botoncarga.attr('disabled',false);
                botoncarga.html(botoncarga.data('original-text'));
                errores.text('La contraseña no es la correcta,intente denuevo');
                errores.fadeIn().delay(3000).fadeOut();
             }else{
                   window.location.href = "/LPU/mismascotas";
             }
          },error : function(result){
                        botoncarga.attr('disabled',false);
                        botoncarga.html(botoncarga.data('original-text'));
                        errores.show();
                        errores.fadeIn().delay(9000).fadeOut();
            }
          });
        });

    $(document).ready(function(){

        $("div.index-head").find('a.rBtn').on('click',function(event){
                  event.preventDefault();
                $('.FormRegistrar #modalRegistrar').modal();
        });
    });

    $(document).ready(function(){
            $('.FormRegistrar #modalRegistrar').on('shown.bs.modal', function(){
        });
    });

    $(document).ready(function(){

        $("div.row").find('a.bBtn').on('click',function(event){
                  event.preventDefault();
                $('#mascotaSubmit').text('Buscar Mascota');
                $('#mascotaSubmit').attr('disabled',false);
                $('.FormBuscar2 #modalBuscar').modal();
        });
    });

    $(document).ready(function(){
            $('.FormBuscar2 #modalBuscar').on('shown.bs.modal', function(){
        });
    });