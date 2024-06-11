package backendhm.serviciosRest.models.azure.repository.ocupacional;

import backendhm.serviciosRest.models.azure.entity.ocupacional.ContratoProtocolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IContratoProtocoloRepository extends JpaRepository<ContratoProtocolo,Long> {
    @Query(value = "select * from contrato_protocolo_hm  where id_protocolo=?;", nativeQuery=true)
    Optional<List<ContratoProtocolo>> buscarContratoProtocoloPorIdProtocolo(long protocoloId);
}
