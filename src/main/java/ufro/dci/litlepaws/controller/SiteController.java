package ufro.dci.litlepaws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ufro.dci.litlepaws.model.Mascota;
import ufro.dci.litlepaws.model.Usuario;
import ufro.dci.litlepaws.model.data.MascotaDao;
import ufro.dci.litlepaws.model.data.UsuarioDao;
import ufro.dci.litlepaws.services.Busqueda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller(value="")
public class SiteController {

    @Autowired
    UsuarioDao usuarioDao;

    @Autowired
    MascotaDao mascotaDao;

    Busqueda busqueda;

    @Autowired
    private BCryptPasswordEncoder crypt;

    @GetMapping(value="")
    public String index(){
        return "sitio/index";
    }

    @RequestMapping(value = "registrar/{user}")
    public String registrar(@PathVariable String user){
        return "sitio/registro";
    }

    @RequestMapping(value = "guarda", method = RequestMethod.POST)
    public String registra(Model model, @ModelAttribute(value = "newUser") Usuario usuario){
        usuario.setContraseña(crypt.encode(usuario.getContraseña()));
        if (usuarioDao.findByRut(usuario.getRut()) != null){
            model.addAttribute("yaRegistrado",true);
            return "sitio/registro";
        }
        usuarioDao.save(usuario);
        model.addAttribute("registrado",true);
        return "redirect:/";
    }

    @RequestMapping(value="login",method = RequestMethod.GET)
        public String login(){
        return "redirect:/";
    }

    @GetMapping(value="vermascota/{id}")
    public String verMascota(@PathVariable Long id,Model modelo){
        Optional<Mascota> mascota = mascotaDao.findById(id);
        modelo.addAttribute("encargado",mascota.get().getEncargado());
        modelo.addAttribute("mascota",mascota.get());
        return "sitio/vermascota";
    }

    @GetMapping(value = "/listado")
    public String listadoMascotasBuscadas(@RequestParam(required = false, defaultValue = "ninguno") String tipo,
                                          @RequestParam(required = false) String edad,
                                          @RequestParam(required = false) String tipoHogar,
                                          @RequestParam(required = false) String chip,
                                          @RequestParam(required = false) String esterilizado,
                                          @RequestParam(required = false) String sexo,
                                          @RequestParam(required = false)String urgente,
                                          @RequestParam(required = false) String raza,
                                          Model modelo){
        if(tipo.equals("ninguno")){
            List<Mascota> todasMascotas = mascotaDao.findAll();
            System.out.println(todasMascotas.isEmpty());
            modelo.addAttribute("Vacia",todasMascotas.isEmpty());
            modelo.addAttribute("mascota",todasMascotas);
            return "sitio/listado";
        }
        System.out.println(tipo+sexo+tipoHogar+chip+esterilizado);
        List<Mascota> mascotas = mascotaDao.findMascotaByTipoOrderByUrgenteDesc(tipo);
        for(Mascota mascota: mascotas){
            System.out.println(mascota.toString());
        }
        List<Mascota> filtroMascota1 = new ArrayList<>(busqueda.busquedaportipoHogar(mascotas,tipoHogar));
        List<Mascota> filtroMascota2 = new ArrayList<>(busqueda.busquedaporsexo(filtroMascota1,sexo));
        List<Mascota> filtroMascota3 = new ArrayList<>(busqueda.busquedaporchip(filtroMascota2,chip));
        List<Mascota> filtroMascota4 = new ArrayList<>(busqueda.busquedaporestirilizado(filtroMascota3,esterilizado));
        List<Mascota> filtroMascota = new ArrayList<>(busqueda.busquedaporurgente(filtroMascota4,urgente));
        //filtroMascota = busqueda.busquedaporraza(mascotas,raza);
        for(Mascota mascota : filtroMascota){
            System.out.println(mascota.toString());
        }
        modelo.addAttribute("Vacia",filtroMascota.isEmpty());
        modelo.addAttribute("mascota",filtroMascota);
        return  "sitio/listado";
    }


    @RequestMapping(value = "imagen/{id}")
    @ResponseBody
    public byte[] getImage(@PathVariable(value = "id") Long id) throws IOException {
        Optional<Mascota> mas = mascotaDao.findById(id);
        return mas.get().getImagen();
    }



    @GetMapping(value="/veterinarios")
    public String verVeterinarios(Model modelo){
        List<Usuario> veterinarios = usuarioDao.findByVeterinario(true);
        modelo.addAttribute("veterinarios",veterinarios);
        return "sitio/veterinarios";
    }

}
