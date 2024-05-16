package backendhm.serviciosRest.models.azure.repository;

import backendhm.serviciosRest.models.azure.entity.TipoArchivoAsignado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITipoArchivoAsignadoRepository extends JpaRepository<TipoArchivoAsignado,Long> {
    @Query(value = "select *from tipo_archivo_asignado where id_rol=?;", nativeQuery=true)
    Optional<List<TipoArchivoAsignado>> listadoTipoArchivoAsignadoPoridRol(long idRol);
}
