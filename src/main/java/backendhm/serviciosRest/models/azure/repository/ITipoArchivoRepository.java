package backendhm.serviciosRest.models.azure.repository;

import backendhm.serviciosRest.models.azure.entity.TipoArchivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoArchivoRepository extends JpaRepository<TipoArchivo,Long> {
}
