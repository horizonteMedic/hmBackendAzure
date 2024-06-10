package backendhm.serviciosRest.models.azure.repository.ocupacional;

import backendhm.serviciosRest.models.azure.entity.ocupacional.ServicioProtocolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IServicioProtocoloRepository extends JpaRepository<ServicioProtocolo,Long> {
}
