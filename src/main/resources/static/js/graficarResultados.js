var hayGrafico = false;
var myChart = '';

/**
 * funcion que permite crear un grafico
 * @param tipo un String que contiene el tipo de grafico que se desea crear
 */
function crearGrafico(tipo,lista){

    if(hayGrafico){
            myChart.destroy();
    }
    //datos entran como
    //var url = "?codigos=a&codigos=b&usos=1&codigos=c&usos=2&usos=3";
    var url = "?";
    for (var i = 0; i < lista.length; i++){
        if(i==0){
            url = url + "codigos=" + lista[i].nombre + "&usos=" + lista[i].veces;
        }
        else{
            url = url + "&codigos=" + lista[i].nombre + "&usos=" + lista[i].veces;
        }
    }
    window.history.pushState(null, null, url);

    //window.location.href entrega la URL actual
    var temp = (parseURLParams(window.location.href));
    var codigos = temp.codigos;
    var usos = temp.usos;

    var colorArray =  setBackground(codigos.length);
    var ctx = document.getElementById("myCanvas");
    myChart = new Chart(ctx, {
        type: tipo,
        data: {
            labels: setLabels(codigos),
            datasets: [
                { label: '# de usos por codigo',
                data: usos,
                backgroundColor : colorArray,
                borderColor: setBorder(codigos.length),
                borderWidth : 1
                }
            ]
        },
        options: {
            scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
            }
        }
    });
    if(hayGrafico){
        refrescarTabla(codigos,usos);
    }
    else{
        crearTabla(codigos,usos);
    }
    hayGrafico = true;
}

/**
 * funcion que crea las etiquetas que usara el grafico
 * @param codigos un arreglo que contiene los nombres de los codigos del proyecto
 * @return labels un arreglo que contiene las etiquetas que usara el grafico
 */
function setLabels(codigos){
  var labels = [];

   for(var i = 0; i < codigos.length; i++){
      labels.push(codigos[i]);
   }

  return labels;
}


/**
 * funcion que selecciona los colores que se usaran en el grafico
 * @param cantidad un arreglo que contiene la cantidad de veces que se usa cada codigo en el proyecto
 * (debe tener un orden correspondiente al arreglo "codigos" usado en la funcion anterior)
 * @return backgrounds un arreglo que contiene los colores que usara el grafico
 */
function setBackground(cantidad){
     var backgrounds = [];
     for(var i = 0; i < cantidad; i++){
        backgrounds.push('rgba(' +
         Math.floor(Math.random() * 256) + ', ' +
         Math.floor(Math.random() * 256) + ', ' +
         Math.floor(Math.random() * 256) + ', 0.2)' );
     }
     return backgrounds;
}


/**
 * funcion que selecciona los colores que usaran los bordes del grafico (de momento son todos negros)
 * @param cantidad un arreglo que contiene la cantidad de veces que se usa cada codigo en el proyecto
 * @return borders un arreglo que contiene los colores que usaran los bordes del grafico
 */
function setBorder(cantidad){
    var borders = [];
    for(var i = 0; i< cantidad; i++){
        borders.push('rgba(0 , 0, 0, 1)' );
    }
    return borders;
}


/**
 * funcion que crea una tabla resumen con la misma informacion que el grafico
 * @param codigos un arreglo que contiene los codigos del proyecto
 * @param usos un arreglo que contiene la cantidad de veces que se usa cada codigo en el proyecto
 * (debe estar en un orden correspondiente al arreglo "codigos")
 */
function crearTabla (codigos,usos) {
    var table = document.createElement ("table");
    table.setAttribute("id", "table");
    table.border = "2px";
    table.cellSpacing = "5px";

    var tHead = table.createTHead ();
    var row = tHead.insertRow (-1);
    for (var i = 0; i < 2; i++) {
        var cell = row.insertCell (-1);
        if(i==0){
            cell.innerHTML = "Codigo";
        }else{
            cell.innerHTML = "Usos";
        }
    }

    var tBody = document.createElement ("tbody");
    table.appendChild (tBody);
    for (var j = 0; j < codigos.length; j++) {
        var row = tBody.insertRow (-1);
        for (var i = 0; i < 2; i++) {
            var cell = row.insertCell (-1);
            if(i==0){
                cell.innerHTML = codigos[j];
            }else{
                cell.innerHTML = usos[j];
            }
        }
    }

    var container = document.getElementById ("container");
    container.appendChild (table);
}

/**
 * funcion que actualiza la informacion de la tabla resumen
 * @param codigos un arreglo que contiene los codigos del proyecto
 * @param usos un arreglo que contiene la cantidad de veces que se usa cada codigo en el proyecto
 * (debe estar en un orden correspondiente al arreglo "codigos")
 */
function refrescarTabla(codigos,usos){
    var table = document.getElementById("table");
    for(var i = table.rows.length - 1; i > 0; i--){
        table.deleteRow(i);
    }
    var table = document.getElementById("table");
    var tHead = table.createTHead ();
    var row = tHead.insertRow (-1);

    var tBody = document.createElement ("tbody");
    table.appendChild (tBody);
    for (var j = 0; j < codigos.length; j++) {
        var row = tBody.insertRow (-1);
        for (var i = 0; i < 2; i++) {
            var cell = row.insertCell (-1);
            if(i==0){
                cell.innerHTML = codigos[j];
            }else{
                cell.innerHTML = usos[j];
            }
        }
    }
}


/**
 * funcion que extrae los datos a graficar desde la URL
 * @param url la direccion del sitio web
 */
function parseURLParams(url) {
    var queryStart = url.indexOf("?") + 1,
        queryEnd   = url.indexOf("#") + 1 || url.length + 1,
        query = url.slice(queryStart, queryEnd - 1),
        pairs = query.replace(/\+/g, " ").split("&"),
        parms = {}, i, n, v, nv;

    if (query === url || query === "") return;

    for (i = 0; i < pairs.length; i++) {
        nv = pairs[i].split("=", 2);
        n = decodeURIComponent(nv[0]);
        v = decodeURIComponent(nv[1]);

        if (!parms.hasOwnProperty(n)) parms[n] = [];
        parms[n].push(nv.length === 2 ? v : null);
    }
    return parms;
}