package backendhm.serviciosRest.models.azure.repository;

import backendhm.serviciosRest.models.azure.entity.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRol extends JpaRepository<UsuarioRol,Long> {
}
