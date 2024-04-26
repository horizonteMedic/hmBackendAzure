package backendhm.serviciosRest.models.azure.repository;

import backendhm.serviciosRest.models.azure.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpleadoRepository extends JpaRepository <Empleado,Long> {
}
