package backendhm.serviciosRest.models.azure.repository;

import backendhm.serviciosRest.models.azure.entity.HistorialPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IHistorialPacienteRepository extends JpaRepository<HistorialPaciente,Long> {

    @Query(value = "select *from listado_datos_pacientes_por_usuario(:id_usuario)", nativeQuery=true)
    Optional<List<HistorialPaciente>> listadoHistorialPaciente(Long id_usuario);
}
