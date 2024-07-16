package backendhm.serviciosRest.models.azure.repository.ocupacional;

import backendhm.serviciosRest.models.azure.entity.ocupacional.CitaOcupacional;
import backendhm.serviciosRest.models.azure.entity.ocupacional.ContratoProtocolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICitaOcupacionalRepository extends JpaRepository<CitaOcupacional,Long> {
    @Query(value = "SELECT * FROM CITA_OCUPACIONAL where dni=? order by fecha_reserva desc limit 1;", nativeQuery=true)
    Optional<CitaOcupacional> buscarContratoCitaOcupacionaPorDniLimit1(long dni);
}
