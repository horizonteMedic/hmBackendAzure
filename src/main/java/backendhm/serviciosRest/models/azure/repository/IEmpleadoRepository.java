package backendhm.serviciosRest.models.azure.repository;

import backendhm.serviciosRest.models.azure.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEmpleadoRepository extends JpaRepository <Empleado,Long> {

    @Query(value = "select *from empleado where num_documento=?;", nativeQuery=true)
    Optional<Empleado> datosEmpleadoPorNroDoc(Long hc);

}
