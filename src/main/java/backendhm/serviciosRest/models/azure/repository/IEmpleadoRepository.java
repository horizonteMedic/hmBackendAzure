package backendhm.serviciosRest.models.azure.repository;

import backendhm.serviciosRest.models.azure.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEmpleadoRepository extends JpaRepository <Empleado,Long> {

    @Query(value = "select *from empleado where num_documento=?;", nativeQuery=true)
    Optional<Empleado> datosEmpleadoPorNroDoc(Long hc);

    @Query(value = "select *from listado_empleado_por_username(?);", nativeQuery=true)
    Optional<List<Empleado>> listadoEmpleadosPorUsername(String userName);
}
