package backendhm.serviciosRest.models.azure.repository.asistencial;

import backendhm.serviciosRest.models.azure.entity.asistencial.DatosPaciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDatosPacienteRepository extends JpaRepository<DatosPaciente,String> {
}
