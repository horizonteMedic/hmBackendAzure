package backendhm.serviciosRest.models.azure.repository.ocupacional;

import backendhm.serviciosRest.models.azure.entity.ocupacional.ServicioProtocolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IServicioProtocoloRepository extends JpaRepository<ServicioProtocolo,Long> {
    @Query(value = "select * from servicios_asignados_protocolos where id_protocolo=?;", nativeQuery=true)
    Optional<List<ServicioProtocolo>> buscarServicioProtocoloPorIdProtocolo(long protocoloId);
}
