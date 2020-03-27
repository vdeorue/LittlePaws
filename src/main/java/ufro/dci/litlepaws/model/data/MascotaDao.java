package ufro.dci.litlepaws.model.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ufro.dci.litlepaws.model.Mascota;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface MascotaDao extends CrudRepository<Mascota,Long> {
    List<Mascota> findAll();
    List<Mascota> findMascotaByTipoOrderByUrgenteDesc(String tipo);
    List<Mascota> findByEncargado_IdOrderByUrgenteDesc(Long id);
}
