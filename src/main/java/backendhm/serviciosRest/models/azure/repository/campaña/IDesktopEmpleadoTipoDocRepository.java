package backendhm.serviciosRest.models.azure.repository.campaña;

import backendhm.serviciosRest.models.azure.entity.campaña.DesktopEmpleadoTipoDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDesktopEmpleadoTipoDocRepository extends JpaRepository<DesktopEmpleadoTipoDoc,Long> {
    @Query(value = "select * from desktop_empleado_tipo_doc where dni=? and tipo_archivo=?;", nativeQuery=true)
    Optional<DesktopEmpleadoTipoDoc> listarArchivoEmpleado(long dni, String tipoArchivo);
}
