package ufro.dci.litlepaws.model.data;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ufro.dci.litlepaws.model.Usuario;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UsuarioDao extends CrudRepository<Usuario,Long> {
 List<Usuario> findByVeterinario(boolean Veterinario);
 Usuario findByRut(String rut);
}
