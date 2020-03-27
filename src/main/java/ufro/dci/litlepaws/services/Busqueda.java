package ufro.dci.litlepaws.services;

import ufro.dci.litlepaws.model.Mascota;

import java.util.ArrayList;
import java.util.List;

public class Busqueda {

    public static List<Mascota> busquedaporsexo (List<Mascota> lista,String sexo){
        if(sexo.equals("Cualquier Sexo")){
            return lista;
        }
        List<Mascota> mascotasFiltradas = new ArrayList<>();
        for(Mascota mascota :lista){
            if(mascota.getSexo().equals(sexo)){
                mascotasFiltradas.add(mascota);
            }
        }
        return mascotasFiltradas;
    }
    public static List<Mascota> busquedaportipoHogar (List<Mascota> lista,String tipoHogar){
        List<Mascota> mascotasFiltradas = new ArrayList<>();
        System.out.println("Pase por aqui");
        if(tipoHogar.equals("Cualquier Hogar")){
            return lista;
        }
        for(Mascota mascota :lista){
            if(mascota.getTipoHogarSugerido().equals(tipoHogar)){
                mascotasFiltradas.add(mascota);
                System.out.println(mascota.toString());
            }else{
                System.out.println("nada");
            }
        }
        return mascotasFiltradas;
    }

    public static List<Mascota> busquedaporchip (List<Mascota> lista,String chip){
        if(chip.equals("No es de interes")){
            return lista;
        }
        boolean conChip;
        List<Mascota> mascotasFiltradas = new ArrayList<>();
        if(chip.equals("Si")){
            conChip = true;
        }else{
            conChip =false;
        }
        for(Mascota mascota :lista){
            if(mascota.isTieneChip() && conChip==true){
                mascotasFiltradas.add(mascota);
            }else if(!mascota.isTieneChip() && conChip==false) {
                mascotasFiltradas.add(mascota);
            }
        }
        return mascotasFiltradas;
    }


    public static List<Mascota> busquedaporestirilizado (List<Mascota> lista,String estirilizado){
        if(estirilizado.equals("No es de interes")){
            return lista;
        }
        boolean esEstirilizado;
        List<Mascota> mascotasFiltradas = new ArrayList<>();
        if(estirilizado.equals("Si")){
            esEstirilizado = true;
        }else{
            esEstirilizado =false;
        }
        for(Mascota mascota :lista){
            if(mascota.isEstirilizado() && esEstirilizado){
                mascotasFiltradas.add(mascota);
            }else if(!mascota.isEstirilizado() && !esEstirilizado) {
                mascotasFiltradas.add(mascota);
            }
        }
        return mascotasFiltradas;
    }

    public static List<Mascota> busquedaporurgente (List<Mascota> lista,String urgente){
        if(urgente.equals("No es de interes")){
            return lista;
        }
        boolean esUrgente;
        List<Mascota> mascotasFiltradas = new ArrayList<>();
        if(urgente.equals("Si")){
            esUrgente = true;
        }else{
            esUrgente =false;
        }
        for(Mascota mascota :lista){
            if(mascota.isUrgente() && esUrgente){
                mascotasFiltradas.add(mascota);
            }else if(!mascota.isUrgente() && !esUrgente) {
                mascotasFiltradas.add(mascota);
            }
        }
        return mascotasFiltradas;
    }

    public static List<Mascota> busquedaporraza (List<Mascota> lista,String raza){
        if(raza.equals("Cualquier Raza")){
            return lista;
        }
        List<Mascota> mascotasFiltradas = new ArrayList<>();
        for(Mascota mascota :lista){
            if(mascota.getRaza().equals(raza)){
                mascotasFiltradas.add(mascota);
            }
        }
        return mascotasFiltradas;
    }


}
