package backendhm.serviciosRest.models.spTrujilloNP.repository;

import backendhm.serviciosRest.models.spTrujilloNP.entity.EmpleadoTipoDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEmpleadoTipoDocRepository extends JpaRepository<EmpleadoTipoDoc,Long> {
    @Query(value = "select * from empleado_tipo_doc where dni=? and tipo_archivo=?;", nativeQuery=true)
    Optional<EmpleadoTipoDoc> listarArchivoEmpleado(long dni, String tipoArchivo);
}
