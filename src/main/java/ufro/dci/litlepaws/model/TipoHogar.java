package ufro.dci.litlepaws.model;

import java.util.HashMap;
import java.util.Map;

public enum TipoHogar {
    CASACONPATIO("Casa con patio"),
    DEPARTAMENTOPEQUEÑO("Departamento pequeño"),
    DEPARTAMENTEMEDIANOOGRANDE("Departamento Mediano o Grande"),
    PARCELA("Parcela");

    private final String nombre;

    TipoHogar(String nombre){
        this.nombre = nombre;
    }
    public String getNombre(){
        return this.nombre;
    }
    //Lookup table
    private static final Map<String, TipoHogar> lookup = new HashMap<>();

    //Populate the lookup table on loading time
    static
    {
        for(TipoHogar env : TipoHogar.values())
        {
            lookup.put(env.getNombre(), env);
        }
    }

    //This method can be used for reverse lookup purpose
    public static TipoHogar get(String url)
    {
        return lookup.get(url);
    }

}
