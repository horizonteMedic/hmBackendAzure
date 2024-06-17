package backendhm.serviciosRest.models.azure.repository.ocupacional;

import backendhm.serviciosRest.models.azure.entity.ocupacional.CitaOcupacional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICitaOcupacionalRepository extends JpaRepository<CitaOcupacional,Long> {
}
