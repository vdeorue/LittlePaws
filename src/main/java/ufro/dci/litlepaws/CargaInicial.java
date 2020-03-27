package ufro.dci.litlepaws;

import org.omg.CORBA.UnknownUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ufro.dci.litlepaws.model.Mascota;
import ufro.dci.litlepaws.model.Usuario;
import ufro.dci.litlepaws.model.data.MascotaDao;
import ufro.dci.litlepaws.model.data.UsuarioDao;

@Configuration
public class CargaInicial {

    @Autowired
    UsuarioDao usuarioDao;

    @Autowired
    MascotaDao mascotaDao;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Bean
    public CommandLineRunner demo(UsuarioDao usuarioDao) {

        return (args -> {
            Usuario u1 = new Usuario();
            u1.setNombre("Agrupación Canes y Felinos");
            u1.setRut("19.305.243-6");
            u1.setContraseña(bCryptPasswordEncoder.encode("1234"));
            u1.setEmail("AgrupacionCanesYFelinos@hotmail.com");
            u1.setTelefono("+56989342110");
            u1.setTwitter("www.twitter.com/Canesyfelinos");
            usuarioDao.save(u1);
            Usuario ua = new Usuario();
            ua.setNombre("Vicente de orue");
            ua.setRut("00.000.000-0");
            ua.setContraseña(bCryptPasswordEncoder.encode("1234"));
            ua.setEmail("Vicente@hotmail.com");
            ua.setTelefono("+56900000000");
            ua.setFacebook("www.Facebook.com/VdeOrue");
            usuarioDao.save(ua);
        });
    }

    @Bean
    public CommandLineRunner demo3(UsuarioDao usuarioDao) {

        return (args -> {
            Usuario u2 = new Usuario();
            u2.setNombre("Jorge Vidal");
            u2.setEmail("JorgeVeterinario@hotmail.com");
            u2.setTelefono("+56989342110");
            u2.setDireccion("Varas 0186, Temuco");
            u2.setVeterinario(true);
            usuarioDao.save(u2);

            Usuario u3 = new Usuario();
            u3.setNombre("Javiera Gonzales");
            u3.setEmail("JavieraVeterinario@hotmail.com");
            u3.setTelefono("+56912343210");
            u3.setDireccion("Heras 0286, Temuco");
            u3.setVeterinario(true);
            usuarioDao.save(u3);

            Usuario u4 = new Usuario();
            u4.setNombre("Francisco perez");
            u4.setEmail("FranciscoVeterinario@hotmail.com");
            u4.setTelefono("+56989432110");
            u4.setDireccion("Carrera 0136, Temuco");
            u4.setVeterinario(true);
            usuarioDao.save(u4);

            Usuario u5 = new Usuario();
            u5.setNombre("Jose Vidal");
            u5.setEmail("JoseVeterinario@hotmail.com");
            u5.setTelefono("+56984562110");
            u5.setDireccion("Barros Arana 1286, Temuco");
            u5.setVeterinario(true);
            usuarioDao.save(u5);

            Usuario u6 = new Usuario();
            u6.setNombre("Juana Rodriguez");
            u6.setEmail("JuanaVeterinario@hotmail.com");
            u6.setTelefono("+56989342333");
            u6.setDireccion("Valparaiso 2186, Temuco");
            u6.setVeterinario(true);
            usuarioDao.save(u6);
        });
    }
}



    /*
    @Bean
    public CommandLineRunner demo1  (MascotaDao mascotaDao) {

        return (args -> {
            Mascota m1 = new Mascota();
            Mascota m2 = new Mascota();
            Mascota m3 = new Mascota();
            Mascota m4 = new Mascota();
            Mascota m5 = new Mascota();
            Mascota m6 = new Mascota();
            Mascota m7 = new Mascota();
            Mascota m8 = new Mascota();
            m1.setTipo("Perro");
            m2.setTipo("Perro");
            m3.setTipo("Perro");
            m4.setTipo("Perro");
            m5.setTipo("Gato");
            m6.setTipo("Gato");
            m7.setTipo("Gato");
            m8.setTipo("Gato");
            m1.setNombre("Juan");
            m2.setNombre("Juanita");
            m3.setNombre("Joan");
            m4.setNombre("Juan carlos");
            m5.setNombre("Jorge");
            m6.setNombre("Jesus");
            m7.setNombre("Jose");
            m8.setNombre("Josefa");
            m1.setSexo("Macho");
            m2.setSexo("Hembra");
            m3.setSexo("Macho");
            m4.setSexo("Macho");
            m5.setSexo("Macho");
            m6.setSexo("Hembra");
            m7.setSexo("Macho");
            m8.setSexo("Hembra");
            m1.setTipoHogarSugerido("Parcela");
            m2.setTipoHogarSugerido("Departamento pequeño");
            m3.setTipoHogarSugerido("Parcela");
            m4.setTipoHogarSugerido("Departamento Mediano o Grande");
            m5.setTipoHogarSugerido("Casa con patio");
            m6.setTipoHogarSugerido("Parcela");
            m7.setTipoHogarSugerido("Casa con patio");
            m8.setTipoHogarSugerido("Departamento pequeño");
            m1.setDescripcion("Buen Perro");
            m2.setDescripcion("Ladra mucho pero protege bien");
            m3.setDescripcion("Cariñosito");
            m4.setDescripcion("Le gusta que lo saquen a pasear seguido");
            m5.setDescripcion("Independiente");
            m6.setDescripcion("Le gusta solamente whiskas");
            m7.setDescripcion("Gordito");
            m8.setDescripcion("Recien nacido");
            m1.setEdad(1);
            m2.setEdad(10);
            m3.setEdad(6);
            m4.setEdad(5);
            m5.setEdad(7);
            m6.setEdad(7);
            m7.setEdad(2);
            m8.setEdad(3);
            m1.setRaza("Yorkshire");
            m2.setRaza("Labrador");
            m3.setRaza("Kiltro");
            m4.setRaza("Pastor Aleman");
            m5.setRaza("Persa");
            m6.setRaza("Bengala");
            m7.setRaza("Siamese");
            m8.setRaza("Abisino");
            m1.isEstirilizado();
            m3.isEstirilizado();
            m4.isEstirilizado();
            m6.isEstirilizado();
            m8.isEstirilizado();
            m1.isTieneChip();
            m2.isTieneChip();
            m7.isTieneChip();
            m8.isTieneChip();
            m5.isTieneChip();
            m1.setNumeroChip("103923-2");
            m2.setNumeroChip("103923-3");
            m7.setNumeroChip("103923-2");
            m8.setNumeroChip("103923-4");
            m5.setNumeroChip("103923-1");
            mascotaDao.save(m1);
            mascotaDao.save(m2);
            mascotaDao.save(m3);
            mascotaDao.save(m4);
            mascotaDao.save(m5);
            mascotaDao.save(m6);
            mascotaDao.save(m7);
            mascotaDao.save(m8);

        });
    }

     */


