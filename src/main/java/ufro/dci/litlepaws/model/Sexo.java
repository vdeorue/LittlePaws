package ufro.dci.litlepaws.model;

import java.util.HashMap;
import java.util.Map;

public enum Sexo {
    MACHO("Macho"),
    HEMBRA("Hembra");

    private final String nombre;

    Sexo(String nombre){
        this.nombre = nombre;
    }
    public String getNombre(){
        return this.nombre;
    }

    //Lookup table
    private static final Map<String, Sexo> lookup = new HashMap<>();

    //Populate the lookup table on loading time
    static
    {
        for(Sexo env : Sexo.values())
        {
            lookup.put(env.getNombre(), env);
        }
    }

    //This method can be used for reverse lookup purpose
    public static Sexo get(String url)
    {
        return lookup.get(url);
    }
}
