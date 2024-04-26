package backendhm.serviciosRest.models.azure.repository;

import backendhm.serviciosRest.models.azure.entity.PrivilegioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPrivilegioRol extends JpaRepository<PrivilegioRol,Long> {
}
