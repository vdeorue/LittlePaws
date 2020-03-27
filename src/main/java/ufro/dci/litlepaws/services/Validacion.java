package ufro.dci.litlepaws.services;

import org.springframework.beans.factory.annotation.Autowired;
import ufro.dci.litlepaws.model.Mascota;
import ufro.dci.litlepaws.model.data.MascotaDao;

import java.util.List;

public class Validacion {



    public static boolean noExisteMascota(String numeroChip, List<Mascota> mascotas){
        for(Mascota mascota : mascotas){
            if(mascota.isTieneChip() && mascota.getNumeroChip().equals(numeroChip)){
                return false;
            }
        }
        return true;
    }
}
