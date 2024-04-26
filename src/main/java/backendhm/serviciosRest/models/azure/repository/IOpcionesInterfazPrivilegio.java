package backendhm.serviciosRest.models.azure.repository;

import backendhm.serviciosRest.models.azure.entity.OpcionesInterfazPrivilegios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOpcionesInterfazPrivilegio extends JpaRepository<OpcionesInterfazPrivilegios,Long> {
}
