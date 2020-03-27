package ufro.dci.litlepaws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.Request;
import ufro.dci.litlepaws.model.*;
import ufro.dci.litlepaws.model.data.MascotaDao;
import ufro.dci.litlepaws.model.data.UsuarioDao;
import ufro.dci.litlepaws.services.Busqueda;
import ufro.dci.litlepaws.services.Refactorizador;
import ufro.dci.litlepaws.services.Validacion;

import javax.crypto.Mac;
import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping(value = "/LPU")
public class UsuarioController {

    @Autowired
    private MascotaDao mascotaDao;

    @Autowired
    private UsuarioDao usuarioDao;

    private Busqueda busqueda;

    private Validacion validacion;

    private Refactorizador refactorizador;

    @GetMapping(value="/vermascota/{id}")
    public String verMascota(@PathVariable Long id,Model modelo){
        Optional<Mascota> mascota = mascotaDao.findById(id);
        modelo.addAttribute("encargado",mascota.get().getEncargado());
        modelo.addAttribute("mascota",mascota.get());
        return "usuario/vermascota";
    }

    @GetMapping(value="/vermimascota/{id}")
    public String verMisMascota(@PathVariable Long id,Model modelo,Authentication aut){
        Usuario u1 = usuarioDao.findByRut(aut.getName());
        Optional<Mascota> mascota = mascotaDao.findById(id);
        modelo.addAttribute("encargado",mascota.get().getEncargado());
        modelo.addAttribute("mascota",mascota.get());
        return "usuario/vermimascota";
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
            return "usuario/listado";
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
        return  "usuario/listado";
    }

    @RequestMapping(value="mismascotas", method= RequestMethod.GET)
    public String misMascotas (Model modelo, Authentication aut){
        Usuario u1 = usuarioDao.findByRut(aut.getName());
        List<Mascota> misMascotas = mascotaDao.findByEncargado_IdOrderByUrgenteDesc(u1.getId());
        modelo.addAttribute("Vacia",misMascotas.isEmpty());
        modelo.addAttribute("mascota",misMascotas);
        return "/usuario/mismascotas";
    }

    @GetMapping("/encontrar/{id}")
    @ResponseBody
    public Mascota encontrar(@PathVariable Long id,Authentication aut){
            Usuario u1 = usuarioDao.findByRut(aut.getName());
            Mascota mk = new Mascota();
            Optional<Mascota> m1 = mascotaDao.findById(id);
            if(m1.get().getEncargado().getId() == u1.getId()){
                System.out.println(m1.get().getId());

                return m1.get();
            }
        System.out.println("asdasdad");
            return mk;
    }


    @RequestMapping(value="eliminar",method=RequestMethod.POST)
    public String eliminarMascota(Mascota mascota1, Authentication aut){
        Usuario u1 = usuarioDao.findByRut(aut.getName());
        List<Mascota> mascotas = mascotaDao.findByEncargado_IdOrderByUrgenteDesc(u1.getId());
        if(mascotas.isEmpty()){
            return "redirect:/LPU/mismascotas";
        }
        for(Mascota mascota: mascotas){
            if(mascota.getId() == mascota1.getId()){
                mascotaDao.delete(mascota);
            }
        }
        return "redirect:/LPU/mismascotas";
    }


    @RequestMapping(value = "imagen/{id}")
    @ResponseBody
    public byte[] getImage(@PathVariable(value = "id") Long id) throws IOException {
        Optional<Mascota> mas = mascotaDao.findById(id);
        return mas.get().getImagen();
    }

    @PostMapping(value ="/agregar")
    @ResponseBody
    public String agregarMascota(@RequestParam MultipartFile fileUpload,Mascota mascota,
                                 Model modelo, Authentication aut) throws IOException {
        List<Mascota> mascotas = mascotaDao.findAll();
        Usuario u1 = usuarioDao.findByRut(aut.getName());
        System.out.println(mascota.getSexo()+mascota.getNumeroChip());
        System.out.println(validacion.noExisteMascota(mascota.getNumeroChip(),mascotas));
        if(validacion.noExisteMascota(mascota.getNumeroChip(),mascotas)) {
            mascota.setImagen(fileUpload.getBytes());
            mascota.setEncargado(u1);
            mascota.cacularEdad();
            mascotaDao.save(mascota);
            return "Sucess";
        }else{
            return "Fail";
        }
    }

    @GetMapping(value="/veterinarios")
    public String verVeterinarios(Model modelo){
        List<Usuario> veterinarios = usuarioDao.findByVeterinario(true);
        modelo.addAttribute("veterinarios",veterinarios);
        return "usuario/veterinarios";
    }
}
