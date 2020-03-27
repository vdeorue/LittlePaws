package ufro.dci.litlepaws.services;

import ufro.dci.litlepaws.model.Sexo;
import ufro.dci.litlepaws.model.TipoHogar;
import ufro.dci.litlepaws.model.TipoMascota;

public class Refactorizador {

    public Sexo verSexo(String sexo){
        if(sexo.equals("Macho")){
            return Sexo.MACHO;
        }else{
            return Sexo.HEMBRA;
        }
    }
    public TipoHogar verTipoHogar(String tipoHogar){
        if(tipoHogar.equals("Casa con patio")){
            return TipoHogar.CASACONPATIO;
        }else if (tipoHogar.equals("Parcela")){
            return TipoHogar.PARCELA;
        }else if (tipoHogar.equals("Departamento pequeño")){
            return TipoHogar.DEPARTAMENTOPEQUEÑO;

        }else{
            return TipoHogar.DEPARTAMENTEMEDIANOOGRANDE;
        }
    }

    public TipoMascota verTipoMascota(String tipoMascota){
        if(tipoMascota.equals("Perro")){
            return TipoMascota.PERRO;

        }else{
            return TipoMascota.GATO;
        }
    }
}
