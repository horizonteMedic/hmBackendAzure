package backendhm.serviciosRest.models.azure.repository;

import backendhm.serviciosRest.models.azure.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository extends JpaRepository<Rol,Long> {
}
