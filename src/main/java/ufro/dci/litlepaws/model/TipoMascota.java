package ufro.dci.litlepaws.model;

import java.util.HashMap;
import java.util.Map;

public enum TipoMascota {
    PERRO("Perro"),
    GATO("Gato");

    private final String nombre;

    TipoMascota(String nombre){
        this.nombre = nombre;
    }
    public String getNombre(){
        return this.nombre;
    }

    //Lookup table
    private static final Map<String, TipoMascota> lookup = new HashMap<>();

    //Populate the lookup table on loading time
    static
    {
        for(TipoMascota env : TipoMascota.values())
        {
            lookup.put(env.getNombre(), env);
        }
    }

    //This method can be used for reverse lookup purpose
    public static TipoMascota get(String url)
    {
        return lookup.get(url);
    }
}
