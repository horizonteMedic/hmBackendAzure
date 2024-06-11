package backendhm.serviciosRest.models.azure.repository.ocupacional;

import backendhm.serviciosRest.models.azure.entity.ocupacional.Protocolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IProtocoloRepository extends JpaRepository<Protocolo,Long> {
    @Query(value = "select * from protocolo_hm where  nombre_protocolo =?;", nativeQuery=true)
    Optional<Protocolo> buscarProtocoloPorNombreProto(String nombre_proto);
}
