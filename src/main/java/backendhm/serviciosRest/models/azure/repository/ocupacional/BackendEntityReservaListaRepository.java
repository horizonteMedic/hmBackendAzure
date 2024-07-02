package backendhm.serviciosRest.models.azure.repository.ocupacional;

import backendhm.serviciosRest.models.azure.entity.ocupacional.BackendEntityReservaLista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BackendEntityReservaListaRepository extends JpaRepository<BackendEntityReservaLista,Long> {
    @Query(value = "select * from listadoReserva(?);", nativeQuery=true)
    Optional<List<BackendEntityReservaLista>> listadoReservaPorUsername(String nombreUser);

}
