package backendhm.serviciosRest.models.azure.repository.sistemasArchivos;

import backendhm.serviciosRest.models.azure.entity.TipoArchivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITipoArchivoRepository extends JpaRepository<TipoArchivo,Long> {


    @Query(value = "select *from tipo_archivo where estado=true;", nativeQuery=true)
    Optional<List<TipoArchivo>> listadoTipoArchivosHabilitados();

}
